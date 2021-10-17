package threads;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Coet {

	final String id;
	final List<Propulsor> propulsors;
	int velocitatInicial, velocitat = 50;

	Coet(String id, List<Propulsor> propulsors) throws Exception {
		if (id.length() != 8)
			throw new Exception("Error: identificador de coet no vàlid");
		this.id = id;
		this.propulsors = propulsors;
		setCoet(this.propulsors);		
	}	
	
	synchronized void calculateVelocity() {
		int sumPotencies = 0;
		for(Propulsor p: propulsors)
			sumPotencies += p.currentPower;
		this.velocitatInicial = velocitatInicial + (int)Math.sqrt(sumPotencies);
		if(this.velocitatInicial > this.velocitat)
			this.velocitatInicial = this.velocitat;
		printVelocityStatus();
	}	
	
	synchronized void printVelocityStatus() {
		String message = "Velocitat coet";

		if (this.velocitatInicial >= this.velocitat) {
			message = "VELOCITAT OBJECTIU ACONSEGUIDA";
		}

		StringBuffer sf = new StringBuffer("Coet " + this.id + " - ");
		sf.append(message + ":\t")
			.append(this.velocitatInicial + " ").append(this.velocitat);
		System.out.println(sf.toString());
	}
	
	//Nivell 2
	public void accelerar2() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				accelerarPropulsors2();
			}

		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void accelerarPropulsors2() {
		for (Propulsor p : propulsors) {
			try {
				p.accelerar2();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		synchronized (this) {
			this.notifyAll(); //notifica quan ha acabat
		}
	}
	
	public boolean accelerar2Executor(int velocitat) {
		this.velocitat = velocitat;
		boolean finished = false;
		ExecutorService es = Executors.newCachedThreadPool();
		try {
			for (Propulsor p : propulsors)
				es.execute(p.accelerar2Executor());
			es.shutdown();
			finished = es.awaitTermination(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(finished) {
			if(velocitatInicial < velocitat) {
				System.out.println("WARNING: No hi ha prou potència per arribar a la velocitat de " 
						+ velocitat);
			}
		}
		return finished;
	}
	
	//Nivell 1
	public void accelerar() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				accelerarPropulsors();
			}

		});
		t.start();
	}

	//Espera a acabar tots els subthreads de cada propulsor
	public boolean accelerarExecutor() {
		boolean finished = false;
		ExecutorService es = Executors.newCachedThreadPool();
		try {
			for (Propulsor p : propulsors)
				es.execute(p.accelerarExecutor());
			es.shutdown();
			finished = es.awaitTermination(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finished;
	}

	public void frenar() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				frenarPropulsors();
			}

		});
		t.start();
	}

	private void accelerarPropulsors() {
		for (Propulsor p : propulsors) {
			try {
				p.accelerar();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		synchronized (this) {
			this.notifyAll(); //notifica quan ha acabat
		}
	}

	private void frenarPropulsors() {
		synchronized (this) {
//			try {
//				this.wait(); //espera a rebre notificació
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			for (Propulsor p : propulsors) {
				try {
					p.frenar();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public void setTargetPower(int power) {
		for (Propulsor p : propulsors) {
			p.setTargetPower(power);
		}
	}

	// Helper
	private void setCoet(List<Propulsor> propulsors) {
		for (Propulsor p : propulsors) {
			p.setCoet(this);
		}
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("[");
		int counter = 0;
		for (Propulsor p : propulsors) {
			if (counter < propulsors.size() - 1)
				sb.append(p.getMaxPower() + " ");
			else
				sb.append(p.getMaxPower());
			counter++;
		}
		sb.append("]");

		return "Rocket id=" + this.id + "\n" + "Potència màxima propulsors: " + sb.toString();
	}
}
