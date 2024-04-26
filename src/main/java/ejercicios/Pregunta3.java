package ejercicios;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import mapeoclases.Fabricantes;
import mapeoclases.Programas;

public class Pregunta3 {
    public static void main(String[] args) {
       
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        /*if(args.length!=3) {
        	System.out.println("[!] Revise los parametros especificados desde el main ");
        	return;
        }*/
        
        String s = "os";
        String s1 = "2";
        try {
            transaction = session.beginTransaction();

            Query<Fabricantes> queryFabricantes = session.createQuery("FROM Fabricantes", Fabricantes.class);
            java.util.List<Fabricantes> fabricantes = queryFabricantes.getResultList();
            Query<Programas> queryprogramas = session.createQuery("FROM Programas", Programas.class);
            java.util.List<Programas> programas = queryprogramas.getResultList();

            for (Programas programa : programas) {
                for (Fabricantes fabricante : fabricantes) {
                    /* Aquí metemos la condicional para el filtrado y la impresión de los datos que queremos. */

                    if (programa.getNombre().toLowerCase().startsWith(s.toLowerCase()) && programa.getVer().contains(s1)) {
                        System.out.println("[+] Programa " + programa.getNombre() + " - versión - " + programa.getVer());
                        System.out.println("    Fabricante: " + fabricante.getNombre());
                        System.out.println();
                    }
                }
            }
     
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
