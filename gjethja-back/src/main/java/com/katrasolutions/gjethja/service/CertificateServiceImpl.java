package com.katrasolutions.gjethja.service;


import com.katrasolutions.gjethja.entity.Certificate;
import com.katrasolutions.gjethja.entity.Provider;
import com.katrasolutions.gjethja.exception.ExceptionMessage;
import com.katrasolutions.gjethja.exception.RestApiForbiddenException;
import com.katrasolutions.gjethja.exception.RestApiNotFoundException;
import com.katrasolutions.gjethja.exception.RestApiUnauthorizedException;
import com.katrasolutions.gjethja.mapper.CertificateMapper;
import com.katrasolutions.gjethja.mapper.ProviderMapper;
import com.katrasolutions.gjethja.model.ProviderModel;
import com.katrasolutions.gjethja.repository.CertificateRepository;
import com.katrasolutions.gjethja.repository.ProviderRepository;
import com.katrasolutions.gjethja.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final ProviderRepository providerRepository;

    private final CertificateMapper certificateMapper;

    private final CertificateRepository certificateRepository;

    private final ProviderMapper providerMapper;

    public CertificateServiceImpl(ProviderRepository providerRepository, CertificateMapper certificateMapper, CertificateRepository certificateRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.certificateMapper = certificateMapper;
        this.certificateRepository = certificateRepository;
        this.providerMapper = providerMapper;
    }

    @Override
    @Transactional
    public ProviderModel addCertificate(MultipartFile multipartFile) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        Provider provider = Optional.ofNullable(providerRepository.findByEmail(currentUser))
                .orElseThrow(() -> new RestApiForbiddenException(ExceptionMessage.ADD_CERTIFICATE_FORBIDDEN));
        Certificate certificate = certificateMapper.multipartFileToEntity(multipartFile);
        provider.getCertificates().add(certificate);
        certificate.setProvider(provider);
        certificateRepository.save(certificate);
        providerRepository.save(provider);
        return providerMapper.entityToModel(provider);
    }

    @Override
    public Certificate getCertificate(String id) {
        return certificateRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.CERTIFICATE_NOT_FOUND));
    }
}
