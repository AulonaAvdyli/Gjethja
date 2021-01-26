package com.katrasolutions.gjethja.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.entity.Provider;
import com.katrasolutions.gjethja.exception.CustomExceptionHandler;
import com.katrasolutions.gjethja.repository.JobsRepository;
import com.katrasolutions.gjethja.repository.ProviderRepository;
import com.katrasolutions.gjethja.request.ProviderUpdateRequest;
import com.katrasolutions.gjethja.service.CertificateService;
import com.katrasolutions.gjethja.service.ProviderService;
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
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderControllerIntegrationTest {

    private final static ObjectMapper om = new ObjectMapper();

    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "test@test.com";
    private static final String PASSWORD = "null";
    private static final String GENDER = "gender";
    private static final String PHONE_NUMBER = "phone";
    private static final String CITY = "city";
    private static final String BIO = "bio";
    private static final String RATE = "rate";
    private static final String EDUCATION = "education";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.parse(LocalDate.now().minusYears(20).toString());


    private static final String FIRSTNAME_UPDATED = "first";
    private static final String LASTNAME_UPDATED = "last";
    private static final String PHONE_NUMBER_UPDATED = "ph";
    private static final String CITY_UPDATED = "f";
    private static final String BIO_UPDATED = "hej";
    private static final String EDUCATION_UPDATED = "education1";

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    private Validator validator;

    @Autowired
    private EntityManager em;

    @Autowired
    private CertificateService certificateService;

    private Provider provider;

    private MockMvc mockMvc;

    @Before
    public void initTest() {
        provider = createEntity(em);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ProviderController providerController = new ProviderController(providerService, certificateService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(providerController).setControllerAdvice(customExceptionHandler).setValidator(validator).build();
    }

    private static Provider createEntity(EntityManager em) {
        Provider provider = new Provider();
        provider.setRate(RATE);
        provider.setPassword(PASSWORD);
        provider.setLastName(LASTNAME);
        provider.setFirstName(FIRSTNAME);
        provider.setEnabled(true);
        provider.setBio(BIO);
        provider.setCity(CITY);
        provider.setDateOfBirth(DATE_OF_BIRTH);
        provider.setEmail(EMAIL);
        provider.setGender(GENDER);
        provider.setPhoneNumber(PHONE_NUMBER);
        provider.setEducation(EDUCATION);
        return provider;
    }

    @Test
    @Transactional
    public void getProviderId() throws Exception {
        Jobs one = jobsRepository.getOne(1L);
        provider.setJobs(Collections.singletonList(one));

        providerRepository.saveAndFlush(provider);

        mockMvc.perform(MockMvcRequestBuilders.get("/providers/{id}", provider.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(FIRSTNAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(LASTNAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(EMAIL))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bio").value(BIO))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate").value(RATE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(CITY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(PHONE_NUMBER))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(CITY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.education").value(EDUCATION))
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobs").value(one.getJobName()));
    }

    @Test
    public void findPostsWithNoExistingId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/providers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void findAllProviders() throws Exception {
        Jobs one = jobsRepository.getOne(1L);
        provider.setJobs(Collections.singletonList(one));

        providerRepository.saveAndFlush(provider);

        mockMvc.perform(MockMvcRequestBuilders.get("/providers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].firstName").value(FIRSTNAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].lastName").value(LASTNAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].email").value(EMAIL))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].bio").value(BIO))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].rate").value(RATE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].city").value(CITY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].phoneNumber").value(PHONE_NUMBER))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].city").value(CITY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].education").value(EDUCATION))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].jobs").value(one.getJobName()));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void update() throws Exception {

        ProviderUpdateRequest providerUpdateRequest = new ProviderUpdateRequest();
        providerUpdateRequest.setLastName(LASTNAME_UPDATED);
        providerUpdateRequest.setFirstName(FIRSTNAME_UPDATED);
        providerUpdateRequest.setBio(BIO_UPDATED);
        providerUpdateRequest.setCity(CITY_UPDATED);
        providerUpdateRequest.setPhoneNumber(PHONE_NUMBER_UPDATED);
        providerUpdateRequest.setEducation(EDUCATION_UPDATED);

        providerRepository.saveAndFlush(this.provider);

        mockMvc.perform(put("/providers/")
                .content(om.writeValueAsString(providerUpdateRequest))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(FIRSTNAME_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(LASTNAME_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bio").value(BIO_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(CITY_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.education").value(EDUCATION_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(PHONE_NUMBER_UPDATED));
    }

    @Test
    @WithMockUser("test@test.com")
    @Transactional
    public void deleteProviders() throws Exception {
        providerRepository.saveAndFlush(this.provider);
        mockMvc.perform(delete("/providers/{id}", provider.getId()))
                .andExpect(status().isNoContent());
    }
}
