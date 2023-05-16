
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    session.invalidate();
%>

<!DOCTYPE html>
<html lang="en">
    <!-- Head -->
    <head>
        <!-- Metas -->
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="../Images/Website Logo.png" type="image/x-icon">

        <!-- Document Title -->
        <title>SQL Exception Error</title>

        <!-- Styles -->
        <link href="../css/styles.css" rel="stylesheet" type="text/css"/>

        <style>
            a:link {
                color: green;
                background-color: transparent;
                text-decoration: none;
            }

            a:visited {
                color: pink;
                background-color: transparent;
                text-decoration: none;
            }

            a:hover {
                color: white;
                background-color: transparent;
                text-decoration: underline;
            }

            a:active {
                color: yellow;
                background-color: transparent;
                text-decoration: underline;
            }
        </style>
    </head>

    <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br />

    <!-- Body -->
    <body>
        
        <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br />

        <div class="container">

            <!-- Section contact -->
            <section id="contact">
                <!-- Section title -->
                <div align="center">
                    <h1>SQL Exception Error</h1>
                </div>

                <div align="center">
                <a href="../login.jsp">Return to login page</a>
                </div>
            </section>

            <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br />

            <!-- Footer -->
            <footer id="footer">
                Copyright © 2023 Fry Me to the Moon. All rights reserved.
            </footer>
        </div>

        <br>
    </body>
</html>
