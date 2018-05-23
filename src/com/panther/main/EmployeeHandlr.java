package com.panther.main;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import com.panther.xmlhandlr.xjcoutput.EmployeeList;
import com.panther.xmlhandlr.xjcoutput.EmployeeType;
import com.panther.xmlhandlr.xjcoutput.ProjectType;

public class EmployeeHandlr {
	private List<EmployeeType> empList;
	private File fileHandlr;
	private EmployeeHandlr() {
		super();
	}
	public EmployeeHandlr(String sXmlFile) throws Exception{
		super();
		try {
			if(sXmlFile == null || sXmlFile.length() <=0) {
				String msg = "File string is null/empty";
				System.out.println(msg);
				throw new Exception(msg);
			}
			fileHandlr = new File(sXmlFile);
			if(fileHandlr == null) {
				String msg = "Not able to open file "+sXmlFile;
				System.out.println(msg);
				throw new Exception(msg);
			}
			empList = readXml();
			if(empList.size() == 0){
				System.out.println("XML File doesn't contain any employee einformation");
			}
		} catch (Exception e) {
			System.out.println("Exception while creating EmployeeHandlr Object");
			e.printStackTrace();
			throw e;
		}
	}
	
	private List<EmployeeType> readXml() throws Exception{
		try {
			JAXBContext jContext = JAXBContext.newInstance("com.panther.xmlhandlr.xjcoutput");
			JAXBElement<EmployeeList> jEle = (JAXBElement<EmployeeList>) jContext.createUnmarshaller().unmarshal(fileHandlr);
			if(jEle == null) {
				String msg = "Invalid XML File";
				System.out.println(msg);
				throw new Exception(msg);
			}
			EmployeeList employeeList = jEle.getValue();
			if(employeeList == null) {
				String msg = "Invalid XML File";
				System.out.println(msg);
				throw new Exception(msg);
			}
			return employeeList.getEmployee();
		} catch (Exception e) {
			System.out.println("Exception while reading XML file");
			e.printStackTrace();
			throw e;
		}
	}
	
	public EmployeeType get(int index) throws Exception{
		try {
			if(index < 0 || index >= empList.size()) {
				String msg = "Out of Boundary";
				System.out.println(msg);
				throw new Exception(msg);
			}
			return empList.get(index);
		} catch (Exception e) {
			System.out.println("Exception while reading data");
			e.printStackTrace();
			throw e;
		}
	}
	
	public void printAll() throws Exception{
		try {
			for (int i = 0; i < empList.size(); i++) {
				printAt(i);
			}
		} catch (Exception e) {
			System.out.println("Exception while printing data");
			e.printStackTrace();
			throw e;
		}
	}
	
	public void printAt(int index) throws Exception{
		try {
			if(index < 0 || index >= empList.size()) {
				String msg = "Out of Boundary";
				System.out.println(msg);
				throw new Exception(msg);
			}
			EmployeeType employee = empList.get(index);
			
			if(employee == null) {
				return;
			}
			
			if(employee.getEmpId() != null)
				System.out.println("Employee ID : " + employee.getEmpId());
			if(employee.getFirstName() != null)
				System.out.println("First Name : " + employee.getFirstName());
			if(employee.getMiddleName() != null)
				System.out.println("Middle Name : " + employee.getMiddleName());
			if(employee.getLastName() != null)
				System.out.println("Last Name : " + employee.getLastName());
			if(employee.getAddress() != null) {
				System.out.println("Address : " );
				if(employee.getAddress().getStreet() != null)
					System.out.print("\t"+employee.getAddress().getStreet()+", ");
				if(employee.getAddress().getStreetL2() != null)
					System.out.print(employee.getAddress().getStreetL2()+"\n");
				else
					System.out.print("\n");
				if(employee.getAddress().getCity() != null)
					System.out.print("\t"+employee.getAddress().getCity()+", ");
				if(employee.getAddress().getState() != null)
					System.out.print(employee.getAddress().getState()+"\n");
				else
					System.out.print("\n");
				if(employee.getAddress().getCountry() != null)
					System.out.print("\t"+employee.getAddress().getCountry()+", ");
				if(employee.getAddress().getPostalcode() != null)
					System.out.print(employee.getAddress().getPostalcode()+"\n");
				else
					System.out.print("\n");
			}
			if(employee.getWorkPhone() != null)
				System.out.println("Work Phone : " + employee.getWorkPhone());
			if(employee.getHomePhone() != null)
				System.out.println("Home Phone : " + employee.getHomePhone());
			if(employee.getMobile() != null)
				System.out.println("Mobile : " + employee.getMobile());
			if(employee.getMobile2() != null)
				System.out.println("Mobile 2 : " + employee.getMobile2());
			if(employee.getEmail1() != null)
				System.out.println("Email 1 : " + employee.getEmail1());
			if(employee.getEmail2() != null)
				System.out.println("Email 2 : " + employee.getEmail2());
			if(employee.getWorkSite() != null) {
				System.out.println("Cube Details : " );
				if(employee.getWorkSite().getCubeId() != null)
					System.out.println("\tId : " + employee.getWorkSite().getCubeId());
				if(employee.getWorkSite().getCubeMode() != null)
					System.out.println("\tCube Type : " + employee.getWorkSite().getCubeMode());
				if(employee.getWorkSite().getFloorNumber() != null)
					System.out.println("\tFloor # : " + employee.getWorkSite().getFloorNumber());
				if(employee.getWorkSite().getBldName() != null)
					System.out.println("\tBld : " + employee.getWorkSite().getBldName());
				if(employee.getWorkSite().getDeskNumber() != null)
					System.out.println("\tDesk # : " + employee.getWorkSite().getDeskNumber());
			}
			if(employee.getProjects() != null && employee.getProjects().getProject() != null && employee.getProjects().getProject().size() > 0) {
				System.out.println("Projects : " );
				List<ProjectType> prjs = employee.getProjects().getProject();
				for (ProjectType projectType : prjs) {
					if(projectType.getId() != null)
						System.out.println("\tProject : " + projectType.getId());
					if(projectType.getProjCode() != null)
						System.out.println("\t\tProject Code : " + projectType.getProjCode());
					if(projectType.getProjUnit() != null)
						System.out.println("\t\tUnit : " + projectType.getProjUnit());
					if(projectType.getProjManager() != null)
						System.out.println("\t\tManager : " + projectType.getProjManager());
					if(projectType.getProjStartDate() != null)
						System.out.println("\t\tStart Date : " + projectType.getProjStartDate());
					if(projectType.getProjEndDate() != null)
						System.out.println("\t\tEnd Date : " + projectType.getProjEndDate());
					else
						System.out.println("\t\tEnd Date : current");
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Exception while printing data");
			e.printStackTrace();
			throw e;
		}
	}
	
}
