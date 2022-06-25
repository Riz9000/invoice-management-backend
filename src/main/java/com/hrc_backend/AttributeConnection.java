package com.hrc_backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttributeConnection {
	private static String jdbcURL="jdbc:mysql://localhost:3306/grey_goose";
	private static String jdbcUsername="root";
	private static String jdbcPassword="Swarnavo@2000";

	protected static Connection getConnection() throws SQLException { 
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}catch(ClassNotFoundException e) {
	        e.printStackTrace();
		}
		
		return connection;
	}
	
	//Geting dataset from SQL
	public static List<Attribute> getAllData() {
		List<Attribute> allData=new ArrayList<>();
		
		try {
		Connection connection=getConnection();
		String GET_DATA="SELECT * from winter_internship";
		
			PreparedStatement ps=connection.prepareStatement(GET_DATA);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int sl_no = rs.getInt("sl_no");
				String business_code = rs.getString("business_code");
				String cust_number = rs.getString("cust_number");
				String clear_date = rs.getString("clear_date");
				String buisness_year = rs.getString("buisness_year");
				String doc_id = rs.getString("doc_id");
				String posting_date = rs.getString("posting_date");
				String document_create_date = rs.getString("document_create_date");
				String due_in_date = rs.getString("due_in_date");
				String invoice_currency = rs.getString("invoice_currency");
				String document_type = rs.getString("document_type");
				String posting_id = rs.getString("posting_id");
				String total_open_amount = rs.getString("total_open_amount");
				String baseline_create_date = rs.getString("baseline_create_date");
				String cust_payment_terms = rs.getString("cust_payment_terms");
				String invoice_id = rs.getString("invoice_id");
				String aging_bucket = rs.getString("aging_bucket");
				
				Attribute d1=new Attribute(sl_no,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,aging_bucket);
				allData.add(d1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allData;
	}
	
	public static int addData(Attribute d1) {
		try {
			Connection connection=getConnection();
			String INSERT_DATA="INSERT INTO winter_internship(business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt=connection.prepareStatement(INSERT_DATA);
			psmt.setString(1, d1.getBusiness_code());
			psmt.setString(2, d1.getCust_number());
			psmt.setString(3, d1.getClear_date());
			psmt.setString(4, d1.getBuisness_year());
			psmt.setString(5, d1.getDoc_id());
			psmt.setString(6, d1.getPosting_date());
			psmt.setString(7, d1.getDocument_create_date());
			psmt.setString(8, d1.getDue_in_date());
			psmt.setString(9, d1.getInvoice_currency());
			psmt.setString(10, d1.getDocument_type());
			psmt.setString(11, d1.getPosting_id());
			psmt.setString(12, d1.getTotal_open_amount());
			psmt.setString(13, d1.getBaseline_create_date());
			psmt.setString(14, d1.getCust_payment_terms());
			psmt.setString(15, d1.getInvoice_id());
			
			if(psmt.executeUpdate()>0)
			{
				return 1;
			}
			else
				return 0;			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public static int updateData(int s_no,String invoice_currency,String cust_payment_terms) {         
		try {
			Connection connection = getConnection();
			String UPDATE_DATA = "UPDATE winter_internship SET invoice_currency = ?, cust_payment_terms = ? WHERE sl_no="+s_no;
			PreparedStatement psmt = connection.prepareStatement(UPDATE_DATA);
			psmt.setString(1, invoice_currency);
			psmt.setString(2, cust_payment_terms);
			
			if(psmt.executeUpdate()>0)
				return 1;
			else
				return 0;			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
}