package com.example.DemoForJpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoForJpa.entity.Customer;
import com.example.DemoForJpa.repo.CustomerRepo;

@RestController
@RequestMapping("/api/customers") // Base path for all endpoints
public class CustomerController {
	@Autowired
	CustomerRepo customerrepo;
	
	
	
	/*public void saveCustomer(@RequestBody Customer customer) {
		System.out.println(customer);*/
	@PostMapping("/save")
	 public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
		return new ResponseEntity<>(customerrepo.save(customer),HttpStatus.CREATED);
	
	}
	@GetMapping(path="/getAllCustomers")
	public ResponseEntity<List<Customer>> getCustomers(){
		return new ResponseEntity<>(customerrepo.findAll(),HttpStatus.OK);
		
	}

	@GetMapping(path="/getCustomerById/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable long id){
		Optional<Customer> customer= customerrepo.findById(id);
		if(customer.isPresent()) {
			return new ResponseEntity<>(customer.get(),HttpStatus.OK);
		}
		else {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}   
		return null;
 	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id,@RequestBody Customer cust){
		Optional<Customer> customer= customerrepo.findById(id);
		if(customer.isPresent()) {
			customer.get().setCustomerName(cust.getCustomerName());
			customer.get().setCustomerEmail(cust.getCustomerEmail());
			customer.get().setCustomerAddress(cust.getCustomerAddress());
			
			return new ResponseEntity<>(customerrepo.save(customer.get()),HttpStatus.OK);
		}
				
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path="/deleteCustomerById/{id}")
	// add a delete method
	public ResponseEntity<Void> deleteCustomer(@PathVariable long id){
		if(customerrepo.existsById(id)) {
			customerrepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
}

