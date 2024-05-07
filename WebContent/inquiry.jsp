<!-- このファイルが通常のHTMLファイルではなく、JSPであることを示している -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- インデント自動でされないため自分でインデント、しないのが正解？ -->
<!-- ブラウザにHTML5標準に従って文書を解釈するよう指示 -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>お問い合わせフォーム</title>
    </head>
    <body>
        <form method="post" action="InquiryServlet">
        名前:<br>
        <input type="text" name="name">
        <br>
        お問合せの種類:<br>
        <select name="qtype">
            <option value="company">会社について</option>
            <option value="product">製品について</option>
            <option value="support">アフターサポートについて</option>
        </select>
        <br>お問合せ<br>
        <textarea name="body"></textarea>
        <br>
        <input type="submit" value="登録">
        </form>
    </body>
</html>