package com.example.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "roles")
@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

}
