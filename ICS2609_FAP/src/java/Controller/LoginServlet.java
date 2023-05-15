package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Security;

public class LoginServlet extends HttpServlet {

    // CONNECTION
    Connection conn;

    // COUNTER
    private int counter = 1;

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

    private static final String SELECT_QUERY = "SELECT * FROM USERS WHERE EMAIL =? AND PASSWORD =? ";
    private static final String VIEW_QUERY = "SELECT EMAIL, USERROLE FROM USERS WHERE EMAIL =? AND PASSWORD =? ";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            // CONNECTION IS NOT NULL
            if (conn != null) {
                HttpSession session = request.getSession();
                // USERNAME AND PASSWORD IS RETRIEVED FROM HTML FORM, AND STORED
                String username = request.getParameter("user");
                String password = request.getParameter("pass");
                // ENCRYPTED PASSSWORD
                String encrypted = Security.encrypt(password);
                // PREPARED STATEMENT
                PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
                ps.setString(1, username);
                ps.setString(2, encrypted);
                ResultSet rs = ps.executeQuery();

                // USERNAME AND PASSWORD MATCHES
                if (rs.next()) {

                    // RETRIEVE AND DECRYPT PASSWORD FROM DB TABLE
                    String pass_db = rs.getString("PASSWORD");
                    String decrypted = Security.decrypt(pass_db);
                    // COMPARE DECRYPTED DB PASSWORD FROM INPUT PASSWORD
                    boolean correct_pass = decrypted.equals(password);

                    // RETRIEVES USERROLE FROM DB TABLE
                    String user_name = rs.getString("EMAIL");
                    String user_role = rs.getString("USERROLE");
                    String admin = "admin";
                    String customer = "customer";
                    // VERIFY IF ADMIN/GUEST
                    boolean admin_user = user_role.equals(admin);
                    boolean customer_user = user_role.equals(customer);
                    // USERNAME STRING
                    request.setAttribute("username", username);

                    // ADMIN LOGIN
                    if (correct_pass && admin_user) {
                        session.setAttribute("username", username);
                        // ADD RECORDS MODULE
                        request.getRequestDispatcher("admin.jsp").forward(request, response);

                        // CUSTOMER LOGIN
                    } else if (correct_pass && customer_user) {
                        session.setAttribute("username", username);
                        // PREPARED STATEMENT
                        PreparedStatement prep = conn.prepareStatement(VIEW_QUERY);
                        prep.setString(1, username);
                        prep.setString(2, encrypted);
                        ResultSet res = ps.executeQuery();
                        request.setAttribute("results", res);

                        // DISPLAY RECORDS (USERNAME/EMAIL, USERROLE)
                        request.getRequestDispatcher("home.jsp").forward(request, response);

                    } else {
                        // ERROR
                        response.sendRedirect("Error Pages/Error 503.jsp");
                    }

                    // USERNAME AND PASSWORD DOES NOT MATCH, COUNTER IS NOT EQUALS TO 3
                } else if (!rs.next() && counter != 3) {
                    response.sendRedirect("Error Pages/incorrect.jsp");
                    // INCREMENTS BY 1
                    counter++;
                    // IF COUNTER IS EQUALS TO 3
                } else if (counter == 3) {
                    // MAX ATTEMPT ERROR
                    response.sendRedirect("Error Pages/maxattempt.jsp");
                    counter = 1;
                }
                // CONNECTION IS NULL
            } else {
                response.sendRedirect("Error Pages/Error 503.jsp");
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
