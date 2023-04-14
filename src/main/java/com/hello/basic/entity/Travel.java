package com.hello.basic.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "travel")
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<Cost> costs = new ArrayList<>();

}
