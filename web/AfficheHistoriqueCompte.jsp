<%@include file="entete.jsp" %>

<!-- start of middle column -->

<h1>Historique Opérations bancaires du compte ${compte.id} appartenant à ${compte.client.nom} ${compte.client.prenom}</h1>
<br>
<table cellspacing="1" border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Montant</th>
    </tr>

    <c:forEach var="u" items="${requestScope['operations']}">
        <tr>
            <td align="center">${u.dateOperation}</td>
            <td align="center">${u.description}</td>
            <td align="center">${u.montant}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="index.jsp">Retour au menu principal</a>

<!-- end of middle column -->

<%@include file="pied.jsp" %>

