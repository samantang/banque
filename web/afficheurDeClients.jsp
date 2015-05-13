<%@include file="entete.jsp" %>

<!-- start of middle column -->



${modifId}

<h1>Listes des clients</h1>
<br>
<table cellspacing="1" border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Email</th>
        <th>Adresse</th>
        <th>Téléphone</th>
        <c:if test="${login == 'admin'}">
            <th>Supprimer</th>
        </c:if>
        <th>Modifier</th>
        <th>Comptes</th>
    </tr>
    <c:if test="${login == 'admin'}">
        <c:forEach var="u" items="${requestScope['clients']}">
            <tr>
                <td align="center">${u.id}</td>
                <td align="center">${u.nom}</td>
                <td align="center">${u.prenom}</td>
                <td align="center">${u.email}</td>
                <td align="center">${u.adresse}</td>
                <td align="center">${u.telephone}</td>
                <td align="center"><a href="clients?action=supprimerClient&supprId=${u.id}">Suppr</a></td>
                <td align="center"><a href="clients?action=modifierClient&modifId=${u.id}">Modifier</a></td>
                <td align="center"><a href="clients?action=afficherCompteClient&id=${u.id}">Voir</a></td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${login == 'client'}">
        <tr>
            <td align="center">${client.id}</td>
            <td align="center">${client.nom}</td>
            <td align="center">${client.prenom}</td>
            <td align="center">${client.email}</td>
            <td align="center">${client.adresse}</td>
            <td align="center">${client.telephone}</td>
            <td align="center"><a href="clients?action=modifierClient&modifId=${client.id}">Modifier</a></td>
            <td align="center"><a href="clients?action=afficherCompteClient&id=${client.id}">Voir</a></td>
        </tr>
    </c:if>


</table>
<br>
<a href="index.jsp">Retour au menu principal</a>


<!-- end of middle column -->

<%@include file="pied.jsp" %>
