package me.shackox.student.dao.impl;

import me.shackox.student.dao.SubjectDao;
import me.shackox.student.domain.dto.SubjectDto;
import me.shackox.hibernate.entity.Subject;
import me.shackox.student.domain.report.SubjectReport;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SHACKOX on 22/01/17.
 */

@Repository
public class SubjectDaoImpl implements SubjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<SubjectReport> subjectList() throws Exception {
        StringBuilder strQuery = new StringBuilder();

        strQuery.append(" SELECT id, name, information, active FROM subject WHERE active = 1 ");

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString());
        query.setResultTransformer(Transformers.aliasToBean(SubjectReport.class));

        query.addScalar("id", StandardBasicTypes.LONG);
        query.addScalar("name", StandardBasicTypes.STRING);
        query.addScalar("information", StandardBasicTypes.STRING);
        query.addScalar("active", StandardBasicTypes.BOOLEAN);

        return query.list();
    }

    @Override
    @Transactional
    public void deleteSubject(SubjectDto subjectDto) throws Exception {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append("  DELETE FROM subject WHERE id = :id ");

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString());
        query.setLong("id", subjectDto.getId());

        query.executeUpdate();

    }

    @Override
    @Transactional
    public void saveSubject(SubjectDto subjectDto) throws Exception {
        StringBuilder strQuery = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        strQuery.append(" INSERT INTO subject (name, information, active) VALUES (:name, :information, :active) ");
        params.put("name", subjectDto.getName());
        params.put("information", StringUtils.defaultIfEmpty(subjectDto.getInformation(), null));
        params.put("active", subjectDto.getActive() ? 1 : 0);

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString());
        query.setProperties(params);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void updateSubject(SubjectDto subjectDto) throws Exception {
        StringBuilder strQuery = new StringBuilder();
        StringBuilder strQueryAdditional = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        strQuery.append(" UPDATE subject SET name = :name ");

        if (StringUtils.isNotBlank(subjectDto.getInformation())) {
            strQueryAdditional.append(" , information = :information ");
            params.put("information", subjectDto.getInformation());
        }

        if (StringUtils.isNotBlank(String.valueOf(subjectDto.getActive()))) {
            strQueryAdditional.append(" , active = :active ");
            params.put("active", subjectDto.getActive());
        }

        strQuery.append(" WHERE id = :id ");

        params.put("name", subjectDto.getName());
        params.put("id", subjectDto.getId());

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery.toString() + strQueryAdditional.toString());
        query.setProperties(params);

        query.executeUpdate();
    }
}
