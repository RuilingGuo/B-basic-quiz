package com.thoughtwork.gtb.basicquiz.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @NotBlank
    @Size(min = 1,max = 128)
    private String name;
    @Range(min = 16)
    private Long age;
    @Size(min = 8,max = 512)
    private String avatar;
    @Size(min = 0,max = 1024)
    private String description;

}
