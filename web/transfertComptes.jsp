

<%@include file="entete.jsp" %>

<h1>Transfert d'argent</h1>
 ${message}
<form method="post" action="ServletOrdreDeTransfert?action=transfert">
    <table>
        <tr>
            <td>Premier compte </td>

            <td>
                <select name="compte1">
                    <option value=""></option>
                    <c:if test="${login == 'admin'}">
                        <c:forEach var="u" items="${requestScope['comptes']}">
                            <option value="${u.id}"> ${u.client.nom}&nbsp;${u.client.prenom} ${u.typeCompte} -- ${u.solde}&euro;</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${login == 'client'}">
                        <c:forEach var="u" items="${requestScope['comptes']}">
                            <c:if test="${u.client.email == client.email}">
                                <option value="${u.id}"> ${u.client.nom}&nbsp;${u.client.prenom} ${u.typeCompte} -- ${u.solde}&euro;</option>
                            </c:if>
                        </c:forEach>
                    </c:if>

                </select>
            </td>
        </tr>
        <tr>
            <td>Deuxi&egrave;me compte </td>
            <td>
                <select name="compte2">
                    <option value=""></option>
                    <c:if test="${login == 'admin'}">
                        <c:forEach var="u" items="${requestScope['comptes']}">
                            <option value="${u.id}"> ${u.client.nom}&nbsp;${u.client.prenom} ${u.typeCompte} -- ${u.solde}&euro;</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${login == 'client'}">
                        <c:forEach var="u" items="${requestScope['comptes']}">
                            <c:if test="${u.client.email == client.email}">
                                <option value="${u.id}"> ${u.client.nom}&nbsp;${u.client.prenom} ${u.typeCompte} -- ${u.solde}&euro;</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
        </tr>
        <tr>
            <td>Montant &agrave; transf&eacute;rer:  </td>
            <td>
                <input type="text" name="montant" value="" >
            </td>
        </tr>
        <tr><td colspan="2" align="center">
                <input class="button" type="submit" value="Valider">
            </td></tr>
    </table>
</form>
<br>
<a href="index.jsp">Retour au menu principal</a>
<%@include file="pied.jsp" %>
