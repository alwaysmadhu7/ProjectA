package com.practice.strategy;

public class CashOnDelivery implements MakePayment {

	@Override
	public void chooseYourPaymentType() {
		System.out.println("Payment done by-CashOnDelivery");
	}

}
