package com.example.gbt_4.info;


import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PracticeDto implements Serializable {

    private String name;
    private int age;

    public PracticeDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
