package com.ecommerce.project.controller;

import com.ecommerce.project.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class GoogleAuthController {

    @Autowired
    GoogleAuthService googleAuthService;

    // This endpoint handles the OAuth2 callback.
    // Normally the frontend would receive the `code` first and send it to backend.
    // But we didn't have frontend yet so we are making google
    // to redirect to this endpoint with c Auth code, after user logs in.
    @GetMapping("/google/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam(name = "code") String code) {
        return new ResponseEntity<>(googleAuthService.OAuthValidation(code), HttpStatus.OK);

    }
}

/*

This is for frontend part. Frontend will hit this url with below details.
Then the code will be sent to redirect uri by google.
https://accounts.google.com/o/oauth2/auth?
client_id=YOUR_CLIENT_ID
    &redirect_uri=YOUR_REDIRECT_URI (api/auth/google/callback)
    &response_type=code
    &scope=email profile
    &access_type=offline
    &prompt=consent

*/

