

<%@include file="entete.jsp" %>

<!-- start of middle column -->

<h1>Supprimer un compte bancaire</h1>
${message}
<form method="post" action="clients?action=supprimerClient">
    <table>
        <tr>
            <td>Client à supprimer </td>

            <td>
                <select name="client">
                    <option value=""></option>
                    <c:forEach var="u" items="${requestScope['clients']}">
                        <option value="${u.id}">${u.id}&nbsp;   ${u.nom} &nbsp;  ${u.prenom} </option>
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
<%@include file="pied.jsp" %>
