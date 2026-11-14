package com.lld.Practice.VendingMachine;

import com.lld.Practice.VendingMachine.PaymentStrategies.Cash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private static Map<String, Integer> takeOrder(Scanner scanner) {
        System.out.println("Enter product code:");
        Map<String, Integer> newProducts = new HashMap<>();
        String newCode = scanner.next();
        System.out.println("Enter the quantity:");
        int newQuantity = scanner.nextInt();
        newProducts.put(newCode, newQuantity);
        return newProducts;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        inventory.addProduct(new Product("C01", "Coke", 40, 10));
        inventory.addProduct(new Product("C02", "Chips", 20, 5));

        VendingMachine vm = new MultiVendingMachine(inventory);
        vm.showAvailableProducts();

        PaymentMethod pm = new Cash();
        vm.processMoney(pm);
        while (vm.isSessionActive()) {

            System.out.println("1. Select product");
            System.out.println("2. Return money");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    Map<String, Integer> order = takeOrder(scanner);

                    SelectionStatus status = vm.placeOrder(order);

                    switch (status) {
                        case SUCCESS ->
                                System.out.println("Purchase successful");

                        case OUT_OF_STOCK ->
                                System.out.println("Product out of stock");

                        case INVALID_PRODUCT ->
                                System.out.println("Invalid product");

                        case INSUFFICIENT_FUNDS ->
                                System.out.println("Insufficient balance");
                    }
                    break;

                case 2:
                    int refund = vm.returnMoney();
                    System.out.println("Returned: ₹" + refund);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
