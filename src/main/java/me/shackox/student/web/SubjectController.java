package me.shackox.student.web;

import me.shackox.student.domain.dto.SubjectDto;
import me.shackox.student.domain.report.SubjectReport;
import me.shackox.student.service.SubjectService;
import me.shackox.util.NotificationType;
import me.shackox.util.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
 * Created by SHACKOX on 22/01/17.
 */

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("views/subject/index");
    }

    @RequestMapping("/get-data.do")
    @ResponseBody
    public List<SubjectReport> getData(HttpServletRequest request, HttpServletResponse response) {

        List<SubjectReport> result = new ArrayList<>();
        try {
            result = subjectService.getAllSubjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) {

        Long subjectId = Long.valueOf(String.valueOf(request.getParameter("id")));

        Map<String, Object> result = new HashMap<>();

        try {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(subjectId);
            subjectService.deleteSubject(subjectDto);
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_STATUS_OK.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "The subject has been deleted successful.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.SUCCESS.getShortName());
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_MESSAGE_ERROR.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "Error! subject can't be deleted.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.ERROR.getShortName());
        }

        return result;
    }

    @RequestMapping("/add-subject.do")
    @ResponseBody
    public Map<String, Object> addSubject(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String information = request.getParameter("information");
        boolean active = Boolean.valueOf(request.getParameter("active"));

        Map<String, Object> result = new HashMap<>();

        try {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setName(name);
            subjectDto.setInformation(information);
            subjectDto.setActive(active);
            subjectService.saveSubject(subjectDto);
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_STATUS_OK.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "The subject has been saved successful.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.SUCCESS.getShortName());
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_MESSAGE_ERROR.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "Error! an error occurred saving subject.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.ERROR.getShortName());
        }

        return result;
    }

    @RequestMapping("/update-subject.do")
    @ResponseBody
    public Map<String, Object> updateSubject(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String information = request.getParameter("information");
        boolean active = Boolean.valueOf(request.getParameter("active"));

        Map<String, Object> result = new HashMap<>();

        try {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(id);
            subjectDto.setName(name);
            subjectDto.setInformation(information);
            subjectDto.setActive(active);
            subjectService.updateSubject(subjectDto);
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_STATUS_OK.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "The subject has been updated successful.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.SUCCESS.getShortName());
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseType.RESPONSE_STATUS.getKeyResponse(), ResponseType.RESPONSE_MESSAGE_ERROR.getValueResponse());
            result.put(ResponseType.RESPONSE_MESSAGE.getKeyResponse(), "Error! an error occurred updating subject.");
            result.put(NotificationType.NOTIFICATION.getCompleteName(), NotificationType.ERROR.getShortName());
        }

        return result;
    }
}
