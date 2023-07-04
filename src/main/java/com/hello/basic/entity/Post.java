package com.hello.basic.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "travel_id", insertable = false, updatable = false)
    private Travel travel;

    @Column(name = "travel_id")
    private Long travelId;

    @Builder
    public Post(String title, String content, Long userId, String picture, Long travelId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.picture = picture;
        this.travelId = travelId;
    }
}
