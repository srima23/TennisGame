package tennisMatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.learning.hello.controller.tennisMatchController;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TennisServlet")
public class tennisMatchServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	private Connection cnx;
	
	@Override
	  public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx =  DriverManager.getConnection("jdbc:mysql://localhost:3306/persistence","Ameena","Ameena");
			}
	    catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	    application = JakartaServletWebApplication.buildApplication(getServletContext());
	    final WebApplicationTemplateResolver templateResolver = 
	        new WebApplicationTemplateResolver(application);
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setSuffix(".html");
	    templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		TennisMatch match = new TennisMatch();
		String p1 = req.getParameter("player1");
		String p2 = req.getParameter("player2");
		String player1 = req.getParameter("playerPoint1");
		Boolean point1;
		int pointId=1;
		int matchId=1;
		String winner = "";
		if(match.getCurrentGamePlayer1Points()!=0)
			winner = "player1";
		else
			winner = "player2";
		
		if(player1.equals("True"))
			point1 = true;
		else
			point1 = false;
		
		match.update(point1);
		
		Statement stmt = null;
	    PreparedStatement insert = null;
		PreparedStatement update = null;
		
		String insertPoint = "INSERT INTO Point(pointID,matchID,winner,comment) VALUES (?,?,?,?)";
	    String updateName = "UPDATE Point SET pointID=?,matchID=?,winner=?,comment=?;";
		
			try {
				insert = cnx.prepareStatement(insertPoint);
				update = cnx.prepareStatement(updateName);
				  insert.setInt(1, pointId);
					insert.setInt(2, matchId);
					insert.setString(3, winner);
					insert.setString(4, "initial");
					insert.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		var out = resp.getWriter();
		final IWebExchange webExchange = 
		        this.application.buildExchange(req, resp);
		final WebContext ctx = new WebContext(webExchange);
		    
		ctx.setVariable("player1",p1);
		ctx.setVariable("player2",p2);
		ctx.setVariable("point1",match.getCurrentGamePlayer1Points());
		ctx.setVariable("point2",match.getCurrentGamePlayer2Points());
		
		templateEngine.process("tennisForm", ctx, out);
	}
	
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    final IWebExchange webExchange = this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);
	    templateEngine.process("tennisForm", ctx, resp.getWriter());
	  }
	

}
	
	