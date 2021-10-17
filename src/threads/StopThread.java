package threads;

public class StopThread extends Thread {
	final Propulsor p;

	StopThread(Propulsor p) {
		this.p = p;
	}

	@Override
	public void run() {
		if(p.currentPower == 0)
			System.out.println(p.coet.id + "-" + p.number + ": No es pot frenar, ja estem aturats !");
		while (p.currentPower > 0) {
			try {
				p.setCurrentPower(p.currentPower - 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printPowerStatus();
		}
	}

	// Print feedback message
	void printPowerStatus() {
		String message = " frenant";

		if (p.currentPower == 0) {
			message = " potència zero aconseguida";
		}

		StringBuffer sf = new StringBuffer(p.coet.id + "-");
		sf.append(p.number).append(message + ": ").append(p.currentPower + " ").append(p.targetPower + " ")
				.append(p.getMaxPower());
		System.out.println(sf.toString());
	}

}
