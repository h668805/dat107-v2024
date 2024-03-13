package no.hvl.dat107;

import java.util.Scanner;


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
		System.out.println(todoDAO.finnAlleTodos());
		
		// b) Hente ut todo med pk=2
		System.out.println(todoDAO.finnTodoMedId(2));
		
		// c.i)   Hente ut SINGLE todo med tekst="Handle mat"
		System.out.println(todoDAO.finnTodoMedTekst("Handle mat"));
		
		// c.ii)  Hente ut SINGLE todo med tekst="Vaske bilen" (som ikke finnes) 
		System.out.println(todoDAO.finnTodoMedTekst("Vaske bilen"));
		
		// c.iii) Hente ut LISTE av todos med tekst="Handle mat" 
		System.out.println(todoDAO.finnTodosMedTekst("Handle mat"));
		
		// c.iv)  Hente ut LISTE av todos med tekst="Vaske bilen" (som ikke finnes)
		System.out.println(todoDAO.finnTodosMedTekst("Vaske bilen"));
		
		// d) Legge til en ny todo med pk=4
		System.out.println(todoDAO.lagreNyTodo(new Todo(4, "Støvsuge")));
		
		// e) Slette todo med pk=4
		System.out.println(todoDAO.slettTodoMedId(4));
		
		// f) Endre tekst på todo med pk=3
		System.out.println(todoDAO.oppdaterTekst(3, "Vaske kjøkken"));
		
		// g) Endre tekst på todo med pk=3, alternativ måte
		System.out.println(todoDAO.oppdaterTodo(new Todo(3, "Kjøkken")));
	}
}