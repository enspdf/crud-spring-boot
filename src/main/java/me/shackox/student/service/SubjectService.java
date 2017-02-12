package me.shackox.student.service;

import me.shackox.student.domain.dto.SubjectDto;
import me.shackox.student.domain.report.SubjectReport;

import java.util.List;

/**
 * Created by SHACKOX on 22/01/17.
 */
public interface SubjectService {
    /**
     * Metodo encargado de retornar una lista de subjects activos
     * @return
     * @throws Exception
     */
    List<SubjectReport> getAllSubjects() throws Exception;

    /**
     * Metodo encargado de realizar el delete de un subject
     * @param subjectDto
     * @throws Exception
     */
    void deleteSubject(SubjectDto subjectDto) throws Exception;

    /**
     * Metodo encargado de guardar un nuevo subject
     * @param subjectDto
     * @throws Exception
     */
    void saveSubject(SubjectDto subjectDto) throws Exception;

    /**
     * Metodo encargado de actualizar un subject
     * @param subjectDto
     * @throws Exception
     */
    void updateSubject(SubjectDto subjectDto) throws Exception;
}
