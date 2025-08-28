package com.ecommerce.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @NotBlank
    @Size(min = 5,message = "Building name must be atleast 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 5,message = "Street name must be atleast 5 characters")
    private String street;

    @NotBlank
    @Size(min = 4,message = "City name must be atleast 4 characters")
    private String city;

    @NotBlank
    @Size(min = 2,message = "State name must be atleast 4 characters")
    private String state;

    @NotBlank
    @Size(min = 2,message = "Country name must be atleast 5 characters")
    private String country;

    @NotBlank
    @Size(min = 5,message = "Pincode must be atleast 5 characters")
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

/*
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
*/

    public Address(String buildingName, String street, String city,
                   String state, String country, String pincode) {
        this.buildingName = buildingName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}

