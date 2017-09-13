<%-- 
    Document   : buyconfirm
    Created on : 2017/09/07, 14:48:58
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>
<%@page import="java.util.ArrayList"%>
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
    // cartItem:カート内の商品詳細情報が配列で格納(商品コード・商品名・画像URL・価格・商品説明・評価点・レビュー件数)　[Add.java][Cart.java]で登録
    ArrayList<ItemData> cartItem = (ArrayList<ItemData>) hs.getAttribute("cartItem");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>購入確認画面 ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/buyconfirm.jsp") %>
    <h1>購入商品一覧</h1>
    <table border="1">
        <% 
            int totalMoney = 0;  // カート内の合計金額
            for(int i = 0; i < cartItem.size(); i++) { 
                totalMoney += Integer.parseInt(cartItem.get(i).getPriceDetail());
                out.println("<tr><td>");
                out.println(cartItem.get(i).getNameDetail() + "<br>");
                out.println(cartItem.get(i).getPriceDetail() + "円");
                out.println("</td></tr>");
            }
        %>
    </table>
    <p>金額：<%= totalMoney %>円</p>
    <form action="BuyComplete" method="POST">
        発送方法：<br>
        <label><input type="radio" name="type" value="1" checked="checked">ヤマト運輸</label>
        <label><input type="radio" name="type" value="2">佐川急便</label><br><br>
        <input type="hidden" name="totalMoney" value="<%= totalMoney %>">
        <input type="submit" value="購入">
    </form><br>
    <form action="cart.jsp" method="POST">
        <input type="submit" value="カートに戻る">
    </form>
</body>
</html>
