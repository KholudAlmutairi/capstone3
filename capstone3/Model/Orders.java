package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private double servicePrice;
    private double deliveryPrice=10;


    private double totalPrice;


    private String nationalAddressOfCustomer;
    private String phoneNumberForCustomer;
    private String districtOfLaundry;
    private String streetOfLaundry;


//    @Max(5)
//    @Min(0)
//    private Integer rating=0;


    private String comment;

    @Pattern(regexp ="^(Waiting|accepted|received stuff|received to laundry|Delivered)$")
    private String status ;

//    private Integer Quantity;
      private Double evaluation=0.0;



    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_id",referencedColumnName = "id")
    @JsonIgnore
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "branch_id",referencedColumnName = "id")
    @JsonIgnore
    private Branch branch1;


    @ManyToMany
    @JsonIgnore
    private Set<ServiceLaundry> serviceLaundries;



}
