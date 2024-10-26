package com.jozitechies.StreetSmart.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "UserTBL")
public class User {

    @Id
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;

}
