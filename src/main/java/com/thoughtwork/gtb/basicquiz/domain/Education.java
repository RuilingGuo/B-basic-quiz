package com.thoughtwork.gtb.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Education {
    private Integer id;
    private Integer userId;
    @Min(value = 1900)
    private Long year;
    @Size(min = 1,max = 256)
    private String title;
    @Size(min = 1,max = 4096)
    private String description;
}
