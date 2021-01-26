package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Certificate;
import com.katrasolutions.gjethja.model.CertificateModel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class CertificateMapper {

    public CertificateModel entityToModel(Certificate certificate) {
        CertificateModel model = new CertificateModel();
        model.setContentType(certificate.getContentType());
        model.setFileName(certificate.getFileName());
        model.setId(certificate.getId());
        return model;
    }

    public Certificate multipartFileToEntity(MultipartFile multipartFile) {
         Certificate entity = new Certificate();
        entity.setFileName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        entity.setContentType(multipartFile.getContentType());
        try {
            entity.setData(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
