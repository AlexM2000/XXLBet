package xxl.bet.milto.servlet;

import xxl.bet.milto.requestbody.RegistrationRequest;
import xxl.bet.milto.validator.Validator;
import xxl.bet.milto.validator.impl.EmailValidator;
import xxl.bet.milto.validator.impl.PasswordValidator;
import xxl.bet.milto.validator.impl.PhoneNumberValidator;
import xxl.bet.milto.validator.impl.UserExistsValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends AbstractServlet {
    private Validator userExistsValidater = UserExistsValidator.getInstance();
    private Validator passwordValidator = PasswordValidator.getInstance();
    private Validator phoneNumberValidator = PhoneNumberValidator.getInstance();
    private Validator emailValidator = EmailValidator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationRequest body = getRequestBody(req, RegistrationRequest.class);
        validate(body.getEmail(), getCurrentLocale(req), emailValidator);
        validate(body.getPassword(), getCurrentLocale(req), passwordValidator);
        validate(body.getPhoneNumber(), getCurrentLocale(req), phoneNumberValidator);
        validate(body.getEmail(), getCurrentLocale(req), userExistsValidater);
        getMapper().writeValue(resp.getWriter(), getErrors());
        getErrors().clear();
    }
}
