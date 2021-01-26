package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Certificate;
import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.entity.Provider;
import com.katrasolutions.gjethja.model.CertificateModel;
import com.katrasolutions.gjethja.model.ProviderModel;
import com.katrasolutions.gjethja.request.ProviderRegisterRequest;
import com.katrasolutions.gjethja.request.ProviderUpdateRequest;
import com.katrasolutions.gjethja.response.ProviderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ProviderMapperTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CertificateMapper certificateMapper;

    private static final Long ID = 1L;
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "password";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final String GENDER = "gender";
    private static final String PHONE_NUMBER = "phone";
    private static final String CITY = "city";
    private static final String BIO = "bio";
    private static final Boolean IS_ENABLED = null;
    private static final String RATE = "rate";
    private static final String PHOTO = null;
    private static final String EDUCATION = "education";
    private static final Set<CertificateModel> CERTIFICATES = Collections.singleton(new CertificateModel("test", "pdf"));
    private static final Set<Certificate> CERTIFICATES_ENTITY = Collections.singleton(new Certificate("test", "pdf", null));
    private static final List<Jobs> JOBS = Collections.singletonList(new Jobs("eldercare"));
    private static final List<String> JOBS_STRING = Collections.singletonList("eldercare");

    @InjectMocks
    private ProviderMapper providerMapper = new ProviderMapper(passwordEncoder, certificateMapper);

    @Test
    public void modelToEntityTest() {
        ProviderModel providerModel = getProviderModel();
        Provider provider = providerMapper.modelToEntity(providerModel);
        assertEquals(provider.getFirstName(), FIRSTNAME);
        assertEquals(provider.getLastName(), LASTNAME);
        assertEquals(provider.getEmail(), EMAIL);
        assertEquals(provider.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
        assertEquals(provider.getGender(), GENDER);
        assertEquals(provider.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(provider.getCity(), CITY);
        assertEquals(provider.getBio(), BIO);
        assertEquals(provider.getEnabled(), IS_ENABLED);
        assertEquals(provider.getRate(), RATE);
        assertEquals(provider.getEducation(), EDUCATION);
    }

    @Test
    public void entityToModelTest() {
        Provider provider = getProvider();
        ProviderModel providerModel = providerMapper.entityToModel(provider);
        assertEquals(providerModel.getFirstName(), FIRSTNAME);
        assertEquals(providerModel.getLastName(), LASTNAME);
        assertEquals(providerModel.getEmail(), EMAIL);
        assertEquals(providerModel.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
        assertEquals(providerModel.getGender(), GENDER);
        assertEquals(providerModel.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(providerModel.getCity(), CITY);
        assertEquals(providerModel.getBio(), BIO);
        assertEquals(providerModel.getEnabled(), IS_ENABLED);
        assertEquals(providerModel.getRate(), RATE);
        assertEquals(providerModel.getJobs(), JOBS_STRING);
        assertEquals(providerModel.getEducation(), EDUCATION);
    }

    @Test
    public void toResponse() {
        Provider provider = getProvider();
        ProviderResponse providerResponse = providerMapper.toResponse(provider);
        assertEquals(providerResponse.getFirstName(), FIRSTNAME);
        assertEquals(providerResponse.getLastName(), LASTNAME);
        assertEquals(providerResponse.getEmail(), EMAIL);
        assertEquals(providerResponse.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
        assertEquals(providerResponse.getGender(), GENDER);
        assertEquals(providerResponse.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(providerResponse.getCity(), CITY);
        assertEquals(providerResponse.getBio(), BIO);
        assertEquals(providerResponse.getRate(), RATE);
        assertEquals(providerResponse.getJobs(), JOBS_STRING);
        assertEquals(providerResponse.getEducation(), EDUCATION);
    }

    @Test
    public void mapProviderUserTest() {
        ProviderRegisterRequest providerRegisterRequest = getProviderRegisterRequest();
        Provider mapProviderUser = providerMapper.mapProviderUser(providerRegisterRequest);
        assertEquals(mapProviderUser.getFirstName(), FIRSTNAME);
        assertEquals(mapProviderUser.getLastName(), LASTNAME);
        assertEquals(mapProviderUser.getEmail(), EMAIL);
        assertEquals(mapProviderUser.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
        assertEquals(mapProviderUser.getGender(), GENDER);
        assertEquals(mapProviderUser.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(mapProviderUser.getCity(), CITY);
        assertEquals(mapProviderUser.getBio(), BIO);
        assertEquals(mapProviderUser.getEnabled(), Boolean.FALSE);
        assertEquals(mapProviderUser.getEducation(), EDUCATION);
        assertEquals(mapProviderUser.getProfilePicture(), PHOTO);
    }

    @Test
    public void updateExistingEntityTest() {
        Provider provider = getProvider();
        ProviderUpdateRequest providerUpdateRequest = getProviderUpdateRequest();
        providerUpdateRequest.setFirstName("first");
        providerUpdateRequest.setLastName("last");
        providerUpdateRequest.setBio("bioo");
        providerUpdateRequest.setCity("fr");
        providerUpdateRequest.setPhoneNumber("ph");
        providerUpdateRequest.setEducation("edu");

        provider = providerMapper.updateExistingEntity(provider, providerUpdateRequest);
        assertEquals(provider.getFirstName(), "first");
        assertEquals(provider.getLastName(), "last");
        assertEquals(provider.getPhoneNumber(), "ph");
        assertEquals(provider.getCity(), "fr");
        assertEquals(provider.getBio(), "bioo");
        assertEquals(provider.getEducation(), "edu");
    }

    private static ProviderRegisterRequest getProviderRegisterRequest() {
        ProviderRegisterRequest providerRegisterResquest = new ProviderRegisterRequest();
        providerRegisterResquest.setFirstName(FIRSTNAME);
        providerRegisterResquest.setLastName(LASTNAME);
        providerRegisterResquest.setEmail(EMAIL);
        providerRegisterResquest.setPassword(PASSWORD);
        providerRegisterResquest.setConfirmPassword(CONFIRM_PASSWORD);
        providerRegisterResquest.setDateOfBirth(DATE_OF_BIRTH);
        providerRegisterResquest.setGender(GENDER);
        providerRegisterResquest.setPhoneNumber(PHONE_NUMBER);
        providerRegisterResquest.setCity(CITY);
        providerRegisterResquest.setBio(BIO);
        providerRegisterResquest.setJobs(JOBS_STRING);
        providerRegisterResquest.setEducation(EDUCATION);
        return providerRegisterResquest;
    }

    private static ProviderUpdateRequest getProviderUpdateRequest() {
        ProviderUpdateRequest providerUpdateRequest = new ProviderUpdateRequest();
        providerUpdateRequest.setFirstName(FIRSTNAME);
        providerUpdateRequest.setLastName(LASTNAME);
        providerUpdateRequest.setCity(CITY);
        providerUpdateRequest.setBio(BIO);
        providerUpdateRequest.setEducation(EDUCATION);
        providerUpdateRequest.setPhoneNumber(PHONE_NUMBER);
        return providerUpdateRequest;
    }

    private static ProviderModel getProviderModel() {
        ProviderModel providerModel = new ProviderModel();
        providerModel.setId(ID);
        providerModel.setFirstName(FIRSTNAME);
        providerModel.setLastName(LASTNAME);
        providerModel.setEmail(EMAIL);
        providerModel.setPassword(PASSWORD);
        providerModel.setDateOfBirth(DATE_OF_BIRTH);
        providerModel.setGender(GENDER);
        providerModel.setPhoneNumber(PHONE_NUMBER);
        providerModel.setCity(CITY);
        providerModel.setBio(BIO);
        providerModel.setEnabled(IS_ENABLED);
        providerModel.setRate(RATE);
        providerModel.setProfilePicture(PHOTO);
        providerModel.setCertificates(CERTIFICATES);
        providerModel.setEducation(EDUCATION);
        return providerModel;
    }

    private static Provider getProvider() {
        Provider provider = new Provider();
        provider.setId(ID);
        provider.setFirstName(FIRSTNAME);
        provider.setLastName(LASTNAME);
        provider.setEmail(EMAIL);
        provider.setPassword(PASSWORD);
        provider.setDateOfBirth(DATE_OF_BIRTH);
        provider.setGender(GENDER);
        provider.setPhoneNumber(PHONE_NUMBER);
        provider.setCity(CITY);
        provider.setBio(BIO);
        provider.setEnabled(IS_ENABLED);
        provider.setRate(RATE);
        provider.setJobs(JOBS);
        provider.setEducation(EDUCATION);
        provider.setCertificates(CERTIFICATES_ENTITY);
        return provider;
    }
    private static ProviderResponse getProviderResponse() {
        ProviderResponse providerResponse = new ProviderResponse();
        providerResponse.setId(ID);
        providerResponse.setFirstName(FIRSTNAME);
        providerResponse.setLastName(LASTNAME);
        providerResponse.setEmail(EMAIL);
        providerResponse.setDateOfBirth(DATE_OF_BIRTH);
        providerResponse.setGender(GENDER);
        providerResponse.setPhoneNumber(PHONE_NUMBER);
        providerResponse.setCity(CITY);
        providerResponse.setBio(BIO);
        providerResponse.setRate(RATE);
        providerResponse.setJobs(JOBS_STRING);
        providerResponse.setEducation(EDUCATION);
        return providerResponse;
    }

}
