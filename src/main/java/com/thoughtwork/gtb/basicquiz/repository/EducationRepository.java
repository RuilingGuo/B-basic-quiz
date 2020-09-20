package com.thoughtwork.gtb.basicquiz.repository;

import com.thoughtwork.gtb.basicquiz.dto.EducationDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends CrudRepository<EducationDto,Long> {

    List<EducationDto> findEducationByUserId(Long userId);

    @Override
    <S extends EducationDto> S save(S entity);
}
