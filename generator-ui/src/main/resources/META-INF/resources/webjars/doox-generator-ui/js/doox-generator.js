// string
String.prototype.compare = function (str) {
    return this.toLowerCase() === str.toLowerCase();
};
String.prototype.startWith = function (str) {
    if (str == null || str === '' || this.length === 0 || str.length > this.length)
        return false;
    return this.substr(0, str.length).compare(str);
};
String.prototype.endWith = function (str) {
    if (str == null || str === "" || this.length === 0 || str.length > this.length)
        return false;
    return this.substring(this.length - str.length) === str;
};
String.prototype.replaceFirstUP = function () {
    if (this.length === 0)
        return this;
    return this.replace(/\b(\w)|\s(\w)|(_\w)/g, function (m) {
        if (m.indexOf('_') > -1) {
            return m.substring(m.indexOf('_') + 1).toUpperCase();
        } else
            return m.toUpperCase();
    });
};
String.prototype.replaceFirstLow = function () {
    if (this.length === 0)
        return this;
    return this.replace(/\b(\w)|\s(\w)|(_\w)/g, function (m) {
        if (m.indexOf('_') > -1) {
            return m.substring(m.indexOf('_') + 1).toLowerCase();
        } else
            return m.toLowerCase();
    });
};
String.prototype.replaceAll = function(regexp, replacement) {
    return this.replace(new RegExp(regexp, "gm"), replacement);
};
// end of string

var nqcxPost = function (url, data, callBack) {
    $.post(url, data, function (resonse) {
        if (!resonse) {
            $.messager.show({
                title: 'Error',
                msg: 'error'
            });
        } else if (!resonse.success && resonse.errorCode === '11') {
            if (retryTimes === 0) {
                $.messager.show({
                    title: 'Error',
                    msg: resonse.errorText || 'error'
                });
            } else {
                retryTimes--;
                retryConnect(function () {
                    nqcxPost(url, data, callBack)
                });
            }
        } else {
            retryTimes = 1;
            if (callBack)
                callBack(resonse);
        }
    }, 'json');

    var retryTimes = 1;
    var retryConnect;
};

var nqcxGet = function (url, data, callBack) {
    if (url) {
        if (url.indexOf('?') === -1)
            url += '?t=' + (new Date()).getTime();
        else
            url += '&t=' + (new Date()).getTime();
    }

    $.get(url, data, function (response) {
        if (!response) {
            $.messager.show({
                title: 'Error',
                msg: 'error'
            });
        } else if (!response.success && response.errorCode === '11') {
            if (retryTimes === 0) {
                $.messager.show({
                    title: 'Error',
                    msg: response.errorText || 'error'
                });
            } else {
                retryTimes--;
                if (retryConnect)
                    retryConnect(function () {
                        nqcxGet(url, data, callBack)
                    });
            }
        } else {
            retryTimes = 1;
            if (callBack)
                callBack(response);
        }
    }, 'json');

    var retryTimes = 1;
    var retryConnect;
};
