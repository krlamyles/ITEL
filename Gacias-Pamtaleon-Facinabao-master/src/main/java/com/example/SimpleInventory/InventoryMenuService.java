package com.example.SimpleInventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.math.BigDecimal;
import java.util.Scanner;

@Service
public class InventoryMenuService {


    private final InventoryService inventoryService;
    private final Scanner scanner;

    @Autowired
    public InventoryMenuService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Display Inventory");
            System.out.println("3. Display Product's Name");
            System.out.println("4. Exit");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
                continue;
            }

            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    inventoryService.getInventory().forEach(System.out::println);
                    break;
                case 3:
                    displayProductByName();
                        break;

                case 4:
                    System.out.println("Exiting program...");


                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }

    public void addItem() {
        String name;
        do {
            System.out.println("Enter product name:");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Product name cannot be blank. Please enter again.");
            }
        } while (name.isEmpty());

        BigDecimal price;
        do {
            System.out.println("Enter product price:");
            try {
                price = scanner.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for price.");
                scanner.nextLine(); // Clear buffer
                price = BigDecimal.ZERO; // Reset price to trigger loop
            }
        } while (price.compareTo(BigDecimal.ZERO) <= 0);

        Long quantity;
        do {
            System.out.println("Enter product quantity:");
            try {
                quantity = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for quantity.");
                scanner.nextLine(); // Clear buffer
                quantity = -1L; // Reset quantity to trigger loop
            }
        } while (quantity < 0);
                                                                  
        scanner.nextLine();


        System.out.println("Categories:");
        for (Category category : Category.values()) {
            System.out.println("- " + category);
        }

        Category category;
        do {
            System.out.println("Enter product category:");
            try {
                category = Category.valueOf(scanner.nextLine().trim().toUpperCase());
                break; // Break the loop if category is valid
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please enter a valid category.");
            }
        } while (true);

        inventoryService.addItemToInventory(name, price, quantity, category);
        System.out.println("Product added to inventory.");

    }

    
    public void displayProductByName() {
        System.out.println("Enter the product's name: ");
        String name = scanner.nextLine();
        Product product = inventoryService.getProductByName(name);
        if(product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found");
        }
    }
}

