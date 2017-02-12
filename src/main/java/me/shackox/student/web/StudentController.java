package me.shackox.student.web;

import me.shackox.student.domain.dto.StudentDto;
import me.shackox.student.domain.dto.SubjectDto;
import me.shackox.student.domain.report.StudentReport;
import me.shackox.student.service.StudentService;
import me.shackox.util.NotificationType;
import me.shackox.util.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SHACKOX on 28/01/17.
 */

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("views/student/index");
    }

    @RequestMapping("/get-data.do")
    @ResponseBody
    public List<StudentReport> getData(HttpServletRequest request, HttpServletResponse response) {

        List<StudentReport> result = new ArrayList<>();

        try {
            result = studentService.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) {

        Long studentId = Long.valueOf(request.getParameter("id"));

        Map<String, Object> result = new HashMap<>();

        try {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(studentId);
            studentService.deleteStudent(studentDto);
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_STATUS_OK.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "The student has been deleted successful.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.SUCCESS.getShortName());
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_MESSAGE_ERROR.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "Error! student can't be deleted.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.ERROR.getShortName());
        }

        return result;
    }

    @RequestMapping("/add-student.do")
    @ResponseBody
    public Map<String, Object> addStudent(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        Long age = Long.valueOf(request.getParameter("age"));
        Long subjectId = Long.valueOf(request.getParameter("subjectId"));
        String notes = request.getParameter("notes");

        Map<String, Object> result = new HashMap<>();

        try {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(subjectId);
            StudentDto studentDto = new StudentDto();
            studentDto.setName(name);
            studentDto.setAge(age);
            studentDto.setSubject(subjectDto);
            studentDto.setName(notes);
            studentService.saveStudent(studentDto);
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_STATUS_OK.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "The student has been saved successful.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.SUCCESS.getShortName());
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_MESSAGE_ERROR.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "Error! an error occurred saving student.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.ERROR.getShortName());
        }

        return result;
    }

    @RequestMapping("/update-student.do")
    @ResponseBody
    public Map<String, Object> updateStudent(HttpServletRequest request, HttpServletResponse response) {

        Long studentId = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        Long age = Long.valueOf(request.getParameter("age"));
        Long subjectId = Long.valueOf(request.getParameter("subjectId"));
        String notes = request.getParameter("notes");

        Map<String, Object> result = new HashMap<>();

        try {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(subjectId);
            StudentDto studentDto = new StudentDto();
            studentDto.setId(studentId);
            studentDto.setName(name);
            studentDto.setAge(age);
            studentDto.setSubject(subjectDto);
            studentDto.setNotes(notes);
            studentService.updateStudent(studentDto);
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_STATUS_OK.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "The student has been updated successful.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.SUCCESS.getShortName());
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_MESSAGE_ERROR.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "Error! an error occurred updating student.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.ERROR.getShortName());
        }

        return result;
    }
}
