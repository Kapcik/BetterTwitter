package com.bettertwitter.data;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Post extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "wall_id", nullable = false)
    private Wall wall;

    @Length(max = 140)
    private String content;

    @Column(updatable = false)
    private Date creationDate;

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
