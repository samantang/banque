/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import beans.GestionnaireDeCompteBancaireLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.CompteBancaire;


@WebServlet(name = "ServletOrdreDeTransfert", urlPatterns = {"/ServletOrdreDeTransfert"})
public class ServletOrdreDeTransfert extends HttpServlet {

    @EJB
    private GestionnaireDeCompteBancaireLocal gestionnaireDeCompteBancaireBean;
    @Resource(name = "jms/OrdresTransfertBancaires")
    private Queue ordresTransfertBancaires;
    @Resource(name = "jms/loggingMessageFactory")
    private ConnectionFactory loggingMessageFactory;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getSession(true).setAttribute("message", "");
        try {
            String action = request.getParameter("action");
            try {
                //gestionnaireDeCompteBancaireBean.insererCompteTest();
                Collection<CompteBancaire> comptes = gestionnaireDeCompteBancaireBean.getAllComptes();

                request.setAttribute("comptes", comptes);

                if (action.equals("transfert")) {

                    String compte1 = "";
                    String compte2 = "";
                    String montant = "";
                    
                    compte1 = request.getParameter("compte1");
                    compte2 = request.getParameter("compte2");
                    montant = request.getParameter("montant");
                    if (compte1.equals("") || compte2.equals("") || montant.equals("")) {

                        request.getSession(true).setAttribute("message", "<span id=\"erreur\">Vérifiez que tous les champs sont remplis</span>");

                    } else {
                        CompteBancaire cb1 = gestionnaireDeCompteBancaireBean.findCompteBancaireById(new Long(compte1));

                        if (cb1.getSolde() < Double.parseDouble(montant)) {
                            request.getSession(true).setAttribute("message", "<span id=\"erreur\">Le montant de transfert doit être < " + cb1.getSolde() + "</span>");

                        } else {
                            OrdreDeTransfert ordre = new OrdreDeTransfert(new Long(compte1), new Long(compte2), Double.parseDouble(montant));
                            sendJMSMessageToOrdresTransfertBancaires(ordre);
                            request.getSession(true).setAttribute("message", "<span id=\"message\">La transaction a été effectuée</span>");
                        }
                    }
                }
            } catch (NullPointerException ex) {
            } catch (JMSException ex) {
                Logger.getLogger(ServletOrdreDeTransfert.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher dp = request.getRequestDispatcher("transfertComptes.jsp");
            dp.forward(request, response);

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Message createJMSMessageForjmsOrdresTransfertBancaires(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        ObjectMessage tm = session.createObjectMessage();
        tm.setObject((Serializable) messageData);
        return tm;
    }

    private void sendJMSMessageToOrdresTransfertBancaires(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = loggingMessageFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(ordresTransfertBancaires);
            messageProducer.send(createJMSMessageForjmsOrdresTransfertBancaires(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
