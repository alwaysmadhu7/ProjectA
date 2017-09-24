package com.practice.strategy;

public class Test {
	public static void main(String[] args) {

		// create your own/likable strategy payment type,Iam choosing Mobile
		MakePayment makePayment = new MobilePayment();

		// create a conext object
		PaymentContext paymentContext = new PaymentContext(makePayment);
		
		//do the actual paymet
		paymentContext.chooseYourPaymentTypeMethod();
		
	}
}
