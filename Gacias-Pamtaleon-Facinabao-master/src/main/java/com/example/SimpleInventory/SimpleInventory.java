package com.example.SimpleInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SimpleInventory {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SimpleInventory.class, args);
		InventoryMenuService menuService = context.getBean(InventoryMenuService.class);
		menuService.displayMenu();

	}

}
