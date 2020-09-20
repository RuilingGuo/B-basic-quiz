package com.thoughtwork.gtb.basicquiz.service;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.exception.UserNotFoundException;
import com.thoughtwork.gtb.basicquiz.repository.EducationRespository_manual;
import com.thoughtwork.gtb.basicquiz.repository.UserRepository_manual;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private EducationRespository_manual educationRespositoryManual;
    private UserRepository_manual userRepositoryManual;

    public EducationService(EducationRespository_manual educationRespositoryManual, UserRepository_manual userRepositoryManual) {
        this.educationRespositoryManual = educationRespositoryManual;
        this.userRepositoryManual = userRepositoryManual;
    }

    public List<Education> findEducationByUserId(Integer userId) {
        return educationRespositoryManual.findEducationsByUserId(userId);
    }

    public Education createEducation(Integer userId, Education education) {
        if (!userRepositoryManual.isUserExistedByUserId(userId)) {
            throw new UserNotFoundException("user not existed");
        }
        education.setUserId(userId);
        return educationRespositoryManual.createEducation(education);
    }
}
