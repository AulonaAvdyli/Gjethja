package com.katrasolutions.feedbackservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katrasolutions.feedbackservice.entity.Feedback;
import com.katrasolutions.feedbackservice.exception.CustomExceptionHandler;
import com.katrasolutions.feedbackservice.model.UserModel;
import com.katrasolutions.feedbackservice.repository.FeedbackRepository;
import com.katrasolutions.feedbackservice.response.FeedbackResponse;
import com.katrasolutions.feedbackservice.service.FeedbackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
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
public class FeedbackControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    private Validator validator;

    @Autowired
    private EntityManager em;

    private MockMvc mockMvc;

    private Feedback feedback;
    private UserModel userModel;


    private static final String DEFAULT_DESCRIPTION = "hkjfshfskgsksk";
    private static final String DEFAULT_DESCRIPTION_UPDATED = "hej";
    private static final String DEFAULT_FIRST_NAME = "first";
    private static final String DEFAULT_LAST_NAME = "last";
    private static final String DEFAULT_EMAIL = "test@test.com";

    @Before
    public void initTest() {
        feedback = createEntity(em);
        this.userModel = defaultUser(em);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        FeedbackController feedbackController = new FeedbackController(feedbackService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(feedbackController)
                .setControllerAdvice(customExceptionHandler)
                .setValidator(validator)
                .build();
    }

    private static Feedback createEntity(EntityManager em) {
        Feedback feedback = new Feedback();
        feedback.setDescription(DEFAULT_DESCRIPTION);
        feedback.setCreatedOn(new Date());
        return feedback;
    }

    private static UserModel defaultUser(EntityManager em) {
        UserModel user = new UserModel();
        user.setLastName(DEFAULT_LAST_NAME);
        user.setFirstName(DEFAULT_FIRST_NAME);
        user.setEmail(DEFAULT_EMAIL);
        return user;
    }

    @Test
    @Transactional
    public void findFeedbackById() throws Exception {

        Feedback feedback = feedbackRepository.saveAndFlush(this.feedback);

        mockMvc.perform(MockMvcRequestBuilders.get("/feedback/{id}", feedback.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    public void findFeedbackWithNoExistingId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/feedback/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void findAllFeedback() throws Exception {

        feedbackRepository.saveAndFlush(feedback);

        mockMvc.perform(MockMvcRequestBuilders.get("/feedback"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @WithMockUser("test@test.com")
    @Profile({"dev"})
    @Transactional
    public void createFeedback() throws Exception {

        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setDescription(DEFAULT_DESCRIPTION);

        mockMvc.perform(post("/feedback/")
                .content(om.writeValueAsString(feedbackResponse))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void updateFeedback() throws Exception {

        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setDescription(DEFAULT_DESCRIPTION_UPDATED);
        Feedback feedback = feedbackRepository.saveAndFlush(this.feedback);

        mockMvc.perform(put("/feedback/{id}", feedback.getId())
                .content(om.writeValueAsString(feedbackResponse))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(DEFAULT_DESCRIPTION_UPDATED));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void deleteFeedback() throws Exception {

        Feedback feedback = feedbackRepository.saveAndFlush(this.feedback);
        mockMvc.perform(delete("/feedback/{id}", feedback.getId()))
                .andExpect(status().isNoContent());
    }
}

