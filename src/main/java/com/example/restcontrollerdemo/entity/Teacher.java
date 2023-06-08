package com.example.restcontrollerdemo.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Teacher {
    private String scienceName;
    private String name;
    private String surname;
    private int ID;
    private int age;
    private int salary;
    private List<Group> groups;
    private List<Subject> subjects;
}


