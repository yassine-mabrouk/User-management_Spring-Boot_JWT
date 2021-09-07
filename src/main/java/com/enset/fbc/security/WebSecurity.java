package com.enset.fbc.security;

import com.enset.fbc.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
class WebSecurity extends WebSecurityConfigurerAdapter {
   private final UserService userDetailsService;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userDetailsService = userDetailsService;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }
   //AuthenticationManagerBuilder=> permet de creer une instance de user  authentifié
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
              .cors().and() //activer cour pour la communication entre les app
              .csrf().disable()// desactiver le csrf (pas de formulaire
              .authorizeRequests()
              .antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL)  // authoriser les R http post avec le segment /users
              .permitAll()
              .anyRequest() // denit the auther request
              .authenticated()
              .and()
              .addFilter(this.getAuthenticationFilter())// utiliser la methode pour changer url de login
            //  .addFilter(new AuthenticationFilter(authenticationManager()));
              .addFilter(new AuthorizationFilter(authenticationManager()))
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      //http.formLogin();
   }
   // methode pour changer url de login
   protected AuthenticationFilter getAuthenticationFilter() throws Exception {
      final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
      filter.setFilterProcessesUrl("/users/login");
      return filter;
   }

}
