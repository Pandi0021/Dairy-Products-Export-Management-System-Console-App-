package DPEMS;

import java.sql.SQLException;

public class Call extends DPEMS {
	static ExportInfo e = new ExportInfo();
	static FarmerInfo f = new FarmerInfo();
	static ProductInfo p = new ProductInfo();
	static Login l = new Login();
	static Calc c = new Calc();

	static void Entry() {

		while (true) {
			System.out.println("\n-----Report Entry Menu-----");
			System.out.println("1. Product Info Entry");
			System.out.println("2. farmer Report Entry");
			System.out.println("3. Export Report Entry");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			sc.nextLine();
			String choice = sc.next();

			switch (choice) {
			case "1":
				p.productInfoEntry();
				break;
			case "2":
				f.farmerReportEntry();
				break;
			case "3":
				e.exportInfoReportEntry();
				break;
			case "4":
				System.out.println("Exiting  Report Entry Menu!.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}

	static void read() {

		while (true) {

			System.out.println("\n-----Report View Menu-----");
			System.out.println("1. Product Info View");
			System.out.println("2. farmer Report View");
			System.out.println("3. Export Report View");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			sc.nextLine();

			String choice = sc.next();

			switch (choice) {
			case "1":
				p.productInfoReport();
				break;
			case "2":
				f.farmerInfoReport();
				break;
			case "3":
				e.exportInfoReport();
				break;
			case "4":
				System.out.println("Exiting  Report View Menu!.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	static void update() {
		while (true) {

			System.out.println("\n-----Report Update Menu-----");
			System.out.println("1. Product Info Update");
			System.out.println("2. farmer Report Update");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			sc.nextLine();
			String choice = sc.next();

			switch (choice) {
			case "1":
				p.productInfoupdate();
				break;
			case "2":
				f.farmerinfoupdate();
				break;

			case "3":
				System.out.println("Exiting  Report Updete Menu!.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

		}
	}

	static void delete() {

		while (true) {

			System.out.println("\n-----Report Delete Menu-----");
			System.out.println("1. Product Report Delete");
			System.out.println("2. farmer Report Delete");
			System.out.println("3. Export Report Delete");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			sc.nextLine();
			String choice = sc.next();

			switch (choice) {
			case "1":
				p.productInfoDeleted();
				break;
			case "2":
				f.farmerInfoDeleted();
				break;
			case "3":
				e.exportInfoDeleted();
				break;
			case "4":
				System.out.println("Exiting Report Delete Menu!.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

		}
	}

	static void calc() {
		while (true) {

			System.out.println("\n-----Calc & Vedic Process-----");
			System.out.println("1. farmer TotalAmount calc");
			System.out.println("2. Export TotalAmount calc");
			System.out.println("3. farmer Monthly TotalAmount calc");
			System.out.println("4. Export Monthly TotalAmount calc");
			System.out.println("5. vedic Process");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			String choice = sc.next();
			switch (choice) {
			case "1":
				c.farmerTotalAmountcalc();
				break;
			case "2":
				c.exportTotalAmountcalc();
				break;
			case "3":
				c.farmerMonthlyAmountCalc();
				break;
			case "4":
				c.exportMonthlyAmountcalc();
				break;
			case "5":
				c.vedicProcess();
				break;
			case "6":
				System.out.println("Exiting Calc & Vedic Process Menu!.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		connect();
		int c = 0;
		String u = "", p = "";
		boolean a = true;
		while (a == true) {
			try {
				if (c == 0||c == 1||c == 2||c == 3||c == 4) {
					System.out.println("Enter Username ");
					u = sc.nextLine();
					System.out.println("Enter Password");
					p = sc.nextLine();
					l.login(u, p);
				} else if (c == 5) {
					System.out.println("in 30 seconds to login");
					Thread.sleep(30000);
					System.out.println("Enter Username ");
					u = sc.nextLine();
					System.out.println("Enter Password");
					p = sc.nextLine();
					l.login(u, p);
				} else if (c == 6) {
					System.out.println("in 60 seconds to login");
					Thread.sleep(60000);
					System.out.println("Enter Username ");
					u = sc.nextLine();
					System.out.println("Enter Password");
					p = sc.nextLine();
					l.login(u, p);
				}
				 else if (c == 7) {
						System.out.println("in 2 minutes to login");
						Thread.sleep(12000);
						System.out.println("Enter Username ");
						u = sc.nextLine();
						System.out.println("Enter Password");
						p = sc.nextLine();
						l.login(u, p);
					}
				 else if (c == 8) {
						System.out.println("in 24 hours to login");
						Thread.sleep(86400);
						System.out.println("Enter Username ");
						u = sc.nextLine();
						System.out.println("Enter Password");
						p = sc.nextLine();
						l.login(u, p);
					}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (l.login(u, p)) {
				System.out.println("Login Successfully!!!!");
				a = false;
			} else {
				System.out.println("Try again!!!!");
				a = true;
				c++;
			}

		}

		while (l.login(u, p)) {

			System.out.println("\nDairy Products Export Management System:");
			System.out.println("1. Report Entry");
			System.out.println("2. View Report");
			System.out.println("3. Report Update");
			System.out.println("4. Report Delete");
			System.out.println("5. Calc & Vedic Process");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			String choice = sc.next();

			switch (choice) {
			case "1":
				Entry();
				break;
			case "2":
				read();
				break;
			case "3":
				update();
				break;
			case "4":
				delete();
				break;
			case "5":
				calc();
				break;
			case "6":
				System.out.println("Exiting the program.");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}
}
