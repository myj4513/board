package study.board.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserTest {

    private User user;

    @BeforeEach
    void beforeEach() {
        user = new User("test", "1234", "tester", "test@gmail.com");
    }

    @Test
    void getLoginId() {
        assertThat(user.getLoginId()).isEqualTo("test");
    }

    @Test
    void getPassword() {
        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    void getName() {
        assertThat(user.getName()).isEqualTo("tester");
    }

    @Test
    void getEmail() {
        assertThat(user.getEmail()).isEqualTo("test@gmail.com");
    }
}