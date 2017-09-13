package kagoyume;

import base.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author hori
 */
public class UserDataDAO {
    
    // インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){ return new UserDataDAO(); }
    
    /**
     * 入力されたIDとPWが正しいか確認する
     * @param userDTO ユーザーIDとパスワードが格納されている
     * @return ユーザーIDとパスワードが一致すればtrue。そうでなければfalseを返却
     * @throws SQLException 
     */
    public boolean login(UserDataDTO userDTO) throws SQLException {
        
        // DBに接続
        Connection conn = DBManager.getConnection();

        try{
            // SELECT文の準備
            PreparedStatement pstmt = 
                    conn.prepareStatement("SELECT * FROM user_t WHERE name = ? AND password = ?");
            // SELECT文中の「?」に使用する値を設定
            pstmt.setString(1, userDTO.getName());
            pstmt.setString(2, userDTO.getPassword());
            // SELECT文を実行
            ResultSet rs = pstmt.executeQuery();
            
            // ログイン成功。deleteFlgが0は生きている。1は削除されているとみなしている
            if(rs.next() && rs.getInt("deleteFlg") == 0) {
                // ユーザー名・パスワード以外の情報をDTOに格納
                userDTO.setUserID(rs.getInt("userID"));
                userDTO.setMail(rs.getString("mail"));
                userDTO.setAddress(rs.getString("address"));
                userDTO.setTotal(rs.getInt("total"));
                userDTO.setNewDate(rs.getTimestamp("newDate"));
                userDTO.setDeleteFlg(rs.getInt("deleteFlg"));
                return true;
            } else {
                return false;
            }
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            // データベース切断
            if(conn != null) { conn.close(); }
        }
    }
    
    /**
     * ログイン中のユーザーが過去に購入した「商品コード・発送方法・購入日時」を配列で返却
     * @param userDTO ユーザー情報が格納されている(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)
     * @return 商品コード・発送方法・購入日時を配列で返却
     * @throws SQLException 
     */
    public ArrayList<ItemDataDTO> myHistory(UserDataDTO userDTO) throws SQLException {
        
        Connection conn = DBManager.getConnection();

        try {
            PreparedStatement pstmt = 
                    conn.prepareStatement("SELECT itemCode, type, buyDate FROM buy_t WHERE userID = ?");
            pstmt.setInt(1, userDTO.getUserID());
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<ItemDataDTO> historyList = new ArrayList<>();
            while(rs.next()) {
                ItemDataDTO itemDTO = new ItemDataDTO();
                itemDTO.setItemCode(rs.getString(1));
                itemDTO.setType(rs.getInt(2));
                itemDTO.setBuyData(rs.getTimestamp(3));
                historyList.add(itemDTO);
            }
            
            return historyList;
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(conn != null) { conn.close(); }
        }
    }
    
    /**
     * 会員情報管理テーブルに新規登録されたユーザーとしてデータを挿入
     * @param userDTO ユーザー情報が格納されている(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)
     * @throws SQLException 
     */
    public void userRegistration(UserDataDTO userDTO) throws SQLException {
        
        Connection conn = DBManager.getConnection();

        try{
            PreparedStatement pstmt = 
                    conn.prepareStatement("INSERT INTO user_t (name, password, mail, address, newDate) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, userDTO.getName());
            pstmt.setString(2, userDTO.getPassword());
            pstmt.setString(3, userDTO.getMail());
            pstmt.setString(4, userDTO.getAddress());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(conn != null) { conn.close(); }
        }
    }
    
    /**
     * 会員情報管理テーブルに登録されているユーザーの更新を行う
     * @param userDTO ユーザー情報が格納されている(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)
     * @throws SQLException 
     */
    public void userUpdate(UserDataDTO userDTO) throws SQLException {
        
        Connection conn = DBManager.getConnection();

        try{
            PreparedStatement pstmt = 
                    conn.prepareStatement("UPDATE user_t SET name = ?, password = ?, mail = ?, address = ? WHERE userID = ?");
            pstmt.setString(1, userDTO.getName());
            pstmt.setString(2, userDTO.getPassword());
            pstmt.setString(3, userDTO.getMail());
            pstmt.setString(4, userDTO.getAddress());
            pstmt.setInt(5, userDTO.getUserID());
            pstmt.executeUpdate();
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(conn != null) { conn.close(); }
        }
    }
    
    /**
     * 会員情報管理テーブルに登録されているユーザーの削除を行う
     * ユーザーID(useID)カラムは主キーであるためDELETEできない。代わりにdeleteFlgを1にすることで、削除したとみなす
     * @param userDTO ユーザー情報が格納されている(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)
     * @throws SQLException 
     */
    public void userDelete(UserDataDTO userDTO) throws SQLException {
        
        Connection conn = DBManager.getConnection();

        try{
            // deleteFlt=1を削除されたユーザーとするため、DELETE文ではなくUPDATE文
            PreparedStatement pstmt = 
                    conn.prepareStatement("UPDATE user_t SET deleteFlg = 1 WHERE userID = ?");
            pstmt.setInt(1, userDTO.getUserID());
            pstmt.executeUpdate();
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(conn != null) { conn.close(); }
        }
    }
    
    /**
     * 会員情報管理テーブルの総購入金額を更新する
     * @param userDTO ユーザー情報が格納されている(ユーザーID・ユーザー名・パスワード・メールアドレス・住所・総購入金額・登録日時・削除フラグ)
     * @throws SQLException 
     */
    public void userTotalMoney(UserDataDTO userDTO) throws SQLException {
        
        Connection conn = DBManager.getConnection();
        
        try{
            PreparedStatement pstmt = 
                    conn.prepareStatement("UPDATE user_t SET total = ? WHERE userID = ?");
            pstmt.setInt(1, userDTO.getTotal());
            pstmt.setInt(2, userDTO.getUserID());
            pstmt.executeUpdate();
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if(conn != null) { conn.close(); }
        }
    }
}
