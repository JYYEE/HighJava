package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		int num1 = Integer.parseInt(request.getParameter("num1")); 
//		String op = request.getParameter("operation");
//		int num2 = Integer.parseInt(request.getParameter("num2")); 
//		double result=0;
//		switch(op) {
//		case "+":
//			result = num1 + num2;
//			break;
//		case "-":
//			result = num1 - num2;
//			break;
//		case "*":
//			result = num1 * num2;
//			break;
//		case "/":
//			result = num1/num2;
//			break;
//		case "%":
//			result = num1%num2;
//			break;
//		} // me

		// tr
		String strNum1 = request.getParameter("num1");
		String strNum2 = request.getParameter("num2");
		String op = request.getParameter("operation");

		int num1 = Integer.parseInt(strNum1);
		int num2 = Integer.parseInt(strNum2);

		double result = 0; // 계산 결과가 저장될 변수 선언
		boolean calcOK = true; // 계산 성공 여부를 나타내는 변수

		switch (op) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			if (num2 == 0) {
				calcOK = false;
			} else {
				result = (double) num1 / num2;
				calcOK = true;
				break;
			}
			break;
		case "%":
			if (num2 == 0) {
				calcOK = false;
			} else {
				result = num1 % num2;
				calcOK = true;
				break;
			}
			break;
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><meta charset='utf-8'><title>Request객체 연습2</title></head>");
		out.println("<body>");
		out.println("<h2>계산 결과</h2>");
		out.println(num1 + op + num2 + " = ");
		if (calcOK == true) {
			out.println(result);
		} else {
			out.println("계산 불능(0으로 나누기)");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
