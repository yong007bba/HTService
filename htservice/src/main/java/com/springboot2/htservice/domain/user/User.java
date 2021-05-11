package com.springboot2.htservice.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_ID")
    private Long id;

    @Column(nullable = false, name = "U_NAME")
    private String name;

    @Column(nullable = false, name = "U_EMAIL")
    private String email;

    @Column(name = "U_GENDER")
    private Gender gender;

    @Builder
    public User(Long id, String name, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }


    public User update(String name){
        this.name = name;

        return this;
    }
}
