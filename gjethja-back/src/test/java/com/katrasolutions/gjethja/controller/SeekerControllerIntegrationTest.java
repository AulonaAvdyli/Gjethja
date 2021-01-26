package com.katrasolutions.gjethja.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katrasolutions.gjethja.controller.SeekerController;
import com.katrasolutions.gjethja.entity.Seeker;
import com.katrasolutions.gjethja.exception.CustomExceptionHandler;
import com.katrasolutions.gjethja.repository.SeekerRepository;
import com.katrasolutions.gjethja.request.SeekerUpdateRequest;
import com.katrasolutions.gjethja.service.SeekerService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeekerControllerIntegrationTest {

    private final static ObjectMapper om = new ObjectMapper();

    @Autowired
    private SeekerService seekerService;

    @Autowired
    private SeekerRepository seekerRepository;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    private Validator validator;

    @Autowired
    private EntityManager em;

    private Seeker seeker;

    private MockMvc mockMvc;

    @Before
    public void initTest() {
        seeker = createEntity(em);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        SeekerController seekerController = new SeekerController(seekerService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(seekerController).setControllerAdvice(customExceptionHandler).setValidator(validator).build();
    }

    private static Seeker createEntity(EntityManager em) {
        Seeker seeker = new Seeker();
        seeker.setId(1L);
        seeker.setRate("rate");
        seeker.setPassword("pass");
        seeker.setFirstName("first");
        seeker.setLastName("last");
        seeker.setEnabled(true);
        seeker.setBio("bio");
        seeker.setCity("city");
        seeker.setAddress("address");
        seeker.setDateOfBirth(LocalDate.now().minusYears(20));
        seeker.setEmail("test@test.com");
        seeker.setGender("unicorn");
        seeker.setPhoneNumber("044123456");
        seeker.setProfilePicture(null);
        return seeker;
    }

    @Test
    @Transactional
    public void getSeekerId() throws Exception {
        seekerRepository.saveAndFlush(seeker);

        mockMvc.perform(MockMvcRequestBuilders.get("/seekers/{id}", seeker.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bio").value("bio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value("rate"));
    }

    @Test
    public void findSeekerWithNoExistingId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/seekers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void findAllSeekers() throws Exception {
        seekerRepository.saveAndFlush(seeker);

        mockMvc.perform(MockMvcRequestBuilders.get("/seekers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].lastName").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].email").value("test@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].bio").value("bio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].rate").value("rate"));
    }

    @Test
    @WithMockUser("test@test.com")
    public void updateSeeker() throws Exception {
        seekerRepository.saveAndFlush(seeker);
        SeekerUpdateRequest seekerUpdateRequest = new SeekerUpdateRequest();
        seekerUpdateRequest.setFirstName("serafina");
        seekerUpdateRequest.setLastName("topalli");
        seekerUpdateRequest.setBio("bio");
        seekerUpdateRequest.setCity("city");
        seekerUpdateRequest.setAddress("addresss");
        seekerUpdateRequest.setPhoneNumber("044123456");

        mockMvc.perform(put("/seekers/")
                .content(om.writeValueAsString(seekerUpdateRequest))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("serafina"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("topalli"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bio").value("bio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("city"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("addresss"));
    }

    @Test
    @WithMockUser("test@test.com")
    public void deleteSeeker() throws Exception {
        seekerRepository.saveAndFlush(seeker);
        mockMvc.perform(delete("/seekers/"))
                .andExpect(status().isNoContent());
    }

}
