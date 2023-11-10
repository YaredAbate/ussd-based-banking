package com.example.demo.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Date dob;
    private String homePostalAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String homePhone;
    private String mobileNo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="accountId")
    List<Account> accounts;
    public Customer() {
    }

    public Customer(String firstName, String lastName, String middleName, String email, Date dob, String homePostalAddress,
                    String city, String state, String postalCode, String country, String homePhone, String mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.dob = dob;
        this.homePostalAddress = homePostalAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", homePostalAddress='" + homePostalAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
