package model;

public enum DeliveryStatuses {
	ACCEPTED("Accepted"), INPROCCESS("In Proccess"), SHIPPING("Shipping"), DELIVERED("Delivered"), OTHER("Other");

	private String statusString;

	DeliveryStatuses(String statusString) {
		this.statusString = statusString;
	}

	public String toString() {
		return statusString;
	}
}
