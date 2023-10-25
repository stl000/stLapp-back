package com.stlapp.stlappback.modelos;

import lombok.Getter;
import lombok.Setter;

public class JwtResponse {

    @Getter @Setter
    private String token;

    public JwtResponse(){

    }

    public JwtResponse(String token){
        this.token=token;
    }
}
