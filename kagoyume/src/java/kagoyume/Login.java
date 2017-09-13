package kagoyume;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hori
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        try {
            request.setCharacterEncoding("UTF-8");

            // [ログアウト]ボタンが押された場合。パラメータは[Helper.java]のmenuメソッドから送信される
            if(request.getParameter("logout") != null && request.getParameter("logout").equals("yes")) {
                session.invalidate();
                request.getRequestDispatcher("/top.jsp").forward(request, response);
            } else {
                // JavaBeansに入力情報を格納
                UserData userBeans = new UserData();
                userBeans.setName(request.getParameter("name"));
                userBeans.setPassword(request.getParameter("password"));
                // DB専用のパラメータに変換
                UserDataDTO userDTO = new UserDataDTO();
                userBeans.mapping(userDTO);
                // ログイン出来るか確認。成功ならtrue　失敗ならfalseを格納
                boolean isLogin = UserDataDAO .getInstance().login(userDTO);
                if(isLogin) {                    
                    // ログイン中のユーザー情報をセッション登録(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)
                    session.setAttribute("loginUser", userDTO);
                    session.setAttribute("loginCheck", "OK");
                    // 元のページにフォワード。パラメータは[Helper.java]のmenuメソッドから送信される
                    request.getRequestDispatcher(request.getParameter("returnPage")).forward(request, response);
                } else {
                    // ログイン失敗を表す文字列failureをリクエストスコープ登録
                    request.setAttribute("loginFailure", "failure");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
        } catch(IOException | SQLException | ServletException e) {
            // 例外発生時、エラー画面にフォワード
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
