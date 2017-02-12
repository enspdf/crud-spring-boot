package me.shackox.student.domain.report;

/**
 * Created by SHACKOX on 22/01/17.
 */
public class StudentReport {
    private Long id;
    private String Name;
    private Long age;
    private Long subjectId;
    private String subjectName;
    private String subjectInformation;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectInformation() {
        return subjectInformation;
    }

    public void setSubjectInformation(String subjectInformation) {
        this.subjectInformation = subjectInformation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
