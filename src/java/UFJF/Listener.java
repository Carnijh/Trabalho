package UFJF;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author carni
 */
public class Listener implements ServletContextListener, HttpSessionListener {
    
    ServletContext servletContext;
    int cont = 0;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.setAttribute("usuarios", Integer.toString(cont));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        cont += 1;
        servletContext.setAttribute("usuarios", Integer.toString(cont));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        cont -= 1;
        servletContext.setAttribute("usuarios", Integer.toString(cont));
    }
}
