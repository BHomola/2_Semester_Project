package model;

import java.util.Date;

public class Invoice {
	private int id;
	private Date paymentDate;
	private double VATratio;
	private double finalPrice;

	public Invoice(int id, Date paymentDate, double vATratio, double finalPrice) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		VATratio = vATratio;
		this.finalPrice = finalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getVATratio() {
		return VATratio;
	}

	public void setVATratio(double vATratio) {
		VATratio = vATratio;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", paymentDate=" + paymentDate + ", VATratio=" + VATratio + ", finalPrice="
				+ finalPrice + "]";
	}

}
