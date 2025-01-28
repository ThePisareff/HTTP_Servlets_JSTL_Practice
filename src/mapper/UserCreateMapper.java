package mapper;

import dto.UserCreateDto;
import entity.Gender;
import entity.Role;
import entity.User;
import util.LocalDateFormatter;

public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private static final UserCreateMapper INSTANCE = new UserCreateMapper();

    private UserCreateMapper(){};

    public static UserCreateMapper getInstance(){return INSTANCE;}

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .build();
    }
}
