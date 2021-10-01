package com.am.assignment.dto.person;

import com.am.assignment.dto.addtess.AddressRequest;
import lombok.Data;

@Data
public class PersonRequest {
    private Long id;
    private Long parentId;
    private String firstName;
    private String lastName;
    private AddressRequest address;
}
