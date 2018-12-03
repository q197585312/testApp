
var _dicTimer = {};
var _dicLID = {};
var _dicUrl = {};
var _dicT = {};
var _dicGtT = {};
var _dicPMS = {};
var _dataM = {};
//var _sportNum = '';
var _sportID = '';
var ACT_AccType = 'MY';
var ACT_UserName = '';
var ACT_UseWebSocket = '0';
var _tf = -1;
var _lastDbId = '';
//var favid = '';
//var _selSpt = '';
var _laertime = '';
var ia = "0";
var iftime = "";
//var ot_ = '0';
var isfav = false;
var isbet = true;
var seldbid = "";
function setSelLeague(_selSpt, _id) {
    sessionStorage.setItem(_selSpt, _id);
    refData();
    //loadOddsShow(_lastDbId);
}
function getSelLeague(_selSpt) {
    //var _selSpt = _dbId.split('_')[0] + '_' + (_ot == 'e' ? '1' : '0');
    return sessionStorage.getItem(_selSpt) || '';
}
function setFav(_snum, _addkey, _delkey, all, len) {
    var _dicTem = JSON.parse(sessionStorage.getItem('_SEL_FAV')) || {};
    //var snum = sessionStorage.getItem('snum') != null ? sessionStorage.getItem('snum').split(',') : [];
    if (!_dicTem[_snum]) {
        _dicTem[_snum] = [];
    }
    if (_addkey != null) {
        _dicTem[_snum].indexOf(_addkey) == -1 && _dicTem[_snum].push(_addkey);
        // snum.indexOf(_snum) == -1 && snum.push(_snum);
    }
    if (_delkey != null) {
        _dicTem[_snum].indexOf(_delkey) != -1 && _dicTem[_snum].splice(_dicTem[_snum].indexOf(_delkey), 1);
        // snum.indexOf('' + _snum + '') != -1 &&_dicTem[_snum].length<1&& snum.splice(snum.indexOf('' + _snum + ''), 1);
    }
    sessionStorage.setItem('_SEL_FAV', JSON.stringify(_dicTem))//转化json为字符串存入
    refData();


    // sessionStorage.setItem('snum', snum);
    //var _$spl = $("#sportList");

    //if (_$spl.find('.MyFavorites').length == 1) {
    //    if (len == (all - 1) || (all == undefined && len == undefined))
    //    {
    //        var FAID = _$spl.find('.MyFavorites')[0].id;
    //        if (FAID == 'MyFavorites') {
    //            var havefav = false;
    //            for (var keyname in _dicTem)
    //            {
    //                if (_dicTem[keyname].length > 0)
    //                {
    //                    havefav = true;
    //                }
    //            }
    //            if (havefav) {
    //                if (isclickfav) {
    //                    refDataFav();
    //                }
    //            } else
    //            {
    //                loadOddsShow(_$spl.find('.category-sportList.active').find('.category-sub-list.active').attr('dtid'))
    //            }

    //            //if (_dicTem.length == 0) {
    //            //    loadOddsShow(_$spl.find('.category-sportList.active').find('.category-sub-list.active').attr('dtid'))
    //            //} else {
    //            //    if (_dicTem.length == 1 && _dicTem[0] == '') {
    //            //        loadOddsShow(_$spl.find('.category-sportList.active').find('.category-sub-list.active').attr('dtid'))
    //            //    } else {
    //            //        if (isclickfav) {
    //            //            //loadOddsShow('1_0_2');
    //            //            refData();
    //            //        }
    //            //    }

    //            //}
    //        }
    //    }

    //}
}
function getFavStare(_snum, _addkey) {
    var _dicTem = JSON.parse(sessionStorage.getItem('_SEL_FAV')) || {};//转化为json数据
    if (!_dicTem[_snum]) {
        _dicTem[_snum] = [];
    }
    return _dicTem[_snum].indexOf(_addkey) != -1;
}

function rasiz() {
    hegit();
    var widt = $(window).width();
    if (widt > 1180)
        widt = 1180;
    if ($("#wfForm01").length > 0) {
        $("#wfForm01").find("#fraHidden").attr("style", "min-width:985px;max-width:1180px;width:" + widt + "px; height:40px;float:left;")
    } else {
        $("#fraHidden").attr("style", "min-width:985px;max-width:1180px;width:" + widt + "px; height:140px;float:left;")
    }
}

function loadOddsShow(_dbId, _sBtn, _op, _ertime) {
    sessionStorage.removeItem('morebet');
    rasiz();


    if (_dbId.split('_')[0] == "1") {
        _laertime = $("#lstDate").val();
    } else {
        _laertime = '7';
    }
    var _$spl = $("#sportList");
    var _$cp = $("#mainArea .caption:first");
    var _$tm1 = _$spl.find('.category-sportList.active');
    //_sportNum = _$tm1.attr('sport');
    _sportID = _$tm1.find('.category-sub-list.active').attr('dtid') || "1_1_2";
    ACT_AccType = $("#accTpLst").val();
    //var _iconsp = 'icon-sport' + iconSprt[_$tm1.attr('sport')];
    var _ttname = _$tm1.find('li.active .betTypeName:first').html();
    _ttname = _$tm1.find('li.active .betTypeName:first').attr('title')

    _sBtn = _sBtn || $('#divOMD').find('.C' + _dbId).attr('sBtn') || '00000101';
    var _arrBtn = ['btnPar'];
    var _ot = 't';
    if (_dbId == '1_0_2' || _dbId == '1_0_1') {
        _iconsp = 'sportFavorite';
        _ttname = $('#mc1 .heading.icon-favorite .text').html();
        _sBtn = '00000101';
    } else {
        $("#MyFavorites").removeClass("MyFavorites");
    }
    if (_$spl.hasClass('live')) {
        _ot = 'r';
        var _arrTm = [];
        _iconsp = 'sportLive';
        _ttname = 'Live';
        var _$liveCkb = _$spl.find('.hasRun .checkbox.checkbox-checked');
        for (var i = 0, leni = _$liveCkb.length; i < leni; i++) {
            var _$lli = _$liveCkb.eq(i).parents('li[sport]:first').find('li[dtid]:first');
            if (_$lli.length > 0) {
                _arrTm.push(_$lli.attr('dtid'));
            }
        }
        _dbId = _arrTm.join(',');
        _sBtn = '11111101';
    } else if (_$spl.hasClass('early')) {
        _ot = 'e';
    }


    //for (var i = 0, leni = _sBtn.length; i < leni; i++) {
    //    if (_sBtn[i] == 1) $('#' + _arrBtn[i]).show(); else $('#' + _arrBtn[i]).hide();
    //}

    if (_dbId == '1_1_3' || _dbId == '1_1_2' || _dbId == '1_1_1') {
        $(".LinkButton2").show();
        if (_ot == 'e') {
            $('#btnPar').show();
            $("#lstDate").show();
            $("#eray").show();
            ia = '1';
        } else {
            $('#btnPar').show();
        }
        $("#typetitle").hide();
        if (_dbId == '1_1_3') {
            $("#bleLines").addClass("icon_DoubleLine").removeClass("icon_SingleLine")

        }
        if (_dbId == '1_1_2') {
            $("#bleLines").addClass("icon_SingleLine").removeClass("icon_DoubleLine")
        }
        $("#btnlistmiandian").hide();
        $("#MyFavorites").removeClass("MyFavorites");
        if (RES.islogin != "True")
            $("#btnlist").show();

    } else {
        if (_dbId == '1_3') {
            $("#MixToday").show();
            _ttname = _$spl.find('li[dtid="1_3"] span:first').attr("title");
        } else {
            $("#MixToday").hide();
        }
        if (_dbId == '1_0_2') {
            $("#typetitle").hide();
            $('#btnPar').hide();
            $("#lstDate").hide();
            //$(".LinkButton2").removeAttr("onclick");
            $(".LinkButton2").hide();
        } else {
            $(".LinkButton2").show();
            if (_dbId == '1_17' || _dbId == '1_17_2' || _dbId == '1_17_3' || _dbId == '1_17_4' || _dbId == '1_17_5') {
                if (_ot == 'e') {
                    ia = '1';
                    $('#btnPar').hide();
                    $("#lstDate").show();
                    $("#btnlistmiandian").show();
                    $("#typetitle").show();
                    $(".titletype").html(_ttname);
                    $(".LinkButton").attr("onclick", "loadOddsShow('" + _dbId + "')");
                } else {
                    $('#btnPar').hide();
                    $("#lstDate").hide();
                    $("#btnlistmiandian").show();
                    $("#typetitle").show();
                    $(".titletype").html(_ttname);
                    $(".LinkButton").attr("onclick", "loadOddsShow('" + _dbId + "')");
                }

            } else {
                if (_ot == 'e') {
                    $('#btnPar').hide();
                    $("#lstDate").hide();
                } else {
                    $('#btnPar').hide();
                }
                $("#typetitle").show();
                $(".titletype").html(_ttname);
                $(".LinkButton").attr("onclick", "loadOddsShow('" + _dbId + "')");
                $("#btnlistmiandian").hide();
            }
            $("#MyFavorites").removeClass("MyFavorites");
        }


        //  $('#btnPar').hide(); $("#lstDate").hide();
    }

    $('#oddsTable').empty()
        .append(
            '<div id="showloading"><img style="position: absolute; top: 325px; left: 655px;" src="../Img/spinner.gif" border="0" alt="" /></div>');
    //改变数据菜单展示图标显示
    if (_op != undefined) {
        if (_op.ov == "1") {
            $("#SortTypeSoring").addClass("icon_SortByTime").removeClass("icon_NormalSorting");
            $("#TopSortTypeSoring").addClass("icon_SortByTime").removeClass("icon_NormalSorting")
        }
        if (_op.ov == "0") {
            $("#SortTypeSoring").addClass("icon_NormalSorting").removeClass("icon_SortByTime");
            $("#TopSortTypeSoring").addClass("icon_NormalSorting").removeClass("icon_SortByTime")
        }
       if (_op.mt == "0"){
           $("#marketLstmenu").addClass("icon_AllMarkets").removeClass("icon_MainMarkets").removeClass("icon_OtherMarkets");
           $("#TopmarketLstmenu").addClass("icon_AllMarkets").removeClass("icon_MainMarkets").removeClass("icon_OtherMarkets");
        }
       if (_op.mt == "1") {
           $("#marketLstmenu").addClass("icon_MainMarkets").removeClass("icon_AllMarkets").removeClass("icon_OtherMarkets");
           $("#TopmarketLstmenu").addClass("icon_MainMarkets").removeClass("icon_AllMarkets").removeClass("icon_OtherMarkets");
       }

        if (_op.mt == "2") {
            $("#marketLstmenu").addClass("icon_OtherMarkets").removeClass("icon_AllMarkets").removeClass("icon_MainMarkets");
            $("#TopmarketLstmenu").addClass("icon_OtherMarkets").removeClass("icon_AllMarkets").removeClass("icon_MainMarkets")
        }

    }
    for (var _id in _dicTimer) {
        if (_dicTimer[_id] != null) {
            if (String(_dicTimer[_id]) == "[object WebSocket]") { if (_dicTimer[_id].readyState == 1) { _dicTimer[_id].close(); } }
            else { clearTimeout(_dicTimer[_id]); } _dicTimer[_id] = null;
        }
    }
    // _$cp.removeClass(_$cp.attr('sport')).addClass(_iconsp).attr('sport', _iconsp);
    // _$cp.find('.mainTitle:first').html(_ttname);
    _dataM = {}; _dicLID = {}; _dicUrl = {}; _dicT = {}; _dicGtT = {}; _dicPMS = {};
    _selSpt = _$tm1.attr('sport') + '_' + (_ot == 'e' ? '1' : '0');
    if ($('#lsttf').hasClass('timefilter') || $('#Toplsttf').hasClass('timefilter')) {
        _tf = $("#lsttf").val();
    }
    var _param;

    if (_dbId != "1_0_2") {
        _lastDbId = _dbId;
    }



    if (_dbId == "1_0_2") {
        var _arrTmFav = [];
        //var favsocodds = [];
        //var sporid = sessionStorage.getItem('snum').split(',');
        var spfav = JSON.parse(sessionStorage.getItem('_SEL_FAV'));

        for (var keyname in spfav) {
            var dtidobj = $("#sportList").find("[sport='" + keyname + "']").find('li[dtid]:first');

            if (dtidobj.length > 0 && spfav[keyname].length) {
                if (keyname == "1") {
                    //if (_lastDbId == "1_1_2" && _arrTmFav.indexOf("1_1_2") == -1) {
                    //    _arrTmFav.push("1_1_2");
                    //}
                    //else if (_lastDbId == "1_1_3" && _arrTmFav.indexOf("1_1_3") == -1) {
                    //    _arrTmFav.push("1_1_3");
                    //}
                    if (_lastDbId == "1_1_2") {
                        _arrTmFav.push("1_1_2");
                    }
                    else if (_lastDbId == "1_1_3") {
                        _arrTmFav.push("1_1_3");
                    }
                    else {
                        _arrTmFav.push("1_1_3");
                    }
                } else {
                    if (_arrTmFav.indexOf(dtidobj.attr('dtid')) == -1) {
                        _arrTmFav.push(dtidobj.attr('dtid'));
                    }
                }
            }
            _dbId = _arrTmFav.join(',');
            //if (favsocodds.indexOf(favid[i][1])) {
            //    favsocodds.push(favid[i][1]);
            //}
        }
        isfav = true;
        if (_laertime == "6" || _laertime == "7")
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType, ia: 1 };
        else
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType };
    }
    else {
        isfav = false;
        //isclickfav = 0;
        if (_laertime == "6" || _laertime=="7")
        {
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType, ia: 1 };
            //ot_ = "1";
        }
        else {
            //ot_ = "0";
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType };
        }

    }
    $.extend(_param, (_op || {}));
    ajCode(_param, null, function (_db) { });

}
function refData(_dbId) {
    if (_dbId == null) {
        $('#oddsTable>table[odbid]').attr('odbid', function (i, _val) {
            _dicLID[_val] = '';
            if (ACT_UseWebSocket == '1') LinkWS(_val);
            else ajaxOdds(_val);
        });
    } else {
        _dicLID[_dbId] = '';
        if (ACT_UseWebSocket == '1') LinkWS(_dbId);
        else ajaxOdds(_dbId);
    }
}
////Fav收藏取消数据刷新
//function refDataFav(_dbId) {
//    if (_dbId == null) {
//        $('#oddsTable>table[odbid]').attr('odbid', function (i, _val) {//找的所有table
//            //if (!$('#oddsTable').find('[odbid="' + _val + '"]').is(":hidden")) {//判断table是否隐藏
//            //    if ($('#oddsTable').find('[odbid="' + _val + '"]').find("img[fav='1']").length > 0) {//判断下面是否还有收藏
//                    if (ACT_UseWebSocket == '1') LinkWS(_val);
//                    else ajaxOdds(_val);
//            //    }
//            //    else {
//            //        $('#oddsTable').find('[odbid="' + _val + '"]').remove();
//            //    }

//            //}
//        });
//    } else {
//        if (ACT_UseWebSocket == '1') LinkWS(_dbId);
//        else ajaxOdds(_dbId);
//    }
//}
function ajaxOdds(_dbId, url, _t) {

    if (_dicTimer[_dbId] != null) { clearTimeout(_dicTimer[_dbId]); _dicTimer[_dbId] = null; }
    if (url != null && _dicUrl[_dbId] == null) _dicUrl[_dbId] = url;
    if (_t == null) { _t = 0; } else if (_dicT[_dbId] == null) { _dicT[_dbId] = _t; _t = 0; } else if (_dicT[_dbId] != null) { _t = _dicT[_dbId]; }
    //var ds = $("#oddsTable").find('iframe').hasClass('iframe12');//检查是否加载iframe，没有加载执行球赛数据定时
    //if (!ds)
    //{

    _dicTimer[_dbId] = setTimeout(function () {
        if (_dicUrl[_dbId] == null)
            return;

        var _selSpt = _dbId.split('_')[0] + '_' + ($("#sportList").hasClass('early') ? '1' : '0');
        //var _fav = (_dbId == '1_0_2_e' || _dbId == '1_0_2_t' || _dbId == '1_0_2_r') ? _dicFavS.join('|') : '';
        var _dicfav = JSON.parse(sessionStorage.getItem('_SEL_FAV')) || {};

        //param
        var markettype;
        if ($("#marketLstmenu").hasClass('icon_AllMarkets'))
            markettype = 0;
        if ($("#marketLstmenu").hasClass('icon_MainMarkets'))
            markettype = 1;
        if ($("#marketLstmenu").hasClass('icon_OtherMarkets'))
            markettype = 2;

        var sort;
        if ($("#SortTypeSoring").hasClass('icon_NormalSorting'))
            sort = 0;
        else
            sort = 1;
        //var lang = $("#lstLang3").val();
        //var acctype = $("#accTpLsts3").val();

        var _fav = (isfav == true) ? (_dbId && _dbId.split('_').length > 0 && _dicfav && _dicfav[_dbId.split('_')[0]] ? _dicfav[_dbId.split('_')[0]].join('|') : '') : '';
        //var regexp1 = new RegExp('(&ov=[0-9,.]{1,2})');
        //var regexp2 = new RegExp('(&mt=[0-9,.]{1,2})');
        if (isfav && _fav == '') _fav = '0_0_0';
        jQuery.ajax({
            async: true, cache: false, url: _dicUrl[_dbId] + '&LID=' + (_dicLID[_dbId] || '') + '&ov=' + sort + '&mt=' + markettype + '&FAV=' + _fav + '&SL=' + getSelLeague(_selSpt), complete: function (_ort) {
                $("#showloading").hide();
                if (_dicTimer[_dbId] != null) {

                    _ort.statusText != "error" && ShowOddsByQuery(_dbId, _ort.responseText);
                    if (_ort.statusText == 'error') {
                        _dicLID[_dbId] = '';
                    }
                    ajaxOdds(_dbId, _dicUrl[_dbId], _dicT[_dbId]);
                }
            }
        });
    }, 1000 * _t);

    // }

};


function LinkWS(_dbId, url) {
    if (url != null && _dicUrl[_dbId] == null) _dicUrl[_dbId] = url;
    //var _fav = (_dbId == '1_0_2_e' || _dbId == '1_0_2_t' || _dbId == '1_0_2_r') ? _dicFavS.join('|') : '';
    if (_dicUrl[_dbId] == null)
        return;

    var ws = _dicTimer[_dbId];

    if (ws && (ws.readyState == 1)) {
        ws.close();
    } else {
        var _selSpt = _dbId.split('_')[0] + '_' + ($("#sportList").hasClass('early') ? '1' : '0');
        var _dicfav = JSON.parse(sessionStorage.getItem('_SEL_FAV')) || {};
        //param
        var markettype;
        if ($("#marketLstmenu").hasClass('icon_AllMarkets'))
            markettype = 0;
        if ($("#marketLstmenu").hasClass('icon_MainMarkets'))
            markettype = 1;
        if ($("#marketLstmenu").hasClass('icon_OtherMarkets'))
            markettype = 2;

        var sort;
        if ($("#SortTypeSoring").hasClass('icon_NormalSorting'))
            sort = 0;
        else
            sort = 1;
        var _fav = (isfav == true) ? (_dbId && _dbId.split('_').length > 0 && _dicfav && _dicfav[_dbId.split('_')[0]] ? _dicfav[_dbId.split('_')[0]].join('|') : '') : '';
        if (isfav && _fav == '') _fav = '0_0_0';
        var _urltm = _dicUrl[_dbId] + '&LID=' + /*(_dicLID[_dbId] || '') +*/ '&ov=' + sort + '&mt=' + markettype + '&FAV=' + _fav + '&SL=' + getSelLeague(_selSpt);
        if (window.location.protocol == 'https:') {
            _urltm = _urltm.replace('ws://', 'wss://').replace(':8888', '');
        }
        ws = new WebSocket(_urltm);
        _dicTimer[_dbId] = ws;
    }
    ws.onopen = function (e) {
        _pingRet[_dbId] = '';
        console.log("Linked...");
    };
    ws.onmessage = function (e) {
        if (ws && ws.readyState == 1) {
            if (e.data == '3') { _pingRet[_dbId] = '3'; }//响应ping
            else {
                $("#showloading").hide();
                if (_dicTimer[_dbId] != null) {
                    ShowOddsByQuery(_dbId, e.data);
                }
            }
        } else { console.log("ws.readyState=" + ws.readyState); }
    };
    ws.onclose = function (e) {
        //if (_dicTimer[_dbId] != null) {
        //    LinkWS(_dbId);
        //}
        //setTimeout(function () { window.location.reload(); }, 1000);
    };

};

var _pingRet = {};
function pingWebsocket() {
    for (var _dbId in _dicTimer) {
        var ws = _dicTimer[_dbId];
        if (ws != null) {
            var pv = _pingRet[_dbId] || '0';
            if (pv == '2') {//发送2次都没有应答
                _pingRet[_dbId] = '';
                LinkWS(_dbId);
            }
            else if (ws && (ws.readyState == 1)) {
                _pingRet[_dbId] = (pv == '1' || pv == '2' ? '2' : '1');
                ws.send('1');
            } else if (ws && (ws.readyState == 3) || _pingRet[_dbId] != '') {
                LinkWS(_dbId);
            }
        }
    }
}

function ShowOddsByQuery(_dbId, _sDb) {
    try {
        if (_sDb != "" || _sDb != undefined) {
            $("#showloading").hide();
        }
        if (_sDb == "TiMeFiLtEr") //TimeFilter
        {
            location.reload(true);
            return;
        }
        else if (_sDb == "IgNoReReQuEsT") //TimeFilter
        {
            _dicLID[_dbId] = '';
            return;
        }
        else if (_sDb == null || _sDb[0] != '[') //TimeFilter
        {
            _dicLID[_dbId] = '';
            return;
        }

        //$('#oddsTable').empty().append('<div id="showloading"><img style="position: absolute; top: 325px; left: 655px;" src="../Img/spinner.gif" border="0" alt="" /></div>');
        //var stra = _dbId.substr(-1);

        var _data = eval("(" + _sDb + ")");
        //if (_data == undefined) debugger;
        //if (_data == null) debugger;
        //if (_data[0] == null) debugger;
        //if (_data[0][0] == null) debugger;
        var _ot = _data[0][2];
        var _$tb = $('#divOdds_' + _dbId);
        var _$rowMod;
        if (_$tb.length <= 0) {
            //_dicGtT[_dbId] = [,];
            _$tb = $('#divOMD').find('.C' + _dbId.replace('_r', '').replace('_t', '').replace('_e', '')).clone();
            _$rowMod = _$tb.find('.gtDbb').clone(); _$tb.find('.gtDbb').remove();
            _$tb.attr('id', 'divOdds_' + _dbId).attr('odbid', _dbId);
            if (_ot == 'r') _$tb.addClass('gtLive'); else _$tb.addClass('gtToday');
            _dicGtT[_dbId] = [_$tb.attr('gtType'), _$tb.find('col').length];
            if (_ot == 'r') $('#oddsTable').prepend(_$tb); else $('#oddsTable').append(_$tb);

            //增加排序

            if (_ot == 'r') {
                _$tb.find("#types").html('<span id="txtRunning">' + RES.Running + (isfav == true ? ' - ' + $('#sportList li[sport="' + _dbId.split('_')[0] + '"] .sportName').html() : '') + '</span>');
                // $("#oddsTable").find("table[odbid=" +_dbId + "]").find("#types").html('<span id="txtRunning">' +RES.Running + '</span>');
            }
            if (_ot == 't') {
                _$tb.find("#types").html('<span id="txtRunning">' + RES.Today + (isfav == true ? ' - ' + $('#sportList li[sport="' + _dbId.split('_')[0] + '"] .sportName').html() : '') + '</span>');
                //$("#oddsTable").find("table[odbid=" + _dbId + "]").find("#types").html('<span id="txtToday">' + RES.Today + '</span>');
            }
            if (_ot == 'e') {
                _$tb.find("#types").html('<span id="txtRunning">' + RES.Early + (isfav == true ? ' - ' + $('#sportList li[sport="' + _dbId.split('_')[0] + '"] .sportName').html() : '') + '</span>');
                // $("#oddsTable").find("table[odbid=" + _dbId + "]").find("#types").html('<span id="txtToday">' + RES.Early + '</span>');

            }
        } else {
            _$rowMod = $('#divOMD').find('.C' + _dbId.replace('_r', '').replace('_t', '').replace('_e', '') + ' .gtDbb').clone();
        }
        updOddsTB(_dbId, _data, _$tb, _$rowMod);
        if (_$tb.find('tbody.gtDbb:first').length == 0)
            _$tb.hide();
        else _$tb.show();
        _dicLID[_dbId] = _data[0][1];
        //refData();
    }
    catch (err) {
        console.log(err.message);
    }
}

