package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.board.dto.User;

@Mapper
public interface UserMapper {

    public User findById(int userId);
    public void add(User user);
    public User findByLoginId(String userLoginId);
    public String getNameById(int id);
    public void updateUserInfo(@Param("password") String password, @Param("name") String name, @Param("email") String email, @Param("id") int id);
    public void deleteUserById(@Param("userId") int userId);
}
