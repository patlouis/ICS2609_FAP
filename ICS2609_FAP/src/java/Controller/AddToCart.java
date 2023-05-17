package Controller;

import Model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {

            ArrayList<Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String price = request.getParameter("price");

            Cart cartProduct = new Cart();
            cartProduct.setId(id);
            cartProduct.setName(name);
            cartProduct.setPrice(price);
            cartProduct.setQuantity(1);

            // Creates session for cart
            HttpSession session = request.getSession();
            ArrayList<Cart> sessionCart = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (sessionCart == null) {
                cartList.add(cartProduct);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("order.jsp");
            } else {
                cartList = sessionCart;
                boolean exist = false;
                for (Cart c : sessionCart) {
                    if (c.getId() == id) {
                        exist = true;
                        response.sendRedirect("order.jsp");
                    }
                }
                if (!exist) {
                    cartList.add(cartProduct);
                    response.sendRedirect("order.jsp");
                }
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
