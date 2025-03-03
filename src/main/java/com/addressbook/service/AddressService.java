package com.addressbook.service;

import com.addressbook.model.AddressModel;
import com.addressbook.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    // UC2-ServiceLayer-------------------------------------------
    @Autowired
    private AddressRepository addressRepository;

    // Get all addresses
    public List<AddressModel> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get an address by ID
    public Optional<AddressModel> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Add a new address
    public AddressModel addAddress(AddressModel address) {
        return addressRepository.save(address);
    }

    //UC2- RestController-------------------------------------------
    // Update an existing address
    public AddressModel updateAddress(Long id, AddressModel address) {
        address.setId(id);
        return addressRepository.save(address);
    }

    // Delete an address
    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
