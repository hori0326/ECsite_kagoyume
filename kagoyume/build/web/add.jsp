<%-- 
    Document   : add
    Created on : 2017/09/07, 11:56:27
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
    <title>「<%= itemDetail.getNameDetail() %>」をカートに追加 ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/add.jsp") %>
    <p>カートに追加しました</p>
    <form action="search.jsp">
        <input type="submit" value="検索結果に戻る">
    </form>
    <br>
    <form action="Cart">
        <input type="submit" value="カートの中身を見る">
    </form>
</body>
</html>
