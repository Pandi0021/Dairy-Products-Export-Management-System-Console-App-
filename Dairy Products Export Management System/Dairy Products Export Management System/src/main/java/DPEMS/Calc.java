package DPEMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Calc extends DPEMS {
	void vedicProcess() {
		try {
			System.out.println("-----Vedic Process-----");
			String q3 = "select quantity from productInfo where product_name='Milk'";
			PreparedStatement pst1 = con.prepareStatement(q3);
			ResultSet r1 = pst1.executeQuery();
			float quantity = 0;
			while (r1.next()) {
				quantity = (r1.getInt(1));
			}
			System.out.println("Quantity of Milk is : " + quantity);
			System.out.println("Enter quantity to vedicProcess:");
			float eq = -1;
			while (eq < 0 && eq < quantity) {
				eq = sc.nextInt();
				if (eq < 0 && eq < quantity) {
					System.out.println("Quantity cannot be negative. Enter a non-negative quantity:");
				}
			}
			quantity -= eq;
			String q2 = "update productInfo set quantity=? where product_name='Milk'";
			PreparedStatement ps = con.prepareStatement(q2);
			ps.setDouble(1, quantity);
			int r = ps.executeUpdate();
			String qv = "select product_name,quantity from productInfo where product_name='Ghee'";
			PreparedStatement pst = con.prepareStatement(qv);
			ResultSet rv = pst.executeQuery();
			float quantitya = 0;
			String product_namea = "";
			while (rv.next()) {
				product_namea = (rv.getString(1));
				quantitya = (rv.getFloat(2));
			}
			quantitya += eq / 2;
			String q = "update productInfo set quantity=? where product_name='Ghee'";
			PreparedStatement p = con.prepareStatement(q);
			p.setFloat(1, quantitya);
			int r2 = p.executeUpdate();
			System.out.println((r == 1 && r2 == 1)
					? "After Vedic Process added " + eq / 2 + " quantity to product_name: " + product_namea + "!!!"
					: "Not Reduced quantity!");
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
			sc.nextLine();
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL!!!");
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("Something went wrong!!!");
			sc.nextLine();
		}
	}

	void farmerMonthlyAmountCalc() {
		try {
			System.out.println("-----Farmer Monthly Total Amount calc-----");
			String q1 = "select distinct (farmer_name) from FarmerInfo";
			PreparedStatement pst1 = con.prepareStatement(q1);
			ResultSet resultSet1 = pst1.executeQuery();
			while (resultSet1.next()) {
				String farmerName = resultSet1.getString("farmer_name");
				System.out.println("Farmer Name: " + farmerName);
			}
			System.out.println("Enter Farmer name");
			sc.nextLine();
			String fn = sc.nextLine();
			String query = "SELECT * FROM FarmerInfo WHERE  YEAR(date) = ? AND MONTH(date) = ? and farmer_name= ?";
			System.out.println("Enter the year");
			int year = sc.nextInt();
			System.out.println("Enter the Month");
			int month = sc.nextInt();

			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, month);
			preparedStatement.setString(3, fn);
			ResultSet resultSet = preparedStatement.executeQuery();

			double totalAmount = 0.0;
			while (resultSet.next()) {
				double price = resultSet.getDouble("price");
				float quantity = resultSet.getFloat("quantity");

				double productTotal = price * quantity;
				totalAmount += productTotal;
			}
			System.out.println("Total amount for " + fn + " " + month + "-" + year + " : $" + totalAmount);
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
			sc.nextLine();
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL!!!");
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("Something went wrong!!!");
			sc.nextLine();
		}
	}

	void exportMonthlyAmountcalc() {
		try {
			System.out.println("-----Export Monthly Total Amount calc-----");
			String q1 = "select distinct (destination) from ExportInfo";
			PreparedStatement pst = con.prepareStatement(q1);
			ResultSet resultSet1 = pst.executeQuery();
			while (resultSet1.next()) {
				String destination = resultSet1.getString("destination");
				System.out.println("Destination: " + destination);
			}
			System.out.println("Enter destination");
			sc.nextLine();
			String ds = sc.nextLine();
			String query = "SELECT * FROM ExportInfo WHERE YEAR(export_date)= ? AND MONTH(export_date) = ? and destination=?";
			System.out.println("Enter the Year");
			int year = sc.nextInt();
			System.out.println("Enter the Month");
			int month = sc.nextInt();

			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, month);
			preparedStatement.setString(3, ds);
			ResultSet resultSet = preparedStatement.executeQuery();

			double totalAmount = 0.0;
			while (resultSet.next()) {
				double price = resultSet.getDouble("price");
				float quantity = resultSet.getFloat("quantity");

				double productTotal = price * quantity;
				totalAmount += productTotal;
			}
			System.out.println("Total amount for " + ds + " " + month + "-" + year + " : $" + totalAmount);

		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
			sc.nextLine();
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL!!!");
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("Something went wrong!!!");
			sc.nextLine();
		}
	}

	void farmerTotalAmountcalc() {
		try {
			System.out.println("-----Farmer Total Amount calc-----");
			String q1 = "select distinct (farmer_name) from FarmerInfo";
			PreparedStatement pst1 = con.prepareStatement(q1);
			ResultSet resultSet = pst1.executeQuery();
			while (resultSet.next()) {
				String farmerName = resultSet.getString("farmer_name");
				System.out.println("Farmer Name: " + farmerName);
			}
			System.out.println("Enter Farmer name");
			sc.nextLine();
			String fn = sc.nextLine();
			String q = "SELECT * FROM FarmerInfo WHERE farmer_name=?";
			double totalAmount = 0.0;

			PreparedStatement pst2 = con.prepareStatement(q);
			pst2.setString(1, fn);
			ResultSet resultSet1 = pst2.executeQuery();
			while (resultSet1.next()) {
				double price = resultSet1.getDouble("price");
				float quantity = resultSet1.getInt("quantity");
				double productTotal = price * quantity;
				totalAmount += productTotal;
			}
			System.out.println("Total Amount for farmer_name " + fn + ": $" + totalAmount);
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
			sc.nextLine();
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL!!!");
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("Something went wrong!!!");
			sc.nextLine();
		}
	}

	void exportTotalAmountcalc() {
		try {
			System.out.println("-----Export Total Amount calc-----");
			String q1 = "select distinct (destination) from ExportInfo";
			PreparedStatement pst = con.prepareStatement(q1);
			ResultSet resultSet1 = pst.executeQuery();
			while (resultSet1.next()) {
				String destination = resultSet1.getString("destination");
				System.out.println("Destination: " + destination);
			}
			System.out.println("Enter destination");
			sc.nextLine();
			String ds = sc.nextLine();
			String q = "SELECT * FROM ExportInfo WHERE destination=?";
			double totalAmount = 0.0;
			PreparedStatement pst1 = con.prepareStatement(q);
			pst1.setString(1, ds);
			ResultSet resultSet = pst1.executeQuery();
			while (resultSet.next()) {
				double price = resultSet.getDouble("price");
				float quantity = resultSet.getFloat("quantity");

				double productTotal = price * quantity;
				totalAmount += productTotal;
			}

			System.out.println("Total Amount for destination " + ds + ": $" + totalAmount);
		} catch (NumberFormatException e) {
			System.out.println("Number format exception");
			sc.nextLine();
		} catch (SQLException e) {
			System.out.println("Something went wrong in SQL!!!");
			sc.nextLine();
		} catch (Exception e) {
			System.out.println("Something went wrong!!!");
			sc.nextLine();
		}
	}
}
