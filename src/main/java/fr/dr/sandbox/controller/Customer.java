package fr.dr.sandbox.controller;

import java.io.Serializable;

/**
 * Customer with id,name and address.
 * @author drieu
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	public String address;
	public String name;
	public String id;

	public Customer() {
	}
	
	public Customer(String id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Print a message.
	 */
	public static void show() {
		System.out.println("Hello Customer!");

	}
}
