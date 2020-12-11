package xxl.bet.milto.validator.impl;

import xxl.bet.milto.service.UserService;
import xxl.bet.milto.service.impl.XxlUserServiceImpl;
import xxl.bet.milto.utils.Errors;
import xxl.bet.milto.validator.Validator;

public class UserExistsValidator implements Validator {
    private UserService service = XxlUserServiceImpl.getInstance();

    @Override
    public void validate(Object target, Errors errors, String locale) {
        String email = (String) target;

        if (service.checkIfUserExists(email)) {
            errors.reject("user.email.already.exists", locale, email);
        }
    }
}
