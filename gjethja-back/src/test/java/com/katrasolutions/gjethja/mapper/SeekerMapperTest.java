package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Seeker;
import com.katrasolutions.gjethja.model.SeekerModel;
import com.katrasolutions.gjethja.request.SeekerRegisterRequest;
import com.katrasolutions.gjethja.request.SeekerUpdateRequest;
import com.katrasolutions.gjethja.response.SeekerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class SeekerMapperTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private SeekerMapper seekerMapper = new SeekerMapper(passwordEncoder);


    private static final Long ID = 1L;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    private static final String GENDER = "gender";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String CITY = "city";
    private static final String BIO = "bio";
    private static final Boolean IS_ENABLED = null;
    private static final String PROFILE_PICTURE = null;
    private static final String RATE = "rate";


    @Test
    public void modelToEntityTest() {
        SeekerModel seekerModel = getSeekerModel();
        Seeker seeker = seekerMapper.modelToEntity(seekerModel);
        seeker.setProfilePicture(null);
        assertEquals(seeker.getFirstName(), FIRST_NAME);
        assertEquals(seeker.getLastName(), LAST_NAME);
        assertEquals(seeker.getEmail(), EMAIL);
        assertEquals(seeker.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
        assertEquals(seeker.getGender(), GENDER);
        assertEquals(seeker.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(seeker.getRate(), RATE);
        assertEquals(seeker.getEnabled(), IS_ENABLED);
        assertEquals(seeker.getCity(), CITY);
        assertNull(seeker.getProfilePicture());
    }

    @Test
    public void toResponse() {
        Seeker seeker = getSeeker();
        SeekerResponse seekerResponse = seekerMapper.toResponse(seeker);
        assertEquals(seekerResponse.getFirstName(), FIRST_NAME);
        assertEquals(seekerResponse.getLastName(), LAST_NAME);
        assertEquals(seekerResponse.getEmail(), EMAIL);
        assertEquals(seekerResponse.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
        assertEquals(seekerResponse.getGender(), GENDER);
        assertEquals(seekerResponse.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(seekerResponse.getCity(), CITY);
        assertEquals(seekerResponse.getBio(), BIO);
        assertEquals(seekerResponse.getRate(), RATE);
    }

    @Test
    public void entityToModelTest() {
        Seeker seeker = getSeeker();
        SeekerModel seekerModel = seekerMapper.entityToModel(seeker);
        seekerModel.setProfilePicture(null);
        assertEquals(seekerModel.getFirstName(), FIRST_NAME);
        assertEquals(seekerModel.getLastName(), LAST_NAME);
        assertEquals(seekerModel.getEmail(), EMAIL);
        assertEquals(seekerModel.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
        assertEquals(seekerModel.getGender(), GENDER);
        assertEquals(seekerModel.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(seekerModel.getRate(), RATE);
        assertEquals(seekerModel.getEnabled(), IS_ENABLED);
        assertEquals(seekerModel.getCity(), CITY);
        assertNull(seekerModel.getProfilePicture());

    }

    @Test
    public void mapSeekerUserTest() {
        SeekerRegisterRequest seekerRegisterRequest = getSeekerRegisterRequest();
        Seeker seeker = seekerMapper.mapSeekerUser(seekerRegisterRequest);
        assertEquals(seeker.getFirstName(), FIRST_NAME);
        assertEquals(seeker.getLastName(), LAST_NAME);
        assertEquals(seeker.getEmail(), EMAIL);
        assertEquals(seeker.getGender(), GENDER);
        assertEquals(seeker.getBio(), BIO);
        assertEquals(seeker.getCity(), CITY);
        assertEquals(seeker.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(seeker.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());

    }


    private static SeekerModel getSeekerModel() {
        SeekerModel seekerModel = new SeekerModel();
        seekerModel.setId(ID);
        seekerModel.setFirstName(FIRST_NAME);
        seekerModel.setLastName(LAST_NAME);
        seekerModel.setEmail(EMAIL);
        seekerModel.setPassword(PASSWORD);
        seekerModel.setDateOfBirth(DATE_OF_BIRTH);
        seekerModel.setGender(GENDER);
        seekerModel.setPhoneNumber(PHONE_NUMBER);
        seekerModel.setCity(CITY);
        seekerModel.setBio(BIO);
        seekerModel.setEnabled(IS_ENABLED);
        seekerModel.setRate(RATE);
        seekerModel.setProfilePicture(PROFILE_PICTURE);
        return seekerModel;

    }


    private static Seeker getSeeker() {
        Seeker seeker = new Seeker();

        seeker.setId(ID);
        seeker.setFirstName(FIRST_NAME);
        seeker.setLastName(LAST_NAME);
        seeker.setEmail(EMAIL);
        seeker.setPassword(PASSWORD);
        seeker.setDateOfBirth(DATE_OF_BIRTH);
        seeker.setGender(GENDER);
        seeker.setPhoneNumber(PHONE_NUMBER);
        seeker.setCity(CITY);
        seeker.setBio(BIO);
        seeker.setEnabled(IS_ENABLED);
        seeker.setRate(RATE);
        return seeker;

    }

    private static SeekerRegisterRequest getSeekerRegisterRequest() {
        SeekerRegisterRequest seekerRegisterRequest = new SeekerRegisterRequest();

        seekerRegisterRequest.setFirstName(FIRST_NAME);
        seekerRegisterRequest.setLastName(LAST_NAME);
        seekerRegisterRequest.setEmail(EMAIL);
        seekerRegisterRequest.setPassword(PASSWORD);
        seekerRegisterRequest.setGender(GENDER);
        seekerRegisterRequest.setDateOfBirth(DATE_OF_BIRTH);
        seekerRegisterRequest.setBio(BIO);
        seekerRegisterRequest.setCity(CITY);
        seekerRegisterRequest.setPhoneNumber(PHONE_NUMBER);
        return seekerRegisterRequest;

    }

    @Test
    public void updateExistingEntity() {
        Seeker seeker = getSeeker();
        SeekerUpdateRequest seekerUpdateRequest = new SeekerUpdateRequest();
        seekerUpdateRequest.setFirstName(FIRST_NAME);
        seekerUpdateRequest.setLastName(LAST_NAME);
        seekerUpdateRequest.setPhoneNumber(PHONE_NUMBER);
        seekerUpdateRequest.setCity(CITY);
        seekerUpdateRequest.setBio(BIO);

        assertEquals(seeker.getFirstName(), FIRST_NAME);
        assertEquals(seeker.getLastName(), LAST_NAME);
        assertEquals(seeker.getPhoneNumber(), PHONE_NUMBER);
        assertEquals(seeker.getCity(), CITY);
        assertEquals(seeker.getBio(), BIO);

//        SeekerModel seekerModel = getSeekerModel();
//        seekerModel.setFirstName(FIRST_NAME);
//        seekerModel.setLastName(LAST_NAME);
//        seekerModel.setEmail(EMAIL);
//        seekerModel.setPassword(PASSWORD);
//        seekerModel.setDateOfBirth(DATE_OF_BIRTH);
//        seekerModel.setGender(GENDER);
//        seekerModel.setPhoneNumber(PHONE_NUMBER);
//        seekerModel.setCity(CITY);
//        seekerModel.setBio(BIO);
//        seekerModel.setEnabled(IS_ENABLED);
//        seekerModel.setRate(RATE);
//        seekerModel.setProfilePicture(PROFILE_PICTURE);
//        seekerModel.setProfilePicture(null);
//        assertEquals(seeker.getFirstName(), FIRST_NAME);
//        assertEquals(seeker.getLastName(), LAST_NAME);
//        assertEquals(seeker.getEmail(), EMAIL);
//        assertEquals(seeker.getDateOfBirth().toString(), DATE_OF_BIRTH.toString());
//        assertEquals(seeker.getGender(), GENDER);
//        assertEquals(seeker.getPhoneNumber(), PHONE_NUMBER);
//        assertEquals(seeker.getRate(), RATE);
//        assertEquals(seeker.getEnabled(), IS_ENABLED);
//        assertEquals(seeker.getCity(), CITY);
//        assertNull(seeker.getProfilePicture());
    }

}
