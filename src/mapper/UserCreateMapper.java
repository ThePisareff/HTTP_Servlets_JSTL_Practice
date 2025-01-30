package mapper;

import dto.UserCreateDto;
import entity.Gender;
import entity.Role;
import entity.User;
import util.LocalDateFormatter;

public class UserCreateMapper implements Mapper<UserCreateDto,User> {

    private static final UserCreateMapper INSTANCE = new UserCreateMapper();
    private static final String IMAGE_FOLDER = "users/";

    private UserCreateMapper(){}

    public static UserCreateMapper getInstance(){return INSTANCE;}

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .build();
    }
}
