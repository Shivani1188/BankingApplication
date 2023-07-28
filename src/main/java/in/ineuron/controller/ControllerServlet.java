package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.dto.Person;
import in.ineuron.factory.PersonServiceFactory;
import in.ineuron.service.IPersonService;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI=request.getRequestURI();
		System.out.println("requestURI"+requestURI);
		IPersonService perService=PersonServiceFactory.getPersonService();
		RequestDispatcher rd=null;
		
		if(requestURI.endsWith("index"))
		{
			rd=request.getRequestDispatcher("../index.html");
			rd.forward(request, response);
		}
		if(requestURI.endsWith("register"))
		{
			String pname=request.getParameter("pname");
			String mname=request.getParameter("mname");
			String fname=request.getParameter("fname");
			String page=request.getParameter("page");
			String mno=request.getParameter("mno");
			String atype=request.getParameter("atype");
			
			System.out.println(atype);
			String paddr=request.getParameter("paddr");
			String pwd=request.getParameter("pwd");
			
			Person person=new Person();
			person.setPname(pname);
			person.setFname(fname);
			person.setMname(mname);
			person.setPage(Integer.parseInt(page));
			//person.setMno(Double.parseDouble(mno));
			person.setMno(mno);
			person.setAtype(atype);
			person.setPaddr(paddr);
			person.setPwd(pwd);
			
			String status=perService.register(person);
			System.out.println(status);
			if(status.equals("success"))
			{
				//System.out.println("");
				rd=request.getRequestDispatcher("../loginAfterReg.html");
				//rd=request.getRequestDispatcher("../success.html");
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("../failure.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("login"))
		{
			
			String ano=request.getParameter("ano");
			System.out.println("controller"+ano);
			String pname=request.getParameter("pname");
			System.out.println("controller"+pname);
			String pwd=request.getParameter("pwd");
			System.out.println("controller"+pwd);
			
//			Person person= new Person();
//			person.setaNo(Integer.parseInt(ano));
//			person.setPname(pname);
//			person.setPwd(pwd);
//			
//			String status=perService.login(person);
//			System.out.println(status);
			
			Integer aNo=Integer.parseInt(ano);
			Person person=perService.getPerson(aNo);
			System.out.println("Person received successfully");
			
			if(person!=null)
			{
				String actualPassword=person.getPwd();
				System.out.println(actualPassword);
				String actualName=person.getPname();
				System.out.println(actualName +"... "+ actualPassword );
				
				
				if(actualPassword.equalsIgnoreCase(pwd) && actualName.equalsIgnoreCase(pname))
				{
					System.out.println("control is coming here..1");
					HttpSession session=request.getSession();
					System.out.println("control is coming here..2");
					session.setAttribute("person", person);
					System.out.println("control is coming here..3");
					rd=request.getRequestDispatcher("../accountPage.html");
					System.out.println("control is coming here..4");
					rd.forward(request, response);
					System.out.println("control is coming here..5");
				}
				else
				{
					rd=request.getRequestDispatcher("../failureAfterLogin.html");
					rd.forward(request, response);
		
				}
			}
			else
			{
				System.out.println("person is null");
			}
		}
		if(requestURI.endsWith("deposit"))
		{
			String amount=request.getParameter("amount");
			HttpSession session= request.getSession(false);
			
			Person person=(Person)session.getAttribute("person");
			//Integer aNo=person.getaNo();
			
			Double aBalance=person.getAmount();
			Double updatedBalance=aBalance+Double.valueOf(amount);
			String status=perService.deposit(person, updatedBalance);
			System.out.println(status);
			
			if(status.equals("success"))
			{
				rd=request.getRequestDispatcher("../depositS.html");
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("../depositF.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("withdraw"))
		{
			String amount=request.getParameter("amount");
			HttpSession session=request.getSession(false);
			
			Person person=(Person)session.getAttribute("person");
			//Integer aNo=person.getaNo();
			Double aBalance=person.getAmount();
			Double updatedBalance=aBalance-Double.valueOf(amount);
			String status=perService.deposit(person, updatedBalance);
			System.out.print(status);
			if(status.equals("success"))
			{
				rd=request.getRequestDispatcher("../withdrawS.html");
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("../withdrawF.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("delete"))
		{
			//String ano=request.getParameter("ano");
			//String ano=request.getParameter("ano");
			HttpSession session=request.getSession(false);
			Person person=(Person)session.getAttribute("person");
			Integer ano=person.getaNo();
			
			String status=perService.deleteByaNo(person);
			
			System.out.println(status);
			
			if(status.equals("success"))
			{
				rd=request.getRequestDispatcher("../deleteS.html");
				rd.forward(request, response);
			}
			else if(status.equals("failure"))
			{
				rd=request.getRequestDispatcher("../deleteF");
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("../notFound.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("editform"))
		{
			System.out.println("Inside editform");
			HttpSession session=request.getSession(false);
			Person person=(Person)session.getAttribute("person");
			Integer ano=person.getaNo();
			
			String status=perService.updateByNo(person);
			if(person!=null)
			{
				response.setContentType("text/html");
				
				//displaying  editpage using html
				
				PrintWriter out=response.getWriter();
				out.println("<html><head><title>OUTPUT</title></head>");
				out.println("<body bgcolor='sky blue'>");
				out.println("<br/><br/><br/>");
				out.println("<form method='post' action='./update'>");
				out.println("<table align='center'>");
				out.println("<tr><th>ANo</th><td>"+person.getaNo()+"</td></tr>");
				out.println("<input type='hidden' name='ano' value='"+person.getaNo()+"'/");
				out.println("<tr><th>Name</th><td><input type='text' name='pname' value='"+person.getPname()+"'/></td></tr>");
				out.println("<tr><th>Mother's Name</th><td><input type='text' name='mname' value='"+person.getMname()+"'/></td></tr>");
				out.println("<tr><th>Father's Name</th><td><input type='text' name='fname' value='"+person.getFname()+"'/></td></tr>");
				out.println("<tr><th>Age</th><td><input type='text' name='page' value='"+person.getPage()+"'/></td></tr>");
				out.println("<tr><th>Mobile Number</th><td><input type='text' name='mno' value='"+person.getMno()+"'/></td></tr>");
				out.println("<tr><th>Type of Account</th><td><input type='text' name='atype' value='"+person.getAtype()+"'/></td></tr>");
				out.println("<tr><th>Address</th><td><input type='text' name='paddr' value='"+person.getPaddr()+"'/></td></tr>");
				out.println("<tr><th>Password</th><td><input type='password' name='pwd' value='"+person.getPwd()+"'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			}
			else
			{
				rd=request.getRequestDispatcher("../notFoundUpdate.html");
				rd.forward(request, response);
			}
			
		}
		if(requestURI.endsWith("update"))
		{
			HttpSession session=request.getSession(false);
			Person person=(Person)session.getAttribute("person");
			
			//String ano=request.getParameter("ano");
			
			Integer ano=person.getaNo();
			String pname=request.getParameter("pname");
			String mname=request.getParameter("mname");
			String fname=request.getParameter("fname");
			//Integer page=person.getPage();
			String page=request.getParameter("page");
			//Double mno=person.getMno();
			String mno=request.getParameter("mno");
			String atype=request.getParameter("atype");
			String paddr=request.getParameter("paddr");
			String pwd=request.getParameter("pwd");
			
			person=new Person();
			person.setaNo(ano);
			person.setPname(pname);
			person.setMname(mname);
			person.setFname(fname);
			person.setPage(Integer.parseInt(page));
			//person.setMno(Double.parseDouble(mno));
			person.setMno(mno);
			person.setAtype(atype);
			person.setPaddr(paddr);
			person.setPwd(pwd);
			
			String status=perService.updateByNo(person);
			if(status.equals("success"))
			{
				rd=request.getRequestDispatcher("../updateS.html");
				rd.forward(request, response);
			}
			else if(status.equals("failure"))
			{
				rd=request.getRequestDispatcher("../updateF.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("accdetails"))
		{
			HttpSession session=request.getSession();
			Person person=(Person)session.getAttribute("person");
			Integer ano=person.getaNo();
			 
			person=perService.findByaNo(person);
			
			if(person!=null)
			{
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<html><head><title>Account Details</title></head>");
				out.println("<body bgcolor='sky blue'>");
				out.println("<br/><br/><br/><br/>");
				out.println("<table align='center' border='1'>");
				out.println("<tr>");
				out.println("<th>Account No</th>");
				out.println("<th>Account Holder Name</th>");
				out.println("<th>Mother's Name</th>");
				out.println("<th>Father's Name</th>");
				out.println("<th>Age</th>");
				out.println("<th>Mobile Number</th>");
				out.println("<th>Account No</th>");
				out.println("<th>Account Type</th>");
				out.println("<th>Address</th>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>"+person.getaNo()+"</td>");
				out.println("<td>"+person.getPname()+"</td>");
				out.println("<td>"+person.getMname()+"</td>");
				out.println("<td>"+person.getFname()+"</td>");
				out.println("<td>"+person.getPage()+"</td>");
				out.println("<td>"+person.getMno()+"</td>");
				out.println("<td>"+person.getAtype()+"</td>");
				out.println("<td>"+person.getPaddr()+"</td>");
				out.println("<td>"+person.getPwd()+"</td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			}
			else
			{
				rd=request.getRequestDispatcher("../notfoundaccdetails.html");
				rd.forward(request, response);
			}
		}
		if(requestURI.endsWith("balanceEnq"))
		{
			HttpSession session=request.getSession();
			Person person=(Person)session.getAttribute("person");
			Integer ano=person.getaNo();
			 
			person=perService.balance(person);
			if(person!=null)
			{
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<html><head><title>Account Details</title></head>");
				out.println("<body bgcolor='sky blue'>");
				out.println("<br/><br/><br/><br/>");
				out.println("<table align='center' border='1'>");
				out.println("<tr>");
				out.println("<th>Your Account Balance is</th>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>"+person.getBalance()+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				out.close();
				
			}
			else
			{
				rd=request.getRequestDispatcher("../notfoundbalance.html");
				rd.forward(request, response);
			}
		}
	}
}
