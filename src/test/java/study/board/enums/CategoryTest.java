package study.board.enums;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CategoryTest {
    @Test
    void contains(){
//        boolean hasDaily = Category.contains("일상");
//        assertThat(hasDaily).isEqualTo(true);
    }

    @Test
    void stringToCategory(){
        Category c = Category.valueOf("DAILY");
        assertThat(c).isEqualTo(Category.DAILY);
    }
}