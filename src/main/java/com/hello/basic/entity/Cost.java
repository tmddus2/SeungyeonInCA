package com.hello.basic.entity;

import javax.persistence.*;

@Entity
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float amount;

    @ManyToOne
    private Travel travel;
}
