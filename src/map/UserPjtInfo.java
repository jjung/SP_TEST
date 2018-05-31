package map;

public class UserPjtInfo {
	public String getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public double getApjt() {
		return apjt;
	}

	public double getBpjt() {
		return bpjt;
	}

	public double getCpjt() {
		return cpjt;
	}

	private String num;
	private String name;
	private double apjt;
	private double bpjt;
	private double cpjt;
	
	public UserPjtInfo(String num, String name, double apjt, double bpjt, double cpjt) {
		this.num = num;
		this.name = name;
		this.apjt = apjt;
		this.bpjt = bpjt;
		this.cpjt = cpjt;
	}
}
