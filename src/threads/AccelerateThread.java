package threads;

public class AccelerateThread extends Thread {
	final Propulsor p;

	AccelerateThread(Propulsor p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (p.currentPower < p.targetPower) {
			try {
				p.setCurrentPower(p.currentPower + 1);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printPowerStatus();
		}
	}

	// Print feedback message
	void printPowerStatus() {
		String message = " accelerant";

		if (p.currentPower == p.targetPower) {
			message = " potència objectiu aconseguida";
		}

		StringBuffer sf = new StringBuffer(p.coet.id + "-");
		sf.append(p.number).append(message + ": ").append(p.currentPower + " ").append(p.targetPower + " ")
				.append(p.getMaxPower());
		System.out.println(sf.toString());
	}
	
}
