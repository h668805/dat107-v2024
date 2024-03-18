package no.hvl.dat107;

public class Main {

	//-------------------------------------------------------------------------
	
	/*
	 *  Hjelpeklasse for å snakke med databasen.
	 *  DAO er en forkortelse for Data Access Object,
	 *  og er et vanlig navn på slike.
	 */
	private static TodoDAO todoDAO = new TodoDAO();
	
	public static void main(String[] args) {

		/* 
		 * Databasen i utgangspunktet:
		 * 	1, 'Handle mat'
		 * 	2, 'Vaske opp'
		 *  3, 'Ta ut bosset'
		 */

		// a) Hente ut alle todos
		System.out.println(todoDAO.finnAlleTodos() + "\n");
		
		// b) Hente ut todo med pk=2
		System.out.println("id = 2: " + todoDAO.finnTodoMedId(2) + "\n");
		
		// c.i)   Hente ut SINGLE todo med tekst="Handle mat"
		System.out.println("tekst = Handle mat: " + todoDAO.finnTodoMedTekst("Handle mat") + "\n");
		
		// c.ii)  Hente ut SINGLE todo med tekst="Vaske bilen" (som ikke finnes) 
		System.out.println("tekst = Vaske bilen: " + todoDAO.finnTodoMedTekst("Vaske bilen") + "\n");
		
		// c.iii) Hente ut LISTE av todos med tekst="Handle mat" 
		System.out.println("tekst = Handle mat: " + todoDAO.finnTodosMedTekst("Handle mat") + "\n");
		
		// c.iv)  Hente ut LISTE av todos med tekst="Vaske bilen" (som ikke finnes)
		System.out.println("tekst = Vaske bilen: " + todoDAO.finnTodosMedTekst("Vaske bilen") + "\n");
		
		// d) Legge til en ny todo med pk=4
		System.out.println("Lagre ny Todo: " + todoDAO.lagreNyTodo(new Todo(4, "Støvsuge")) + "\n");
		
		// e) Slette todo med pk=4
		System.out.println("Slett Todo: " + todoDAO.slettTodoMedId(4) + "\n");
		
		// f) Endre tekst på todo med pk=3
		System.out.println("Oppdater tekst: " + todoDAO.oppdaterTekst(3, "Vaske kjøkken") + "\n");
		
		// g) Endre tekst på todo med pk=3, alternativ måte
		System.out.println("Endre tekst: " + todoDAO.oppdaterTodo(new Todo(3, "Kjøkken")) + "\n");
	}
}