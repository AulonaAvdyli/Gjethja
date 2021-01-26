package com.katrasolutions.gjethja.repository;

import com.katrasolutions.gjethja.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, String> {
}
