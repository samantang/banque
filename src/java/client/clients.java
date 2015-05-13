package client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.GestionnaireClientLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Client;
import metier.CompteBancaire;

@WebServlet(name = "clients", urlPatterns = {"/clients"})
public class clients extends HttpServlet {

    @EJB
    private GestionnaireClientLocal gestionnaireClientBean;

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
            String page = "";
            if (action.equals("test")) {
                gestionnaireClientBean.creerClientTest();

                Collection<Client> clients = gestionnaireClientBean.findAllClient();
                request.setAttribute("clients", clients);
                // request.setAttribute("soldes", calculTotalSoldes(comptes));
                page = "afficheurDeClients.jsp";
            } else if (action.equals("creerClient")) {

                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String adresse = request.getParameter("adresse");
                String email = request.getParameter("email");
                String telephone = request.getParameter("telephone");
                if (nom.equals("") || prenom.equals("") || adresse.equals("") || email.equals("") || telephone.equals("")) {

                    request.getSession(true).setAttribute("message", "<span id=\"erreur\">Vérifiez que tous les champs sont remplis</span>");

                } else {
                    try {
                        gestionnaireClientBean.creerClient2(nom, prenom, adresse, email, telephone);
                        request.getSession(true).setAttribute("message", "<span id=\"message\">Le client a été crée avec succès</span>");

                    } catch (NullPointerException ex) {
                        request.getSession(true).setAttribute("message", "<span id=\"erreur\">Client non créer</span>");

                    }
                }

                page = "creerClient.jsp";


            } else if (action.equals("creerCompteClient")) {
                Collection<Client> clients = gestionnaireClientBean.findAllClient();
                request.setAttribute("clients", clients);


                try {
                    String clientID = request.getParameter("clientID");
                    if (!clientID.equals("") && clientID != null) {
                        String typeCompte = request.getParameter("typeCompte");

                        String solde = request.getParameter("solde");

                        if (solde.equals("")) {

                            request.getSession(true).setAttribute("message", "<span id=\"erreur\">Vérifiez que tous les champs sont remplis</span>");

                        } else {
                            try {
                                Client c = gestionnaireClientBean.findClientById(new Long(clientID));

                                if (typeCompte.equals("courant")) {
                                    gestionnaireClientBean.creerClient(c.getNom(), c.getPrenom(), c.getAdresse(), c.getEmail(), c.getTelephone(), Double.parseDouble(solde), CompteBancaire.TypeCompte.COMPTE_COURANT);
                                } else {
                                    gestionnaireClientBean.creerClient(c.getNom(), c.getPrenom(), c.getAdresse(), c.getEmail(), c.getTelephone(), Double.parseDouble(solde), CompteBancaire.TypeCompte.COMPTE_EPARGNE);

                                }
                                request.getSession(true).setAttribute("message", "<span id=\"message\">Le client a été crée avec succès</span>");

                            } catch (NumberFormatException nF) {
                                request.getSession(true).setAttribute("message", "<span id=\"erreur\">Le solde doit être un nombre réel</span>");

                            }
                        }

                    }


                } catch (NullPointerException ex) {
                }
                page = "creerCompteClient.jsp";
            } else if (action.equals("modifierClient")) {
                try {
                    String modifId = request.getParameter("modifId");
                    request.setAttribute("modifId", modifId);

                    Client c = gestionnaireClientBean.findClientById(new Long(modifId));
                    request.setAttribute("client", c);

                    String nom = request.getParameter("nom");
                    if (!nom.equals("") && nom != null) {
                        String prenom = request.getParameter("prenom");
                        String adresse = request.getParameter("adresse");
                        String email = request.getParameter("email");
                        String telephone = request.getParameter("telephone");

                        gestionnaireClientBean.modifierClientById(new Long(modifId), nom, prenom, adresse, email, telephone);
                        request.getSession(true).setAttribute("message", "<span id=\"message\">Modification du client OK</span>");
                        c = gestionnaireClientBean.findClientById(new Long(modifId));
                        request.setAttribute("client", c);
                    }

                } catch (NullPointerException ex) {
                }


                page = "modifierClient.jsp";
            } else if (action.equals("afficherClients")) {

                Collection<Client> clients = gestionnaireClientBean.findAllClient();

                request.setAttribute("clients", clients);

                page = "afficheurDeClients.jsp";
            } else if (action.equals("afficherCompteClient")) {
                try {

                    String clientID = request.getParameter("id");

                    Client c = gestionnaireClientBean.findClientById(new Long(clientID));

                    request.setAttribute("client", c);

                    Collection<CompteBancaire> comptesClient = c.getListeComptes();

                    request.setAttribute("comptesClient", comptesClient);
                } catch (NullPointerException e) {
                }
                page = "afficherCompteClient.jsp";
            } else if (action.equals("supprimerClient")) {

                Collection<Client> clients = gestionnaireClientBean.findAllClient();
                request.setAttribute("clients", clients);

                try {
                    //id du client
                    String supprId = request.getParameter("supprId");
                    request.setAttribute("supprId", supprId);
                    //récupération du client
                    Client client = gestionnaireClientBean.findClientById(new Long(supprId));
                    //récupération des comptes du client
                    Collection<CompteBancaire> listeComptesDuClient = client.getListeComptes();
                    System.out.println("liste des Comptes Du Client1 servlet = " + listeComptesDuClient);
                    //récupération des id des comptes et suppression de ceux-ci
                    gestionnaireClientBean.suprrimerComptesDunClient(new Long(supprId));
                    if (listeComptesDuClient == null) {
                        System.out.println("Liste des comptes d'un client supprimée");
                    } else {
                        System.out.println("liste des id Comptes Du Client servlet NON SUPPRIMER = " + listeComptesDuClient);
                    }

                    if (supprId == null) {
                        request.getSession(true).setAttribute("message", "<span id=\"message\">Suppression du client IMPOSSIBLE </span>");
                    } else {
                        System.out.println("supprId = " + supprId);
                        gestionnaireClientBean.supprimerClient(new Long(supprId));
                        request.getSession(true).setAttribute("message", "<span id=\"message\">Suppression du client OK</span>");
                    }

                } catch (NullPointerException ex) {
                }
                clients = gestionnaireClientBean.findAllClient();
                request.setAttribute("clients", clients);

                page = "afficheurDeClients.jsp";

            } else if (action.equals("connexion")) {
                String login = request.getParameter("login");
                String mdp = request.getParameter("password");

                if (login.equals("admin") && mdp.equals("admin")) {
                    request.getSession(true).setAttribute("login", "admin");
                    request.getSession(true).setAttribute("connexion", "ok");
                } else {
                    Client c = gestionnaireClientBean.findClientByEmail(login);
                    if (c != null) {
                        if (!mdp.equals(c.getNom())) {
                            request.getSession(true).setAttribute("login", "");
                            request.getSession(true).setAttribute("connexion", "");
                        } else {

                            request.getSession(true).setAttribute("client", c);
                            request.getSession(true).setAttribute("login", "client");
                            request.getSession(true).setAttribute("connexion", "ok");
                        }

                    } else {
                        request.getSession(true).setAttribute("login", "");
                        request.getSession(true).setAttribute("connexion", "");
                    }
                }
                page = "index.jsp";
            } else if (action.equals("deconnexion")) {
                request.getSession(true).setAttribute("login", "");
                request.getSession(true).setAttribute("connexion", "");
                page = "index.jsp";
            }
            RequestDispatcher dp = request.getRequestDispatcher(page);
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
}
