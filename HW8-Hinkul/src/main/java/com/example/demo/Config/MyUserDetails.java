package com.example.demo.Config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class MyUserDetails extends User {


    public MyUserDetails(
            final String username,
            final String password,
            final List<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }
}
