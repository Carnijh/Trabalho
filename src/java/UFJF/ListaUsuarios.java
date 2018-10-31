package UFJF;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author carni
 */
public class ListaUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        if( session.getAttribute("loggedIn") != null ) {
            // Pega usuário e senha para acessar o BD
            String usuario = (String) this.getServletContext().getInitParameter("usuario");
            String senha = (String) this.getServletContext().getInitParameter("senha");

            String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
            String DB_URL = "jdbc:derby://localhost:1527/usuarios";
            // Database credentials
            Connection conn = null;
            Statement stmt = null;
            String resp = null;
            boolean result = false;

            // Set response content type
            try {
                // Register JDBC driver
                Class.forName(JDBC_DRIVER);
                // Open a connection
                conn = DriverManager.getConnection(DB_URL, usuario, senha);
                // Execute SQL query
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT login FROM APP.USUARIOS ORDER BY login ASC";

                ResultSet rs = stmt.executeQuery(sql);

                // Extract data jrom result set

                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Usuários Cadastrados</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div align='center'>");
                    out.println("<h1>Usuários Cadastrados</h1>");
                    while (rs.next()) {
                        out.println(rs.getString("login")+"<br>");
                    }
                    out.println("<form action='Controller' method='POST'>");
                    out.println("<button name='name' value='Menu' type='submit'> Voltar </button>");
                    out.println("</form>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                }

                // Extract data from result set
                if (rs.next()) {
                    result = true;
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                //Handle errors for JDBC
                //throw new ServletException(e);
                resp = e.getMessage();
                throw new ServletException(e);
            } catch (Exception e) {
                //Handle errors for Class.forName
                //throw new ServletException(e);
                resp = e.getMessage();
                throw new ServletException(e);
            }
        } else{
            response.sendRedirect("index.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
