import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")

//HelloServlet（子クラス） extends（継承） HttpServlet（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class HelloServlet extends HttpServlet {

	public HelloServlet() {

		//スーパークラス（親クラス）のフィールド（メンバ変数）やメソッドを、
		//サブクラス（子クラス）から参照するときに使う修飾子
		super();
	}

	//「doGet」メソッドはクライアントからデータの要求がある場合に呼び出されます
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字出力用のストリームの取得とは、HttpServletResponseオブジェクトから文字を出力する入れ物を取り出すこと。
		//ストリームというのは文字が順番に連続して並んでいる入れ物のこと。
		//取り出したストリームへ文字を書き出すと、それがクライアントに送られ、画面に表示される。
		PrintWriter out = response.getWriter();

		//ブラウザで表示した後［ページのソースを表示］をクリックすると下記が表示される
		//ブラウザではこのように表示するよってこと
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("<H3>Hello Servlet!!!!!</H3>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

}
