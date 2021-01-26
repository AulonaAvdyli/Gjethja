package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.request.SeekerRegisterRequest;
import com.katrasolutions.gjethja.request.SeekerUpdateRequest;
import com.katrasolutions.gjethja.response.RegisterResponse;
import com.katrasolutions.gjethja.response.SeekerResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SeekerService {

    RegisterResponse register(SeekerRegisterRequest registerRequest, HttpServletRequest httpServletRequest);

    SeekerResponse update(SeekerUpdateRequest seekerUpdateRequest);

    SeekerResponse getSeeker(Long seekerId);

    List<SeekerResponse> findAllSeekers();

    void delete();

}
