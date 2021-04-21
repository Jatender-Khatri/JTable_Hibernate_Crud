package com.DaoImp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Dao.StudentDao;
import com.DbManager.DbConnection;
import com.Model.Student;

public class StudentDaoImp implements StudentDao {
	
	@Override
	public Integer addStudent(Student student) {
		Integer roll= null;
		try {
			SessionFactory factory = DbConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.save(student);
			transaction.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}

	@Override
	public List<Student> getStudents() {
		List<Student> list = new ArrayList<>();
		try {
			SessionFactory factory = DbConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			list = session.createQuery("From Student").list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer deleteStudent(Student student) {
		Integer roll = null;
		try {
			SessionFactory factory = DbConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.remove(student);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}

	@Override
	public Student getStudentById(int id) {
		Student s = null;
		try {
			SessionFactory factory = DbConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			s = session.get(Student.class, id);
			
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public Integer updateStudent(Student student) {
		Integer roll = null;
		try {
			SessionFactory factory = DbConnection.getFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.update(student);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roll;
	}

	@Override
	public Integer getIdByStudent(String name) {
		Student student = null;
		Integer roll = null;
		try {
			SessionFactory factory = DbConnection.getFactory();
			Session session = factory.openSession();

			Transaction transaction = session.beginTransaction();

			Query query=session.createQuery("from Student t where t.name=:n");
			query.setParameter("n", name);
			  
			List list= query.list();//will return the records from 5 to 10th number  
			
			student =(Student) list.get(0);

			transaction.commit();
			//factory.close();
			session.close();
		}catch( Exception e) {
			e.printStackTrace();
		}
		return student.getId();
	}
	
}
