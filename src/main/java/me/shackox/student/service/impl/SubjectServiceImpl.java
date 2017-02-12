package me.shackox.student.service.impl;

import me.shackox.student.dao.SubjectDao;
import me.shackox.student.domain.dto.SubjectDto;
import me.shackox.student.domain.report.SubjectReport;
import me.shackox.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by SHACKOX on 22/01/17.
 */

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    @Transactional(readOnly = true)
    public List<SubjectReport> getAllSubjects() throws Exception {
        return subjectDao.subjectList();
    }

    @Override
    @Transactional
    public void deleteSubject(SubjectDto subjectDto) throws Exception {
        Assert.notNull(subjectDto, "dto is required");
        Assert.notNull(subjectDto.getId(), "subject id is required");

        subjectDao.deleteSubject(subjectDto);
    }

    @Override
    @Transactional
    public void saveSubject(SubjectDto subjectDto) throws Exception {
        Assert.notNull(subjectDto, "dto is required");
        Assert.notNull(subjectDto.getName(), "subject name is required");

        subjectDao.saveSubject(subjectDto);
    }

    @Override
    @Transactional
    public void updateSubject(SubjectDto subjectDto) throws Exception {
        Assert.notNull(subjectDto, "dto is required");
        Assert.notNull(subjectDto.getId(), "subject id is required");
        Assert.notNull(subjectDto.getName(), "subject name is required");

        subjectDao.updateSubject(subjectDto);
    }
}
