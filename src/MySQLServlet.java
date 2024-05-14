import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MySQLServlet")
//MySQLServlet（子クラス） extends（継承） HttpServlet（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class MySQLServlet extends HttpServlet {

	public MySQLServlet() {

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

		//文字出力用のストリームの取得とは、HttpServletResponseオブジェクトから文字を出力する入れ物を取り出すことである。
		//ストリームというのは文字が順番に連続して並んでいる入れ物のことである。
		//取り出したストリームへ文字を書き出すと、それがクライアントに送られ、画面に表示される。
		//書き方の基本=PrintWriterストリームの参照変数 = HttpServletResponseオブジェクト. getWriter();
		//実際のコード=PrintWriter out = response.getWriter();
		PrintWriter out = response.getWriter();

		//<input type="button" value="MySQLServlet" onClick="location.href='MySQLServlet'">を入力することで
		//MySQLServletボタンが表示される、それをクリックすると下記が表示される
		//ブラウザで表示した後［ページのソースを表示］をクリックすると下記が表示される
		//ブラウザではデータベーステストと表示される
		out.println("<html>");
		out.println("<head>");
		out.println("<title>データベーステスト</title>");
		out.println("</head>");
		out.println("<body>");

		//接続オブジェクトの定義
		Connection conn = null;

		//接続先情報を表す文字列の定義
		String url = "jdbc:mysql://localhost/testdb";

		//接続時に使用するユーザー名の定義
		String user = "root";

		//接続時に使用するパスワードの定義
		String password = "root";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			//ドライバーがロードされ使えるような状態にしている、覚える。
			conn = DriverManager.getConnection(url, user, password);

			//データベースの接続後に、sql文をデータベースに直接渡すのではなく、
			//sqlコンテナの役割を果たすオブジェクトに渡すためのStatement オブジェクトを作成する。
			//Statementオブジェクトには３種類あり、目的による使い分ける。
			//通常のsql文を処理する場合には、Statementオブジェクトを用いる。
			//また、sql文のプリコンパイル用にはPreparedStatementを、
			//ストアドプロシージャ用にはCallableStatementを利用する。
			//Statementオブジェクトは、直接newする必要なく、
			//接続を確立した Connectionオブジェクトから目的にあったStatementオブジェクトを 得て利用する。
			//stmt＝PDOStatementオブジェクトを表している
			Statement stmt = conn.createStatement();

			//SELECT データを抽出する
			//＊ テーブルに含まれる項目全て
			//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
			//test_tableに入っているデータがsqlに代入される
			String sql = "SELECT * FROM test_table";

			//executeQuery();は実行メソッドで、必ず ResultSetが返ってくる
			//ResultSetは問い合わせにより返されるデータの行（レコード） をあらわす
			ResultSet rs = stmt.executeQuery(sql);

			//while(rs.next())はカーソルを1行ずつ実行していきデータがなくなったら実行を終了する
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String userPassword = rs.getString("password");

				//ブラウザで表示した後［ページのソースを表示］をクリックすると下記が表示される
				//ブラウザでは"ユーザーID:〇〇 , ユーザー名:〇〇, パスワード:〇〇と表示される
				out.println("<p>");
				out.println("ユーザーID:" + userId + ", ユーザー名:" + userName + ", パスワード:" + userPassword);
				out.println("</p>");
			}

			//データベースとの接続をクローズ
			//これをしないとデータベースを接続したまま作業が実行されてしまってメモリに負荷がかかる
			rs.close();
			stmt.close();

		//tryの中でエラーが発生した場合、catchが受け取り
		} catch (ClassNotFoundException e) {

			//例外がスローされる原因となったエラーまたは動作の説明を返します
			out.println("ClassNotFoundException:" + e.getMessage());

		//tryの中でエラーが発生した場合、catchが受け取り
		} catch (SQLException e) {

			//例外がスローされる原因となったエラーまたは動作の説明を返します
			out.println("SQLException:" + e.getMessage());

		//tryの中でエラーが発生した場合、catchが受け取り
		} catch (Exception e) {

			//例外がスローされる原因となったエラーまたは動作の説明を返します
			out.println("Exception:" + e.getMessage());

		//最後に実行されるものを指定するための構文
		//例外が発生しcatchされてもされなくても必ず行われる処理を書くことができる。
		} finally {

			//try.catchはjavaの例外処理のための構文
			try {

				//もし接続オブジェクトの値が違ったら（５６行目）
				//データベースとの接続をクローズ？
				if (conn != null) {
					conn.close();
				}

			//tryの中でエラーが発生した場合、catchが受け取り
			} catch (SQLException e) {

				//例外がスローされる原因となったエラーまたは動作の説明を返します。
				out.println("SQLException:" + e.getMessage());
			}
 		}

		//ブラウザで表示した後［ページのソースを表示］をクリックすると下記が表示される
		out.println("</body>");
		out.println("</html>");
	}

}
