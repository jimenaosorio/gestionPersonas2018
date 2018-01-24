/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controller;

import cl.model.IUtilidad;
import cl.model.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
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

    @Inject
    private IUtilidad utilidad;

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
        
        List<Persona> list = (List<Persona>) 
                            getServletContext().getAttribute("data");
        
        Persona p = utilidad.buscar(rut, list);
        p.setActivo(activo.equalsIgnoreCase("Si"));
        getServletContext().setAttribute("data", list);
        response.sendRedirect("personas.jsp");
        
    }
    
    

    protected void procesaRut(HttpServletRequest request, 
            HttpServletResponse response,
            String boton)
            throws ServletException, IOException {
        List<Persona> list = (List<Persona>)
                getServletContext().getAttribute("data");
        
        Persona p = utilidad.buscar(boton, list);
        request.setAttribute("persona", p);
        request.getRequestDispatcher("editarPersona.jsp").forward(request, response);
        
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rut = request.getParameter("rut");
        String clave = request.getParameter("clave");

        List<Persona> list = (List<Persona>) getServletContext().getAttribute("data");

        Persona p = utilidad.loguear(rut, clave, list);

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
