package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Profile {

    @Id
    private Integer id;

    @Column(columnDefinition = "int")
    private Integer age;

    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(30) not null")
    private String district;

    @Column(columnDefinition = "varchar(8) not null")
    private String nationalAddress;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Customer customer;

}
