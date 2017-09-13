<%-- 
    Document   : registration
    Created on : 2017/08/31, 11:57:47
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>
<%@page import="kagoyume.UserData"%>

<%
    HttpSession hs = request.getSession();
    // loginCheck:ログイン中の場合は"OK"の文字列を格納　[Login.java]で登録
    String loginCheck = (String) session.getAttribute("loginCheck");
    String userName = "";
    boolean isLogin = false;
    // loginUser:ログイン中のユーザー情報(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)　[Login.java]で登録
    UserDataDTO userDTO = (UserDataDTO) hs.getAttribute("loginUser");
    if(loginCheck != null && loginCheck.equals("OK")) {
        userName = userDTO.getName();
        isLogin = true;
    }

    UserData userBeans = null;
    boolean reInput = false;
    // 再入力 or 新規入力を判定
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("reInput")) {
        reInput = true;
        // registrationInfo:ユーザー新規登録時に入力した情報(ユーザー名・パスワード・メールアドレス・住所)　[RegistrationConfirm.java]で登録
        userBeans = (UserData) hs.getAttribute("registrationInfo");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会員登録 ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/registration.jsp") %>
    <h1>会員登録</h1>
    <form action="RegistrationConfirm" method="POST">
    <input type ="text" name="name" placeholder="ユーザー名" value="<% if(reInput) { out.print(userBeans.getName()); } %>"><br>
    <input type="password" name="password" placeholder="パスワード" value="<% if(reInput) { out.print(userBeans.getPassword()); } %>"><br>
    <input type="text" name="mail" placeholder="メールアドレス" value="<% if(reInput) { out.print(userBeans.getMail()); } %>"><br>
    <input type="text" name="address" placeholder="住所" value="<% if(reInput) { out.print(userBeans.getAddress()); } %>"><br>
    <br>
    <input type="submit" value="確認画面へ">
    </form>
</body>
</html>