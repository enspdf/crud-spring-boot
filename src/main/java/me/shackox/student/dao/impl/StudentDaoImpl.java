package me.shackox.student.dao.impl;

import me.shackox.student.dao.StudentDao;
import me.shackox.student.domain.dto.StudentDto;
import me.shackox.student.domain.report.StudentReport;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SHACKOX on 28/01/17.
 */

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<StudentReport> studentList() throws Exception {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append(" SELECT student.id, student.name, student.age, ")
                .append(" subject.id AS subjectId, subject.name AS subjectName, subject.information AS subjectInformation, ")
                .append(" student.notes ")
                .append(" FROM student student ")
                .append(" INNER JOIN subject subject ")
                .append(" ON student.subject = subject.id ");

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString());

        query.setResultTransformer(Transformers.aliasToBean(StudentReport.class));

        query.addScalar("id", StandardBasicTypes.LONG);
        query.addScalar("name", StandardBasicTypes.STRING);
        query.addScalar("age", StandardBasicTypes.LONG);
        query.addScalar("subjectId", StandardBasicTypes.LONG);
        query.addScalar("subjectName", StandardBasicTypes.STRING);
        query.addScalar("subjectInformation", StandardBasicTypes.STRING);
        query.addScalar("notes", StandardBasicTypes.STRING);

        return query.list();
    }

    @Override
    @Transactional
    public void deleteStudent(StudentDto studentDto) throws Exception {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append(" DELETE FROM student WHERE id = :id ");

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString());
        query.setLong("id", studentDto.getId());

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void saveStudent(StudentDto studentDto) throws Exception {
        StringBuilder strQuery = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        strQuery.append(" INSERT INTO student (name, age, subject, notes) VALUES (:name, :age, :subject, :notes) ");
        params.put("name", studentDto.getName());
        params.put("age", studentDto.getAge());
        params.put("subject", studentDto.getSubject().getId());
        params.put("notes", StringUtils.defaultIfEmpty(studentDto.getNotes(), null));

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString());
        query.setProperties(params);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void updateStudent(StudentDto studentDto) throws Exception {
        StringBuilder strQuery = new StringBuilder();
        StringBuilder strQueryAdditional = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        strQuery.append(" UPDATE student SET name = :name ");

        if (StringUtils.isNotBlank(String.valueOf(studentDto.getAge()))) {
            strQueryAdditional.append(" , age = :age ");
            params.put("age", studentDto.getAge());
        }

        if (studentDto.getSubject() != null && StringUtils.isNotBlank(String.valueOf(studentDto.getSubject().getId()))) {
            strQueryAdditional.append(" , subject = :subjectId ");
            params.put("subjectId", studentDto.getSubject().getId());
        }

        if (StringUtils.isNotBlank(studentDto.getNotes())) {
            strQueryAdditional.append(" , notes = :notes ");
            params.put("notes", studentDto.getNotes());
        }

        strQuery.append(" WHERE id = :id ");

        params.put("name", studentDto.getName());
        params.put("id", studentDto.getId());

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString() + strQueryAdditional.toString());
        query.setProperties(params);

        query.executeUpdate();
    }
}
