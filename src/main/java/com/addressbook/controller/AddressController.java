package com.addressbook.controller;

import com.addressbook.dto.AddressDTO;
import com.addressbook.model.AddressModel;
import com.addressbook.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {

    //UC1-Validation-----------------------------------------------------------

    @Autowired
    private AddressService addressService;

    // Convert AddressModel to AddressDTO
    private AddressDTO convertToDTO(AddressModel addressModel) {
        return new AddressDTO(addressModel.getId(), addressModel.getName(), addressModel.getAddress(), addressModel.getPhone());
    }

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        log.info("Fetching all addresses.");
        List<AddressModel> addresses = addressService.getAllAddresses();
        List<AddressDTO> addressDTOs = addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        log.info("Successfully fetched {} addresses.", addressDTOs.size());
        return ResponseEntity.ok(addressDTOs);
    }

    // Get an address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        log.info("Fetching address with ID: {}", id);
        Optional<AddressModel> address = addressService.getAddressById(id);
        if (address.isPresent()) {
            log.info("Address found: {}", address.get());
            return ResponseEntity.ok(convertToDTO(address.get()));
        } else {
            log.warn("Address with ID: {} not found.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Add a new address
    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        log.info("Adding a new address: {}", addressDTO);
        AddressModel addressModel = new AddressModel(addressDTO.getId(), addressDTO.getName(), addressDTO.getAddress(), addressDTO.getPhone());
        AddressModel savedAddress = addressService.addAddress(addressModel);
        log.info("Successfully added new address with ID: {}", savedAddress.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedAddress));
    }

    // Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        log.info("Updating address with ID: {}", id);
        Optional<AddressModel> updatedAddress = addressService.getAddressById(id);
        if (updatedAddress.isPresent()) {
            AddressModel addressModel = new AddressModel(id, addressDTO.getName(), addressDTO.getAddress(), addressDTO.getPhone());
            AddressModel result = addressService.updateAddress(id, addressModel);
            log.info("Successfully updated address with ID: {}", id);
            return ResponseEntity.ok(convertToDTO(result));
        } else {
            log.warn("Address with ID: {} not found for update.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete an address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        log.info("Attempting to delete address with ID: {}", id);
        boolean isDeleted = addressService.deleteAddress(id);
        if (isDeleted) {
            log.info("Successfully deleted address with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.warn("Address with ID: {} not found for deletion.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
