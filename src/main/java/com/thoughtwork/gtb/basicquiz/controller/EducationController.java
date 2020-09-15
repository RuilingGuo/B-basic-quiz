package com.thoughtwork.gtb.basicquiz.controller;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class EducationController {

    private EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/{userId}/educations")
    public List<Education> findByUserId(@PathVariable Integer userId) {
        return educationService.findEducationByUserId(userId);
    }

    @PostMapping("/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education createEducation(@PathVariable Integer userId, @RequestBody Education education) {
        return educationService.createEducation(userId, education);
    }
}
