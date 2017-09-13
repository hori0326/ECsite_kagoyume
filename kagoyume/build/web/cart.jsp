<%-- 
    Document   : cart
    Created on : 2017/09/07, 13:15:21
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
    <title>買い物かご一覧 ‐ かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/cart.jsp") %>
    <h1>買い物かご一覧</h1>
    <%
        if(cartItem.size() != 0) {
            out.println("<table border=\"1\">");
            int totalMoney = 0;  // 買い物かご内の合計金額
            for(int i = 0; i < cartItem.size(); i++) { 
                totalMoney += Integer.parseInt(cartItem.get(i).getPriceDetail());
                out.println("<tr>");
                out.println("<td><img src=\"" + cartItem.get(i).getImageDetail() + "\"></td>");
                out.println("<td>");
                out.println("<b><a href=\"Item?itemcode=" + cartItem.get(i).getCodeDetail() + "\">" + cartItem.get(i).getNameDetail() + "</a><br>");
                out.println(cartItem.get(i).getPriceDetail() + "円</b><br><br>");
                out.println("<a href=\"Cart?delete=" + i + "\">削除</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<p>");
            out.println("数量：" + cartItem.size() + "点<br>");
            out.println("金額：" + totalMoney + "円");
            out.println("</p>");
            out.println("<form action=\"buyconfirm.jsp\"  method=\"POST\">");
            out.println("<input type=\"submit\" value=\"購入画面へ\">");
            out.println("</form>");
        } else { out.println("買い物カゴの中に商品はありません。"); }
    %>
</body>
</html>
