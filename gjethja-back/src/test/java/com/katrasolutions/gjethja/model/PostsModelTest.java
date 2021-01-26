package com.katrasolutions.gjethja.model;

import com.katrasolutions.gjethja.model.PostsModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PostsModelTest {
    private static Validator validator;

    private static ValidatorFactory validatorFactory;

    private static final Long ID = 1L;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final Date CREATED_ON = new Date();
    private static final String STATUS = "status";
    private static final String USER = "user";

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void postsModelWithEmptyTitle() {
        PostsModel postsModel = postsModel();
        postsModel.setTitle(null);
        Set<ConstraintViolation<PostsModel>> validate = validator.validate(postsModel);
        ConstraintViolation<PostsModel> violation = validate.iterator().next();
        assertEquals("title must not be empty", violation.getMessage());

    }

    @Test
    public void postsModelWithEmptyDescription() {
        PostsModel postsModel = postsModel();
        postsModel.setDescription(null);
        Set<ConstraintViolation<PostsModel>> validate = validator.validate(postsModel);
        ConstraintViolation<PostsModel> violation = validate.iterator().next();
        assertEquals("description must not be empty", violation.getMessage());

    }

    @Test
    public void postsModelWithEmptyStatus() {
        PostsModel postsModel = postsModel();
        postsModel.setStatus(null);
        Set<ConstraintViolation<PostsModel>> validate = validator.validate(postsModel);
        ConstraintViolation<PostsModel> violation = validate.iterator().next();
        assertEquals("status must not be empty", violation.getMessage());

    }

    private static PostsModel postsModel() {
        PostsModel postsModel = new PostsModel();
        postsModel.setId(ID);
        postsModel.setCreatedOn(CREATED_ON);
        postsModel.setDescription(DESCRIPTION);
        postsModel.setTitle(TITLE);
        postsModel.setStatus(STATUS);
        postsModel.setUser(USER);

        return postsModel;

    }

}
