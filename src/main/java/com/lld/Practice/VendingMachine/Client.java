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
        VendingMachine vm = new MultiVendingMachine();
        vm.showAvailableProducts();

        PaymentMethod pm = new Cash();
        vm.processMoney(pm);
        while(vm.isSessionActive()) {
            Map<String, Integer> order = Client.takeOrder(scanner);
            SelectionStatus result = vm.placeOrder(order);

//            if(result == SelectionStatus.SUCCESS)

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
                    Map<String, Integer> newOrder = Client.takeOrder(scanner);
                    vm.placeOrder(newOrder);
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
