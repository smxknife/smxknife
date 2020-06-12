console.log("this is index.js")

applicationCache.addEventListener("updateready",function(){
    applicationCache.swapCache();      // 手工更新本地缓存
    location.reload();    //重新加载页面页面
},true);