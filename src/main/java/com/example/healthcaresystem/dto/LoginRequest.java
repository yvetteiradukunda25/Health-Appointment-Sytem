package com.example.healthcaresystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
<<<<<<< HEAD

}
=======
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
>>>>>>> 0e749a333005295295466a474a710474e77252fa
