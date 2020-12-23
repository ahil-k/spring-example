package com.vmware.hello.service;

import java.util.Optional;

import com.vmware.hello.model.dto.ErrorResponse;
import com.vmware.hello.model.dto.UserDto;
import com.vmware.hello.model.entity.UserEntity;
import com.vmware.hello.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

   @Autowired
   private UserRepository userRepository;

   private ModelMapper modelMapper = new ModelMapper();

   public ResponseEntity<String> getHomePage() {
      StringBuilder homePageHtml = new StringBuilder();
      homePageHtml.append("<html><body>");
      homePageHtml.append("<h1>Welcome to the app.</h1>");
      homePageHtml.append("<a href=\"user\">Click to register</a");
      homePageHtml.append("</body></html>");
      return new ResponseEntity<>(homePageHtml.toString(), HttpStatus.OK);
   }

   public ResponseEntity<?> loginUser(UserDto userDto) {
      Optional<UserEntity> userEntityOptional = userRepository.findById(userDto.getEmailAddress());
      if (userEntityOptional.isPresent()) {
         if (userDto.equals(modelMapper.map(userEntityOptional.get(), UserDto.class))) {
            logger.debug("Login Successfull");
            return ResponseEntity.accepted().build();
         }
         String msg = "Credentials don't match";
         logger.debug(msg);
         return ResponseEntity.badRequest().body(new ErrorResponse(msg));
      }
      String msg = "Email doesn't exist in DB";
      logger.debug(msg);
      return ResponseEntity.badRequest().body(new ErrorResponse(msg));

   }

   public ResponseEntity<?> registerUser(UserDto userDto) {
      Optional<UserEntity> userEntityOptional = userRepository.findById(userDto.getEmailAddress());
      if (userEntityOptional.isPresent()) {
         String msg = "Email Already registered";
         logger.debug(msg);
         return ResponseEntity.badRequest().body(new ErrorResponse(msg));
      } else {
         logger.debug("Persisting user data in db");
         userRepository.save(modelMapper.map(userDto, UserEntity.class));
         return ResponseEntity.ok().build();
      }

   }
}
