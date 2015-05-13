

<%@include file="entete.jsp" %>

<h1>Création d'un client</h1>
<br>
${message}
<form method="post" action="client?action=creerClient">
    <table>
        <tr>
            <td>Nom &nbsp;&nbsp;</td>

            <td>
                <input type="text" name="nom" size="30">
            </td>
        </tr>
        <tr>
            <td>Prenom &nbsp;&nbsp;</td>

            <td>
                <input type="text" name="prenom" size="30">
            </td>
        </tr>
        <tr>
            <td>Adresse &nbsp;&nbsp;</td>

            <td>
                <input type="text" name="adresse" size="30">
            </td>
        </tr>
        <tr>
            <td>Email &nbsp;&nbsp;</td>

            <td>
                <input type="text" name="email" size="30">
            </td>
        </tr>
        <tr>
            <td>Téléphone &nbsp;&nbsp;</td>

            <td>
                <input type="text" name="telephone" size="30">
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