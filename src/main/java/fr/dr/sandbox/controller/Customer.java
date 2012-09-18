package fr.dr.sandbox.controller;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	public String address;
	public String name;
	public String id;

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
