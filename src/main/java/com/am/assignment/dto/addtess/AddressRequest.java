package com.am.assignment.dto.addtess;

import lombok.Data;

@Data
public class AddressRequest {
    private Long id;
    private String street;
    private String city;
    private String zip;
}
