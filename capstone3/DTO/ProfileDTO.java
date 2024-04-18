package com.example.capstone3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDTO {

    private Integer customer_id;

    @Positive
    private Integer age;

    @NotEmpty(message = "email can not null")
    @Email(message = "email must be valid email")
    private String email;

    @NotEmpty(message = "phone number can not null")
    private String phoneNumber;

    @NotEmpty(message = "district can not null")
    private String district;

    @NotEmpty(message = "national address can not null")
    private String nationalAddress;
}
