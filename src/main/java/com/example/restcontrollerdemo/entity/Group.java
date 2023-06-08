package com.example.restcontrollerdemo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Group {
    private int id;
    private String gorupName;
    private List<Teacher> teacherList;
}
