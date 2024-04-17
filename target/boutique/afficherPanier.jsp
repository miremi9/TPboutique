<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Boutique2</title>
  </head>
  <body>
    <h4>${sessionScope.USER.name}</h4>
    <a href="connexion">Déconnexion</a>
    <h1>Contenu du panier</h1>
      <fieldset>
        <legend>Panier</legend>
        <table>
          <thead>
            <tr>
              <th scope="col">Article</th>
              <th scope="col">Prix</th>
              <th scope="col">Quantité</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${articles}" var="articleEntry">
              <c:set var="article" value="${articleEntry.key}" />
              <c:set var="quantity" value="${articleEntry.value}" />
              <tr>
                  <td>${article.name}</td>
                  <td>${article.price}</td>
                  <td>${quantity}</td>
              </tr>
          </c:forEach>
          </tbody>
        </table>
        <h5>Le prix total est :${sessionScope.USER_CART.cartPrice}</h5>
      </fieldset>
      <input type="submit" value="Payer" />
    <form action="articles" method="post">
      <input type="submit" value="Retour" />
    </form>
    
  </body>
</html>

<style>
  table {
    border-collapse: collapse;
    border: 1px solid rgb(140 140 140);
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