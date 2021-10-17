package study.board.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.board.dto.Comment;
import study.board.dto.CommentView;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDao {

    private final DataSource dataSource;

    public int add(Comment comment){
        String sql ="insert into comment(article_id, content, user_id) values(?,?,?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, comment.getArticleId());
            pstmt.setString(2, comment.getContent());
            pstmt.setInt(3, comment.getUserId());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally{
            close(con, pstmt, rs);
        }
    }

    public List<CommentView> findAllByArticleId(int articleId){
        String sql = "select user_id, content, regdate from comment where article_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, articleId);
            rs = pstmt.executeQuery();

            List<CommentView> commentViews = new ArrayList<>();

            while(rs.next()){
                CommentView commentView = new CommentView(rs.getInt(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime());
                commentViews.add(commentView);
            }
            return commentViews;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally{
            close(con, pstmt, rs);
        }
    }

    private void close(Connection con, PreparedStatement pstmt, ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(pstmt!=null){
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(con!=null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
