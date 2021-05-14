package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readOrders() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table class='table table-hover'><tr>"
					
					+ "<th>OrderID</th>"
					+ "<th>User ID</th>"
					+ "<th>Funding Bodies ID</th>"
					+ "<th>Research Project ID</th>"
					+ "<th>Research Project Fund ID</th>"
					+ "<th>Order Total Amount</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from orders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String oId = Integer.toString(rs.getInt("oId"));
				String orderId = rs.getString("orderId");
				String userId = rs.getString("userId");
				String fundingBodiesId = rs.getString("fundingBodiesId");
				String researchProjectId = rs.getString("researchProjectId");
				String researchProjectFundId = rs.getString("researchProjectFundId");
				String orderTotalAmount = rs.getString("orderTotalAmount");
				
				// Add into the html table
				output += "<tr><td><input id='hidoIdUpdate' name='hidoIdUpdate' type='hidden' value='" + oId
						+ "'>" + orderId + "</td>";
				output += "<td>" + userId + "</td>";
				output += "<td>" + fundingBodiesId + "</td>";
				output += "<td>" + researchProjectId + "</td>";
				output += "<td>" + researchProjectFundId + "</td>";
				output += "<td>" + orderTotalAmount + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ oId + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Orders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertOrders(String orderId, String userId, String fundingBodiesId, String researchProjectId,
			String researchProjectFundId, String orderTotalAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into orders(`oId`,`orderId`,`userId`,`fundingBodiesId`,`researchProjectId`,`researchProjectFundId`,`orderTotalAmount`)"
					+ "values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 00);
			preparedStmt.setString(2, orderId);
			preparedStmt.setString(3, userId);
			preparedStmt.setString(4, fundingBodiesId);
			preparedStmt.setString(5, researchProjectId);
			preparedStmt.setString(6, researchProjectFundId);
			preparedStmt.setString(7, orderTotalAmount);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newOrd = readOrders();
			output = "{\"status\":\"success\", \"data\": \"" +newOrd+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Orders.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateOrders(String oId,String orderId, String userId, String fundingBodiesId, String researchProjectId,
			String researchProjectFundId, String orderTotalAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE orders SET orderId=?,userId=?,fundingBodiesId=?,researchProjectId=?,researchProjectFundId=?,orderTotalAmount=? WHERE oId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
		
			preparedStmt.setString(1, orderId);
			preparedStmt.setString(2, userId);
			preparedStmt.setString(3, fundingBodiesId);
			preparedStmt.setString(4, researchProjectId);
			preparedStmt.setString(5, researchProjectFundId);
			preparedStmt.setString(6, orderTotalAmount);
			
			preparedStmt.setInt(7, Integer.parseInt(oId));
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newOrd =readOrders();
			output = "{\"status\":\"success\", \"data\": \"" +newOrd+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the orders.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteOrders(String oId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from orders where oId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(oId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newOrd = readOrders();
			output = "{\"status\":\"success\", \"data\": \"" +newOrd + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the orders.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
