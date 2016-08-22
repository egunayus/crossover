package com.crossover.techtrial.api.rest.security.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

@Service
public class TokenVerifyService {

	private static final String JWT_SECRET = "lopus_diem_hebele_sweet_123987";
	private static final String USER_ID = "userId";

	public String generateSignedToken(Long userId) {
        Map<String, Object> json = new HashMap();
        json.put(USER_ID, String.valueOf(userId));

        JWTSigner signer = new JWTSigner(JWT_SECRET);
        String accessToken = signer.sign(json);
		
        return accessToken;
	}
	
	public Long getUserId(String accessToken) {
		try {
			Map<String, Object> json = new JWTVerifier(JWT_SECRET).verify(accessToken);

			if (! json.containsKey(USER_ID))
	        	return null;
	        
	        return Long.valueOf((String) json.get(USER_ID));
		} catch (Exception e) {
			return null;
		} 
	}
	
}
