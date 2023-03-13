package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        List<Student> students = new ArrayList<>();
        try {
            StudentDao studentDao = new StudentDaoImpl(sessionFactoryUtils);
            for (int i = 0; i < 100; i++) {
                students.add(new Student("Student " + i, (int) (Math.random()*5)));
            }
            studentDao.saveList(students);
            System.out.println(studentDao.findAll());
            studentDao.deleteById(1L);
            System.out.println(studentDao.findAll());
            Student student = studentDao.findById(2L);
            student.setName("Changed Name");
            studentDao.saveOrUpdate(student);
            System.out.println(studentDao.findAll());
            Student newStudent = new Student();
            newStudent.setName("New Student");
            newStudent.setMark(10);
            studentDao.saveOrUpdate(newStudent);
            System.out.println(studentDao.findAll());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}