
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    session.removeAttribute("username");
    session.invalidate();
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Fry Me to the Moon</title>
        <meta property="og:title" content="Mobillio Online Store" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta charset="utf-8" />
        <meta property="twitter:card" content="summary_large_image" />

        <style data-tag="reset-style-sheet">
            html {
                line-height: 1.15;
            }
            body {
                margin: 0;
            }
            * {
                box-sizing: border-box;
                border-width: 0;
                border-style: solid;
            }
            p,li,ul,pre,div,h1,h2,h3,h4,h5,h6,figure,blockquote,figcaption {
                margin: 0;
                padding: 0;
            }
            button {
                background-color: transparent;
            }
            button,input,optgroup,select,textarea {
                font-family: inherit;
                font-size: 100%;
                line-height: 1.15;
                margin: 0;
            }
            button,select {
                text-transform: none;
            }
            button,[type="button"],[type="reset"],[type="submit"] {
                -webkit-appearance: button;
            }
            button::-moz-focus-inner,[type="button"]::-moz-focus-inner,[type="reset"]::-moz-focus-inner,[type="submit"]::-moz-focus-inner {
                border-style: none;
                padding: 0;
            }
            button:-moz-focus,[type="button"]:-moz-focus,[type="reset"]:-moz-focus,[type="submit"]:-moz-focus {
                outline: 1px dotted ButtonText;
            }
            a {
                color: inherit;
                text-decoration: inherit;
            }
            input {
                padding: 2px 4px;
            }
            img {
                display: block;
            }
            html {
                scroll-behavior: smooth
            }
        </style>
        <style data-tag="default-style-sheet">
            html {
                font-family: Jost;
                font-size: 16px;
            }

            body {
                font-weight: 400;
                font-style:normal;
                text-decoration: none;
                text-transform: none;
                letter-spacing: 0.02;
                line-height: 1.55;
                color: var(--dl-color-gray-black);
                background-color: var(--dl-color-gray-white);

            }
        </style>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap"
            data-tag="font"
            />
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div>
            <link href="css/home.css" rel="stylesheet" type="text/css"/>

            <div class="home-container">
                <div class="home-navbar">
                    <header data-role="Header" class="home-header max-width-container">
                        <div class="home-navbar1">
                            <div class="home-container1"></div>
                            <div class="home-middle">
                                <div class="home-left">
                                    <span class="home-logo-center navbar-logo-title">
                                        <a href="#top">Fry Me to the Moon</a>
                                    </span>
                                    <span class="home-logo-center navbar-logo-title">
                                    </span>
                                </div>
                            </div>
                            <div class="home-right">   
                                <span class="navbar-link">
                                    <a href="#about-section">ABOUT</a>
                                </span>
                                <span class="navbar-link">
                                    <a href="menu.jsp"/>MENU</a>
                                </span>        
                                <span class="navbar-link">
                                    <a href="login.jsp">LOGIN</a>
                                </span>
                                <span class="navbar-link">
                                    <a href="signup.jsp">SIGNUP</a>
                                </span>
                            </div>
                            <div class="home-icons">
                                <a href="login.jsp"/>
                                <img
                                    alt="iconsbxscart3271299"
                                    src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0nMjQnIGhlaWdodD0nMjQnIHZpZXdCb3g9JzAgMCAyNCAyNCcgZmlsbD0nbm9uZScgeG1sbnM9J2h0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnJz4KPHBhdGggZD0nTTIxLjgyMiA3LjQzMUMyMS42MzUgNy4xNjEgMjEuMzI4IDcgMjEgN0g3LjMzM0w2LjE3OSA0LjIzQzUuODY3IDMuNDgyIDUuMTQzIDMgNC4zMzMgM0gyVjVINC4zMzNMOS4wNzcgMTYuMzg1QzkuMjMyIDE2Ljc1NyA5LjU5NiAxNyAxMCAxN0gxOEMxOC40MTcgMTcgMTguNzkgMTYuNzQxIDE4LjkzNyAxNi4zNTJMMjEuOTM3IDguMzUyQzIyLjA1MiA4LjA0NCAyMi4wMDkgNy43IDIxLjgyMiA3LjQzMVonIGZpbGw9JyMxNjE2MTYnLz4KPHBhdGggZD0nTTEwLjUgMjFDMTEuMzI4NCAyMSAxMiAyMC4zMjg0IDEyIDE5LjVDMTIgMTguNjcxNiAxMS4zMjg0IDE4IDEwLjUgMThDOS42NzE1NyAxOCA5IDE4LjY3MTYgOSAxOS41QzkgMjAuMzI4NCA5LjY3MTU3IDIxIDEwLjUgMjFaJyBmaWxsPScjMTYxNjE2Jy8+CjxwYXRoIGQ9J00xNy41IDIxQzE4LjMyODQgMjEgMTkgMjAuMzI4NCAxOSAxOS41QzE5IDE4LjY3MTYgMTguMzI4NCAxOCAxNy41IDE4QzE2LjY3MTYgMTggMTYgMTguNjcxNiAxNiAxOS41QzE2IDIwLjMyODQgMTYuNjcxNiAyMSAxNy41IDIxWicgZmlsbD0nIzE2MTYxNicvPgo8L3N2Zz4K"
                                    class="home-image"
                                    /></a>
                            </div>
                        </div>
                </div>
                <div class="home-main">     
                    <div class="home-hero section-container">
                        <section id="home-section">
                            <div class="home-max-width max-width-container">
                                <div class="home-hero1">
                                    <img
                                        alt="image"
                                        src="Images/Regular Fries.png"
                                        class="home-image2"
                                        />
                                    <span class="home-text09">
                                        Welcome to Fry Me to the Moon, the ultimate fries destination!
                                        Our restaurant is dedicated solely to fries, offering a
                                        variety of options to satisfy any craving. From classic
                                        shoestring fries to curly fries and even sweet potato fries,
                                        we've got something for everyone. Our fries are always cooked
                                        fresh to order and served hot and crispy. And with a range of
                                        dipping sauces and toppings, you can create your own unique
                                        flavor combinations. So if you're a fry lover looking for your
                                        next fix, come visit us at Fry Me to the Moon and experience
                                        fries like never before!
                                    </span>
                                </div>
                            </div>
                            <br>
                        </section>
                    </div>

                    <section id="about-section">
                        <div class="section-container column">
                            <div class="home-banner">
                                <div class="home-container3">
                                    <h3 class="home-text10">Fry Me to the Moon</h3>
                                    <span class="home-text11">ABOUT US</span>
                                </div>
                            </div>
                            <div class="home-container4 max-width-container">
                                <div class="home-container5">
                                    <span class="home-text12">
                                        <span>
                                            Fry Me to the Moon is a fries-focused restaurant that began
                                            with a simple idea - to create the ultimate fry experience.
                                            Our founders, a group of foodies and fries enthusiasts,
                                            wanted to create a restaurant dedicated solely to fries,
                                            offering a range of options that would satisfy any craving.
                                        </span>
                                        <br/>
                                        <span>
                                            The idea quickly gained traction, and Fry Me to the Moon was
                                            born. Our first location opened in a small town in the
                                            Midwest, and word quickly spread about our delicious fries
                                            and unique flavor combinations.
                                        </span>
                                        <br />
                                        <span>
                                            Today, Fry Me to the Moon has become a destination for fries
                                            lovers from all over the world. Our menu features a variety
                                            of fry options, including classic shoestring fries, waffle
                                            fries, crinkle-cut fries, and even sweet potato fries. And
                                            with a range of dipping sauces and toppings, the
                                            possibilities for creating your own unique flavor
                                            combinations are endless.
                                        </span>
                                        <br />
                                        <span>
                                            At Fry Me to the Moon, we take pride in using only the
                                            freshest and highest quality potatoes and spices to create
                                            our fries. We believe that fries are not just a side dish,
                                            but an experience, and our dedication to quality and flavor
                                            reflects this belief.
                                        </span>
                                        <br />
                                        <span>
                                            But Fry Me to the Moon is more than just a restaurant - it's
                                            a community. We are passionate about bringing people
                                            together over a shared love of fries, and our space is
                                            designed to be both comfortable and inviting. We believe
                                            that good food and good company are the ingredients for a
                                            happy life, and we strive to provide both to our customers.
                                        </span>
                                        <br />
                                        <span>
                                            So if you're a fries enthusiast looking for your next fix,
                                            come visit us at Fry Me to the Moon and experience the
                                            ultimate fry experience. We promise that you won't be
                                            disappointed!
                                        </span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="home-footer">
                    <div class="max-width-container">
                        <footer class="home-footer1">
                            <div class="home-container6">
                                <h3 class="home-text24">Fry Me to the Moon</h3>
                                <span class="home-text25">
                                    Pier Giorgio Frassati, O.P. Bldg., University of Santo Tomas,
                                    España Blvd., Sampaloc, Manila, Philippines 1008
                                </span>
                                <span class="home-text26">(0917) 555-6666</span>
                                <span class="home-text27">frymetothemoon@gmail.com</span>
                            </div>
                            <div class="home-links-container"></div>
                        </footer>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://unpkg.com/@teleporthq/teleport-custom-scripts"></script>
    </body>
</html>
