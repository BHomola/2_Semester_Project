package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OrderInfo {
	private int id;
	private Customer customer;
	private List<StoneProduct> products;
	private double orderPrice;
	private Employee employee;
	private Location office;
	private Invoice invoice;
	private DeliveryStatuses deliveryStatus;
	private Date deliveryDate;
	private String address;
	private City city;
	private double deposit;
	private boolean isPaid;
	private String customerNote;
	private String updates;

	public OrderInfo(int id, Customer customer, double orderPrice, Employee employee, Location office, Invoice invoice,
			DeliveryStatuses deliveryStatus, Date deliveryDate, String address, City city, double deposit,
			boolean isPaid, String customerNote) {
		super();
		this.id = id;
		this.customer = customer;
		this.orderPrice = orderPrice;
		this.employee = employee;
		this.office = office;
		this.invoice = invoice;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.address = address;
		this.city = city;
		this.deposit = deposit;
		this.isPaid = isPaid;
		this.customerNote = customerNote;
	}

	public OrderInfo(Customer customer, Employee employee, Location office, String address, City city,
			String customerNote) {
		super();
		this.id = (int) Math.floor(Math.random() * (101));
		this.customer = customer;
		this.products = null;
		this.orderPrice = 0;
		this.employee = employee;
		this.office = office;
		this.invoice = null;
		this.deliveryStatus = DeliveryStatuses.RECEIVED;
		this.deliveryDate = null;
		this.address = address;
		this.city = city;
		this.deposit = 0;
		this.isPaid = false;
		this.customerNote = customerNote;
		this.updates = null;
	}

	// TODO SETTERS IF CHANGE -> PUT NOTE INTO UPDATES

	private void updateOrderPrice() {
		orderPrice = 0;
		if (products != null)
			for (StoneProduct product : products)
				orderPrice += product.getPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<StoneProduct> getProducts() {
		return products;
	}

	public void setProducts(List<StoneProduct> products) {
		this.products = products;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Location getOffice() {
		return office;
	}

	public void setOffice(Location office) {
		this.office = office;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {

		this.invoice = invoice;
	}

	public DeliveryStatuses getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatuses deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
		updateOrderPrice();
	}

	public void addUpdate(String note) {
		this.updates += LocalDate.now() + ": " + note + "\n";
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", orderLines=" + products + ", orderPrice=" + orderPrice
				+ ", employee=" + employee + ", office=" + office + ", invoice=" + invoice + ", deliveryStatus="
				+ deliveryStatus + ", deliveryDate=" + deliveryDate + ", address=" + address + ", city=" + city
				+ ", deposit=" + deposit + ", isPaid=" + isPaid + ", customerNote=" + customerNote + ", updates="
				+ updates + "]";
	}
}
