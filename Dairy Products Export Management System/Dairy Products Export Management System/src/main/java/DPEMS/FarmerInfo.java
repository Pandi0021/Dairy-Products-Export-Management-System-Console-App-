package DPEMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmerInfo extends DPEMS  {
	void farmerReportEntry() {
		try {
			System.out.println("-----Farmer Report Entry-----");
			System.out.println("Enter product_name ");
			sc.nextLine();
			String pn = sc.nextLine();
			System.out.println("Enter farmer_name ");
			String fn = sc.nextLine();
			
			System.out.println("Enter quantity");
			float fq = -1; 
		        while (fq < 0) {
		            fq = sc.nextFloat();
		            if (fq < 0) {
		                System.out.println("Quantity cannot be negative. Enter a non-negative quantity:");
		            }
		        }
		        System.out.println("Enter price");
		        double fp = -1.0; 
		        while (fp < 0.0) {
		            fp = sc.nextDouble();
		            if (fp < 0.0) {
		                System.out.println("Price cannot be negative. Enter a non-negative price:");
		            }
		        }
			String q = "insert into farmerinfo(farmer_name,quantity,price)values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(q);
			pst.setString(1, fn);
			pst.setFloat(2, fq);
			pst.setDouble(3, fp);
			int res = pst.executeUpdate();
			String q1 = "select quantity from productInfo where product_name=?";
			PreparedStatement pst1 = con.prepareStatement(q1);
			pst1.setString(1, pn);
			ResultSet r1 = pst1.executeQuery();
			float quantity = 0;
			while (r1.next()) {
				quantity = (r1.getInt(1) + fq);
			}

			String q2 = "update productInfo set quantity=? where product_name=?";
			PreparedStatement ps = con.prepareStatement(q2);
			ps.setFloat(1, quantity);
			ps.setString(2, pn);
			int r = ps.executeUpdate();
			System.out.println((res == 1 && r == 1) ? "Farmer Info Added Succesfully!!!" : "Not added!");
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL Try Again!!!");
		} catch (Exception e) {
			System.out.println("Something went wrong Try Again!!!");
		}
	}

	void farmerinfoupdate() {
		try {
			System.out.println("-----Farmer Info Update-----");
			System.out.println("Enter farmer_name ");
			sc.nextLine();
			String fn = sc.nextLine();
			System.out.println("Enter new price");
			double fp = sc.nextDouble();
			String q = "update farmerinfo set price=? where farmer_name=?";
			PreparedStatement pst = con.prepareStatement(q);
			pst.setDouble(1, fp);
			pst.setString(2, fn);
			int r = pst.executeUpdate();
			System.out.println((r > 0) ? "Updated Succesfully!!!" : "Not Updated!!");
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL Try Again!!!");
		} catch (Exception e) {
			System.out.println("Something went wrong Try Again!!!");
		}
	}

	void farmerInfoDeleted() {
		try {
			System.out.println("-----farmer Info Deleted-----");
			System.out.println("Enter farmer_name ");
			sc.nextLine();
			String fn = sc.nextLine();
			String q = "delete from farmerinfo where farmer_name=?";
			PreparedStatement pst = con.prepareStatement(q);
			pst.setString(1, fn);
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

	void farmerInfoReport() {
		try {
			System.out.println("-----farmer Info Report-----");
			String q = "select * from farmerInfo";
			PreparedStatement pst1 = con.prepareStatement(q);
			ResultSet resultSet = pst1.executeQuery();
			while (resultSet.next()) {
				int farmerId = resultSet.getInt("farmer_id");
				String farmerName = resultSet.getString("farmer_name");
				float quantity = resultSet.getFloat("quantity");
				double price = resultSet.getDouble("price");
				String date = resultSet.getString("date");
				System.out.println("farmer ID: " + farmerId);
				System.out.println("farmer Name: " + farmerName);
				System.out.println("Quantity: " + quantity);
				System.out.println("Price: $" + price);
				System.out.println("Date: " + date);
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
