package com.addressbook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {

    // UC1-DTO and Model-----------------------------------
    @Id
    private Long id;
    private String name;
    private String address;
    private String phone;
}
