package com.thoughtwork.gtb.basicquiz.dto;

import com.thoughtwork.gtb.basicquiz.domain.Education;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "education")
public class EducationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Min(value = 1900)
    Long year;
    @Size(min = 1,max = 256)
    String title;
    @Size(min = 1,max = 4096)
    String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserDto user;


    public static EducationDto bind(Education education, UserDto userDto) {
        return EducationDto.builder()
                .year(education.getYear())
                .title(education.getTitle())
                .description(education.getDescription())
                .user(userDto)
                .build();
    }
}