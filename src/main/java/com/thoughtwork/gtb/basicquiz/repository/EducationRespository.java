package com.thoughtwork.gtb.basicquiz.repository;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

// GTB: - 拼写错误：Respository，IDEA 都提醒了，怎么还是不改？
@Repository
public class EducationRespository {

    // GTB: - 通常在声明处直接初始化
    private Map<Integer,Education> educationList ;
    // GTB: - EDUCATION_INC_NUM 不是常量，最好别全大写
    private static Integer EDUCATION_INC_NUM = 1;

    // GTB: - 通常一个 Repository 不会再依赖另一个 Repository 了，你遇到的问题需要用另外的方式解决，可以跟组里小伙伴讨论交流一下
    private UserRepository userRepository;

    public EducationRespository(UserRepository userRepository) {
        this.educationList = new HashMap<>();
        this.initEducationList();
        this.userRepository = userRepository;

    }

    public Education createEducation(Education education) {
        education.setId(EDUCATION_INC_NUM++);
        educationList.put(education.getId(),education);
        return education;
    }

    public List<Education> findEducationsByUserId(Integer userId){
        // GTB: 有这个判断很好，但这也让当前 repo 依赖了 UserRepository，前面我也写了，看看怎们去掉这个依赖
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
