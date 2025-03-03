package com.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    // UC1- Lombok Library  -----------------------------------------------
    private Long id;
    private String name;
    private String address;
    private String phone;


}
