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
public class BuyComplete extends HttpServlet {

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
            
            // cartItem:カート内の商品詳細情報が配列で格納(商品コード・商品名・画像URL・価格・商品説明・評価点・レビュー件数)　[Add.java][Cart.java]で登録          
            ArrayList<ItemData> cartItem = (ArrayList<ItemData>) session.getAttribute("cartItem");
            // loginUser:ログイン中のユーザー情報(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)　[Login.java]で登録
            UserDataDTO userDTO = (UserDataDTO) session.getAttribute("loginUser");
            ItemDataDTO itemDTO = new ItemDataDTO();
            ItemData itemDetail;
            
            // 購入管理テーブル(DB)に行を1商品ごと作成する
            // パラメータを[buyconfirm.jsp]から取得
            int type = Integer.parseInt(request.getParameter("type"));
            for(int i = 0; i < cartItem.size(); i++) {
                itemDetail = cartItem.get(i);
                itemDetail.mapping(itemDTO, userDTO, type);
                ItemDataDAO.getInstance().itemRegistration(itemDTO);
            }
            
            // 会員情報管理テーブル(DB)の総購入金額を更新する
            userDTO.setTotal(userDTO.getTotal() + Integer.parseInt(request.getParameter("totalMoney")));
            UserDataDAO.getInstance().userTotalMoney(userDTO);
            
            // 総購入金額を更新(=購入処理完了)したため、セッションを破棄
            session.removeAttribute("cartItem");
            
            request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);
        
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
