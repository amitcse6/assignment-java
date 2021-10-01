package com.am.assignment.dto.person;

import com.am.assignment.dto.addtess.AddressResponse;
import lombok.Data;

@Data
public class PersonResponse {
    private Long id;
    private Long parentId;
    private String firstName;
    private String lastName;
    private AddressResponse address;
}
