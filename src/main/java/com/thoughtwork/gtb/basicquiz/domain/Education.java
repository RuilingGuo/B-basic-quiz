package com.thoughtwork.gtb.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Education {
    private Integer id;
    private Integer userId;
    private Integer year;

    @Size
    private String title;
    private String description;
}
