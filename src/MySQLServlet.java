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

		out.println("<html>");
		out.println("<head>");
		out.println("<title>データベーステスト</title>");
		out.println("</head>");
		out.println("<body>");

		Connection conn = null;
		String url = "jdbc:mysql://localhost/testdb";
		String user = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);

			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM test_table";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String userPassword = rs.getString("password");

				out.println("<p>");
				out.println("ユーザーID:" + userId + ", ユーザー名:" + userName + ", パスワード" + userPassword);
				out.println("</p>");
			}

			rs.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			out.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException e) {
			out.println("SQLException:" + e.getMessage());
		} catch (Exception e) {
			out.println("Exception:" + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				out.println("SQLException:" + e.getMessage());
			}
 		}
		out.println("</body>");
		out.println("</html>");
	}

}
