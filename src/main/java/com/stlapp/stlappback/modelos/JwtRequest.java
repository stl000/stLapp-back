package com.stlapp.stlappback.modelos;

import lombok.Getter;
import lombok.Setter;

public class JwtRequest {

    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;

    public JwtRequest(){

    }

    public JwtRequest(String username, String password){
        this.username=username;
        this.password=password;
    }
}
