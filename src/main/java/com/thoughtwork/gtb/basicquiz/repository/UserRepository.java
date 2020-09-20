package com.thoughtwork.gtb.basicquiz.repository;

import com.thoughtwork.gtb.basicquiz.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDto,Long> {
    @Override
    Optional<UserDto> findById(Long aLong);

    @Override
    <S extends UserDto> S save(S entity);

    @Override
    boolean existsById(Long userId);
}
