package com.ecommerce.project.service;

import com.ecommerce.project.security.payload.UserInfoResponse;

public interface GoogleAuthService {

    UserInfoResponse OAuthValidation(String code);

}
