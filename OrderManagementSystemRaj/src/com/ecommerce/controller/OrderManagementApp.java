

package com.ecommerce.controller;

import java.util.Scanner;
import com.ecommerce.dao.OrderDAO;

public class OrderManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderDAO dao = new OrderDAO();

        int choice;

        do {
            System.out.println("\n===== ORDER MANAGEMENT SYSTEM =====");
            System.out.println("1. Insert Sample Orders");
            System.out.println("2. Display Orders by Status");
            System.out.println("3. Increase Order Quantity");
            System.out.println("4. Update Order Status");
            System.out.println("5. Display Total Value and Delete Cancelled Order");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    dao.insertSampleOrders();
                    break;

                case 2:
                    System.out.print("Enter Order Status: ");
                    String status = sc.nextLine();
                    dao.displayOrdersByStatus(status);
                    break;

                case 3:
                    System.out.print("Enter Order ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Additional Quantity: ");
                    int quantity = sc.nextInt();

                    dao.increaseQuantity(id, quantity);
                    break;

                case 4:
                    System.out.print("Enter Order ID: ");
                    int orderId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Status: ");
                    String newStatus = sc.nextLine();

                    dao.updateOrderStatus(orderId, newStatus);
                    break;

                case 5:
                    dao.displayTotalOrderValue();

                    System.out.print("Enter Order ID to delete: ");
                    int deleteId = sc.nextInt();

                    dao.deleteCancelledOrder(deleteId);
                    break;

                case 0:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
