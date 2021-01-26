package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.entity.Role;
import com.katrasolutions.gjethja.entity.Seeker;
import com.katrasolutions.gjethja.enums.RoleName;
import com.katrasolutions.gjethja.exception.ExceptionMessage;
import com.katrasolutions.gjethja.exception.RestApiNotFoundException;
import com.katrasolutions.gjethja.exception.RestApiUnauthorizedException;
import com.katrasolutions.gjethja.mapper.SeekerMapper;
import com.katrasolutions.gjethja.repository.RoleRepository;
import com.katrasolutions.gjethja.repository.SeekerRepository;
import com.katrasolutions.gjethja.request.SeekerRegisterRequest;
import com.katrasolutions.gjethja.request.SeekerUpdateRequest;
import com.katrasolutions.gjethja.response.RegisterResponse;
import com.katrasolutions.gjethja.response.SeekerResponse;
import com.katrasolutions.gjethja.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeekerServiceImpl implements SeekerService {

    private final SeekerMapper seekerMapper;
    private final RoleRepository roleRepository;
    private final SeekerRepository seekerRepository;
    private final EmailSenderService emailSenderService;

    public SeekerServiceImpl(SeekerMapper seekerMapper, RoleRepository roleRepository, SeekerRepository seekerRepository, EmailSenderService emailSenderService) {
        this.seekerMapper = seekerMapper;
        this.roleRepository = roleRepository;
        this.seekerRepository = seekerRepository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    @Transactional
    public RegisterResponse register(SeekerRegisterRequest registerRequest, HttpServletRequest httpServletRequest) {
        Seeker seeker = seekerMapper.mapSeekerUser(registerRequest);
        Role role = roleRepository.findByName(RoleName.ROLE_USER);
        seeker.setRoles(Collections.singleton(role));
        seekerRepository.save(seeker);
        emailSenderService.sendConfirmationEmail(seeker, registerRequest.getUrl());

        return new RegisterResponse(true, registerRequest.getFirstName() + " registered successfully, all that's left is to confirm the email");
    }

    @Override
    @Transactional
    public SeekerResponse update(SeekerUpdateRequest seekerUpdateRequest) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        Seeker seeker = seekerRepository.findByEmail(currentUser);
        seeker = seekerMapper.updateExistingEntity(seeker, seekerUpdateRequest);
        return seekerMapper.toResponse(seeker);
    }

    @Override
    public SeekerResponse getSeeker(Long seekerId) {
        Seeker seeker = seekerRepository.findById(seekerId).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        return seekerMapper.toResponse(seeker);
    }

    @Override
    public List<SeekerResponse> findAllSeekers() {
        return seekerRepository.findAll().stream().map(seekerMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete() {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        Seeker seeker = seekerRepository.findByEmail(currentUser);
        seeker.setEnabled(false);
        seekerRepository.save(seeker);
//        seekerRepository.delete(seeker);
    }

    private String confirmUrl(HttpServletRequest httpServletRequest) {
        String requestUrl = httpServletRequest.getRequestURL().toString();
        return requestUrl.replaceAll("seekers/register", "confirm-account");
    }
}
