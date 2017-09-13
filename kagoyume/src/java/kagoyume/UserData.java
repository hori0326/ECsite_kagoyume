package kagoyume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * フォームからの入出力されるデータを格納するBeansオブジェクト
 * DTOからの変換、逆に、DTOへの変換を行うメソッドを保持する
 * 
 * @author hori
 */
public class UserData implements Serializable {
    
    private String name;        // ユーザー名
    private String password;    // パスワード
    private String mail;        // メールアドレス
    private String address;     // 住所
    
    public UserData() {}
    
    /**
     * 新規ユーザー登録時の入力されたデータに未入力がある場合、対象項目名を配列で返却
     * @return 未入力項目の名前
    */
    public ArrayList<String> checkProperties(){
        
        ArrayList<String> checkList = new ArrayList<>();
        if(this.name.equals("")) { checkList.add("name"); }
        if(this.password.equals("")) { checkList.add("password"); }
        if(this.mail.equals("")) { checkList.add("mail"); }
        if(this.address.equals("")) { checkList.add("address"); }
        
        return checkList;
    }
    
    public void mapping(UserDataDTO userDTO) {
        userDTO.setName(this.name);
        userDTO.setPassword(this.password);
        userDTO.setMail(this.mail);
        userDTO.setAddress(this.address);
    }
     
    public String getName() { return name; }
    public void setName(String name) { 
        // 未入力の場合、空文字をセット
        if(name.trim().length() == 0) { this.name = ""; }
        else{ this.name = name; }
    }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { 
        if(password.trim().length()==0){ this.password = ""; }
        else{ this.password = password; }
    }
    
    public String getMail() { return mail; }
    public void setMail(String mail) {
        if(mail.trim().length() == 0) { this.mail = ""; }
        else{ this.mail = mail; }
    }
    
    public String getAddress() { return address; }
    public void setAddress(String address) {
        if(address.trim().length() == 0) { this.address = ""; }
        else{ this.address = address; }
    }
}
