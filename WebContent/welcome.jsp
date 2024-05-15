<!-- このファイルが通常のHTMLファイルではなく、JSPであることを示している -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- ブラウザにHTML5標準に従って文書を解釈するよう指示 -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>WELCOME</title>
    </head>
    <body>
        名前とパスワードを入力してください。

        <!-- ブラウザ上のWelcomeServletボタンを押した先の入力項目が下記の内容
        名前とパスワードを入力して送信ボタンを押すとWelcomeServlet.javaファイルにアクションする -->
        <form method="post" action="WelcomeServlet">
            <input type="text" name="username">
            <input type="password" name="password">
            <input type="submit" value="送信">
        </form>

    </body>
</html>