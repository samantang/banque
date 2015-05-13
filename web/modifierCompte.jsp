

<%@include file="entete.jsp" %>

<!-- start of middle column -->

<h1>Modification du Client</h1>
${message}

<form id="form" name="compte" method="post" action="comptes?action=modifierCompte&modifId=${modifId}">
    <br>
    <table  border="1" cellspacing="1" cellpadding="1" align="center">
        <tr>
            <th width="32%" align="center" valign="middle" scope="row"> ID Compte à modifier </th>
            <td width="68%" align="center" valign="middle">${modifId} </td>
        </tr>
        <tr>
            <th width="32%" align="center" valign="middle" scope="row">TYPE Actuel </th>
            <td width="68%" align="center" valign="middle">${compte.typeCompte}</td>
        </tr>
        <tr>
            <th align="center" valign="middle" scope="row">Nouveau Solde</th>
            <td align="center" valign="middle">
                <label>
                    <input type="text" name="solde" id="solde" value="${compte.solde}"/>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="3" align="center"><input class="button" type="submit" name="modifier" id="modifier" value="Modifier" /></td>
        </tr>
    </table>
</form>
<br>
<a href="index.jsp">Retour au menu principal</a>


<!-- end of middle column -->

<%@include file="pied.jsp" %>

