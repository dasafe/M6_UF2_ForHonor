package persistencia;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import practica.Personajes;

public class HibernateManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ArrayList<Personajes> personajes = (ArrayList<Personajes>) session.createQuery("FROM Personajes").list();

		int opcion = 0;

		do {
			System.out.println("\n\n1. Listado de personajes\n2. Modificar ataque personaje\n3. Borrar Personaje");
			System.out.println("\nElige una opcion:");
			opcion = reader.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("\n- Listado -");
				try {
					for (Personajes l : personajes) {
						System.out.println("\n Nombre: " + l.getNombrePersonaje());
						System.out.println(" Ataque: " + l.getAtaque());
						System.out.println("Defensa: " + l.getDefensa());
						System.out.println("Faccion: " + l.getFaccion().getNombreFaccion());
					}
					session.getTransaction().commit();
				} catch (HibernateException e) {
					if (session != null)
						session.getTransaction().rollback();
					e.printStackTrace();
				}
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:
				System.out.println("\nBye!");
				break;

			default:
				System.out.println("\nOpcion invalida");
				break;
			}
		} while (opcion != 7);

		HibernateUtil.getSessionFactory().close();
	}

}
