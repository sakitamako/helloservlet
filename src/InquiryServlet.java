import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InquiryServlet")
//InquiryServlet（子クラス） extends（継承） HttpServlet（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class InquiryServlet extends HttpServlet {

	public InquiryServlet() {

		//スーパークラス（親クラス）のフィールド（メンバ変数）やメソッドを、
		//サブクラス（子クラス）から参照するときに使う修飾子
		super();
    }

	//「doPost」メソッドは、POSTメソッドで呼ばれる可能性があるサーブレットであれば次のようになります。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータ取得時のエンコーディング変換
		//Javaサーブレットでは以下の方法でリクエストパラメータ取得時のエンコーディング指定が可能
		//※実行の順番に注意。必ずgetParameterメソッドの前に実行すること。
		//※リクエストパラメータが複数の場合は初めに一度だけ実行すればよい。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//request. getParameter("HTMLのname属性の値");
		//HTMLのname属性値を設定することで、name属性とセットで書いているvalue属性の値を取得することができます。
		String name = request.getParameter("name");

		System.out.println(name);

		//文字出力用のストリームの取得とは、HttpServletResponseオブジェクトから文字を出力する入れ物を取り出すことである。
		//ストリームというのは文字が順番に連続して並んでいる入れ物のことである。
		//取り出したストリームへ文字を書き出すと、それがクライアントに送られ、画面に表示される。
		//書き方の基本=PrintWriterストリームの参照変数 = HttpServletResponseオブジェクト. getWriter();
		//実際のコード=PrintWriter out = response.getWriter();
		PrintWriter out = response.getWriter();

		//inquiry.jspを入力して登録ボタンを押すと下記が表示される
		//ブラウザで表示させた後［ページのソースを表示］をクリックすると下記が表示される
		//ブラウザでは〇〇さん、お問合せありがとうございました！！！と表示される
		out.println("<html><head></head><body><br>" + name +"さん、お問合せありがとうございました！！！</body></html>");
	}

}
