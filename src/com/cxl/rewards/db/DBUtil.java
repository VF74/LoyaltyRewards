package com.cxl.rewards.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cxl.rewards.model.AccountInfo;
import com.cxl.rewards.model.FeaturedItem;
import com.cxl.rewards.model.RedemptionItem;

public class DBUtil {

	// get pin and expiration info
	public static final String GET_ACCT_INFO = "select balance, pin, next_points_to_expire, next_points_expiration_date from accounts where account_number = 'abc1234'";

	// get redemption history, most recent items first
	public static final String GET_ACCT_REDEMPTIONS = "select redemption_date, item_description from account_redemptions where account_number = 'abc1234' order by redemption_date desc";

	// get targeted offer info in sequence order
	public static final String GET_FEAUTRED_ITEMS = "select item_description, item_code, point_value from account_targeted_offers where account_number = 'abc1234' order by item_sequence";
	
	// get targeted offer info in sequence order
	public static final String GET_FEAUTRED_ITEMS_BY_CODE = "select item_description, item_code, point_value from account_targeted_offers where account_number = 'abc1234' and item_code='AMAZONEC100'";

	public static Connection getConnection() throws Exception {
		//Every thing is harcoded for now
		Connection conn = null;
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://mycompany.cvxcdyqwxebd.us-east-1.rds.amazonaws.com:5432/mycompanydb";
		Properties props = new Properties();
		props.setProperty("user", "loyaltyone");
		props.setProperty("password", "connexions");
		// props.setProperty("ssl","true");
		conn = DriverManager.getConnection(url, props);

		return conn;

	}

	public static void main(String args[]) {
		AccountInfo accountInfo = null;
		
		List<RedemptionItem> redemptionItems=null;
		
		 List<FeaturedItem> featuredItems=null;

		try {

			redeemItem("abc1234","AMAZONEC100");
			
			accountInfo = getAccountInfo("abc1234");
			
			redemptionItems=getRedemptionHistory("abc1234");
			
			featuredItems=getFeaturedItems("abc1234");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Print Values
		System.out.println("AccountNumber:" + accountInfo.getAccountNumber());
		System.out.println("AccountPIN:" + accountInfo.getAccountPIN());
		System.out.println("AccountBalance:" + accountInfo.getAccountBalance());
		
		System.out.println("Redemption History Count:" + redemptionItems.size());
		
		System.out.println("Featured Items Count:" + featuredItems.size());
		
	}

// Get Account Info
	public static AccountInfo getAccountInfo(String accountNumber) throws Exception {

		AccountInfo accountInfo = new AccountInfo();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(GET_ACCT_INFO);

			if (rs.next()) {
				accountInfo.setAccountBalance(rs.getString(1));
				accountInfo.setAccountPIN(rs.getString(2));
				accountInfo.setAccountNumber(accountNumber);
				accountInfo.setPointsToExpire(rs.getString(3));
				accountInfo.setExpirationDate(rs.getString(4));
				//System.out.println(rs.getString(1));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			closeConnectionObjects(conn,stmt,rs);
		}

		return accountInfo;
	}
	
	
	// Get Redemption History
		public static List<RedemptionItem> getRedemptionHistory(String accountNumber) throws Exception {

			List<RedemptionItem> redemptions= new ArrayList<RedemptionItem>();
			
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {

				conn = getConnection();

				stmt = conn.createStatement();
				rs = stmt.executeQuery(GET_ACCT_REDEMPTIONS);

				while (rs.next()) {
					
					RedemptionItem item= new RedemptionItem();
					
					item.setRedemptionDate(rs.getString(1));
					item.setItemDescription(rs.getString(2));
					redemptions.add(item);
				
				}

			} catch (Exception e) {
				throw e;
			} finally {
				closeConnectionObjects(conn,stmt,rs);
			}

			return redemptions;
		}


		
		// Get Redemption History
				public static void redeemItem(String accountNumber,String itemCode) throws Exception {

					FeaturedItem item= new FeaturedItem();
					
					Connection conn = null;
					PreparedStatement stmt = null;
					ResultSet rs = null;

					try {

						conn = getConnection();
						
						stmt = conn.prepareCall(GET_FEAUTRED_ITEMS_BY_CODE);
						
						//stmt.setString(0, itemCode);
							
						rs=stmt.executeQuery();
						
						

						if (rs.next()) {
							
						
							
							item.setItemDescription(rs.getString(1));
							item.setItemCode(rs.getString(2));
							item.setItemPointValue(rs.getString(3));
						
						
						}
						
					//
					String insertAccountRedemptions="INSERT INTO account_redemptions(account_number, redemption_date, item_description) VALUES ('abc1234', now(), ' $100 Amazon.com Gift Card' )";
				
					stmt = conn.prepareCall(insertAccountRedemptions);
						
					//stmt1.setString(1, item.getItemDescription());
						
					stmt.executeUpdate();
					
					
					String updateAccount= "UPDATE accounts SET balance=balance-10000 WHERE account_number = 'abc1234'";
						
					
					stmt = conn.prepareCall(updateAccount);
						
					//stmt.setString(0, item.getItemDescription());
						
					stmt.executeUpdate();
					
					

					} catch (Exception e) {
						throw e;
					} finally {
						closeConnectionObjects(conn,stmt,rs);
					}

					
				}

				
				
		// Get Featured Items
				public static List<FeaturedItem> getFeaturedItems(String accountNumber) throws Exception {

					List<FeaturedItem> featuredItems= new ArrayList<FeaturedItem>();
					
					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;

					try {

						conn = getConnection();

						stmt = conn.createStatement();
						rs = stmt.executeQuery(GET_FEAUTRED_ITEMS);

						while (rs.next()) {
							
							FeaturedItem item= new FeaturedItem();
							
							item.setItemDescription(rs.getString(1));
							item.setItemCode(rs.getString(2));
							item.setItemPointValue(rs.getString(3));
							featuredItems.add(item);
						
						}

					} catch (Exception e) {
						throw e;
					} finally {
						closeConnectionObjects(conn,stmt,rs);
					}

					return featuredItems;
				}
				
				
		public static void closeConnectionObjects(Connection conn,Statement stmt,ResultSet rs) {
			
			try {

				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
