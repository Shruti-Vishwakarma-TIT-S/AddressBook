package com.addressbook.repository;

import com.addressbook.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

// UC1 - Create a AddressBook
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
}
