package demos.metier;
import java.util.*;

public class EvaluateurNiveau {
	
	public static final int MIN_COURS = 0;
	public static final int MIN_EXAM = 0;
	public static final int MAX_COURS = 25;
	public static final int MAX_EXAM = 75;
	
	public static final int LIMITE_DC = 30;
	public static final int LIMITE_CB = 50;
	public static final int LIMITE_BA = 70;
	
	public static final String NIVEAU_A = "A";
	public static final String NIVEAU_B = "B";
	public static final String NIVEAU_C = "C";
	public static final String NIVEAU_D = "D";
	
	// Fonctions utilitaires
	private static boolean estEntier( String valeur ) {
		try {
			Integer.valueOf(valeur);
		}
		catch( NumberFormatException e ) {
			return false;
		}
		return true;
	}
	
	private static boolean estDansBorne( int valeur , int min , int max ) {
		 return ( valeur >= min && valeur <= max );
	}

	/**
	 * Permet d'évaluer le niveau d'un étudiant connaissant ses notes de cours et d'examen
	 * 
	 * @param cours : une chaine représentant un entier entre MIN_COURS et MAX_COURS
	 * @param examen : une chaine représentant un entier entre MIN_EXAM et MAX_EXAM
	 * @return une chaine représentant le niveau de l'étudiant parmi "A","B","C" et "D"
	 * @throws ExceptionValeurNonEntiere si cours ou examen n'est pas un entier
	 * @throws ExceptionValeurHorsBorne si cours et examen ne sont pas dans leurs intervalles
	 */
	public static String evaluerNiveau(String cours, String examen) throws ExceptionValeurNonEntiere, ExceptionValeurHorsBorne {
		// TODO Auto-generated method stub
		if ( ! estEntier( cours )) {
			throw new ExceptionValeurNonEntiere("Cours " + cours + " n'est pas un entier");
		}
		if ( ! estEntier( examen )) {
			throw new ExceptionValeurNonEntiere("Examen " + examen + " n'est pas un entier");
		}
		// Les valeurs sont entieres 
		int iCours = Integer.valueOf( cours );
		if ( ! estDansBorne(iCours, MIN_COURS , MAX_COURS )) {
			throw new ExceptionValeurHorsBorne( "Cours " + cours + " n'est pas dans ["+MIN_COURS+".."+MAX_COURS+"]");
		}
		int iExamen = Integer.valueOf( examen );
		if ( ! estDansBorne(iExamen, MIN_EXAM , MAX_EXAM )) {
			throw new ExceptionValeurHorsBorne( "Examen " + examen + " n'est pas dans ["+MIN_EXAM+".."+MAX_EXAM+"]");
		}
		
		int somme = iCours + iExamen;
		 
		if ( somme < LIMITE_DC ) {
			return NIVEAU_D;
		} else if ( somme < LIMITE_CB ) {
			return NIVEAU_C;
		} else if ( somme < LIMITE_BA ) {
			return NIVEAU_B;
		}
		else return NIVEAU_A;
	}
}
