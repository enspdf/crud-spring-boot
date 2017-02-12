package me.shackox.student.service;

import me.shackox.student.domain.dto.StudentDto;
import me.shackox.student.domain.report.StudentReport;

import java.util.List;

/**
 * Created by SHACKOX on 28/01/17.
 */
public interface StudentService {
    /**
     * Metodo encargado de retornar la lista de estudiantes
     * @return
     * @throws Exception
     */
    List<StudentReport> getAllStudents() throws Exception;

    /**
     * Metodo encargado de realizar el delete de un student
     * @param studentDto
     * @throws Exception
     */
    void deleteStudent(StudentDto studentDto) throws Exception;

    /**
     * Metodo encargado de guardar un student
     * @param studentDto
     * @throws Exception
     */
    void saveStudent(StudentDto studentDto) throws Exception;

    /**
     * Metodo encargado de actualizar un student
     * @param studentDto
     * @throws Exception
     */
    void updateStudent(StudentDto studentDto) throws Exception;
}
