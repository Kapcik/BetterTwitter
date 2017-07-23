package com.bettertwitter.rest;

import com.bettertwitter.data.Post;
import com.bettertwitter.data.PostRepository;
import com.bettertwitter.data.User;
import com.bettertwitter.data.UserRepository;
import com.bettertwitter.utils.Deserializer;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/rest/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostResource {

    public static final long POST_MAX_LENGTH = 140;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String add(@PathVariable("id") long userId, @RequestBody String jsonPost) {

        User user = userRepository.findOne(userId);

        Post post = deserializePost(jsonPost);
        post.setCreationDate(new Date());
        post.setWall(user.getWall());

        user.getWall().getPostList().add(post);

        postRepository.save(post);
        return HttpStatus.OK.toString();
    }

    private Post deserializePost(String jsonPost) {
        return Deserializer.deserializePost(new JsonParser().parse(jsonPost).getAsJsonObject());
    }

}
