package com.demo.dto;

public class JwtResponse {

    private final String jwttoken, jwtrole;

    public JwtResponse(String jwttoken, String jwtclaim) {
        this.jwttoken = jwttoken;
        this.jwtrole = jwtclaim;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getRole() {
        return this.jwtrole;
    }
}