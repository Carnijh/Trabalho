package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import DAO.UsuarioDAO;

public class DAO implements UsuarioDAO {

    // Pega senha do danco de dados
    String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    String DB_URL = "jdbc:derby://localhost:1527/usuarios";

    @Override
    public boolean login(Usuario a, String usuario, String senha) {

        // Database credentials
        Connection con = null;
        Statement stmt = null;
        String resp = null;
        boolean result = false;

        // Set response content type
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            con = DriverManager.getConnection(DB_URL, usuario, senha);
            // Execute SQL query
            stmt = (Statement) con.createStatement();
            String sql;
            sql = "SELECT login, senha FROM APP.USUARIOS where upper(login) = '" + a.getNome().toUpperCase() + "' and senha='" + a.getSenha() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            if (rs.next()) {
                result = true;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            //Handle errors for JDBC
            //throw new ServletException(e);
            resp = e.getMessage();
            try {
                throw new ServletException(e);
            } catch (ServletException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            //Handle errors for Class.forName
            //throw new ServletException(e);
            resp = e.getMessage();
            try {
                throw new ServletException(e);
            } catch (ServletException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            //System.out.printf(resp);
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                try {
                    throw new ServletException(e);
                } catch (ServletException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }// nothing we can do// nothing we can do
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                try {
                    throw new ServletException(e);
                } catch (ServletException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }//end finally try

        if (result) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Usuario> listarClientes(String usuario, String senha) {
        List<Usuario> lista = new ArrayList<Usuario>();

  
        Statement stmt = null;
        Connection conn = null;
        String resp = null;
        boolean result = false;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, usuario, senha);
            // Execute SQL query
            stmt = (Statement) conn.createStatement();
            String sql;
            sql = "SELECT login FROM APP.USUARIOS ORDER BY login";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario a = new Usuario();
                a.setNome(rs.getString("usuario"));
                lista.add(a);
            }

            // Extract data from result set
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            //Handle errors for JDBC
            //throw new ServletException(e);
            resp = e.getMessage();
            try {
                throw new ServletException(e);
            } catch (ServletException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            //Handle errors for Class.forName
            //throw new ServletException(e);
            resp = e.getMessage();
            try {
                throw new ServletException(e);
            } catch (ServletException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            //System.out.printf(resp);
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                try {
                    throw new ServletException(e);
                } catch (ServletException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }// nothing we can do// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                try {
                    throw new ServletException(e);
                } catch (ServletException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return lista;
    }
}
