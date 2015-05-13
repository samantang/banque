
<%@page import="javassist.bytecode.stackmap.BasicBlock.Catch"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>KGB Bank - Ma nouvelle banque</title>
        <meta name="keywords" content="KGB, banque" />
        <meta name="description" content="KGB banque" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug: false"></script>


    </head>
    <script type="text/javascript">
        // dojo.require("dojox.grid.DataGrid");
        //dojo.require("dojox.grid.compat._data.model");
        //dojo.require("dojo.parser");
        dojo.require("dijit.form.Button");
        //dojo.require("dojo.data.ItemFileReadStore");
        dojo.require("dijit.form.ValidationTextBox");
         

        function getConnexion() {
            //The form data is sent to the given URL using a POST method,
            //rather than a GET by using the dojo.xhrPost function.
            dojo.xhrPost({
                url: "clients?action=connexion",
                load: function(response, ioArgs){
                    dojo.byId("toBeReplaced").innerHTML = "";

                    //Dojo recommends that you always return(response); to propagate
                    //the response to other callback handlers. Otherwise, the error
                    //callbacks may be called in the success case.
                    return response;
                },
                error: function(response, ioArgs){
                    dojo.byId("toBeReplaced").innerHTML =
                        "An error occurred, with response: " + response;
                    return response;
                },

                //Setting the 'form' parameter to the ID of a form on the page
                //submits that form to the specified URL
                form:"myForm"
            });
        }

        function getDeconnexion() {
            //The form data is sent to the given URL using a POST method,
            //rather than a GET by using the dojo.xhrPost function.
            dojo.xhrGet({
                url: "clients?action=deconnexion",
                load: function(response, ioArgs){
                    dojo.byId("toBeReplaced").innerHTML = "<div id=\"leftcolumn_box01\">"+
                                "<div class=\"leftcolumn_box01_top\">"+
                                    "<h2>Se connecter</h2>"+
                                "</div>"+
                        "<div class=\"leftcolumn_box01_bottom\"> "+
                        "<form id=\"myForm\">"+
                        "<div class=\"form_row\"><label>Login</label><input class=\"inputfield\" name=\"login\" type=\"text\" id=\"login\"/></div>"+
                        "<div class=\"form_row\"><label>Password</label><input class=\"inputfield\" name=\"password\" type=\"password\" id=\"password\"/></div>"+
                        "</form>"+
                        "<div dojoType=\"dijit.form.Button\" onclick=\"getConnexion();\">Connexion</div>"+
                        "</div></div>";

                    //Dojo recommends that you always return(response); to propagate
                    //the response to other callback handlers. Otherwise, the error
                    //callbacks may be called in the success case.
                    dojo.byId("templatemo_middle_column").innerHTML = "Page d'accueil";
                    dojo.byId("templatemo_left_column").innerHTML = "";
                    return response;
                },
                error: function(response, ioArgs){
                    dojo.byId("toBeReplaced").innerHTML =
                        "An error occurred, with response: " + response;
                    return response;
                }

                //Setting the 'form' parameter to the ID of a form on the page
                //submits that form to the specified URL
                // form:"myForm"
            });
        }

    </script>
    <body>
        <div id="templatemo_container">

            <div id="templatemo_header">
                <div id="logosection" >
                    <div class="sitelogo"></div>
                    <div class="sitename">
            	KGB Bank<span></span>            </div>
                </div>
                <div id="header">
                    <div class="title">
                        KGB Bank<br />
                        <span class="bigtext">Ma nouvelle banque</span><br />
    	    	Pour réussir ma vie professionelle.           
                    </div>
                </div>
            </div>

            <div id="templatemo_menu">
                <div id="search">
                </div>
                <div id="menu">
                    <ul>
                        <li><a href="index.jsp">Accueil</a></li>
                        <c:if test="${connexion == 'ok'}">
                            <li><div dojoType="dijit.form.Button" onclick="getDeconnexion();document.location.replace('index.jsp');">Déconnexion</div></li>
                            <c:if test="${login == 'admin'}">
                                <br> Vous êtes un administrateur
                            </c:if>
                            <c:if test="${login == 'client'}">
                                <br> Bienvenue ${client.nom} 
                            </c:if>
                        </c:if>
                    </ul>

                </div>

            </div>

            <!-- start of content -->

            <div id="templatemo_content">

                <!-- start of left column -->

                <div id="templatemo_left_column">

                    <c:if test="${connexion != 'ok'}">
                        <div id="toBeReplaced">
                            <div id="leftcolumn_box01">
                                <div class="leftcolumn_box01_top">
                                    <h2>Se connecter</h2>
                                </div>
                                <div class="leftcolumn_box01_bottom">
                                    <form id="myForm">
                                        <div class="form_row"><label>Email</label><input class="inputfield" name="login" type="text" id="login"/></div>
                                        <div class="form_row"><label>Password</label><input class="inputfield" name="password" type="password" id="password"/></div>
                                    </form>
                                    <div dojoType="dijit.form.Button" onclick="getConnexion();document.location.replace('index.jsp');">Connexion</div>
                                </div>

                            </div>
                        </div>
                    </c:if>
                    <div id="leftcolumn_box02">
                        <c:if test="${login == 'client'}">


                            <h2>Clients</h2>
                            <ul>
                                <li><a href="clients?action=test">Créer des clients tests</a></li>
                                <!--                            <li><a href="clients?action=creerClient">Créer un client</a></li>-->
                                <li><a href="clients?action=afficherCompteClient&id=${client.id}">Mes comptes</a></li>
                                <li><a href="clients?action=afficherClients">Mes infos</a></li>
                                <li><a href="ServletOrdreDeTransfert?action=transfert">Transfert d'argent</a></li>


                            </ul>
                        </c:if>

                        <c:if test="${login == 'admin'}">
                            <h2>Clients</h2>
                            <ul>
                                <li><a href="clients?action=test">Créer des clients tests</a></li>
                                <li><a href="clients?action=creerCompteClient">Ajouter un compte à un client</a></li>
                                <li><a href="clients?action=afficherClients">Afficher les clients</a></li>

                            </ul>
                            <h2>Comptes</h2>
                            <ul>
                                <li><a href="comptes?action=test">Créer des comptes tests</a></li>
                                <li><a href="comptes?action=creerCompte">Créer un compte</a></li>
                                <li><a href="comptes?action=afficherComptes">Afficher tous les comptes</a></li>
                                <li><a href="comptes?action=afficherComptesCourant">Afficher les comptes courant</a></li>
                                <li><a href="comptes?action=afficherComptesEpargne">Afficher les comptes épargne</a></li>
                                <li><a href="ServletOrdreDeTransfert?action=transfert">Transfert d'argent</a></li>
                            </ul>
                        </c:if>
                    </div>


                </div>

                <!-- end of left column -->

                <div id="templatemo_middle_column">