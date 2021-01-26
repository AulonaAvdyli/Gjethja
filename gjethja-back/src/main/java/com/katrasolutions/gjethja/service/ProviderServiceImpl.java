package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.entity.Jobs;
import com.katrasolutions.gjethja.entity.Provider;
import com.katrasolutions.gjethja.entity.Role;
import com.katrasolutions.gjethja.enums.RoleName;
import com.katrasolutions.gjethja.exception.ExceptionMessage;
import com.katrasolutions.gjethja.exception.RestApiBadRequestException;
import com.katrasolutions.gjethja.exception.RestApiNotFoundException;
import com.katrasolutions.gjethja.exception.RestApiUnauthorizedException;
import com.katrasolutions.gjethja.mapper.ProviderMapper;
import com.katrasolutions.gjethja.repository.JobsRepository;
import com.katrasolutions.gjethja.repository.ProviderRepository;
import com.katrasolutions.gjethja.repository.RoleRepository;
import com.katrasolutions.gjethja.request.ProviderRegisterRequest;
import com.katrasolutions.gjethja.request.ProviderUpdateRequest;
import com.katrasolutions.gjethja.response.ProviderResponse;
import com.katrasolutions.gjethja.response.RegisterResponse;
import com.katrasolutions.gjethja.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final RoleRepository roleRepository;
    private final EmailSenderService emailSenderService;
    private final JobsRepository jobsRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository, ProviderMapper providerMapper, RoleRepository roleRepository, EmailSenderService emailSenderService, JobsRepository jobsRepository) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.roleRepository = roleRepository;
        this.emailSenderService = emailSenderService;
        this.jobsRepository = jobsRepository;
    }

    @Transactional
    @Override
    public RegisterResponse register(ProviderRegisterRequest registerRequest, HttpServletRequest httpServletRequest) {
        Provider provider = providerMapper.mapProviderUser(registerRequest);
        Role role = roleRepository.findByName(RoleName.ROLE_USER);
        provider.setRoles(Collections.singleton(role));
        provider.setJobs(validateJobs(registerRequest.getJobs()));
        providerRepository.saveAndFlush(provider);
        emailSenderService.sendConfirmationEmail(provider, registerRequest.getUrl());
        return new RegisterResponse(true, provider.getFirstName() + " registered successfully, all that's left is to confirm the email");
    }

    private List<Jobs> validateJobs(List<String> jobNames) {
        List<String> jobs = jobsRepository.findAll().stream().map(Jobs::getJobName).collect(toList());
        for (String job : jobNames) {
            if (!jobs.contains(job)) {
                throw new RestApiNotFoundException(ExceptionMessage.JOB_NOT_FOUND);
            }
        }
        return jobsRepository.findAll().stream().filter(jobs1 -> jobNames.contains(jobs1.getJobName())).collect(toList());
    }

    @Override
    public ProviderResponse getProvider(Long providerId) {
        Provider provider = providerRepository.findById(providerId).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        return providerMapper.toResponse(provider);
    }

    @Override
    public List<ProviderResponse> findAllProviders() {
        return providerRepository.findAll().stream().map(providerMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProviderResponse update(ProviderUpdateRequest providerUpdateRequest) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        Provider provider = providerRepository.findByEmail(currentUser);
        provider = providerMapper.updateExistingEntity(provider, providerUpdateRequest);
        return providerMapper.toResponse(provider);
    }

    @Override
    public void delete() {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        Provider provider = providerRepository.findByEmail(currentUser);
        provider.setEnabled(false);
        providerRepository.save(provider);
    }

    private boolean validateRageOfAge(Provider entity, Long startAge, Long endAge) {
        Period period = Period.between(entity.getDateOfBirth(), LocalDate.now());
        int age = period.getYears();
        if (endAge == null) {
            return age >= startAge;
        }
        if (startAge == null) {
            return age <= endAge;
        }
        if (endAge < startAge) {
            throw new RestApiBadRequestException(ExceptionMessage.AGE_BAD_REQUEST);
        }
        return age >= startAge && age <= endAge;
    }

    @Override
    public List<ProviderResponse> filterData(Long startAge, Long endAge, String city, String education) {
        if (startAge == null && endAge == null && StringUtils.isEmpty(city) && StringUtils.isEmpty(education)) {
            return providerRepository.findAll().stream().map(providerMapper::toResponse).collect(toList());
        }

        return providerRepository.findAll().stream()
                .filter(provider -> {
                    if (!StringUtils.isEmpty(city)) {
                        return provider.getCity().equalsIgnoreCase(city);
                    }
                    return true;
                }).filter(provider -> {
                    if (!StringUtils.isEmpty(education)) {
                        return provider.getEducation().equalsIgnoreCase(education);
                    }
                    return true;
                }).filter(provider -> {
                    if (startAge != null || endAge != null) {
                        return validateRageOfAge(provider, startAge, endAge);
                    }
                    return true;
                }).map(providerMapper::toResponse).collect(toList());
    }
}