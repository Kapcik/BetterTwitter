package com.bettertwitter.rest;

import com.bettertwitter.data.User;
import com.bettertwitter.data.UserRepository;
import com.bettertwitter.exception.UserChecker;
import com.bettertwitter.utils.Deserializer;
import com.bettertwitter.utils.UserTypes;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/follow", produces = MediaType.APPLICATION_JSON_VALUE)
public class FollowResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String add(@PathVariable("id") long userId, @RequestBody String jsonFollowing) {

        User user = userRepository.findOne(userId);
        UserChecker.checkIfUserNotNull(user, UserTypes.USER);

        User following = deserializeFollowing(jsonFollowing);
        UserChecker.checkIfUserNotNull(following, UserTypes.FOLLOWING);

        user.getFallowing().add(following);

        userRepository.save(user);

        return HttpStatus.OK.toString();
    }

    private User deserializeFollowing(String jsonFollowing) {
        JsonElement element = new JsonParser().parse(jsonFollowing);
        Long followingId = Deserializer.deserializeFollowing(element.getAsJsonObject());
        return userRepository.findOne(followingId);
    }

}
