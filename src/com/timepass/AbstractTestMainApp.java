package com.timepass;

public class AbstractTestMainApp {

	public static void main(String[] args) {
		//Test 1
		AbstractBase abstractBase = new ConcreteChild();
		abstractBase.display();

	}
}

abstract class AbstractBase{
	
	public abstract void display();
}

class ConcreteChild extends AbstractBase{
	private String thulla = "Saad";

	@Override
	public void display() {
		System.out.println(thulla);
	}
}