package com.bettertwitter;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import static org.fest.assertions.Assertions.assertThat;

public class PostResourceTest extends BaseResourceTest {


    @Test
    public void shouldCreatePost() {

        String userUrl = getBaseUrl() + "user";
        HttpEntity<String> userEntity1 = new HttpEntity<String>("{firstName:\"Jan\", lastName: \"Kowalski\"}");
        String userResponse = restTemplate.postForObject(userUrl, userEntity1, String.class);
        assertThat(userResponse).isNotNull();

        HttpEntity<String> entity = new HttpEntity<String>("{content:\"test content\"}");
        String postUrl = getBaseUrl() + "post/" + userResponse;
        String forObject = restTemplate.postForObject(postUrl, entity, String.class);

        assertThat(forObject).isEqualTo(HttpStatus.OK.toString());

    }
}
