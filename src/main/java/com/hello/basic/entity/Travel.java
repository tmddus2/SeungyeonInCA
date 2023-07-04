package com.hello.basic.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "travel")
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<Cost> costs = new ArrayList<>();

    @OneToOne(mappedBy = "travel")
    private Post post;

    @Builder
    public Travel(Long userId) {
        this.userId = userId;
    }
}
