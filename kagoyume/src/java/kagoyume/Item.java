package kagoyume;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author hori
 */
public class Item extends HttpServlet {

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
            
            // パラメータを[search.jsp]から取得しエンコード
            String itemCode = request.getParameter("itemcode");
            itemCode = URLEncoder.encode(itemCode, "UTF-8");
            
            // 外部API「商品コード検索(商品詳細)」に接続　
            URL url = new URL("https://shopping.yahooapis.jp/ShoppingWebService/V1/itemLookup?"
                    + "appid=dj0zaiZpPThVRERNSTVoYXJVdyZzPWNvbnN1bWVyc2VjcmV0Jng9NjA-&responsegroup=medium&itemcode=" + itemCode);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.connect();
            
            // XMLを取得
            InputStream is = conn.getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is);
            is.close();

            // 取得したXMLを解析
            Element rootElement = doc.getDocumentElement();
            NodeList nodeList = rootElement.getElementsByTagName("Hit");
            Element elementHit = (Element)nodeList.item(0);
            // 商品IDを取得後、格納　<Code>～</Code>
            NodeList codeList = elementHit.getElementsByTagName("Code");
            Element codeElement = (Element)codeList.item(0);
            String code = codeElement.getFirstChild().getNodeValue();
            // 商品名を取得後、格納　<Name>～</Name>
            NodeList nameList = elementHit.getElementsByTagName("Name");
            Element nameElement = (Element)nameList.item(0);
            String name = nameElement.getFirstChild().getNodeValue();
            // 画像URLを取得後、格納　<Medium>～</Medium>
            NodeList imageList = elementHit.getElementsByTagName("Medium");
            Element imageElement = (Element)imageList.item(0);
            String image = imageElement.getFirstChild().getNodeValue();
            // 金額を取得後、格納　<Price>～</Price>
            NodeList priceList = elementHit.getElementsByTagName("Price");
            Element priceElement = (Element)priceList.item(0);
            String price = priceElement.getFirstChild().getNodeValue();
            // 商品説明を取得後、格納　<Description>～</Description>
            NodeList descriptionList = elementHit.getElementsByTagName("Description");
            Element descriptionElement = (Element)descriptionList.item(0);
            String description = descriptionElement.getFirstChild().getNodeValue();
            // 評価点を取得後、格納　<Rate>～</Rate>
            NodeList rateList = elementHit.getElementsByTagName("Rate");
            Element rateElement = (Element)rateList.item(0);
            float rate = Float.parseFloat(rateElement.getFirstChild().getNodeValue());
            // レビュー件数を取得後、格納　<Count>～</Count>
            NodeList countList = elementHit.getElementsByTagName("Count");
            Element countElement = (Element)countList.item(0);
            String count = countElement.getFirstChild().getNodeValue();
            
            // JavaBeansに格納
            ItemData itemDetail = new ItemData();
            itemDetail.setCodeDetail(code);
            itemDetail.setNameDetail(name);
            itemDetail.setImageDetail(image);
            itemDetail.setPriceDetail(price);
            itemDetail.setDescription(description);
            itemDetail.setRate(rate);
            itemDetail.setCount(count);
            
            // 商品の詳細情報をセッション登録(商品コード・商品名・画像URL・価格・商品説明・評価点・レビュー件数)
            session.setAttribute("searchResultDetail", itemDetail);
            
            request.getRequestDispatcher("/item.jsp").forward(request, response); 
            
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
