package com.panther.main;

public class MainOut {

	public static void main(String[] args) {
		try {
			EmployeeHandlr empHandlr = new EmployeeHandlr("./resource/Employee.xml");
			empHandlr.printAll();
		} catch (Exception e) {
			System.out.println("Exception caught in main");
			e.printStackTrace();
		}

	}

}
