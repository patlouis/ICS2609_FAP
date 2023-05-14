
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
        <title>Admin</title>

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

    <br />&nbsp;<br /><br><br>

    <!-- Body -->
    <body>

        <div class="container">

            <!-- Section contact -->
            <section id="contact">
                <!-- Section title -->
                <div align="center">
                    <h1>Sign up</h1>
                    <h5>Please add a username and password</h5>
                </div>

                <!-- Form -->
                <form action="SignupServlet" method="post" id="contact-form">
                    <!-- Name -->
                    <div class="form-group">
                        <label for="name">Username</label>
                        <input
                            type="email"
                            id="name"
                            name="user"
                            placeholder="Enter a username..."
                            />
                    </div>

                    <!-- Password -->
                    <div class="form-group">
                        <label for="email" class="required">Password</label>
                        <input
                            type="password"
                            id="email"
                            name="pass"
                            placeholder="Enter a password..."
                            required
                            />
                    </div>

                    <!-- Confirm Password -->
                    <div class="form-group">
                        <label for="email" class="required">Confirm Password</label>
                        <input
                            type="password"
                            id="email"
                            name="confirm"
                            placeholder="Confirm password..."
                            required
                            />
                    </div>

                    <!-- Captcha -->
                    <div class="form-group">
                        <label for="email" class="required">Captcha</label>
                        <img src="CaptchaServlet"/>
                        <label></label>
                        <input type="text" 
                               name="captcha" 
                               id="captcha" 
                               placeholder="Enter the captcha..." 
                               required
                               />
                    </div>

                    <!-- Button -->
                    <button type="submit" id="submit" class="btn">Sign up</button>

                </form>

                <div align="center">
                    <h5>Return to <a href="landing.jsp">Homepage</a></h5>
                </div>

            </section>

            <br />&nbsp;<br /><br>

            <!-- Footer -->
            <footer id="footer">
                Copyright 2023 © All rights reserved.
            </footer>
        </div>
        <br>

    </body>
</html>
