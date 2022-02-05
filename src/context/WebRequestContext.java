package context;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class WebRequestContext implements RequestContext{
    private HttpServletRequest request;
    private Map<String,String[]> parametersMap;
    private String pictFileName;

    public void setRequest(Object request){
        this.request = (HttpServletRequest)request;
        parametersMap = this.request.getParameterMap();
    }

    public Object getRequest(){
        return request;
    }

    public String getCommandKey(){
        String servletPath = request.getServletPath();
        String key = servletPath.substring(1);

        return key;
    }

    public String[] getParameter(String key){
        String[] value = (String[])parametersMap.get(key);

        return value;
    }

    public boolean uploadFile() { // 写真アップロードメソッド
    	@SuppressWarnings("deprecation") // 処理の都合上、非推奨メソッドを呼び出してるため警告を消すアノテーションを記述
		String imagePath = request.getRealPath("images"); // imagesフォルダまでの絶対パスを取得「C:\pleiades\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\Egroup_PROJECT\WebContent\images」

    	System.out.println("フォルダまでのPath：" + imagePath);

    	boolean uploadFlag = false;

    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(factory);

    	try {
    		List<FileItem> list = sfu.parseRequest(request); // doPostで送られたリクエストをフォームの部品ごとにFileItem型で分割しリストで返す
    		Iterator<FileItem> it = list.iterator();

    		while(it.hasNext()) {
    			FileItem item = (FileItem)it.next();

    			if(!item.isFormField()) { // もしフォームのFileアップロード以外の部品の時Trueを返すメソッドを使用して写真アップロードの部品のみ処理を加えるようにする
    				String fileName = item.getName();

    				if((fileName != null) && (!fileName.equals(""))) { // ファイル名が名無しじゃないか判定
    					fileName = new File(fileName).getName(); // あげたユーザーのパスなどは消えて〇〇.pngなどの名前が取得できる

    					System.out.println("アップロードファイル名:" + fileName);

    					File pictFile = new File(imagePath + File.separator + fileName);

    					if(pictFile.exists()) { // 同一写真が存在していたら処理終了
    						uploadFlag = false;
    						return uploadFlag;
    					}

    					setPictFileName(fileName);

    					item.write(pictFile); // 指定されたパスに保存する
    					uploadFlag = true;
    				}
    			}
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		uploadFlag = false;
    	}

    	return uploadFlag;
    }

    private void setPictFileName(String pictFileName) { // このクラス内でしか呼び出したくないためprivateのセッター
    	this.pictFileName = pictFileName;
    }

    public String getPictFileName() {
    	return pictFileName;
    }

    public void setToken(Object token) {
    	request.getSession().setAttribute("token", token);
    }

    public Object getToken() {
		System.out.println("セッション登録値(token) :" + request.getSession().getAttribute("token"));

    	return request.getSession().getAttribute("token");
    }

	public void setSessionAttribute(String attrName, Object value) {
		request.getSession().setAttribute(attrName, value);

	}

	public Object getSessionAttribute(String attrName) {
		System.out.println("セッション登録値 :" + request.getSession().getAttribute(attrName));

		return request.getSession().getAttribute(attrName);
	}

	public void removeSessionAttribute(String attrName) {
		request.getSession().removeAttribute(attrName);
	}

	public void sessionInvalidate() {
		System.out.println("セッションの破棄");
		request.getSession().invalidate();
	}

	public void setPastLocation(String location) {
		request.getSession().setAttribute("pastLocation", location);
	}

	public String getPastLocation() {
		return (String)request.getSession().getAttribute("pastLocation");
	}

	public String getOneBeforeLocation() {
		String referer = request.getHeader("referer");
		String trimLocation = referer.substring(37); // refererからコンテキストパスなどを取り除く

		System.out.println("referer :" + referer);
		System.out.println("referer(トリミング済み) :" + trimLocation);

		if(trimLocation.isEmpty()) {
			trimLocation = "topPage";
		}

		return trimLocation;
	}
}