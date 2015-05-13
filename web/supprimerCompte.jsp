

<%@include file="entete.jsp" %>

<!-- start of middle column -->

<div id="templatemo_middle_column">

    <h1>Supprimer un compte bancaire</h1>
    ${message}
    <form method="post" action="comptes?action=supprimerCompte">
         <br>
        <table>
            <tr>
                <td>Compte à supprimer </td>

                <td>
                    <select name="compte">
                        <option value=""></option>
                        <c:forEach var="u" items="${requestScope['comptes']}">
                            <option value="${u.id}"> ${u.client.nom}&nbsp; ${u.client.prenom} &nbsp; ${u.typeCompte}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>

            <tr><td colspan="2" align="center">
                    <input class="button" type="submit" value="Valider">
                </td></tr>
        </table>
    </form>
    <br>
    <a href="index.jsp">Retour au menu principal</a>

</div>

<!-- end of middle column -->

<%@include file="pied.jsp" %>