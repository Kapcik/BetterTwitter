package com.bettertwitter.utils;


import com.bettertwitter.data.Post;
import com.bettertwitter.data.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Serializer {

    public static JsonArray serializePosts(List<Post> postList) {

        JsonArray postArray = new JsonArray();
        for (Post post : postList) {
            postArray.add(makeJsonPost(post));
        }

        return postArray;
    }

    public static JsonObject makeJsonPost(Post post) {

        JsonObject jsonPost = new JsonObject();

        if (post != null) {
            jsonPost.addProperty("id", post.getId());
            jsonPost.addProperty("content", post.getContent());
        }

        return jsonPost;
    }

    public static JsonArray serializeFollowingPosts(Set<User> following) {
        JsonArray fallowingArray = new JsonArray();

        for (User user : following) {
            JsonArray postArray = new JsonArray();
            Collections.sort(user.getWall().getPostList(), new Comparator<Post>() {
                public int compare(Post o2, Post o1) {
                    return o1.getCreationDate().compareTo(o2.getCreationDate());
                }
            });
            for (Post post : user.getWall().getPostList()) {
                postArray.add(makeJsonPost(post));
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userId", user.getId());
            jsonObject.add("posts", postArray);
            fallowingArray.add(jsonObject);
        }

        return fallowingArray;
    }

}
