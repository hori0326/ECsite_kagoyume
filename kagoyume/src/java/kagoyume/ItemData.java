package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author hori
 */
public class ItemData implements Serializable {
    
    private String query;               // 検索キーワード
    private int totalResult;            // 検索結果数
    private ArrayList<String> code;     // 商品ID
    private ArrayList<String> name;     // 商品名
    private ArrayList<String> image;    // 商品画像
    private ArrayList<String> price;    // 商品価格
    private String codeDetail;          // 商品ID(詳細)
    private String nameDetail;          // 商品名(詳細)
    private String imageDetail;         // 商品画像(詳細)
    private String priceDetail;         // 商品価格(詳細)
    private String description;         // 商品説明
    private float rate;                 // 評価点
    private String count;               // レビュー件数
    
    ItemData() {}
    
    // DB用のパラメータにマッピング
    public void mapping(ItemDataDTO itemDTO, UserDataDTO userDTO, int type) {
        itemDTO.setUserID(userDTO.getUserID());
        itemDTO.setItemCode(this.codeDetail);
        itemDTO.setType(type);
    }
    
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
    
    public int getTotalResult() { return totalResult; }
    public void setTotalResult(int totalResult) { this.totalResult = totalResult; }
    
    public ArrayList<String> getCode() { return code; }
    public void setCode(ArrayList code) { this.code = code; }
    
    public ArrayList<String> getName() { return name; }
    public void setName(ArrayList name) { this.name = name; }
    
    public ArrayList<String> getImage() { return image; }
    public void setImage(ArrayList image) { this.image = image; }
    
    public ArrayList<String> getPrice() { return price; }
    public void setPrice(ArrayList price) { this.price = price; }
    
    public String getCodeDetail() { return codeDetail; }
    public void setCodeDetail(String codeDetail) { this.codeDetail = codeDetail; }
    
    public String getNameDetail() { return nameDetail; }
    public void setNameDetail(String nameDetail) { this.nameDetail = nameDetail; }
    
    public String getImageDetail() { return imageDetail; }
    public void setImageDetail(String imageDetail) { this.imageDetail = imageDetail; }
    
    public String getPriceDetail() { return priceDetail; }
    public void setPriceDetail(String priceDetail) { this.priceDetail = priceDetail; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public float getRate() { return rate; }
    public void setRate(float rate) { this.rate = rate; }
    
    public String getCount() { return count; }
    public void setCount(String count) { this.count = count; }
}
