/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UFJF;

import DAO.DAO;
import DAO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author carni
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session;
            String parametro;

            parametro = (String) request.getParameter("name");
            if (parametro == null) {
                parametro = "null";
            }

            switch (parametro) {
                case "null":
                    response.sendRedirect("index.jsp");
                    break;
                case "Login":
                    response.setContentType("text/html;charset=UTF-8");
                    
                    Usuario a = new Usuario();
                    a.setNome(request.getParameter("usuario"));
                    a.setSenha(request.getParameter("senha"));

                    String usuario = (String) this.getServletContext().getInitParameter("usuario");
                    String senha = (String) this.getServletContext().getInitParameter("senha");
                    
                    DAO um = new DAO();
                    boolean result = um.login(a, usuario, senha);
                    
                    if (result) {

                        session = request.getSession(true);

                        session.setAttribute("loggedIn", "true");
                        response.sendRedirect("menu.jsp");
                        /*request.setAttribute("parametro", "menu.jsp");
                        request.getRequestDispatcher("./menu.jsp").forward(request, response);*/

                    } else {
                        request.setAttribute("mensagem","erro");
                        request.getRequestDispatcher("./index.jsp").forward(request, response);
                    }
                    break;

                case "Menu":
                    response.sendRedirect("menu.jsp");
                    break;

                case "Usuarios":
                    session = request.getSession(true);
                    response.sendRedirect("./ListaUsuarios");
                    break;

                case "Sair":
                    session = request.getSession(true);
                    session.invalidate();
                    response.sendRedirect("./index.jsp");

                    break;

            }

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
