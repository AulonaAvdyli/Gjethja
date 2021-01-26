package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Posts;
import com.katrasolutions.gjethja.model.PostsModel;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsMapper {

    public Posts modelToEntity(PostsModel postsModel) {
        Posts posts = new Posts();
        posts.setTitle(postsModel.getTitle());
        posts.setDescription(postsModel.getDescription());
        posts.setCreatedOn(new Date());
        posts.setStatus(postsModel.getStatus());
        return posts;
    }

    public PostsModel entityToModel(Posts posts) {
        PostsModel postsModel = new PostsModel();
        postsModel.setId(posts.getId());
        postsModel.setTitle(posts.getTitle());
        postsModel.setDescription(posts.getDescription());
        postsModel.setCreatedOn(posts.getCreatedOn());
        postsModel.setStatus(posts.getStatus());
        postsModel.setUser(posts.getUser().getFirstName() + " " + posts.getUser().getLastName());
        return postsModel;
    }

    public List<PostsModel> entitiesToModels(List<Posts> posts) {
        return posts.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public Posts updateExistingEntity(Posts posts, PostsModel postsModel) {
        posts.setTitle(postsModel.getTitle());
        posts.setDescription(postsModel.getDescription());
        posts.setStatus(postsModel.getStatus());
        return posts;
    }

}
