package com.vmware.hello.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class UserEntity implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "email_address")
   private String emailAddress;

   @Column(name = "name")
   private String username;

   @Column(name = "password")
   @ToString.Exclude
   private String password;

}
