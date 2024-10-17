package com.fittrack.fit_track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.fittrack.fit_track.repository")
public class FitTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitTrackApplication.class, args);
	}

}
