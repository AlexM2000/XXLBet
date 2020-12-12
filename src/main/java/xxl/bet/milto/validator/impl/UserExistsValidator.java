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
public class UserExistsValidator implements Validator {
    private UserService service = XxlUserServiceImpl.getInstance();

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String email = (String) target;

        if (service.checkIfUserExists(email)) {
            errors.reject("user.email.already.exists", locale, email);
        }
    }
}
