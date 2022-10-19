package com.example.apijwt.user;

import com.example.apijwt.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @Transactional
    public User addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }
}
