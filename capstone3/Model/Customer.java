package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can not be null")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private Profile profile;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
    private Set<Orders> orders;
}
