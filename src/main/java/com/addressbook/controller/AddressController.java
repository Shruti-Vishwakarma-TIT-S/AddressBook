package com.addressbook.controller;

import com.addressbook.model.AddressModel;
import com.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    //UC1- Create AddressBook ------------------------
    @Autowired
    private AddressService addressService;

    // Get all addresses
    @GetMapping
    public List<AddressModel> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Get an address by ID
    @GetMapping("/{id}")
    public Optional<AddressModel> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    // Add a new address
    @PostMapping
    public AddressModel addAddress(@RequestBody AddressModel address) {
        return addressService.addAddress(address);
    }

    //UC2-Rest controller ----------------------------------------------------
    // Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable Long id, @RequestBody AddressModel address) {
        Optional<AddressModel> updatedAddress = addressService.getAddressById(id);
        if (updatedAddress.isPresent()) {
            AddressModel result = addressService.updateAddress(id, address);
            return ResponseEntity.ok(result);
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
