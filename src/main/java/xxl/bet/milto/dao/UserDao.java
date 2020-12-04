package xxl.bet.milto.dao;

import xxl.bet.milto.domain.User;
import xxl.bet.milto.requestbody.RegistrationRequest;

public interface UserDao {
    User getUserById(long id);
    User getUserByEmail(String email);
    User getUserByPhoneNumber(String phoneNumber);
    User getUserByEmailAndPassword(String email, String password);
    User getUserByPhoneNumberAndPassword(String phoneNumber, String password);
    User createUser(RegistrationRequest body);
    User deleteUser(String email, String phoneNumber);
    User updateUser(User user);
}
