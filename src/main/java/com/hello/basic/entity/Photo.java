package com.hello.basic.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne
    private Travel travel;
}
