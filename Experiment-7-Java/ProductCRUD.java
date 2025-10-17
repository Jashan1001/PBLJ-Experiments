import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/shop_db";
    static final String USER = "root";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false); // transaction control
            int choice;

            do {
                System.out.println("\n=== Product Management Menu ===");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addProduct(con, sc);
                    case 2 -> viewProducts(con);
                    case 3 -> updateProduct(con, sc);
                    case 4 -> deleteProduct(con, sc);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 5);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addProduct(Connection con, Scanner sc) throws SQLException {
        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        System.out.print("Enter Product ID: ");
        ps.setInt(1, sc.nextInt());
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        ps.setString(2, sc.nextLine());
        System.out.print("Enter Price: ");
        ps.setDouble(3, sc.nextDouble());
        System.out.print("Enter Quantity: ");
        ps.setInt(4, sc.nextInt());

        ps.executeUpdate();
        con.commit();
        System.out.println("✅ Product added successfully!");
    }

    static void viewProducts(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

        System.out.println("\nProductID\tProductName\tPrice\tQuantity");
        while (rs.next()) {
            System.out.printf("%d\t\t%s\t\t%.2f\t%d\n",
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getDouble("Price"),
                    rs.getInt("Quantity"));
        }
    }

    static void updateProduct(Connection con, Scanner sc) throws SQLException {
        String sql = "UPDATE Product SET Price=?, Quantity=? WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter Product ID to Update: ");
        ps.setInt(3, sc.nextInt());
        System.out.print("Enter New Price: ");
        ps.setDouble(1, sc.nextDouble());
        System.out.print("Enter New Quantity: ");
        ps.setInt(2, sc.nextInt());

        ps.executeUpdate();
        con.commit();
        System.out.println("✅ Product updated successfully!");
    }

    static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        String sql = "DELETE FROM Product WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter Product ID to Delete: ");
        ps.setInt(1, sc.nextInt());
        ps.executeUpdate();
        con.commit();
        System.out.println("🗑️ Product deleted successfully!");
    }
}
