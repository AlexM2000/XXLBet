package com.emap.xxlbet.milto.servlet;

import com.emap.xxlbet.milto.requestbody.RegistrationRequest;
import com.emap.xxlbet.milto.service.UserService;
import com.emap.xxlbet.milto.validator.Validator;
import com.emap.xxlbet.milto.validator.impl.EmailValidator;
import com.emap.xxlbet.milto.validator.impl.PasswordValidator;
import com.emap.xxlbet.milto.validator.impl.PhoneNumberValidator;
import com.emap.xxlbet.milto.validator.impl.UserExistsValidator;
import com.emap.xxlbet.milto.service.impl.XxlUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends AbstractServlet {
    private Validator userExistsValidater;
    private Validator passwordValidator;
    private Validator phoneNumberValidator;
    private Validator emailValidator;
    private UserService userService;

    @Override
    public void init() {

        userExistsValidater = UserExistsValidator.getInstance();
        passwordValidator = PasswordValidator.getInstance();
        phoneNumberValidator = PhoneNumberValidator.getInstance();
        emailValidator = EmailValidator.getInstance();
        userService = XxlUserServiceImpl.getInstance();
    }

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

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            userService.createNewUser(body);
        }

        getMapper().writeValue(resp.getWriter(), getErrors());
        getErrors().clear();
    }
}
