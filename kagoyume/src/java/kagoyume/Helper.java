package kagoyume;

import java.util.ArrayList;

/**
 *
 * @author hori
 */
public class Helper {
    
    public static Helper getInstance() { return new Helper(); }
    
    /**
     * 各ページの画面上部のメニューを表示するためのHTML文を返却
     * ログインしているか・していないかで表示するメニューを分岐させている
     * @param isLogin ログインしている場合はtrue。していない場合はfalse
     * @param userName ログインしているユーザーの名前
     * @param returnPage ログイン画面に移動する前のページ名
     * @return 画面上部にメニューを表示するための文字列
     */
    public String menu(boolean isLogin, String userName, String returnPage) {
        
        String top     = "<a href=\"top.jsp\" style=\"text-decoration:none;\">[トップ] </a>";
        String login   = "<a href=\"login.jsp?returnPage=" + returnPage + "\" style=\"text-decoration:none;\">[ログイン] </a>";
        String logout  = "<a href=\"Login?logout=yes\" style=\"text-decoration:none;\">[ログアウト] </a>";
        String cart    = "<a href=\"Cart\" style=\"text-decoration:none;\">[買い物かご] </a>";
        String welcome = "ようこそ！<a href=\"mydata.jsp\" style=\"text-decoration:none;\">" + userName + "</a>さん";
        
        if(isLogin) { return welcome + "<br>" + top + logout + cart + "<br><br>"; }
        else { return top + login + "<br><br>"; } 
    }
    
    /**
     * 新規ユーザー登録時の入力されたデータに未入力がある場合、どの項目かをHTML文で返却
     * @param checkList UserDataで生成されるリスト。未入力項目の名前が格納されている。
     * @return 未入力の項目に対応する文字列
    */
    public String checkInput(ArrayList<String> checkList) {
        
        String outPut = "";
        for(String value : checkList) {
            if(value.equals("name")) { outPut += "ユーザー名"; }
            if(value.equals("password")) { outPut += "パスワード"; }
            if(value.equals("mail")) { outPut += "メールアドレス"; }
            if(value.equals("address")) { outPut += "住所"; }
            outPut += "が未記入です。<br>";
        }
        return outPut;
    }
}
