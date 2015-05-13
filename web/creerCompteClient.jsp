

<%@include file="entete.jsp" %>
<!-- start of middle column -->

<div id="templatemo_middle_column">

    <h1>Ajout d'un nouveau compte à un client</h1>
    <br>
    ${message}
    <br>

    <form method="post" action="clients?action=creerCompteClient">

        <table>
            <tr>
                <td align="left">Choississez un client </td>
                <td>
                    <select name="clientID">
                        <option value=""></option>
                        <c:forEach var="u" items="${requestScope['clients']}">
                            <option value="${u.id}"> ${u.nom}&nbsp;${u.prenom}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="left">Type</td>
                <td> <select name="typeCompte">
                        <option value="courant">Compte courant</option>
                        <option value="epargne">Compte épargne</option>

                    </select>
                </td>
            </tr>
            <tr>
                <td align="left">Solde &nbsp;&nbsp;</td>
                <td>
                    <input type="text" name="solde" size="30">
                </td>
            </tr>  
            <tr>
                <td colspan="2" align="center">
                <input class="button" type="submit" value="Valider">
                </td>
            </tr>
        </table>
    </form><br>
    <a href="index.jsp">Retour au menu principal</a>

</div>
<!-- end of middle column -->
<%@include file="pied.jsp" %>