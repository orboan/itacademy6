package threads;

public class Accelerate2Thread extends Thread {
	
	private final String LINE = "---------------";

	
	final Propulsor p;

	Accelerate2Thread(Propulsor p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (p.currentPower < p.targetPower
				&& p.coet.velocitatInicial < p.coet.velocitat) {
			try {
				p.setCurrentPower(p.currentPower + 1);
				p.coet.calculateVelocity();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printStatus();
		}
	}

	// Print feedback message
	void printStatus() {
		String message = " accelerant";

		if (p.currentPower == p.targetPower) {
			message = " POTÈNCIA EXHAURIDA ";
		}

		StringBuffer sf = new StringBuffer(LINE + "\tPropulsor " + p.coet.id + "-");
		sf.append(p.number).append(message + ": ")			
			.append(p.currentPower + " ").append(p.targetPower + " ")
			.append(p.getMaxPower());
		System.out.println(sf.toString());
	}
}
