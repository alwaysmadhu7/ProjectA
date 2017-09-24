package com.me.pack;

public class A {
	ARESP m1(AREQ areq) {
		ARESP aresp=new ARESP();
		
		BRESP bresp=new BRESP();
		
		BREQ breq=new BREQ();
		breq.setId(areq.getId());
		breq.setName(areq.getName());
		
		B b=new B();
		bresp=b.m2(breq);
		
		aresp.setDesc(bresp.getDesc());
		
		System.out.println("This is added after intial commit");
		
		return aresp;
	}
}
