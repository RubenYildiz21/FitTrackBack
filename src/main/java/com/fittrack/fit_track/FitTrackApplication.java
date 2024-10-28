package com.fittrack.fit_track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.fittrack.fit_track.repository")
public class FitTrackApplication {
	public static void main(String[] args) {
		SpringApplication.run(FitTrackApplication.class, args);
	}

}
