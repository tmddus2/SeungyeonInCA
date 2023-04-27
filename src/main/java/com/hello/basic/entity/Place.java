package com.hello.basic.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Float score;

    private String comment;

    @ManyToOne
    private Travel travel;
}
