package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.User;
import com.katrasolutions.gjethja.model.UserModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {
    private static final Long ID = 1L;
    private static final String FIRST_NAME = "first";
    private static final String LAST_NAME = "last";
    private static final String EMAIL = "test@test.com";
    private UserMapper userMapper = new UserMapper();

    @Test
    public void toEntity() {
        UserModel userModel = getUserModel();
        User user = userMapper.toEntity(userModel);
        assertEquals(user.getFirstName(), FIRST_NAME);
        assertEquals(user.getLastName(), LAST_NAME);
        assertEquals(user.getEmail(), EMAIL);
    }


    private static UserModel getUserModel() {
        UserModel userModel = new UserModel();
        userModel.setFirstName(FIRST_NAME);
        userModel.setLastName(LAST_NAME);
        userModel.setEmail(EMAIL);
        return userModel;
    }
}
