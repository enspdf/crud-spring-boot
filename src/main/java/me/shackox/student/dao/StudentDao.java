package me.shackox.student.dao;

import me.shackox.student.domain.dto.StudentDto;
import me.shackox.student.domain.report.StudentReport;

import java.util.List;

/**
 * Created by SHACKOX on 28/01/17.
 */
public interface StudentDao {

    /**
     * Metodo encargado de realizar la consulta de todos los students y retornarlos
     * @return
     * @throws Exception
     */
    List<StudentReport> studentList() throws Exception;

    /**
     * Metodo encargado de realizar el delete de la base de datos
     * @param studentDto
     * @throws Exception
     */
    void deleteStudent(StudentDto studentDto) throws Exception;

    /**
     * Metodo encargado de realizar el insert de un nuevo student
     * @param studentDto
     * @throws Exception
     */
    void saveStudent(StudentDto studentDto) throws Exception;

    /**
     * Metodo encargado de realizar el update de un student
     * @param studentDto
     * @throws Exception
     */
    void updateStudent(StudentDto studentDto) throws Exception;
}
