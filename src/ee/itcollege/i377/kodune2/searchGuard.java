package ee.itcollege.i377.kodune2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchGuard
 */
@WebServlet(
		urlPatterns = { "/searchGuard" }, 
		initParams = { 
				@WebInitParam(name = "name", value = ""), 
				@WebInitParam(name = "age", value = "")
		})
public class searchGuard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchGuard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		 try {
				Class.forName("org.hsqldb.jdbcDriver");
				Connection conn = DriverManager.getConnection("jdbc:hsqldb:C:/kodune/GUARDDB");
				Statement s = conn.createStatement();
				String queryString = "select * FROM valvur ";
				if (request.getParameter("name")!=null  )
				{
					queryString += "where firstname = '" +request.getParameter("name") +"'";
					if (request.getParameter("age")!=null)
					{
						queryString += "AND age = '"+request.getParameter("age")+"'";
					}
				}else if (request.getParameter("age")!=null)
				{
					queryString += "where age = '" +request.getParameter("age") +"'";
				}
				
				
				
				
				
				
				
				//conn.close();
				
		 ResultSet rs = s.executeQuery(queryString);
		 
		   while(rs.next()) {
		        Guard g = new Guard();
		        
		        g.setFirstname(rs.getString(2));
		        g.setSurname(rs.getString(3));
		        g.setAge(rs.getString(4));
		        out.write(g.firstname+" , "+g.surname+" , "+g.age +"\n");
		   }
		   
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
