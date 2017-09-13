<%-- 
    Document   : myupdateresult
    Created on : 2017/09/01, 14:18:53
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
    // registrationInfoResult:ユーザー情報更新時に入力した情報(ユーザー名・パスワード・メールアドレス・住所)　[MyUpdateResult.java]で登録
    UserDataDTO userUpdateDTO = (UserDataDTO) request.getAttribute("updateInfo");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</tetle>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/myupdateresult.jsp") %>
    <h1>会員登録結果</h1>
    ユーザー名：<%= userUpdateDTO.getName() %><br>
    パスワード：<%= userUpdateDTO.getPassword() %><br>
    メールアドレス：<%= userUpdateDTO.getMail() %><br>
    住所：<%= userUpdateDTO.getAddress() %><br>
    <br>
    以上の内容で登録しました。<br>
</body>
</html>
