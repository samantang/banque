
<%@include file="entete.jsp" %>



<h1>Listes des comptes</h1>
<br>
${message}
<br>

<table cellspacing="1" border="1">
    <tr>
        <th>ID</th>
        <th>Nom propriétaire</th>
        <th>Type de compte</th>
        <th>Solde</th>
        <th>Détails des opérations</th>
        <th>Supprimer</th>
        <th>Modifier</th>
    </tr>

    <c:if test="${param['action'] == 'afficherComptes'}" >

        <c:forEach var="u" items="${requestScope['comptes']}">
            <tr>
                <td align="center">${u.id}</td>
                <td align="center">${u.client.nom}&nbsp; ${u.client.prenom}</td>
                <td align="center">${u.typeCompte}</td>
                <td align="center">${u.solde}</td>
                <td align="center"><a href="comptes?action=detaillerCompte&detailId=${u.id}">Détails</a></td>
                <td align="center"><a href="comptes?action=supprimerCompte&supprId=${u.id}">Suppr</a></td>
                <td align="center"><a href="comptes?action=modifierCompte&modifId=${u.id}">Modifier</a></td>
            </tr>
        </c:forEach>
    </c:if>
    <c:forEach var="u" items="${requestScope['comptes']}">
        <c:if test="${u.typeCompte=='COMPTE_COURANT'}">
            <c:if test="${param['action'] == 'afficherComptesCourant'}" >
                <tr>
                    <td align="center">${u.id}</td>
                    <td align="center">${u.client.nom}&nbsp; ${u.client.prenom}</td>
                    <td align="center">${u.typeCompte}</td>
                    <td align="center">${u.solde}</td>
                    <td align="center"><a href="comptes?action=detaillerCompte&detailId=${u.id}">Détails</a></td>
                    <td align="center"><a href="comptes?action=supprimerCompte&supprId=${u.id}">Suppr</a></td>
                    <td align="center"><a href="comptes?action=modifierCompte&modifId=${u.id}">Modifier</a></td>
                </tr>
            </c:if>
        </c:if>
    </c:forEach>
    <c:forEach var="u" items="${requestScope['comptes']}">
        <c:if test="${u.typeCompte=='COMPTE_EPARGNE'}">
            <c:if test="${param['action'] == 'afficherComptesEpargne'}" >
                <tr>
                    <td align="center">${u.id}</td>
                    <td align="center">${u.client.nom}&nbsp; ${u.client.prenom}</td>
                    <td align="center">${u.typeCompte}</td>
                    <td align="center">${u.solde}</td>
                    <td align="center"><a href="comptes?action=detaillerCompte&detailId=${u.id}">Détails</a></td>
                    <td align="center"><a href="comptes?action=supprimerCompte&supprId=${u.id}">Suppr</a></td>
                    <td align="center"><a href="comptes?action=modifierCompte&modifId=${u.id}">Modifier</a></td>
                </tr>
            </c:if>
        </c:if>
    </c:forEach>
    <tr>
        <td align="center">Total</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">${soldes}</td>
        <td align="center"></td>
    </tr>
</table>

<br>
<a href="index.jsp">Retour au menu principal</a>


<!-- end of middle column -->

<%@include file="pied.jsp" %>
