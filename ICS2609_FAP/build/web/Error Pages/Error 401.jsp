
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- meta tags -->
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- title -->
        <title>401 - Authorization Required</title>

        <link href="../css/styles1.css" rel="stylesheet" type="text/css"/>

        <!-- google font  -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Inconsolata:wght@700&family=Space+Mono:wght@400;700&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <header>
            <h1>401 Error</h1>
        </header>
        <main>
            <img src="../Images/Scarecrow.png" alt="Scarecrow Image"/>
            <div class="hero">
                <h2>Authorization Required</h2>
                <p>
                    Your authorization failed. Please try refreshing the page and fill in the correct login details.
                </p>
                <button>
                    <a href="LandingJSP.jsp">Back to homepage</a>
                </button>
            </div>
        </main>
    </body>
</html>
