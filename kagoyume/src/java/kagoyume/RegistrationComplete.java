package kagoyume;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hori
 */
public class RegistrationComplete extends HttpServlet {

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

        // セッション開始
        HttpSession session = request.getSession();
        
        try {            
            // registrationInfo:ユーザー新規登録時に入力した情報(ユーザー名・パスワード・メールアドレス・住所)　[RegistrationConfirm.java]で登録を実行
            UserData userBeans = (UserData) session.getAttribute("registrationInfo");
            // JavaBeansをDB専用のパラメータに変換
            UserDataDTO userDTO = new UserDataDTO();
            userBeans.mapping(userDTO);
            // DBに接続し、新規ユーザー登録を実行
            UserDataDAO .getInstance().userRegistration(userDTO);
            // 登録完了後、セッション破棄
            session.removeAttribute("registrationInfo");
            // 登録結果画面用にリクエストパラメータ登録
            request.setAttribute("registrationInfoResult", userDTO);
            
            // フォワード
            request.getRequestDispatcher("/registrationcomplete.jsp").forward(request, response);
            
        } catch(Exception e) {
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
