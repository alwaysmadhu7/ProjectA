package com.practice.strategy;

public class OnlinePayment implements MakePayment {

	@Override
	public void chooseYourPaymentType() {
		System.out.println("Payment done by-Online");
	}

}
