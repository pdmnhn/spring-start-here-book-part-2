package org.example.service;

import org.example.model.LoginProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


@Service
@SessionScope
public class LoggedUserManagementService {
    private String username;

    public LoggedUserManagementService() {
        System.out.println(LoggedUserManagementService.class + " instantiated!");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
