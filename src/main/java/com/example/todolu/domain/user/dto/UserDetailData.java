package com.example.todolu.domain.user.dto;

import com.example.todolu.domain.user.User;

import java.util.UUID;

public record UserDetailData(UUID id, String login) {

    public UserDetailData(User user){
        this(user.getId(), user.getLogin());
    }

}
