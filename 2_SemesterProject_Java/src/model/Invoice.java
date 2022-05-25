package model;

import java.util.Date;

public class Invoice {
	private Date paymentDate;
	private double VATratio;
	private double finalPrice;
//	private double discount;

//	TODO: DISCOUNT
	public Invoice(Date paymentDate, double VATratio, double finalPrice) {
		super();
		//this.id = id;
		this.paymentDate = paymentDate;
		this.VATratio = VATratio;
		this.finalPrice = finalPrice;
		//this.discount = disocunt;
	}
	
	public Invoice(Date paymentDate, double VATratio) {
		super();
		this.paymentDate = paymentDate;
		this.VATratio = VATratio;
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
		this.finalPrice = finalPrice*VATratio;
	}

	@Override
	public String toString() {
		return "Invoice [paymentDate=" + paymentDate + ", VATratio=" + VATratio + ", finalPrice="
				+ finalPrice + "]";
	}

}
