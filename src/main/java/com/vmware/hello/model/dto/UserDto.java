package com.vmware.hello.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class UserDto {

   private String emailAddress;

   private String username;

   @ToString.Exclude
   private String password;

}
