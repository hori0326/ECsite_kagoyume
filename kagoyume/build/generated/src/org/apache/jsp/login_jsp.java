package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.Helper;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    Helper helper = Helper.getInstance();
    
    // loginFailure:"ログインに失敗しました。"という文字列。ログイン失敗時に生成される。　[Login.java]で登録
    String loginFailure = (String) request.getAttribute("loginFailure");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>ログイン ‐ かごゆめ</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    ");
 if(loginFailure != null && loginFailure.equals("ログインに失敗しました。")) { 
      out.print( loginFailure );
 } 
      out.write("\n");
      out.write("    <form action=\"Login\" method=\"POST\">\n");
      out.write("        <input type=\"text\" name=\"name\" value=\"test\" placeholder=\"ユーザー名\"><br>\n");
      out.write("        <input type=\"password\" name=\"password\" value=\"test\" placeholder=\"パスワード\"><br>\n");
      out.write("        <br>\n");
      out.write("        <input type=\"submit\" value=\"ログイン\">\n");
      out.write("    </form>\n");
      out.write("    <br>\n");
      out.write("    <a href=\"registration.jsp\">[新規会員登録]</a>\n");
      out.write("</body>\n");
      out.write("</html>");
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
