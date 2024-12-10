package com.java.service;

import com.java.model.AuthRequest;
import com.java.model.AuthResponse;
import com.java.model.RegisterRequest;
import com.java.model.User;
import com.java.model.VerificationRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
	
	AuthResponse register(RegisterRequest request);
	
	AuthResponse authenticate(AuthRequest request);
	
	void saveUserToken(User user, String jwtToken);
	
	void revokeAllUserTokens(User user);
	
	void refreshToken(HttpServletRequest request , HttpServletResponse response);

	AuthResponse verifyCode(VerificationRequest verificationRequest);
	
	void revokeToken(String token);
	
	void activateAccount(String token);
	
	String generateAndSaveActivationToken(User user);
	
	void sendValidationEmail(User user);
	
	String generateActivationCode(Integer length);
	
	
	
	
	

}
