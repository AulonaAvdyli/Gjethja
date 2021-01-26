package com.katrasolutions.gjethja.controller;

import com.katrasolutions.gjethja.model.SeekerModel;
import com.katrasolutions.gjethja.request.SeekerRegisterRequest;
import com.katrasolutions.gjethja.request.SeekerUpdateRequest;
import com.katrasolutions.gjethja.response.RegisterResponse;
import com.katrasolutions.gjethja.response.SeekerResponse;
import com.katrasolutions.gjethja.service.SeekerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/seekers", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeekerController {

    private final SeekerService seekerService;

    public SeekerController(SeekerService seekerService) {
        this.seekerService = seekerService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse registerProvider(@RequestBody @Valid SeekerRegisterRequest seekerRegisterRequest, HttpServletRequest httpServletRequest) {
        return seekerService.register(seekerRegisterRequest, httpServletRequest);
    }

    @PutMapping
    public SeekerResponse update(@RequestBody SeekerUpdateRequest seekerUpdateRequest) {
        return seekerService.update(seekerUpdateRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        seekerService.delete();
    }

    @GetMapping("/{id}")
    public SeekerResponse getSeeker(@PathVariable("id") Long id) {
        return seekerService.getSeeker(id);
    }

    @GetMapping
    public List<SeekerResponse> findAllSeekers() {
        return seekerService.findAllSeekers();
    }

}
