package com.bettertwitter.rest;

import com.bettertwitter.data.Post;
import com.bettertwitter.data.User;
import com.bettertwitter.data.UserRepository;
import com.bettertwitter.data.WallRepository;
import com.bettertwitter.exception.UserChecker;
import com.bettertwitter.exception.UserNotFoundException;
import com.bettertwitter.utils.Serializer;
import com.bettertwitter.utils.UserTypes;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping(value = "/rest/wall", produces = MediaType.APPLICATION_JSON_VALUE)
public class WallResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") long userId) {

        User user = userRepository.findOne(userId);

        UserChecker.checkIfUserNotNull(user, UserTypes.USER);

        Collections.sort(user.getWall().getPostList(), new Comparator<Post>() {
            public int compare(Post o2, Post o1) {
                System.out.println(o1.getCreationDate() + " " + o2.getCreationDate());
                return o1.getCreationDate().compareTo(o2.getCreationDate());
            }
        });

        JsonArray jsonPosts = Serializer.serializePosts(user.getWall().getPostList());

        return jsonPosts.toString();
    }

}
