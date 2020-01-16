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
		ArrayList<Personajes> personajes = (ArrayList<Personajes>) session.createQuery("FROM Personajes").list();

		int opcion = 0;
		int id;

		do {
			System.out.println(
					"\n\n1. Listado de personajes\n2. Modificar ataque personaje\n3. Borrar Personaje\n4. Cambiar faccion de personaje\n5. Salir");
			System.out.println("\nElige una opcion:");
			opcion = reader.nextInt();
			switch (opcion) {
			case 1:
				session.beginTransaction();
				System.out.println("\n- Listado -");
				try {
					for (Personajes l : personajes) {
						System.out.println("\n     ID: " + l.getPersonajeId());
						System.out.println(" Nombre: " + l.getNombrePersonaje());
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
				session.beginTransaction();
				int ataque;
				System.out.println("\nID del personaje a modificar:");
				id = reader.nextInt();
				System.out.println("Nuevo valor de ataque:");
				ataque = reader.nextInt();
				personajes.get(id - 1).setAtaque(ataque);
				session.update(personajes.get(id - 1));
				System.out.println("\nPersonaje id " + id + " modificado");
				session.getTransaction().commit();
				break;
			case 3:
				session.beginTransaction();
				System.out.println("\nID del personaje a borrar:");
				id = reader.nextInt();
				session.remove(personajes.get(id - 1));
				personajes.remove(id - 1);
				System.out.println("\nPersonaje id " + id + " eliminado");
				session.getTransaction().commit();
				break;
			case 4:

				break;

			case 5:
				System.out.println("\nBye!");
				break;

			default:
				System.out.println("\nOpcion invalida");
				break;
			}
		} while (opcion != 5);

		HibernateUtil.getSessionFactory().close();
	}

}
