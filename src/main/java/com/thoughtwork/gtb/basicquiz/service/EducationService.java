package com.thoughtwork.gtb.basicquiz.service;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.dto.EducationDto;
import com.thoughtwork.gtb.basicquiz.dto.UserDto;
import com.thoughtwork.gtb.basicquiz.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private EducationRepository educationRepository;
    private UserService userService;

    public EducationService(EducationRepository educationRepository, UserService userService) {
        this.educationRepository = educationRepository;
        this.userService = userService;
    }

    public List<EducationDto> findEducationByUserId(Long userId) {
        return educationRepository.findEducationByUserId(userId);
    }

    public EducationDto createEducation(Long userId, Education education) {
        UserDto userDto = userService.findUserById(userId);
        return educationRepository.save(EducationDto.bind(education,userDto));
    }
}
