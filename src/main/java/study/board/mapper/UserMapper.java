package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.board.dto.User;

@Mapper
public interface UserMapper {

    public User findById(int userId);
    public void add(User user);
    public User findByLoginId(String userLoginId);
}
