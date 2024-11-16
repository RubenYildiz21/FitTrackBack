package com.fittrack.fit_track;

import java.security.Key;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootApplication
@EnableJpaRepositories("com.fittrack.fit_track.repository")
public class FitTrackApplication {
	public static void main(String[] args) {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("Generated Secret Key: " + base64Key);
		SpringApplication.run(FitTrackApplication.class, args);
	}

}
