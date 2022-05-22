package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Order {
	private int id;
	private Customer customer;
	private List<OrderLine> orderLines;
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

	public Order(int id, Customer customer, double orderPrice, Employee employee, Location office, Invoice invoice,
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

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
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
	}
	
	public void addUpdate(String note) {
		this.updates += LocalDate.now() + ": " + note + "\n";
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", orderLines=" + orderLines + ", orderPrice="
				+ orderPrice + ", employee=" + employee + ", office=" + office + ", invoice=" + invoice
				+ ", deliveryStatus=" + deliveryStatus + ", deliveryDate=" + deliveryDate + ", address=" + address
				+ ", city=" + city + ", deposit=" + deposit + ", isPaid=" + isPaid + ", customerNote=" + customerNote
				+ ", updates=" + updates + "]";
	}
}
