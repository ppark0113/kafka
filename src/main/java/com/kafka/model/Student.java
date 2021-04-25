package com.kafka.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Student {

    private String name;
    private int id;

    public Student(int id, String name ){
        this.id = id;
        this.name = name;
    }
}
