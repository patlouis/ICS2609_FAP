
package Controller;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kim
 */
public class AdminReceipt extends HttpServlet {

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
    private static final String SELECT_QUERY = "SELECT EMAIL, USERROLE FROM USERS";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            if (conn != null) {
                // System.out.println("got in admin report");
                String username = request.getParameter("username");
                PreparedStatement ps = conn.prepareStatement(SELECT_QUERY);
                ResultSet rs = ps.executeQuery();

                // Set the content type and attachment header.
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=Admin Report.pdf");

                // Create a new document and write some text to it.
                Document document = new Document();
                document.setPageSize(PageSize.LETTER.rotate());
                PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
                FooterEvent event = new FooterEvent(username);
                writer.setPageEvent(event);
                writer.setPageCount(0);

                document.open();
                // TITLE
                Font font = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
                Paragraph admin_report = new Paragraph("Admin Receipt", font);
                admin_report.setAlignment(Element.ALIGN_CENTER);
                document.add(admin_report);
                Chunk chunk = new Chunk(" ");
                chunk.setLineHeight(20);

                document.add(new Paragraph(chunk));

                // DATABASE RECORD
                Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                // TABLE HEADER
                PdfPCell usernameHeader = new PdfPCell(new Phrase("Username", headerFont));
                usernameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell roleHeader = new PdfPCell(new Phrase("Role", headerFont));
                roleHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPTable table = new PdfPTable(2);
                table.addCell(usernameHeader);
                table.addCell(roleHeader);

                while (rs.next()) {
                    String user_name = rs.getString("EMAIL");
                    String user_role = rs.getString("USERROLE");

                    PdfPCell usernameCell = new PdfPCell(new Phrase(user_name));
                    usernameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    PdfPCell roleCell = new PdfPCell(new Phrase(user_role));
                    roleCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                    table.addCell(usernameCell);
                    table.addCell(roleCell);
                }

                document.add(table);

                document.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class FooterEvent extends PdfPageEventHelper {

        private final String username;

        public FooterEvent(String username) {
            this.username = username;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a");
            String formattedDate = formatter.format(date);
            Font FooterFont = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC);
            Paragraph footer = new Paragraph("Date Generated: " + formattedDate, FooterFont);
            Paragraph generated = new Paragraph("Generated by: " + username, FooterFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, footer, (float) ((document.left()) / 0.5 + document.leftMargin()),
                    document.bottom() - 8, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, generated, (float) ((document.left()) / 0.5 + document.leftMargin()),
                    document.bottom() - 16, 0);

            // Calculate the total number of pages in the document
            int totalPage = (int) Math.ceil(writer.getVerticalPosition(false) / (document.getPageSize().getHeight() - document.bottomMargin() - document.topMargin()));
            totalPage++;
            totalPage++;
// Create a Phrase with the current page number and total number of pages
            Phrase page = new Phrase(String.format("Page %d of %d", writer.getPageNumber(), totalPage), FooterFont);
            // Create a Paragraph with the footer text and page number
            Paragraph footer1 = new Paragraph(page);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, footer1, (float) ((document.right()) / 1.13 + document.rightMargin()),
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