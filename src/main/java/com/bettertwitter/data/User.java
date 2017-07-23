package com.bettertwitter.data;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends AbstractPersistable<Long> {

    @Length(max = 20)
    private String firstName;

    @Length(max = 20)
    private String lastName;

    @Column(updatable = false)
    private Date creationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "i_me_fallow", joinColumns = {@JoinColumn(name = "following_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> followers = new HashSet<User>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "i_me_fallow", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "following_id")})
    private Set<User> fallowing = new HashSet<User>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wall wall = new Wall(this);

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFallowing() {
        return fallowing;
    }

    public void setFallowing(Set<User> fallowing) {
        this.fallowing = fallowing;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
}
