package com.thoughtwork.gtb.basicquiz.repository;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EducationRespository {

    private Map<Integer,Education> educationList ;
    private static Integer EDUCATION_INC_NUM = 1;

    private UserRepository userRepository;

    public EducationRespository(UserRepository userRepository) {
        this.educationList = new HashMap<>();
        this.userRepository = userRepository;

    }

    public Education createEducation(Education education) {
        education.setId(EDUCATION_INC_NUM++);
        educationList.put(education.getId(),education);
        return education;
    }

    public List<Education> findEducationsByUserId(Integer userId){
        if(!userRepository.isUserExistedByUserId(userId)){
            throw new UserNotFoundException("user not existed");
        }
        return educationList.values().stream()
                .filter(education -> education.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    private void initEducationList(){
        Education education1 = new Education(
                EDUCATION_INC_NUM++,
                1,
                2005,
                "Secondary school specializing in artistic",
                "Eos, explicabo, nam, tenetur et ab eius " +
                        "deserunt aspernatur ipsum ducimus quibusdam " +
                        "quis voluptatibus.");
        Education education2 = new Education(
                EDUCATION_INC_NUM++,
                1,
                2009,
                "First level graduation in Graphic Design",
                "Aspernatur, mollitia, quos maxime eius " +
                        "suscipit sed beatae ducimus quaerat quibusdam " +
                        "perferendis? Iusto, quibusdam asperiores unde " +
                        "repellat.");
        this.educationList.put(education1.getId(),education1);
        this.educationList.put(education2.getId(),education2);
    }
}
