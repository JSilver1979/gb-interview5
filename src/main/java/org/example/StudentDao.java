package org.example;

import java.util.List;

public interface StudentDao {
    Student findById(Long id);

    List<Student> findAll();

    void deleteById(Long id);

    void saveOrUpdate(Student student);

    void saveList(List<Student> students);
}
