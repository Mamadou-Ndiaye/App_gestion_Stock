package sn.ucad.gestionstock.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.dto.auth.AuthenticationRequest;
import sn.ucad.gestionstock.dto.auth.AuthenticationResponse;
//import sn.ucad.gestionstock.model.auth.ExtendUser;
import sn.ucad.gestionstock.services.auth.ApplicationUserDetailService;
import sn.ucad.gestionstock.utils.JwtUtil;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;


@Slf4j
@RestController
@Api(APP_ROOT + "/auth")
@RequestMapping(APP_ROOT + "/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ApplicationUserDetailService applicationUserDetailService;


    @Autowired
    JwtUtil jwtUtil;


    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @PostMapping(value = "/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest authenticationRequest)
    {

       authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getLogin(), authenticationRequest.getPassword()
              )
         );

       final UserDetails userDetails = applicationUserDetailService.loadUserByUsername(authenticationRequest.getLogin());



       //  final  String jwt = jwtUtil.generateToken((ExtendUser)userDetails);
        final  String jwt = jwtUtil.generateToken(userDetails);

        return  ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
