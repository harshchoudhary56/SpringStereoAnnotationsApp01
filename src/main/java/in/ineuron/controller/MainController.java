package in.ineuron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.dto.CustomerDTO;
import in.ineuron.service.ICustomerMgmntService;
import in.ineuron.vo.CustomerVO;

@Component(value = "component")
public class MainController {
	
	static {
		System.out.println("MainController.class file is loading...");
	}
	
	@Autowired
	private ICustomerMgmntService service;
	
	public MainController() {
		System.out.println("MainController :: zero param constructor...");
	}
	
//	public MainController(ICustomerMgmntService service) {
//		this.service = service;
//		System.out.println("MainController :: one param constructor --> " + service.getClass().getName());
//	}


	public String processCustomer(CustomerVO vo) {
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerName(vo.getCustomerName());
		customerDTO.setCustomerAddress(vo.getCustomerAddress());
		customerDTO.setPrincipalAmount(Float.parseFloat(vo.getPrincipalAmount()));
		customerDTO.setRate(Float.parseFloat(vo.getRate()));
		customerDTO.setTime(Float.parseFloat(vo.getTime()));
		
		String result = "";
		try {
			result = service.calculateSimpleInterest(customerDTO);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "MainController [service=" + service + "]";
	}
}
