<%-- 
    Document   : item
    Created on : 2017/09/06, 17:58:18
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>
<%@page import="kagoyume.ItemData"%>

<%
    HttpSession hs = request.getSession();
    // loginCheck:ログイン中の場合は"OK"の文字列を格納　[Login.java]で登録
    String loginCheck = (String) session.getAttribute("loginCheck");
    String userName = "";
    boolean isLogin = false;
    if(loginCheck != null && loginCheck.equals("OK")) {
        // loginUser:ログイン中のユーザー情報(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)　[Login.java]で登録
        UserDataDTO userDTO = (UserDataDTO) hs.getAttribute("loginUser");
        userName = userDTO.getName();
        isLogin = true;
    }
    // searchResultDetail:商品の詳細情報を格納(商品コード・商品名・画像URL・価格・商品説明・評価点・レビュー件数)　[Item.java]で登録
    ItemData itemDetail = (ItemData) hs.getAttribute("searchResultDetail");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%= itemDetail.getNameDetail() %> ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/item.jsp") %>
    <h2><%= itemDetail.getNameDetail() %></h2>
    <img src="<%= itemDetail.getImageDetail() %>"><br>
    <b><font color="red"><%= itemDetail.getPriceDetail() %>円</font>（税込）</b><br><br>
    <%= itemDetail.getDescription() %><br><br>
    評価点：<%= itemDetail.getRate() %>pt<br>
    レビュー件数：<%= itemDetail.getCount() %>件<br><br>
    <form action="Add" method="POST">
        <input type="submit" value="カートに追加">
    </form>
</body>
</html>
