package com.example;

import com.example.domain.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author jy
 */
public interface UserMapper {

    List<User> findAll(RowBounds rowBounds);

    int deleteUser(int userId);
}
