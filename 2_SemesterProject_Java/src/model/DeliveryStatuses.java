package model;

public enum DeliveryStatuses {
	ACCEPTED("Accepted", 0), INPROCCESS("In Proccess", 1), SHIPPING("Shipping", 2), DELIVERED("Delivered", 3),
	OTHER("Other", 4);

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
			return DeliveryStatuses.ACCEPTED;
		case 1:
			return DeliveryStatuses.INPROCCESS;
		case 2:
			return DeliveryStatuses.SHIPPING;
		case 3:
			return DeliveryStatuses.DELIVERED;
		default:
			return DeliveryStatuses.OTHER;
		}
	}

	public String toString() {
		return statusString;
	}
}
