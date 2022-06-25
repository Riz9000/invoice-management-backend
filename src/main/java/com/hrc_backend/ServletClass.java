package com.hrc_backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ServletClass
 */
@WebServlet("/")
public class ServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static Gson gson=new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
			response.addHeader("Access-Control-Allow-Origin","*");
			response.setContentType("application/json");
			
		String action=request.getServletPath();
		switch(action)
		{
				case "/get-all-data":
					getAllData(request,response);
					break;
				case "/add-data":
					addData(request,response);
					break;
				case "/update-data":
					updateData(request,response);
					break;
		}
		
	}
	
	public void getAllData(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Attribute> d1=AttributeConnection.getAllData();
			String JSONresponse=gson.toJson(d1);
			response.getWriter().append(JSONresponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addData(HttpServletRequest request, HttpServletResponse response) {
		String business_code=request.getParameter("business_code");
		String cust_number = request.getParameter("cust_number");
		String clear_date = request.getParameter("clear_date");
		String buisness_year =  request.getParameter("buisness_year");
		String doc_id = request.getParameter("doc_id");
		String posting_date = request.getParameter("posting_date");
		String document_create_date =  request.getParameter("document_create_date");
		String due_in_date = request.getParameter("due_in_date");
		String invoice_currency =  request.getParameter("invoice_currency");
		String document_type =  request.getParameter("document_type");
		String posting_id = request.getParameter("posting_id");
		String total_open_amount = request.getParameter("total_open_amount");
		String baseline_create_date =request.getParameter("baseline_create_date");
		String cust_payment_terms =  request.getParameter("cust_payment_terms");
		String invoice_id = request.getParameter("invoice_id");
		Attribute d1=new Attribute(business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id);
		int result=AttributeConnection.addData(d1);
		String JSONresponse;
		if(result==1)
			JSONresponse=gson.toJson("Data insterted successfully");
		else
			JSONresponse=gson.toJson("Error in inserting data");
		try {
			response.getWriter().append(JSONresponse);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void updateData(HttpServletRequest request, HttpServletResponse response) {
		try {
			int s_no=Integer.parseInt(request.getParameter("s_no"));
			String invoice_currency =  request.getParameter("invoice_currency");
			String cust_payment_terms =  request.getParameter("cust_payment_terms");
			int result=AttributeConnection.updateData(s_no, invoice_currency, cust_payment_terms);
			String JSONresponse;
			if(result==1)
				JSONresponse=gson.toJson("Data Updated successfully");
			else
				JSONresponse=gson.toJson("Error in Updating data");
				response.getWriter().append(JSONresponse);
			} 
		  catch(NumberFormatException a)
		  {
			  try {
				response.getWriter().append("Data updated without parsing null string");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		catch (IOException e){
				e.printStackTrace();
			}
}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
