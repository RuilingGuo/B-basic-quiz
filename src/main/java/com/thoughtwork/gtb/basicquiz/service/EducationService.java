package com.thoughtwork.gtb.basicquiz.service;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.domain.User;
import com.thoughtwork.gtb.basicquiz.exception.UserNotFoundException;
import com.thoughtwork.gtb.basicquiz.repository.EducationRespository;
import com.thoughtwork.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {
    private EducationRespository educationRespository;
    private UserRepository userRepository;

    public EducationService(EducationRespository educationRespository, UserRepository userRepository) {
        this.educationRespository = educationRespository;
        this.userRepository = userRepository;
    }

    public List<Education> findEducationByUserId(Integer userId) {
        return educationRespository.findEducationsByUserId(userId);
    }

    public Education createEducation(Integer userId, Education education) {
        if (!userRepository.isUserExistedByUserId(userId)) {
            throw new UserNotFoundException("user not existed");
        }
        education.setUserId(userId);
        return educationRespository.createEducation(education);
    }
}
