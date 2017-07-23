package com.bettertwitter;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import static org.fest.assertions.Assertions.assertThat;

public class TimelineResourceTest extends BaseResourceTest {

    private String timelineResponseBody = "[{\"userId\":2,\"posts\":[{\"id\":2,\"content\":\"test content\"}]}]";

    @Test
    public void shouldReturnAllFollowingUsersPosts() {

        String userUrl = getBaseUrl() + "user";
        HttpEntity<String> userEntity1 = new HttpEntity<String>("{firstName:\"Jan\", lastName: \"Kowalski\"}");
        String userResponse1 = restTemplate.postForObject(userUrl, userEntity1, String.class);
        assertThat(userResponse1).isNotNull();

        HttpEntity<String> userEntity2 = new HttpEntity<String>("{firstName:\"Mirek\", lastName: \"Nowak\"}");
        String userResponse2 = restTemplate.postForObject(userUrl, userEntity2, String.class);
        assertThat(userResponse2).isNotNull();

        HttpEntity<String> postEntity = new HttpEntity<String>("{content:\"test content\"}");

        String postUrl1 = getBaseUrl() + "post/" + userResponse1;
        String postResponse1 = restTemplate.postForObject(postUrl1, postEntity, String.class);
        assertThat(postResponse1).isNotNull();

        String postUrl2 = getBaseUrl() + "post/" + userResponse2;
        String postResponse2 = restTemplate.postForObject(postUrl2, postEntity, String.class);
        assertThat(postResponse2).isNotNull();

        HttpEntity<String> fallowEntity = new HttpEntity<String>("{following:2}");
        String followUrl = getBaseUrl() + "follow/" + userResponse1;
        String followResponse = restTemplate.postForObject(followUrl, fallowEntity, String.class);
        assertThat(followResponse).isEqualTo(HttpStatus.OK.toString());

        String timelineUrl = getBaseUrl() + "timeline/1";
        String timelineResponse = restTemplate.getForObject(timelineUrl, String.class);
        assertThat(timelineResponse).isEqualTo(timelineResponseBody);
    }

}
