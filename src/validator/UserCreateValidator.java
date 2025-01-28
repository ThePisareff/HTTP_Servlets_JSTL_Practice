package validator;

import dto.UserCreateDto;
import entity.Gender;
import util.LocalDateFormatter;

public class UserCreateValidator implements Validator<UserCreateDto> {

    private static final UserCreateValidator INSTANCE = new UserCreateValidator();

    private UserCreateValidator(){};

    public static UserCreateValidator getInstance(){return INSTANCE;}

    @Override
    public ValidationResult validate(UserCreateDto object) {
        var validationResult = new ValidationResult();

        if(Gender.find(object.getGender()).isEmpty()){
            validationResult.add(Error.of("invalid.gender","Gender is invalid"));
        }
        if(!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Error.of("invalid.birthday","Birthday is invalid"));
        }

        return validationResult;
    }
}
