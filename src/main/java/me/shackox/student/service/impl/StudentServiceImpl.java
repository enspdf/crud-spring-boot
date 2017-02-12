package me.shackox.student.service.impl;

import me.shackox.student.dao.StudentDao;
import me.shackox.student.domain.dto.StudentDto;
import me.shackox.student.domain.report.StudentReport;
import me.shackox.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by SHACKOX on 28/01/17.
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional
    public List<StudentReport> getAllStudents() throws Exception {
        return studentDao.studentList();
    }

    @Override
    @Transactional
    public void deleteStudent(StudentDto studentDto) throws Exception {
        Assert.notNull(studentDto, "dto is required");
        Assert.notNull(studentDto.getId(), "student id is required");

        studentDao.deleteStudent(studentDto);
    }

    @Override
    @Transactional
    public void saveStudent(StudentDto studentDto) throws Exception {
        Assert.notNull(studentDto, "dto is required");
        Assert.notNull(studentDto.getName(), "student name is required");
        Assert.notNull(studentDto.getAge(), "student age is required");

        studentDao.saveStudent(studentDto);
    }

    @Override
    @Transactional
    public void updateStudent(StudentDto studentDto) throws Exception {
        Assert.notNull(studentDto, "dto is required");
        Assert.notNull(studentDto.getId(), "student id is required");
        Assert.notNull(studentDto.getName(), "student name is required");
        Assert.notNull(studentDto.getAge(), "student age is required");

        studentDao.updateStudent(studentDto);
    }
}
