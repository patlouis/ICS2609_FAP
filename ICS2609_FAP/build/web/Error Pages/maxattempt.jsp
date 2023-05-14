

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

        <!-- Document Title -->
        <title>Max Attempt Error</title>

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
                Submitted by Patrick Louis T. Rivera, for ICS2609 Machine Problem 4.
            </footer>
        </div>

        <br>
    </body>
</html>
