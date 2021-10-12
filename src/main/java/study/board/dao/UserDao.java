package study.board.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.board.dto.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao{

    private final DataSource dataSource;

    public int add(User user) {

        String sql = "insert into user(loginid, password, name, email) values(?,?,?,?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getLoginId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());

            return pstmt.executeUpdate();
        }catch (Exception e) {
            throw new IllegalStateException(e);
        }finally{
            close(con, pstmt, rs);
        }
    }

    public Optional<User> findUserById(int id) {
        String sql = "select * from user where id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return Optional.of(user);
            }
            else{
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally{
            close(con, pstmt, rs);
        }
    }

    public void close(Connection con, PreparedStatement pstmt, ResultSet rs){
        try{
            if(rs!=null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(pstmt!=null)
                pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(con!=null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        String sql = "delete from user";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        finally {
            close(con, pstmt, rs);
        }
    }
}
