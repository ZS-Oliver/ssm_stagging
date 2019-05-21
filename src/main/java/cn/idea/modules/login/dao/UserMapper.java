package cn.idea.modules.login.dao;

import cn.idea.modules.login.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return
     */
    public UserVo findByUsername(@Param("username") String username);
}
