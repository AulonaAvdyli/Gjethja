package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.entity.Posts;
import com.katrasolutions.gjethja.entity.User;
import com.katrasolutions.gjethja.exception.ExceptionMessage;
import com.katrasolutions.gjethja.exception.RestApiForbiddenException;
import com.katrasolutions.gjethja.exception.RestApiNotFoundException;
import com.katrasolutions.gjethja.exception.RestApiUnauthorizedException;
import com.katrasolutions.gjethja.mapper.PostsMapper;
import com.katrasolutions.gjethja.model.PostsModel;
import com.katrasolutions.gjethja.repository.PostsRepository;
import com.katrasolutions.gjethja.repository.UserRepository;
import com.katrasolutions.gjethja.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final PostsMapper postsMapper;
    private final UserRepository userRepository;

    public PostsServiceImpl(PostsRepository postsRepository, PostsMapper postsMapper, UserRepository userRepository) {
        this.postsRepository = postsRepository;
        this.postsMapper = postsMapper;
        this.userRepository = userRepository;
    }

    @Override
    public PostsModel getById(Long id) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.POST_NOT_FOUND));
        return postsMapper.entityToModel(post);
    }

    @Override
    public List<PostsModel> getByUserId() {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = userRepository.findByEmail(currentUser);
        return postsMapper.entitiesToModels(user.getPosts());
    }

    @Override
    public List<PostsModel> getAll() {
        return postsMapper.entitiesToModels(postsRepository.findAll());
    }

    @Override
    @Transactional
    public PostsModel create(PostsModel postsModel) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = userRepository.findByEmail(currentUser);
        Posts post = postsMapper.modelToEntity(postsModel);
        post.setUser(user);
        postsRepository.save(post);
        return postsMapper.entityToModel(post);
    }

    @Override
    @Transactional
    public PostsModel update(Long id, PostsModel postsModel) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = userRepository.findByEmail(currentUser);
        Posts post = postsRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.POST_NOT_FOUND));
        if (!post.getUser().getEmail().equals(user.getEmail())) {
            throw new RestApiForbiddenException(ExceptionMessage.UPDATE_POST_FORBIDDEN);
        }
        post = postsMapper.updateExistingEntity(post, postsModel);
        postsRepository.save(post);
        return postsMapper.entityToModel(post);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = userRepository.findByEmail(currentUser);
        Posts post = postsRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.POST_NOT_FOUND));
        if (!post.getUser().getEmail().equals(user.getEmail())) {
            throw new RestApiForbiddenException(ExceptionMessage.DELETE_POST_FORBIDDEN);
        }
        postsRepository.delete(post);
    }
}
