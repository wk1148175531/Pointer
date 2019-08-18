<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="referrer" content="no-referrer">
<title>Test</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/Popper.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/vue.js"></script>
<script type="text/javascript" src="js/axios.js"></script>
<script type="text/javascript" src="js/bootstrap-table.js"></script>
<script type="text/javascript" src="js/bootstrap-table-vue.js"></script>
<script type="text/javascript" src="js/bootstrap-table-zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-table.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
</head>
<body>
<div id="sse">
         <a href="javascript:WebSocketTest()">运行 WebSocket</a>
         <button onclick="notifyMe()">Notify me!</button>
</div>
<div id="vue_det">
    <h1>site : {{site}}</h1>
    <h1>url : {{url}}</h1>
    <h1>{{details()}}</h1>
</div>
 <div id="table">
      <bootstrap-table :columns="columns" :data="data" :options="options"></bootstrap-table>
    </div>
<script type="text/javascript">
    var vm = new Vue({
        el: '#vue_det',
        data: {
            site: "Pointer",
            url: "www.Pointer.com",
            alexa: "10000"
        },
        mounted(){
        	axios
        	.get('getJson')
        	.then(response=>(console.log(response)))
        	.catch(function(error){
        		console.log(error);
        	});
        },
        methods: {
            details: function() {
                return  this.site + " Pointer";
            }
        }
    }); 
    new Vue({
      el: '#table',
      components: {
        'BootstrapTable': BootstrapTable
      },
      data: {
        columns: [
          {
            title: '电影名',
            field: 'videoname',
            width: 50
          },
          {
            field: 'url',
            title: '图片地址',
            formatter: function(value, row, index){
            	if (value) {
            		  return [
            		    '<img  src="'+value+'" style="width:60px;height:80px"/>'
            		  ].join('')
            		}
            },
            width: 80
          }, {
            field: 'desc',
            title: '电影简介',
          }
        ],
        data: [],
        options: {
          url: "getJson",
          search: false,
          showColumns: true,
          pagination: true,
          sidePagination: "server",
          pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
          pageSize: 10,                     //每页的记录行数（*）
          pageList: [10, 25, 50, 100],
          showRefresh: true,                  //是否显示刷新按钮
          minimumCountColumns: 2,             //最少允许的列数
          clickToSelect: true,                //是否启用点击选中行
          striped: true,                      //是否显示行间隔色
          
        }
      },
      mounted(){
      	axios
      	.get('getJson')
      	.then(response=>(this.data=response.data.data))
      	.catch(function(error){
      		console.log(error);
      	});
      }
    });
    /* websocket */
    function WebSocketTest()
    {
       if ("WebSocket" in window)
       {
          // 打开一个 web socket
          var ws = new WebSocket("ws://localhost:8080/Pointer/websocket/20");
           
          ws.onopen = function()
          {
             // Web Socket 已连接上，使用 send() 方法发送数据
             ws.send("发送数据");
             alert("数据发送中...");
          };
           
          ws.onmessage = function (evt) 
          { 
             var received_msg = evt.data;
             alert("数据已接收...,");
             console.log("后台消息："+received_msg);
          };
           
          ws.onclose = function()
          { 
             // 关闭 websocket
             alert("连接已关闭..."); 
          };
       }
       
       else
       {
          // 浏览器不支持 WebSocket
          alert("您的浏览器不支持 WebSocket!");
       }
    }
    
    
    function notifyMe() {
    	  // 先检查浏览器是否支持
    	  if (!("Notification" in window)) {
    	    alert("This browser does not support desktop notification");
    	  }

    	  // 检查用户是否同意接受通知
    	  else if (Notification.permission === "granted") {
    	    // If it's okay let's create a notification
    	    var notification = new Notification("Hi there!");
    	  }

    	  // 否则我们需要向用户获取权限
    	  else if (Notification.permission !== 'denied') {
    	    Notification.requestPermission(function (permission) {
    	      // 如果用户同意，就可以向他们发送通知
    	      if (permission === "granted") {
    	        var notification = new Notification("Hi there!");
    	      }
    	    });
    	  }
    }
  </script>
</body>
</html>