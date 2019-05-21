package cn.idea.modules.login.service;

import cn.idea.modules.login.dao.UserMapper;
import cn.idea.modules.login.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserVo findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
