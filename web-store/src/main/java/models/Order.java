package models;


public class Order {
	private int order_id;
	private String name;
	private String phone;
	private int sum;
	private String state;
	
    public Order(int order_id, String name,String phone,int sum,String state) {
    	this.order_id = order_id;
	    this.name = name;
	    this.phone = phone;
	    this.sum = sum;
	    this.state = state;
	}
    public int getOrder_id() {
		return order_id;
	}
    
    public String getName() {
		return name;
	}
    public String getPhone() {
		return phone;
	}
	public int getSum() {
		return sum;
	}
	public String getState() {
		return state;
	}

}
