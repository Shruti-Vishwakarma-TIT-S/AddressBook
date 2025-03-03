package com.addressbook.controller;

import com.addressbook.dto.AddressDTO;
import com.addressbook.model.AddressModel;
import com.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    // UC2-Service Layer-----------------------------------------------
    @Autowired
    private AddressService addressService;

    // Convert AddressModel to AddressDTO
    private AddressDTO convertToDTO(AddressModel addressModel) {
        return new AddressDTO(addressModel.getId(), addressModel.getName(), addressModel.getAddress(), addressModel.getPhone());
    }

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressModel> addresses = addressService.getAllAddresses();
        List<AddressDTO> addressDTOs = addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(addressDTOs);
    }

    // Get an address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressModel> address = addressService.getAddressById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok(convertToDTO(address.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Add a new address
    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        AddressModel addressModel = new AddressModel(addressDTO.getId(), addressDTO.getName(), addressDTO.getAddress(), addressDTO.getPhone());
        AddressModel savedAddress = addressService.addAddress(addressModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedAddress));
    }

    // Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        Optional<AddressModel> updatedAddress = addressService.getAddressById(id);
        if (updatedAddress.isPresent()) {
            AddressModel addressModel = new AddressModel(id, addressDTO.getName(), addressDTO.getAddress(), addressDTO.getPhone());
            AddressModel result = addressService.updateAddress(id, addressModel);
            return ResponseEntity.ok(convertToDTO(result));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete an address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        boolean isDeleted = addressService.deleteAddress(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
