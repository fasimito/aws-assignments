function doShortUrl(){
    $.ajax({
        cache: false,
        type: "POST",
        url:"/url/short", //把表单数据发送到后端
        data:{
            url: $.trim($("#url").val()),
            comments: $.trim($("#comments").val())
        },
        dataType: "text",
        async: false,
        error: function(request) {
            alert("发送请求失败！");
        },
        success: function(data) {
            $("#shortUrlDiv").html("<p>Short URL:</p> <a href='"+data+"'>"+data+"</a>"); //将返回的结果显示
        }
    });

}

function clickTest(){
    alert("test click");
}