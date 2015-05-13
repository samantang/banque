

<%@include file="entete.jsp" %>

<!-- start of middle column -->

<h1>Modification du Client</h1>
${message}

<form id="form" name="client" method="post" action="clients?action=modifierClient&modifId=${modifId}">
    <br>
    <table  border="1" cellspacing="1" cellpadding="1" align="center">
        <tr>
            <th width="32%" align="center" valign="middle" scope="row"> ID Client à modifier </th>
            <td width="68%" align="center" valign="middle">${modifId}</td>
        </tr>
        <tr>
            <th width="32%" align="center" valign="middle" scope="row">Nouveau NOM</th>
            <td width="68%" align="center" valign="middle">
                <label>
                    <input type="text" name="nom" id="nom" value="${client.nom}"/>
                </label>
            </td>
        </tr>
        <tr>
            <th align="center" valign="middle" scope="row">Nouveau PRENOM</th>
            <td align="center" valign="middle">
                <label>
                    <input type="text" name="prenom" id="prenom" value="${client.prenom}"/>
                </label>
            </td>
        </tr>
        <tr>
            <th align="center" valign="middle" scope="row">Nouvelle ADRESSE</th>
            <td align="center" valign="middle">
                <label>
                    <input type="text" name="adresse" id="adresse"value="${client.adresse}" />
                </label>
            </td>
        </tr>
        <tr>
            <th align="center" valign="middle" scope="row">Nouvel EMAIL</th>
            <td align="center" valign="middle">
                <label>
                    <input type="text" name="email" id="email" value="${client.email}"/>
                </label>
            </td>
        </tr>
        <tr>
            <th align="center" valign="middle" scope="row">Nouveau TELEPHONE</th>
            <td align="center" valign="middle">
                <label>
                    <input type="text" name="telephone" id="telephone" value="${client.telephone}"/>
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

