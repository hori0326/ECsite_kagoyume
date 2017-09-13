<%-- 
    Document   : top
    Created on : 2017/08/29, 15:17:32
    Author     : hori
--%>

<%@page import="kagoyume.UserDataDTO"%>
<%@page import="kagoyume.Helper"%>

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
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>かごゆめ</title>
</head>
<body>
    <%= Helper.getInstance().menu(isLogin, userName, "/top.jsp") %>
    <h1>かごゆめ</h1>
    <p>
        ショッピングサイトを使っている時、このような経験はありませんか？<br>
        <br>
        「あれいいな」「これいいな」「あっ、関連商品のこれもいい」「20%OFFセールだって！？買わなきゃ！」...<br>
        そしていざ『買い物かご』を開いたとき、その合計金額に愕然とします。<br>
        「こんなに買ってたのか・・・仕方がない。いくつか減らそう・・・」<br>
        <br>
        仕方がありません。無駄遣いは厳禁です。<br>
        でも、一度買うと決めたものを諦めるなんて、ストレスではありませんか？<br>
        できればお金の事を考えずに好きなだけ買い物がしたい・・・。<br>
        <br>
        このサービスは、そんなフラストレーションを解消するために生まれた、<br>
        『金銭取引が絶対に発生しない』『いくらでも、どんなものでも購入できる(気分になれる)』ECサイトです。
    </p>
    <form action="Search">
        <input type="text" name="query" placeholder="何をお探しですか？" required>
        <select name="category_id">
            <option value="1">すべてのカテゴリから</option>
            <option value="13457">ファッション</option>
            <option value="2498">食品</option>
            <option value="2500">ダイエット、健康</option>
            <option value="2501">コスメ、香水</option>
            <option value="2502">パソコン、周辺機器</option>
            <option value="2504">AV機器、カメラ</option>
            <option value="2505">家電</option>
            <option value="2506">家具、インテリア</option>
            <option value="2507">花、ガーデニング</option>
            <option value="2508">キッチン、生活雑貨、日用品</option>
            <option value="2503">DIY、工具、文具</option>
            <option value="2509">ペット用品、生き物</option>
            <option value="2510">楽器、趣味、学習</option>
            <option value="2511">ゲーム、おもちゃ</option>
            <option value="2497">ベビー、キッズ、マタニティ</option>
            <option value="2512">スポーツ</option>
            <option value="2513">レジャー、アウトドア</option>
            <option value="2514">自転車、車、バイク用品</option>
            <option value="2516">CD、音楽ソフト</option>
            <option value="2517">DVD、映像ソフト</option>
            <option value="10002">本、雑誌、コミック</option>
        </select>
        <select name="sort">
            <option value="-score">おすすめ順</option>
            <option value="-sold">売れている順</option>
            <option value="+price">安い順</option>
            <option value="-price">高い順</option>
        </select>
        <input type="submit" value="検索">
    </form>
</body>
</html>
