package com.thoughtwork.gtb.basicquiz.controller;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.dto.EducationDto;
import com.thoughtwork.gtb.basicquiz.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class EducationController {

    private EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/{userId}/educations")
    public List<EducationDto> findByUserId(@PathVariable Long userId) {
        return educationService.findEducationByUserId(userId);
    }

    @PostMapping("/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public EducationDto createEducation(@PathVariable Long userId, @RequestBody @Valid Education education) {
        return educationService.createEducation(userId, education);
    }
}
