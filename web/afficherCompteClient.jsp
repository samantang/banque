<%@include file="entete.jsp" %>



<h1>Comptes client ${client.nom} &nbsp; ${client.prenom}</h1>
<br>
<table cellspacing="1" border="1">
    <tr>
        <th>Type compte</th>
        <th>Solde</th>
        <th>Historique</th>

    </tr>

    <c:forEach var="u" items="${requestScope['comptesClient']}">
        <tr>
            <td align="center">${u.typeCompte}</td>
            <td align="center">${u.solde}</td>
            <td align="center"><a href="comptes?action=detaillerCompte&detailId=${u.id}">Détails</a></td>
        </tr>
    </c:forEach>

</table>
<br>
<a href="index.jsp">Retour au menu principal</a>

<%@include file="pied.jsp" %>
