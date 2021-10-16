package study.board.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.board.dto.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserDaoTest {

    private UserDao userDao;
    private User user;

    @Autowired
    public UserDaoTest(UserDao userDao){
        this.userDao = userDao;
    }

    @BeforeEach
    void beforeEach(){
        userDao.add(new User("test1", "abcd", "tester1", "test1@gmail.com"));
        userDao.add(new User("test2", "1111", "tester2", "test2@daum.net"));
        user = new User("test3", "1234", "tester3", "test3@gmail.com");
    }

    @AfterEach
    void afterEach() {
//        userDao.clear();
    }

    @Test
    void add() {
        int result = userDao.add(user);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void findUserById() {
        Optional<User> userById = userDao.findById(1);
        User user = userById.get();

        assertThat(user.getLoginId()).isEqualTo("test1");
        assertThat(user.getPassword()).isEqualTo("abcd");
        assertThat(user.getName()).isEqualTo("tester1");
        assertThat(user.getEmail()).isEqualTo("test1@gmail.com");
    }
}