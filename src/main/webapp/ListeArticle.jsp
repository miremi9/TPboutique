<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Boutique</title>
    <style>
      table {
        border-collapse: collapse;
        border: 2px solid rgb(140 140 140);
        font-family: sans-serif;
        font-size: 0.8rem;
        letter-spacing: 1px;
      }

      thead {
        background-color: rgb(228 240 245);
      }

      th,
      td {
        border: 1px solid rgb(160 160 160);
        padding: 8px 10px;
      }

      td:last-of-type {
        text-align: center;
      }

      tbody > tr:nth-of-type(even) {
        background-color: rgb(237 238 242);
      }

    </style>

  </head>
  <body>
    <h4>
      <a href="connexion">Déconnexion</a>
      <c:out value="${sessionScope.user.name}" />
  </h4>
    <h1>Liste des articles</h1>
    
      <fieldset>
        <legend>Articles</legend>
        <table>
          <tr>
            <th>Nom</th>
            <th>Prix</th>
            <th>Restant</th>
            <th>Enlever 1 au panier</th>
            <th>Actuellement dans le panier</th>
            <th>Ajouter 1 au panier</th>
          </tr>
          <c:forEach items="${requestScope.articles}" var="article">
            <tr>
              <td>${article.name}</td>
              <td>${article.price}</td>
              <td>${article.nbRestant}</td>
              <td>
                  <form action="articles" method="post">
                  <!-- Ajoutez les champs de formulaire nécessaires ici -->
                  <input type="hidden" name="article2remove" value=${article.id} />              
                  <!-- Bouton pour soumettre le formulaire -->
                  <button type="submit" >-</button>
                </form>
            </td>
              <td>${sessionScope.USER_CART.getNumberArticle(article)}</td>
              <td>
                <form action="articles" method="post">
                  <!-- Ajoutez les champs de formulaire nécessaires ici -->
                  <input type="hidden" name="article2add" value=${article.id} />              
                  <!-- Bouton pour soumettre le formulaire -->
                  <button type="submit">+</button>
                </form>
              </td>
            </tr>
          </c:forEach>
        </table>
      </fieldset>
      <form action="panier" method="post">
      <input type="submit" value="Afficher le panier" />
    </form>
  </body>
</html>