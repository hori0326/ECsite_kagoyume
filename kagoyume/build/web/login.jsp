<%-- 
    Document   : login
    Created on : 2017/08/29, 15:53:10
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>

<%
    // loginFailure:ログイン失敗時、"failure"の文字列が格納されている　[Login.java]で登録
    String loginFailure = (String) request.getAttribute("loginFailure");
    // PleaseLogin:ログインせずに買い物カゴの中身を見ようとした時、"PleaseLogin"が格納されている　[Login.java]で登録
    String PleaseLogin = (String) request.getAttribute("PleaseLogin");
    // ログイン画面に移動する前のページ名が格納されている。[Helper.java]からパラメーターを送信。
    String returnPage = request.getParameter("returnPage");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ログイン ‐ かごゆめ</title>
</head>
<body>
    <%
        if(loginFailure != null && loginFailure.equals("failure")) {
            out.println("<p>ログインに失敗しました。</p>");
        }
        // ログインせずに買い物かごを見ようとした場合。ログイン成功後は買い物かごページに戻る
        if(PleaseLogin != null && PleaseLogin.equals("PleaseLogin")) {
           out.println("<p>ログインしてください。</p>");
           returnPage = "/cart.jsp";
        }
    %>
    <form action="Login?returnPage=<%= returnPage %>" method="POST">
        <input type="text" name="name" value="test" placeholder="ユーザー名"><br>
        <input type="password" name="password" value="test" placeholder="パスワード"><br>
        <br>
        <input type="submit" value="ログイン">
    </form>
    <br>
    <a href="registration.jsp">[新規会員登録]</a>
</body>
</html>