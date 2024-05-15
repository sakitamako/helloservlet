<!-- このファイルが通常のHTMLファイルではなく、JSPであることを示している -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- インデント自動でされないため自分でインデント、しないのが正解？ -->
<!-- ブラウザにHTML5標準に従って文書を解釈するよう指示 -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>HelloServlet</title>
    </head>
    <body>

        <!-- ブラウザ上のHelloServletボタン -->
        <input type="button" value="HelloServlet" onClick="location.href='HelloServlet'">

        <!-- ブラウザ上のWelcomeServletボタン -->
        <input type="button" value="WelcomeServlet" onClick="location.href='welcome.jsp'">

        <!-- ブラウザ上の問い合わせボタン -->
        <input type="button" value="問い合わせ" onClick="location.href='inquiry.jsp'">

        <!-- ブラウザ上のMySQLServletボタン -->
        <input type="button" value="MySQLServlet" onClick="location.href='MySQLServlet'">
        <br>

        <!-- ブラウザ上の送信ボタンを押すとTestServle.javaファイルにアクションする -->
        GET通信
        <form method="get" action="TestServlet">
            <input type="text" name="username">
            <input type="password" name="password">
            <input type="submit" value="送信">
        </form>

        <!-- ブラウザ上の送信ボタンを押すとTestServle.javaファイルにアクションする -->
        POST通信
        <form method="post" action="TestServlet">
            <input type="text" name="username">
            <input type="password" name="password">
            <input type="submit" value="送信">
        </form>

    </body>
</html>