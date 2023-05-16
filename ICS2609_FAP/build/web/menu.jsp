
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Inventory"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Inventory inventory = new Inventory();
    ArrayList<Product> pList = inventory.getProductInvetory();
    Product pOffer = inventory.getProductOffer();
%>

<!DOCTYPE html>
<html lang="en">
    <!-- Head -->
    <head>
        <!-- Metas -->
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=0.9" />
        <link rel="icon" href="Images/Website Logo.png" type="image/x-icon">

        <!-- Document Title -->
        <title>Menu</title>

        <!-- Styles -->
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>

    </head>

    <!-- Body -->
    <body>
        <!-- Navigation -->
        <nav id="navbar">
            <!-- Navbar list -->
            <ul id="nav-list">
                <!-- Landing Page -->
                <li class="nav-item">
                    <a href="landing.jsp">Home</a>
                </li>

                <!-- Products -->
                <li class="nav-item">
                    <a href="#products">Products</a>
                </li>

                <!-- Login -->
                <li class="nav-item">
                    <a href="login.jsp">Login</a>
                </li>
            </ul>
        </nav>

        <div class="container">
            <!-- Welcome section -->
            <section id="welcome-section">

                <!-- blob -->
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="960"
                    height="540"
                    class="blob"
                    >
                <path
                    d="M646.22 141.57c26.5 45 19.8 104.7 13.6 158.1-6.3 53.3-12.1 100.5-38.6 130.5s-73.7 42.8-113.1 35.2c-39.5-7.7-71.3-35.9-113-65.9-41.7-30-93.2-61.8-99.8-100.2-6.6-38.4 31.7-83.4 73.4-128.4s86.7-90 139-97.3c52.3-7.3 112 23 138.5 68"
                    />
                </svg>

                <!-- Title -->
                <h1>MENU</h1>

                <!-- Subtitle -->
                <p>Fry Me to the Moon: Discover a world of flavors.</p>

                <!-- Scroll icon -->
                <a href="#products">
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        width="24"
                        height="24"
                        class="scroll-icon"
                        >
                    <path fill="none" d="M0 0h24v24H0z" />
                    <path
                        d="M12 2c5.52 0 10 4.48 10 10s-4.48 10-10 10S2 17.52 
                        2 12 6.48 2 12 2zm0 18c4.42 0 8-3.58 8-8s-3.58-8-8-8-8 
                        3.58-8 8 3.58 8 8 8zm1-8h3l-4 4-4-4h3V8h2v4z"
                        />
                    </svg>
                </a>

            </section>

            <section id="products">
                <div class="small-container">
                    <h2 class="title">Products</h2>        
                    <br>
                    <div class="grid-container">
                        <% if (!pList.isEmpty()) {
                                for (Product p : pList) {%>
                        <div class="grid-item">
                            <img class="feat-prod-img" src="<%= p.getImage()%>" alt="<%= p.getName()%>" width="200" height="200"/>
                            <div class="product-info">
                                <h4><%= p.getName()%></h4>
                                <p>₱<%= p.getPrice()%></p>
                                <br>
                                <style>
                                    a {
                                        text-decoration: none; /* Removes underline */
                                        color: white; /* Changes color to white */
                                    }
                                </style>
                                <button class="btn">
                                    <a href="login.jsp">Add to cart</a>
                                </button>
                            </div>
                        </div>
                        <% }
                            }%>
                    </div>
                </div>
            </section>


            <br/>&nbsp;<br/><br>

            <!-- Footer -->
            <footer id="footer">
                Copyright © 2023 Fry Me to the Moon. All rights reserved.
            </footer>
        </div>
    </body>
</html>
