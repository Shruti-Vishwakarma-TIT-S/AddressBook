package com.addressbook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {

    // UC1- Validation-----------------------------------------
    @Id
    private Long id;


    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must contain only letters and spaces")

    private String name;
    private String address;
    private String phone;
}
