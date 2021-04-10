package com.example.demo;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserModel {

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Wrong login")
    private String login;
    @Size(max = 20, min=8, message = "Wrong password size")
    private String password;


}
