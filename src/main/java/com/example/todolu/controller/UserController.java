package com.example.todolu.controller;

import com.example.todolu.domain.user.UserService;
import com.example.todolu.domain.user.dto.UserDetailData;
import com.example.todolu.user.UserCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserCreateData userCreateData, UriComponentsBuilder uriComponentsBuilder){

        var user = userService.create(userCreateData);

        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDetailData(user));
    }

}
