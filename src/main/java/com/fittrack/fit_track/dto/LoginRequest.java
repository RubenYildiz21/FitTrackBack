package com.fittrack.fit_track.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Getter
    @Setter
    private String email;
    
    @NotBlank(message = "Password is mandatory")
    @Getter
    @Setter
    private String password;
}
