package study.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.board.dao.UserDao;
import study.board.dto.LoginForm;
import study.board.dto.User;
import study.board.utils.SHA256;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean signup(User user) throws NoSuchAlgorithmException {
        if(validateDuplicateLoginId(user.getLoginId())){
            return false;
        }

        String encryptedPassword = SHA256.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.add(user);
        return true;
    }

    private boolean validateDuplicateLoginId(String loginId){
        return userDao.findByLoginId(loginId).isPresent();
    }

    public User login(LoginForm loginForm) throws NoSuchAlgorithmException {
        Optional<User> user = userDao.findByLoginId(loginForm.getLoginId());
        if(user.isPresent()){
            String encryptedPassword = SHA256.encrypt(loginForm.getPassword());
            if(encryptedPassword.equals(user.get().getPassword())){
                return user.get();
            }
        }
        return null;
    }
}
