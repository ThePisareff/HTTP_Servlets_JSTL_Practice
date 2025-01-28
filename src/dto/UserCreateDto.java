package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateDto {
    String id;
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
