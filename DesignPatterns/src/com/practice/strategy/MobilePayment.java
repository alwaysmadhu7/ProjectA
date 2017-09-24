package com.practice.strategy;

public class MobilePayment implements MakePayment {

	@Override
	public void chooseYourPaymentType() {
		System.out.println("Payment done by-Mobile");
	}

}
