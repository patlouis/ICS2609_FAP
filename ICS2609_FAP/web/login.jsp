
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <!-- Head -->
    <head>
        <!-- Metas -->
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Document Title -->
        <title>Log in</title>

        <!-- Styles -->
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>

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

    <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br>

    <!-- Body -->
    <body>

        <div class="container">

            <!-- Section contact -->
            <section id="contact">
                <!-- Section title -->
                <div align="center">
                    <h1>Log in</h1>
                    <h5>Please enter your username and password</h5>
                </div>

                <!-- Form -->
                <form action="LoginServlet" method="post" id="contact-form">
                    <!-- Name -->
                    <div class="form-group">
                        <label for="name">Username</label>
                        <input
                            type="email"
                            id="name"
                            name="user"
                            placeholder="Enter your username..."
                            />
                    </div>

                    <!-- Email -->
                    <div class="form-group">
                        <label for="email" class="required">Password</label>
                        <input
                            type="password"
                            id="email"
                            name="pass"
                            placeholder="Enter your password..."
                            required
                            />

                        <!-- Button -->
                        <button type="submit" id="submit" class="btn">Login</button>

                </form>
            </section>

            <div align="center">
                <h5>New user? <a href="signup.jsp">Create an account</a></h5>
            </div>

            <br>

            <div align="center">
                <h5>Return to <a href="landing.jsp">Homepage</a></h5>
            </div>

            <br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br />&nbsp;<br /><br>

            <!-- Footer -->
            <footer id="footer">
                Copyright 2023 © All rights reserved.
            </footer>
        </div>
    </body>
</html>
