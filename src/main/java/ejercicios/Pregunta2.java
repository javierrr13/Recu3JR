package ejercicios;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import mapeoclases.Fabricantes;

public class Pregunta2 {
	
	
	public static void main (String [] args) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = sesion.beginTransaction();
		
		try {
			Fabricantes f1 = new Fabricantes();
			f1.setCodFab("222");
			f1.setNombre("ALBERTI");
			f1.setPais("armenia");
			
			sesion.persist(f1);
			transaction.commit();
			
			System.out.println("Fabricante insertado con exito");
		}catch (ConstraintViolationException e) {
			// TODO: handle exception
			System.out.println("Fabricante ya existente");
			transaction.rollback();
		}catch (PersistentObjectException e) {
			// TODO: handle exception
			System.out.println("Persistence error");
			transaction.rollback();
		}finally {
			sesion.close();
		}
		
	}
}
