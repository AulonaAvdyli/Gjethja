package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.request.ProviderRegisterRequest;
import com.katrasolutions.gjethja.request.ProviderUpdateRequest;
import com.katrasolutions.gjethja.response.ProviderResponse;
import com.katrasolutions.gjethja.response.RegisterResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProviderService {

    RegisterResponse register(ProviderRegisterRequest registerRequest, HttpServletRequest httpServletRequest);

    ProviderResponse getProvider(Long providerId);

    List<ProviderResponse> findAllProviders();

    ProviderResponse update(ProviderUpdateRequest providerUpdateRequest);

    void delete();

    List<ProviderResponse> filterData(Long startAge, Long endAge, String city, String education);
}
