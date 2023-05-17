

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Destroys the session object --%>
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
        <!-- Website Icon -->
        <link rel="icon" href="../Images/Website Logo.png" type="image/x-icon">

        <!-- Document Title -->
        <title>Max Attempt Reached</title>

        <!-- Styles -->
        <link href="../css/styles.css" rel="stylesheet" type="text/css"/>

    </head>

    <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br>

    <!-- Body -->
    <body>

        <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br />

        <div class="container">

            <!-- Section contact -->
            <section id="contact">
                <!-- Section title -->
                <div align="center">
                    <h1>Sorry, you have reached the limit of 3 tries, good bye!</h1>
                </div>

            </section>

            <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br />

            <!-- Footer -->
            <footer id="footer">
                Copyright © 2023 Fry Me to the Moon. All rights reserved.
            </footer>
        </div>

        <br>
    </body>
</html>
