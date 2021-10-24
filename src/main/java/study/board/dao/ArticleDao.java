package study.board.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.board.dto.Article;
import study.board.dto.ArticleView;
import study.board.enums.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleDao {

    private final DataSource dataSource;

    public int add(Article article){
        String sql = "insert into article(title, content, user_id, category) values(?,?,?,?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setInt(3, article.getUserId());
            pstmt.setString(4, article.getCategory().name());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public Optional<Article> findById(int articleId){
        String sql = "select * from article where id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, articleId);
            rs = pstmt.executeQuery();

            if(rs.next()){
                Article article = new Article(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getTimestamp(4).toLocalDateTime(),
                        rs.getInt(5), rs.getInt(6),
                        Category.valueOf(rs.getString(7)));
                return Optional.of(article);
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

    public List<ArticleView> findAll(){
        String sql = "select id, title, regdate from article;";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<ArticleView> list = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                LocalDateTime regDate = rs.getTimestamp(3).toLocalDateTime();
                ArticleView articleView = new ArticleView(id, title, regDate);
                list.add(articleView);
            }
            return list;
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
}
