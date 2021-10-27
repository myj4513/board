package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.board.dto.LoginForm;
import study.board.dto.User;
import study.board.exceptions.DuplicateLoginIdException;
import study.board.exceptions.EncryptionException;
import study.board.exceptions.WrongLoginDataException;
import study.board.mapper.UserMapper;
import study.board.utils.SHA256;

import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void signup(User user) {  //리턴 boolean 수정 (예외로 다루자)
        if(hasDuplicateLoginId(user.getLoginId())){//중복아이디로 인한 로그인 실패
            throw new DuplicateLoginIdException();
        }

        try{
            String encryptedPassword = SHA256.encrypt(user.getPassword());
            user.setPassword(encryptedPassword);
            userMapper.add(user);
        } catch (NoSuchAlgorithmException e) {
            log.error("",e);
        }
    }

    private boolean hasDuplicateLoginId(String loginId){
        return userMapper.findByLoginId(loginId)!=null;
    }

    public User login(LoginForm loginForm) {
        User user = userMapper.findByLoginId(loginForm.getLoginId());

        if(user==null) throw new WrongLoginDataException();

        try{
            String encryptedPassword = SHA256.encrypt(loginForm.getPassword());
            if(!encryptedPassword.equals(user.getPassword())){
                throw new WrongLoginDataException();
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new EncryptionException();
        }
        return user;
    }

    public String getWriterNameById(int userId){
        return userMapper.getNameById(userId);
    }
}
