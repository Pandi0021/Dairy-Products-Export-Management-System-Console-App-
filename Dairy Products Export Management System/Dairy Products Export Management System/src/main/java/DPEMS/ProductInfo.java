package DPEMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInfo extends DPEMS {
	void productInfoEntry() {
		try {
			System.out.println("-----Product Info Entry-----");
			System.out.println("Enter Product ID");
			int pi = sc.nextInt();
			System.out.println("Enter Product_name");
			String pn = sc.nextLine();
			pn = sc.nextLine();
			System.out.println("Enter description ");
			String pd = sc.nextLine();
			System.out.println("Enter category");
			String pc = sc.nextLine();
			System.out.println("Enter quantity");
			float pq = -1;
			while (pq < 0) {
				pq = sc.nextInt();
				if (pq < 0) {
					System.out.println("Quantity cannot be negative. Enter a non-negative quantity:");
				}
			}
			System.out.println("Enter price");
			double pp = -1.0;
			while (pp < 0.0) {
				pp = sc.nextDouble();
				if (pp < 0.0) {
					System.out.println("Price cannot be negative. Enter a non-negative price:");
				}
			}
			String q = "insert into productInfo(product_id,product_name,description,category,quantity,price)values(?,?,?,?,?,?)";
			PreparedStatement pst;

			pst = con.prepareStatement(q);
			pst.setInt(1, pi);
			pst.setString(2, pn);
			pst.setString(3, pd);
			pst.setString(4, pc);
			pst.setFloat(5, pq);
			pst.setDouble(6, pp);
			int res = pst.executeUpdate();
			System.out.println((res == 1) ? "Product Info Added Succesfully!!!" : "Not added Try Again!");
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL!!!");
		} catch (Exception e) {
			System.out.println("Something went wrong Try Again!!!");
		}
	}

	void productInfoupdate() {
		try {
			System.out.println("-----Product Info Update-----");
			System.out.println("Enter Product_name");
			sc.nextLine();
			String pn = sc.nextLine();
			System.out.println("Enter new price");
			double pp = sc.nextLong();
			String q = "update productInfo set price=? where product_name=?";
			PreparedStatement pst = con.prepareStatement(q);
			pst.setDouble(1, pp);
			pst.setString(2, pn);
			int r = pst.executeUpdate();
			System.out.println((r > 0) ? "Updated Succesfully!!!" : "Not Updated!!");
		} catch (NumberFormatException e) {
			System.out.println("Number format exception Try Again");
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL Try Again!!!");
		} catch (Exception e) {
			System.out.println("Something went wrong Try Again!!!");
		}
	}

	void productInfoDeleted() {
		try {
			System.out.println("-----Product Info Deleted-----");
			System.out.println("Enter Product_name");
			sc.nextLine();
			String pn = sc.nextLine();
			String q = "delete from productInfo where  product_name=?";
			PreparedStatement pst = con.prepareStatement(q);
			pst.setString(1, pn);
			System.out.println("Press Y to confirm deletion...");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				int r = pst.executeUpdate();
				System.out.println((r > 0) ? "Deleted!!!" : "Not Deleted");
			} else {
				System.out.println("Process Terminated!!");
			}
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL Try Again!!!");
		} catch (Exception e) {
			System.out.println("Something went wrong Try Again!!!");
		}
	}

	void productInfoReport() {
		try {
			String q = "select * from productInfo";
			PreparedStatement pst1 = con.prepareStatement(q);
			ResultSet resultSet = pst1.executeQuery();
			System.out.println("-----Product Info Report-----");
			while (resultSet.next()) {
				int productId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String description = resultSet.getString("description");
				String category = resultSet.getString("category");
				float quantity = resultSet.getFloat("quantity");
				double price = resultSet.getDouble("price");
				String dateAdded = resultSet.getString("date_added");
				System.out.println("Product ID: " + productId);
				System.out.println("Product Name: " + productName);
				System.out.println("Description: " + description);
				System.out.println("Category: " + category);
				System.out.println("Quantity: " + quantity);
				System.out.println("Price: $" + price);
				System.out.println("Date Added: " + dateAdded);
				System.out.println("-------------------------------------------");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Interrupted Exception");
				}

			}
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL Try Again!!!");
		} catch (Exception e) {
			System.out.println("Something went wrong Try Again!!!");
		}
	}

}