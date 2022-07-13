package com.example.springboot.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String photo; //name of file

    public Passport(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }
}
