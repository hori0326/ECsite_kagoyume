package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.UserDataDTO;
import kagoyume.Helper;
import kagoyume.ItemData;

public final class item_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");

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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>");
      out.print( itemDetail.getNameDetail() );
      out.write(" ‐ かごゆめ</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    ");
      out.print( Helper.getInstance().menu(isLogin, userName, "/item.jsp") );
      out.write("\n");
      out.write("    <h2>");
      out.print( itemDetail.getNameDetail() );
      out.write("</h2>\n");
      out.write("    <img src=\"");
      out.print( itemDetail.getImageDetail() );
      out.write("\"><br>\n");
      out.write("    <b><font color=\"red\">");
      out.print( itemDetail.getPriceDetail() );
      out.write("円</font>（税込）</b><br><br>\n");
      out.write("    ");
      out.print( itemDetail.getDescription() );
      out.write("<br><br>\n");
      out.write("    評価点：");
      out.print( itemDetail.getRate() );
      out.write("pt<br>\n");
      out.write("    レビュー件数：");
      out.print( itemDetail.getCount() );
      out.write("件<br><br>\n");
      out.write("    <form action=\"Add\">\n");
      out.write("        <input type=\"hidden\" name=\"ac\" value=\"");
      out.print( hs.getAttribute("ac") );
      out.write("\">\n");
      out.write("        <input type=\"submit\" value=\"カートに追加\">\n");
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
