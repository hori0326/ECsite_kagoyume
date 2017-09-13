<%-- 
    Document   : registrationcomplete
    Created on : 2017/09/01, 11:37:19
    Author     : hori
--%>

<%@page import="kagoyume.Helper"%>
<%@page import="kagoyume.UserDataDTO"%>

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
    // registrationInfoResult:ユーザー新規登録時に入力した情報(ユーザー名・パスワード・メールアドレス・住所)　[RegistrationComplete.java]で登録
    UserDataDTO userResultDTO = (UserDataDTO) request.getAttribute("registrationInfoResult");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会員登録結果 ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/registrationcomplete.jsp") %>
    <h1>会員登録結果</h1>
    ユーザー名：<%= userResultDTO.getName() %><br>
    パスワード：<%= userResultDTO.getPassword() %><br>
    メールアドレス：<%= userResultDTO.getMail() %><br>
    住所：<%= userResultDTO.getAddress() %><br>
    <br>
    以上の内容で登録しました。<br>
</body>
</html>
