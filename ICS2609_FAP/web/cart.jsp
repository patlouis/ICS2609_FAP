
<%@page import="Model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Cart"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if (session.getAttribute("username") == null) {
        response.sendRedirect("landing.jsp");
    }
    if (session.getAttribute("cart-list") == null) {
        ArrayList<Cart> sessionCart = new ArrayList();
        session.setAttribute("cart-list", sessionCart);
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    double cartTotal = 0;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Website Icon -->
        <link rel="icon" href="Images/Website Logo.png" type="image/x-icon">
        <!-- Styles -->
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <title>Cart</title>
    </head>
    <body>
        <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br />
        <style>
            a {
                text-decoration: none; /* Removes underline */
                color: white; /* Changes color to white */
            }
        </style>
        <div class="container">
            <div align="center">
                <h1 class="title cart-title">My Cart</h1>
                <button class="btn">
                    <a href="order.jsp#products">Back to menu</a>
                </button>
            </div>
            <br><br>
            <%
                if (!cart_list.isEmpty()) {
                    for (Cart p : cart_list) {
                        cartTotal += Double.parseDouble(p.getPrice().replaceAll(",", ""));
            %>
            <div class="col-4 remove">
                <h5><%= p.getName() + ", " + p.getPrice()%></h5> 
                <button class="btn">
                    <a href="RemoveFromCart?index=<%= cart_list.indexOf(p)%>">Remove</a>
                </button>
            </div>
            <br>
            <%
                }
            } else {
            %>

            <br />&nbsp;<br />

            <h5 class="empty-text">Your cart is empty. Add something from the menu.</h5>

            <%
                }
            %>

            <br>

            <h3>Total: ₱<%= cartTotal%></h3> 

            <br />&nbsp;<br />

            <form action="CustomerReceipt" method="POST">
                <!--hidden attributes for receipt-->
                <input type="hidden" name="total" value="<%= cartTotal%>">
                <%
                    session.setAttribute("order", cart_list);
                %>
                <input type="submit" value="Place Order" class="btn"/>
            </form>

        </div>
        <br />&&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br />
    </body>
</html>
