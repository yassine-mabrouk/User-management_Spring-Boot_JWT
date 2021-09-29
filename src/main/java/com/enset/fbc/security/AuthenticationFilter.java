package com.enset.fbc.security;
import com.enset.fbc.SpringApplicationContext;
import com.enset.fbc.dto.UserDto;
import com.enset.fbc.request.UserLoginRequest;
import com.enset.fbc.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.view.tiles3.SpringWildcardServletTilesApplicationContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;



// verifier authentifiacation et geneer le token
public class AuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    /*
    // noter
     // needed : je veut Id public de user auth
     @Autowired
      UserService userService ;
      // the best way
     // en ne peut pas creer une instance de userservice car AuthenticationFilter n'est pas singleton
     // il est creé a chaque requete dans la class webSecurity configure(HttpSecurity http)
     // pour creer un userservice il faut creer un Context et recuperer l'instance creé dans app
     precisement dans l'execution de  configure(HttpSecurity http) dans la classe webSecurity
     */
    // creation d'un obj
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
     // une fois la requette /login execute cette methode sera déclancher
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        try {
            UserLoginRequest creds=new ObjectMapper().readValue(request.getInputStream(),UserLoginRequest.class);
           return authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword(),new ArrayList<>())
           );

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


  // methode sexecuter si le user existe
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                          Authentication auth) throws IOException, ServletException {
        String userName = ((User) auth.getPrincipal()).getUsername();
        // Notes : il faut ecrire la class en miniscule au debut userSeviceImpl dans getBean
        UserService userService = (UserService)SpringApplicationContext.getBean("userSeviceImpl");
        UserDto userDto = userService.getUser(userName);

        String token = Jwts.builder()
                .setSubject(userName)
                .claim("id", userDto.getId())// clianm pour envoyer data dans payload de token
                .claim("name",userDto.getName())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.TOKEN_SECRET)
                 .compact();

       res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
       res.addHeader("user_id", userDto.getUserID());
       res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ userDto.getId() + "\"}");


    }


    }






