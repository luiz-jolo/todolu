package com.example.todolu.domain.user;

import com.example.todolu.user.UserCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User create(UserCreateData userCreateData){

        var user = new User();
        user.setLogin(userCreateData.login());
        user.setPassword(encoder.encode(userCreateData.password()));
        return userRepository.save(user);
    }

}
