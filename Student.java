package SchoolAssissementstudent;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Student")
public class Student extends GenericServlet{

	  @Override
	  public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
	    
	    String studentid=req.getParameter("studentId");
	    String grade=req.getParameter("grade");
	    String telugu=req.getParameter("telugu");
	    String hindi=req.getParameter("hindi");
	    String english=req.getParameter("english");
	    String maths=req.getParameter("maths");
	    String science=req.getParameter("science");
	    String social=req.getParameter("social");
	    int marks = Integer.parseInt(telugu) + Integer.parseInt(hindi) + Integer.parseInt(english)
	        + Integer.parseInt(maths) + Integer.parseInt(science) + Integer.parseInt(social);
	        float percentage = (marks / 6.0f);
	    
	String url="jdbc:mysql://localhost:3306/teca43?user=root&password=12345";
	String insert="insert into student1( grade, Telugu, Hindi, English, Maths, Science, social, marks, percentage)"+"values(?,?,?,?,?,?,?,?,?)";
	    
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection connection=DriverManager.getConnection(url);
	      PreparedStatement ps=connection.prepareStatement(insert);
	      
	      ps.setString(1, grade);
	      ps.setString(2, telugu);
	      ps.setString(3, hindi);
	      ps.setString(4, english);
	      ps.setString(5, maths);
	      ps.setString(6, science);
	      ps.setString(7, social);
	      ps.setInt(8, marks);
	      ps.setFloat(9, percentage);
	    PrintWriter writer = resp.getWriter();
	      int num=ps.executeUpdate();
	      if (num>0) {
	        RequestDispatcher dispatcher = req.getRequestDispatcher("out.html");
	        dispatcher.include(req, resp);
	        writer.println("ok");
	       
	      } 
	      
	      
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    
	    
	  }

	}

