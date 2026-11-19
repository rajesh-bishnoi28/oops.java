
    
package com.ecommerce.dao;

import java.sql.*;
import com.ecommerce.model.Order;

public class OrderDAO {


    public void insertSampleOrders() {

        String sql = "INSERT INTO Orders VALUES (?, ?, ?, ?, ?, ?)";

        Order[] orders = {
            new Order(101, "Rajesh", "Laptop", 3, 50000, "Pending"),
            new Order(102, "Aman", "Mobile", 2, 20000, "Shipped"),
            new Order(103, "Rahul", "Keyboard", 5, 1500, "Cancelled")
        };

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (Order order : orders) {

                ps.setInt(1, order.getOrderId());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getProductName());
                ps.setInt(4, order.getQuantity());
                ps.setDouble(5, order.getUnitPrice());
                ps.setString(6, order.getStatus());

                try {
                    ps.executeUpdate();
                    System.out.println("Order inserted successfully!");

                } catch (SQLException e) {

                    if ("23000".equals(e.getSQLState())) {
                        System.out.println("Duplicate Order ID: "
                                + order.getOrderId());
                    } else {
                        System.out.println(e.getMessage());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

// Q1(b): Search orders by status
public void displayOrdersByStatus(String status) {

    String sql = "SELECT * FROM Orders " +
                 "WHERE status = ? AND quantity > 2 " +
                 "ORDER BY unit_price DESC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, status);

        ResultSet rs = ps.executeQuery();

        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.println(
                "Order ID: " + rs.getInt("order_id") +
                " | Customer: " + rs.getString("customer_name") +
                " | Product: " + rs.getString("product_name") +
                " | Quantity: " + rs.getInt("quantity") +
                " | Price: " + rs.getDouble("unit_price") +
                " | Status: " + rs.getString("status")
            );
        }

        if (!found) {
            System.out.println("No matching orders found!");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

// Q1(c): Increase order quantity
public void increaseQuantity(int orderId, int additionalQuantity) {

    String sql = "UPDATE Orders SET quantity = quantity + ? WHERE order_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, additionalQuantity);
        ps.setInt(2, orderId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Quantity updated successfully!");
        } else {
            System.out.println("Order does not exist!");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
public void updateOrderStatus(int orderId, String newStatus) {

    String status = null;

    if (newStatus.equalsIgnoreCase("Pending")) {
        status = "Pending";
    } else if (newStatus.equalsIgnoreCase("Shipped")) {
        status = "Shipped";
    } else if (newStatus.equalsIgnoreCase("Delivered")) {
        status = "Delivered";
    } else {
        System.out.println("Invalid status!");
        return;
    }

    String selectSql = "SELECT * FROM Orders WHERE order_id = ?";
    String updateSql = "UPDATE Orders SET status = ? WHERE order_id = ?";

    try (Connection con = DBConnection.getConnection()) {

        // Check whether order exists
        try (PreparedStatement ps = con.prepareStatement(selectSql)) {
            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Order does not exist!");
                    return;
                }

                System.out.println("Existing Order Details:");
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Customer: " + rs.getString("customer_name"));
                System.out.println("Product: " + rs.getString("product_name"));
                System.out.println("Old Status: " + rs.getString("status"));
            }
        }

        // Update status
        try (PreparedStatement ps = con.prepareStatement(updateSql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }

        // Display updated details
        try (PreparedStatement ps = con.prepareStatement(selectSql)) {
            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nUpdated Order Details:");
                    System.out.println("Order ID: " + rs.getInt("order_id"));
                    System.out.println("Customer: " + rs.getString("customer_name"));
                    System.out.println("Product: " + rs.getString("product_name"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Unit Price: " + rs.getDouble("unit_price"));
                    System.out.println("New Status: " + rs.getString("status"));
                }
            }
        }

    } catch (SQLException e) {
        System.out.println("Database Error: " + e.getMessage());
    }
}
// (e) Display total value of all orders
public void displayTotalOrderValue() {

    String sql = "SELECT SUM(quantity * unit_price) AS total_value FROM Orders";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            System.out.println("Total Order Value: " +
                rs.getDouble("total_value"));
        }

    } catch (SQLException e) {
        System.out.println("Database Error: " + e.getMessage());
    }
}


// Delete an order only if its status is Cancelled
public void deleteCancelledOrder(int orderId) {

    String selectSql = "SELECT status FROM Orders WHERE order_id = ?";
    String deleteSql = "DELETE FROM Orders WHERE order_id = ? AND status = 'Cancelled'";

    try (Connection con = DBConnection.getConnection()) {

        // First check whether the order exists
        try (PreparedStatement ps = con.prepareStatement(selectSql)) {
            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    System.out.println("Order does not exist!");
                    return;
                }

                String status = rs.getString("status");

                if (!status.equalsIgnoreCase("Cancelled")) {
                    System.out.println("Cannot delete! Order is not Cancelled.");
                    return;
                }
            }
        }

        // Delete the cancelled order
        try (PreparedStatement ps = con.prepareStatement(deleteSql)) {
            ps.setInt(1, orderId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Cancelled order deleted successfully!");
            } else {
                System.out.println("Order could not be deleted!");
            }
        }

    } catch (SQLException e) {
        System.out.println("Database Error: " + e.getMessage());
    }
}

}


