package com.bettertwitter.rest;

import com.bettertwitter.data.User;
import com.bettertwitter.data.UserRepository;
import com.bettertwitter.utils.Deserializer;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Long create(@RequestBody String jsonUser) {

        User user = deserializeUser(jsonUser);

        userRepository.save(user);

        return user.getId();
    }

    private User deserializeUser(String jsonUser) {
        return Deserializer.deserializeUser(new JsonParser().parse(jsonUser).getAsJsonObject());
    }

}
