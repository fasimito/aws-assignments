<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="jquery/jquery-3.4.0.js"></script>
    <script type="text/javascript" src="js/urls.js"></script>
</head>
<body>
    <div align="center">
        <form id="addUrlForm" name="addUrlForm" action="/short/url" method="post">
            <div><label>URL : <input type="text" name="url" id="url"/> </label>
                <input type="button" onclick="doShortUrl()" value="Shorten"/>
            </div>
            <div><label>Comments : <input type="text" name="comments" id="comments"/> </label></div>
            <div id="shortUrlDiv"></div>
        </form>
    </div>
</body>
</html>
