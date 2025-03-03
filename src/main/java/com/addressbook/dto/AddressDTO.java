package com.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  // Lombok generates a no-args constructor
@AllArgsConstructor // Lombok generates an all-args constructor
public class AddressDTO {

    // UC2- Servicelayer ---------------------------------------
    private Long id;
    private String name;
    private String address;
    private String phone;


}
