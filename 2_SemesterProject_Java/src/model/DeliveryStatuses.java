package model;

public enum DeliveryStatuses {
	RECEIVED("Received", 0), ACCEPTED("Accepted", 1), INPROCCESS("In Proccess", 2), SHIPPING("Shipping", 3), DELIVERED("Delivered", 4),
	OTHER("Other", 5);

	private String statusString;
	private int id;

	DeliveryStatuses(String statusString, int id) {
		this.statusString = statusString;
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public static DeliveryStatuses GetStatusByID(int id) {
		switch (id) {
			case 0:
				return DeliveryStatuses.RECEIVED;
			case 1:
				return DeliveryStatuses.ACCEPTED;
			case 2:
				return DeliveryStatuses.INPROCCESS;
			case 3:
				return DeliveryStatuses.SHIPPING;
			case 4:
				return DeliveryStatuses.DELIVERED;
			default:
				return DeliveryStatuses.OTHER;
		}
	}

	public String toString() {
		return statusString;
	}
}
