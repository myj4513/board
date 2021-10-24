package study.board;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"study.board.mapper"})
public class Board2Application {
    public static void main(String[] args) {
        SpringApplication.run(Board2Application.class, args);
    }
}
