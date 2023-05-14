package Controller;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.text.renderer.ColoredEdgesWordRenderer;

public class CaptchaServlet extends HttpServlet {

    // CAPTCHA COMPONENTS
    private static final long serialVersionUID = 1L;

    private static final int _width = 200;
    private static final int _height = 50;

    private static final List<Color> COLORS = new ArrayList<>(2);
    private static final List<Font> FONTS = new ArrayList<>(3);

    static {
        COLORS.add(Color.BLACK);
        COLORS.add(Color.BLUE);

        FONTS.add(new Font("Geneva", Font.ITALIC, 48));
        FONTS.add(new Font("Courier", Font.BOLD, 48));
        FONTS.add(new Font("Arial", Font.BOLD, 48));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // CAPTCHA BUILDER
        ColoredEdgesWordRenderer wordRenderer = new ColoredEdgesWordRenderer(COLORS, FONTS);
        Captcha captcha = new Captcha.Builder(_width, _height).addText(wordRenderer)
                .gimp(new DropShadowGimpyRenderer())
                .gimp()
                .addNoise()
                .addNoise()
                .addNoise()
                .addBackground(new GradiatedBackgroundProducer())
                .build();

        String answer = captcha.getAnswer();

        // STORE THE CAPTCHA SOLUTION IN THE SESSION
        HttpSession session = request.getSession();
        session.setAttribute("captcha_solution", captcha);
        session.setAttribute("answer", answer);

        // WRITE THE CAPTCHA IMAGE TO THE RESPONSE OUTPUTSTREAM
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(captcha.getImage(), "png", outputStream);

        // REQUEST DISPATCHER
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminServlet");
        dispatcher.forward(request, response);

        RequestDispatcher dp = request.getRequestDispatcher("SignupServlet");
        dp.forward(request, response);

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
