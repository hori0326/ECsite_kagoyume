package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.UserDataDTO;
import kagoyume.Helper;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
  
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>かごゆめ</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    ");
      out.print( Helper.getInstance().menu(isLogin, userName, "/top.jsp") );
      out.write("\n");
      out.write("    <h1>かごゆめ</h1>\n");
      out.write("    <p>\n");
      out.write("        ショッピングサイトを使っている時、このような経験はありませんか？<br>\n");
      out.write("        <br>\n");
      out.write("        「あれいいな」「これいいな」「あっ、関連商品のこれもいい」「20%OFFセールだって！？買わなきゃ！」...<br>\n");
      out.write("        そしていざ『買い物かご』を開いたとき、その合計金額に愕然とします。<br>\n");
      out.write("        「こんなに買ってたのか・・・仕方がない。いくつか減らそう・・・」<br>\n");
      out.write("        <br>\n");
      out.write("        仕方がありません。無駄遣いは厳禁です。<br>\n");
      out.write("        でも、一度買うと決めたものを諦めるなんて、ストレスではありませんか？<br>\n");
      out.write("        できればお金の事を考えずに好きなだけ買い物がしたい・・・。<br>\n");
      out.write("        <br>\n");
      out.write("        このサービスは、そんなフラストレーションを解消するために生まれた、<br>\n");
      out.write("        『金銭取引が絶対に発生しない』『いくらでも、どんなものでも購入できる(気分になれる)』ECサイトです。\n");
      out.write("    </p>\n");
      out.write("    <form action=\"Search\">\n");
      out.write("        <input type=\"text\" name=\"query\" placeholder=\"キーワード\">\n");
      out.write("        <input type=\"submit\" value=\"検索\">\n");
      out.write("    </form>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
