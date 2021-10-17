package lambdes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {
		App app = new App();
		try {
			//Nivell 3
			System.out.println("\n**** NIVELL 3 ****");
			Alumne[] alumnes = new Alumne[] {
					new Alumne("Laura", 23, "JAVA", 8),
					new Alumne("Jordi", 33, "PHP", 4),
					new Alumne("Anna", 17, "JAVA", 7),
					new Alumne("Josep", 34, "PYTHON", 5),
					new Alumne("Aina", 29, "PHP", 6),
					new Alumne("Oriol", 41, "JAVA", 10),
					new Alumne("Julia", 38, "PYTHON", 7),
					new Alumne("Carles", 26, "JAVA", 3),
					new Alumne("Maria", 19, "PYTHON", 9),
					new Alumne("Albert", 32, "JAVA", 5)
			};
			
			List<Alumne> llista = Arrays.asList(alumnes);
			Predicate<Alumne> predicate;
			
			System.out.println("\n-- Nivell 3, punt 5 --");
			predicate = a -> (a.edat >= 18 && a.curs.equals("JAVA"));			
			List<Alumne> llistaFiltrada1 = 
						app.getFilteredAlumnesList(llista, predicate);
			llistaFiltrada1.forEach(a -> System.out.println(a.nom + 
					" " + a.curs + " - edat: " + a.edat));
			
			System.out.println("\n-- Nivell 3, punt 4 --");
			predicate = a -> (a.nota >= 5 && !a.curs.equals("PHP"));			
			llistaFiltrada1 = 
						app.getFilteredAlumnesList(llista, predicate);
			llistaFiltrada1.forEach(a -> System.out.println(a.nom + 
					" " + a.curs + " - nota: " + a.nota));
			
			System.out.println("\n-- Nivell 3, punt 3 --");
			predicate = a -> a.nota >= 5;			
			llistaFiltrada1 = 
						app.getFilteredAlumnesList(llista, predicate);
			llistaFiltrada1.forEach(a -> System.out.println(a.nom + " - nota: " + a.nota));
			
			System.out.println("\n-- Nivell 3, punt 2 --");
			String letter = "a"; //podem posar "A" i funciona igualment
			String l = letter.toLowerCase();
			predicate = a -> a.nom.startsWith(l) 
					|| a.nom.startsWith(l.toUpperCase());
			
			llistaFiltrada1 = 
						app.getFilteredAlumnesList(llista, predicate);
			llistaFiltrada1.forEach(a -> System.out.println(a.nom));
			
			System.out.println("\n-- Nivell 3, punt 1 --");
			app.printNomEdatAlumnes(llista);			
			
			//Nivell 2
			System.out.println("\n**** NIVELL 2 ****");
			
			// Nivell 2 - Exercici 2 (operar)
			System.out.println("\n-- Nivell 2, exercici 2 --");
			Calc suma = (a,b) -> a + b;
			Calc resta = (a,b) -> a - b;
			Calc multiplicacio = (a,b) -> a * b;
			Calc divisio = (a,b) -> a / b;
			float x = 10.0f, y = 5.0f;
			String numbers = x + " i " + y;
			System.out.println("Suma " + numbers + " = " + app.calcula(x,y,suma));
			System.out.println("Resta " + numbers + " = " + app.calcula(x,y,resta));
			System.out.println("Multiplicacio " + numbers + " = " + app.calcula(x,y,multiplicacio));
			System.out.println("Divisió " + numbers + " = " + app.calcula(x,y,divisio));

			
			// Nivell 2 - Exercici 1 (matriu)
			System.out.println("\n-- Nivell 2, exercici 1 --");
			
			/* 
			 * Crea una matriu de mida "limit x 2" 
			* limit és el segon paràmetre, en aquest exemple 20
			*
			* El primer element de cada "fila" de la matriu és un nom
			* El segon element de cada "fila" de la matriu és 
			* la llargada del nom
			*/
			String[][] matrix = app.buildMatrix("names.txt",20);
			
			System.out.println("\n:: Només elements 100% numèrics ::");
		    app.numericsAndPrint(matrix);
			
			System.out.println("\n:: Canvi dels caràcters “a” a “4” ::");
		    app.mapA4AndPrint(matrix);
			
			System.out.println("\n:: Ordenació per contenir 'e' ::");
			app.sortByCompatatorAndPrint(matrix, Comparator.comparingInt(a -> a.contains("e")? 0 : 1));
			
			System.out.println("\n:: Ordenació alfabètica pel primer caràcter ::");
			app.sortByCompatatorAndPrint(matrix, (a, b) -> a.charAt(0) - b.charAt(0));
			
			System.out.println("\n:: Ordenació per longitud, de més llarga a més curta ::");
			app.sortByCompatatorAndPrint(matrix, (a, b) -> b.length() - a.length());
			
			System.out.println("\n:: Ordenació per longitud, de més curta a més llarga ::");
			app.sortByCompatatorAndPrint(matrix, (a, b) -> a.length() - b.length());

			// Nivell 1 - Fase 3
			System.out.println("\n**** NIVELL 1, FASE 3 ****");
			Cadena r = str -> {
				StringBuilder sb = new StringBuilder(str);
				sb.reverse();
				return sb.toString();
			};
			System.out.println(r.reverse("0123456789"));
			System.out.println(r.reverse("tururut"));

			// Nivell 1 - Fase 2
			System.out.println("\n**** NIVELL 1, FASE 2 ****");
			Pi pi = () -> 3.1416;
			System.out.println("Pi = " + pi.getPiValue());

			// Nivell 1 - Fase 1
			System.out.println("\n**** NIVELL 1, FASE 1 ****");

			String[] mesosArr = { "GENER", "FEBRER", "MARÇ", "ABRIL", "MAIG", "JUNY", "JULIOL", "AGOST", "SETEMBRE",
					"OCTUBRE", "NOVEMBRE", "DESEMBRE" };

			// Sisè exercici
			System.out.println("\n-- Nivell 1, fase 1, exercici 6 --");
			List<String> mesos = Arrays.asList(mesosArr);
			mesos.stream().map(String::toLowerCase).forEach(System.out::println);

			// Cinquè exercici
			System.out.println("\n-- Nivell 1, fase 1, exercici 5 --");
			mesos.stream().forEach(m -> System.out.println(m));

			// Quart exercici
			System.out.println("\n-- Nivell 1, fase 1, exercici 4 --");
			List<String> cadenesAmbOoMes5 = app.containsOoMes5("names.txt", 20);
			for (String s : cadenesAmbOoMes5)
				System.out.println(s);

			// Tercer exercici
			System.out.println("\n-- Nivell 1, fase 1, exercici 3 --");
			List<String> cadenesAmbO = app.containsO("names.txt", 10);
			for (String s : cadenesAmbO)
				System.out.println(s);

			// Segon exercici
			System.out.println("\n-- Nivell 1, fase 1, exercici 2 --");
			System.out.println(app.evenOddInts(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)));

			// Primer exercici
			System.out.println("\n-- Nivell 1, fase 1, exercici 1 --");
			List<String> namesA3char = app.getA3chars("names.txt");
			for (String s : namesA3char)
				System.out.println(s);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// **** NIVELL 3 ****
	
	private List<Alumne> getFilteredAlumnesList(List<Alumne >llista, Predicate<Alumne> p){
		return llista.stream().filter(p).collect(Collectors.toList());
	}
	
	private void printNomEdatAlumnes(List<Alumne> llista) {
		llista.stream().forEach(a -> System.out.println(
				a.nom + " " + a.edat));
	}
	
	
	// **** NIVELL 2 ****
	
	private float calcula(float a, float b, Calc c) {
		return c.operacio(a, b);
	}
	
	private void numericsAndPrint(String[][] matrix) {
		Stream<String> stream = Arrays.stream(matrix).flatMap(x -> Arrays.stream(x));
		stream.filter(s -> isNumeric(s)).forEach(System.out::println);
	}
	
	private void mapA4AndPrint(String[][] matrix) {
		Stream<String> stream = Arrays.stream(matrix).flatMap(x -> Arrays.stream(x));
		stream.map(s -> s.replace("a", "4")).forEach(System.out::println);
	}
	
	private void sortByCompatatorAndPrint(String[][] matrix, Comparator<String> comp) {
		Stream<String> stream = Arrays.stream(matrix).flatMap(x -> Arrays.stream(x));
		stream.sorted(comp).forEach(System.out::println);
	}
	
	/* 
	 * Crea una matriu de mida "limit x 2" 
	* limit és el segon paràmetre
	* 
	* S'extreuen els valors del fitxer names.txt
	*
	* El primer element de cada "fila" de la matriu és un nom
	* El segon element de cada "fila" de la matriu és 
	* la llargada del nom
	*/
	private String[][] buildMatrix(String filename, int limit) throws IOException {
		String[][] matrix = Files.lines(Path.of(filename)).limit(limit)
				.map(s -> new String[] { s, Integer.toString(s.length()) })
				.toArray(size -> new String[size][2]);
		return matrix;
	}

	// **** NIVELL 1 ****
	// Quart exercici
	private List<String> containsOoMes5(String filename, int n) throws IOException {
		List<String> cadenes = (Files.lines(Path.of(filename))).filter(a -> a.contains("o") || a.length() > 5).limit(n)
				.collect(Collectors.toList());
		return cadenes;
	}

	// Tercer exercici
	private List<String> containsO(String filename, int n) throws IOException {
		List<String> cadenes = (Files.lines(Path.of(filename))).filter(a -> a.contains("o")).limit(n)
				.collect(Collectors.toList());
		return cadenes;
	}

	// Segon exercici
	private String evenOddInts(List<Integer> numbers) {
		return numbers.stream().map(n -> {
			String r;
			if (n % 2 == 0)
				r = "e" + String.valueOf(n);
			else
				r = "o" + String.valueOf(n);
			return r;
		}).collect(Collectors.joining(","));
	}

	// Primer exercici
	private List<String> getA3chars(String filename) throws IOException {
		List<String> names = (Files.lines(Path.of(filename))).filter(a -> a.startsWith("A"))
				.filter(s -> (s.length() == 3)).collect(Collectors.toList());
		return names;
	}

	
	//Helpers
	private boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
