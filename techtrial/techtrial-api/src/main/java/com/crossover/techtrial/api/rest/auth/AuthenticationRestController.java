package com.crossover.techtrial.api.rest.auth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWTSigner;
import com.crossover.techtrial.api.rest.auth.model.AuthenticationResponse;
import com.crossover.techtrial.api.rest.auth.model.GoogleAuthRequest;
import com.crossover.techtrial.api.rest.auth.model.GoogleAuthResponse;
import com.crossover.techtrial.api.rest.security.service.TokenVerifyService;
import com.crossover.techtrial.domain.model.user.User;
import com.crossover.techtrial.domain.repository.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

/**
 * Authenticates the user's oauth token. If the user's token is not
 * authenticated the secure API requests are rejected
 * 
 * @author egunay
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Value("${auth.google.client.id}")
	private String clientId;

	@Value("${auth.google.client.secret}")
	private String clientSecret;

	@Value("${auth.google.url.format}")
	private String urlFormat;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenVerifyService tokenVerifyService;

	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(value = "/google", method = RequestMethod.POST)
	public AuthenticationResponse authenticateFromGoogle(@RequestBody GoogleAuthRequest authRequest) throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		String authUrl = String.format(urlFormat, clientId, clientSecret, authRequest.getRedirectUri(),
				authRequest.getCode());

		ResponseEntity<GoogleAuthResponse> responseEntity = restTemplate.postForEntity(authUrl, null, GoogleAuthResponse.class);
		GoogleAuthResponse googleAuthResponse = responseEntity.getBody();

		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		if (googleAuthResponse.getIdToken() == null) {
			authenticationResponse.setError(googleAuthResponse.getError());
			authenticationResponse.setErrorDescription(googleAuthResponse.getErrorDescription());

			return authenticationResponse;
		}

		System.out.println(googleAuthResponse.getIdToken());
		log.debug(googleAuthResponse.getIdToken());

		GsonFactory mJFactory = new GsonFactory();
		GoogleIdToken token = GoogleIdToken.parse(mJFactory, googleAuthResponse.getIdToken());
		NetHttpTransport transport = new NetHttpTransport();
		GoogleIdTokenVerifier mVerifier = new GoogleIdTokenVerifier(transport, mJFactory);

		if (mVerifier.verify(token)) {
			GoogleIdToken.Payload payload = token.getPayload();

			String sub = (String) payload.get("sub");
			User user = userRepository.findByUsername(sub);

			// create a new user if it does not exist
			if (user == null) {
				user = new User();
				user.setUsername(sub);
				user.setEmail((String) payload.get("email"));
				user.setName((String) payload.get("name"));

				userRepository.save(user);
			}
			
			String accessToken = tokenVerifyService.generateSignedToken(user.getId());
			authenticationResponse.setAuthToken(accessToken);
		}

		return authenticationResponse;
	}
	
	
	/**
	 * check if the provided authToken is a valid token 
	 * 
	 * @param authToken
	 * @return
	 */
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> checkAuthToken(@RequestBody String authToken) {
		Long userId = tokenVerifyService.getUserId(authToken);
		
		if (userId == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		
		User user = userRepository.findOne(userId);
		if (user == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setAuthToken(authToken);
		
		return ResponseEntity.ok().body(authenticationResponse);
	}
}
