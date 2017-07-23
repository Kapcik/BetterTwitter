package com.bettertwitter;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.fest.assertions.Assertions.assertThat;

public class WallResourceTest extends BaseResourceTest {

    @Test
    public void shouldThrowResourceNotFoundException() {
        try {
            restTemplate.getForObject(getBaseUrl(), String.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    protected String getBaseUrl() {
        return super.getBaseUrl() + "wall/1";
    }
}
