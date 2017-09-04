/**
 * Created by User on 2017/9/2.
 */
/**
 * 创建XMLHttpRequest对象
 * @returns {*} createXmlHttp xmlhttp对象
 * @constructor
 */
function createXmlHttp(){
    var xmlhttp;
    try {//FireFox,Opera,Safari
        xmlhttp = new XMLHttpRequest();
    }catch (e){
        try { //IE
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        }catch (e){
            try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP") ;
            }catch (e){

            }
        }
    }
    return xmlhttp;
}

/**
 * 用户名校验
 */
function checkUserName(){
    var url = "${pageContext.request.contextPath}";
    var username = $("#username").val();
    alert("checkUserName"+username);
    var xhr = createXmlHttp();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4){
            if (xhr.status == 200){
                $("#usernameId").html(xhr.response);
            }
        }
    }
    xhr.open("GET",url+"/user_findByUserName.action?username="+username+"&time="+new Date().getTime(),true);
    xhr.send(null);
}