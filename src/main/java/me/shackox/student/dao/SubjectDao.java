package me.shackox.student.dao;

import me.shackox.student.domain.dto.SubjectDto;
import me.shackox.student.domain.report.SubjectReport;

import java.util.List;

/**
 * Created by SHACKOX on 22/01/17.
 */
public interface SubjectDao {

    /**
     * Metodo encargado de realizar la consulta de todos los subject y retornarlos
     * @return
     * @throws Exception
     */
    List<SubjectReport> subjectList() throws Exception;

    /**
     * Metodo encargado de realizar el delete de la base de datos
     * @param subjectDto
     * @throws Exception
     */
    void deleteSubject(SubjectDto subjectDto) throws Exception;

    /**
     * Metodo encargado de realizar el insert de un nuevo subject
     * @param subjectDto
     * @throws Exception
     */
    void saveSubject(SubjectDto subjectDto) throws Exception;

    /**
     * Metodo encargado de realizar el update de un subject
     * @param subjectDto
     * @throws Exception
     */
    void updateSubject(SubjectDto subjectDto) throws Exception;
}
