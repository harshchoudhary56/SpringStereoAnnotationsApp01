package in.ineuron.test;

import java.util.Scanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import in.ineuron.controller.MainController;
import in.ineuron.vo.CustomerVO;

public class TestApp {
	

	public static void main(String[] args) {
		
		// Reading the input
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the customerName:: ");
		String customerName = scanner.next();
		
		System.out.print("Enter the customerAddress:: ");
		String customerAddress = scanner.next();
		
		System.out.print("Enter the principalAmount:: ");
		String principalAmount = scanner.next();
		
		System.out.print("Enter the  RateOfInterest:: ");
		String rate = scanner.next();
		
		System.out.print("Enter the TimePeriod:: ");
		String time = scanner.next();
		
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerName(customerName);
		customerVO.setCustomerAddress(customerAddress);
		customerVO.setPrincipalAmount(principalAmount);
		customerVO.setRate(rate);
		customerVO.setTime(time);
		
		// To read the data from properties file into xml by the container we need to use "ApplicationContext(I)"  IOC container.
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("/in/ineuron/cfg/applicationContext.xml");		
		System.out.println("**********CONTAINER STARTED**********");
		
		MainController controller = factory.getBean(MainController.class);
		String result = controller.processCustomer(customerVO);
		System.out.println(result);
		
		scanner.close();
		factory.close();
		System.out.println("************CONTAINER STOPPED**********");
	}

}
