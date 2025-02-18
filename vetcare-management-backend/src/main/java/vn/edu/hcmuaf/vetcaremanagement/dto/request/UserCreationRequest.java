package vn.edu.hcmuaf.vetcaremanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 4, message = "Username must be at least 4 characters")
    String username;

    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;
    @Email(message = "Email should be valid")
    String email;
    int age;
    String gender;
}