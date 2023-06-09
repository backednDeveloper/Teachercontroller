package com.example.restcontrollerdemo.service;

import com.example.restcontrollerdemo.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private List<Teacher> teacherServices = new ArrayList<>();
    private Long sequence = 0L;

    public List<Teacher> getAllTeacher() {
        return teacherServices;
    }

    public List<Teacher> createTeacherService(Teacher teacher) {
        teacher.setID(Math.toIntExact(++sequence));
        teacherServices.add(teacher);
        return teacherServices;
    }

    public Teacher updateTeacherService(Teacher teacherService, long id) {
        Teacher oldTeacher = teacherServices.stream()
                .filter(teacher -> teacher.equals(teacherService) || teacher.getID() == id)
                .findFirst()
                .orElse(null);
        if (oldTeacher != null) {
            oldTeacher.setName(teacherService.getName());
            oldTeacher.setSurname(teacherService.getSurname());
            oldTeacher.setSalary(teacherService.getSalary());
            oldTeacher.setID(teacherService.getID());
            oldTeacher.setAge(teacherService.getAge());
            oldTeacher.setGroups(teacherService.getGroups());
            oldTeacher.setSubjects(teacherService.getSubjects());
        }
        return oldTeacher;
    }

    public Teacher getSearchTeacher(long id , Teacher name) {
        Teacher searchTeacher = teacherServices.stream()
                .filter(teacher -> teacher.getID() == id || teacher.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (searchTeacher == null) {
            System.out.println("This teacher does not exist.");
        }
        return searchTeacher;
    }

    public void deleteTeacher(long id, Teacher teacher) {
        teacherServices.removeIf(teacher1 -> teacher1.getID() == id || teacher1.equals(teacher));
    }
}

