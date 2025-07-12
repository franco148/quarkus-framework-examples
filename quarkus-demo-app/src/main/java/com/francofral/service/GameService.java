package com.francofral.service;

import jakarta.enterprise.context.Dependent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * DI options
 * - @ApplicationScoped: Only one instance when the application starts
 * - @Singleton: Only one instance for the whole application, not managed by the CDI mechanisms
 * - @RequestScoped: Per request, can't be shared with other users
 * - @Dependent
 */
@Dependent
public class GameService {

    @ConfigProperty(name = "test")
    String test;

    public void test(){
        System.out.println(test);
    }
}

