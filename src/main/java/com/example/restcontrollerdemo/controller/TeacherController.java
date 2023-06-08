package com.example.restcontrollerdemo.controller;

import com.example.restcontrollerdemo.entity.Teacher;
import com.example.restcontrollerdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public List<Teacher> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/{id}")
    public Teacher searchTeacherWithId(@PathVariable long id) {
        return teacherService.getSearchTeacher(id);
    }

    @PostMapping("/create")
    public List<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacherService(teacher);
    }

    @PutMapping("/update/{id}")
    public Teacher updateTeacher(@PathVariable long id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacherService(teacher, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeacher(@RequestBody Teacher teacher, @PathVariable long id) {
        teacherService.deleteTeacher(id, teacher);
    }
}

