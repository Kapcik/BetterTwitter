package com.bettertwitter.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DefaultResource {

    @RequestMapping
    public String get() {
        return "Better Twitter Application ";
    }

}
