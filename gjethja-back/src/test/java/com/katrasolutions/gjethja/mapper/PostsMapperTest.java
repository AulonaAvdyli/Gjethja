package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Posts;
import com.katrasolutions.gjethja.entity.User;
import com.katrasolutions.gjethja.mapper.PostsMapper;
import com.katrasolutions.gjethja.model.PostsModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostsMapperTest {

    private static final Long ID = 1L;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final Date CREATED_ON = new Date();
    private static final String STATUS = "status";
    private static final String USER = "user";
    private static final User USER1 = new User();
    private PostsMapper postsMapper = new PostsMapper();



    @Test
    public void modelToEntityTest() {
        PostsModel postsModel = getPostsModel();
        Posts posts = postsMapper.modelToEntity(postsModel);
        assertEquals(posts.getCreatedOn().toString(), CREATED_ON.toString());
        assertEquals(posts.getDescription(), DESCRIPTION);
        assertEquals(posts.getStatus(), STATUS);
        assertEquals(posts.getTitle(), TITLE);
    }

    @Test
    public void entityToModelTest() {
        Posts posts = getPosts();
        PostsModel postsModel = postsMapper.entityToModel(posts);
        assertEquals(postsModel.getId(), ID);
        assertEquals(postsModel.getDescription(), DESCRIPTION);
        assertEquals(postsModel.getStatus(), STATUS);
        assertEquals(postsModel.getTitle(), TITLE);
        assertEquals(postsModel.getCreatedOn(), CREATED_ON);
    }

    @Test
    public void updateExistingEntityTest() {
        Posts posts = getPosts();
        PostsModel postsModel = getPostsModel();
        postsModel.setDescription("Test test");
        postsModel.setStatus("Done");
        postsModel.setTitle("Test");
        posts = postsMapper.updateExistingEntity(posts, postsModel);
        assertEquals(posts.getDescription(), "Test test");
        assertEquals(posts.getStatus(), "Done");
        assertEquals(posts.getTitle(), "Test");
    }


    private static PostsModel getPostsModel() {
        PostsModel postsModel = new PostsModel();
        postsModel.setId(ID);
        postsModel.setCreatedOn(CREATED_ON);
        postsModel.setDescription(DESCRIPTION);
        postsModel.setTitle(TITLE);
        postsModel.setStatus(STATUS);
        postsModel.setUser(USER);
        return postsModel;
    }

    private static Posts getPosts(){
        Posts posts = new Posts();
        posts.setUser(USER1);
        posts.setDescription(DESCRIPTION);
        posts.setStatus(STATUS);
        posts.setTitle(TITLE);
        posts.setCreatedOn(CREATED_ON);
        posts.setId(ID);
        return posts;
    }

}
