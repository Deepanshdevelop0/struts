package strut1;

public class Product {
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String execute() {
		return "Success";
	}
}
