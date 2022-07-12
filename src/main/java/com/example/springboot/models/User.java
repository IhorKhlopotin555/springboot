package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    //@JsonIgnore
    private Passport passport;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_card",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_card")
    )
    private List<Card> cards;

}
