package model;

public class Orden {
	
	private String order;
	private String type;
	private String time;
	private String ipFrom;
	private String portFrom;
	private boolean ready;

	public String getIpFrom() {
		return ipFrom;
	}

	public void setIpFrom(String ipFrom) {
		this.ipFrom = ipFrom;
	}

	public String getPortFrom() {
		return portFrom;
	}

	public void setPortFrom(String portFrom) {
		this.portFrom = portFrom;
	}

	
	
	public Orden(String time, String type) {
		this.time = time;
		this.type =  type;
	}
	
	public Orden() {
		// TODO Auto-generated constructor stub
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOrder() {
		return order;
	}
	public String getType() {
		return type;
	}
	public boolean getReady() {
		return ready;
	}
	public String getTime() {
		return time;
	}
}
