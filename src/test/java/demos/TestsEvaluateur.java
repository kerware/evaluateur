package demos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import demos.metier.EvaluateurNiveau;
import demos.metier.ExceptionValeurHorsBorne;
import demos.metier.ExceptionValeurNonEntiere;


@DisplayName("Tests de bon fonctionnement de la classe EvaluateurNiveau")
public class TestsEvaluateur {
	
	static List<String> niveauxPossibles;
	static final int MAX_COURS=25;
	static final int MAX_EXAMEN=75;
	
	@BeforeAll
	public static void preparation() {
		niveauxPossibles = new ArrayList<>();
		niveauxPossibles.add("A");
		niveauxPossibles.add("B");
		niveauxPossibles.add("C");
		niveauxPossibles.add("D");
	}
	
	@Nested
	@DisplayName("Tests nominaux aléatoires")
	public class TestsNominauxAleatoires {	
		
		@RepeatedTest( value=50, name="Test nominal avec cours et examen générés aléatoirement - Test n°{currentRepetition}")
		public void testsAleatoires() 
				throws ExceptionValeurNonEntiere, ExceptionValeurHorsBorne {
			
			// Arrange
			int iCours = (int) Math.round(Math.random()*(MAX_COURS));
			int iExamen = (int) Math.round(Math.random()*(MAX_EXAMEN));
			String cours = Integer.toString(iCours);
			String examen = Integer.toString(iExamen);
		    
			// Act
			String niveauObtenu = EvaluateurNiveau.evaluerNiveau(cours, examen);
		    
			// Assert		
			//Avec hamcrest assertThat( niveauxPossibles , hasItem( niveauObtenu ));
			assertTrue( niveauxPossibles.contains(niveauObtenu));
		}
	}
	
	@Nested
	@DisplayName("Tests des limites de changement de niveau")
	public class TestsLimites {	
		@ParameterizedTest(name="Test avec cours={0} et examen={1} niveau attendu : {2}")
		@CsvFileSource( resources="/limites_evaluateur.csv" , numLinesToSkip=1 )
		public void testLimite( String cours, String examen, String niveauAttendu ) 
				throws ExceptionValeurNonEntiere, ExceptionValeurHorsBorne {
		    // Act
			String niveauObtenu = EvaluateurNiveau.evaluerNiveau(cours, examen);
		    // Assert
			assertEquals( niveauAttendu, niveauObtenu );
		}
	}
	
	@Nested
	@DisplayName("Tests négatifs : valeurs hors bornes et non entières")
	@TestMethodOrder(OrderAnnotation.class)
	public class TestsNegatifs {
		
		@Order(2)
		@ParameterizedTest(name="Test avec cours={0} et examen={1} ")
		@CsvSource( {"xx,30","20.56,30","20,YYYY","20,40.56"} )
		public void testValeurNonEntiere( String cours, String examen ) 
				throws ExceptionValeurNonEntiere, ExceptionValeurHorsBorne {
		    assertThrows( ExceptionValeurNonEntiere.class, 
		    		() -> {EvaluateurNiveau.evaluerNiveau(cours, examen); });
		}
		
		@Order(1)
		@ParameterizedTest(name="Test avec cours={0} et examen={1}")
		@CsvSource( {"-1,30","26,30","20,-1","20,76"} )
		public void testValeurHorsBorne( String cours, String examen ) 
				throws ExceptionValeurNonEntiere, ExceptionValeurHorsBorne {
		    assertThrows( ExceptionValeurHorsBorne.class, 
		    		() -> {EvaluateurNiveau.evaluerNiveau(cours, examen); });
		}
	}
	
	
	
	
	
	
	
}
