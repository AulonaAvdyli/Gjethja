package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.entity.Certificate;
import com.katrasolutions.gjethja.model.ProviderModel;
import org.springframework.web.multipart.MultipartFile;

public interface CertificateService {

    ProviderModel addCertificate(MultipartFile multipartFile);

    Certificate getCertificate(String id);
}
