package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.entity.Seeker;
import com.katrasolutions.gjethja.model.SeekerModel;
import com.katrasolutions.gjethja.request.SeekerRegisterRequest;

import com.katrasolutions.gjethja.request.SeekerUpdateRequest;
import com.katrasolutions.gjethja.response.SeekerResponse;
import com.katrasolutions.gjethja.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeekerMapper {

    private final PasswordEncoder passwordEncoder;

    public SeekerMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Seeker mapSeekerUser(SeekerRegisterRequest seekerRegisterRequest) {
        Seeker seeker = new Seeker();
        seeker.setFirstName(seekerRegisterRequest.getFirstName());
        seeker.setLastName(seekerRegisterRequest.getLastName());
        seeker.setEmail(seekerRegisterRequest.getEmail());
        seeker.setPassword(passwordEncoder.encode(seekerRegisterRequest.getPassword()));
        seeker.setBio(seekerRegisterRequest.getBio());
        seeker.setCity(seekerRegisterRequest.getCity());
        seeker.setDateOfBirth(seekerRegisterRequest.getDateOfBirth());
        seeker.setGender(seekerRegisterRequest.getGender());
        seeker.setPhoneNumber(seekerRegisterRequest.getPhoneNumber());

        return seeker;
    }
    public SeekerResponse toResponse(Seeker seeker) {
        SeekerResponse seekerResponse = new SeekerResponse();
        seekerResponse.setId(seeker.getId());
        seekerResponse.setFirstName(seeker.getFirstName());
        seekerResponse.setLastName(seeker.getLastName());
        seekerResponse.setEmail(seeker.getEmail());
        seekerResponse.setAddress(seeker.getAddress());
        seekerResponse.setCity(seeker.getCity());
        seekerResponse.setDateOfBirth(seeker.getDateOfBirth());
        seekerResponse.setGender(seeker.getGender());
        seekerResponse.setPhoneNumber(seeker.getPhoneNumber());
        seekerResponse.setProfilePicture(seeker.getProfilePicture() == null ? null : ImageUtils.convertImageToString(seeker.getProfilePicture()));
        seekerResponse.setRate(seeker.getRate());
        seekerResponse.setBio(seeker.getBio());
        return seekerResponse;
    }

    public List<SeekerModel> entitiesToModels(List<Seeker> seekers) {
        return seekers.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Seeker> modelsToEntities(List<SeekerModel> seekers) {
        return seekers.stream().map(this::modelToEntity).collect(Collectors.toList());
    }

    public Seeker modelToEntity(SeekerModel seekerModel) {

        Seeker seeker = new Seeker();
        seeker.setFirstName(seekerModel.getFirstName());
        seeker.setLastName(seekerModel.getLastName());
        seeker.setGender(seekerModel.getGender());
        seeker.setPhoneNumber(seekerModel.getPhoneNumber());
        seeker.setDateOfBirth(seekerModel.getDateOfBirth());
        seeker.setCity(seekerModel.getCity());
        seeker.setAddress((seekerModel.getAddress()));
        seeker.setBio(seekerModel.getBio());
        seeker.setEmail(seekerModel.getEmail());
        seeker.setProfilePicture(seekerModel.getProfilePicture() == null ? null : ImageUtils.convertStringToImage(seekerModel.getProfilePicture()));
        seeker.setRate(seekerModel.getRate());

        return seeker;
    }

    public SeekerModel entityToModel(Seeker seeker) {

        SeekerModel seekerModel = new SeekerModel();
        seekerModel.setId(seeker.getId());
        seekerModel.setLastName(seeker.getLastName());
        seekerModel.setFirstName(seeker.getFirstName());
        seekerModel.setGender(seeker.getGender());
        seekerModel.setPhoneNumber(seeker.getPhoneNumber());
        seekerModel.setDateOfBirth(seeker.getDateOfBirth());
        seekerModel.setCity(seeker.getCity());
        seekerModel.setAddress(seeker.getAddress());
        seekerModel.setBio(seeker.getBio());
        seekerModel.setEmail(seeker.getEmail());
        seekerModel.setRate(seeker.getRate());

        return seekerModel;
    }

    public Seeker updateExistingEntity(Seeker seeker, SeekerUpdateRequest seekerUpdateRequest) {
        if (seekerUpdateRequest.getFirstName() != null) {
            seeker.setFirstName(seekerUpdateRequest.getFirstName());
        }
        if (seekerUpdateRequest.getLastName() != null) {
            seeker.setLastName(seekerUpdateRequest.getLastName());
        }
        if (seekerUpdateRequest.getPhoneNumber() != null) {
            seeker.setPhoneNumber(seekerUpdateRequest.getPhoneNumber());
        }
        if (seekerUpdateRequest.getCity() != null) {
            seeker.setCity(seekerUpdateRequest.getCity());
        }
        if (seekerUpdateRequest.getAddress() != null) {
            seeker.setAddress(seekerUpdateRequest.getAddress());
        }
        if (seekerUpdateRequest.getBio() != null) {
            seeker.setBio(seekerUpdateRequest.getBio());
        }
        return seeker;
    }

}
