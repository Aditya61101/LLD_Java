package com.lld.Practice.VendingMachine;

import com.lld.Practice.VendingMachine.PaymentStrategies.Cash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vm = new MultiVendingMachine();
        vm.showAvailableProducts();

        PaymentMethod pm = new Cash();
        vm.processMoney(pm);
        while(vm.isSessionActive()) {
            Map<String, Integer> products = new HashMap<>();

            String code = scanner.next();
            int quantity = scanner.nextInt();
            products.put(code, quantity);

            SelectionStatus result = vm.placeOrder(products);

            if(result == SelectionStatus.SUCCESS) break;

            if(result == SelectionStatus.OUT_OF_STOCK) {
                System.out.println("Product is out of stock.");
            } else if(result == SelectionStatus.INVALID_PRODUCT) {
                System.out.println("Invalid Product.");
            }
            System.out.println("1. Select another product");
            System.out.println("2. Return money");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter product code:");
                    Map<String, Integer> newProducts = new HashMap<>();
                    String newCode = scanner.next();
                    int newQuantity = scanner.nextInt();
                    newProducts.put(newCode, newQuantity);
                    vm.placeOrder(newProducts);
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
