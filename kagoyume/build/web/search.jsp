<%-- 
    Document   : search
    Created on : 2017/09/05, 17:42:50
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>
<%@page import="kagoyume.ItemData"%>
<%@page import="java.util.List"%>

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
    // searchResult:商品の検索結果(検索キーワード・検索結果数・商品コード・商品名・画像URL・価格)　[Search.java]で登録
    ItemData item = (ItemData) hs.getAttribute("searchResult");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>「<%= item.getQuery() %>」の検索結果 ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/search.jsp") %>
    検索キーワード：<%= item.getQuery() %><br>
    検索結果数：<%= item.getTotalResult() %>件<br>
    <br>
    <table border="1">
        <%
            // 検索上位20件が表示される(YahooAPIの仕様)
            for(int i = 0; i < item.getName().size(); i++) {
                out.println("<tr>");
                out.println("<td><img src=\"" + item.getImage().get(i) + "\"></td>");
                out.println("<td><b>");
                out.println("<a href=\"Item?itemcode=" + item.getCode().get(i) + "\">" + item.getName().get(i) + "</a><br>");
                out.println("<font color=\"red\">" + item.getPrice().get(i) + "円</font>");
                out.println("</b></td>");
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>