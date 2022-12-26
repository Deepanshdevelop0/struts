package com.registration.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.registration.jpa.student;
import com.registration.hbnprop.Hbnprop;

public class Userdao {
	
	public void saveUser(student stud) {
        Transaction transaction = null;
        try (Session session = Hbnprop.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(stud);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
	
	
	public student getUser(int id) {

        Transaction transaction = null;
        student user = null;
        try (Session session = Hbnprop.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.get(student.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get all Users
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < student > getAllUser() {

        Transaction transaction = null;
        List < student > listOfUser = null;
        try (Session session = Hbnprop.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            listOfUser = session.createQuery("from student").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
	
}
