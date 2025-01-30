package dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateDto {
    String id;
    String name;
    String birthday;
    String email;
    Part image;
    String password;
    String role;
    String gender;
}