function updOddsTB(_dbId, _data, _$tb, _$rowMod) {
    var _ot = _data[0][2];
    if (_data[0][0] == 1) {
        _$tb.find('tbody.gtDbb').remove();
        _dicPMS[_dbId] = _data[0];// _$tb.find('tbody.gtDbb:first').empty();
        for (var i = 0, len = _data[3].length; i < len; i++) {
            addTBL(_dbId, _data[3][i], true, _$tb, _$rowMod, _data[0]);
        }
        //orgDataR = _data[3];
    }
    else {
        //delete
        for (var i = 0, len = _data[2].length; i < len; i++) {
            delM_S(_dbId, _data[2][i], _$tb, _data[0][2]);
        }
        //New Add
        for (var i = 0, len = _data[3].length; i < len; i++) {
            addTBL(_dbId, _data[3][i], false, _$tb, _$rowMod, _data[0]);
        }
        //update
        for (var i = 0, len = _data[4].length; i < len; i++) {
            addTBL(_dbId, _data[4][i], false, _$tb, _$rowMod, _data[0]);
        }
        //updCol
        if (_data[5] != null) {
            for (var i = 0, len = _data[5].length; i < len; i++) {
                updDtM(_dbId, _data[5][i], _$tb, _$rowMod, _data[0][2]);
            }
        }
    }
};


//function drawTBHd(_$tb, _hdtxt, _ot) {
//    _$tb.empty().html(sb.toString());
//};
var _dicFavS = {};
var _dicFavP = [];
function SetFavAll(_aFavAll, _ot, isAdd) {
    var _$tbL = $(_aFavAll).parents("tbody:first");
    var _imgs = _$tbL.find("img[fav='0']");
    if (_imgs.length <= 0) {
        _imgs = _$tbL.find("img[fav='1']");
    }
    for (var i = 0, len = _imgs.length; i < len; i++) {
        SetFavOne(_imgs[i], _ot, isAdd, (len - 1 != i ? true : false), _imgs.length, i);//全部收藏添加长度和执行次数
    }
}
function SetFavOne(_aFavOne, _ot, isAdd, _Notref, all, len) {
    var _$trM = $(_aFavOne).parents("[oddsid]:first");
    var _img = _$trM.find("img[fav]");
    var _$spl = $("#sportList");

    if (_img.attr("fav") == "0" && isAdd !== false) {
        isAdd = true;
        _img.attr("fav", "1");
        _img.attr("src", "../Img/FavAdd.gif");
    } else {
        isAdd = false;
        _img.attr("fav", "0");
        _img.attr("src", "../Img/FavOri.gif");
    }
    var _spNum = $(_aFavOne).parents('table[odbid]:first').attr('odbid').split('_')[0];
    var mk = _$trM.attr('mkey').split('_')[0] + "_" + _$trM.attr('mkey').split('_')[1] + "_" + _$trM.attr('mkey').split('_')[2];
    if (!$(_aFavOne).hasClass('added') && isAdd !== true || isAdd === true) {
        setFav(_spNum, mk, null, all, len);
        $(_aFavOne).addClass('added');
    } else {
        setFav(_spNum, null, mk, all, len);
        $(_aFavOne).removeClass('added');
    }
}



