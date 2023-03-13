package org.example;

import org.hibernate.Session;

import java.util.List;

public class StudentDaoImpl implements StudentDao{

    private SessionFactoryUtils sessionFactoryUtils;

    public StudentDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Student findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Student> students = session
                    .createQuery("select s from Student s")
                    .getResultList();
            session.getTransaction().commit();
            return students;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.remove(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveOrUpdate(Student student) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveList(List<Student> students) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            students.forEach(session::persist);
            session.getTransaction().commit();
        }
    }
}
