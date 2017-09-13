<%-- 
    Document   : registrationconfirm
    Created on : 2017/08/31, 13:21:05
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kagoyume.UserData"%>

<%
    Helper helper = Helper.getInstance();
    
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
    
    // registrationInfo:ユーザー新規登録時に入力した情報(ユーザー名・パスワード・メールアドレス・住所)　[RegistrationConfirm.java]で登録
    UserData userBeans = (UserData) hs.getAttribute("registrationInfo");
    
    // ユーザー新規登録に未入力項目があるか確認
    ArrayList<String> checkList = userBeans.checkProperties();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>会員登録確認 ‐ かごゆめ</title>
</head>
<body>
    <%= helper.menu(isLogin, userName, "/registrationcomplete.jsp") %>
    <!--未入力なし-->
    <% if(checkList.size() == 0) { %>
        <h1>会員登録確認</h1>
        ユーザー名：<%= userBeans.getName() %><br>
        パスワード：<%= userBeans.getPassword() %><br>
        メールアドレス：<%= userBeans.getMail() %><br>
        住所：<%= userBeans.getAddress() %><br>
        <br>
        上記の内容で登録します。よろしいですか？<br>
        <br>
        <form action="RegistrationComplete" method="POST">
            <input type="submit" name="yes" value="登録">
        </form>
    <!--未入力あり-->
    <% } else {%>
        <!--未入力項目を表示-->
        <%= helper.checkInput(checkList) %>
    <% } %>
    <form action="registration.jsp" method="POST">
        <!-- 再入力であることを表す値、reInputを送信 -->
        <input type="hidden" name="mode" value="reInput">
        <input type="submit" name="no" value="戻る">
    </form>
</body>
</html>