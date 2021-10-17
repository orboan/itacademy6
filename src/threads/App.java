package threads;

import java.util.ArrayList;
import java.util.List;

public class App {
	
	private static App app = getInstance();
	private App() {}
	public static App getInstance() {
		if(app == null)
			app = new App();
		return app;
	}

	public static void main(String[] args) {
		
		app.run();
		
	}
	
	private void run() {
		Thread t = Thread.currentThread();
		
		List<Propulsor> propulsors1 = new ArrayList<>();
		fillListOfPropulsors(propulsors1, 
				new int[] {10,30,80}); //max power
		Coet coet1;		
		
		
		List<Propulsor> propulsors2 = new ArrayList<>();
		fillListOfPropulsors(propulsors2, 
				new int[] {30,40,50,50,30,10}); //max power
		Coet coet2;
		
		try {
			coet1 = new Coet("32WESSDS", propulsors1);
			coet1.setTargetPower(20);
			coet2 = new Coet("LDSFJA32", propulsors2);
			coet2.setTargetPower(20);
						
			System.out.println("");
			System.out.println(coet1.toString());			
			System.out.println("");
			System.out.println(coet2.toString());
			System.out.println("");
			
			
			//***** NIVELL 1 ******//
			
			//Els dos coets acceleren amb cada
			//propulsor accelerant en el seu propi thread
//			accelerarDosCoets(coet1, coet2);
			
			
			//El thread que arribi primer a objectiu, guanya
			//Objectiu d'accelerar: arribar a potència objectiu
			//Objectiu de frenar: arribar a potència 0
//			accelerarAndFrenarCompetingThreadsInCoet(coet1);
			
			//Quan el coet ha acabat d'accelerar (tots els propulsors)
			//llavors pot començar a frenar (no abans)
//			accelerarAndFrenarSerieCoet(coet1);		
			
			//***** NIVELL 2 ******//
			//Li passem la velocitat objectiu a assolir
			coet1.accelerar2Executor(150); //S'assoleix la velocitat
			coet1.accelerar2Executor(300); //No hi ha prou potència
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	private void accelerarDosCoets(Coet coet1, Coet coet2) {
		coet1.accelerar();
		coet2.accelerar();
	}
	
	private void accelerarAndFrenarCompetingThreadsInCoet(Coet coet){
		//El thread que arribi primer a objectiu, guanya
		//Objectiu d'accelerar: arribar a potència objectiu
		//Objectiu de frenar: arribar a potència 0
		coet.accelerar();
		coet.frenar();
	}
	
	private void accelerarAndFrenarSerieCoet(Coet coet){
		//Quan el coet ha acabat d'accelerar (tots els propulsors)
		//llavors pot començar a frenar (no abans)
		if(coet.accelerarExecutor())
			coet.frenar();
	}
	
	private void fillListOfPropulsors(List<Propulsor> propulsors,
			int[] maxPowers) {
		for(int i = 0; i < maxPowers.length; i++) {
			propulsors.add(new Propulsor(maxPowers[i],i+1));
		}		
	}

}
