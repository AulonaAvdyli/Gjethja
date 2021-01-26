package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.entity.Provider;
import com.katrasolutions.gjethja.model.ProviderModel;
import com.katrasolutions.gjethja.request.ProviderRegisterRequest;
import com.katrasolutions.gjethja.request.ProviderUpdateRequest;
import com.katrasolutions.gjethja.response.ProviderResponse;
import com.katrasolutions.gjethja.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProviderMapper {

    private final PasswordEncoder passwordEncoder;
    private final CertificateMapper certificateMapper;

    public ProviderMapper(PasswordEncoder passwordEncoder, CertificateMapper certificateMapper) {
        this.passwordEncoder = passwordEncoder;
        this.certificateMapper = certificateMapper;
    }

    public Provider mapProviderUser(ProviderRegisterRequest providerRegisterRequest) {
        Provider provider = new Provider();
        provider.setFirstName(providerRegisterRequest.getFirstName());
        provider.setLastName(providerRegisterRequest.getLastName());
        provider.setEmail(providerRegisterRequest.getEmail());
        provider.setPassword(passwordEncoder.encode(providerRegisterRequest.getPassword()));
        provider.setBio(providerRegisterRequest.getBio());
        provider.setGender(providerRegisterRequest.getGender());
        provider.setCity(providerRegisterRequest.getCity());
        provider.setPhoneNumber(providerRegisterRequest.getPhoneNumber());
        provider.setDateOfBirth(providerRegisterRequest.getDateOfBirth());
        provider.setEducation(providerRegisterRequest.getEducation());
        provider.setEnabled(false);

        return provider;
    }

    public ProviderResponse toResponse(Provider provider) {
        ProviderResponse providerResponse = new ProviderResponse();
        providerResponse.setId(provider.getId());
        providerResponse.setFirstName(provider.getFirstName());
        providerResponse.setLastName(provider.getLastName());
        providerResponse.setEmail(provider.getEmail());
        providerResponse.setAddress(provider.getAddress());
        providerResponse.setCity(provider.getCity());
        providerResponse.setEducation(provider.getEducation());
        providerResponse.setDateOfBirth(provider.getDateOfBirth());
        providerResponse.setGender(provider.getGender());
        providerResponse.setPhoneNumber(provider.getPhoneNumber());
        providerResponse.setJobs(provider.getJobs().stream().map(Jobs::getJobName).collect(Collectors.toList()));
        providerResponse.setProfilePicture(provider.getProfilePicture() == null ? null : ImageUtils.convertImageToString(provider.getProfilePicture()));
        providerResponse.setRate(provider.getRate());
        providerResponse.setBio(provider.getBio());
        return providerResponse;
    }

    public List<ProviderModel> entitiesToModels(List<Provider> seekers) {
        return seekers.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    public List<Provider> modelsToEntities(List<ProviderModel> seekers) {
        return seekers.stream().map(this::modelToEntity).collect(Collectors.toList());
    }

    public Provider modelToEntity(ProviderModel providerModel) {

        Provider provider = new Provider();
        provider.setFirstName(providerModel.getFirstName());
        provider.setLastName(providerModel.getLastName());
        provider.setGender(providerModel.getGender());
        provider.setPhoneNumber(providerModel.getPhoneNumber());
        provider.setDateOfBirth(providerModel.getDateOfBirth());
        provider.setCity(providerModel.getCity());
        provider.setBio(providerModel.getBio());
        provider.setEmail(providerModel.getEmail());
        provider.setProfilePicture(providerModel.getProfilePicture() == null ? null : ImageUtils.convertStringToImage(providerModel.getProfilePicture()));
        provider.setRate(providerModel.getRate());
        provider.setEducation(providerModel.getEducation());

        return provider;
    }

    public ProviderModel entityToModel(Provider provider) {

        ProviderModel providerModel = new ProviderModel();
        providerModel.setId(provider.getId());
        providerModel.setFirstName(provider.getFirstName());
        providerModel.setLastName(provider.getLastName());
        providerModel.setGender(provider.getGender());
        providerModel.setPhoneNumber(provider.getPhoneNumber());
        providerModel.setDateOfBirth(provider.getDateOfBirth());
        providerModel.setCity(provider.getCity());
        providerModel.setEmail(provider.getEmail());
        providerModel.setBio(provider.getBio());
        providerModel.setProfilePicture(provider.getProfilePicture() == null ? null : ImageUtils.convertImageToString(provider.getProfilePicture()));
        providerModel.setRate(provider.getRate());
        providerModel.setEducation(provider.getEducation());
        providerModel.setCertificates(provider.getCertificates().stream().map(c -> certificateMapper.entityToModel(c)).collect(Collectors.toSet()));
        providerModel.setJobs(provider.getJobs().stream().map(Jobs::getJobName).collect(Collectors.toList()));

        return providerModel;
    }

    public Provider updateExistingEntity(Provider provider, ProviderUpdateRequest providerUpdateRequest) {

        if (providerUpdateRequest.getFirstName() != null) {
            provider.setFirstName(providerUpdateRequest.getFirstName());
        }

        if (providerUpdateRequest.getLastName() != null) {
            provider.setLastName(providerUpdateRequest.getLastName());
        }

        if (providerUpdateRequest.getBio() != null) {
            provider.setBio(providerUpdateRequest.getBio());
        }

        if (providerUpdateRequest.getPhoneNumber() != null) {
            provider.setPhoneNumber(providerUpdateRequest.getPhoneNumber());
        }

        if (providerUpdateRequest.getCity() != null) {
            provider.setCity(providerUpdateRequest.getCity());
        }

        if (providerUpdateRequest.getEducation() != null) {
            provider.setEducation(providerUpdateRequest.getEducation());
        }

        return provider;
    }
}
