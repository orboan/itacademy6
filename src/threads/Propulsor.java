package threads;

public class Propulsor{

	int number;	
	
	final int maxPower;
	int currentPower, targetPower;
	Coet coet;
	
	public Propulsor(int maxPower, int number) {
		this.maxPower = maxPower;
		this.number = number;
	}	

	public void accelerar2() throws Exception {
		Thread at = new Accelerate2Thread(this);
		at.start();		
	}
	
	public Runnable accelerar2Executor() throws Exception {
		Thread at = new Accelerate2Thread(this);
		return at;	
	}
	
	public Runnable accelerarExecutor() throws Exception {
		Thread at = new AccelerateThread(this);
		return at;	
	}
	
	public void accelerar() throws Exception {
		Thread at = new AccelerateThread(this);
		at.start();		
	}

	void frenar() throws Exception {
		Thread st = new StopThread(this);
		st.start();
	}

	//getter
	public int getMaxPower() {
		return this.maxPower;
	}

	//setters
	void setCurrentPower(int currentPower) throws Exception {
		if (checkPower(currentPower))
			this.currentPower = currentPower;
		else
			throw new Exception("Error: potència no vàlida");
	}

	public void setTargetPower(int targetPower) {
		if (targetPower <= this.maxPower)
			this.targetPower = targetPower;
		else
			this.targetPower = this.maxPower;
	}

	public void setCoet(Coet coet) {
		this.coet = coet;
	}	

	//Helpers
	private boolean checkPower(int power) {
		if (power > this.maxPower || power > this.targetPower)
			return false;
		return true;
	}

}
