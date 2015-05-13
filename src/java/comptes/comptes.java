/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comptes;

import beans.GestionnaireDeCompteBancaireLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.*;


public class comptes extends HttpServlet {

    @EJB
    private GestionnaireDeCompteBancaireLocal gestionnaireDeCompteBancaireBean;

    public double calculTotalSoldes(Collection<CompteBancaire> comptes) {
        double total = 0;
        for (CompteBancaire cb : comptes) {
            total += cb.getSolde();
        }
        return total;
    }

    public double calculTotalComptesEpargnes(Collection<CompteBancaire> comptes) {
        double total = 0;
        for (CompteBancaire cb : comptes) {
            if (cb instanceof CompteEpargne) {
                total += cb.getSolde();
            }
        }
        return total;
    }

    public double calculTotalComptesCourants(Collection<CompteBancaire> comptes) {
        double total = 0;
        for (CompteBancaire cb : comptes) {
            if (cb instanceof CompteCourant) {
                total += cb.getSolde();
            }
        }
        return total;
    }

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

            if (action != null) {
                if (action.equals("test")) {
                    gestionnaireDeCompteBancaireBean.insererCompteTest();

                    Collection<CompteBancaire> comptes = gestionnaireDeCompteBancaireBean.getAllComptes();
                    request.setAttribute("comptes", comptes);
                    request.setAttribute("soldes", calculTotalSoldes(comptes));

                    page = "afficheurDeComptes.jsp?action=afficherComptes";
                } else {
                    Collection<CompteBancaire> comptes = gestionnaireDeCompteBancaireBean.getAllComptes();


                    request.setAttribute("comptes", comptes);
                    /*
                    if (action.equals("transfert")) {
                    String compte1 = "";
                    String compte2 = "";
                    String montant = "";
                    try {
                    compte1 = request.getParameter("compte1");
                    compte2 = request.getParameter("compte2");
                    montant = request.getParameter("montant");
                    if (compte1.equals("") || compte2.equals("") || montant.equals("")) {
                    
                    request.getSession(true).setAttribute("message", "<span id=\"erreur\">Vérifiez que tous les champs sont remplis</span>");
                    
                    } else {
                    
                    
                    CompteBancaire cb1 = gestionnaireDeCompteBancaireBean.findCompteBancaireById(new Long(compte1));
                    double montantATransferer = Double.parseDouble(montant);
                    
                    try {
                    gestionnaireDeCompteBancaireBean.retirerMontant(new Long(compte1), montantATransferer);
                    gestionnaireDeCompteBancaireBean.ajouterMontant(new Long(compte2), montantATransferer);
                    request.getSession(true).setAttribute("message", "<span id=\"message\">La transaction a été effectuée</span>");
                    } catch (NumberFormatException nF) {
                    request.getSession(true).setAttribute("message", "<span id=\"erreur\">Le montant de transfert doit être un nombre réel</span>");
                    } catch (RetraitMontantException rM) {
                    request.getSession(true).setAttribute("message", "<span id=\"erreur\">Veuillez saisir un montant < " + cb1.getSolde() + "</span>");
                    
                    } catch (AjoutMontantException aE) {
                    request.getSession(true).setAttribute("message", "<span id=\"erreur\">Le montant de transfert doit être > 0</span>");
                    }
                    
                    
                    }
                    } catch (NullPointerException ex) {
                    }
                    page = "transfertComptes.jsp";
                    
                    } else */

                    if (action.equals("creerCompte")) {
                        try {
                            String nom = request.getParameter("nom");
                            String prenom = request.getParameter("prenom");
                            String adresse = request.getParameter("adresse");
                            String email = request.getParameter("email");
                            String telephone = request.getParameter("telephone");
                            String typeCompte = request.getParameter("typeCompte");

                            String solde = request.getParameter("solde");

                            if (nom.equals("") || solde.equals("") || adresse.equals("") || email.equals("") || telephone.equals("")) {

                                request.getSession(true).setAttribute("message", "<span id=\"erreur\">Vérifiez que tous les champs sont remplis</span>");

                            } else {
                                try {
                                    if (typeCompte.equals("courant")) {
                                        gestionnaireDeCompteBancaireBean.creerCompteBancaire(new Client(nom, prenom, adresse, email, telephone), Double.parseDouble(solde), CompteBancaire.TypeCompte.COMPTE_COURANT);

                                    } else {
                                        gestionnaireDeCompteBancaireBean.creerCompteBancaire(new Client(nom, prenom, adresse, email, telephone), Double.parseDouble(solde), CompteBancaire.TypeCompte.COMPTE_EPARGNE);

                                    }
                                    request.getSession(true).setAttribute("message", "<span id=\"message\">Le compte a été crée avec succès</span>");

                                } catch (NumberFormatException nF) {
                                    request.getSession(true).setAttribute("message", "<span id=\"erreur\">Le solde doit être un nombre réel</span>");

                                }
                            }

                        } catch (NullPointerException ex) {
                        }
                        page = "creerCompte.jsp";
                    } else if (action.equals("supprimerCompte2")) {
                        try {
                            String compte = request.getParameter("compte");
                            if (!compte.equals("")) {
                                gestionnaireDeCompteBancaireBean.supprimerCompteBancaire(new Long(compte));
                                request.getSession(true).setAttribute("message", "<span id=\"message\">Suppression du compte OK</span>");

                            } else {
                                request.getSession(true).setAttribute("message", "<span id=\"erreur\">Veuillez choisir un compte dans la liste</span>");

                            }
                        } catch (NullPointerException ex) {
                        }

                        page = "supprimerCompte.jsp";
                    } else if (action.equals("supprimerCompte")) {
                        try {
                            //String compte = request.getParameter("compte");
                            // if (!compte.equals("")) {
                            String supprId = request.getParameter("supprId");
                            request.setAttribute("supprId", supprId);

                            if (!supprId.equals("")) {
                                CompteBancaire compteASupp = gestionnaireDeCompteBancaireBean.findCompteBancaireById(new Long(supprId));
                                System.out.println("Compte à supprimer  = " + compteASupp);

                                gestionnaireDeCompteBancaireBean.supprimerCompteBancaire(new Long(supprId));
                                request.getSession(true).setAttribute("message", "<span id=\"message\">Suppression du compte OK</span>");

                            } else {
                                request.getSession(true).setAttribute("message", "<span id=\"erreur\">Veuillez choisir un compte dans la liste</span>");

                            }
                        } catch (NullPointerException ex) {
                        }

                        page = "afficheurDeComptes.jsp";
                    } else if (action.equals("modifierCompte")) {
                        try {
                            String modifId = request.getParameter("modifId");
                            request.setAttribute("modifId", modifId);

                            if (!modifId.equals("") && modifId != null) {

                                CompteBancaire cb = gestionnaireDeCompteBancaireBean.findCompteBancaireById(new Long(modifId));
                                request.setAttribute("compte", cb);
                                System.out.println("Compte à modifier  = " + cb);

                                String solde = request.getParameter("solde");

                                gestionnaireDeCompteBancaireBean.modifierCompteBancaire(new Long(modifId), new Double(solde));
                                request.getSession(true).setAttribute("message", "<span id=\"message\">Modification du compte OK</span>");

                            } else {
                                request.getSession(true).setAttribute("message", "<span id=\"erreur\">Veuillez choisir un compte</span>");
                            }
                        } catch (NullPointerException ex) {
                        }

                        page = "modifierCompte.jsp";

                    } else if (action.equals("afficherComptes")) {

                        request.setAttribute("soldes", calculTotalSoldes(comptes));
                        page = "afficheurDeComptes.jsp?action=afficherComptes";

                    } else if (action.equals("afficherComptesCourant")) {
                        // Collection<CompteBancaire> comptesCourant = new ArrayList<CompteBancaire>();

                        request.setAttribute("soldes", calculTotalComptesCourants(comptes));
                        page = "afficheurDeComptes.jsp?action=afficherComptesCourant";

                    } else if (action.equals("afficherComptesEpargne")) {

                        request.setAttribute("soldes", calculTotalComptesEpargnes(comptes));
                        page = "afficheurDeComptes.jsp?action=afficherComptesEpargne";

                    } else if (action.equals("detaillerCompte")) {

                        String detailId = request.getParameter("detailId");
                        System.out.println("Compte1  = " + detailId);
                        if (detailId != null) {
                            CompteBancaire cb = gestionnaireDeCompteBancaireBean.findCompteBancaireById(new Long(detailId));

                            Collection<OperationBancaire> operations = cb.getOperations();

                            request.setAttribute("operations", operations);
                            request.setAttribute("compte", cb);

                            page = "AfficheHistoriqueCompte.jsp";

                        } else {
                            page = "index.jsp";
                        }
                    }


                }
                RequestDispatcher dp = request.getRequestDispatcher(page);
                dp.forward(request, response);
            }
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
