package Controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Security;
import nl.captcha.Captcha;

public class AdminServlet extends HttpServlet {

    // CONNECTION
    Connection conn;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            // JDBC CLASS NAME
            Class.forName(config.getInitParameter("jdbcClassName"));
            // System.out.println("jdbcClassName: " + config.getInitParameter("jdbcClassName"));
            // DB USERNAME
            String username = config.getInitParameter("dbUserName");
            // DB PASSWORD
            String password = config.getInitParameter("dbPassword");
            // DB URL
            StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getInitParameter("dbHostName"))
                    .append(":")
                    .append(config.getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getInitParameter("databaseName"));

            // CONNECTION
            conn = DriverManager.getConnection(url.toString(), username, password);

            // SQL EXCEPTION ERROR
        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
            // CLASSNOTFOUND EXCEPTION ERROR
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        }
    }

    private static final String INSERT_QUERY = "INSERT INTO users (Email, Password, UserRole) VALUES (?, ?, ?)";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // RESULT
        int result = 0;
        RequestDispatcher dispatcher = null;

        try {

            // CONNECTION IS NOT NULL
            if (conn != null) {
                // USERNAME, PASSWORD, AND USERROLE IS RETRIEVED FROM HTML FORM, AND STORED
                String username = request.getParameter("user");
                String password = request.getParameter("pass");
                String confirm = request.getParameter("confirm");
                String role = request.getParameter("role");
                // ENCRYPTED PASSWORD
                String encrypted = Security.encrypt(password);
                // PREPARED STATEMENT
                PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
                ps.setString(1, username);
                ps.setString(2, encrypted);
                ps.setString(3, role);

                // VERIFY IF USERROLE INPUT IS CORRECT
                boolean correctRole = role.equals("admin") || role.equals("customer");

                // GET THE SESSION OBJECT
                HttpSession session = request.getSession();

                // RETRIEVES THE CAPTCHA OBJECT FROM SESSION ATTRIBUTE
                Captcha captcha = (Captcha) session.getAttribute("captcha_solution");
                String answer = (String) session.getAttribute("answer");
                String answerStr = request.getParameter("captcha");

                // VERIFY IF CAPTCHA IS CORRECT
                boolean isCorrect = captcha.isCorrect(answerStr);

                // EXECUTE ADD RECORD IF CONDITION IS MET
                if (result >= 0 && confirm.equals(password) && correctRole) {
                    // CAPTCHA IS CORRECT
                    if (isCorrect) {
                        result = ps.executeUpdate();
                        request.setAttribute("status", "success");
                        dispatcher = request.getRequestDispatcher("login.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // INCORRECT ERROR
                        response.sendRedirect("Error Pages/incorrect.jsp");
                    }
                } else {
                    // ERROR
                    response.sendRedirect("Error Pages/incorrect.jsp");
                    request.setAttribute("status", "failed");
                }
            }

            // SQL EXCEPTION ERROR
        } catch (SQLException sqle) {
            response.sendRedirect("Error Pages/sqlexception.jsp");
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
