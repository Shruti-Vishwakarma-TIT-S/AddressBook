package com.addressbook.controller;

import com.addressbook.model.AddressModel;
import com.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Update an existing address
    @PutMapping("/{id}")
    public AddressModel updateAddress(@PathVariable Long id, @RequestBody AddressModel address) {
        return addressService.updateAddress(id, address);
    }

    // Delete an address
    @DeleteMapping("/{id}")
    public boolean deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id);
    }
}
