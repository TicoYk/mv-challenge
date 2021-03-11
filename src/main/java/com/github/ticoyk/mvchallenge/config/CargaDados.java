package com.github.ticoyk.mvchallenge.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CargaDados {
    
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    public void init() {
        if(activeProfile.equals("dev")){
        }
    } 
}