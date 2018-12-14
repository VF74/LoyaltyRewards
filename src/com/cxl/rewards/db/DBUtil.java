package com.cxl.rewards.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.cxl.rewards.model.AccountInfo;

public class DBUtil {

	// get pin and expiration info
	public static final String GET_ACCT = "select balance, pin, next_points_to_expire, next_points_expiration_date from accounts where account_number = 'abc1234'";

	// get redemption history, most recent items first
	public static final String GET_ACCT_REDEMPTION = "select redemption_date, item_description from account_redemptions where account_number = 'abc1234' order by redemption_date desc";

	// get targeted offer info in sequence order
	public static final String GET_ACCT_TARGET_OFFER = "select item_description, item_code, point_value from account_targeted_offers where account_number = 'abc1234' order by item_sequence";

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

		try {

			accountInfo = getAccountInfo("abc1234");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Print Values
		System.out.println("AccountNumber:" + accountInfo.getAccountNumber());
		System.out.println("AccountPIN:" + accountInfo.getAccountPIN());
		System.out.println("AccountBalance:" + accountInfo.getAccountBalance());
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
			rs = stmt.executeQuery(GET_ACCT);

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
			try {

				// Step 5 Close connection
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return accountInfo;
	}

}
