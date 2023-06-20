package com.hello.basic.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "travel")
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<Cost> costs = new ArrayList<>();

}
