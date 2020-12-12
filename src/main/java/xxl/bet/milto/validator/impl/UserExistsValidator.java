package xxl.bet.milto.validator.impl;

import xxl.bet.milto.service.UserService;
import xxl.bet.milto.service.impl.XxlUserServiceImpl;
import xxl.bet.milto.utils.Errors;
import xxl.bet.milto.validator.Validator;

/**
 * UserExistsValidator.
 *
 * @author alexm2000
 */
public final class UserExistsValidator implements Validator {
    private static UserExistsValidator instance;
    private UserService service = XxlUserServiceImpl.getInstance();

    private UserExistsValidator() { }

    public static UserExistsValidator getInstance() {
        if (instance == null) {
            instance = new UserExistsValidator();
        }

        return instance;
    }

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String email = (String) target;

        if (service.checkIfUserExists(email)) {
            errors.reject("user.email.already.exists", locale, email);
        }
    }
}
