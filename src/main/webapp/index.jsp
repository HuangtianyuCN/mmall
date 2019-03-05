<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>

springMVC上传文件
<form action="/manage/product/upload.do" name="form1" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springMVC上传文件"></input>
</form>

<br>
<br>

springMVC上传富文本图片
<form action="/manage/product/richtext_img_upload.do" name="form2" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springMVC上传富文本图片"></input>
</form>
</body>
</html>