function setRowTr(_$rowMod, _$tbL, mid, _dr, pm_S, newAll, _dbId, _IsMMMY) {
    //if (newAll) {
    //    _$tr = $(_$rowMod.html());
    //    _$tr.attr('oddsid', (_IsMMMY == true ? _dr.oddsid + "M" : _dr.oddsid));
    //    _$tbL.append(_$tr);
    //    _newtr = true;
    //} else {
    //    _$tr = _$tbL.find("[oddsid='" + (_IsMMMY == true ? _dr.oddsid + "M" : _dr.oddsid) + "']");
    //    _newtr = _$tr.length == 0;
    //    var _preid;
    //    if (_newtr) {
    //        _$tr = $(_$rowMod.html());
    //        _$tr.attr('oddsid', _IsMMMY == true ? _dr.oddsid + "M" : _dr.oddsid);
    //    } else {
    //        _preid = _$tr.prevAll('[oddsid]:first').attr('oddsid') || _$tr.parent().prev().find('>tr[oddsid]:last').attr('oddsid') || '0';
    //    }
    //    //if (_preid == undefined)
    //    if (_preid == undefined||_preid.indexOf('M') < 0)
    //    {
    //        if (_preid != _dr.preoddsid) {
    //            var _$pretr = _$tbL.find("[oddsid='" + _dr.preoddsid + "']");
    //            if (_$pretr.length == 0) {
    //                _$tbL.find('tr.league:first').after(_$tr);
    //            } else {
    //                _$pretr.after(_$tr);
    //            }
    //        }
    //    }
    //}
    //var IsChangeGoal = false;
    //if (_$tr.attr('runscore') != "" && _$tr.attr('runscore') != undefined && _$tr.attr('runscore') != _dr.runhomesocre + '-' + _dr.runawayscore)
    //    IsChangeGoal = true;
    //if (_dr.isRun) {
    //    _$tr.attr({ 'preoddsid': (_preid == undefined ? _dr.preoddsid : (_preid.indexOf('M') > 0 ? _preid.replace('M', '') : _dr.preoddsid)), 'runscore': _dr.runhomesocre + '-' + _dr.runawayscore, 'mkey': mid + '_' + _dr.hid + '_' + _dr.aid + '_' + _dr.isgive });
    //} else {
    //    _$tr.attr({ 'preoddsid': (_preid == undefined ? _dr.preoddsid : (_preid.indexOf('M') > 0 ? _preid.replace('M', '') : _dr.preoddsid)), 'mkey': mid + '_' + _dr.hid + '_' + _dr.aid + '_' + _dr.isgive });
    //}
    //if (_dr.hid == 3325 && _dr.aid == 2180) {
    //    debugger;
    //}

    //记录定位置
    var _$tr = null, _newtr = false;
    var mmmyNum = _IsMMMY ? '1' : '0';
    if (newAll) {
        _$tr = $(_$rowMod.html());
        _$tr.attr('oddsid', _dr.oddsid).attr('MMMY', mmmyNum);
        if (mBetOddsLId == mid) {
            var _$trTm = _$tbL.find('.gtRow[oddsid="' + mBetOddsId + '"]:first');
            var _mbMkey = _$trTm.attr('mkey');
            if (mid + '_' + _dr.hid + '_' + _dr.aid + '_' + _dr.isgive == _mbMkey) {
                _$trTm.prev().nextAll('.gtRow[mkey="' + _mbMkey + '"]:last').after(_$tr);
            } else {
                _$tbL.append(_$tr);
            }
        } else {
            _$tbL.append(_$tr);
        }
        _newtr = true;
        //赋值
        setRowVal(_$tbL, _$tr, mid, _dr, pm_S, _dbId, _newtr, _IsMMMY);
    }
    else {
        var _$tb = _$tbL.parent();
        _$tr = _$tbL.find("tr.gtRow[oddsid='" + _dr.oddsid + "'][MMMY='" + mmmyNum+ "']");
        if (_$tr == null || _$tr.length == 0) {
            _$tr = $(_$rowMod.html());
            _$tr.attr('oddsid', _dr.oddsid).attr('MMMY', mmmyNum);
            _$tbL.append(_$tr);
            _newtr = true;
            /*
            //赋值
            setRowVal(_$tbL, _$tr, mid, _dr, pm_S, _dbId, _newtr, _IsMMMY);
            */
        }
        if ((_$tr.prevAll('tr[oddsid!="' + _dr.oddsid + '"]:first').attr('oddsid') || _$tr.parent().prev().find('>tr[oddsid]:last').attr('oddsid') || _$tbL.prev().find('>tr[oddsid]:last').attr('oddsid') || '0') != _dr.preoddsid) {

            setRowVal(_$tbL, _$tr, mid, _dr, pm_S, _dbId, _newtr, _IsMMMY);
            var _$TrS = _$tbL.find("tr[oddsid='" + _dr.oddsid + "']");
            if (_dr.preoddsid == '0') {
                _$tbL.find('tr.league:first').after(_$TrS);
                if (_$tb.find('[soclid]:first').length > 0) {
                    _$tb.find('[soclid]:first').before(_$tbL);
                } else { _$tb.append(_$tbL); }
                mergeTbL(_$tbL);
            }
            else {
                var _$pretr = _$tbL.find("tr.gtRow[oddsid='" + _dr.preoddsid + "']:last");
                //var _$preMortr = _$tbL.find("tr[mbkey='" + _dr.preoddsid + "']:last");
                if (_$pretr.length == 0) {
                    _$pretr = _$tbL.parent().find("tr.gtRow[oddsid='" + _dr.preoddsid + "']:last");
                    //_$preMortr = _$tbL.parent().find("tr[mbkey='" + _dr.preoddsid + "']:last");
                }
                //if (_$preMortr.length > 0) {
                //    _$pretr = _$preMortr;
                //}
                var _$trMoreBet = _$pretr.nextAll('.moreBetTr[mkey="' + _$pretr.attr('mkey') + '"]');
                if (_$pretr.length == 0) {
                    refData(_dbId); return false;
                } else {
                    if (_$pretr.parent().attr('soclid') == mid) {
                        if (_$pretr.attr('mkey') == _$tr.attr('mkey') ) {
                            _$pretr.after(_$TrS);
                        } else {
                            if (_$trMoreBet.length > 0) {
                                _$trMoreBet.after(_$TrS);
                            } else {
                                _$pretr.after(_$TrS);
                            }
                        }
                        //_$pretr.after(_$TrS);
                        mergeTbL(_$tbL);
                        mergeTbL(_$pretr.parent());
                    } else {
                        //if (pm_S[3] != '1') {
                        //    _$pretr.parent().after(_$tbL);
                        //    mergeTbL(_$tbL);
                        //} else {
                        var _$trPre = _$tr.prevAll('[oddsid]:not([oddsid="' + _dr.oddsid + '"]):first');
                        var _merge1, _merge2, _merge3;
                        if (_$trPre.length > 0) {
                            //复制生成空联赛架构
                            //debugger;
                            var _$tbtm1 = _$tbL.clone();
                            _$tbtm1.find('tr[oddsid]').remove();
                            _$tbtm1.append(_$trPre.prevAll('[oddsid]'));
                            _$tbtm1.append(_$trPre);
                            _$tbL.before(_$tbtm1);
                            _merge1 = _$tbtm1;
                        }

                        _$trPre = _$pretr.nextAll('tr.gtRow[oddsid]:not([oddsid="' + _$pretr.attr('oddsid') + '"]):first');
                        if (_$trPre.length > 0 ) {
                            if (_$pretr.attr('mkey') == _$trPre.attr('mkey')) {
                                var _$mfirst = _$trPre.prevAll('tr.grRow[mkey="' + _$trPre.attr('mkey') + '"]:last');
                                _$trPre.find('td.tdLive:first').html(_$mfirst.find('td.tdLive:first').html());
                                _$trPre.find('td.tdshoucang:first').html(_$mfirst.find('td.tdshoucang:first').html());
                                _$trPre.find('td.tdTeam:first').html(_$mfirst.find('td.tdTeam:first').html());
                                _$trPre.find('td.tdLast:first').html(_$mfirst.find('td.tdLast:first').html());
                            }
                            //复制生成空联赛架构
                            var _$tbtm1 = _$pretr.parent().clone();
                            _$tbtm1.find('tr[oddsid]').remove();
                            var _Nall = _$trPre.nextAll('[oddsid]');
                            var _$tblpre = _$trPre.parent();
                            _$tbtm1.append(_$trPre);
                            _$tbtm1.append(_Nall);
                            _$pretr.parent().after(_$tbtm1);
                            _$pretr.parent().after(_$tbL);
                            if (_$trMoreBet.length > 0) {
                                _$pretr.nextAll('tr.gtRow[mkey="' + _$pretr.attr('mkey') + '"]:last').after(_$trMoreBet);
                            }
                            //mergeTbL(_$tblpre);
                            _merge2 = _$tblpre;
                            _merge3 = _$pretr.parent();
                            mergeTbL(_$tbL);
                        } else {
                            _$pretr.parent().after(_$tbL);
                            mergeTbL(_$tbL);
                        }

                        _merge1 && mergeTbL(_merge1);
                        _merge2 && mergeTbL(_merge2);
                        _merge3 && mergeTbL(_merge3);

                        //}
                    }
                }
            }

        } else {
            setRowVal(_$tbL, _$tr, mid, _dr, pm_S, _dbId, _newtr, _IsMMMY);
        }
        ////_$tr = _$tbL.find("[oddsid='" + _dr.oddsid + "']");pm_S[3]
        ////if (_$tr.length == 0)
        ////    _$tr = _$tbL.parent().find("[oddsid='" + _dr.oddsid + "']");

        ////if (_$tr.length == 0) {
        ////    _$tr = $(_$rowMod.html());
        ////    _$tr.attr('oddsid', _dr.oddsid).attr('MMMY', mmmyNum);
        ////    _$tbL.append(_$tr);
        ////    _newtr = true;
        ////}
        ////else {
        ////    if (_$tbL.find('tr.gtRow[oddsid]').length == 0) {
        ////        //debugger
        ////        _$tbL.remove();
        ////    }
        ////    _$tbL = _$tr.parent();
        ////}
        ////} else {
        //////判断缅甸盘口，是的取第一个不是判断是不是有相同oddsid有取第二个（第一个是缅甸盘口）没有相同直接去oddsid的tr
        ////if (_IsMMMY==true)
        ////    _$tr = _$tbL.find("[oddsid='" + _dr.oddsid + "']:first");
        ////else
        ////    _$tr = _$tbL.find("[oddsid='" + _dr.oddsid + "']").length > 1 ?$( _$tbL.find("[oddsid='" + _dr.oddsid + "']")[1]) : _$tbL.find("[oddsid='" + _dr.oddsid + "']");
        ////_newtr = _$tr.length == 0;
        //var _preid = -1;
        ////if (_newtr) {
        ////    _$tr = $(_$rowMod.html());
        ////    _$tr.attr('oddsid', _dr.oddsid);
        ////} else {
        ////if (!_newtr) {
        //    _preid = _$tr.prevAll('tr[oddsid!="' + _dr.oddsid + '"]:first').attr('oddsid') || _$tr.parent().prev().find('>tr[oddsid]:last').attr('oddsid') || _$tbL.prev().find('>tr[oddsid]:last').attr('oddsid') || '0';
        ////}
        //if (_preid != _dr.preoddsid) {
        //    var _$pretr;
        //    //if (_$tbL.find("[oddsid='" + _dr.oddsid + "']").length > 1)//判断缅甸盘口相同oddsid和preoddsid，用于排序
        //    //    _$pretr = _$tbL.find("[oddsid='" + _dr.oddsid + "']");
        //    //else
        //    _$pretr = _$tbL.find("tr[oddsid='" + _dr.preoddsid + "']:last");
        //    if (_$pretr.length > 0) {
        //        _$pretr.after(_$tr);
        //    }
        //    else {
        //        //如果前一ID在其他联赛，必须拆分当前联赛
        //        _$pretr = _$tr.prevAll('tr[oddsid!="' + _dr.oddsid + '"]:first');
        //        if (_$pretr.length > 0) {//查找_$tr中不！=_dr.oddsid所有相同元素
        //            //复制生成空联赛架构
        //            var _$tbtm1 = _$tbL.clone();
        //            _$tbtm1.find('tr[oddsid]').remove();
        //            var _trNextall = _$tr.nextAll();
        //            _$tbtm1.append(_$pretr.prevAll('tr[oddsid]'));
        //            _$tbtm1.append(_$pretr);
        //            _$tbL.before(_$tbtm1);
        //        }

        //        _$pretr = _$tbL.parent().find("tr[oddsid='" + _dr.preoddsid + "']:last");
        //        if (_$pretr.length > 0) {
        //            if (_$pretr.parent().attr('soclid') == mid) {
        //                _$pretr.after(_$tr);
        //                mergeTbL(_$tbL, _dbId);
        //                _$tbL = _$pretr.parent();
        //            } else {
        //                //if (_$tr.prevAll('.gtRow[oddsid!="' + _dr.oddsid + '"]:first').length > 0) {//查找_$tr中不！=_dr.oddsid所有相同元素
        //                //    var _$tbtm1 = _$tbL.clone();//复制$tbl
        //                //    //var _$tbtm2 = _$tr.parent();//元素_$tr集合中每个元素的父元素
        //                //    _$tbtm1.find('tr.gtRow[oddsid]').remove();//查找tr中有oddsid的删掉
        //                //    var _trNextall = _$tr.nextAll();//获得匹配元素集合中每个元素的所有跟随的同胞元素也就是这个下面所有的相同元素
        //                //    _$tbtm1.append(_$tr);//添加_$tr到_$tbtm1
        //                //    _$tbtm1.append(_trNextall);//添加_trNextall到_$tbtm1
        //                //    //if (_$tbtm2.find('tr.gtRow[oddsid]').length == 0) {//查找_$tbtm2中tr.gtRow[oddsid]匹配元素
        //                //    //    _$tbtm2.remove();//删除_$tbtm2
        //                //    //}
        //                //    //if (_$tbL.find('tr.gtRow[oddsid]').length == 0) {//查找_$tbL中tr.gtRow[oddsid]匹配元素
        //                //    //    _$tbL.remove();//删除_$tbL
        //                //    //}

        //                //    //_$tbtm2.find('tr.gtRow[oddsid]').length == 0 && _$tbtm2.remove();
        //                //    //_$tbL.find('tr.gtRow[oddsid]').length == 0 && _$tbL.remove();
        //                //    _$tbL = _$tbtm1;//重新定义_$tbL 使值为 _$tbtm1
        //                //}
        //                //前一联赛中间插入，需拆分
        //                if (_$pretr.nextAll('.gtRow[oddsid!="' + _dr.preoddsid + '"]:first').length > 0) {
        //                    var _$tbtm1 = _$pretr.parent().clone();
        //                    _$tbtm1.find('tr.gtRow[oddsid]').remove();
        //                    var _trNextall = _$pretr.nextAll('.gtRow[oddsid!="' + _dr.preoddsid + '"]:first').nextAll();
        //                    _$tbtm1.append(_$pretr.nextAll('.gtRow[oddsid!="' + _dr.preoddsid + '"]:first'));
        //                    _$tbtm1.append(_trNextall);
        //                    _$pretr.parent().after(_$tbtm1);
        //                }
        //                //_$tbL.next() && _$pretr.parent().after(_$tbL.nextAll());
        //                _$pretr.parent().after(_$tbL);
        //                mergeTbL(_$tbL);

        //                //if (_$tbL.next().attr('soclid') > 0 && _$tbL.attr('soclid') == _$tbL.next().attr('soclid')) {
        //                //    _$tbL.append(_$tbL.next().find('tr.league:first').nextAll());
        //                //    _$tbL.next().remove();
        //                //}
        //            }
        //        }
        //        else {
        //            if (_dr.preoddsid == '0') {
        //                var _$tbTm = _$tbL.parent();
        //                //if (_$tr.prevAll('.gtRow[oddsid!="' + _dr.oddsid + '"]:first').length > 0) {
        //                //    var _$tbtm1 = _$tbL.clone();
        //                //    _$tbtm1.find('tr.gtRow[oddsid]').remove();
        //                //    var _trNextall = _$tr.nextAll();
        //                //    _$tbtm1.append(_$tr);
        //                //    _$tbtm1.append(_trNextall);
        //                //    _$tbL = _$tbtm1;
        //                //}
        //                //_$tbL.next() && _$tbTm.find('>tbody:first').after(_$tbL.nextAll());
        //                _$tbTm.find('>tbody:first').after(_$tbL);
        //                mergeTbL(_$tbL);
        //                //if (_$tbL.next().attr('soclid') > 0 && _$tbL.attr('soclid') == _$tbL.next().attr('soclid')) {
        //                //    _$tbL.append(_$tbL.next().find('tr.league:first').nextAll());
        //                //    _$tbL.next().remove();
        //                //}
        //            } else {
        //                refData();
        //                _$tbL.find('tr.league:first').after(_$tr);
        //            }
        //        }
        //    }
        //}
        //_newtr && _$tr.show();
        //_newTbL && _$tbL.show();
        //if (_$tr == null || _$tr.length == 0 || !_$tr.is(":visible")) {
        //    debugger;
        //}
    }

    if (mBetOddsId == _dr.oddsid) {
        showMoreBet(_$tr.find('.tdLast .icon-moreCollapse,.tdLast .icon-moreExpand'), _dr.oddsid, _$tr.attr('mkey'), mid);
    }
    //调整重复名称

    return _$tbL;
}
function mergeTbL(_$tbL, _dbId) {
    if (_$tbL.next().attr('soclid') > 0 && _$tbL.attr('soclid') == _$tbL.next().attr('soclid')) {
        _$tbL.append(_$tbL.next().find('tr.league:first').nextAll());
        _$tbL.next().remove();
    }
    if (_$tbL.prev().attr('soclid') > 0 && _$tbL.attr('soclid') == _$tbL.prev().attr('soclid')) {
        _$tbL.find('tr.league:first').after(_$tbL.prev().find('tr.league:first').nextAll());
        _$tbL.prev().remove();
    }
    if (_$tbL.find('tr.gtRow[oddsid]:first').length == 0) {
        _$tbL.remove();
    } else {
        adjTb(_$tbL, (_dbId == undefined ? false:(_dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_1' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_2' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3')));
    }
}
function setRowVal(_$tbL, _$tr, mid, _dr, pm_S, _dbId, _newtr, _IsMMMY) {
    var mmmyNum = _IsMMMY ? '1' : '0';
    var IsChangeGoal = false;
    //判断两个不同时为0才进入展示界面
    if (_dr.runhomesocre + _dr.runawayscore != "0") {
        if (_$tr.attr('runscore') != "" && _$tr.attr('runscore') != undefined && _$tr.attr('runscore') != _dr.runhomesocre + '-' + _dr.runawayscore) {
            IsChangeGoal = true;
            var ScoreLeagueName = _$tbL.find('.leagueName:first span:first').html();
            showTipScore(ScoreLeagueName, _dr.homeName, _dr.awayName, _$tr.attr('runscore'), _dr.runhomesocre, _dr.runawayscore);
        }
    }
    if (_dr.isRun) {
        _$tr.attr({ 'preoddsid': _dr.preoddsid, 'runscore': _dr.runhomesocre + '-' + _dr.runawayscore, 'mkey': mid + '_' + _dr.hid + '_' + _dr.aid + '_' + _dr.isgive });
        var mkeysorce = mid + '_' + _dr.hid + '_' + _dr.aid + '_' + 0;
        var mkeysorce2 = mid + '_' + _dr.hid + '_' + _dr.aid + '_' + 1;
        _$tr.parents("tbody[soclid]:first").find('[mkey="' + mkeysorce  + '"]').attr('runscore', _dr.runhomesocre + '-' + _dr.runawayscore);
        _$tr.parents("tbody[soclid]:first").find('[mkey="' + mkeysorce2 + '"]').attr('runscore', _dr.runhomesocre + '-' + _dr.runawayscore);
    } else {
        _$tr.attr({ 'preoddsid': _dr.preoddsid, 'mkey': mid + '_' + _dr.hid + '_' + _dr.aid + '_' + _dr.isgive });
    }
    if (_$tr.length > 1) {
        _$tr = _$tr.filter("[MMMY='" + mmmyNum + "']");
    }
    for (var _col in _dr) {
        if (_col === 'oddsid' || _col === 'preoddsid' || _col === 'hid' || _col === 'aid' || _col === 'isgive' || _col === 'tdLast') continue;
        var _cell = _$tr.find('.' + _col);
        var _tdll = _$tr.find('.' + _col).parent();
        if (_newtr) {
            _cell.html(_dr[_col]);
        }
        else if (_cell.html() != _dr[_col]) {

            if (_cell.length > 0 && _col == "live" && IsChangeGoal) {//&& _cell.find("span").hasClass("Heading7 M_Live")
                _cell.addClass('liveChg');
            }

            if (_cell.hasClass('oddsBet')) {
                var _oldn = getNumByS(_cell.html());
                var _newn = getNumByS(_dr[_col]);
                if (_newn == -99999) {
                    _cell.removeClass("indicatorDown").removeClass('indicatorUp');
                    //_tdll.removeClass("NewRunBG").removeClass('indicatorUp');
                }
                else if (_oldn < _newn) {
                    _cell.addClass('indicatorUp').removeClass('indicatorDown').attr("chgTms", 0);
                    // _tdll.addClass('indicatorUp').removeClass('NewRunBG').attr("chgTms", 0);
                }
                else if (_oldn > _newn) {
                    _cell.addClass('indicatorDown').removeClass('indicatorUp').attr("chgTms", 0);
                    // _tdll.addClass('NewRunBG').removeClass('indicatorUp').attr("chgTms", 0);
                }
            }
            _cell.html(_dr[_col]);
        }
    }
    if (_newtr) {
        if (_dbId.replace('_r', '') == "36_23" || _dbId.replace('_t', '') == "36_23" || _dbId.replace('_e', '') == "36_23")
            _$tr.find('.tdLast').append('<div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.jpg" border="0" style="height: 11px;"><span class="GamesSum">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum:'')+'</span></a></div>');
        else if (_dbId.replace('_r', '') == "36_10" || _dbId.replace('_t', '') == "36_10" || _dbId.replace('_e', '') == "36_10")
            _$tr.find('.tdLast').append('<div></div>');
        else if (_dbId.replace('_r', '') == "1_17_4" || _dbId.replace('_t', '') == "1_17_4" || _dbId.replace('_e', '') == "1_17_4" || _dbId.replace('_r', '') == "1_17_2" || _dbId.replace('_t', '') == "1_17_2" || _dbId.replace('_e', '') == "1_17_2")
            _$tr.find('.tdLast').append('<div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.jpg" border="0" style="height: 11px;"><span class="GamesSum">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum : '') +'</span></a></div>');
        else if (_dbId.replace('_r', '') == "1_17_5" || _dbId.replace('_t', '') == "1_17_5" || _dbId.replace('_e', '') == "1_17_5")
            _$tr.find('.tdLast').append('');
        else
            if (RES.islogin == "True")
                _$tr.find('.tdLast').append('<div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.jpg" border="0"><span class="GamesSum">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum : '') +'</span></a></div>');
            else {
                // onclick="openWin(userName=' + RES.logname + '&amp;moduleId=' + _dr.moduleId + '&amp;wDate=' + _dr.wDate + '&amp;homeId=' + _dr.hid + '&amp;awayId=' + _dr.aid + '&amp;isrun=' + (_dr.isRun==true?1:0) + '\')"
                //_$tr.find('.tdLast').append('<button class="flexible specialC icon-moreExpand smallBtn-text" title="More Bet Types" ></button>');  <a title="Forecast" onclick="openWin(\'_view/wfFC_HandicapAndOU_Detail3.html?userName=' + RES.logname + '&amp;moduleId=' + _dr.moduleId + '&amp;wDate=' + _dr.wDate + '&amp;homeId=' + _dr.hid + '&amp;awayId=' + _dr.aid + '&amp;isrun=' + (_dr.isRun==true?1:0) + '\')"><img src="../Img/Forecast.jpg" border="0"></a>
                _$tr.find('.tdLast').append('<div class="CSTurl">' + _dr.STurl + '</div><div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.jpg" border="0" style="height: 11px;"><span class="GamesSum">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum : '') +'</span></a></div><div><a title="Forecast" href="javascript:GetDetails(&quot;userName=' + RES.logname + '&amp;moduleId=' + _dr.moduleId + '&amp;wDate=' + _dr.wDate + '&amp;homeId=' + _dr.hid + '&amp;awayId=' + _dr.aid + '&amp;isRun=' + _dr.isRun + '&quot;)""><img src="../Img/Forecast.jpg" border="0"></a></div>');
            }
        ////_$tr.find('.HAIcon').append('<span class="gticon accent icon-soccer smallBtn"></span>');
    }
    else {
        _$tr.find('.tdLast .CSTurl:first').html(_dr.STurl);
    }
    if (_$tr.find('.shoucang').html() == "") {
        //  _$tr.find('.shoucang').append((_dicFavS.length > 0 && ifArrVal(_dicFavS, _dr.oddsid) == 1) ? '<img fav="1"  src="../Img/FavAdd.gif" border="0" align="absmiddle" onclick="SetFavOne(this)"; class="added"/>' : ' <img fav="0"  src="../Img/FavOri' + '.gif" border="0" align="absmiddle" onclick="SetFavOne(this)";/>');
        _$tr.find('.shoucang').append((getFavStare(_dbId.split('_')[0], mid + '_' + _dr.hid + '_' + _dr.aid) == true) ? '<img fav="1"  style="cursor: pointer;" src="../Img/FavAdd.gif" border="0" align="absmiddle" onclick="SetFavOne(this)"; class="added"/>' : ' <img fav="0" style="cursor: pointer;"  src="../Img/FavOri' + '.gif" border="0" align="absmiddle" onclick="SetFavOne(this)";/>');
    }
    //else {
    //    _$tr.prev().attr('oddsid') == _dr.preoddsid
    //}

    if (_IsMMMY == true)
        _$tr.addClass("altMatch2").removeClass("altMatch");

}

function ss() {
    if (_updBetLsT != null) {
        clearInterval(_updBetLsT); _updBetLsT = null;
    }
    var _delay = 5;
    $("#txtchgTms").empty().html(_delay);
    _lastupdBet = (new Date()).getTime();
    _updBetLsT = setInterval(function () {
        _delay--;
        if (_delay >= 0) {
            $("#txtchgTms").empty().html(_delay);
        } else {
            clearInterval(_updBetLsT); _updBetLsT = null;
        }
    }, 3000);
}
var _timerTip;
function tipCountDown() {
    var _$tip_li = $('#tip_cust88 > ul > li > .tmCountDown');
    var _ntm = (new Date()).getTime();
    _$tip_li.each(function (_li, _inx) {
        var _tm = $(this).attr('tm');
        if (_ntm - _tm >= 1000) {
            var _m = $(this).attr('m');
            _m = _m - 1;
            if (_m < 0) {
                $(this).parent().slideUp({
                    duration: 300, complete: function () { $(this).remove(); }
                });
            } else {
                $(this).attr('tm', _ntm).attr('m', _m).html(_m + 's');
            }
        }
    });
    _timerTip && clearTimeout(_timerTip);
    if (_$tip_li.length > 0) {
        _timerTip = setTimeout(tipCountDown, 500);
    } else { $('#tip_cust88').hide(); }
}


function showTip(_html, _time, _color) {
    if (_time == undefined || _time == null) _time = 3;
    var _$tip = $('#tip_cust88');
    if (_$tip.length == 0) {
        _$tip = $('<div id="tip_cust88" class="tip-cust"><ul class="list-group "></ul></div>');
        _$tip.appendTo('body');
    }
    var _$li = $('<li class="list-group-item" style="display:none;background-color:' + _color + ';"><div class="btn btn-clear" role="button" onclick="$(this).parent().remove();">X</div><span class="tmCountDown" style="color:red" tm="' + (new Date()).getTime() + '" m="' + _time + '">' + _time + 's</span></li>');
    _$li.append(_html);
    _$tip.find('ul:first').append(_$li); _$li.slideDown('fast');
    _$tip.show();
    tipCountDown();
}
//function showTip(_html) {
//    $("#ScoreChangeA").empty().html(_html);
//    ss();
//    setTimeout(function () {
//        $("#ScoreChangeA").children().removeClass("showScore");
//        $("#ScoreChangeA").empty();
//    }, 6000)
//}

function showTipScore(_mName, _hName, _aName, _hoScore, _hnScore, _anScore) {
    var showTipS = new StringBuilder();
    showTipS.append("<div class=''><table align='center' valign='center'  class='Sorcetable'>");
    //showTipS.append("<tr style='height:10px'>");
    //showTipS.append("<td style='width:200px;text-align: left'><span id='txtchgTms'></span>s</td>");
    //showTipS.append("<td style='width:200px;text-align: left'></td>");
    //showTipS.append("<td style='width:30px;text-align:right'><span class='Scoreclose' onclick='showScore();'><a href='#'>X</a></span></td></tr>");
    showTipS.append("<tr ><td id='ScoreChLeagueName' colspan='3' style='text-align: center'>" + _mName + "</td></tr>");
    showTipS.append("<tr >");
    showTipS.append("<td id='ScoreChHomeName' class='tipsnbright' style='text-align: unset'>" + _hName+ "</td>");
    showTipS.append("<td style='width:30px;text-align:center'>-VS-</td><td id='ScoreChAwayName' class='tipsnbleft'>" + _aName + "</td></tr>");
    showTipS.append("<tr>");
    if (_hoScore.split('-')[0] != _hnScore) {
        showTipS.append("<td id='ScoreChHomeG' class='ScoreColor tipsnbright'>" + _hnScore + "</td>");
    } else {
        showTipS.append("<td id='ScoreChHomeG' class='tipsnbright' >" + _hnScore + "</td>");
    }

    showTipS.append("<td style='width:30px;text-align:center'>-</td>");

    if (_hoScore.split('-')[1] != _anScore) {
        showTipS.append("<td id='ScoreChAwayG' class='ScoreColor tipsnbleft' >" + _anScore + "</td></tr>");
    } else {
        showTipS.append("<td id='ScoreChAwayG' class='tipsnbleft'>" + _anScore + "</td></tr>");
    }

    showTipS.append("</table></div>");
    showTip(showTipS.toString(), 3,"#ecf7e4");
    //showTip(showTipS.toString());
    return showTipS.toString();
}

function ifArrVal(arr, value) {//多维数组判断是否存在某值
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] instanceof Array) {//判断是否为多维数组
            if (!ifSingelArrVal(arr[i], value))
                continue;
            else
                return 1;//存在
        } else {
            if (arr[i] == value) {
                return 1;//存在
            }
        }
    }
    return -1;//不存在
}

function ifSingelArrVal(arr, value) {//判断是否存在某值
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == value) {
            return true;//存在
        }
    }
    return false;//不存在
}


function getNumByS(_shtml) {
    var _oldv = null;
    for (var i = 0; i < 5 && isNaN(_shtml) ; i++) {
        try { _shtml = $(_shtml).html(); } catch (err) { _shtml = null; break; }
    }
    if (_shtml == null || _shtml.replace(' ', '') == '' || isNaN(_shtml)) return -99999;
    else return Number(_shtml);
}
function addTBL(_dbId, _rL, updAll, _$tb, _$rowMod, pm_S) {
    var _$tbL = null, _IsNewL = false;
    if (updAll) {
        _$tbL = $('<tbody class="gtDbb" soclid="' + _rL[0][0] + '"><tr class="league" style="cursor:pointer"><td align="left" nowrap="nowrap" style="border: 0px!important;" colspan="' + (_dicGtT[_dbId][1] + 1) + '" >' + ((_dicGtT[_dbId][0] == 'S1' || _dicGtT[_dbId][0] == 'S2') ? "<span class=\"gticon icon-favorite\">" : "") + '<span ><a href="javascript:;" class="L_NameFav_RMOdds" onclick=\"SetFavAll(this, ' + _rL[0][0] + ');\";return false;"><img title="Add All" src="../Img/FavAdd.gif" style="float: left;margin-top: 0px;margin-left: 1px;" border="0" align="absmiddle"></a><div class="leagueName"  style=" width: 877px;" onclick="refreshDiv(this)"><span style="display: inline - block;float: left;">' + _rL[0][1] + '</span>' + '<img class="divloading" style="position: absolute; top: 23px;left: 378px; display: none;" src="../Img/spinner.gif" border="0" alt=""><div class="refreshlogo" onclick="refreshDiv(this)"><img src="../Img/refresh3Run.gif" /></div></div></td></tr></tbody> ');
        _$tb.append(_$tbL); _IsNewL = true;
    } else {
        var _$tr = _$tb.find(">tbody[soclid='" + _rL[0][0] + "'] > tr[oddsid='" + _rL[1][0][0] + "']:first");
        if (_$tr.length > 0) _$tbL = _$tr.parents('tbody:first');
        else _$tbL = _$tb.find(">tbody[soclid='" + _rL[0][0] + "']:first");
        if (_$tbL.length == 0) {
            _$tbL = $('<tbody class="gtDbb" soclid="' + _rL[0][0] + '" ><tr class="league" style="cursor:pointer"><td align="left" nowrap="nowrap" style="border: 0px!important;" colspan="' + (_dicGtT[_dbId][1] + 1) + '" >' + ((_dicGtT[_dbId][0] == 'S1' || _dicGtT[_dbId][0] == 'S2') ? "<span class=\"gticon icon-favorite\">" : "") + '<span ><a href="javascript:;" class="L_NameFav_RMOdds" onclick=\"SetFavAll(this, ' + _rL[0][0] + ');\";return false;"><img title="Add All" src="../Img/FavAdd.gif" style="float: left;margin-top: 0px;margin-left: 1px;" border="0" align="absmiddle"></a><div class="leagueName"  style=" width: 877px;" onclick="refreshDiv(this)"><span style="display: inline - block;float: left;">' + _rL[0][1] + '</span>' + '<img class="divloading" style="position: absolute; top: 23px;left: 378px; display: none;" src="../Img/spinner.gif" border="0" alt=""><div class="refreshlogo" onclick="refreshDiv(this)"><img src="../Img/refresh3Run.gif" /></div></div></td></tr></tbody> ');
            _$tb.append(_$tbL); _IsNewL = true;
        }
    }

    //_$tb.find(">tbody[soclid='" + _rL[0][0] + "']:first");
    //var _IsNewL = _$tbL.length == 0;
    //if (_IsNewL || updAll) {
    //    if (_$tb[0].attributes['isFav'].nodeValue == '1')
    //        _$tbL = $('<tbody class="gtDbb" soclid="' + _rL[0][0] + '"><tr class="league" style="cursor:pointer"><td align="left" nowrap="nowrap" style="border: 0px!important;" colspan="' + (_dicGtT[_dbId][1]+1) + '" >' + ((_dicGtT[_dbId][0] == 'S1' || _dicGtT[_dbId][0] == 'S2') ? "<span class=\"gticon icon-favorite\">" : "") + '<span ><a href="javascript:;" class="L_NameFav_RMOdds" onclick=\"SetFavAll(this, ' + _rL[0][0] + ');\";return false;"><img title="Add All" src="../Img/FavAdd.gif" style="float: left;margin-top: 3px;margin-left: 1px;" border="0" align="absmiddle"></a><div class="leagueName"  style=" width: 877px;" onclick="refreshDiv(this)"><span style="display: inline - block;float: left;">' + _rL[0][1] + '</span>' + '<img class="divloading" style="position: absolute; top: 23px;left: 378px; display: none;" src="../Img/spinner.gif" border="0" alt=""><div class="refreshlogo" onclick="refreshDiv(this)"><img src="../Img/refresh3Run.gif" /></div></div></td></tr></tbody> ');
    //    else
    //        _$tbL = $('<tbody class="gtDbb" soclid="' + _rL[0][0] + '"><tr class="league" style="cursor:pointer"><td align="left" nowrap="nowrap" style="border: 0px!important;" colspan="' + (_dicGtT[_dbId][1]) + '" >' + ((_dicGtT[_dbId][0] == 'S1' || _dicGtT[_dbId][0] == 'S2') ? "<span class=\"gticon icon-favorite\">" : "") + '<span ><div class="leagueName"  style=" width: 877px;" onclick="refreshDiv(this)"><span style="display: inline - block;float: left;">' + _rL[0][1] + '</span>' + '<img class="divloading" style="position: absolute; top: 23px;left: 378px; display: none;" src="../Img/spinner.gif" border="0" alt=""><div class="refreshlogo" onclick="refreshDiv(this)"><img src="../Img/refresh3Run.gif" /></div></div></td></tr></tbody> ');
    //    _$tb.find(">tbody:last").after(_$tbL);
    //}
    //if (_IsNewL && _rL[1].length > 0) {
    //    var _preoid = _rL[1][0].preoddsid;
    //    if (parseInt(_preoid) > 0) {
    //        var _$preTm = _$tb.find("tbody[soclid] >tr[oddsid='" + _preoid + "']:last");
    //        if (_$preTm.length <= 0) {
    //            _$tb.find(">tbody:last").after(_$tbL);
    //        } else {
    //            _$preTm.parents("tbody[soclid]:first").after(_$tbL);
    //        }
    //    } else {
    //        _$tb.find(">tbody:last").after(_$tbL);
    //    }

    //}
    for (var i = 0, len = _rL[1].length; i < len; i++) {
        if (_dicGtT[_dbId][0] == 'S18')
            var _dr = ShowOddsFn[_dicGtT[_dbId][0]](_rL[1][i], _rL[0][0], _rL[0][1], _$tbL, _IsNewL, pm_S, false, _dbId, _$rowMod);
        else
            var _dr = ShowOddsFn[_dicGtT[_dbId][0]](_rL[1][i], _rL[0][0], _rL[0][1], _$tbL, _IsNewL, pm_S);
        _$tbL = setRowTr(_$rowMod, _$tbL, _rL[0][0], _dr, pm_S, updAll,  _dbId, false);
        setDtM(_dbId, pm_S[2], _rL[1][i]);
    }
    //if (_IsNewL) {
    //    var _preoid = _$tbL.find('tr[soclid]:first').attr('preoddsid');
    //    if (parseInt(_preoid) > 0) {
    //        var _$preTm = _$tb.find("tbody[soclid] >tr[oddsid='" + _preoid + "']:first");
    //        if (_$preTm.length <= 0) {
    //            _dicLID[_dbId] = ''; ajaxOdds(_dbId); return;
    //        }
    //        _$preTm.parents("tbody[soclid]:first").after(_$tbL);
    //    } else {
    //        _$tb.find(">tbody:last").after(_$tbL);
    //    }
    //}
//    //当时间排序时，保证操作删除的名称正确
//    if (_$tb.find(">tbody[soclid='" + _rL[0][0] + "']").length > 1) {
//        var  soclidLength=_$tb.find(">tbody[soclid='" +_rL[0][0]+ "']").length-1;
//        _$tbL = _$tb.find(">tbody[soclid='" + _rL[0][0] + "']").eq(soclidLength);
    //} _dbId.replace('_r','').replace('_t','').replace('_e','')=='1_1_1'||_dbId.replace('_r','').replace('_t','').replace('_e','')='1_1_2'||_dbId.replace('_r','').replace('_t','').replace('_e','')='1_1_3'

    //adjTb(_$tbL, (_dicGtT[_dbId][0] == 'S1' || _dicGtT[_dbId][0] == 'S2'));
    adjTb(_$tbL, (_dbId.replace('_r','').replace('_t','').replace('_e','')=='1_1_1'||_dbId.replace('_r','').replace('_t','').replace('_e','')=='1_1_2'||_dbId.replace('_r','').replace('_t','').replace('_e','')=='1_1_3'));
    if (RES.islogin == "True") {
        $(".leagueName").removeAttr("onclick");
        $(".refreshlogo").removeAttr("onclick");
    }

};

function KenGame(initScripts) {
    var sb = new StringBuilder()
    sb.append('<table cellspacing="0" cellpadding="0" width="819" height="100%" align="left" border="0" class="bodyBorder">');
    sb.append(' <tr valign="top"> <td>');
    sb.append('<table width="775" align="center" border="0" cellpadding="0" cellspacing="0">');
    sb.append('<tr><td width="100%" style="display:none;"> ' + initScripts + '</td> </tr>');
    sb.append('<tr> <td height="10px"></td> </tr>');
    sb.append('<tr valign="top"> <td align="left">');
    sb.append('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,0,0" width="750" height="500" align="left" id="keno" VIEWASTEXT>');
    sb.append('<param name="allowScriptAccess" value="always" />');
    sb.append('<param name="FlashVars" value="ActionURL=_view/keno.xml" />');
    sb.append('<param name="movie" value="_view/keno.swf" />');
    sb.append('<param name="quality" value="high" />');
    sb.append('<param name="wmode" value="opaque" />');
    sb.append('<embed FlashVars="ActionURL=wfKenotest" src="_view/keno.swf" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"');
    sb.append('type="application/x-shockwave-flash" width="750" height="500" align="left"> </embed>');
    sb.append(' </object></td> </tr> </table> </td> </tr> </table>');
    $("#oddsTable").html(sb.toString());
};






function setDtM(_dbId, _ot, _rM) {
    if (_dataM[_dbId] == null) _dataM[_dbId] = {};
    if (_dataM[_dbId][_ot] == null) {
        _dataM[_dbId][_ot] = {};
    }
    _dataM[_dbId][_ot][_rM[0]] = _rM;
};
function delDtM(_dbId, _ot, _oddsid) {
    if (_dataM[_dbId] == null) return;
    if (_dataM[_dbId][_ot] != null) {
        _dataM[_dbId][_ot][_oddsid] = null;
        delete _dataM[_dbId][_ot][_oddsid];
    }
};
function updDtM(_dbId, _ruM, _$tb, _$rowMod, _ot) {
    var _oddsid = _ruM[0];
    //if (_dataM[_dbId][_ot][_oddsid] != null) {
    var _$trM = _$tb.find("tbody[soclid] > tr[oddsid='" + _oddsid + "']");
    if (_$trM.length > 0) {
        var _$tbL = _$trM.parents("tbody[soclid]:first");
        var _LId = _$tbL.attr('soclid');
        var _LTitle = _$tbL.find('.leagueName:first').html();
        //var _isAlt = _$tb.hasClass("GridAltRunItem") || _$tb.hasClass("GridAltItem");
        for (var i = 0, len = _ruM[1].length; i < len; i++) {
            _dataM[_dbId][_ot][_oddsid][_ruM[1][i]] = _ruM[2][i];
        }

        var _dr = ShowOddsFn[_dicGtT[_dbId][0]](_dataM[_dbId][_ot][_oddsid], _LId, _LTitle, _$tbL, false, _dicPMS[_dbId]);
        setRowTr(_$rowMod, _$tbL, _LId, _dr, _dicPMS[_dbId], false,   _dbId, false);
        //addM_S(_dataM[_dbId][_ot][_oddsid], _isAlt, (_ot == 'r'), _LId, _LTitle, _$tbL, false, _ot);
        return true;
    }
    // }
    return false;
};

function delM_S(_dbId, _oddsid, _$tb, _ot) {
    var _$trM = _$tb.find("tr.gtRow[oddsid='" + _oddsid + "']:first");
    if (_$trM.length > 0) {
        //_$trM.next().remove();
        //_$trM.remove();
        if ((_dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_1' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_2' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3')) {
            var _$nextTr = _$trM.nextAll('tr.gtRow[oddsid!="' + _oddsid + '"]:first');
            if (_$trM.attr('mkey') == _$nextTr.attr('mkey') && _$trM.find('td.tdTeam:first').html() != '' && _$nextTr.find('td.tdTeam:first').html() == '') {
                //_$trM.find('td.tdLast:first').attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
                //_ftd.attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
                //_$trM.next().prepend(_ftd).append(_$trM.find('td.tdLast:first')).find('td.tdTeam').html(_$trM.find('td.tdTeam:first').html());
                _$nextTr.find('td.tdLive:first').html(_$trM.find('td.tdLive:first').html());
                _$nextTr.find('td.tdshoucang:first').html(_$trM.find('td.tdshoucang:first').html());
                _$nextTr.find('td.tdTeam:first').html(_$trM.find('td.tdTeam:first').html());
                _$nextTr.find('td.tdLast:first').html(_$trM.find('td.tdLast:first').html());
                //_$trM.find('td.shoucang:first').attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
            }
            //var _ftd = _$trM.find('td.tdLive:first');
            //if (_ftd.length > 0) {
            //    if (_$trM.attr('mkey') == _$trM.next().attr('mkey')) {
            //        //_$trM.find('td.tdLast:first').attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
            //        //_ftd.attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
            //        //_$trM.next().prepend(_ftd).append(_$trM.find('td.tdLast:first')).find('td.tdTeam').html(_$trM.find('td.tdTeam:first').html());
            //        _$trM.next().find('td.tdTeam').html(_$trM.find('td.tdTeam:first').html());
            //        //_$trM.find('td.shoucang:first').attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
            //    }
            //} else {
            //    //_ftd = _$trM.prevAll('[mkey="' + _$trM.attr('mkey') + '"]').find('td.tdLive:first');
            //    //_ftd.nextAll('.tdLast:first').attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
            //    //_ftd.attr('rowspan', parseInt(_ftd.attr('rowspan')) - 1);
            //}
        }
        var _$tbL = _$trM.parents("tbody:first");
        //if (_$trM.siblings('tr.gtRow[mkey="' + _$trM.attr('mkey') + '"]').length == 0) {
        //    _$trM.nextAll('.moreBetTr[mkey="' + _$trM.attr('mkey') + '"]:first').remove();
        //}
        var deletmkey = _$tbL.find('tr[oddsid="' + _oddsid + '"]').attr('mkey');
        _$tbL.find('tr[oddsid="' + _oddsid + '"]').remove();
        var _$cMoreBet = $('#cMoreBet');
        if (_$cMoreBet.attr('oddsid') == _oddsid) {
            if (_$tbL.find('tr[mkey="' + deletmkey + '"]').length == 0) {
                _$cMoreBet.attr('oddsid', '').attr('mkey', '').attr('LId', ''); _$cMoreBet.empty();
                mBetOddsId = "";
            }
            else {
                var newoddids = _$tbL.find('tr[mkey="' + deletmkey + '"]:first').attr('oddsid');
                _$cMoreBet.attr('oddsid', newoddids);
                mBetOddsId = newoddids;
            }
        }
        //if (_$tbL.find('tr.gtRow[oddsid]').length == 0) {
        //    if (_$tbL.prev().attr('soclid') > 0 && _$tbL.prev().attr('soclid') == _$tbL.next().attr('soclid')) {
        //        _$tbL.prev().append(_$tbL.next().find('tr.league:first').nextAll());
        //        _$tbL.next().remove();
        //    }
        //    _$tbL.remove();
        //} else {
            mergeTbL(_$tbL);
        //}
    }
    delDtM(_dbId, _ot, _oddsid);
}

//function adjTrp(_$tb, _$trMs, _$trM, _sd) {
//    if (_$trM.length <= 0) return null;
//    var _$next;
//    if (_$trMs == null)
//        _$trMs = _$tb.find('tr[preoddsid="' + _$trM.attr('oddsid') + '"]');
//    else
//        _$next = _$trMs.filter('[preoddsid="' + _$trM.attr('oddsid') + '"]');
//    if (_$next.length <= 0) return null;
//    var _$LgTm = _$trM.parents('tbody[soclid]:first');
//    var _$nextLgTm = _$next.parents('tbody[soclid]:first');
//    if (_$LgTm.attr('soclid') == _$nextLgTm.attr('soclid')) {
//        if (_sd == 1) {
//            _$trM.after(_$next);
//        } else {
//            var _Ntm = _$next.next();
//            _$trM.next().after(_$next);
//            _$next.after(_Ntm);
//        }
//    } else {
//        var _$newNextLgTm;
//        if (_$next.siblings('[oddsid]').length > 0) {
//            _$newNextLgTm = _$nextLgTm.clone();
//            _$newNextLgTm.find('tr:gt(0)').remove();
//            if (_sd == 1) {
//                _$newNextLgTm.append(_$next);
//            } else {
//                var _ntm = _$next.next();
//                _$newNextLgTm.append(_$next);
//                _$newNextLgTm.append(_ntm);
//            }
//        } else {
//            _$newNextLgTm = _$nextLgTm;
//        }

//        if (_$trM.nextAll('[oddsid]').length > 0) {
//            var _$newLgTm = _$LgTm.clone();
//            _$newLgTm.find('tr:gt(0)').remove();
//            if (_sd == 1) {
//                _$newLgTm.append(_$trM.nextAll());
//            } else {
//                _$newLgTm.append(_$trM.next().nextAll());
//            }
//            _$LgTm.after(_$newLgTm);
//        }
//        _$LgTm.after(_$newNextLgTm);
//    }
//    return _$next;
//}
//function adjTrPos(_$tb, _sd) {
//    var _$trMs = _$tb.find("tr[oddsid]");
//    var _$Tm0 = _$trMs.filter('[preoddsid="0"]');
//    for (var i = 0, len = _$trMs.length; i < len; i++) {
//        _$Tm0 = adjTrp(_$tb, _$trMs, _$Tm0, _sd);
//        if (_$Tm0 == null) {
//            break;
//        }
//    }
//    _$tb.find("tbody[soclid] tr:only-child").parent().remove();
//}

function adjTb(_$tbL, _IsMerge) {
    var _isAlt = true, _ek = '', _preTr = null; var arr$Tr = [];
    if (_$tbL.prev().length > 0 && _$tbL.prev().find('tr.gtRow[oddsid]:last').hasClass('altMatch')) {
        _isAlt = false;
    }
    var _ctr = _$tbL.find("tr.gtRow[oddsid]");
    var snum = 0;
    for (var i = 0, len = _ctr.length; i < len; i++) {
        if (!_IsMerge) {
            if (_isAlt) _ctr.eq(i).addClass('altMatch');
            else _ctr.eq(i).removeClass('altMatch');
            _isAlt = !_isAlt;
            if (_ctr.eq(i).attr('mkey') != _ctr.eq(i + 1).attr('mkey')) continue;
            else {
                var mkeyshowref = _ctr.eq(i).attr('mkey');
                if (_$tbL.find("tr.gtRow[mkey=" + mkeyshowref + "]:first").find(".tdshoucang").html() == "") {
                    var _trhtml = _$tbL.find("tr.gtRow[mkey=" + mkeyshowref + "]")
                    var _trhtmlFirst = _$tbL.find("tr.gtRow[mkey=" + mkeyshowref + "]:first")
                    _trhtml.find(".tdshoucang").html()
                    for (j = 1; j < _trhtml.find(".tdshoucang").length; j++) {
                        if (_trhtml.eq(j).find(".tdshoucang").html() != "") {
                            var tdshoucangshow = _trhtml.eq(j).find(".tdshoucang").html()
                            var tdLiveshow = _trhtml.eq(j).find(".tdLive").html();
                            var tdTeamshow = _trhtml.eq(j).find(".tdTeam").html();
                            var tdLastshow = _trhtml.eq(j).find(".tdLast").html();
                        }
                    }
                    _trhtml.find(".tdshoucang").empty();
                    _trhtml.find(".tdLive").empty();
                    _trhtml.find(".tdTeam").empty();
                    _trhtml.find(".tdLast").empty();
                    _trhtmlFirst.find(".tdshoucang").html(tdshoucangshow);
                    _trhtmlFirst.find(".tdLive").html(tdLiveshow);
                    _trhtmlFirst.find(".tdTeam").html(tdTeamshow);
                    _trhtmlFirst.find(".tdLast").html(tdLastshow);
                }
                else continue;
            }
        }
        if (len - 1 == i || i < len - 1 && _ctr.eq(i).attr('mkey') != _ctr.eq(i + 1).attr('mkey')) {
            for (var k = i; k >= snum; k--) {
                if (_isAlt) _ctr.eq(k).addClass('altMatch');
                else _ctr.eq(k).removeClass('altMatch');
                if (k == snum) {
                    //  _ctr.eq(k).find('td.tdLive:first,td.tdLast:first').attr('rowspan', (i - snum + 1));
                } else {
                    //  _ctr.eq(k).find('td.tdLive:first,td.tdLast:first').remove();
                    _ctr.eq(k).find('td.tdTeam:first').empty();
                    _ctr.eq(k).find('td.tdshoucang:first').empty();
                    _ctr.eq(k).find('td.tdLive:first,td.tdLast:first,td.tdshoucang:first').empty();
                }
            }
            snum = i + 1; _isAlt = !_isAlt;
        }
    }




    //_$tbL.find("tr[oddsid]").each(function () {
    //    //for (var i = 0, len = _$trMs.length; i < len; i++) {
    //    var _$trM = $(this);

    //    if (_IsMerge) {
    //        if (_ek != _$trM.attr('favid') + '|' + _$trM.attr('isGive')) {
    //            _isAlt = !_isAlt;
    //            _ek = _$trM.attr('favid') + '|' + _$trM.attr('isGive');
    //        } else {
    //            $(_$trM.children()[0]).empty();
    //            $(_$trM.children()[1]).empty()
    //            $(_$trM.children()[2]).empty();
    //            _$trM.find('A[title="More bets"]').parents('td:first').empty();
    //        }
    //    } else {
    //        _isAlt = !_isAlt;
    //    }
    //    if (_isAlt) {
    //        if (_$trM.hasClass('GridRunItem')) {
    //            _$trM.removeClass('GridRunItem').addClass('GridAltRunItem');
    //            if (_$trM2) {
    //                _$trM2.removeClass('GridRunItem').addClass('GridAltRunItem');
    //            }
    //        }
    //        if (_$trM.hasClass('GridItem')) {
    //            _$trM.removeClass('GridItem').addClass('GridAltItem');
    //            if (_$trM2) {
    //                _$trM2.removeClass('GridItem').addClass('GridAltItem');
    //            }
    //        }
    //    } else {
    //        if (_$trM.hasClass('GridAltRunItem')) {
    //            _$trM.removeClass('GridAltRunItem').addClass('GridRunItem');
    //            if (_$trM2) {
    //                _$trM2.removeClass('GridAltRunItem').addClass('GridRunItem');
    //            }
    //        }
    //        if (_$trM.hasClass('GridAltItem')) {
    //            _$trM.removeClass('GridAltItem').addClass('GridItem');
    //            if (_$trM2) {
    //                _$trM2.removeClass('GridAltItem').addClass('GridItem');
    //            }
    //        }

    //    }
    //    //}

    //    _preTr = _$trM;
    //});

};



var _tmChgN;
$(function () {
    var _$tbR = $('#oddsTable');
    _$tbR.on('mouseover', '.gtRow', function () {
        var _$this = $(this); var gttype = _$this.parents('table:first').attr('gttype');
        if ((gttype == 'S1' || gttype == 'S2')) {
            _$this.addClass('hoverMatch').siblings('[oddsid="' + _$this.attr('oddsid') + '"]').addClass('hoverMatch');
        } else {
            _$this.addClass('hoverMatch');
        }
    });
    _$tbR.on('mouseout', '.gtRow', function () {
        var _$this = $(this); var gttype = _$this.parents('table:first').attr('gttype');
        if ((gttype == 'S1' || gttype == 'S2')) {
            _$this.removeClass('hoverMatch').siblings('[oddsid="' + _$this.attr('oddsid') + '"]').removeClass('hoverMatch');
        } else {
            _$this.removeClass('hoverMatch');
        }
    });
    _$tbR.on('click', '.leagueName,.mbTbHd1 .gameName', function () {
        var _$this = $(this);
        _$this.parents('tr:first').find('.icon-open,.icon-close').trigger('click');
    });
    //_$tbR.on('click', '.oddsBet', function () {
    //    var _$this = $(this);
    //});
    $(window).resize(function () {
        //var _$tbcol = _$tbR.find('colgroup');
        //for (var i = 0, leni = _$tbcol.length; i < leni; i++) {
        //    var _$autocol; var _twidth = _$tbcol.eq(i).parent().width();
        //    var _$col = _$tbcol.eq(i).find('col');
        //    for (var k = 0, lenk = _$col.length; k < lenk; k++) {
        //        if (_$col.eq(k).width() == 0)
        //            _$col.eq(k).attr('autoWidth', '1');
        //        if (_$col.eq(k).attr('autoWidth') == '1') {
        //            _$autocol = _$col.eq(k);
        //        } else {
        //            _twidth = _twidth - _$col.eq(k).width();
        //        }
        //    }
        //    _$autocol.width(_twidth);
        //}
    });
    _$tbR.on('click', '.mbTbHd1 .icon-open,.mbTbHd1 .icon-close', function () {
        var _$tdicon;
        var _$this = $(this);
        if (_$this.hasClass('icon-open')) {
            _$this.addClass('icon-close').removeClass('icon-open');
            _$this.parents('tr:first').nextAll().hide();
        }
        else {
            _$this.addClass('icon-open').removeClass('icon-close');
            _$this.parents('tr:first').nextAll().show();
        }
    });
    _$tbR.on('click', '.league .icon-open,.league .icon-close,.gtHdtr .icon-open,.gtHdtr .icon-close', function () {
        var _$this = $(this); var _$tr; var _$tdicon;
        if (_$this.parent().hasClass('gtHdTdF')) {
            _$tr = _$this.parents('table:first').find('.gtRow');
            _$tdicon = _$this.parents('table:first').find('.icon-open,.icon-close');
        } else {
            _$tr = _$this.parents('tbody:first').find('.gtRow');
            _$tdicon = _$this;
        }
        if (_$this.hasClass('icon-open')) {
            _$tdicon.addClass('icon-close').removeClass('icon-open');
            _$tr.hide();
        }
        else {
            _$tdicon.addClass('icon-open').removeClass('icon-close');
            _$tr.show();
        }
    });
    _$tbR.on('click', '.tdLast .icon-moreExpand,.tdLast .icon-moreCollapse', function () {
        showMoreBet($(this));
    });

    _tmChgN = setInterval(function () {
        if (_$tbR.length > 0) {
            _$tbR.find(".indicatorDown,.indicatorUp").each(function () {
                _$this = $(this);
                var _tms = _$this.attr("chgTms") || 0;
                _tms++;
                if (_tms > 5) { _$this.removeClass('indicatorDown').removeClass('indicatorUp'); _tms = 0; }
                _$this.attr("chgTms", _tms);
            });

            var _$celltm = _$tbR.find(".liveChg");
            if (_$celltm.length > 0) {
                onPlaySound(ScoreSound);
                _$celltm.removeClass('liveChg');
            }
        }
        pingWebsocket();
        // var ws = _dicTimer[_dbId];
    }, 6000);

    $('#betTxtAmount').on('keyup', function (event) {
        CountMaxPayout2();
        if (event.keyCode == 13 && isbet) {
            submitBet();
            isbet = false;
        }
    });
    _Bettimer = setInterval(function () {
        readStake();
    }, 5000);

});

function boundMoreBet(_sDb, ossdid) {
    var _tr = $('#oddsTable').find('tr[oddsid="' + ossdid + '"]:first');

    var _data = eval("(" + _sDb + ")");
    _tr.find('.GamesSum').html(_data._count);
    var _$cMoreBet = $('#cMoreBet');
    //var _$tr = $('#oddsTable').find('.tdLast .icon-moreCollapse').parents('tr:first');
    //var _$trm = _$tr.nextAll('tr.moreBetTr:first');
    var _$mbet = _$cMoreBet.find('.moreBetDiv:first');
    if (_$mbet.length <= 0) {
        _$mbet = $('#moreBet').clone(); _$mbet.attr('id', '').addClass('moreBetDiv');
        _$cMoreBet.empty().append(_$mbet);
    }

    var mekymorbet = $("#oddsTable").find("tr[oddsid="+ _data["SocOddsId"] + "]:first").attr('mkey');
    var sooddsmorbet = $("#oddsTable").find("tr[mkey=" + mekymorbet + "]:last").attr('oddsid')
    //_$trm.attr("mkey", mekymorbet);
    //_$trm.attr('mbkey', sooddsmorbet);
    //_$trm.attr('oddsid', sooddsmorbet);
    for (var _col in _data) {
        var _cell = _$mbet.find('.' + _col);
        _cell.html(_data[_col]);
    }

    var _datafthdp = [], _datafhhdp = [];
    if (_data.FTodds != undefined)
        _datafthdp = eval("[" + _data.FTodds + "]");
    if (_data.FHodds != undefined)
        _datafhhdp = eval("[" + _data.FHodds + "]");
    var morebetlength = _datafthdp.length;
    if (_$mbet.find("#Socid" + (morebetlength)).nextAll().length > 0) {
        _$mbet.find("#Socid" + (morebetlength)).nextAll().remove();
    }
    _$mbet.find(".FHOdds").empty();
    _$mbet.find(".FAOdds").empty();
    _$mbet.find(".FOOdds").empty();
    _$mbet.find(".FUOdds").empty();
    _$mbet.find(".FHdp1").empty();
    _$mbet.find(".FOU1").empty();

    //FT.HDP&FT.OU
    for (var i = 0; i < _datafthdp.length; i++) {
        var _$tdSoc;
        if (_$mbet.find("#Socid").length > 0) {
            _$tdSoc = _$mbet.find("#Socid").clone();
            _$tdSoc.attr("id", "Socid" + (i + 1));
        } else {
            _$tdSoc = _$mbet.find("#Socid" + (i + 1));
            if (_$tdSoc.length == 0){
                _$tdSoc = $("#moreBet").find("#Socid").clone();
                _$tdSoc.attr("id", "Socid" + (i + 1));
            }
        }

        for (var _col in _datafthdp[i]) {
            _$tdSoc.attr("socid", _datafthdp[i]["SocOddsId"]);
            if (_col == "HDP"){
                _$tdSoc.find(".FHdp1").html(UtilGetDisplayHdp(parseFloat(_datafthdp[i]["HDP"]), _datafthdp[i]["IsHomeGive"], "HDP"));
            }
            if (_col == "HomeOdds" || _col == "AwayOdds") {
                if (_datafthdp[i]["HomeOdds"] != 0 && _datafthdp[i]["AwayOdds"] != 0) {
                    if (_datafthdp[i]["Hdp_visible"]) {
                        _$tdSoc.find(".FHOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&par_b=" + (_datafthdp[i]["HasPar"] =="True"?"home_par":"")+"&b=home&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["HomeOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["HomeOdds"]) + "</span>");
                        _$tdSoc.find(".FAOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "away_par" : "") +"&b=away&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["AwayOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["AwayOdds"]) + "</span>");
                    } else {
                        _$tdSoc.find(".FHOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafthdp[i]["HomeOdds"]) + "</span>");
                        _$tdSoc.find(".FAOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafthdp[i]["AwayOdds"]) + "</span>");
                    }
                }
            }

            if (_col == "OU"){
                _$tdSoc.find(".FOU1").html(UtilGetDisplayHdp(parseFloat(_datafthdp[i]["OU"]), _datafthdp[i]["IsHomeGive"], "OU"));
            }
            if (_col == "OverOdds" || _col == "UnderOdds") {
                if (_datafthdp[i]["OverOdds"] != 0 && _datafthdp[i]["UnderOdds"] != 0) {
                    if (_datafthdp[i]["OU_visible"]) {
                        _$tdSoc.find(".FOOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "over_par" : "") +"&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["HomeOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["OverOdds"]) + "</span>");
                        _$tdSoc.find(".FUOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&par_b=" + (_datafthdp[i]["HasPar"] == "True"? "under_par" : "") +"&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["AwayOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["UnderOdds"]) + "</span>");
                    } else {
                        _$tdSoc.find(".FOOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafthdp[i]["OverOdds"]) + "</span>");
                        _$tdSoc.find(".FUOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafthdp[i]["UnderOdds"]) + "</span>");
                    }
                }
            }
        }

        if (_$mbet.find("#Socid").length > 0)
            _$mbet.find("#Socid").before(_$tdSoc);
        else
            _$mbet.find("#Socid" + (i)).after(_$tdSoc);
    }

    var fthdp = '', ftou = '';
    //for (var i = 0; i < _datafhhdp.length; i++) {
    //    var _$tdSoc;
    //    if (_$mbet.find("#Socid" + (i + 1)).length > 0) {
    //        _$tdSoc = _$mbet.find("#Socid" + (i + 1));
    //    }
    //    var Socid = _$tdSoc.attr("socid");
    //    for (var _col in _datafhhdp[i]) {
    //        if (_col == "HDP") {
    //            _$tdSoc.find(".HHdp1").html(UtilGetDisplayHdp(parseFloat(_datafhhdp[i]["HDP"]), _datafhhdp[i]["IsHomeGive"], "HDP"));
    //        }
    //        if (_col == "HomeOdds" || _col == "AwayOdds") {
    //            if (_datafhhdp[i]["HomeOdds"] != 0 && _datafhhdp[i]["AwayOdds"] != 0) {
    //                if (_datafhhdp[i]["Hdp_visible"]) {
    //                    _$tdSoc.find(".HHOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=home&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "home_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["HomeOdds"]) + "</span>");
    //                    _$tdSoc.find(".HAOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=away&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "away_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["AwayOdds"]) + "</span>");
    //                } else {
    //                    _$tdSoc.find(".HHOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["HomeOdds"]) + "</span>");
    //                    _$tdSoc.find(".HAOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["AwayOdds"]) + "</span>");
    //                }
    //            }
    //        }

    //        if (_col == "OU") {
    //            _$tdSoc.find(".HOU1").html(UtilGetDisplayHdp(parseFloat(_datafhhdp[i]["OU"]), _datafhhdp[i]["IsHomeGive"], "OU"));
    //        }
    //        if (_col == "OverOdds" || _col == "UnderOdds") {
    //            if (_datafhhdp[i]["OverOdds"] != 0 && _datafhhdp[i]["UnderOdds"] != 0) {
    //                if (_datafhhdp[i]["OU_visible"]) {
    //                    _$tdSoc.find(".HOOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "under_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["OverOdds"]) + "</span>");
    //                    _$tdSoc.find(".HUOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "over_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["UnderOdds"]) + "</span>");
    //                } else {
    //                    _$tdSoc.find(".HOOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["OverOdds"]) + "</span>");
    //                    _$tdSoc.find(".HUOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["UnderOdds"]) + "</span>");
    //                }
    //            }
    //        }
    //    }
    //}
    //_$mbet.find(".FHdp1").each(function () {
    //    if ($(this).html() == '') {
    //        fthdp = fthdp + 'true';
    //    }
    //});
    //_$mbet.find(".FOU1").each(function () {
    //    if ($(this).html() == '') {
    //        ftou = ftou + 'true';
    //    }
    //});
    //if (fthdp.indexOf('true') > -1 && _datafthdp.length>0) {
    //    _data._count = _data._count - 1;
    //};
    //if (ftou.indexOf('true') > -1 && _datafthdp.length > 0) {
    //    _data._count = _data._count - 1;
    //}

    if (_$mbet.find("#Socid").length > 0)
        _$mbet.find("#Socid").remove();
    _$mbet.find(".HHOdds").each(function () {

    });
    _$mbet.find(".HHOdds").each(function () {

    });
    //FH.HDP&FH.OU
    _$mbet.find(".HHOdds").empty();
    _$mbet.find(".HAOdds").empty();
    _$mbet.find(".HOOdds").empty();
    _$mbet.find(".HUOdds").empty();
    _$mbet.find(".HHdp1").empty();
    _$mbet.find(".HOU1").empty();
    var fhhdp = '', fhou = '';
    for (var i = 0; i < _datafhhdp.length; i++) {
        var _$tdSoc;
        if (_$mbet.find("#Socid" + (i + 1)).length > 0) {
            _$tdSoc = _$mbet.find("#Socid" + (i + 1));
        }
        var Socid = _$tdSoc.attr("socid");
        for (var _col in _datafhhdp[i]) {
            if (_col == "HDP") {
                _$tdSoc.find(".HHdp1").html(UtilGetDisplayHdp(parseFloat(_datafhhdp[i]["HDP"]), _datafhhdp[i]["IsHomeGive"], "HDP"));
            }
            if (_col == "HomeOdds" || _col == "AwayOdds") {
                if (_datafhhdp[i]["HomeOdds"] != 0 && _datafhhdp[i]["AwayOdds"] != 0) {
                    if (_datafhhdp[i]["Hdp_visible"]) {
                        _$tdSoc.find(".HHOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=home&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "home_par" : "") +"&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["HomeOdds"]) + "</span>");
                        _$tdSoc.find(".HAOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=away&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "away_par" : "")+"&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["AwayOdds"]) + "</span>");
                    } else {
                        _$tdSoc.find(".HHOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["HomeOdds"]) + "</span>");
                        _$tdSoc.find(".HAOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["AwayOdds"]) + "</span>");
                    }
                }
            }

            if (_col == "OU") {
                _$tdSoc.find(".HOU1").html(UtilGetDisplayHdp(parseFloat(_datafhhdp[i]["OU"]), _datafhhdp[i]["IsHomeGive"], "OU"));
            }
            if (_col == "OverOdds" || _col == "UnderOdds") {
                if (_datafhhdp[i]["OverOdds"] != 0 && _datafhhdp[i]["UnderOdds"] != 0) {
                    if (_datafhhdp[i]["OU_visible"]) {
                        _$tdSoc.find(".HOOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&par_b=" +( _datafthdp[i]["HasPar"] == "True" ? "under_par" : "" )+"&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["OverOdds"]) + "</span>");
                        _$tdSoc.find(".HUOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&par_b=" +( _datafthdp[i]["HasPar"] == "True" ?"over_par" : "" )+"&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["UnderOdds"]) + "</span>");
                    } else {
                        _$tdSoc.find(".HOOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["OverOdds"]) + "</span>");
                        _$tdSoc.find(".HUOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["UnderOdds"]) + "</span>");
                    }
                }
            }
        }
    }
    //_$mbet.find(".HHdp1").each(function() {
    //    if ($(this).html() == '') {
    //        fhhdp = fhhdp + 'true';
    //    }
    //});
    //_$mbet.find(".HOU1").each(function () {
    //    if ($(this).html() == '') {
    //        fhou = fhou + 'true';
    //    }
    //});
    //if (fhhdp.indexOf('true') > -1 && _datafhhdp.length) {
    //    _data._count = _data._count - 1;
    //};
    //if (fhou.indexOf('true') > -1 && _datafhhdp.length) {
    //    _data._count = _data._count - 1;
    //}
    _tr.find('.GamesSum').html(_data._count);
    if (_data["FTHDP_CNT"] == "0" && _data["FHHDP_CNT"] == "0") {
        _$mbet.find("#HDPOU").hide();
    } else {
        _$mbet.find("#HDPOU").show();
    }

    if (_data["F1X2_CNT"] == "0") {
        _$mbet.find("#FT1X2").hide();
    } else {
        _$mbet.find("#FT1X2").show();
    }

    if (_data["H1X2_CNT"] == "0") {
        _$mbet.find("#FH1X2").hide();
    } else {
        _$mbet.find("#FH1X2").hide();
    }

    if (_data["F1X2_CNT"] == "0") {
        _$mbet.find("#FT1X2").hide();
    } else {
        _$mbet.find("#FT1X2").show();
    }

    if (_data["H1X2_CNT"] == "0") {
        _$mbet.find("#FH1X2").hide();
    } else {
        _$mbet.find("#FH1X2").show();
    }

    if (_data["FDB_CNT"] == "0") {
        _$mbet.find("#FTDC").hide();
    } else {
        _$mbet.find("#FTDC").show();
    }

    if (_data["HDB_CNT"] == "0") {
        _$mbet.find("#FHDC").hide();
    } else {
        _$mbet.find("#FHDC").show();
    }

    if (_data["FOE_CNT"] == "0") {
        _$mbet.find("#FTOE").hide();
    } else {
        _$mbet.find("#FTOE").show();
    }

    if (_data["HOE_CNT"] == "0") {
        _$mbet.find("#FHOE").hide();
    } else {
        _$mbet.find("#FHOE").show();
    }

    if (_data["FCS_CNT"] == "0") {
        _$mbet.find("#FHCS").hide();
    } else {
        _$mbet.find("#FHCS").show();
    }

    if (_data["HCS_CNT"] == "0") {
        _$mbet.find("#HHCS").hide();
    } else {
        _$mbet.find("#HHCS").show();
    }

    if (_data["HTFT_CNT"] == "0" && _data["ISRUN"] == "1") {
        _$mbet.find("#HTFT").hide();
    } else {
        _$mbet.find("#HTFT").show();
    }

    if (_data["FGLG_CNT"] == "0") {
        _$mbet.find("#FGLG").hide();
    } else {
        _$mbet.find("#FGLG").show();
    }

    if (_data["TG_CNT"] == "0") {
        _$mbet.find("#TG").hide();
    } else {
        _$mbet.find("#TG").show();
    }

    if (_data["HTTG_CNT"] == "0") {
        _$mbet.find("#HTTG").hide();
    } else {
        _$mbet.find("#HTTG").show();
    }

    if (_data["ATTG_CNT"] == "0") {
        _$mbet.find("#ATTG").hide();
    } else {
        _$mbet.find("#ATTG").show();
    }

    if (_data["MG15_CNT"] == "0") {
        _$mbet.find("#FT150015").hide();
    } else {
        _$mbet.find("#FT150015").show();
    }

    if (_data["MG30_CNT"] == "0") {
        _$mbet.find("#FT151530").hide();
    } else {
        _$mbet.find("#FT151530").show();
    }

    if (_data["MG45_CNT"] == "0") {
        _$mbet.find("#FT153045").hide();
    } else {
        _$mbet.find("#FT153045").show();
    }

    if (_data["MG60_CNT"] == "0") {
        _$mbet.find("#FT154560").hide();
    } else {
        _$mbet.find("#FT154560").show();
    }

    if (_data["MG75_CNT"] == "0") {
        _$mbet.find("#FT156075").hide();
    } else {
        _$mbet.find("#FT156075").show();
    }

    if (_data["MG15_CNT"] == "0" && _data["MG30_CNT"] == "0" && _data["MG45_CNT"] == "0" && _data["MG60_CNT"] == "0" && _data["MG75_CNT"] == "0") {
        _$mbet.find("#FT15MINS").hide();
    } else {
        _$mbet.find("#FT15MINS").show();
    }
}
var _timerMoreBet;
function ajaxMoreBet(_iTm) {
    _iTm = _iTm || 5;
    var _$cMoreBet = $('#cMoreBet');
    _timerMoreBet && clearTimeout(_timerMoreBet); _timerMoreBet = null;
    //if (_$tr == null) {
    //    _iTm = 10;
    //    _$tr = $('#oddsTable').find('.tdLast .icon-moreCollapse').parents('tr:first');
    //}
    if (_$cMoreBet.length > 0) {
        var _Oid = _$cMoreBet.attr('oddsid');
        if (_Oid) {
            jQuery.ajax({
                async: true, cache: false, url: wroot + 'pgajaxS.axd?T=MB3&oId=' + _Oid + '&r=' + Date.now(), complete: function (_ort) {
                    var _$trMoreBet = $('#oddsTable').find('tr.moreBetTr[mkey="' + _$cMoreBet.attr('mkey') + '"]:first');
                    _ort.statusText != "error" && boundMoreBet(_ort.responseText, _Oid);
                    _$trMoreBet.find('>td').html(_$cMoreBet.html());

                    _timerMoreBet = setTimeout(function () {
                        ajaxMoreBet();
                    }, 1000 * _iTm);
                }
            });
        } else {
            var _$trMoreBet = $('#oddsTable').find('tr.moreBetTr[mkey="' + _$cMoreBet.attr('mkey') + '"]:first');
            _$trMoreBet.remove();
        }
    }
}
function closeMB_R() {
    mBetOddsId = '';
    mBetOddsLId = '';
    ShowMB_R($('#oddsTable').find('tr.moreBetTr').find('.tdLast .icon-moreCollapse,.tdLast .icon-moreExpand'),'','','');
    ////$(".moreBetTr").remove();
    //$('#oddsTable').find('tr.moreBetTr').find('.tdLast .icon-moreCollapse,.tdLast .icon-moreExpand');
    //$('#oddsTable').find('.tdLast .icon-moreCollapse').removeClass('icon-moreCollapse').addClass('icon-moreExpand');
}
function ShowMB_R(_$this) {
    showMoreBet(_$this);
    //var _$tr = $('#oddsTable').find('.tdLast .icon-moreCollapse').parents('tr:first');
    //if (_$this != "0") {
    //    $('#oddsTable').find('tr.moreBetTr').remove();
    //    var _colspan = 0;
    //    _$tr.find('>td').each(function () {
    //        _colspan += Number($(this).attr('colspan') || '1');
    //    });
    //    var _$trm = $('<tr class="moreBetTr"><td colspan="' + _colspan + '"></td></tr>');
    //    _$tr.prev().nextAll('[mkey="' + _$tr.attr('mkey') + '"]:last').after(_$trm);
    //    //$('#oddsTable').find('.tdLast .icon-moreCollapse').removeClass('icon-moreExpand');
    //}
    //ajaxMoreBet(_$tr);
}
var mBetOddsId = '',mBetOddsLId='';
function showMoreBet(_$this, _oId, _mkey, _LId) {
    var _$tr = _$this.parents('tr[oddsid]:first'); //var _needExp = _$this.hasClass('icon-moreExpand');
    var _$tb = _$tr.parents('table.gtTb:first');
    var _$cMoreBet = $('#cMoreBet');
    if (_$cMoreBet.length == 0) {
        _$cMoreBet = $('<div id="cMoreBet" style="display:none;" oddsid="" mkey="" LId=""></div>');
        $('#oddsTable').append(_$cMoreBet);
    }
    //$('#divOMD').append($('#moreBet'));
    var _needCopy = false;
    if (_$cMoreBet.attr('mkey') != _$tr.attr('mkey')) {
        $('#oddsTable').find('tr.moreBetTr').remove();
        _$cMoreBet.attr('oddsid', '');
    } else {
        _needCopy = true;
    }
    if (_oId || _oId==='') {
        _$cMoreBet.attr('oddsid', _oId).attr('mkey', _mkey).attr('LId', _LId);
    }
    else if (_$cMoreBet.attr('oddsid') == '') {
        _$cMoreBet.attr('oddsid', _$tr.attr('oddsid')).attr('mkey', _$tr.attr('mkey')).attr('LId', _$tr.parents('[soclid]:first').attr('soclid'));
    } else {
        _$cMoreBet.attr('oddsid', '').attr('mkey', '').attr('LId', ''); _$cMoreBet.empty();
    }
    mBetOddsId = _$cMoreBet.attr('oddsid');
    mBetOddsLId = _$cMoreBet.attr('LId');
    var _$trMoreBet = _$tr.siblings('.moreBetTr[mkey="' + _$tr.attr('mkey') + '"]');
    if (_$cMoreBet.attr('oddsid') != '') {
        if (_$trMoreBet.length == 0) {
            var _colspan = 0;
            _$tr.find('>td').each(function () {
                _colspan += Number($(this).attr('colspan') || '1');
            });
            _$trMoreBet = $('<tr class="moreBetTr" mkey="' + _$cMoreBet.attr('mkey') + '" oddsid="' + _$cMoreBet.attr('oddsid') + '" LId="' + _$cMoreBet.attr('LId') + '"><td colspan="' + _colspan + '"></td></tr>');
        }
        _$tr.prev().nextAll('.gtRow[mkey="' + _$tr.attr('mkey') + '"]:last').after(_$trMoreBet);
        _$this.addClass('icon-moreCollapse').removeClass('icon-moreExpand');
    } else {
        _$this.addClass('icon-moreExpand').removeClass('icon-moreCollapse');
    }
    if (_needCopy) {
        _$trMoreBet.find('>td').html(_$cMoreBet.html());
    }
    ajaxMoreBet(0);
    //$('#oddsTable').find('.tdLast .icon-moreCollapse').addClass('icon-moreExpand').removeClass('icon-moreCollapse');
    //if (_needExp) {
    //    var _colspan = 0;
    //    _$tr.find('>td').each(function () {
    //        _colspan += Number($(this).attr('colspan') || '1');
    //    });
    //    var _$trm = $('<tr class="moreBetTr"><td colspan="' + _colspan + '"></td></tr>');
    //    _$tr.prev().nextAll('[mkey="' + _$tr.attr('mkey') + '"]:last').after(_$trm);
    //    _$this.addClass('icon-moreCollapse').removeClass('icon-moreExpand');
    //    mBetOddsId = _$tr.attr('oddsid');
    //    //sessionStorage.setItem('morebet', _$tr.attr('oddsid'));
    //    ajaxMoreBet(_$tr);


    //} else {
    //    mBetOddsId = '';
    //    //sessionStorage.removeItem('morebet');
    //    _$this.addClass('icon-moreExpand').removeClass('icon-moreCollapse');
    //}

}

function sjson(_s) {
    return encodeURIComponent(_s).replace("\"", "\\\"").replace("\'", "\\\'");
};
/*----------------------------------- Odds Function BEGIN -----------------------------------*/
//---------- From SocOdds CLASS
function SocOddsIsAvailable(odds) {
    return ((odds > -1 && odds < 1) ? false : true);
}

function SocOddsIsAvailable3(odds1, odds2, hdpOdds) {

    if (ACT_AccType == "EU") {
        odds1 -= 10;
        odds2 -= 10;
    }

    if (hdpOdds == 0)
        return false;

    odds1 = odds1 / 10;
    odds2 = odds2 / 10;

    if (odds1 == 0 || odds2 == 0)
        return false;

    //Convert back to malay odds
    if (odds1 > 1 || odds1 < -1) {
        odds1 = -1 / odds1;
    }

    if (odds2 > 1 || odds2 < -1) {
        odds2 = -1 / odds2;
    }

    if (odds1 < 0 && odds2 < 0)
        return false;

    if (odds1 < 0 && odds2 > 0) {
        if (Math.abs(odds1) < odds2) {
            return false;
        }
    }
    else if (odds2 < 0 && odds1 > 0) {
        if (Math.abs(odds2) < odds1) {
            return false;
        }
    }

    if (odds1 > 0 && odds1 < 0.03)
        return false;

    if (odds2 > 0 && odds2 < 0.03)
        return false;

    return true;
}


function SocOddsIsAvailableOU(ou, runHomeScore, runAwayScore) {
    if (ou < 0)
        return false;
    else
        return (ou - (runHomeScore + runAwayScore) >= 0.5);
}

function SocOddsIsAvailable2(odds) {
    return ((odds > -1 && odds < 1) ? false : true);
}
function GetDisplayOdds(odds) {
    odds /= 10;

    return odds.toFixed(3);
}

function GetDisplayOdds2(odds) {
    odds = parseFloat(odds);
    return odds.toFixed(3);
}

function GetDisplayMMPct(pct) {
    if (pct < 0)
        return '<font color="#990000">' + (parseFloat(pct) / 100.00) + '</font>';
    else
        return (parseFloat(pct) / 100.00);
}

//---------- From Util CLASS


function GetDisplayHdp(hdp, isHomeGive, isBetHome) {
    if (hdp == 0.0) {
        return hdp.toString();
    }
    if (isHomeGive == isBetHome) {
        return ("-" + hdp.toString());
    }
    return (hdp.toString());
}

function UtilGetDisplayHdp(hdp, isHomeGive, transType) {
    var output = "";

    if (hdp == -1) {
        output = "";
    }
    else {

        if (hdp % 0.5 == 0)
            output = '<span class="bold">' + hdp.toString() + '</span>';
        else
            output = '<span class="bold">' + (hdp - 0.25).toString() + "-" + (hdp + 0.25).toString() + "</span>";

    }
    return output;
}

function UtilGetDisplayOdds(odds) {
    odds /= 10;
    //return odds.toFixed(3); /*original*/
    //return odds.toFixed(2); /*show 2 decimals*/

    //Remark: The toFixed() method converts a number into a string, keeping a specified number of decimals.
    odds = odds.toFixed(3);
    var len = odds.length;
    var finalOdds;

    var res = odds.substr(len - 1, 1);

    if (res == 0)
        finalOdds = odds.substr(0, len - 1);
    else
        finalOdds = odds.substr(0, len);

    finalOdds = (odds < 0 ? ('<span class="Negative">' + finalOdds + "</span>") : finalOdds);
    return finalOdds;
}

function UtilGetDisplayOdds4(odds) {
    return odds;
}
function UtilGetDisplayOdds2(odds) {
    //return odds.toFixed(3); /*original*/
    //return odds.toFixed(2); /*show 2 decimals*/
    if (odds < 1) return '';
    //Remark: The toFixed() method converts a number into a string, keeping a specified number of decimals.
    odds = odds.toFixed(3);
    var len = odds.length;
    var finalOdds;

    var res = odds.substr(len - 1, 1);

    if (res == 0)
        finalOdds = odds.substr(0, len - 1);
    else
        finalOdds = odds.substr(0, len);

    return finalOdds;
}

//JSon format using this
function UtilGetTextDecoration() {
    //return "onmouseover=\"this.style.textDecoration='underline'\" onmouseout=\"this.style.textDecoration='none'\"";
}

/*----------------------------------- Odds Function END -----------------------------------*/

function GetClsOdds(odds) {

    if (odds < 0)
        return 'NegOdds';
    else
        return 'PosOdds02';
}

function GetClsOddsX12(odds, isHomeGive, type, langClass) {

    var cls = '';

    if (isHomeGive) {
        if (type == '1') {
            cls = langClass + 'Give';
        }
        else if (type == '2') {
            cls = langClass + 'Take';
        }
        else if (type == 'X') {
            cls = 'Draw';
        }
    }
    else {
        if (type == '1') {
            cls = langClass + 'Take';
        }
        else if (type == '2') {
            cls = langClass + 'Give';
        }
        else if (type == 'X') {
            cls = 'Draw';
        }
    }
    return cls;
}

function _closeBet(e) {
    var event = e.originalEvent || event || window.event;
    var _$this = $(event.target);
    if (_$this.parents("#quickBetPanel").length == 0) {
        var _$qBP = $('#quickBetPanel');
        $('#divOMD').append(_$qBP);
        $('#oddsTable .oddsBet.selected').removeClass('selected panelActive-bottomRight')
        $(document).unbind('click', _closeBet);
        console.log('2');
    }
}

var _updBetLsT;
var _updBetLsUrl;
var _lastupdBet;
var _isLoadingBet = false;
var _Bettimer;
var _isBettimer = false;
//{
//    "BetType":"over_par", "GTitle":"PARLAY", "ParIsHomeGive":true, "ParHome":"Lazio", "ParAway":"Zulte Waregem", "ParIsRun":false, "ParGameType3":"N", "BetCoupon":1, "MaxParTicket":"1259", "MaxPar":"1259"
//        , "BetPar":[{ "SocOddsId": 14214, "Home": "Lazio", "Away": "Zulte Waregem", "IsHomeGive": true, "IsBetHome": false, "ParFullTimeId": "0", "TransType": "HDP", "IsOddsChange": false, "ParUrl": "http://localhost:222/Bet/JRecPanel.aspx?g=2&b=over_par&oId=14215&odds=20.2&_=1506587586612", "BetHdp": "+1.75", "BetOu": "1.5-2", "BetOdds": "1.54", "Test2": "testing2" }
//            , { "SocOddsId": 14215, "Home": "Lazio", "Away": "Zulte Waregem", "IsHomeGive": true, "IsBetHome": true, "ParFullTimeId": "0", "TransType": "OU", "IsOddsChange": false, "ParUrl": "http://localhost:222/Bet/JRecPanel.aspx?g=2&b=over_par&oId=14215&odds=20.2&_=1506587586612", "BetHdp": "-3.5", "BetOu": "3.5", "BetOdds": "2.02", "Test2": "testing2" }]
//        , "PayoutOdds":3.111, "BetUrl":"PanelBet.aspx?betType=over_par&odds=3.1108", "MinLimit":"10", "MaxLimit":"1259", "HidOdds":"2.111", "AmtS":"110", "ExRate":"3.97", "BetterOdds":1, "MoreBetUrl":"", "Test":"testing"
//}
//function closeBetBox() {
//    //clearParSession();
//    //clearBetCtrlValue();
//    //document.getElementById('tbBetBox').style.display = "none";
//    //document.getElementById('betChkMaxBet').checked = false;
//    //document.getElementById('panSports').style.display = "";

//    //if ("0" == "13")
//    //    document.getElementById("pSportList").style.display = "";
//}
//if (originalRequest.status != 200) {
//    //replaceStatus();
//    readStake();
//}
//SocTransId,DangerStatusTxt，TransType,TransTypeTxt,BetType,RefNo,TransDate,ModuleTitle,Home,Away,IsHomeGive,IsBetHome,Hdp,Score,Odds,OddsType,FirstHalf,Amt

function isInteger(obj) {
    return typeof parseFloat(obj) === 'number' && parseFloat(obj) % 0.5 === 0
}

function getBetInfo(_rows) {
    var _sb = [];
    for (var i = 0, leni = _rows.length; i < leni; i++) {
        var _dr = _rows[i];
        _sb.push('<table border="0" cellspacing="0" cellpadding="10" width="230px" id="tabtouzhu">');
        _sb.push(' <tbody><tr><td><table border="0" cellspacing="0" cellpadding="0" width="100%" background="' + (_dr[17].replace(/^\s+|\s+$/g, "") == "D" ? "../Img/dangerBlink.gif" : "") + '"  style="' + (_dr[17].replace(/^\s+|\s+$/g, "") == "D" ? "background: #E9FBDB;" : "") + '"><tbody> <tr>');
        _sb.push('<td class="Normal" valign="bottom" colspan="2"><font size="1">' + _dr[5] + '&nbsp;(' + _dr[6] + ')</font><br />');
        if (_dr[2] == 'PAR') {
            _sb.push('<b>' + _dr[8] + '</b><a href="javascript:GetPamTrans(\'userName=' + RES.logname + '&amp;id=' + _dr[0] + '\');" class="bold">&nbsp;<span class="gbParlay">(' + RES.Transactions + '...)</span></a><br /> <b>' + _dr[13] + '</b><br /><font size="1">' + _dr[20] + '</font><br /><font size="1">' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == 'DC') {
            _sb.push('' + _dr[8] + " x " + _dr[9] + '<br /><span class="gbGive">' + _dr[3] + '<br />' + _dr[12] + '</span> <b>&nbsp;&nbsp;' + _dr[13] + '&nbsp;&nbsp;' + _dr[14] + '</b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == 'CSR') {
            _sb.push(_dr[8] + " x " + _dr[9] + '<br />' + _dr[12] + '&nbsp;<b>' + _dr[13] + ' &nbsp;&nbsp;' + _dr[14] + '</b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == '1') {
            if (_dr[19] != "O")
                _sb.push('' + (_dr[19] == "O" ? "" : (_dr[4] == "O" ? "" : _dr[8] + " x " + _dr[9])) + '<br />');
            _sb.push('<span class="' + (_dr[11] != "1" ? "gbGive" : "gbTake2") + '">' + (_dr[8]) + '&nbsp;(' + RES.WIN2 + ')</span> <b>&nbsp;' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br /><font size="1">' + _dr[7] + '<br />' + (_dr[19] == "O" ? RES.OUTRIGHT2 : _dr[3]) + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == 'X') {
            _sb.push('' + _dr[4] == "O" ? "" : _dr[8] + " x " + _dr[9] + '<br />');
            _sb.push('<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + _dr[8] + '&nbsp;(' + RES.DRAW2 + ')</span><b>&nbsp;' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == '2') {
            _sb.push('' + _dr[4] == "O" ? "" : _dr[8] + " x " + _dr[9] + '<br />');
            _sb.push('<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + (_dr[9]) + '&nbsp;(' + RES.WIN2 + ')</span><b>&nbsp;' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == 'OE') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br /><span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + _dr[4] + '</span> <b>' + _dr[13] + '&nbsp;&nbsp;<span class="">(' + _dr[14] + ')</span></b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == '1DT' || _dr[2] == '2DT' || _dr[2] == "3D") {
            _sb.push(_dr[2] + ',' + _dr[7] + ',' + _dr[6].substr(0, 5) + '<br />' + _dr[12] + '</td> </tr> <tr>');
        }
        if (_dr[2] == 'FLG') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + (_dr[11] != "1" ? "gbGive" : "gbTake2") + '">' + _dr[12] + '</span> <b>&nbsp' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '</font></td>');
        }
        if (_dr[2] == 'OU') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' +
                _dr[4] + '</span> <b>(' + (isInteger(_dr[12]) == true ? _dr[12] : (parseFloat(_dr[12]) - 0.25) + "-" + (parseFloat(_dr[12]) + 0.25)) + (_dr[21] == "False" ? ")&nbsp;@" : ' @ ' + _dr[18] + ')&nbsp;&nbsp;') + '' + _dr[13] + '<span class="">(' + _dr[14] + ')</span></b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == 'HDP') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br />' +
                (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + ((_dr[10] == _dr[11]) ? "gbGive" : "gbTake2") + '">' +
                (_dr[11] == "1" ? _dr[8] : _dr[9]) + '</span> <b>(' + _dr[12] + (_dr[21] == "False" ? ")&nbsp;@" : ' @ ' + _dr[18] + ')&nbsp;&nbsp;') + '' + _dr[13] + '<span class="Negative"></span>&nbsp;(' + _dr[14] + ')</b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == 'PAM') {
            _sb.push('<b>' + _dr[8] + '</b><a href=javascript:GetPamTrans(\'userName=' + RES.logname + '&amp;id=' + _dr[0] + '\'); class="bold">&nbsp;<span class="gbParlay">(' + RES.Transactions + '...)</span></a><br /> <b>' + _dr[13] + '</b><br /><font size="1">' + _dr[20] + '</font><br /><font size="1">' + _dr[3] + '&nbsp;' + _dr[15] + '</font></td>');
        }
        if (_dr[2] == 'TG') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br /><span class="gbGive">' + RES.TOTALGOAL2 + '<br>' + _dr[12] + '</span> <b>&nbsp;&nbsp;' + _dr[13] + '</b><br><font size="1">' + _dr[7] + '<br>' + _dr[3] + '</font></td>');
        }
        if (_dr[2] == 'HFT') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br><span class="gbGive">' + _dr[12] + '</span> <b>&nbsp;&nbsp;' + _dr[13] + '</b><br><font size="1">' + _dr[7] + '<br>' + _dr[3] + '</font></td>');
        }
        if (_dr[2] == 'FS1') {
        }
        if (_dr[2] == 'MMH') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + ((_dr[11] == _dr[10]) ? "gbGive" : "gbTake2") + '">' +
                (_dr[11] == "1" ? _dr[8] : _dr[9]) + '</span> <b>(' + _dr[12] + (_dr[21] == "False" ? ")&nbsp;@" : ' @ ' + _dr[18] + ")&nbsp;&nbsp;") + '<span class="Negative">' + _dr[14] + '</span>&nbsp;' + (_dr[13] / 10.00).toFixed(2) + '</b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '</font></td>');
        }
        if (_dr[2] == 'MMO') {
            _sb.push(_dr[8] + ' x ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + _dr[4] + '</span> <b>(' + _dr[12] + (_dr[21] == "False" ? "))&nbsp;@" : ' @ ' + _dr[18] + "))&nbsp;&nbsp;") + '' + UtilGetDisplayMMPcts(_dr[13]) + '<span class="Negative">' + _dr[14] + '</span>&nbsp;</b><br /><font size="1">' + _dr[7] + '<br />' + _dr[3] + '</font></td>');
        }
        _sb.push('</tr><tr><td class="Normal" valign="bottom">');
        if (_dr[22].replace(/^\s+|\s+$/g, "") != '' && _dr[22].replace(/^\s+|\s+$/g, "") != _dr[17].replace(/^\s+|\s+$/g, "")) {
            _sb.push('<span class="Waiting">Waiting</span>&nbsp;<span class="Bold">/</span>&nbsp;');
        }
        _sb.push('<span class="' + (_dr[17].replace(/^\s+|\s+$/g, "") == "A" || _dr[17].replace(/^\s+|\s+$/g, "") == "N" ? "Accepted" : (_dr[17].replace(/^\s+|\s+$/g, "") == "D" ? "Waiting" : "Rejected")) + '">' + (_dr[1] != "" ? _dr[1] : "Rejected (" + (_dr[17].replace(/^\s+|\s+$/g, "") == "RA" ? "Abnormal bet" : _dr[17].replace(/^\s+|\s+$/g, "")) + "&nbsp;[" + _dr[6] + "])") + '</span></td>');
        var srca = (_dr[17].replace(/^\s+|\s+$/g, "") == "R" || _dr[17].replace(/^\s+|\s+$/g, "") == "RA" || _dr[17].replace(/^\s+|\s+$/g, "") == "RJ" || _dr[17].replace(/^\s+|\s+$/g, "") == "RA" || _dr[17].replace(/^\s+|\s+$/g, "") == "RG" || _dr[17].replace(/^\s+|\s+$/g, "") == "RP" || _dr[17].replace(/^\s+|\s+$/g, "") == "RR" || _dr[17].replace(/^\s+|\s+$/g, "") == "D" || _dr[17].replace(/^\s+|\s+$/g, "") == "C" ? "" : "<a href='wfBetSlip0.html?id=" + _dr[0] + "' target='_blank'><img src='../Img/print.gif' border='0' height=\"16\" width=\"16\" /></a>");
        _sb.push(' <td class="bold" valign="bottom" align="right" style="color:black">' + toThousands(_dr[16]) + '&nbsp;' + srca + '</td>');
        _sb.push('</tr></tbody></table></td></tr></tbody></table>');
    }
    if (_sb.length == 0) return '<div class="betInfo">No Bet</div>';
    return _sb.join('');
}
function drawStake(_sDb) {
    // check
    if (undefined == _sDb || null == _sDb || 0 == _sDb.length) {
        /*
        var _$Content = $('#tableStake');
        if (_$Content){
            _$Content.find('.betList-bets').empty().html('<space>is empty.</space>');
        }
        */
        return;
    }
    //$("#tableStake").html(_sDb);
    var _bets = '', _waiting = '', _void = '';
    if (_sDb != "ErRor") {
        //$('#betListContent .betList-bets .scroll-content:first').html(_sDb);
        var _data = eval("(" + _sDb + ")");
        if (_data) {
            _bets = getBetInfo(_data[0]);
            _waiting = getBetInfo(_data[1]);
            _void = getBetInfo(_data[2]);
        }
    }
    var _$Content = $('#tableStake');
    _$Content.find('.betList-bets').empty().html(_bets);
    //_$Content.find('.betList-waiting').empty().html(_waiting);
    //_$Content.find('.betList-void').empty().html(_void);
}

//function readStake() {
//    if (!_isBettimer && $('#hositrybet').hasClass('current')) {
//        _isBettimer = true;
//        jQuery.ajax({
//            async: true, cache: false, url: wroot + 'Bet/BetStake.ashx?&t=' + Date.now(), complete: function (_ort) {
//                _isBettimer = false; _ort.statusText != "error" && drawStake(_ort.responseText);
//            }
//        });
//    }
//}

function readStake() {
    if (!_isBettimer && $('#pBetList').is(":visible")) {
        _isBettimer = true;
        jQuery.ajax({
            async: true, cache: false, url: wroot + 'Bet/BetStake.ashx?type=W0&t=' + Date.now(), complete: function (_ort) {
                //async: true, cache: false, url: wroot + 'Bet/BetStake.ashx?&t=' + Date.now(), complete: function (_ort) {
                _isBettimer = false; _ort.statusText != "error" && drawStake(_ort.responseText);
            }
        });
    }
}

function UtilIsAvailableMM(Pct) {
    if (Pct == -1)
        return false;
    else
        return true;

}

function UtilGetDisplayMMPcts(pct) {

    return "<span >" + (pct / 10.00).toFixed(2) + "</span>";
}

function UtilGetDisplayMMPct(pct) {
    if (pct < 0)
        return '<span class="MM_red">(' + (pct / 100.00) + ")</span>";
    else
        return '<span class="MM_blue">(' + (pct / 100.00) + ")</span>";
}

function showBetStatus(_betval, _msg) {
    if (_msg == "NoLogin")
    {
        window.open(wroot + 'Signout.aspx', '_top');
        alert('Login information invalid');
        return;
    }
    //alert(originalRequest.responseText);
    //alert("<SPAN class='positive4'>1,001,781</SPAN>");
    var _traid = 0;
    if (_msg == "ACCESS") {
        window.open(wroot + 'AccessDenied.aspx', '_top');
    }
    else if (_msg == "SIGNOUT") {
        window.open(wroot + 'Signout.aspx', '_top');
    }
    else if (_msg == "MULTIBET") {
        //Do nothing
    }
    else if (_msg.indexOf("CHG|") != -1) {
        //showHint("<span class='Error'>" + originalRequest.responseText.split('|')[1] + "</span>");
        //$("#socBetOdds").html("<span class='boldBg2'>" + "&nbsp;@&nbsp;" + originalRequest.responseText.split('|')[2] + "</span>");
        updBetOdds();
    }
    else if (_msg.split("|")[0] == "NA") {
        $('#lblStatusBet').html('<span class="Error">' + RES.Oddsisnotavailable + "!</span>");
        $('#betTxtAmount')[0].disabled = true;
        $('#betBtnBet').hide();
    }
    else {

        var str = _msg.split("|");
        var msg = "";
        var rt = "";
        var param = "";
        var refresh = "";

        //clearBetCtrlValue();

        //Success Bet
        if (str.length == 6) {
            var msg = str[0];
            var rt = str[1];
            var param = str[2];
            var refresh = str[3];
            var amt = str[4];
            _traid = str[5]; betList(_betval);
            $("#betTxtAmount").val("");
            //$("#tbBetBox").hide();
            closeBetBox();
            $("#panSports").show();
            $("#socBetOdds").empty();
            switchPanel(1);
            readStake();
            showCredit();
            _betList = {};
        }
        else if (str.length == 4) {
            var type = str[0];
            var msg = str[1];
            var chgOdds = str[2];
            var betType = str[3];
            showHint("<span class='Error'>" + msg + "</span>");

            //if (type == 'CHG') {

            //    //VIP14 == "True" || VIP15== "True"
            //    //alert(msg);

            //    /* If Odds has changed, update the BetUrl's odds with the changed odds */
            //    var endIdx = bUrl.indexOf("&odds");
            //    if (endIdx > -1)
            //        bUrl = bUrl.substring(0, endIdx) + "&odds=" + chgOdds;

                //if (betType == "KEN")
                //    document.getElementById("odds_KEN").innerHTML = GetDisplayOdds2(chgOdds);
                //else if (betType == "HTB")
                //    document.getElementById("odds_HTB").innerHTML = GetDisplayOdds2(chgOdds);
                //else if (betType == "HKL")
                //    document.getElementById("odds_HKL").innerHTML = GetDisplayOdds2(chgOdds);
                //else if (betType == "ICJ")
                //    document.getElementById("odds_ICJ").innerHTML = GetDisplayOdds2(chgOdds);
                //else if (betType == "TNJ2")
                //    document.getElementById("odds_TNJ2").innerHTML = GetDisplayOdds2(chgOdds);
                //else if (betType == "1D" || betType == "2D")
                //    document.getElementById("odds_4D").innerHTML = GetDisplayOdds2(chgOdds);
                //else {
            //    if (betType == "home" || betType == "away" || betType == "over" || betType == "under" ||
            //        betType == "mmhome" || betType == "mmaway" || betType == "mmover" || betType == "mmunder")
            //        //document.getElementById("socBetOdds").innerHTML = "&nbsp;@&nbsp;" + GetDisplayOdds(chgOdds);
            //        //document.getElementById("socBetHdp").innerHTML = "<span class='boldBg2'>" + GetDisplayOdds(chgOdds) + "</span>";
            //        $('#txtBetOdds').html("<span class='boldBg2'>" + GetDisplayOdds(chgOdds) + "</span>");
            //        //document.getElementById("socBetOdds").innerHTML = "<span class='boldBg2'>&nbsp;@&nbsp;" + GetDisplayOdds(chgOdds) + "</span>";
            //    else if (betType == "odd" || betType == "even") {
            //        if (chgOdds < 0)
            //            $('#txtBetOdds').html("<span class='txtBetRedBold'>" + GetDisplayOdds(chgOdds) + "</span>");
            //            //document.getElementById("socBetOdds").innerHTML = "&nbsp;@&nbsp;<span class='txtBetRedBold'>" + GetDisplayOdds(chgOdds) + "</span>";
            //        else
            //            $('#txtBetOdds').html("<span class='txtBetBlueBold'>" + GetDisplayOdds(chgOdds) + "</span>");
            //        //document.getElementById("socBetOdds").innerHTML = "&nbsp;@&nbsp;<span class='txtBetBlueBold'>" + GetDisplayOdds(chgOdds) + "</span>";
            //    }
            //        //else if (betType == "even") {
            //        //    if (chgOdds < 0)
            //        //        document.getElementById("socBetOdds").innerHTML = "&nbsp;@&nbsp;<span class='txtBetRedBold'>" + GetDisplayOdds(chgOdds) + "</span>";
            //        //    else
            //        //        document.getElementById("socBetOdds").innerHTML = "&nbsp;@&nbsp;<span class='txtBetBlueBold'>" + GetDisplayOdds(chgOdds) + "</span>";
            //        //}
            //    else if (betType == "1" || betType == "X" || betType == "2")
            //        $('#txtBetOdds').html(GetDisplayOdds2(chgOdds));
            //        //document.getElementById("socBetOdds").innerHTML = "@&nbsp;" + GetDisplayOdds2(chgOdds) + "</span>";
            //        //else if (betType == "cs" || betType == "tg" || betType == "ttg" ||
            //    else if (betType == "cs" || betType == "csr" || betType == "tg" ||
            //        betType == "dc" || betType == "htft" || betType == "fglg")
            //        $('#txtBetOdds').html(chgOdds);
            //    //$('#mainSection .betInfo-live').addClass('statusChanged');
            //    //document.getElementById("socBetOdds").innerHTML = "&nbsp;@&nbsp;" + chgOdds + "</span>";
            //    //}
            //} //end if (type == CHG)
            //else if (type == 'CHGPCT') {

            //    /* If Pct has changed, update the BetUrl's odds with the changed Pct */
            //    var endIdx = bUrl.indexOf("&pct");
            //    if (endIdx > -1)
            //        bUrl = bUrl.substring(0, endIdx) + "&pct=" + parseInt(chgOdds) + "&odds=" + chgMMHdpOdds;

            //    if (betType == "mmhome" || betType == "mmaway") {
            //        $('#txtBetHdp').html("HDP&nbsp;&nbsp;" + chgMMHdp + "(" + "<span class='boldBg2'>" + GetDisplayMMPct(chgOdds) + "</span>)");
            //        //document.getElementById("socBetHdp").innerHTML = "HDP&nbsp;&nbsp;" + chgMMHdp + "(" + "<span class='boldBg2'>" + GetDisplayMMPct(chgOdds) + "</span>)";
            //    }
            //    else if (betType == "mmover" || betType == "mmunder") {
            //        $('#txtBetHdp').html(chgMMHdp + "(" + "<span class='boldBg2'>" + GetDisplayMMPct(chgOdds) + "</span>)");
            //        //document.getElementById("socBetHdp").innerHTML = chgMMHdp + "(" + "<span class='boldBg2'>" + GetDisplayMMPct(chgOdds) + "</span>)";
            //    }
            //    //$('#mainSection .betInfo-live').addClass('statusChanged');
            //} //end else if (type == 'CHGPCT')
            //else {
                //alert(msg);
               // showHint("<span class='Error'>" + msg + "</span>");
                //document.getElementById('lblStatusBet').innerHTML = "<span class='Error'>" + msg + "</span>";
            //}
            //setInitialFocus();
        }
        else if (str.length == 2) {
            var type = str[0];
            var msg = str[1];

            //if (type == 'PARCHG') {
            //    //alert(msg);
            //    showBetBox(panelUrl + '&isBP=1');
            //    //displayBetBox(panelUrl + '&isBP=1');
            //}
            //else if (type == 'CHG') {
            //    //alert(msg);
            //    showBetBox(panelUrl);
            //    //displayBetBox(panelUrl);
            //}
            //else {
            //    //alert(msg);
                showHint("<span class='Error'>" + msg + "</span>");
                //document.getElementById('lblStatusBet').innerHTML = "<span class='Error'>" + msg + "</span>";
            //}
            //setInitialFocus();
        }
        else {
            var _errMsg = str[0];
            //if (msg == "SUSPEND") {
            //    msg = RES.ErrMsg_SUSPEND
            //}
            //if (msg == "LOCKED") {
            //    msg = RES.STRAccountislocked
            //}
            //if (msg == "Exceeded_BetCount") {
            //    msg = RES.Transactionlimitexceeded
            //}
            //if (msg == "EXCEED_RunLimit" || msg == "EXCEED_MATCHBEFORERUN") {
            //    msg = RES.Stakeismorethanmatchbeforerunlimit
            //}
            //alert(msg);
            showHint("<span class='Error'>" + _errMsg + "</span>");
            return;
        }
    }

    if (_msg.status != 200) {
        //replaceStatus();
        readStake();
    }
    //$("#tbBetBox").hide();
    //$("#panSports").show();
    //switchPanel(1);
    //showCredit();
}
function showHint(_msg) {
    if (_msg) {
        $('#lblStatusBet').html(_msg);
        $("#tbBetBox").show();
        $("#panSports").hide();
        return;
    } else {
        $('#lblStatusBet').html('');
    }
}
function submitBet() {
    $("#divloading").show();
    $('#submitBetT').unbind('click', submitBet);
    showHint();
    if ($("#tbBetBox").is(':hidden')) return;
    ///CreditM = $("#txtXYCredit").find('span').html().replace(/,/g, '');
    //betUrl = betUrl || $('#txtBetUrl').val();
    maxLimit = bMaxLimit || parseFloat($('#betMaxLimit').val());
    var bminAmt = Number($("#betMinLimit").html().replace(/\$|\,/g, ''));
    var bAmt = Number($("#betTxtAmount").val().replace(/\$|\,/g, ''));
    if (parseFloat(bAmt) > maxLimit) {
        $("#divloading").hide();
        $('#submitBetT').bind('click', submitBet);
        isbet = true;
        showHint("<span class='Error'>Transaction limit exceeded!!!</span>");
        return;
    }
    if ((bAmt).length == 0) {
        $("#divloading").hide();
        isbet = true;
        $('#submitBetT').bind('click', submitBet);
        showHint("Please enter an amount!"); $('#betTxtAmount').focus();
        return;
    }
    if (bAmt < bminAmt) {
        $("#divloading").hide();
        isbet = true;
        $('#submitBetT').bind('click', submitBet);
        showHint("<span class='Error'>" + RES.EXCEEDMIN + "</span>");
        return;
    }
    if (betmode == "P") {
        var _arrTm = [];
        $("#betPar").find('table[betval]').attr('betval', function (_i, _col) { _arrTm.push(_col); });
        if (_arrTm.length < 2) return;
        $("#divloading").show();
        var coupon = $('#lstMultiPar2').val() || '0';
        ajaxBet($('#txtParOdds').attr('burl') + '&amt=' + bAmt + '&coupon=' + coupon + '&exRate=' + $('#txtParOdds').attr('ExRate') + '&BETID=' + _arrTm.join(','));
    }
    else {
        ajaxBet($("#txtBetUrl").val() + '&BTMD=' + betmode + '&amt=' + bAmt, $("#bettable1").attr('betval'));
    }
    //alert(maxLimit);
    //alert("BetUrl:" + betUrl);

    //var bAmt = $('#betTxtAmount').val();
    ///var bAmtM = $('#betTxtAmount').val().replace(/,/g, '');
    //var amt = '&amt=' + bAmt;
    //alert("betAmount:" + bAmt);
    ////if (Number(CreditM) < Number(bAmtM) || Number(CreditM)<=0) {
    ////    showHint("<span class='Error'>Not enough credit balance</span>");
    ////    return;
    ////}

    //if (parseFloat(bAmt.replace(/\$|\,/g, '')) > maxLimit) {
    //    $("#divloading").hide();
    //    showHint("<span class='Error'>Transaction limit exceeded!!!</span>");
    //    return;
    //}
    ////else

    //if ((bAmt).length == 0) {
    //    $("#divloading").hide();
    //    showHint("Please enter an amount!"); $('#betTxtAmount').focus();
    //    //setInitialFocus();
    //    return;
    //}

    //var _url = "";
    ////Bet Parlay
    //if (betUrl.indexOf("_par") > -1) {
    //    if ($('#lstMultiPar2').val() != null) {
    //        var coupon = $('#lstMultiPar2').val();

    //        //  var multiParMaxBet = '5000'; RES.multiParMaxBet
    //        var multiParMaxBet = RES.multiParMaxBet;
    //        var _maxParTicket = parseFloat(multiParMaxBet) / bExRate; //2000 / bExRate;
    //        var _maxPar = maxLimit;
    //        var maxBet = _maxParTicket / coupon;
    //        if (maxBet > _maxPar)
    //            maxBet = _maxPar;
    //        if (amt > maxBet) {
    //            $("#divloading").hide();
    //            showHint('<span class="Error">Transaction limit exceeded!!!</span>');
    //            return;
    //        }

    //        _url = betUrl + amt + '&coupon=' + coupon + '&exRate=' + bExRate;
    //    }
    //    else {

    //        if (parseFloat(bAmt) > parseFloat(maxLimit)) {
    //            $("#divloading").hide();
    //            showHint('<span class="Error">Transaction limit exceeded!!!</span>');
    //            return;
    //        }
    //        //ajaxBet(_col + '&BTMD=' + betmode + '&amt=' + bAmt, $(this).attr('betval'));

    //        _url = betUrl + '&BTMD=' + betmode + amt;
    //    }
    //}
    //else {
    //    //isBetterOdds
    //    var isBetterOdds = false;

    //    if ($('#betChkBetterOdds')[0].checked == true) {
    //        isBetterOdds = true;
    //    }

    //    _url = betUrl + '&BTMD=' + betmode + amt;
    //    //url = betUrl + amt;
    //}

    //jQuery.ajax({
    //    async: true, cache: false, url:  '/Bet/' + _url, complete: function (_ort) {
    //        _ort.statusText != "error" && showBetStatus(_ort);
    //        $("#divloading").hide();
    //    }
    //});
    //showHint();
    //showCredit();
}
function ajaxBet(_url, _betval) {
        jQuery.ajax({
            async: true, cache: false, url: '/Bet/' + _url, complete: function (_ort) {
                showBetStatus(_betval, _ort.statusText == "OK" || _ort.statusText == "success" ? _ort.responseText : 'err');
                $("#divloading").hide(); $('#submitBetT').bind('click', submitBet); isbet = true;
            }
        });
}
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3) ; i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    //return (((sign)?'':'-') + '$' + num + '.' + cents);
    return (((sign) ? '' : '-') + num + '.' + cents);
}


function CountMaxPayout2() {
    var amt = Number($("#betTxtAmount").val().replace(/\$|\,/g, ''));
    var odds = $("#txtHidOdds").val();
    var max = 0;
    var maxLimit = Number($("#betMaxLimit").html().replace(/\$|\,/g, ''));
    if (parseFloat(amt) > parseFloat(maxLimit)) {
        amt = Math.floor(parseFloat(maxLimit));
        $("#betTxtAmount").val(amt);
    }
    //add 1 for capital amount
    if (parseFloat(odds) < 0)
        max = 2 * amt; // Odds = 1, so 1 + 1 = 2
    else
        max = amt * (parseFloat(odds) + 1);
    $("#betLblMaxPayout").html(formatCurrency(max));
}



//新写的odd
var panelUrl = "";
var bUrl = "";
var bMaxLimit = "";
var bExRate = "";

//下注信息填充
function showBet(_gt, _b, _g, _oId, _oId_fh, _sc, _mkey, _TVurl) {
    betmode = _oId || _betLen <= 1 ? 'S' : 'P';
    if (betmode == 'S') {
        //var _mkey = _mkey;
        if (!_oId) {
            for (var _key in _betList) {
                _gt = _betList[_key].gt; _b = _betList[_key].b; _g = _betList[_key].g; _oId = _betList[_key].oId; _oId_fh = _betList[_key].oId_fh; _sc = _betList[_key].sc; /*_L = _betList[_key].L; _H = _betList[_key].H; _A = _betList[_key].A;*/ _TVurl = _betList[_key].TVurl;
                _mkey = _key;
                break;
            }
        }
        var _betid = _gt + '|' + _b + '|' + _g + '|' + _oId + '|' + _oId_fh + '|' + _sc;
        $("#bettable1").attr('betid', _mkey || _betid).attr('betval', _betid);//.find('>.list-group-item-heading>i').attr('class', _SPCS);
        $("#tbBetBox").attr("height", "391px");
        $("#bettable2").hide();
        $("#bettable1").show();
        $("#tableParlay").hide();
        $("#betTxtAmount").val("");

        //if (_TVurl != "" && _TVurl != undefined && _TVurl != '0') {
        //    //var _arrTVshow = TVshow.split('|');
        //    //var url = "LiveCast.aspx?Home=" + _arrTVshow[2] + "&Away=" + _arrTVshow[3] + "&Id=" + _arrTVshow[1];
        //    var src = '<iframe src="' + _TVurl + '" width="215px" height="100%" style="border:0px;" scrolling="yes" style="-ms-overflow-style:none; overflow:-moz-scrollbars-none; "></iframe>';

        //    $("#TVShowORNot").empty().html(src);
        //    $("#TVShowORNot").css({ 'overflow': 'scroll' });
        //    $("#TVShowORNot").show();
        //}
        //else {
        //    $("#TVShowORNot").hide();
        //    $("#TVFrame").empty();
        //}
        //$("#socModuleTitle").empty().html(_L);
        //$("#socClsHome").empty().html('<label id="socHome" >' + _H + '</label>');
        //$("#socClsAway").empty().html('<label id="socAway" >' + _A + '</label>');

    }
    else if (betmode == 'P') {
        $("#betGTitle").empty().html(RES.Parlay);
        $("#tbBetBox").removeAttr("height");
        $("#bettable2").show();
        $("#bettable1").hide();
        //$("#betPar").empty();
        _$betMD = $("#ParClone");
        var _len = 0;
        for (var _key in _betList) {
            _len++;
            var _$betItem, _betval = _betList[_key].gt + '|' + _betList[_key].b + (betmode == 'P' ? '_par' : '') + '|' + _betList[_key].g + '|' + _betList[_key].oId + '|' + _betList[_key].oId_fh + '|' + _betList[_key].sc;
            if ($("#betPar").find("table.GridItem[betid=" + _key + "]").length == 0) {
                _$betItem = _$betMD.find('table.GridItem:first').clone().attr('betid', _key).attr('betval', _betval).attr('id', '');
                //_$betItem.find('span[dbcol="homeName"]:first').html(_betList[_key].H);
                //_$betItem.find('span[dbcol="awayName"]:first').html(_betList[_key].A);
                $("#betPar").append(_$betItem);
            }
            else {
                $("#betPar").find("table.GridItem[betid=" + _key + "]").attr('betid', _key).attr('betval', _betval).attr('id', '');
            }
        }
        LoadLstMultiPar2(_len, 1);
    }
    updBetOdds();
}




function clearParSession() {
    jQuery.ajax({
        async: true, cache: false, url: wroot + 'Bet/ParRemove.aspx', complete: function (_ort) {
        }
    });
}

function LoadLstMultiPar2(parCnt, betCoupon) {
    var strMPar = "";
    //strMPar += "<select id='lstMultiPar2' name='lstMultiPar2' onChange='updateMaxParTicket2();'>";
    if (parCnt >= 2 && parCnt <= 10) {
        if (parCnt == 2) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>2 X 1</option>";
            else
                strMPar += "<option value='1'>2 X 1</option>";

            if (betCoupon == 2)
                strMPar += "<option value='2' selected>2 X 2</option>";
            else
                strMPar += "<option value='2'>2 X 2</option>";
            if (betCoupon == 3)
                strMPar += "<option value='3' selected>2 X 3</option>";
            else
                strMPar += "<option value='3'>2 X 3</option>";
        }
        else if (parCnt == 3) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>3 X 1</option>";
            else
                strMPar += "<option value='1'>3 X 1</option>";

            if (betCoupon == 3)
                strMPar += "<option value='3' selected>3 X 3</option>";
            else
                strMPar += "<option value='3'>3 X 3</option>";

            if (betCoupon == 4)
                strMPar += "<option value='4' selected>3 X 4</option>";
            else
                strMPar += "<option value='4'>3 X 4</option>";
        }
        else if (parCnt == 4) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>4 X 1</option>";
            else
                strMPar += "<option value='1'>4 X 1</option>";

            if (betCoupon == 4)
                strMPar += "<option value='4' selected>4 X 4</option>";
            else
                strMPar += "<option value='4'>4 X 4</option>";

            if (betCoupon == 5)
                strMPar += "<option value='5' selected>4 X 5</option>";
            else
                strMPar += "<option value='5'>4 X 5</option>";

            if (betCoupon == 6)
                strMPar += "<option value='6' selected>4 X 6</option>";
            else
                strMPar += "<option value='6'>4 X 6</option>";
        }
        else if (parCnt == 5) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>5 X 1</option>";
            else
                strMPar += "<option value='1'>5 X 1</option>";

            if (betCoupon == 5)
                strMPar += "<option value='5' selected>5 X 5</option>";
            else
                strMPar += "<option value='5'>5 X 5</option>";

            if (betCoupon == 6)
                strMPar += "<option value='6' selected>5 X 6</option>";
            else
                strMPar += "<option value='6'>5 X 6</option>";

            if (betCoupon == 10)
                strMPar += "<option value='10' selected>5 X 10</option>";
            else
                strMPar += "<option value='10'>5 X 10</option>";

            if (betCoupon == 16)
                strMPar += "<option value='16' selected>5 X 16</option>";
            else
                strMPar += "<option value='16'>5 X 16</option>";

            if (betCoupon == 20)
                strMPar += "<option value='20' selected>5 X 20</option>";
            else
                strMPar += "<option value='20'>5 X 20</option>";

            if (betCoupon == 26)
                strMPar += "<option value='26' selected>5 X 26</option>";
            else
                strMPar += "<option value='26'>5 X 26</option>";
        }
        else if (parCnt == 6) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>6 X 1</option>";
            else
                strMPar += "<option value='1'>6 X 1</option>";

            if (betCoupon == 6)
                strMPar += "<option value='6' selected>6 X 6</option>";
            else
                strMPar += "<option value='6'>6 X 6</option>";

            if (betCoupon == 7)
                strMPar += "<option value='7' selected>6 X 7</option>";
            else
                strMPar += "<option value='7'>6 X 7</option>";

            if (betCoupon == 15)
                strMPar += "<option value='15' selected>6 X 15</option>";
            else
                strMPar += "<option value='15'>6 X 15</option>";

            if (betCoupon == 20)
                strMPar += "<option value='20' selected>6 X 20</option>";
            else
                strMPar += "<option value='20'>6 X 20</option>";

            if (betCoupon == 35)
                strMPar += "<option value='35' selected>6 X 35</option>";
            else
                strMPar += "<option value='35'>6 X 35</option>";

            if (betCoupon == 42)
                strMPar += "<option value='42' selected>6 X 42</option>";
            else
                strMPar += "<option value='42'>6 X 42</option>";

            if (betCoupon == 50)
                strMPar += "<option value='50' selected>6 X 50</option>";
            else
                strMPar += "<option value='50'>6 X 50</option>";

            if (betCoupon == 57)
                strMPar += "<option value='57' selected>6 X 57</option>";
            else
                strMPar += "<option value='57'>6 X 57</option>";
        }
        else if (parCnt == 7) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>7 X 1</option>";
            else
                strMPar += "<option value='1'>7 X 1</option>";

            if (betCoupon == 7)
                strMPar += "<option value='7' selected>7 X 7</option>";
            else
                strMPar += "<option value='7'>7 X 7</option>";

            if (betCoupon == 8)
                strMPar += "<option value='8' selected>7 X 8</option>";
            else
                strMPar += "<option value='8'>7 X 8</option>";

            if (betCoupon == 21)
                strMPar += "<option value='21' selected>7 X 21</option>";
            else
                strMPar += "<option value='21'>7 X 21</option>";

            if (betCoupon == 28)
                strMPar += "<option value='28' selected>7 X 28</option>";
            else
                strMPar += "<option value='28'>7 X 28</option>";

            if (betCoupon == 29)
                strMPar += "<option value='29' selected>7 X 29</option>";
            else
                strMPar += "<option value='29'>7 X 29</option>";

            if (betCoupon == 35)
                strMPar += "<option value='35' selected>7 X 35</option>";
            else
                strMPar += "<option value='35'>7 X 35</option>";

            if (betCoupon == 56)
                strMPar += "<option value='56' selected>7 X 56</option>";
            else
                strMPar += "<option value='56'>7 X 56</option>";

            if (betCoupon == 70)
                strMPar += "<option value='70' selected>7 X 70</option>";
            else
                strMPar += "<option value='70'>7 X 70</option>";

            if (betCoupon == 91)
                strMPar += "<option value='91' selected>7 X 91</option>";
            else
                strMPar += "<option value='91'>7 X 91</option>";

            if (betCoupon == 98)
                strMPar += "<option value='98' selected>7 X 98</option>";
            else
                strMPar += "<option value='98'>7 X 98</option>";

            if (betCoupon == 99)
                strMPar += "<option value='99' selected>7 X 99</option>";
            else
                strMPar += "<option value='99'>7 X 99</option>";

            if (betCoupon == 112)
                strMPar += "<option value='112' selected>7 X 112</option>";
            else
                strMPar += "<option value='112'>7 X 112</option>";

            if (betCoupon == 119)
                strMPar += "<option value='119' selected>7 X 119</option>";
            else
                strMPar += "<option value='119'>7 X 119</option>";

            if (betCoupon == 120)
                strMPar += "<option value='120' selected>7 X 120</option>";
            else
                strMPar += "<option value='120'>7 X 120</option>";

        }
        else if (parCnt == 8) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>8 X 1</option>";
            else
                strMPar += "<option value='1'>8 X 1</option>";
        }
        else if (parCnt == 9) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>9 X 1</option>";
            else
                strMPar += "<option value='1'>9 X 1</option>";
        }
        else if (parCnt == 10) {
            if (betCoupon == 1)
                strMPar += "<option value='1' selected>10 X 1</option>";
            else
                strMPar += "<option value='1'>10 X 1</option>";
        }


        $(".betParOdds").show();
        $("#lstMultiPar2").show();
        $("#tableParlay").show();
        $("#lstMultiPar2").html(strMPar);
    } else {
        $(".betParOdds").hide();
        $("#lstMultiPar2").hide();
    }

    //return strMPar;
}

function removePar(_this) {
    $(_this).parents("table:first").parents("table:first").attr('betid', function (_i, _val) {
        betList(false, _val);
        if (_betLen == 1) { showBet(); }
        else if (_betLen > 1) {
            LoadLstMultiPar2(_betLen, 1); updBetOdds();
        }
    }).remove();
}

function floorCurrency(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3) ; i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    //return (((sign)?'':'-') + '$' + num + '.' + cents);
    //return (((sign) ? '' : '-') + num + '.' + cents);
    return (((sign) ? '' : '-') + num);
}

var _timeupdBetOdds;
var maxPar2 = 0;
var maxParTicket2 = 0;
function updBetOdds() {
    if ($('#tbBetBox').is(':hidden')) {
        clearTimeout(_timeupdBetOdds);
        return;
    }
    var _$betTick;
    var _arrTm = [];
    if ($("#bettable2").is(':hidden') && !$("#bettable1").is(':hidden')) {
        _$betTick = $('#bettable1');
        _arrTm.push($('#bettable1').attr('betval'));
    }
    else if ($("#bettable1").is(':hidden') && !$("#bettable2").is(':hidden')) {
        _$betTick = $('#betPar');
        _$betTick.find('table.GridItem').attr('betval', function (_i, _col) {
            _arrTm.push(_col);
        });
    }
    if (_arrTm.length == 0) return;
    _timeupdBetOdds && clearTimeout(_timeupdBetOdds);
    jQuery.ajax({
        async: true, cache: false, url: '/Bet/hBetOdds.ashx?BTMD=' + betmode + '&coupon=' + ($('#lstMultiPar2').val() || '0') + '&BETID=' + _arrTm.join(','), complete: function (_ort) {
            try {
                if (_ort.statusText == "OK" || _ort.statusText == "success") {
                    var _rows = eval(_ort.responseText); setBetOddsVal(_$betTick, _rows);
                }
            } catch (err) { }
            _timeupdBetOdds = setTimeout(function () {
               updBetOdds();
            }, 1000 * 3);
        }
    });
}

function setBetOddsVal(_$betTick, _rows) {
    //单场下注
    if ($("#bettable2").is(':hidden') && !$("#bettable1").is(':hidden')) {
        var _data = _rows[0][0];
        if (_rows[0].length == 0) {
            closeBetBox();
            return;
        }
        if (_rows[0].length > 0) {
            var betType = _data.id.split('|')[1];
            $("#socModuleTitle").empty().html(_data.League);
            _H = '<label id="socHome" ><span class="' + (_data.IsGive=="1"?"Give":"Take")+'">' + _data.Home+'</span></label>';
            _A = '<label id="socAway" ><span class="' + (_data.IsGive=="1" ? "Take" : "Give") + '">' + _data.Away +'</span></label>';
            $("#socClsHome").empty().html(_H);
            $("#socClsAway").empty().html(_A);
            if (betType == "mmhome" || betType == "mmaway" || betType == "mmover" || betType == "mmunder")
                BetHdp = _data.Hdp;
            else BetHdp = _data.Hdp.replace("(", "").replace(")", "");
            if (_data.bTeam == "Home") {
                BETeam = $("#socHome").html();
            }
            else if(_data.bTeam=="Away"){
                BETeam = $("#socAway").html();
            }
            if (_data.outright == "true") { gTitle = RES.OUTRIGHT2; $("#trPanMatch").hide(); BetHdp = ""; BETeam = "<span class='gbX12'>" + _data.Home+ "&nbsp;(" + RES.Win + ")</span>" }
            else {
                $("#trPanMatch").show();
                if (betType == "home" || betType == "away" || betType == "mmhome" || betType == "mmaway")
                {
                    gTitle = RES.HANDICAP2;
                }
                else if (betType == "over" || betType == "under" || betType == "mmover" || betType == "mmunder")
                {
                    gTitle = RES.OVERUNDER2;
                    if (betType == "over" || betType == "mmover") {
                        BETeam = '<span class="GBOver">' + RES.Odds_OVER + '</span>';
                    }
                    else {
                        BETeam = '<span class="GBUnder2" >' + RES.Odds_UNDER + '</span>';
                    }
                }
                else if (betType == "odd" || betType == "even"){
                    gTitle = RES.ODDEVEN2;
                    BetHdp = "";
                    if (betType == "odd") BETeam = '<span class="GBGive">' + RES.ODD2 + '<span>';
                    else BETeam = '<span class="GBUnder">' + RES.EVEN2 + '<span>';
                }
                else if ((betType == "1" || betType == "X" || betType == "2")){
                    gTitle = "1X2";
                    BetHdp = "";
                    if (betType == "1") BETeam = '<span class="gbX12">' + _data.Home + '&nbsp;(' + RES.Win + ')</span>';
                    else if (betType == "2") BETeam = '<span class="gbX12">' + _data.Away + '&nbsp;(' + RES.Win + ')</span>';
                    else BETeam = '<span class="gbX12">' + _data.Home + '&nbsp;(' + RES.Draw + ')</span>';
                }
                else if (betType == "cs"||betType == "csr"){
                    gTitle = RES.CORRECTSCORE2; BETeam = "";
            }
                else if (betType == "tg"){
                    gTitle = RES.TOTALGOAL2;
                    BETeam = '<span class="GBUnder GBRed" >' + RES.TOTALGOAL2 + '</span>';
                }
                else if (betType == "htft") {
                    gTitle = RES.HALFTIMEFULLTIME2;
                    BETeam = '<span class="GBUnder GBRed" >' +RES.HALFTIME_FULLTIME + '</span>';
                }
                else if (betType == "fglg"){
                    gTitle = RES.FIRSTGOALLASTGOAL2;
                    var dc = _data.id.split('|')[5];
                    //var HomeNameFG = $("#socHome").find("span").length > 0 ? $("#socHome").find("span").html() : $("#socHome").html();
                    //var AwayNameFG = $("#socAway").find("span").length > 0 ? $("#socAway").find("span").html() : $("#socAway").html();
                    if (dc == "1") BetHdp = "<span class='Take'>" + _data.Home + "(" + RES.LastGoal + ")" + "</span>";
                    else if (dc == "10") BetHdp = "<span class='Take'>" + _data.Home + "(" + RES.FirstGoal + ")" + "</span>";
                    else if (dc == "0") BetHdp = "<span class='Take'>" + RES.NoGoal + "</span>";
                    else if (dc == "2") BetHdp = "<span class='Take'>" + _data.Away + "(" + RES.LastGoal + ")" + "</span>";
                    else if (dc == "20") BetHdp = "<span class='Take'>" + _data.Away + "(" +RES.FirstGoal + ")" +"</span>";
                    BETeam = '<span class="GBUnder GBRed" >' +RES.FIRSTGOALLASTGOAL2 + '</span>';
                }
                else if (betType == "dc") {
                    gTitle = RES.DOUBLECHANCE;
                    BetHdp = _data.bTT.replace("@", "").replace("FT.", "").replace("1H.", "").replace(/(^\s*)|(\s*$)/g, "");
                    BETeam = '<span class="GBUnder GBRed" >' +RES.DOUBLECHANCE + '</span>';
                }
            }
            $("#betGTitle").empty().html(gTitle);
            $("#socBetTeam").empty().html(BETeam);
            if (_data.Score != "") {
                $("#socIsRun").html(_data.Score.split('-')[0]);
                $("#socIsRun2").html(_data.Score.split('-')[1]);
            }
            else {
                $("#socIsRun").html('');
                $("#socIsRun2").html('');
            }

            if ($("#socBetOdds").html() != "" && $("#bettable1").attr("Odds") != _data.Odds) {
                $("#socBetHdp").html('<span class="boldBg2">' + BetHdp + '</span>');
                $("#socBetOdds").html('<span class="boldBg2">&nbsp;@&nbsp;' + _data.Odds + "</span>");
            }
            else {
                $("#socBetHdp").html(BetHdp)
                $("#socBetOdds").html('&nbsp;@&nbsp;' + _data.Odds);
            }

            $("#betMaxLimit").empty().html(Number(_data.MaxLimit) == 0 ? Number(_data.MaxLimit):toThousands(Number(_data.MaxLimit)));
            var betHidOdds = ($("#socBetOdds").find("span span").length > 0) ? $("#socBetOdds").find("span span").html().replace("&nbsp;@&nbsp;", "") : (($("#socBetOdds").find("span ").length > 0) ? $("#socBetOdds").find("span ").html().replace("&nbsp;@&nbsp;", "") : _data.Odds);
            $("#betHidOdds").val(betHidOdds);
            $("#betMinLimit").empty().html(Number(_data.MinLimit) == 0 ? Number(_data.MinLimit):toThousands(Number(_data.MinLimit)));
            $("#socFullTimeId").empty().html(_data.IsFH);
            CountSETAMT(_data.AmtS, _data.Odds, _data.MinLimit);
            $('#txtBetUrl').val(_data.beturl);
            $("#bettable1").attr('b', _data.id.toString().split('|')[1]).attr('Odds', _data.Odds).attr('Max', _data.MaxLimit).attr('Min', _data.MinLimit);

        }
        //else {
        //    closeBetBox();
        //}
    }
     //混合下注
    else if ($("#bettable1").is(':hidden') && !$("#bettable2").is(':hidden')) {
        _$betTick.find('>table[betval]').attr('st', 'upd');
        for (var i = 0, len = _rows[0].length; i < len; i++) {
            var _dr = _rows[0][i], _$betItem = _$betTick.find('>table[betval="' + _dr.id + '"]');
            _H = '<span class="' + (_dr.IsGive == "1" ? "Give" : "Take") + '">' + _dr.Home + '</span>';
            _A = '<span class="' + (_dr.IsGive == "1" ? "Take" : "Give") + '">' + _dr.Away + '</span>';
            _$betItem.find('span[dbcol="homeName"]:first').html(_H);
            _$betItem.find('span[dbcol="awayName"]:first').html(_A);
            var betType = _dr.id.split('|')[1]
            _selName = (_dr.bTeam == 'Home' ? _$betItem.find('span[dbcol="homeName"]').html() : (_dr.bTeam == 'Away' ? _$betItem.find('span[dbcol="awayName"]').html() : _dr.bTeam));
            var _IsFH = _dr.IsFH == "" ? "" : _dr.IsFH+"<br>";
            _betHdp = _dr.Hdp.replace("(", "").replace(")", "")
            if (betType == "home_par" || betType == "away_par") {
                _betHdp = _betHdp  + "&nbsp;@";
                _betType = "&nbsp;&nbsp";
            }
            else if (betType == "over_par" || betType == "under_par") {
                _betHdp = UtilGetDisplayHdp(parseFloat(_betHdp)) + '&nbsp;@';
                if (betType == "over_par") {
                    _betType = '<span class="GBOver">(' + RES.Odds_OVER + ')</span>&nbsp;&nbsp';
                }
                else {
                    _betType = '<span class="Take" >(' + RES.Odds_UNDER + ')</span>&nbsp;&nbsp';
                }
            }
            else if (betType == "odd_par" || betType == "even_par") {
                _betHdp = "&nbsp;@";
                if (betType == "odd_par") {
                    _betType = '<span class="GBOver">(' + RES.ODD2 + ')<span>&nbsp;&nbsp';
                } else {
                    _betType = '<span class="Take">(' + RES.EVEN2 + ')<span>&nbsp;&nbsp';
                }
            }
            else if ((betType == "1_par" || betType == "X_par" || betType == "2_par")) {
                _betHdp = "&nbsp;@";
                 if (betType == "1_par") {
                    _betType = '<span class="gbX12">(' + RES.Win + ')</span>&nbsp;&nbsp';
                }
                else if(betType == "X_par") {
                    _betType = '<span class="gbX12" >(' +RES.Draw + ')</span>&nbsp;&nbsp';
                }
                 else if(betType == "2_par") {
                    _betType = '<span class="gbX12" >(' +RES.Win + ')</span>&nbsp;&nbsp';
                }
            }
            _betScore = _dr.Score != "" ? "<span>(" + _dr.Score + ")<span>" : "";
            var _html = _IsFH + "&nbsp;" + _selName + "&nbsp;&nbsp" + _betType + _betScore + "<br>" + "&nbsp;&nbsp;" + _betHdp + "&nbsp;" + _dr.Odds;
            _$betItem.find(".betName").html(_html);
            _$betItem.find(".Parpicture").html('<img id="btnDelete" src="../Img/panel/btnClose.jpg" width="21px" height="19px" border="0" /></span>');
            _$betItem.attr('st', '');
            //_$betItem.find(".betNameHDP").html(_betHdp);
            //_$betItem.find(".betNameODD").html(_dr.Odds);

        }
        var _blUpd = false;
        _$betTick.find('>table[st="upd"]').each(function () {
            _blUpd = true;
            betList(false, $(this).attr('betid'));
            $(this).remove();
        });
        if (_blUpd) {
            showBet();
            return;
        }
        $("#betMaxLimit").empty().html(toThousands(Number(_rows[1][3])));
        maxPar2 = _rows[1][0];
        maxParTicket2 = _rows[1][3];
        $("#betMinLimit").empty().html(toThousands(Number(_rows[1][1])));
        $('#txtParOdds').html(_rows[1][2].toFixed(2)).attr('burl', _rows[1][4]).attr('ExRate', _rows[1][5]);
        CountSETAMT(_rows[1][6], _rows[1][2].toFixed(2), _rows[1][1]);
        updateMaxParTicket2();
    }
}
function CountSETAMT(AMT, Odds, MinLimit) {
    var betLblMaxPayout = parseFloat(AMT) * parseFloat(Odds) + parseFloat(MinLimit);
    if (AMT != "0") {
        if ($("#betTxtAmount").val() == AMT || $("#betTxtAmount").val() == "") {
            $("#betTxtAmount").val(AMT);
            $("#betTxtAmount").select();
        }
        if ($("#betLblMaxPayout").html == "0.00") {
            $("#betLblMaxPayout").html(betLblMaxPayout);
        }
        else {
            if ($("#betLblMaxPayout").html() != betLblMaxPayout) {
                $("#betLblMaxPayout").html(CountMaxPayout2());
            }
        }

    } else {
        if ($("#betTxtAmount").val() == "") {
            $("#betTxtAmount").val("");
            $("#betTxtAmount").focus();
        }
    }
    CountMaxPayout2();
}



var betmode = '';
function showBetBoxMB(url) {
    var event = event || window.event || arguments.callee.caller.arguments[0];
    event.cancelBubble = true;
    var _$this = $(event.target);
    showBetBox(url, _$this);
}
function showBetBox(url, _this) {
    isbet = true;
    var _$this = _this;
    if (_this == undefined || _this == "") {
        var event = event || window.event || arguments.callee.caller.arguments[0];
        event.cancelBubble = true;
        _$this = $(event.target);
    }
    var mkeyval, _HomeAway, mkeyvalNew;
    if (_$this.parents(".moreBetTr:first").length > 0)
        mkeyval = _$this.parents(".moreBetTr:first").prev().attr("mkey");
    else if (_$this.parents(".gtRCS:first").length > 0)
        mkeyval=_$this.parents(".gtRCS:first").parents("tr:first").attr("mkey");
    else
        mkeyval = _$this.parents(".gtRow:first").attr("mkey");
    //var _IsHomeGiveColor = mkeyval.split("_")[3];
    //var _L = _$this.parents("tbody[soclid]:first").find("tr.league:first .leagueName>span").html() || '';
    //if (_$this.parents(".gtRCS:first").length > 0)
    //    _HomeAway = _$this.parents(".gtRCS:first").parents("tr:first").prev().find(".homeNameawayName:first");
    //else
    //     _HomeAway = _$this.parents("tbody[soclid]:first").find("tr[mkey=" + mkeyval + "]:first .tdTeam:first");

    //var _H, _A, _HomeName, _AwayName;
    //if (_HomeAway.find(".Outright").length > 0) {
    //    _H = _HomeAway.find(".HomeNamePC").html() || '';
    //    _A = _HomeAway.find(".HomeNamePC").html() || '';
    //}
    //else {
    //    _HomeName = _HomeAway.find(".HomeNamePC").html();
    //    _AwayName = _HomeAway.find(".AwayNamePC").html();
    //    _H = "<span class='" + (IsHomeGiveColor == 1 ? ("Give") : ("Take")) + "'>" + _HomeName + "</span>" || '';
    //    _A = "<span class='" + (IsHomeGiveColor == 1 ? ("Take") : ("Give")) + "'>" + _AwayName + "</span>" || '';
    //}

//    if (_HomeAway.find(".homeName").length == 0) {
//        var _HomeName = _HomeAway.find(".HomeName .HomeRank").length > 0 ? _HomeAway.find(".HomeName .HomeRank").html():_HomeAway.find(".HomeName").html();
//          if (_HomeAway.find(".Outright").length > 0) {
//                _H = _HomeName ;
//                _A= _HomeName ;
//          } else {
//              var _AwayName = _HomeAway.find(".AwayName .AwayRank").length > 0 ? _HomeAway.find(".AwayName .AwayRank").html():_HomeAway.find(".AwayName").html();

//        _H = "<span class='" + (IsHomeGiveColor == 1 ? ("Give"): ("Take")) + "'>" + _HomeName + "</span>"  || '';
//        _A =  "<span class='" +(IsHomeGiveColor == 1 ? ("Take"): ("Give")) + "'>" +_AwayName + "</span>"  || '';
//    }
//    }
//    else {
//        var _home = _HomeAway.find(".homeName").children(":first").clone();
//        if (_home.find(".HomeRank").length > 0) _home.find(".HomeRank").remove();

//        var _away = _HomeAway.find(".awayName").children(":first").clone();
//        if (_away.find(".AwayRank").length > 0) _away.find(".AwayRank").remove();

//        _H = "<span class='" + (IsHomeGiveColor == 1 ? ("Give") : ("Take")) + "'>" + _home.html() + "</span>" || '';
//        _A = "<span class='" + (IsHomeGiveColor == 1 ? ("Take") : ("Give")) + "'>" + _away.html() + "</span>"  || '';
//}

    var b = GetQueryStr('b', url) || '', _haspar = (b == 'home' || b == 'away' || b == 'over' || b == 'under' || b == '1' || b == 'X' || b == '2' || b == 'odd' || b == 'even');
    var _tg = GetQueryStr('tg', url) || ''
    var _MinG = GetQueryStr('MinG', url) || ''
    var _par_b = GetQueryStr('par_b', url) || '', _gt = GetQueryStr('gt', url) || '', _g = GetQueryStr('g', url) || '1', _b = GetQueryStr('b', url) || '', _oId = GetQueryStr('oId', url) || '', _oId_fh = GetQueryStr('oId_fh', url) || '', _sc = GetQueryStr('sc', url) || '';
    if (_oId_fh && GetQueryStr('isFH', url) != 'true') _oId_fh = '';
    //if (_tg.split('|')[0] == "1") {
    //    var _$matchHTTG = (_tg.split('|')[1] == "H" ? true : false);
    //    var _$matchATTG = (_tg.split('|')[1] == "A" ? true : false);
    //    var _ishomegive = _H.indexOf('Give') > 0;
    //    var _dom = _$matchHTTG ? _H.split("</") : _$matchATTG ? _A.split("</") : "";
    //    if (_dom[0].indexOf('TG - [') < 0) {
    //        _H = (_ishomegive ? _dom[0].replace("Take", "Give") + (" TG - [O]") : _dom[0].replace("Give", "Take") + (" TG - [O]")) + "</" + _dom[1];
    //        _A = (!_ishomegive ? _dom[0].replace("Take", "Give") + (" TG - [U]") : _dom[0].replace("Give", "Take") + (" TG - [U]")) + "</" + _dom[1];
    //    }
    //    if (_L.indexOf("TEAM TOTAL GOALS") < 0)
    //        _L = _L + (" - TEAM TOTAL GOALS");
    //}
    //if (_MinG != "") {
    //    _L = _L + (" - SPECIFIC 15 MINS HANDICAP & OVER/UNDER");
    //    var _HMinG,_AMinG;
    //    if (_MinG == '1') {
    //        _HMinG = _HomeName + (" 00:00-15:00");
    //        _AMinG = _AwayName + (" 00:00-15:00");
    //    }
    //    else if (_MinG == '2') {
    //        _HMinG = _HomeName + (" 15:01-30:00");
    //        _AMinG = _AwayName + (" 15:01-30:00");
    //    }
    //    else if (_MinG == '3') {
    //        _HMinG = _HomeName + (" 30:01-45:00");
    //        _AMinG = _AwayName + (" 30:01-45:00");
    //    }
    //    else if (_MinG == '4') {
    //        _HMinG = _HomeName + (" 45:01-60:00");
    //        _AMinG = _AwayName + (" 45:01-60:00");
    //    }
    //    else {
    //        _HMinG = _HomeName + (" 60:01-75:00");
    //        _AMinG = _AwayName + (" 60:01-75:00");
    //    }
    //    _H = "<span class='" + (IsHomeGiveColor == 1 ? ("Give") : ("Take")) + "'>" + _HMinG+ "</span>" || '';
    //    _A = "<span class='" + (IsHomeGiveColor == 1 ? ("Take") : ("Give")) + "'>" + _AMinG + "</span>" || '';

    //}

    stacoupon = 1;
    showHint();
    //球赛和下注的显示和隐藏
    $('#betTxtAmount')[0].disabled = false;
    $('#betBtnBet').show();
    $("#tbBetBox").show();
    $("#tbBetBox").addClass('active');
    $("#panSports").hide();
    //$("#socModuleTitle").html("");
    //$("#socBetTeam").html("");
    //$("#socIsRun").html(" ");
    //$("#socIsRun2").html(" ");
    $("#betTxtAmount").val("");
    $('#betChkMaxBet')[0].checked = false;
    if (_par_b == '') {
        _haspar = false;
    }

    mkeyvalNew = mkeyval.split("_")[0] + mkeyval.split("_")[1] + mkeyval.split("_")[2];
    //把过关的数据写入
    if (_haspar) {
        if (_betLen < 10) {
            betList(true, mkeyvalNew, _gt, _b, _g, _oId, _oId_fh, _sc);
        }
    }
    if (!_haspar || _haspar && _betLen == 1) {
        //betList(false); $("#betPar").empty();
        //if (_haspar) betList(true, mkeyvalNew, _gt, _b, _g, _oId, _oId_fh, _sc, _L, _H, _A);
        if (_betLen > 0 && !_haspar) {
            if (_betList[mkeyvalNew] != "" && _betList[mkeyvalNew] != undefined && _betLen==1) {
                betList(false, mkeyvalNew);
            }
            else {
                $("#TipSParlary").addClass("showTipParlary");
                setTimeout(function () {
                    $("#TipSParlary").removeClass("showTipParlary");
                }, 2000)
            }
        }
        if (_betLen == 0 && !_haspar) $("#PARTipsInTable").show();
        else $("#PARTipsInTable").hide();
        if (_betLen > 1 || (_betLen == 1 && !_haspar))  return;
        showBet(_gt, _b, _g, _oId, _oId_fh, _sc, mkeyvalNew);
    } else {
        showBet();
    }


    //if (_updBetLsT != null) {
    //clearInterval(_updBetLsT); _updBetLsT = null;
    //}

    //_updBetLsUrl = url;
    ////$("#tbBetBox").show();
    ////$("#panSports").hide();
    //_ajaxBetList(_updBetLsUrl);
    //_lastupdBet = (new Date()).getTime();
    //if ($("#tbBetBox").is(":visible") == true) {
    //    _updBetLsT = setInterval(function () {
    //        var _autoUpdBet = $('#betChkBetterOdds')[0].checked;
    //            if (!_isLoadingBet) {
    //                _ajaxBetList(_updBetLsUrl);
    //            }
    //    }, 9000);
    // }


    //  var _delay = 10;
    //  _updBetLsUrl = url;
    ////showBet(_gt, _b, _g, _oId, _oId_fh, _sc, _L, _H, _A, _TVurl, _$match.attr('mkey'));
    //  _ajaxBetList(_updBetLsUrl, _L, _H, _A, mkeyval);
    //  _lastupdBet = (new Date()).getTime();
    //  _updBetLsT = setInterval(function () {
    //      if ($('#tbBetBox').is(":visible")) {
    //          if (!_isLoadingBet) {
    //              var _tt = (_delay - ((new Date()).getTime() - _lastupdBet) / 1000).toFixed(0);
    //              if (_tt <= 0) {
    //                  _lastupdBet = (new Date()).getTime();
    //                      _ajaxBetList(_updBetLsUrl, _L, _H, _A, mkeyval);
    //              }
    //          }
    //      }
    //  }, 1000);

    //$('#toubet').trigger('click');
}

var _betList = {}, _betLen = 0;
function betList(_add, _id, _gt, _b, _g, _oId, _oId_fh, _sc) {
    if (_add) {
        delete _betList[_id];
        _betList[_id] = { gt: _gt, b: _b, g: _g, oId: _oId, oId_fh: _oId_fh, sc: _sc };
    } else if (_id == null) {
        _betList = {};
    } else {
        delete _betList[_id];
    }
    var _len = 0;
    for (var _key in _betList) { _len++; } _betLen = _len;
}

var oldindex = '';
//showBetBoxRU('JRecPanel.aspx?gt=s&b=over&oId=13329', '13329E1734|4878|26', 'over', 't', 0);
function showBetBoxRU(url, idx, b, ot, delay) {
    var event = event || window.event || arguments.callee.caller.arguments[0];
    event.cancelBubble = true;
    var _$this = $(event.target);
    $('#betTxtAmount')[0].disabled = false;
    $('#betBtnBet').show();
    var odds = getNumByS(_$this.html());
    if (odds == -99999) return;
    var b = GetQueryStr('b', url);
    if (b == 'home' || b == 'away' || b == 'over' || b == 'under' ||
        b == 'homefh' || b == 'awayfh' || b == 'overfh' || b == 'underfh') {
        odds *= 10;
        odds = odds.toFixed(3);
    }
    url = url + '&odds=' + odds;
    if (delay == 20) {
        setTimeout('showBetBox("' + url + ',' + _$this+'")', 5000);
    }
    else if (delay == 30) {
        setTimeout('showBetBox("' + url + ',' + _$this+'")', 7000);
    }
    else { showBetBox(url, _$this); }

    return false;

}
function addBetSlip() {

}

function fFormatDecimal(total, DecimalPlaces) {
    if (fTrim(total) == "")
        return "";
    total = total.toString().replace(/\$|\,/g, '');
    var isNegative = false;
    // First verify incoming value is a number
    if (isNaN(total)) {
        var returnValue = "0";
        if (DecimalPlaces > 0)
            returnValue += ".";
        for (var i = 0; i < DecimalPlaces; i++)
            returnValue += "0";
        return returnValue;
    }
    if (total < 0) {
        isNegative = true;
        total = total * -1;
    }
    // Second round incoming value to correct number of decimal places
    var RoundedTotal = total * Math.pow(10, DecimalPlaces);
    RoundedTotal = Math.round(RoundedTotal);
    RoundedTotal = RoundedTotal / Math.pow(10, DecimalPlaces);

    // Third pad with 0's if necessary the number to a string
    var Totalstring = RoundedTotal.toString(); // Convert to a string
    var DecimalPoint = Totalstring.indexOf("."); // Look for decimal point
    if (DecimalPoint == -1) {
        // No decimal so we need to pad all decimal places with 0's - if any
        currentDecimals = 0;
        // Add a decimal point if DecimalPlaces is GT 0
        Totalstring += DecimalPlaces > 0 ? "." : "";
    }
    else {
        // There is already a decimal so we only need to pad remaining decimal places with 0's
        currentDecimals = Totalstring.length - DecimalPoint - 1;
    }
    // Determine how many decimal places need to be padded with 0's
    var Pad = DecimalPlaces - currentDecimals;
    if (Pad > 0) {
        for (var count = 1; count <= Pad; count++)
            Totalstring += "0";
    }

    var num = null;
    if (Totalstring.indexOf(".") != -1) {
        num = Totalstring.substring(0, Totalstring.indexOf("."));
    } else {
        num = Totalstring;
    }

    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3) ; i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
    if (Totalstring.indexOf(".") != -1) {
        Totalstring = num + Totalstring.substring(Totalstring.indexOf("."));
    } else {
        Totalstring = num;
    }

    if (isNegative)
        Totalstring = "-" + Totalstring;
    return Totalstring;
}
function fTrim(s) {
    s = s + ""; //this is used to convert to string, where numeic not support length function for funrther processing
    while ((s.substring(0, 1) == ' ') || (s.substring(0, 1) == '\n') || (s.substring(0, 1) == '\r')) {
        s = s.substring(1, s.length);
    } //end while

    // Remove trailing spaces and carriage returns
    while ((s.substring(s.length - 1, s.length) == ' ') || (s.substring(s.length - 1, s.length) == '\n') || (s.substring(s.length - 1, s.length) == '\r')) {
        s = s.substring(0, s.length - 1);
    } //end while
    return s;
}

//MM/dd/yyyy时间格式转换为yyyy-MM-dd
function toDate(_dtStr) {
    var _val = _dtStr;
    var _arr = _val.match(/\d+/g);
    if (_val == '' || _arr[0].length == 4) return _val;
    if (_arr.length >= 3) _val = _arr[2] + '-' + _arr[0] + '-' + _arr[1];
    if (_arr.length >= 6) {
        _val = _val + ' ' + _arr[3] + ':' + _arr[4] + ':' + _arr[5];
        if (_dtStr.toUpperCase().indexOf('AM') != -1) _val = _val + ' AM';
        if (_dtStr.toUpperCase().indexOf('PM') != -1) _val = _val + ' PM';
    }
    return _val;
}