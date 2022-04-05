function hookAJAX() {
    console.log("xxxxx")
    XMLHttpRequest.prototype.nativeOpen = XMLHttpRequest.prototype.open;
    var customizeOpen = function (method, url, async, user, password) {
        // do something
        console.log("hookAJAX...")
        this.nativeOpen(method, url, async, user, password);
    };

    XMLHttpRequest.prototype.open = customizeOpen;
}

/**
 *全局拦截Image的图片请求添加token
 *
 */
function hookImg() {
    const property = Object.getOwnPropertyDescriptor(Image.prototype, 'src');
    const nativeSet = property.set;

    function customiseSrcSet(url) {
        // do something
        console.log("hookImg...")
        nativeSet.call(this, url);
    }
    Object.defineProperty(Image.prototype, 'src', {
        set: customiseSrcSet,
    });
}

/**
 * 拦截全局open的url添加token
 *
 */
function hookOpen() {
    const nativeOpen = window.open;
    window.open = function (url) {
        // do something
        console.log("hookOpen...")
        nativeOpen.call(this, url);
    };
}

function hookFetch() {
    var fet = Object.getOwnPropertyDescriptor(window, 'fetch')
    Object.defineProperty(window, 'fetch', {
        value: function (a, b, c) {
            // do something
            console.log("hookFetch...")
            return fet.value.apply(this, args)
        }
    })
}

hookAJAX()
hookImg()
hookOpen()
hookFetch()