package kagoyume;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hori
 */
public class Cart extends HttpServlet {

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
            
            // loginCheck:ログイン中の場合は"OK"の文字列を格納　[Login.java]で登録
            String loginCheck = (String) session.getAttribute("loginCheck");
            if(loginCheck != null && loginCheck.equals("OK")) {
                ArrayList<ItemData> cartItem = (ArrayList<ItemData>) session.getAttribute("cartItem"); 
                if(cartItem == null) { cartItem = new ArrayList<>(); }
                session.setAttribute("cartItem", cartItem);

                request.setCharacterEncoding("UTF-8");
                // カート内から商品を削除。削除する配列の要素番号は[cart.jsp]から送られる。
                if(request.getParameter("delete") != null){
                    cartItem.remove(Integer.parseInt(request.getParameter("delete")));
                }

                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            } else {
                // "ログインしてください"を表す文字列 PleaseLoginをリクエストスコープ登録
                request.setAttribute("PleaseLogin", "PleaseLogin");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
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
