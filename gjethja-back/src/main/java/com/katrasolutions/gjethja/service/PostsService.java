package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.model.PostsModel;

import java.util.List;

public interface PostsService {
    PostsModel getById(Long id);

    List<PostsModel> getByUserId();

    List<PostsModel> getAll();

    PostsModel create(PostsModel postsModel);

    PostsModel update(Long id, PostsModel postsModel);

    void delete(Long id);
}
