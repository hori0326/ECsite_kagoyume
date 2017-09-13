<%-- 
    Document   : error
    Created on : 2017/08/30, 13:05:27
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>

<%
    // セッション開始
    HttpSession hs = request.getSession();
    // loginCheck:ログイン中の場合は"ログイン中"という文字列が格納されている　[Login.java]で登録
    String loginCheck = (String) session.getAttribute("loginCheck");
    String userName = "";
    boolean isLogin = false;
    
    if(loginCheck != null && loginCheck.equals("OK")) {
        // loginUser:ログイン中のユーザー情報(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)　[Login.java]で登録
        UserDataDTO userDTO = (UserDataDTO) hs.getAttribute("loginUser");
        userName = userDTO.getName();
        isLogin = true;
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>error</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/error.jsp") %>
    エラーが発生しました。以下の項目を確認してください。<br>
    <%= request.getAttribute("error") %><br><br>
</body>
</html>
