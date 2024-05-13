import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
//TestServlet（子クラス） extends（継承） HttpServlet（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class TestServlet extends HttpServlet {

    public TestServlet() {
    	//スーパークラス（親クラス）のフィールド（メンバ変数）やメソッドを、
    	//サブクラス（子クラス）から参照するときに使う修飾子
    	super();
    }

  //「doGet」メソッドは、Getメソッドで呼ばれる可能性があるサーブレットであれば次のようになります。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータ取得時のエンコーディング変換
		//Javaサーブレットでは以下の方法でリクエストパラメータ取得時のエンコーディング指定が可能
		//※実行の順番に注意。必ずgetParameterメソッドの前に実行すること。
		//※リクエストパラメータが複数の場合は初めに一度だけ実行すればよい。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//request. getParameter("HTMLのname属性の値");
		//HTMLのname属性値を設定することで、name属性とセットで書いているvalue属性の値を取得することができます。
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);

		//文字出力用のストリームの取得とは、HttpServletResponseオブジェクトから文字を出力する入れ物を取り出すことである。
		//ストリームというのは文字が順番に連続して並んでいる入れ物のことである。
		//取り出したストリームへ文字を書き出すと、それがクライアントに送られ、画面に表示される。
		//書き方の基本=PrintWriterストリームの参照変数 = HttpServletResponseオブジェクト. getWriter();
		//実際のコード=PrintWriter out = response.getWriter();
		PrintWriter out = response.getWriter();

		//index.jspで力ししたGET通信内に名前とパスワードを入力して登録ボタンを押すと下記が表示される
		//ブラウザで表示させた後［ページのソースを表示］をクリックすると下記が表示される
		//ブラウザでは名前とパスワードが表示される
		//※GET 通信を使った場合には、送信される情報が URLに表示されます。
		out.println("<html><head></head><body><br>" +username + "<br>" + password + "</body></html>");
	}

	//「doPost」メソッドは、Postメソッドで呼ばれる可能性があるサーブレットであれば次のようになります
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータ取得時のエンコーディング変換
		//Javaサーブレットでは以下の方法でリクエストパラメータ取得時のエンコーディング指定が可能
		//※実行の順番に注意。必ずgetParameterメソッドの前に実行すること。
		//※リクエストパラメータが複数の場合は初めに一度だけ実行すればよい
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//request. getParameter("HTMLのname属性の値");
		//HTMLのname属性値を設定することで、name属性とセットで書いているvalue属性の値を取得することができます。
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);

		//文字出力用のストリームの取得とは、HttpServletResponseオブジェクトから文字を出力する入れ物を取り出すことである。
		//ストリームというのは文字が順番に連続して並んでいる入れ物のことである。
		//取り出したストリームへ文字を書き出すと、それがクライアントに送られ、画面に表示される。
		//書き方の基本=PrintWriterストリームの参照変数 = HttpServletResponseオブジェクト. getWriter();
		//実際のコード=PrintWriter out = response.getWriter();
		PrintWriter out = response.getWriter();

		//index.jspで力ししたPOST通信内に名前とパスワードを入力して登録ボタンを押すと下記が表示される
		//ブラウザで表示させた後［ページのソースを表示］をクリックすると下記が表示される
		//ブラウザでは名前とパスワードが表示される
		//※POST通信を使った場合には、送信される情報は URLに表示されない。
		out.println("<html><head></head><body><br>" + username + "<br>" + password + "</body></html>");
	}

}
