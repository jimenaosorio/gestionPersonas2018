/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.beans.PersonaBeanLocal;

import cl.model.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roman
 */
@WebServlet(name = "ControladorServlet", urlPatterns = {"/control.do"})
public class ControladorServlet extends HttpServlet {

   
    
    @EJB
    private PersonaBeanLocal service;
    
    //Definición de instancias para MDB
    @Resource(mappedName="jms/QueueFactory")
    QueueConnectionFactory factory;
    
    @Resource(mappedName="jms/Queue") //importar desde javax.jms.Queue;
    private Queue queue;
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String boton = request.getParameter("boton");
        switch (boton) {
            case "login":
                login(request, response);
                break;
            case "registro":
                registro(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
                
            default:
                procesaRut(request, response, boton);
        }
    }
    
    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rut = request.getParameter("rut");
        String activo = request.getParameter("activo");
        
        service.editar(rut,activo.equalsIgnoreCase("si"));
      
        response.sendRedirect("personas.jsp");
        
    }
    
    

    protected void procesaRut(HttpServletRequest request, 
            HttpServletResponse response,
            String boton)
            throws ServletException, IOException {

        
        Persona p=service.buscar(boton);
        request.setAttribute("persona", p);
        request.getRequestDispatcher("editarPersona.jsp").forward(request, response);
        
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rut = request.getParameter("rut");
        String clave = request.getParameter("clave");



        Persona p=service.logear(rut, clave);
        
        if (p == null) {
            request.setAttribute("msg", "Hubo un error al iniciar sesion :(");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (p.getPerfil().equalsIgnoreCase("administrador")) {
            request.getSession().setAttribute("admin", p);
            response.sendRedirect("inicio.jsp");
        } else {
            request.getSession().setAttribute("person", p);
            response.sendRedirect("inicio.jsp");
        }
    }

    protected void registro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con=factory.createConnection();
            Session sesion=con.createSession(false,Session.AUTO_ACKNOWLEDGE);
            MessageProducer msgp = sesion.createProducer(queue);
            MapMessage mensaje=sesion.createMapMessage();
            mensaje.setString("mensaje", "Hello from serlet");
            msgp.send(mensaje);
            msgp.close();
            sesion.close();
            con.close();
        } catch (JMSException ex) {
            Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Recuperar el formulario
        String rut=request.getParameter("rut");
        String nombre=request.getParameter("nombre");
        String mail=request.getParameter("mail");
        String clave1=request.getParameter("clave1");
        String clave2=request.getParameter("clave2");
        String errores="";
        //Falta validar
        
        //Si no hay errores
        if(errores.equals("")){
            Persona p=new Persona(rut,nombre,"persona",mail,clave1,true);
            service.add(p);
            //Redireccionar al index
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
