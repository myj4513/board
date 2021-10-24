package study.board.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.board.dto.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void findByIdTest(){
        log.info("findById: {}", userMapper.findById(1));
        log.info("findById: {}", userMapper.findById(2));
        log.info("findById: {}", userMapper.findById(3));
    }

    @Test
    void addUserTest(){
        User user = new User("loginId", "password", "name", "email");
        userMapper.add(user);
    }

    @Test
    void findByLoginIdTest(){
        User user = userMapper.findByLoginId("loginId");
        log.info("id: {}", user.getId());
        log.info("password: {}", user.getPassword());
        log.info("name: {}", user.getName());
        log.info("email: {}", user.getEmail());

    }
}