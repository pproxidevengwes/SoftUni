package com.paintingscollectors.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {
    private long id;
    private String username;

    public void login(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public boolean isLoggedIn() {
        return this.id > 0;
    }

    public long id() {
        return id;
    }

    public String username() {
        return username;
    }

    public void logout() {
        this.id = 0;
        this.username = null;
    }

}
