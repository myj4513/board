package study.board.databaseconnection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    String insertSql = "insert into user(loginid, password, name, email) values(?,?,?,?)";
    String selectSql = "select * from user";
    @Test
    void databaseConnectionTest(){
        try{
            Connection connection = dataSource.getConnection();
            System.out.println("연결 성공");
        } catch (SQLException throwables) {
            System.out.println("연결 실패");
            throwables.printStackTrace();
        }
    }
}
