package kagoyume;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author hori
 */
public class ItemDataDTO implements Serializable {
    
    private int buyID;          // 購入ID
    private int userID;         // ユーザーID
    private String itemCode;    // 商品コード
    private int type;           // 発送方法
    private Timestamp buyData;  // 購入日時
    
    ItemDataDTO() {}
    
    public int getBuyID() { return buyID; }
    public void setBuyID(int buyID) { this.buyID = buyID; }
    
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    
    public int getType() { return type; }
    public void setType(int type) { this.type = type; }
    
    public Timestamp getBuyData() { return buyData; }
    public void setBuyData(Timestamp buyData) { this.buyData = buyData; }
}
