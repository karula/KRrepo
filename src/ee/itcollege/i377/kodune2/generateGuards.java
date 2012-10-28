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
 * Servlet implementation class generateGuards
 */
@WebServlet(
		urlPatterns = { "/generateGuards" }, 
		initParams = { 
				@WebInitParam(name = "count", value = "")
		})
public class generateGuards extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public generateGuards() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//try {
		
		int x = Integer.parseInt(request.getParameter("count"));
		for (int i = 0; i<x;i++){
		String html = "<h2>Sisesta Valvur:</h2> <form method=\"post\" action=\"generateGuards?count="+x+"\" name=\"sisestamine\"> valvuri eesnimi: <input type=\"text\" name=\"eesnimi"+i+"\"> <br/>valvuri perenimi: <input type=\"text\" name=\"perenimi"+i+"\"> <br/> valvuri vanus: <input type=\"text\" name=\"vanus"+i+"\"> <br/>";
		out.write(html);
		}
		out.write("<input type=\"submit\" value=\"salvesta\" /></form>");
		
		
		
		
		
		
		
		
		//String statement = "  CREATE TABLE GUARD(id INTEGER identity,firstname VARCHAR(50) not null,surname VARCHAR(50) not null,age VARCHAR(3) not null,PRIMARY KEY (id))";

	        
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int x = Integer.parseInt(request.getParameter("count"));
		String vastus = "";
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:C:/kodune/GUARDDB");
		    Statement s = conn.createStatement();
		    for (int i = 0; i<x;i++){
		    s.execute("insert into valvur (firstname, surname, age) values ('"+request.getParameter("eesnimi"+i)+"', '"+request.getParameter("perenimi"+i)+"', '"+request.getParameter("vanus"+i)+"')");
		    vastus = vastus + request.getParameter("eesnimi"+i)+"\n";
		    conn.close();
		}
		    out.write(vastus);
		    
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//out.write("org.hsqldb.jdbcDriver");
			out.write(e.getMessage());
		}
		
		//out.write(request.getParameter("eesnimi0"));
		
		
		
		
	}

}
