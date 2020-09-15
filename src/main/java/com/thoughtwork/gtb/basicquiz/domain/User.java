package com.thoughtwork.gtb.basicquiz.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String avatar;
    private String description;

}
