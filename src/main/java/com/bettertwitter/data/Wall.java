package com.bettertwitter.data;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wall extends AbstractPersistable<Long> {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wall", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<Post>();

    public Wall() {
    }

    public Wall(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
