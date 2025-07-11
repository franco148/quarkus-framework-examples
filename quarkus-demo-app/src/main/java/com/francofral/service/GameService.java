package com.francofral.service;

import jakarta.enterprise.context.Dependent;

/**
 * DI options
 * - @ApplicationScoped: Only one instance when the application starts
 * - @Singleton: Only one instance for the whole application, not managed by the CDI mechanisms
 * - @RequestScoped: Per request, can't be shared with other users
 * - @Dependent
 */
@Dependent
public class GameService {
    public void test(){
        System.out.println("TEST");
    }
}

