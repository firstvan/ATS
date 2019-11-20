package com.tigra.ats.service.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public String errorMessage;


    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl("/login?error=true");

        super.onAuthenticationFailure(request, response, exception);

        System.out.println("||||"+exception.getMessage()+"||||");


        errorMessage = new String();

        if (exception.getMessage().contains("blocked")) {
            errorMessage = "A bejelentkezés le lett tíltva, próbálkozzon 5 perc múlva.";
        }else if(exception.getMessage().contains("Bad credentials")){
            errorMessage="Rossz felhasználói adatok!";
        }else if(exception.getMessage().contains("No user found with username: ")){
            errorMessage="Rossz felhasználói adatok!";
        }


    }
}