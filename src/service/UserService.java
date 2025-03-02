package service;

import dao.UserDao;
import dto.UserCreateDto;
import dto.UserDto;
import entity.User;
import exception.ValidationException;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mapper.UserCreateMapper;
import mapper.UserMapper;
import validator.UserCreateValidator;

import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private static final UserCreateValidator userCreateValidator = UserCreateValidator.getInstance();
    private static final UserDao userDao = UserDao.getInstance();
    private static final UserCreateMapper userCreateMapper = UserCreateMapper.getInstance();
    private static final ImageService imageService = ImageService.getInstance();
    private static final UserMapper userMapper = UserMapper.getInstance();

    public static UserService getInstance() {return INSTANCE;}

    public Optional<UserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email,password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(UserCreateDto userDto){
        //STEP I - Validation
        //STEP II - map

        var validationResult = userCreateValidator.validate(userDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }

        User user = userCreateMapper.mapFrom(userDto);
        imageService.upload(user.getImage(), userDto.getImage().getInputStream());
        userDao.save(user);

        return user.getId();
    }
}
