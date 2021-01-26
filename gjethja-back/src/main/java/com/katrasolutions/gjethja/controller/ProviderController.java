package com.katrasolutions.gjethja.controller;

import com.katrasolutions.gjethja.entity.Certificate;
import com.katrasolutions.gjethja.model.ProviderModel;
import com.katrasolutions.gjethja.request.ProviderRegisterRequest;
import com.katrasolutions.gjethja.request.ProviderUpdateRequest;
import com.katrasolutions.gjethja.response.ProviderResponse;
import com.katrasolutions.gjethja.response.RegisterResponse;
import com.katrasolutions.gjethja.service.CertificateService;
import com.katrasolutions.gjethja.service.ProviderService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/providers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProviderController {

    private final ProviderService providerService;
    private final CertificateService certificateService;

    public ProviderController(ProviderService providerService, CertificateService certificateService) {
        this.providerService = providerService;
        this.certificateService = certificateService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse registerProvider(@RequestBody @Valid ProviderRegisterRequest providerRegisterRequest, HttpServletRequest httpServletRequest) {
        return providerService.register(providerRegisterRequest, httpServletRequest);
    }

    @PostMapping(value = "/add-certificate")
    public ProviderModel addCertificate(@RequestParam("file") MultipartFile multipartFile) {
        return certificateService.addCertificate(multipartFile);
    }

    @GetMapping("/certificate/{id}")
    public ResponseEntity<Resource> getCertificate(@PathVariable String id) {
        Certificate certificate = certificateService.getCertificate(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(certificate.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + certificate.getFileName() + "\"")
                .body(new ByteArrayResource(certificate.getData()));
    }

    @GetMapping("/{id}")
    public ProviderResponse getProvider(@PathVariable("id") Long id) {
        return providerService.getProvider(id);
    }

    @GetMapping
    public List<ProviderResponse> findAllProviders() {
        return providerService.findAllProviders();
    }

    @PutMapping
    public ProviderResponse update(@RequestBody @Valid ProviderUpdateRequest providerUpdateRequest) {
        return providerService.update(providerUpdateRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        providerService.delete();
    }

    @GetMapping("/filter")
    public List<ProviderResponse> filterData(@RequestParam(required = false) Long startAge, @RequestParam(required = false) Long endAge,
                                             @RequestParam(required = false) String city, @RequestParam(required = false) String education) {
        return providerService.filterData(startAge, endAge, city, education);
    }
}
