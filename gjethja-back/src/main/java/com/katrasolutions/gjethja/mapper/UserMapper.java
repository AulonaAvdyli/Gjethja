package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.User;
import com.katrasolutions.gjethja.model.UserModel;
import com.katrasolutions.gjethja.response.UserResponse;
import com.katrasolutions.gjethja.util.ImageUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        return user;
    }

    public UserModel toModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        return userModel;
    }

    public UserResponse userToModel(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setAddress(user.getAddress());
        userResponse.setBio(user.getBio());
        userResponse.setCity(user.getCity());
        userResponse.setProfilePicture(ImageUtils.convertImageToString(user.getProfilePicture()));
        return userResponse;
    }
}
