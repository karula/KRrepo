package ee.itcollege.i377.kodune2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateGuard
 */
@WebServlet(
		urlPatterns = { "/updateGuard" }, 
		initParams = { 
				@WebInitParam(name = "id", value = ""), 
				@WebInitParam(name = "name", value = ""), 
				@WebInitParam(name = "age", value = "")
		})
public class updateGuard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateGuard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ID;
		PrintWriter out = response.getWriter();
		try{
		ID = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e)
		{
			out.write("PARAMEETER id ON KOHUSTUSLIK JA SEE PEAB OLEMA TAISARV");
			return;
		}
		
		  try {
				Class.forName("org.hsqldb.jdbcDriver");
				Connection conn = DriverManager.getConnection("jdbc:hsqldb:C:/kodune/GUARDDB");
			    Statement s = conn.createStatement();
			    String statementString="UPDATE VALVUR SET ";
			    
			    String name = request.getParameter("name");
			    String age = request.getParameter("age");
			    
			    if(name!=null && !name.isEmpty())
			    {
			    	statementString+="firstname = '"+name+"' ";
			    	if (age!=null && !age.isEmpty())
			    	{
			    		statementString+=", age = '"+age+"' ";
			    	}
			    }else if(age!=null && !age.isEmpty())
			    {
			    	statementString+= "age = '"+age+"' ";
			    }else
			    {
			    	out.write("POLE MIDAGI UUENDADA");
			    	conn.close();
			    	return;
			    }
			    
			    s.execute(statementString + " where id ="+ID);
			    conn.close();
			    out.write("UUENDATUD!");
			    
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				//out.write("org.hsqldb.jdbcDriver");
				out.write(e.getMessage());
				
			}
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
