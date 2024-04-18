package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class ServiceLaundry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "category should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    @Pattern(regexp ="^(clothing|home|carpet)$", message = "category must be clothing or home or carpet")
    private String category;
    @NotEmpty(message = "name should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    private String name;
    @NotEmpty(message = "service type should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    @Pattern(regexp ="^(laundry|presser|ironAndWash|steamIroning)$", message = "service type must be laundry or presser or ironAndWash or steamIroning")
    private String serviceType;
    @NotNull(message = "price should not be null")
    @Column(columnDefinition = "int not null")
    private Double price;
    private Integer numberOrder=0;



    @ManyToOne
    @JoinColumn(name = "branch_id",referencedColumnName = "id")
    @JsonIgnore
    private Branch branch;

    @ManyToMany(mappedBy = "serviceLaundries")
    private Set<Orders> orders;

}
