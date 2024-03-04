package demos.metier;

public class EvaluateurRunner {

	public static void main(String[] args) throws ExceptionValeurNonEntiere, ExceptionValeurHorsBorne {
		String cours = args[0];
		String examen = args[1];
		String niveau = EvaluateurNiveau.evaluerNiveau(cours, examen);
		System.out.println("Niveau obtenu : " + niveau );
	}

}
