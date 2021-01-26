package com.katrasolutions.gjethja.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katrasolutions.gjethja.entity.Posts;
import com.katrasolutions.gjethja.entity.User;
import com.katrasolutions.gjethja.exception.CustomExceptionHandler;
import com.katrasolutions.gjethja.model.PostsModel;
import com.katrasolutions.gjethja.repository.PostsRepository;
import com.katrasolutions.gjethja.repository.UserRepository;
import com.katrasolutions.gjethja.service.PostsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsControllerIntegrationTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    private Validator validator;

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private User user;

    private Posts posts;

    private static final String DEFAULT_TITLE = "OMG";
    private static final String DEFAULT_DESCRIPTION = "hkjfshfskgsksk";
    private static final String DEFAULT_STATUS = "open";

    private static final String DEFAULT_TITLE_UPDATED = "ofc";
    private static final String DEFAULT_DESCRIPTION_UPDATED = "hej";
    private static final String DEFAULT_STATUS_UPDATED = "close";

    private static final String DEFAULT_FIRST_NAME = "first";
    private static final String DEFAULT_LAST_NAME = "last";
    private static final String DEFAULT_EMAIL = "test@test.com";
    private static final String DEFAULT_PASSWORD = "pass";
    private static final String DEFAULT_BIO = "bio";
    private static final String DEFAULT_CITY = "city";
    private static final String DEFAULT_GENDER = "f";
    private static final String DEFAULT_PHONE_NUMBER = "omh";

    @Before
    public void initTest() {
        posts = createEntity(em);
        this.user = defaultUser(em);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PostsController postsController = new PostsController(postsService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(postsController)
                .setControllerAdvice(customExceptionHandler)
                .setValidator(validator)
                .build();
    }

    private static Posts createEntity(EntityManager em) {
        Posts posts = new Posts();
        posts.setDescription(DEFAULT_DESCRIPTION);
        posts.setStatus(DEFAULT_STATUS);
        posts.setTitle(DEFAULT_TITLE);
        posts.setCreatedOn(new Date());
        return posts;
    }

    private static User defaultUser(EntityManager em) {
        User user = new User();
        user.setPassword(DEFAULT_PASSWORD);
        user.setLastName(DEFAULT_LAST_NAME);
        user.setFirstName(DEFAULT_FIRST_NAME);
        user.setEnabled(true);
        user.setBio(DEFAULT_BIO);
        user.setCity(DEFAULT_CITY);
        user.setDateOfBirth(LocalDate.now().minusYears(20));
        user.setEmail(DEFAULT_EMAIL);
        user.setGender(DEFAULT_GENDER);
        user.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        return user;
    }

    @Test
    @Transactional
    public void findPostsById() throws Exception {
        userRepository.saveAndFlush(user);

        posts.setUser(user);

        Posts post = postsRepository.saveAndFlush(this.posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{id}", post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(DEFAULT_TITLE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(DEFAULT_DESCRIPTION))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    public void findPostsWithNoExistingId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void findAllPosts() throws Exception {
        userRepository.saveAndFlush(user);

        posts.setUser(user);

        postsRepository.saveAndFlush(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].title").value(DEFAULT_TITLE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].description").value(DEFAULT_DESCRIPTION))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].status").value(DEFAULT_STATUS));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void createPosts() throws Exception {
        userRepository.saveAndFlush(user);

        PostsModel postsModel = new PostsModel();
        postsModel.setTitle(DEFAULT_TITLE);
        postsModel.setStatus(DEFAULT_STATUS);
        postsModel.setDescription(DEFAULT_DESCRIPTION);

        mockMvc.perform(post("/posts/")
                .content(om.writeValueAsString(postsModel))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(DEFAULT_TITLE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(DEFAULT_DESCRIPTION))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void updatePosts() throws Exception {
        userRepository.saveAndFlush(user);

        PostsModel postsModel = new PostsModel();
        postsModel.setTitle(DEFAULT_TITLE_UPDATED);
        postsModel.setStatus(DEFAULT_STATUS_UPDATED);
        postsModel.setDescription(DEFAULT_DESCRIPTION_UPDATED);
        posts.setUser(user);
        Posts post = postsRepository.saveAndFlush(this.posts);

        mockMvc.perform(put("/posts/{id}", post.getId())
                .content(om.writeValueAsString(postsModel))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(DEFAULT_TITLE_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(DEFAULT_DESCRIPTION_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(DEFAULT_STATUS_UPDATED));

    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void deletePosts() throws Exception {
        userRepository.saveAndFlush(user);

        posts.setUser(user);

        Posts post = postsRepository.saveAndFlush(posts);
        mockMvc.perform(delete("/posts/{id}", post.getId()))
                .andExpect(status().isNoContent());

    }
}
