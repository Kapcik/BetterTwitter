package com.bettertwitter.utils;

import com.bettertwitter.data.Post;
import com.bettertwitter.data.User;
import com.bettertwitter.exception.PostTooLongException;
import com.bettertwitter.rest.PostResource;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;

public class Deserializer {

    public static User deserializeUser(JsonObject jsonUser) {
        User user = new User();

        JsonElement jsonFirstName = jsonUser.get("firstName");
        if (checkIfNotNull(jsonFirstName)) {
            user.setFirstName(jsonFirstName.getAsString());
        }

        JsonElement jsonLastName = jsonUser.get("lastName");
        if (checkIfNotNull(jsonLastName)) {
            user.setLastName(jsonLastName.getAsString());
        }

        user.setCreationDate(new Date());

        return user;
    }

    public static Post deserializePost(JsonObject jsonPost) {
        Post post = new Post();

        JsonElement jsonContent = jsonPost.get("content");
        if (checkIfNotNull(jsonContent)) {
            if (jsonContent.getAsString().length() > PostResource.POST_MAX_LENGTH) {
                throw new PostTooLongException();
            }
            post.setContent(jsonContent.getAsString());
        }

        return post;
    }

    public static Long deserializeFollowing(JsonObject element) {
        JsonElement jsonContent = element.get("following");
        if (checkIfNotNull(jsonContent)) {
            return Long.parseLong(jsonContent.getAsString());
        }
        return null;
    }

    private static boolean checkIfNotNull(JsonElement element) {
        return element != null && !element.isJsonNull();
    }
}
