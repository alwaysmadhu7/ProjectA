package com.practice.strategy;

public class PaymentContext {
	private MakePayment makePayment;

	public PaymentContext(MakePayment makePayment) {
		this.makePayment = makePayment;
	}
	
	public void chooseYourPaymentTypeMethod() {
		this.makePayment.chooseYourPaymentType();
	}
}
