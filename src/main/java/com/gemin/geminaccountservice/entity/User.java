package com.gemin.geminaccountservice.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "users", uniqueConstraints= @UniqueConstraint(columnNames = "email"))
public class User extends BaseEntity {

    @Column(name = "firstname", length = 100)
    private String firstName;

    @Column(name = "lastname", length = 100)
    private String lastName;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "phone_number", length = 16)
    private String phoneNumber;
}