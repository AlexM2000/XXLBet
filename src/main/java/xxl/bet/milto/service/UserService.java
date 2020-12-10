package xxl.bet.milto.service;

import xxl.bet.milto.domain.User;

public interface UserService {
    User createUser(String email, String phoneNumber, String password);
}
