package com.example.split.service;

import io.split.client.SplitClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class SayHelloService {
    public static final String OFF = "off";
    public static final String ON = "on";
    public static final String ADD_DATE_FEATURE = "feature-1";

    @Autowired
    private SplitClient splitClient;

    public String getMessage(String user) {
        String message = "Hello %s";

        switch (splitClient.getTreatment(user, ADD_DATE_FEATURE)){
            case OFF: return sayHello(user, message);
            case ON: return  addDateToResponse(sayHello(user, message));
            default: throw new IllegalStateException();
        }
    }

    private static String sayHello(String user, String message) {
        return String.format(message, user);
    }

    private String addDateToResponse(String message) {
        return message+", current date is "+ZonedDateTime.now();
    }
}
