package com.katrasolutions.gjethja.mapper;

import com.katrasolutions.gjethja.entity.Certificate;
import com.katrasolutions.gjethja.mapper.CertificateMapper;
import com.katrasolutions.gjethja.model.CertificateModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateMapperTest {
    private static final String ID = "id";
    private static final String FILE_NAME = "fileName";
    private static final String CONTENT_TYPE = "contentType";

    CertificateMapper certificateMapper = new CertificateMapper();

    @Test
    public void entityToModelTest() {
        Certificate certificate = getCertifacte();
        CertificateModel certificateModel = certificateMapper.entityToModel(certificate);
        assertEquals(certificateModel.getId(), ID);
        assertEquals(certificateModel.getFileName(), FILE_NAME);
        assertEquals(certificateModel.getContentType(), CONTENT_TYPE);

    }

    private static CertificateModel getCertificateModel() {
        CertificateModel certificateModel = new CertificateModel();
        certificateModel.setId(ID);
        certificateModel.setFileName(FILE_NAME);
        certificateModel.setContentType(CONTENT_TYPE);
        return certificateModel;
    }

    private static Certificate getCertifacte() {
        Certificate certificate = new Certificate();
        certificate.setId(ID);
        certificate.setFileName(FILE_NAME);
        certificate.setContentType(CONTENT_TYPE);
        return certificate;
    }
}
