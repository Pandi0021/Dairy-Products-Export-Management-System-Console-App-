package DPEMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportInfo extends DPEMS {
	void exportInfoReportEntry() {
		try {
			System.out.println("-----Export Info Report Entry-----");
			System.out.println("Enter product id");
			int pi = sc.nextInt();
			System.out.println("Enter quantity");
			float eq = -1;
			while (eq < 0) {
				eq = sc.nextInt();
				if (eq < 0) {
					System.out.println("Quantity cannot be negative. Enter a non-negative quantity:");
				}
			}
			System.out.println("Enter price");
			double ep = -1.0;
			while (ep < 0.0) {
				ep = sc.nextDouble();
				if (ep < 0.0) {
					System.out.println("Price cannot be negative. Enter a non-negative price:");
				}
			}
			System.out.println("Enter destination");
			String ed = sc.nextLine();
			ed = sc.nextLine();
			String q = "insert into ExportInfo(product_id,quantity,price,destination)values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(q);
			pst.setInt(1, pi);
			pst.setFloat(2, eq);
			pst.setDouble(3, ep);
			pst.setString(4, ed);
			int res = pst.executeUpdate();
			String q3 = "select quantity from productInfo where product_id=?";
			PreparedStatement pst1 = con.prepareStatement(q3);
			pst1.setInt(1, pi);
			ResultSet r1 = pst1.executeQuery();
			float quantity = 0;

			while (r1.next()) {

				quantity = (r1.getInt(1) - eq);
			}
			String q2 = "update productInfo set quantity=? where product_id=?";
			PreparedStatement ps = con.prepareStatement(q2);
			ps.setFloat(1, quantity);
			ps.setInt(2, pi);
			int r = ps.executeUpdate();
			System.out.println((res == 1 & r == 1) ? " Export Info Added Succesfully!!!" : "Not added!");
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL Try Again!!!");
		} catch (Exception e) {
			System.out.println("Something went wrong Try Again!!!");
		}
	}

	void exportInfoReport() {
		try {
			System.out.println("-----Export Info Report-----");
			String q = "select * from ExportInfo";
			PreparedStatement pst1 = con.prepareStatement(q);
			ResultSet resultSet = pst1.executeQuery();
			while (resultSet.next()) {
				int exportId = resultSet.getInt("export_id");
				int product_id = resultSet.getInt("product_id");
				String export_date = resultSet.getString("export_date");
				float quantity = resultSet.getFloat("quantity");
				double price = resultSet.getDouble("price");
				String destination = resultSet.getString("destination");
				System.out.println("Export ID: " + exportId);
				System.out.println("Product_id: " + product_id);
				System.out.println("export_date: " + export_date);
				System.out.println("Quantity: " + quantity + " l");
				System.out.println("Price: $" + price);
				System.out.println("Destination: " + destination);
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

	void exportInfoDeleted() {
		try {
			System.out.println("-----Export Info Deleted-----");
			System.out.println("Enter Destination");
			sc.nextLine();
			String ed = sc.nextLine();
			String q = "delete from exportInfo where destination=?";
			PreparedStatement pst = con.prepareStatement(q);
			pst.setString(1, ed);
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
}
