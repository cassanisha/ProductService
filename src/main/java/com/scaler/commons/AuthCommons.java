package com.scaler.commons;

import com.scaler.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;
    @Autowired
    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token ){
        //hardcoded the url here, in reality multiple servers of userService will be running.
        ResponseEntity<UserDto> responseEntity= restTemplate.getForEntity("http://localhost:4141/users/validate/"+token, UserDto.class);
        if( responseEntity.getBody()== null){
            //throw new exception here
            return null;
        }
        else return responseEntity.getBody();
    }
}