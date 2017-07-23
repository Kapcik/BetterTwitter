package com.bettertwitter;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BetterTwitter.class)
@WebIntegrationTest(randomPort = true)
public abstract class BaseResourceTest {

    @Value("${local.server.port}")
    protected int port;
    protected RestTemplate restTemplate = new RestTemplate();

    protected String getBaseUrl() {
        return "http://localhost:" + port + "/rest/";
    }

}
