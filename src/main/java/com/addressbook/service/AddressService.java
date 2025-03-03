package com.addressbook.service;

import com.addressbook.model.AddressModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    // In-memory data store (List)
    private List<AddressModel> addressList = new ArrayList<>();

    // Get all addresses
    public List<AddressModel> getAllAddresses() {
        return addressList;
    }

    // Get an address by ID
    public Optional<AddressModel> getAddressById(Long id) {
        return addressList.stream()
                .filter(address -> address.getId().equals(id))
                .findFirst();
    }

    // Add a new address
    public AddressModel addAddress(AddressModel address) {
        // Simulate auto-incrementing ID
        long nextId = addressList.size() + 1;
        address.setId(nextId);
        addressList.add(address);
        return address;
    }

    // Update an existing address
    public AddressModel updateAddress(Long id, AddressModel address) {
        Optional<AddressModel> existingAddress = getAddressById(id);
        if (existingAddress.isPresent()) {
            // Replace the old address with the new address
            address.setId(id);
            addressList.remove(existingAddress.get());
            addressList.add(address);
            return address;
        }
        return null;  // Address not found
    }

    // Delete an address
    public boolean deleteAddress(Long id) {
        Optional<AddressModel> address = getAddressById(id);
        if (address.isPresent()) {
            addressList.remove(address.get());
            return true;
        }
        return false;
    }
}
