<%-- 
    Document   : mydata
    Created on : 2017/09/09, 16:13:37
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
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会員情報 ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/mydata.jsp") %>
    ユーザー名：<%= userDTO.getName() %><br>
    パスワード：<%= userDTO.getPassword() %><br>
    メールアドレス：<%= userDTO.getMail() %><br>
    住所：<%= userDTO.getAddress() %><br>
    総購入金額：<%= userDTO.getTotal() %>円<br>
    登録日時：<%= userDTO.getNewDate() %><br><br>
    <a href="MyHistory">購入履歴</a><br>
    <a href="./myupdate.jsp">会員情報更新</a><br>
    <a href="./mydelete.jsp">会員情報削除</a><br>
</body>
</html>
