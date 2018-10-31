/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Valida extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    public String usuario;
    public String senha;
    
    public void init(){
        usuario=getInitParameter("usuario");
        senha=getInitParameter("senha");
    }
     */
    // Pega senha do danco de dados
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);

        // Pega usuário e senha para acessar o BD
        String usuario = (String) this.getServletContext().getInitParameter("usuario");
        String senha = (String) this.getServletContext().getInitParameter("senha");

        // Pega usuário e senha recebidos do formulário
        String usuarioForm = request.getParameter("usuario");
        String senhaForm = request.getParameter("senha");

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
            sql = "SELECT login, senha FROM APP.USUARIOS where upper(login) = '" + usuarioForm.toUpperCase() + "' and senha='" + senhaForm + "'";

            ResultSet rs = stmt.executeQuery(sql);
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

        /*
        String aplicacao = (String) this.getServletContext().getInitParameter("aplicacao");
        String url = (String) this.getInitParameter("url");
        
        try (PrintWriter out = response.getWriter()) {
         */
        if (result) {
            session.setAttribute("loggedIn","true");
            response.sendRedirect("menu.jsp");
        } else {
            request.setAttribute("mensagem", new String("erro"));
            //response.sendRedirect("index.jsp");
            request.getRequestDispatcher("./index.jsp").forward(request,response);
        }
        /*
        }
         */
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