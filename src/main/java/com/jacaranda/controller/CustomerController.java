package com.jacaranda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Suscription;
import com.jacaranda.entity.Visual;
import com.jacaranda.service.CustomerService;

/**
 * Controller to manage customer related requests
 * @author raul
 */

@RestController
@RequestMapping(path = "/netflix")
public class CustomerController {
		
	@Autowired
	private CustomerService customerService;

	
	// ------------------------------------------------ CRUD CUSTOMER ------------------------------------------------
	
	// ----- GET -----
	
	/**
	 * GET. Método para mostrar el listado de clientes existentes.
	 * 
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/customer")
	public ResponseEntity<?> getCustomers() {		
		return customerService.getCustomers();
	}
	
	
	/**
	 * GET. Método para buscar un cliente indicando id.
	 * 
	 * @param id
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Long id){
		return ResponseEntity.ok(customerService.getOneCustomerById(id));
	}
	
	
	/**
	 * GET. Método para buscar un cliente indicando nombre y apellido.
	 * 
	 * @param name
	 * @param surname
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/customer/{name}/{surname}")
	public ResponseEntity<?> getCustomerByNameAndSurname(@PathVariable String name, @PathVariable String surname){
		return ResponseEntity.ok(customerService.getOneCustomerByNameAndSurname(name, surname));
	}
	
	
	/**
	 * GET. Método para buscar clientes indicando la ciudad.
	 * 
	 * @param city
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/customer/city/{city}")
	public ResponseEntity<?> getCustomerByCity(@PathVariable String city){
		return customerService.getCustomersByCity(city);
	}
	
	
	/**
	 * GET. Método para mostrar clientes, ordenados por DNI.
	 * 
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/customer/dni")
	public ResponseEntity<?> getCustomerByDni(){
		return customerService.getCustomersByDni();
	}
	
	
	
	// ----- POST -----	
	
	/**
	 * POST. Método para crear cliente.
	 * 
	 * @param customer
	 * @return
	 */
	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
		
	
	// ----- PUT -----
	
	/** <---- CREAR MÉTODO PUT ----> */


	
	// ----- DELETE -----	

	/**
	 * DELETE. Método para eliminar cliente indicando id.
	 * 
	 * @param id
	 */
	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable Long id){
		customerService.deleteCustomer(id);
	}

	
	// ------------------------------------------------ CRUD CUSTOMER ------------------------------------------------
	
	
	
	// ------------------------------------------------- CRUD VISUAL -------------------------------------------------
	
	// ----- GET -----	

	/**
	 * GET. Mostrar todas las visualizaciones del sistema
	 * 
	 * @return
	 */
	@GetMapping("/visual")
	public ResponseEntity<?> getVisuals() {
		return customerService.getVisuals();
	}
		
	
	/**
	 * GET. Mostrar las visualizaciones de un cliente concreto
	 * 
	 * @param idCustomer
	 * @return
	 */
	@GetMapping("/visual/c{idCustomer}")
	public ResponseEntity<?> getCustomerVisuals(@PathVariable Long idCustomer) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerVisuals(idCustomer));		
	}
	
	
	
	// ----- POST -----	
	
	/**
	 * POST. Inserta una visualización a un cliente
	 * 
	 * @param newVisual Visual
	 * @param idCustomer Long
	 * @param idProduct Long
	 * @return
	 */
	@PostMapping("/visual/c{idCustomer}/p{idProduct}")
	public ResponseEntity<?> addVisual(@RequestBody Visual newVisual, @PathVariable Long idCustomer, @PathVariable Long idProduct) {		
		
		return ResponseEntity.status(HttpStatus.OK).body(customerService.addVisual(newVisual, idCustomer, idProduct));		
	}
	
	
	
	// ----- PUT -----
	
	/** <---- CREAR MÉTODO PUT ----> */

	
	
	// ----- DELETE -----	

	/**
	 * DELETE. Método para eliminar visualización indicando id.
	 * 
	 * @param id
	 */
	@DeleteMapping("/visual/c{idCustomer}/v{idVisual}")
	public void deleteVisual(@PathVariable Long idCustomer, @PathVariable Long idVisual){
		customerService.deleteVisual(idCustomer, idVisual);
	}
	// ------------------------------------------------- CRUD VISUAL -------------------------------------------------

	
	// ----------------------------------------------- CRUD SUSCRIPTION ----------------------------------------------

	// ----- GET -----
	
	/**
	 * GET. Mostrar todas las suscripciones del sistema
	 * 
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/suscription")
	public ResponseEntity<?> getSuscriptions() {
		return customerService.getSuscriptions();
	}
		
	
	/**
	 * GET. Mostrar las suscripciones de un cliente concreto
	 * 
	 * @param idCustomer
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/suscription/c{idCustomer}")
	public ResponseEntity<?> getCustomerSuscription(@PathVariable Long idCustomer) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerSuscription(idCustomer));
	}

	
	
	// ----- POST -----	
	
	/**
	 * POST. Inserta una suscripción a un cliente
	 * 
	 * @param newSuscription
	 * @param idCustomer
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/suscription/c{idCustomer}")
	public ResponseEntity<?> addSuscription(@RequestBody Suscription newSuscription, @PathVariable Long idCustomer) {
		
		ResponseEntity<?> response = null;
		Customer resultado = customerService.addSuscription(newSuscription, idCustomer);
		
		if (resultado == null) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR. No se puede asignar otra suscripción al cliente");
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(resultado);
		}
		
		return response;
	}
	
	
	
	// ----- PUT -----
	
	/** <---- CREAR MÉTODO PUT ----> */

	
	
	// ----- DELETE -----
	/**
	 * DELETE. Método para eliminar suscripción indicando id.
	 * 
	 * @param id
	 */
	@DeleteMapping("/suscription/c{idCustomer}")
	public void deleteSuscription(@PathVariable Long idCustomer){
		customerService.deleteSuscription(idCustomer);
	}

	
	// ----------------------------------------------- CRUD SUSCRIPTION ----------------------------------------------
	
}