package Controller;

import Model.Cart;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerReceipt extends HttpServlet {

    Connection conn;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            // JDBC CLASS NAME
            Class.forName(config.getInitParameter("jdbcClassName"));
            // System.out.println("jdbcClassName: " + config.getInitParameter("jdbcClassName"));
            // DB USERNAME
            String username = config.getInitParameter("dbUserName");
            // DB PASSWORD
            String password = config.getInitParameter("dbPassword");
            // DB URL
            StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getInitParameter("dbHostName"))
                    .append(":")
                    .append(config.getInitParameter("dbPort"))
                    .append("/")
                    .append(config.getInitParameter("databaseName"));

            // CONNECTION
            conn = DriverManager.getConnection(url.toString(), username, password);

            // SQL EXCEPTION ERROR
        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
            // CLASSNOTFOUND EXCEPTION ERROR
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //The attributes for the receipt
        double total = Double.parseDouble(request.getParameter("total"));
        HttpSession session = request.getSession();
        ArrayList<Cart> order = (ArrayList<Cart>) session.getAttribute("order");
        String username = (String) session.getAttribute("username");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Customer Receipt.pdf");

        try {

            if (conn != null) {

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a");
                String formattedDate = formatter.format(date);
                PreparedStatement prep = conn.prepareStatement("UPDATE USERS SET DATE=? WHERE EMAIL=?");
                prep.setString(1, formattedDate);
                prep.setString(2, username);
                prep.executeUpdate();

                // Create a new document and write some text to it.
                Document document = new Document();
                document.setPageSize(PageSize.LETTER.rotate());
                PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
                FooterEvent event = new FooterEvent(username);
                writer.setPageEvent(event);

                document.open();
                // TITLE
                Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
                Paragraph guest_report = new Paragraph("Fry Me To The Moon", font);
                guest_report.setAlignment(Element.ALIGN_CENTER);
                document.add(guest_report);
                Chunk chunk = new Chunk(" ");
                chunk.setLineHeight(30);

                document.add(new Paragraph(chunk));

                // DATABASE RECORD
                Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

                // TABLE HEADER
                PdfPCell itemNameHeader = new PdfPCell(new Phrase("Items ordered", headerFont));
                itemNameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell priceHeader = new PdfPCell(new Phrase("Price", headerFont));
                priceHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPTable table = new PdfPTable(2);
                table.addCell(itemNameHeader);
                table.addCell(priceHeader);

                if (!order.isEmpty()) {
                    for (Cart p : order) {
                        PdfPCell nameCell = new PdfPCell(new Phrase(p.getName()));
                        nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        PdfPCell priceCell = new PdfPCell(new Phrase("P" + p.getPrice()));
                        priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                        table.addCell(nameCell);
                        table.addCell(priceCell);
                    }
                }
                document.add(table);
                Font pFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);
                Paragraph totalOrder = new Paragraph("Total: P" + total, pFont);
                totalOrder.setAlignment(Element.ALIGN_CENTER);
                document.add(totalOrder);

                Paragraph paragraph = new Paragraph("Thank you for Ordering at Fry Me to the Moon", pFont);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);

                document.close();

            }

            //Optional: if you can't find the pdf
            //File outputFile = new File("Fry Me to the Moon Receipt.pdf");
            //String absolutePath = outputFile.getAbsolutePath();
            //System.out.println("Generated PDF file path: " + absolutePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");

        HttpSession sessions = request.getSession();
        ArrayList<Cart> sessionCart = (ArrayList<Cart>) sessions.getAttribute("cart-list");
        if (sessionCart.isEmpty()) {
            sessions.setAttribute("order-status", null);
        } else {
            sessions.setAttribute("order-status", true);
            sessionCart.clear();
            sessions.setAttribute("cart-list", sessionCart);
        }
        response.sendRedirect("home.jsp");
    }

    public class FooterEvent extends PdfPageEventHelper {

        private String username;

        public FooterEvent(String name) {
            this.username = name;
            System.out.println("username at footer: " + this.username);
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a");
            String formattedDate = formatter.format(date);
            Font FooterFont = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC);
            Paragraph footer = new Paragraph("Date Generated: " + formattedDate, FooterFont);
            Paragraph generated = new Paragraph("Ordered by: " + username, FooterFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            // DATE AND TIME
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, footer, (float) ((document.left()) / 0.5 + document.leftMargin()),
                    document.bottom() - 8, 0);
            // GENERATED BY
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, generated, (float) ((document.left()) / 0.5 + document.leftMargin()),
                    document.bottom() - 16, 0);

            // Calculate the total number of pages in the document
            int totalPage = (int) Math.ceil(writer.getVerticalPosition(false) / (document.getPageSize().getHeight() - document.bottomMargin() - document.topMargin()));
            // Create a Phrase with the current page number and total number of pages
            Phrase page = new Phrase(String.format("Page %d of %d", writer.getPageNumber(), totalPage), FooterFont);
            // Create a Paragraph with the footer text and page number
            Paragraph pageXofY = new Paragraph(page);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, pageXofY, (float) ((document.right()) / 1.13 + document.rightMargin()),
                    document.bottom() - 12, 0);
        }
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
