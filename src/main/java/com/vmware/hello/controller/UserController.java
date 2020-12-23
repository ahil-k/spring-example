package com.vmware.hello.controller;

import com.vmware.hello.model.dto.UserDto;
import com.vmware.hello.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

   @Autowired
   private UserService userService;

   @GetMapping
   public @ResponseBody ResponseEntity<String> homePage() {
      return userService.getHomePage();
   }

   @PostMapping(value = "user/login")
   public @ResponseBody ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
      return userService.loginUser(userDto);
   }

   @PostMapping(value = "user/register")
   public @ResponseBody ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
      return userService.registerUser(userDto);
   }

}
