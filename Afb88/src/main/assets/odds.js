
var _dicTimer = {};
var _dicLID = {};
var _dicUrl = {};
var _dicT = {};
var _dicGtT = {};
var _dicPMS = {};
var _dataM = {};
var _sportID = '';
var ACT_AccType = 'MY';
var ACT_UserName = '';
var ACT_UseWebSocket = '0';
var _tf = -1;
var _lastDbId = '';
var _laertime = '';
var ia = "0";
var iftime = "";
var isfav = false;
//var isbet = true;
var seldbid = "";
//var hidedhshow = true;

function setSelLeague(_selSpt, _id) {
    sessionStorage.setItem(_selSpt, _id);
    refData();
}
function getSelLeague(_selSpt) {
    return sessionStorage.getItem(_selSpt) || '';
}
function setFav(_snum, _addkey, _delkey, all, len) {
    var _dicTem = JSON.parse(sessionStorage.getItem('_SEL_FAV')) || {};
    if (!_dicTem[_snum]) {
        _dicTem[_snum] = [];
    }
    if (_addkey != null) {
        _dicTem[_snum].indexOf(_addkey) == -1 && _dicTem[_snum].push(_addkey);
    }
    if (_delkey != null) {
        _dicTem[_snum].indexOf(_delkey) != -1 && _dicTem[_snum].splice(_dicTem[_snum].indexOf(_delkey), 1);
    }
    sessionStorage.setItem('_SEL_FAV', JSON.stringify(_dicTem))//转化json为字符串存入
    //refData();
}
function getFavStare(_snum, _addkey) {
    var _dicTem = JSON.parse(sessionStorage.getItem('_SEL_FAV')) || {};//转化为json数据
    if (!_dicTem[_snum]) {
        _dicTem[_snum] = [];
    }
    return _dicTem[_snum].indexOf(_addkey) != -1;
}


function loadOddsShow(_dbId, _sBtn, _op, _ertime) {
    sessionStorage.removeItem('morebet');
    MHeight && MHeight();
    if (_dbId == "666") {
        $("#TOP5").addClass("Top5Change");
    } else {
        $("#TOP5").removeClass("Top5Change");
    }

    if (_dbId.split('_')[0] == "1") {
        _laertime = $("#lstDate").val();
    } else {
        _laertime = '7';
    }
    var _$spl = $("#sportList");
    var _$cp = $("#mainArea .caption:first");
    var _$tm1 = _$spl.find('.category-sportList.active');
    if (_dbId == "666") {
        _sportID = "666_666";//用于TOP的选择联赛使用

    }
    else {
        _sportID = _$tm1.find('.category-sub-list.active').attr('dtid') || "1_1_2";
    }
    ACT_AccType = $("#accTpLst").val();
    var _ttname = _$tm1.find('li.active .betTypeName:first').html();
    _ttname = _$tm1.find('li.active .betTypeName:first').attr('title')
    if (_dbId == "666") {
        _ttname = _$spl.find("li[dtid='1_1_3'] .betTypeName:first").attr('title')
    }

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
    if (_dbId == '1_1_3' || _dbId == '1_1_2' || _dbId == '1_1_1' || _dbId == '1_1_3_F' || _dbId == '1_1_3_H') {
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
        if (_dbId == '1_1_3' || _dbId == '1_1_3_F' || _dbId == '1_1_3_H') {
            $("#bleLines").addClass("icon_DoubleLine").removeClass("icon_SingleLine")
        }
        if (_dbId == '1_1_2') {
            $("#bleLines").addClass("icon_SingleLine").removeClass("icon_DoubleLine")
        }
        $("#btnlistmiandian").hide();
        $("#MyFavorites").removeClass("MyFavorites");
        if (RES.islogin != "True") {
            $("#btnlist").show();
            $("#menuSingleDouble").parents("td:first").show();
        }
            
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
            $(".LinkButton2").hide();
        } else {
            $(".LinkButton2").show();
            if (_dbId == '1_17' || _dbId == '1_17_2' || _dbId == '1_17_3' || _dbId == '1_17_4' || _dbId == '1_17_5') {
                if (RES.islogin != "True") {
                    $("#btnlist").show();
                    $("#menuSingleDouble").parents("td:first").hide();
                }
                if (_ot == 'e') {
                    ia = '1';
                    $("#lstDate").show();
                    $("#btnlistmiandian").show();
                    $("#typetitle").show();
                    $(".titletype").html(_ttname);
                    $(".LinkButton").attr("onclick", "loadOddsShow('" + _dbId + "')");
                } else {
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

    }

    $('#oddsTable').empty().attr("isload", "0");

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
        if (_op.mt == "0") {
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
    _dataM = {}; _dicLID = {}; _dicUrl = {}; _dicT = {}; _dicGtT = {}; _dicPMS = {};
    _selSpt = _$tm1.attr('sport') + '_' + (_ot == 'e' ? '1' : '0');
    if ($('#lsttf').hasClass('timefilter') || $('#Toplsttf').hasClass('timefilter')) {
        _tf = $("#lsttf").val();
    }
    var _param;

    if (_dbId != "1_0_2") {
        _lastDbId = _dbId;
    }
    if (_dbId == "6_12" || _dbId == "33_18" || _dbId == "33_19" || _dbId == "33_20") return;
    if (_dbId == "1_0_2") {
        var _arrTmFav = [];
        var spfav = JSON.parse(sessionStorage.getItem('_SEL_FAV'));

        for (var keyname in spfav) {
            var dtidobj = $("#sportList").find("[sport='" + keyname + "']").find('li[dtid]:first');

            if (dtidobj.length > 0 && spfav[keyname].length) {
                if (keyname == "1") {
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
        }
        isfav = true;
        if (_laertime == "6" || _laertime == "7")
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType, ia: 1 };
        else
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType };
    }
    else {
        isfav = false;
        if (_laertime == "6" || _laertime == "7") {
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType, ia: 1 };
        }
        else {
            _param = { ACT: 'LOS', DBID: _dbId, ot: _ot, tf: _tf, timess: _laertime, accType: ACT_AccType };
        }
    }
    $.extend(_param, (_op || {}));
    if (ACT_UseWebSocket == '1') {
        GetDataByWS(_param);
    } else {
        ajCode(_param, null, function (_db) { });
    }
}
function refData(_dbId) {
    if (ACT_UseWebSocket == '1') {
        GetDataByWS(lastWsParam); return;
    }
    if (_dbId == null) {
        $('#oddsTable>table[odbid]').attr('odbid', function (i, _val) {
            _dicLID[_val] = '';
           
             ajaxOdds(_val);
        });
    } else {
        _dicLID[_dbId] = '';
         ajaxOdds(_dbId);
    }
}
function ajaxOdds(_dbId, url, _t) {
    if (_dicTimer[_dbId] != null) { clearTimeout(_dicTimer[_dbId]); _dicTimer[_dbId] = null; }
    if (url != null && _dicUrl[_dbId] == null) _dicUrl[_dbId] = url;
    if (_t == null) { _t = 0; } else if (_dicT[_dbId] == null) { _dicT[_dbId] = _t; _t = 0; } else if (_dicT[_dbId] != null) { _t = _dicT[_dbId]; }
    _dicTimer[_dbId] = setTimeout(function () {
        if (_dicUrl[_dbId] == null)
            return;
        var _selSpt = _dbId.split('_')[0] + '_' + ($("#sportList").hasClass('early') ? '1' : '0');
        var _dicfav = JSON.parse(sessionStorage.getItem('_SEL_FAV')) || {};
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
};

var ws, wsParam = {}, lastWsParam = {},_IDS=[];
function CreatWS(_url) {
    if (ws && (ws.readyState == 1)) {
        ws.close();
    } else {
        if (window.location.protocol == 'https:') {
            _urltm = _urltm.replace('ws://', 'wss://').replace(':8888', ':8887');
        }
        ws = new WebSocket(_url);


        ws.onopen = function (e) {
            console.log("Linked...");
            GetDataByWS(lastWsParam);
        };
        ws.onmessage = function (e) {
            if (ws && ws.readyState == 1) {
                if (e.data == '3') { _pingRet = '3'; }//响应ping
                else {
                    var _data = JSON.parse(e.data);
                    if (_data.dbtype == "IDS") {
                        _IDS = _data.dbid.split(',');
                    }
                    else {
                        ShowOddsByQuery(_data.dbid, _data.data);
                        if (_IDS.indexOf(_data.dbid) == -1) { $('#divOdds_' + _data.dbid).empty().remove(); }
                    }
                    $("#showloading").hide();
                    //if (_dicTimer[_dbId] != null) {
                    //    ShowOddsByQuery(_dbId, e.data);
                    //}
                }
            } else { console.log("ws.readyState=" + ws.readyState); }
        };
        ws.onclose = function (e) {
            console.log("Closed...");
        };

    }
}
function GetDataByWS(_param) {
    $('#oddsTable').empty();
    lastWsParam = _param;
    var _wsparam = {};
    $.extend(_wsparam, wsParam);
    $.extend(_wsparam, _param);

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
    _wsparam.ov= sort;
    _wsparam.mt = markettype;


    var _arrTm = _wsparam.DBID.split(',');
    var _postParam = [];
    for (var i = 0, len = _arrTm.length; i < len; i++) {
        var _paraTm = {}, _dbId = _arrTm[i];
        $.extend(_paraTm,_wsparam);
        var _fav = (isfav == true) ? (_dbId && _dbId.split('_').length > 0 && _dicfav && _dicfav[_dbId.split('_')[0]] ? _dicfav[_dbId.split('_')[0]].join('|') : '') : '';
        if (isfav && _fav == '') _fav = '0_0_0';
        _paraTm.FAV = _fav;

        var _selSpt = _dbId.split('_')[0] + '_' + ($("#sportList").hasClass('early') ? '1' : '0');
        _paraTm.SL = getSelLeague(_selSpt);
        _paraTm.DBID = _dbId;
        _paraTm.betable = _paraTm.betable=='1';
        _paraTm.fh = _paraTm.fh == '1';
        _paraTm.isToday = _paraTm.isToday == '1';
        _postParam.push(_paraTm);
    }
    //var _urltm = _dicUrl[_dbId] + '&LID=' + /*(_dicLID[_dbId] || '') +*/ '&ov=' + sort + '&mt=' + markettype + '&FAV=' + _fav + '&SL=' + getSelLeague(_selSpt);
    if (ws && ws.readyState == 1 && _postParam.length > 0) {
        ws.send("01"+JSON.stringify( _postParam ));
    }
}
//del
function LinkWSdel(_dbId, url) {
    if (url != null && _dicUrl[_dbId] == null) _dicUrl[_dbId] = url;
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
            _urltm = _urltm.replace('ws://', 'wss://').replace(':8888', ':8887');
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
        console.log("Closed...");
    };
};

var _pingRet ;
var _sendPing = 0;
function pingWebsocket() {
    _sendPing++; if (_sendPing < 9) return; _sendPing = 0;

    if (ws != null) {
        var pv = _pingRet || '0';
        if (pv == '2') {//发送2次都没有应答
            _pingRet = '';
            CreatWS(ws.url);
            GetDataByWS(lastWsParam);
        }
        else if (ws && (ws.readyState == 1)) {
            _pingRet = (pv == '1' || pv == '2' ? '2' : '1');
            ws.send('1');
        } else if (ws && (ws.readyState == 3) || _pingRet != '') {
            CreatWS(ws.url);
            GetDataByWS(lastWsParam);
        }
    }

    //for (var _dbId in _dicTimer) {
    //    var ws = _dicTimer[_dbId];
    //    if (ws != null) {
    //        var pv = _pingRet[_dbId] || '0';
    //        if (pv == '2') {//发送2次都没有应答
    //            _pingRet[_dbId] = '';
    //            LinkWS(_dbId);
    //        }
    //        else if (ws && (ws.readyState == 1)) {
    //            _pingRet[_dbId] = (pv == '1' || pv == '2' ? '2' : '1');
    //            ws.send('1');
    //        } else if (ws && (ws.readyState == 3) || _pingRet[_dbId] != '') {
    //            LinkWS(_dbId);
    //        }
    //    }
    //}
}

function ShowOddsByQuery(_dbId, _sDb) {
    //try {
        var _data;
        if (typeof (_sDb) == "string") {
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
            _data = JSON.parse(_sDb);// eval("(" + _sDb + ")");
        } else {
            _data = _sDb;
        }
        var _$tb = $('#divOdds_' + _dbId);
        if (_data.length == 0) {
            _$tb.empty().remove(); return;
        }
        var _ot = _data[0][2];
        var _$rowMod;
        if (_$tb.length <= 0) {
            _$tb = $('#divOMD').find('.C' + _dbId.replace('_r', '').replace('_t', '').replace('_e', '')).clone();
            _$rowMod = _$tb.find('.gtDbb').clone(); _$tb.find('.gtDbb').remove();
            _$tb.attr('id', 'divOdds_' + _dbId).attr('odbid', _dbId);
            if (_ot == 'r') _$tb.addClass('gtLive'); else _$tb.addClass('gtToday');
            _dicGtT[_dbId] = [_$tb.attr('gtType'), _$tb.find('col').length];
            if (_ot == 'r') $('#oddsTable').prepend(_$tb); else $('#oddsTable').append(_$tb);
            if (_ot == 'r') {
                _$tb.find("#types").html('<span id="txtRunning">' + RES.Running + (isfav == true ? ' - ' + $('#sportList li[sport="' + _dbId.split('_')[0] + '"] .sportName').html() : '') + '</span>');
            }
            if (_ot == 't') {
                _$tb.find("#types").html('<span id="txtRunning">' + RES.Today + (isfav == true ? ' - ' + $('#sportList li[sport="' + _dbId.split('_')[0] + '"] .sportName').html() : '') + '</span>');
            }
            if (_ot == 'e') {
                _$tb.find("#types").html('<span id="txtRunning">' + RES.Early + (isfav == true ? ' - ' + $('#sportList li[sport="' + _dbId.split('_')[0] + '"] .sportName').html() : '') + '</span>');
            }
            if ((_ot == 'r' || $('#oddsTable').attr("isload") == "0") && $("#FixoddsTableHeader").length > 0) {
                $("#FixoddsTableHeader").empty().append(_$tb.clone().attr('id', ''));
            }
        } else {
            _$rowMod = $('#divOMD').find('.C' + _dbId.replace('_r', '').replace('_t', '').replace('_e', '') + ' .gtDbb').clone();
        }
        updOddsTB(_dbId, _data, _$tb, _$rowMod);
        if (_$tb.find('tbody.gtDbb:first').length == 0)
            _$tb.hide();
        else _$tb.show();
        _dicLID[_dbId] = _data[0][1];
   // }
   // catch (err) {
    //    console.log(err.message);
    //}
}

function updOddsTB(_dbId, _data, _$tb, _$rowMod) {
    var _ot = _data[0][2];
    if (_data[0][0] == 1) {
        _$tb.find('tbody.gtDbb').remove();
        _dicPMS[_dbId] = _data[0];
        for (var i = 0, len = _data[3].length; i < len; i++) {
            addTBL(_dbId, _data[3][i], true, _$tb, _$rowMod, _data[0]);
        }
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
    $(_$tb).find('tbody.gtDbb').each(function () {
        if ($(this).find('tr.gtRow').length == 0) {
            $(this).remove();
        }
    });
};


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

    //判断是不是点击半场，半场有不有数据
    var _isFHnull = 0;
    if (_dbId.replace('_r', '') == "1_1_3_H" || _dbId.replace('_t', '') == "1_1_3_H" || _dbId.replace('_e', '') == "1_1_3_H") {
        if (_dr.Hdp1_FH == "" && _dr.HOdds_FH == "" && _dr.AOdds_FH == ""
            && _dr.OU1_FH == "" && _dr.OOdds_FH == "" && _dr.UOdds_FH == "") {
            _isFHnull = 1;

        }
    }
    //判断全场是不是有数据
    if (_dbId.replace('_r', '') == "1_1_3_F" || _dbId.replace('_t', '') == "1_1_3_F" || _dbId.replace('_e', '') == "1_1_3_F") {
        if (_dr.Hdp1 == "" && _dr.HOdds == "" && _dr.AOdds == ""
            && _dr.OU1 == "" && _dr.OOdds == "" && _dr.UOdds == "") {
            _isFHnull = 1;

        }
    }
    //记录定位置
    var _$tr = null, _newtr = false;
    var mmmyNum = _IsMMMY ? '1' : '0';
    if (newAll) {
        if (_isFHnull == 0) {
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
    }
    else {
        var _$tb = _$tbL.parent();
        _$tr = _$tb.find("tr.gtRow[oddsid='" + _dr.oddsid + "'][MMMY='" + mmmyNum + "']");
        if (_$tr == null || _$tr.length == 0) {
            _$tr = $(_$rowMod.html());
            _$tr.attr('oddsid', _dr.oddsid).attr('MMMY', mmmyNum);
            if (_$tbL.find("tr.gtRow[oddsid='" + _dr.preoddsid + "']:last").is(":hidden"))
                _$tr.hide();
            _$tbL.append(_$tr);
            _newtr = true;
        }
        if ((_$tr.prevAll('tr[oddsid!="' + _dr.oddsid + '"]:first').attr('oddsid') || _$tr.parent().prev().find('>tr[oddsid]:last').attr('oddsid') || _$tbL.prev().find('>tr[oddsid]:last').attr('oddsid') || '0') != _dr.preoddsid) {
            setRowVal(_$tbL, _$tr, mid, _dr, pm_S, _dbId, _newtr, _IsMMMY);
            var _$TrS = _$tbL.find("tr[oddsid='" + _dr.oddsid + "']");
            if (_dr.preoddsid == '0') {
                if (_IsMMMY == "1") {
                    _$tbL.find('tr.league:first').after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='0']"));
                    _$tbL.find('tr.league:first').after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='1']"));
                } else {
                    _$tbL.find('tr.league:first').after(_$TrS);
                }
                if (_$tb.find('[soclid]:first').length > 0) {
                    _$tb.find('[soclid]:first').before(_$tbL);
                } else { _$tb.append(_$tbL); }
                mergeTbL(_$tbL);
            }
            else {
                var _$pretr = _$tbL.find("tr.gtRow[oddsid='" + _dr.preoddsid + "']:last");
                if (_$pretr.length == 0) {
                    _$pretr = _$tbL.parent().find("tr.gtRow[oddsid='" + _dr.preoddsid + "']:last");
                }
                var _$trMoreBet = _$pretr.nextAll('.moreBetTr[mkey="' + _$pretr.attr('mkey') + '"]');
                if (_$pretr.length == 0) {
                    refData(_dbId); return false;
                } else {
                    if (_$pretr.parent().attr('soclid') == mid) {
                        if (_$pretr.attr('mkey') == _$tr.attr('mkey')) {
                            if (_IsMMMY == "1") {
                                _$pretr.after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='0']"));
                                _$pretr.after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='1']"));
                            } else {
                                _$pretr.after(_$TrS);
                            }
                        } else {
                            if (_$trMoreBet.length > 0) {
                                if (_IsMMMY == "1") {
                                    _$trMoreBet.after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='0']"));
                                    _$trMoreBet.after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='1']"));
                                } else {
                                    _$trMoreBet.after(_$TrS);
                                }
                            } else {
                                if (_IsMMMY == "1") {
                                    _$pretr.after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='0']"));
                                    _$pretr.after(_$tbL.find("tr[oddsid='" + _dr.oddsid + "'][MMMY='1']"));
                                } else {
                                    _$pretr.after(_$TrS);
                                }
                             
                            }
                        }
                        mergeTbL(_$tbL);
                        mergeTbL(_$pretr.parent());
                    } else {
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
                        if (_$trPre.length > 0) {
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
        adjTb(_$tbL, (_dbId == undefined ? false : (_dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_1' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_2' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '666' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3_H' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3_F')));
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
        _$tr.parents("tbody[soclid]:first").find('[mkey="' + mkeysorce + '"]').attr('runscore', _dr.runhomesocre + '-' + _dr.runawayscore);
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
                }
                else if (_oldn < _newn) {
                    _cell.addClass('indicatorUp').removeClass('indicatorDown').attr("chgTms", 0);
                }
                else if (_oldn > _newn) {
                    _cell.addClass('indicatorDown').removeClass('indicatorUp').attr("chgTms", 0);
                }
            }
            _cell.html(_dr[_col]);
        }
    }
    if (_newtr) {
        if (_dbId.replace('_r', '') == "36_23" || _dbId.replace('_t', '') == "36_23" || _dbId.replace('_e', '') == "36_23")
            _$tr.find('.tdLast').append('<div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.png" border="0" style="height: 11px;"><span class="GamesSum" style="position: relative;bottom: 1px;">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum : '') + '</span></a></div>');
        else if (_dbId.replace('_r', '') == "36_10" || _dbId.replace('_t', '') == "36_10" || _dbId.replace('_e', '') == "36_10")
            _$tr.find('.tdLast').append('<div></div>');
        else if (_dbId.replace('_r', '') == "1_17_4" || _dbId.replace('_t', '') == "1_17_4" || _dbId.replace('_e', '') == "1_17_4" || _dbId.replace('_r', '') == "1_17_2" || _dbId.replace('_t', '') == "1_17_2" || _dbId.replace('_e', '') == "1_17_2")
            _$tr.find('.tdLast').append('<div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.png" border="0" style="height: 11px;"><span class="GamesSum" style="position: relative;bottom: 1px;">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum : '') + '</span></a></div>');
        else if (_dbId.replace('_r', '') == "1_17_5" || _dbId.replace('_t', '') == "1_17_5" || _dbId.replace('_e', '') == "1_17_5")
            _$tr.find('.tdLast').append('');
        else
            if (RES.islogin == "True")
                _$tr.find('.tdLast').append('<div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.png" border="0" style="height: 11px;"><span class="GamesSum" style="position: relative;bottom: 1px;">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum : '') + '</span></a></div>');
            else {
                _$tr.find('.tdLast').append('<div><a title="More bets" class="flexible specialC icon-moreExpand smallBtn-text" ;return false;" href="javascript:;" target="_self"><img src="../Img/MoreBets.png" border="0" style="height: 11px;"><span class="GamesSum" style="position: relative;bottom: 1px;">' + (_dr.GamesSum != null || _dr.GamesSum != undefined ? _dr.GamesSum : '') + '</span></a></div>');
                _$tr.find('.lastif').append('<div class="CSTurl">' + _dr.STurl + '</div><div style="margin-top: 0;"><a title="Forecast" href="javascript:GetDetails(&quot;userName=' + RES.logname + '&amp;moduleId=' + _dr.moduleId + '&amp;wDate=' + _dr.wDate + '&amp;homeId=' + _dr.hid + '&amp;awayId=' + _dr.aid + '&amp;isRun=' + _dr.isRun + '&quot;)""><img src="../Img/Forecast.jpg" border="0" style="width:14px"></a></div>');
            }
    }
    else {
        _$tr.find('.tdLast .CSTurl:first').html(_dr.STurl);
    }
    if (_$tr.find('.shoucang').html() == "") {
        _$tr.find('.shoucang').append((getFavStare(_dbId.split('_')[0], mid + '_' + _dr.hid + '_' + _dr.aid) == true) ? '<img fav="1"  style="cursor: pointer;" src="../Img/FavAdd.gif" border="0" align="absmiddle" onclick="SetFavOne(this)"; class="added"/>' : ' <img fav="0" style="cursor: pointer;"  src="../Img/FavOri' + '.gif" border="0" align="absmiddle" onclick="SetFavOne(this)";/>');
    }

    if (_IsMMMY == true)
        _$tr.addClass("altMatch2").removeClass("altMatch");
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
    _timerTip && clearTimeout(_timerTip); _timerTip = null;
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

function showTipScore(_mName, _hName, _aName, _hoScore, _hnScore, _anScore) {
    setTimeout(function () {
        var showTipS = [];
        showTipS.push("<div class=''><table align='center' valign='center'  class='Sorcetable'>");
        showTipS.push("<tr ><td id='ScoreChLeagueName' colspan='3' style='text-align: center'>" + _mName + "</td></tr>");
        showTipS.push("<tr >");
        showTipS.push("<td id='ScoreChHomeName' class='tipsnbright' style='text-align: unset'>" + _hName + "</td>");
        showTipS.push("<td style='width:30px;text-align:center'>-VS-</td><td id='ScoreChAwayName' class='tipsnbleft'>" + _aName + "</td></tr>");
        showTipS.push("<tr>");
        if (_hoScore.split('-')[0] != _hnScore) {
            showTipS.push("<td id='ScoreChHomeG' class='ScoreColor tipsnbright'>" + _hnScore + "</td>");
        } else {
            showTipS.push("<td id='ScoreChHomeG' class='tipsnbright' >" + _hnScore + "</td>");
        }

        showTipS.push("<td style='width:30px;text-align:center'>-</td>");

        if (_hoScore.split('-')[1] != _anScore) {
            showTipS.push("<td id='ScoreChAwayG' class='ScoreColor tipsnbleft' >" + _anScore + "</td></tr>");
        } else {
            showTipS.push("<td id='ScoreChAwayG' class='tipsnbleft'>" + _anScore + "</td></tr>");
        }

        showTipS.push("</table></div>");
        showTip(showTipS.join(""), 3, "#ecf7e4");
    }, 20);

    //return showTipS.join("");
}

function getNumByS(_shtml) {
    var _oldv = null;
    for (var i = 0; i < 5 && isNaN(_shtml); i++) {
        try { _shtml = $(_shtml).html(); } catch (err) { _shtml = null; break; }
    }
    if (_shtml == null || _shtml.replace(' ', '') == '' || isNaN(_shtml)) return -99999;
    else return Number(_shtml);
}
function addTBL(_dbId, _rL, updAll, _$tb, _$rowMod, pm_S) {
    var _$tbL = null, _IsNewL = false;
    if (updAll) { // onclick="refreshDiv(this)" style="float: left;margin-top: 0px;margin-left: 1px;" <img class="divloading" style="position: absolute; top: 23px;left: 378px; display: none;" src="../Img/spinner.gif" border="0" alt="">
        _$tbL = $('<tbody class="gtDbb" soclid="' + _rL[0][0] + '"><tr class="league"  style="cursor:pointer" ><td><b class="bottom"><i class="arrow1 bottom-arrow1"></i><i class="arrow2 bottom-arrow2"></i></b></td><td align="left" nowrap="nowrap" style="border: 0px!important;" colspan="' + (_dicGtT[_dbId][1]) + '" >' + ((_dicGtT[_dbId][0] == 'S1' || _dicGtT[_dbId][0] == 'S2') ? "<span class=\"gticon icon-favorite\">" : "") + '<span ><a href="javascript:;" class="L_NameFav_RMOdds" ><img title="Add All" src="../Img/FavAdd.gif" class="trfavorutes"  border="0" align="absmiddle"></a><div class="leagueName"  style=" width: 800px;"><span style="display: inline - block;float: left;">' + _rL[0][1] + '</span></div></td></tr></tbody> ');
        _$tb.append(_$tbL); _IsNewL = true;
    } else {
        var _$tr = _$tb.find(">tbody[soclid='" + _rL[0][0] + "'] > tr[oddsid='" + _rL[1][0][0] + "']:first");
        if (_$tr.length > 0) _$tbL = _$tr.parents('tbody:first');
        else _$tbL = _$tb.find(">tbody[soclid='" + _rL[0][0] + "']:first");
        if (_$tbL.length == 0) {
            _$tbL = $('<tbody class="gtDbb" soclid="' + _rL[0][0] + '" ><tr class="league" style="cursor:pointer" ><td><b class="bottom"><i class="arrow1 bottom-arrow1"></i><i class="arrow2 bottom-arrow2"></i></b></td><td align="left" nowrap="nowrap" style="border: 0px!important;" colspan="' + (_dicGtT[_dbId][1]) + '" >' + ((_dicGtT[_dbId][0] == 'S1' || _dicGtT[_dbId][0] == 'S2') ? "<span class=\"gticon icon-favorite\">" : "") + '<span ><a href="javascript:;" class="L_NameFav_RMOdds" ><img title="Add All" src="../Img/FavAdd.gif" class="trfavorutes"  border="0" align="absmiddle"></a><div class="leagueName"  style=" width: 800px;"><span style="display: inline - block;float: left;">' + _rL[0][1] + '</span></div></td></tr></tbody> ');
            _$tb.append(_$tbL); _IsNewL = true;
        }
    }
    for (var i = 0, len = _rL[1].length; i < len; i++) {
        if (_dicGtT[_dbId][0] == 'S18')
            var _dr = ShowOddsFn[_dicGtT[_dbId][0]](_rL[1][i], _rL[0][0], _rL[0][1], _$tbL, _IsNewL, pm_S, false, _dbId, _$rowMod);
        else
            var _dr = ShowOddsFn[_dicGtT[_dbId][0]](_rL[1][i], _rL[0][0], _rL[0][1], _$tbL, _IsNewL, pm_S);
        _$tbL = setRowTr(_$rowMod, _$tbL, _rL[0][0], _dr, pm_S, updAll, _dbId, false);
        if (_$tbL === false) return;
        setDtM(_dbId, pm_S[2], _rL[1][i]);
    }
    adjTb(_$tbL, (_dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_1' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_2' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '666' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3_H' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3_F'));
    if (RES.islogin == "True") {
        _$tbL.find(".leagueName").removeAttr("onclick");
        _$tbL.find(".refreshlogo").removeAttr("onclick");
    }
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
    //setTimeout(function () { 
    var _oddsid = _ruM[0];
    var _$trM = _$tb.find("tbody[soclid] > tr[oddsid='" + _oddsid + "']");
    if (_$trM.length > 0) {
        var _$tbL = _$trM.parents("tbody[soclid]:first");
        var _LId = _$tbL.attr('soclid');
        var _LTitle = _$tbL.find('.leagueName:first').html();
        for (var i = 0, len = _ruM[1].length; i < len; i++) {
            _dataM[_dbId][_ot][_oddsid][_ruM[1][i]] = _ruM[2][i];
        }

        var _dr = ShowOddsFn[_dicGtT[_dbId][0]](_dataM[_dbId][_ot][_oddsid], _LId, _LTitle, _$tbL, false, _dicPMS[_dbId]);
        setRowTr(_$rowMod, _$tbL, _LId, _dr, _dicPMS[_dbId], false, _dbId, false);
        return true;
    }
    return false;
    //}, 20)
};

function delM_S(_dbId, _oddsid, _$tb, _ot) {
    var _$trM = _$tb.find("tr.gtRow[oddsid='" + _oddsid + "']:first");
    if (_$trM.length > 0) {
        if ((_dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_1' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_2' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '666' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3_H' || _dbId.replace('_r', '').replace('_t', '').replace('_e', '') == '1_1_3_F')) {
            var _$nextTr = _$trM.nextAll('tr.gtRow[oddsid!="' + _oddsid + '"]:first');
            if (_$trM.attr('mkey') == _$nextTr.attr('mkey') && _$trM.find('td.tdTeam:first').html() != '' && _$nextTr.find('td.tdTeam:first').html() == '') {
                _$nextTr.find('td.tdLive:first').html(_$trM.find('td.tdLive:first').html());
                _$nextTr.find('td.tdshoucang:first').html(_$trM.find('td.tdshoucang:first').html());
                _$nextTr.find('td.tdTeam:first').html(_$trM.find('td.tdTeam:first').html());
                _$nextTr.find('td.tdLast:first').html(_$trM.find('td.tdLast:first').html());
            }
        }
        var _$tbL = _$trM.parents("tbody:first");
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
        mergeTbL(_$tbL);
    }
    delDtM(_dbId, _ot, _oddsid);
}


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
                } else {
                    _ctr.eq(k).find('td.tdTeam:first').empty();
                    _ctr.eq(k).find('td.tdshoucang:first').empty();
                    _ctr.eq(k).find('td.tdLive:first,td.tdLast:first,td.tdshoucang:first').empty();
                }
            }
            snum = i + 1; _isAlt = !_isAlt;
        }
    }
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
    _$tbR.on('click', 'a.icon-moreExpand', function () {
        showMoreBet($(this));
        $('#oddsTable').find('a.icon-moreCollapse').removeClass("icon-moreCollapse").addClass('icon-moreExpand');
        $(this).addClass('icon-moreCollapse');
    });
    _$tbR.on('click', 'a.icon-moreCollapse', function () {
        closeMB_R();
    });
    _$tbR.on('click', ' tbody.gtDbb tr.league a.L_NameFav_RMOdds', function () {
        SetFavAll(this, $(this).parents(".gtDbb:first").attr("soclid"));
	});
	

    _tmChgN = setInterval(function () {
        if (_$tbR.length > 0) {
            _$tbR.find(".indicatorDown,.indicatorUp").each(function () {
                _$this = $(this);
                var _tms = _$this.attr("chgTms") || 0;
                _tms++;
                if (_tms > 6) { _$this.removeClass('indicatorDown').removeClass('indicatorUp'); _tms = 0; }
                _$this.attr("chgTms", _tms);
            });

            var _$celltm = _$tbR.find(".liveChg");
            if (_$celltm.length > 0) {
                onPlaySound(ScoreSound);
                _$celltm.removeClass('liveChg');
            }
        }
        if (ACT_UseWebSocket == '1')
            pingWebsocket();
    }, 3000);

    $('#betTxtAmount,#betTxtAmount1').on('keyup', function (event) {
        CountMaxPayout2();
        if (event.keyCode == 13 /*&& isbet*/) {
            //isbet = false;
            submitBet();

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
    var mekymorbet = $("#oddsTable").find("tr[oddsid=" + _data["SocOddsId"] + "]:first").attr('mkey');
    if ($(".moreBetTr[mkey=" + mekymorbet + "]").length > 0) {
        _$cMoreBet = $(".moreBetTr[mkey=" + mekymorbet + "]");
    }
    var _$mbet = _$cMoreBet.find('.moreBetDiv:first');
    if (_$mbet.length <= 0) {
        _$mbet = $('#moreBet').clone(); _$mbet.attr('id', '').addClass('moreBetDiv');
        _$cMoreBet.find('td').empty().append(_$mbet);
    }


    var sooddsmorbet = $("#oddsTable").find("tr[mkey=" + mekymorbet + "]:last").attr('oddsid')
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
            if (_$tdSoc.length == 0) {
                _$tdSoc = $("#moreBet").find("#Socid").clone();
                _$tdSoc.attr("id", "Socid" + (i + 1));
            }
        }

        for (var _col in _datafthdp[i]) {
            _$tdSoc.attr("socid", _datafthdp[i]["SocOddsId"]);
            if (_col == "HDP") {
                _$tdSoc.find(".FHdp1").html(UtilGetDisplayHdp(parseFloat(_datafthdp[i]["HDP"]), _datafthdp[i]["IsHomeGive"], "HDP"));
            }
            if (_col == "HomeOdds" || _col == "AwayOdds") {
                if (_datafthdp[i]["HomeOdds"] != 0 && _datafthdp[i]["AwayOdds"] != 0) {
                    if (_datafthdp[i]["Hdp_visible"]) {
                        _$tdSoc.find(".FHOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "home_par" : "") + "&b=home&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["HomeOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["HomeOdds"]) + "</span>");
                        _$tdSoc.find(".FAOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "away_par" : "") + "&b=away&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["AwayOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["AwayOdds"]) + "</span>");
                    } else {
                        _$tdSoc.find(".FHOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafthdp[i]["HomeOdds"]) + "</span>");
                        _$tdSoc.find(".FAOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafthdp[i]["AwayOdds"]) + "</span>");
                    }
                }
            }

            if (_col == "OU") {
                _$tdSoc.find(".FOU1").html(UtilGetDisplayHdp(parseFloat(_datafthdp[i]["OU"]), _datafthdp[i]["IsHomeGive"], "OU"));
            }
            if (_col == "OverOdds" || _col == "UnderOdds") {
                if (_datafthdp[i]["OverOdds"] != 0 && _datafthdp[i]["UnderOdds"] != 0) {
                    if (_datafthdp[i]["OU_visible"]) {
                        _$tdSoc.find(".FOOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "over_par" : "") + "&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["HomeOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["OverOdds"]) + "</span>");
                        _$tdSoc.find(".FUOdds").html("<span style='cursor:pointer;' class='" + (_datafthdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "under_par" : "") + "&oId=" + _datafthdp[i]["SocOddsId"] + "&odds=" + _datafthdp[i]["AwayOdds"] + "&isRun=" + _datafthdp[i]["IsRun"] + "'); \">" + UtilGetDisplayOdds(_datafthdp[i]["UnderOdds"]) + "</span>");
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
                        _$tdSoc.find(".HHOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["HomeOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=home&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "home_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["HomeOdds"]) + "</span>");
                        _$tdSoc.find(".HAOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["AwayOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=away&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "away_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["AwayOdds"]) + "</span>");
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
                        _$tdSoc.find(".HOOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=over&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "under_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["OverOdds"]) + "</span>");
                        _$tdSoc.find(".HUOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "' onclick=\"showBetBox('JRecPanel.aspx?gt=s&b=under&par_b=" + (_datafthdp[i]["HasPar"] == "True" ? "over_par" : "") + "&isFH=true&oId=" + Socid + "&oId_fh=" + _datafhhdp[i]["SocOddsId"] + (_datafhhdp[i]["IsRun"] ? "&isRun=true" : "") + "'); \">" + UtilGetDisplayOdds(_datafhhdp[i]["UnderOdds"]) + "</span>");
                    } else {
                        _$tdSoc.find(".HOOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["OverOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["OverOdds"]) + "</span>");
                        _$tdSoc.find(".HUOdds").html("<span style='cursor:pointer;' class='" + (_datafhhdp[i]["UnderOdds"] < 0 ? "MB_NegOdds" : "MB_PosOdds") + "'>" + UtilGetDisplayOdds(_datafhhdp[i]["UnderOdds"]) + "</span>");
                    }
                }
            }
        }
    }
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
    _iTm = _iTm || 10;
    var _$cMoreBet = $('#cMoreBet');
    _timerMoreBet && clearTimeout(_timerMoreBet); _timerMoreBet = null;
    if (_$cMoreBet.length > 0) {
        var _Oid = _$cMoreBet.attr('oddsid');
        if (_Oid) {
            jQuery.ajax({
                async: true, cache: false, url: wroot + 'pgajaxS.axd?T=MB3&oId=' + _Oid + '&r=' + Date.now(), complete: function (_ort) {
                    var _$trMoreBet = $('#oddsTable').find('tr.moreBetTr[mkey="' + _$cMoreBet.attr('mkey') + '"]:first');
                    _ort.statusText != "error" && boundMoreBet(_ort.responseText, _Oid);

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
    ShowMB_R($('#oddsTable').find('tr.moreBetTr').find('.tdLast .icon-moreCollapse,.tdLast .icon-moreExpand'), '', '', '');
    $('#oddsTable').find('a.icon-moreCollapse').removeClass("icon-moreCollapse").addClass('icon-moreExpand');
}
function ShowMB_R(_$this) {
    showMoreBet(_$this);
}
var mBetOddsId = '', mBetOddsLId = '';
function showMoreBet(_$this, _oId, _mkey, _LId) {
    var _$tr = _$this.parents('tr[oddsid]:first');
    var _$tb = _$tr.parents('table.gtTb:first');
    var _$cMoreBet = $('#cMoreBet');
    if (_$cMoreBet.length == 0) {
        _$cMoreBet = $('<div id="cMoreBet" style="display:none;" oddsid="" mkey="" LId=""></div>');
        $('#oddsTable').append(_$cMoreBet);
    }
    var _needCopy = false;
    if (_$cMoreBet.attr('mkey') != _$tr.attr('mkey')) {
        $('#oddsTable').find('tr.moreBetTr').remove();
        _$cMoreBet.attr('oddsid', '');
    } else {
        _needCopy = true;
    }
    if (_oId || _oId === '') {
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
        //var mekymorbet = $("#oddsTable").find("tr[oddsid=" + _data["SocOddsId"] + "]:first").attr('mkey');
        //if ($(".moreBetTr[mkey=" + mekymorbet + "]").length > 0) {
        //    _$cMoreBet = $(".moreBetTr[mkey=" + mekymorbet + "]");
        //}
        //_$trMoreBet.find('>td').html(_$cMoreBet.html());
    }
    ajaxMoreBet(10);
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
    if (odds < 1) return '';
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



/*----------------------------------- Odds Function END -----------------------------------*/

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

function isInteger(obj) {
    return typeof parseFloat(obj) === 'number' && parseFloat(obj) % 0.5 === 0
}

function getBetInfo(_rows) {
    var _sb = [];
    for (var i = 0, leni = _rows.length; i < leni; i++) {
        var j = (i + 1) < 10 ? "0" + (i + 1) : (i + 1);
        var _dr = _rows[i];
        _sb.push('<table border="0" cellspacing="0" cellpadding="10" width="230px" id="tabtouzhu">');
        //style = "' + (_dr[17].replace(/^\s+|\s+$/g, "") == "D" ? "background: #E9FBDB; " : "") + '"
        _sb.push(' <tbody><tr><td><table border="0" cellspacing="0" cellpadding="0" width="100%" background="' + (_dr[17].replace(/^\s+|\s+$/g, "") == "D" ? "../Img/dangerBlink.gif" : "") + '" ><tbody> <tr>');
        _sb.push('<td class="Normal" valign="bottom" colspan="2"><span class="TipsNO">' + j + '</span>');
        if (_dr[2] == 'PAR') {
            _sb.push('<font size="1">' + _dr[3] + '&nbsp;' + _dr[15] + '</font><br /><b>' + _dr[8] + '</b><a href="javascript:GetPamTrans(\'userName=' + RES.logname + '&amp;id=' + _dr[0] + '\');" class="bold">&nbsp;<span class="gbParlay">(' + RES.Transactions + '...)</span></a><br /> <b>' + _dr[13] + '</b><br /><font size="1">' + _dr[20] + '</font><br />');
        }
        if (_dr[2] == 'DC') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font><br />' + _dr[8] + " v " + _dr[9] + '<br /><span class="gbGive">' + _dr[3] + '<br />' + _dr[12] + '</span> <b>&nbsp;&nbsp;' + _dr[13] + '&nbsp;&nbsp;' + _dr[14] + '</b><br />');
        }
        if (_dr[2] == 'CSR') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font><br />' + _dr[8] + " v " + _dr[9] + '<br />' + _dr[12] + '&nbsp;<b>' + _dr[13] + ' &nbsp;&nbsp;' + _dr[14] + '</b><br />');
        }
        if (_dr[2] == '1') {
            if (_dr[19] != "O")
                _sb.push('<font size="1">' + _dr[7] + '<br />' + (_dr[19] == "O" ? RES.OUTRIGHT2 : _dr[3]) + '&nbsp;' + _dr[15] + '</font><br />' + (_dr[19] == "O" ? "" : (_dr[4] == "O" ? "" : _dr[8] + " v " + _dr[9])) + '<br />');
            _sb.push('<span class="' + (_dr[11] != "1" ? "gbGive" : "gbTake2") + '">' + (_dr[8]) + '&nbsp;(' + RES.WIN2 + ')</span> <b>&nbsp;' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br />');
        }
        if (_dr[2] == 'X') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font><br />' + (_dr[4] == "O" ? "" : _dr[8] + " v " + _dr[9] )+ '<br />');
            _sb.push('<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + _dr[8] + '&nbsp;(' + RES.DRAW2 + ')</span><b>&nbsp;' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br />');
        }
        if (_dr[2] == '2') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font><br />' + (_dr[4] == "O" ? "" : _dr[8] + " v " + _dr[9] )+ '<br />');
            _sb.push('<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + (_dr[9]) + '&nbsp;(' + RES.WIN2 + ')</span><b>&nbsp;' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br />');
        }
        if (_dr[2] == 'OE') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '&nbsp;' + _dr[15] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br /><span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + _dr[4] + '</span> <b>' + _dr[13] + '&nbsp;&nbsp;<span class="">(' + _dr[14] + ')</span></b><br />');
        }
        if (_dr[2] == '1DT' || _dr[2] == '2DT' || _dr[2] == "3D") {
            _sb.push(_dr[2] + ',' + _dr[7] + ',' + _dr[6].substr(0, 5) + '<br />' + _dr[12] + '<br />');
        }
        if (_dr[2] == 'FLG') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + (_dr[11] != "1" ? "gbGive" : "gbTake2") + '">' + _dr[12] + '</span> <b>&nbsp' + _dr[13] + '<span class="">' + _dr[14] + '</span></b><br />');
        }
        if (_dr[2] == 'OU') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[15] + '' + _dr[3] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' +
                _dr[4] + '</span> <b>(' + (_dr[21] == "False" || _dr[21] == "0" || _dr[21] == "false" ? "" : '<span style="color:red">' + _dr[18] + '</span>&nbsp;@') + (isInteger(_dr[12]) == true ? + _dr[12] + ')' : + parseFloat(_dr[12]) + ')') + '&nbsp;' + _dr[13] + '<span class="">(' + _dr[14] + ')</span></b><br />');
        }
        if (_dr[2] == 'HDP') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[15] + '' + _dr[3] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br />' +
                (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + ((_dr[10] == _dr[11]) ? "gbGive" : "gbTake2") + '">' +
                (_dr[11] == "1" ? _dr[8] : _dr[9]) + '</span> <b>(' + (_dr[21] == "False" || _dr[21] == "0" || _dr[21] == "false" ? "&nbsp;" : '<span style="color:red">' + _dr[18] + '</span>&nbsp;@') + (_dr[12].indexOf("-") != -1 ? '<span style="color:red">' + _dr[12] + '</span>)' : + _dr[12] + ')') + '&nbsp;&nbsp' + _dr[13] + '<span class="Negative"></span>&nbsp;(' + _dr[14] + ')</b><br />');
        }
        if (_dr[2] == 'PAM') {
            _sb.push('<font size="1">' + _dr[3] + '&nbsp;' + _dr[15] + '</font><br /><b>' + _dr[8] + '</b><a href=javascript:GetPamTrans(\'userName=' + RES.logname + '&amp;id=' + _dr[0] + '\'); class="bold">&nbsp;<span class="gbParlay">(' + RES.Transactions + '...)</span></a><br /> <b>' + _dr[13] + '</b><br /><font size="1">' + _dr[20] + '</font><br />');
        }
        if (_dr[2] == 'TG') {
            _sb.push('<font size="1">' + _dr[7] + '<br>' + _dr[3] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br /><span class="gbGive">' + RES.TOTALGOAL2 + '<br>' + _dr[12] + '</span> <b>&nbsp;&nbsp;' + _dr[13] + '</b><br>');
        }
        if (_dr[2] == 'HFT') {
            _sb.push('<font size="1">' + _dr[7] + '<br>' + _dr[3] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br><span class="gbGive">' + _dr[12] + '</span> <b>&nbsp;&nbsp;' + _dr[13] + '</b><br>');
        }
        if (_dr[2] == 'FS1') {
        }
        if (_dr[2] == 'MMH') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + ((_dr[11] == _dr[10]) ? "gbGive" : "gbTake2") + '">' +
                (_dr[11] == "1" ? _dr[8] : _dr[9]) + '</span> <b>(<span class="' + ((_dr[11] == _dr[10]) ? "gbGive" : "") + '">' + _dr[12].replace("+", "").replace("@", "").replace(/(^\s*)|(\s*$)/g, "")+"</span>"+ (_dr[21] == "False" || _dr[21] == "0" || _dr[21] == "false" ? ")&nbsp;" : ' @<span style="color:red"> ' + _dr[18] + "</span>)&nbsp;&nbsp;") + '<span class="Negative">' + _dr[14] + '</span>&nbsp;' + (_dr[13] / 10.00).toFixed(2) + '</b><br />');
        }
        if (_dr[2] == 'MMO') {
            _sb.push('<font size="1">' + _dr[7] + '<br />' + _dr[3] + '</font><br />' + _dr[8] + ' v ' + _dr[9] + '<br />' + (_dr[2] == "ou" ? "../Img/dangerBlink.gif" : "") + '<span class="' + (_dr[11] == "1" ? "gbGive" : "gbTake2") + '">' + _dr[4] + '</span> <b>(' + _dr[12].replace("+", "").replace("@", "").replace(/(^\s*)|(\s*$)/g, "") + (_dr[21] == "False" || _dr[21] == "0" || _dr[21] == "false" ? ")&nbsp;" : '@<span style="color:red"> ' + _dr[18] + "</span>)&nbsp;&nbsp;") + '' + UtilGetDisplayMMPcts(_dr[13]) + '<span class="Negative">' + _dr[14] + '</span>&nbsp;</b><br />');
        }
        _sb.push('<font size="1">' + _dr[5] + '&nbsp;(' + _dr[6] + ')</font></td></tr>');
        _sb.push('<tr class=' + (_dr[23] == "0" && (RES.IsTestACC == "true" || RES.IsTestACC == "1" || RES.IsTestACC == "True") ? "" : "hide") + '><td class="bold" valign="bottom" colspan="2"  align="right" style="color:black"><span class="Normal">' + RES.Cashout + ':</span><span style="cursor: pointer" onclick="showEdit(' + _dr[0] + ',' + _dr[25] + ',' + _dr[26] + ',' + _dr[24] + ',\'' + _dr[27] + '\')" class="' + (Number(_dr[24]) <0 ? "gbGive" : "gbTake2") + '">' + Number(_dr[24]).toFixed(2) + '</span></td></tr>');
        _sb.push('<tr><td class="Normal" valign="bottom">');
        if (_dr[22].replace(/^\s+|\s+$/g, "") != '' && _dr[22].replace(/^\s+|\s+$/g, "") != _dr[17].replace(/^\s+|\s+$/g, "")) {
            _sb.push('<span class="Waiting">' + RES.Waiting2 + '</span>&nbsp;<span class="Bold">/</span>&nbsp;');
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
    if (undefined == _sDb || null == _sDb || 0 == _sDb.length) {
        return;
    }
    var _bets = '', _waiting = '', _void = '';
    if (_sDb != "ErRor") {
        $("#tvhref").attr('src', '');
        var _data = eval("(" + _sDb + ")");
        if (_data) {
            _bets = getBetInfo(_data[0]);
            _waiting = getBetInfo(_data[1]);
            _void = getBetInfo(_data[2]);
        }
    }
    var _$Content = $('#tableStake');
    _$Content.find('.betList-bets').empty().html(_bets);
}



function readStake() {
    if (!_isBettimer && $('#pBetList').is(":visible")) {
        $("#divloading").hide();
        //hideWrapBet();
        //$("#dhshowbet").css('visibility', 'hidden');
        $("#tvhref").attr('src', '');
        //$('#oddsTable .bingobet').removeClass('bingobet');
        _isBettimer = true;
        jQuery.ajax({
            async: true, cache: false, url: wroot + 'Bet/BetStake.ashx?type=W0&t=' + Date.now(), complete: function (_ort) {
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
    $("#divloading").hide();
    if (_msg == "NoLogin") {
        window.open(wroot + 'Signout.aspx', '_top');
        alert('Login information invalid');
        return;
    }
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
        updBetOdds();
    }
    else if (_msg.split("|")[0] == "NA") {
        $('#lblStatusBet,#lblStatusBet1').html('<span class="Error">' + RES.Oddsisnotavailable + "!</span>");
        $('#betTxtAmount,#betTxtAmount1')[0].disabled = true;
        $("#submitBetT").hide();
        //$('#betBtnBet').hide();
    }
    else {
        var str = _msg.split("|");
        var msg = "";
        var rt = "";
        var param = "";
        var refresh = "";
        if (str.length == 6) {
            var msg = str[0];
            var rt = str[1];
            var param = str[2];
            var refresh = str[3];
            var amt = str[4];
            _traid = str[5];
            $("#betTxtAmount,#betTxtAmount1").val("");
            closeBetBox();
            $("#panSports").show();
            $("#socBetOdds,#socBetOdds1").empty();
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
        }
        else if (str.length == 2) {
            var type = str[0];
            var msg = str[1];
            showHint("<span class='Error'>" + msg + "</span>");
        }
        else {
            var _errMsg = str[0];
            showHint("<span class='Error'>" + _errMsg + "</span>");
            return;
        }
    }

    if (_msg.status != 200) {
        readStake();
        hideWrapBet();
    }
}
function showHint(_msg) {
    if (_msg) {
        $('#lblStatusBet').html(_msg);
        $('#lblStatusBet1').html(_msg);
        $("#tbBetBox").show();
        $("#panSports").hide();
        return;
    } else {
        $('#lblStatusBet').html('');
        $('#lblStatusBet1').html('');
    }
}
var  TmallowSubBet=null;
function submitBet() {
    if (TmallowSubBet != null ) { $("#divloading").hide();   return; }
    TmallowSubBet = setTimeout(function () {  TmallowSubBet && clearTimeout(TmallowSubBet); TmallowSubBet = null;  }, 1500); 

    //$("#submitBetT").css("pointer-events", "none");
    //$("#submitBetT1").css("pointer-events", "none");
    $("#divloading").show();
    //$('#submitBetT').unbind('click', submitBet);
    //$('#submitBetT1').unbind('click', submitBet);
    showHint();
    if ($("#tbBetBox").is(':hidden'))/*&& $('#dhshowbet').css("visibility") == "hidden")*/ return;
    maxLimit = bMaxLimit || parseFloat($('#betMaxLimit').val());
    var bminAmt = Number($("#betMinLimit").html().replace(/\$|\,/g, ''));
    var bAmt = "";
    if ($('#pBetList').is(":visible")) {
        bAmt = Number($("#betTxtAmount1").val().replace(/\$|\,/g, ''));
    } else {
        bAmt = Number($("#betTxtAmount").val().replace(/\$|\,/g, ''));
    }

    if (parseFloat(bAmt) > maxLimit) {
        //$("#submitBetT").css("pointer-events", "unset");
        //$("#submitBetT1").css("pointer-events", "unset");
        $("#divloading").hide();
        //$('#submitBetT').bind('click', submitBet);
        //$('#submitBetT1').bind('click', submitBet);
        //isbet = true;
        showHint("<span class='Error'>Transaction limit exceeded!!!</span>");
        return;
    }
    if ((bAmt).length == 0) {
        $("#divloading").hide();
        //isbet = true;
        //$('#submitBetT').bind('click', submitBet);
        //$('#submitBetT1').bind('click', submitBet);
        //$("#submitBetT").css("pointer-events", "unset");
        //$("#submitBetT1").css("pointer-events", "unset");
        showHint("Please enter an amount!");
        if ($('#pBetList').is(":visible")) {
            $('#betTxtAmount1').focus();
        }
        else {
            $('#betTxtAmount').focus();
        }

        return;
    }
    if (bAmt < bminAmt) {
        $("#divloading").hide();
        //isbet = true;
        //$("#submitBetT").css("pointer-events", "unset");
        //$("#submitBetT1").css("pointer-events", "unset");
        //$('#submitBetT').bind('click', submitBet);
        //$('#submitBetT1').bind('click', submitBet);
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
}
function ajaxBet(_url, _betval) {
    jQuery.ajax({
        async: true, cache: false, url: wroot + 'Bet/' + _url, complete: function (_ort) {
            showBetStatus(_betval, _ort.status && _ort.status == 200 || _ort.statusText == "OK" || _ort.statusText == "success" ? _ort.responseText : 'err');
            $("#divloading").hide();
            //setTimeout(function () {
            //$('#submitBetT').bind('click', submitBet);
            //$('#submitBetT1').bind('click', submitBet);
            //$("#submitBetT").css("pointer-events", "unset");
            //$("#submitBetT1").css("pointer-events", "unset");
            //    isbet = true;
            //}, 1500)

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
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}

//新写的odd
var panelUrl = "";
var bUrl = "";
var bMaxLimit = "";
var bExRate = "";

//下注信息填充
function showBet(_gt, _b, _g, _oId, _oId_fh, _sc, _mkey, _TVurl) {
    if (_oId!=null) _SBet = { gt: _gt, b: _b, g: _g, oId: _oId, oId_fh: _oId_fh, sc: _sc, TVurl: _TVurl};

    betmode =  _betLen <= 1 ? 'S' : 'P';
    if (betmode == 'S') {
        if (_oId==null) {
            for (var _key in _betList) {
                _gt = _betList[_key].gt; _b = _betList[_key].b; _g = _betList[_key].g; _oId = _betList[_key].oId; _oId_fh = _betList[_key].oId_fh; _sc = _betList[_key].sc; /*_L = _betList[_key].L; _H = _betList[_key].H; _A = _betList[_key].A;*/ _TVurl = _betList[_key].TVurl;
                _mkey = _key;
                _TVurl = _betList[_key].TVurl;
                break;
            }
        }
        var _betval = _gt + '|' + _b + '|' + _g + '|' + _oId + '|' + _oId_fh + '|' + _sc;
        $("#bettable1").attr('betid', _mkey).attr('betval', _betval);//.find('>.list-group-item-heading>i').attr('class', _SPCS);
        $("#tbBetBox").attr("height", "391px");
        $("#bettable2,#bettable2_1").hide();
        $("#notgg").hide();
        $("#mixtitle1").hide();
        $("#dzbet1").show();
        $("#bettable1").show();
        $("#tableParlay").hide();
        $("#betTxtAmount,#betTxtAmount1").val("");

        if (_TVurl != "" && _TVurl != undefined && _TVurl != '0') {
            $("#tvhref").attr('src', _TVurl);
            $("#TVFrame").show()
        } else {
            $("#tvhref").attr('src', '');
            $("#TVFrame").hide();
        }
    }
    else if (betmode == 'P') {
        $("#betGTitl,#betGTitle1").empty().html(RES.Parlay);
        $("#tbBetBox").removeAttr("height");
        $("#bettable2,#bettable2_1").show();
        $("#tvhref").attr('src', '');
        $("#TVFrame").hide();
        $("#notgg").show();
        $("#mixtitle1").show();
        $("#dzbet1").hide();
        $("#bettable1").hide();
        _$betMD = $("#ParClone");
        var _len = 0;
        var _$betPar = $("#betPar"), _$betPar1 = $("#betPar1");
        for (var _key in _betList) {
            _len++;
            var _betval = _betList[_key].gt + '|' + _betList[_key].b + (betmode == 'P' ? '_par' : '') + '|' + _betList[_key].g + '|' + _betList[_key].oId + '|' + _betList[_key].oId_fh + '|' + _betList[_key].sc;
            if (_$betPar.find("table.GridItem[betid = " + _key + "]:first").length == 0) {
                var _$betItem = _$betMD.find('table.GridItem:first').clone().attr('betid', _key).attr('betval', _betval).attr('id', '').removeAttr('id');
                _$betPar.append(_$betItem);
            }
            else {
                _$betPar.find("table.GridItem[betid = " + _key + "]:first").attr('betval', _betval);
            }
            if (_$betPar1.find("table.GridItem[betid = " + _key + "]:first").length == 0) {
                var _$betItem = _$betMD.find('table.GridItem:first').clone().attr('betid', _key).attr('betval', _betval).attr('id', '').removeAttr('id');
                _$betPar1.append(_$betItem);
            }
            else {
                _$betPar1.find("table.GridItem[betid = " + _key + "]:first").attr('betval', _betval);
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
        $("#lstMultiPar2,#lstMultiPar2_1").show();
        $("#tableParlay").show();
        $("#lstMultiPar2,#lstMultiPar2_1").html(strMPar);
    } else {
        $(".betParOdds").hide();
        $("#lstMultiPar2,#lstMultiPar2_1").hide();
    }

    //return strMPar;
}

function removePar(_this) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    e.stopPropagation && e.stopPropagation(); e.cancelBubble = true;
    var _$this = $(_this) || $(e.target);
    var btid = _$this.parents("table[betid]:first").attr('betid');
    $('#oddsTable .oddsBetWrap > .divWrapBet table[betid="' + btid + '"]').remove();
    $('#betPar table[betid="' + btid + '"]').remove();

    //$("#bettable2 table[betid = " + btid + "]").each(function () {
    //    $(this).remove();
    //});
    //$("#bettable2_1 table[betid = " + btid + "]").each(function () {
    //    $(this).remove();
    //});
    //_$this.parents("table[betid]").attr('betid', function (_i, _val) {
    betList(false, btid);
    if (_betLen == 1) { showBet(); }
    else if (_betLen > 1) {
        LoadLstMultiPar2(_betLen, 1); updBetOdds();
    }
    //}).remove();

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
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num);
}

var _timeupdBetOdds;
var maxPar2 = 0;
var maxParTicket2 = 0;
function updBetOdds() {
    if ($('#tbBetBox').is(':hidden')) {
        clearTimeout(_timeupdBetOdds); _timeupdBetOdds = null;
        return;
    }
    //var _$betTick;
    var _arrTm = [];
    //if (!$('#bettable2').is(":visible") && $('#bettable1').is(":visible")) {
    //    _$betTick = $('#bettable1');
    //    _arrTm.push($('#bettable1').attr('betval'));
    //}
    //else if ($('#bettable1').css("display") == "none" && !($('#bettable2').css("display") == "none")) {
    //    _$betTick = $('#betPar,#betPar1');
    //    _$betTick.find('table.GridItem').attr('betval', function (_i, _col) {
    //        _arrTm.push(_col);
    //    });
    //}
    if (_betLen >= 1) {
        for (var _key in _betList) {
            var _betval = _betList[_key].gt + '|' + _betList[_key].b + (betmode == 'P' ? '_par' : '') + '|' + _betList[_key].g + '|' + _betList[_key].oId + '|' + _betList[_key].oId_fh + '|' + _betList[_key].sc;
            _arrTm.push(_betval);
        }
    } else {
        var _betval = _SBet.gt + '|' + _SBet.b + (betmode == 'P' ? '_par' : '') + '|' + _SBet.g + '|' + _SBet.oId + '|' + _SBet.oId_fh + '|' + _SBet.sc;
        _arrTm.push(_betval);
    }

    if (_arrTm.length == 0) return;
    _timeupdBetOdds && clearTimeout(_timeupdBetOdds); _timeupdBetOdds = null;
    jQuery.ajax({
        async: true, cache: false, url: wroot+ 'Bet/hBetOdds.ashx?BTMD=' + betmode + '&coupon=' + ($('#lstMultiPar2').val() || '0') + '&BETID=' + _arrTm.join(','), complete: function (_ort) {
            try {
                if (_ort.status && _ort.status == 200 || _ort.statusText == "OK" || _ort.statusText == "success") {
                    var _rows = eval(_ort.responseText); setBetOddsVal( _rows);
                }
            } catch (err) { }
            _timeupdBetOdds = setTimeout(function () {
                updBetOdds();
            }, 1000 * 10);
        }
    });
}

function setBetOddsVal( _rows) {
    //var dancahngtime1;
    //$("#dhshowbet").mouseover(function () {
    //       clearTimeout(dancahngtime1); dancahngtime1 = null;
    //	hidedhshow = false;
    //}).mouseout(function () {
    //	hidedhshow = true;
    //	dancahngtime1 = setTimeout(function () {
    //		if (hidedhshow) {
    //			$('#dhshowbet').css('visibility', 'hidden');
    //               $('#oddsTable .bingobet').removeClass('bingobet');
    //               $("#tvhref").attr('src', '');
    //		}
    //	}, 5000);
    //});
    var _$BetC;
    //单场下注
    if (betmode == 'S') {
    //if ($('#bettable2').css("display") == "none" && !($('#bettable1').css("display") == "none")) {
        var _data = _rows[0][0];
        if (_rows[0].length == 0) {
            closeBetBox();
            hideWrapBet();
            //$("#dhshowbet").css('visibility', 'hidden');
            $("#tvhref").attr('src', '');
            return;
        }
        if (_rows[0].length > 0) {
            //-----------------------------------------------------------------------------------------------------------------------
            //if ($('#oddsTable').find('.bingobet').length >0) {

            //var curleft = $('#oddsTable .bingobet').offset().left;
            //var curtop = $('#oddsTable .bingobet').offset().top + $('#oddsTable .bingobet').outerHeight();
            //var top = $("#fraMain").scrollTop();
            //var wangyekj = document.documentElement.clientHeight;
            //var tkheight = $('#dhshowbet').height();
            //var sfupdown = wangyekj - curtop;
            //var dingw = curleft - 249;
            //if ($('#dhshowbet').css("visibility") == "hidden") {
            //	hidedhshow = false;
            //} else { 
            //	if ($("#oddsTable .bingobet").parents('div').hasClass('moreBetDiv')) {
            //	if (dingw > 842) {
            //		if (tkheight - sfupdown >= 10) {
            //			$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys rgafter2');
            //		} else {
            //			$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys rgafter2');
            //		}
            //	} else if (dingw > 810) {
            //		if (tkheight - sfupdown >= 10) {
            //			$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys rgafter1');
            //		} else {
            //			$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');
            //		}
            //	} else if (dingw > 300) {
            //		if (tkheight - sfupdown >= 10) {
            //			$('#dhshowbet').css('top', curtop + top - 147 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');
            //		} else {
            //			$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
            //		}
            //	} else {
            //		if (tkheight - sfupdown >= 10) {
            //			$('#dhshowbet').css('top', curtop + top - 147 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 262 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('cunzaiwys rgafter rgafter1 rgafter2');
            //		} else {
            //			$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 262 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter rgafter1 rgafter2').addClass('cunzaiwys');
            //		}
            //           }
            //} else {
            //	//if (dingw > 540) 
            //	if (dingw > 842) {
            //		if (tkheight - sfupdown >= 10) {
            //			$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys').removeClass('rgafter2');
            //		} else {
            //			$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys rgafter2');
            //		}
            //	} else if (dingw > 810) {
            //		if (tkheight - sfupdown >= 10) {
            //			$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys').removeClass('rgafter1');
            //		} else {
            //			$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');
            //		}
            //	} else {
            //		if (tkheight - sfupdown >= 10) {
            //			$('#dhshowbet').css('top', curtop + top - 147 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys').removeClass('rgafter');
            //		} else {
            //			$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
            //			$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
            //		}
            //	}
            //	}

            //	}
            //}
            //-----------------------------------------------------------------------------------------------------------------------
            var betType = _data.id.split('|')[1];
            _$BetC = $('#bettable1,#oddsTable .oddsBetWrap > .divWrapBet #dzbet1');

            _$BetC.find("#socModuleTitle,#socModuleTitle1").empty().html(_data.League);
            var _H = '<span class="' + (_data.IsGive == "1" ? "Give" : "Take") + '">' + _data.Home + '</span>';
            var _A = '<span class="' + (_data.IsGive == "1" ? "Take" : "Give") + '">' + _data.Away + '</span>';
            _$BetC.find("#socClsHome,#socClsHome1").empty().html(_H);
            _$BetC.find("#socClsAway,#socClsAway1").empty().html(_A);
            if (betType == "mmhome" || betType == "mmaway" || betType == "mmover" || betType == "mmunder")
                BetHdp = _data.Hdp;
            else BetHdp = _data.Hdp.replace("(", "").replace(")", "");
            if (_data.bTeam == "Home") {
                BETeam = _H;// $("#socHome").html();
            }
            else if (_data.bTeam == "Away") {
                BETeam = _A;// $("#socAway").html();
            }
            if (_data.outright == "true") { gTitle = RES.OUTRIGHT2; gTitle2 = RES.OUTRIGHT2; _$BetC.find("#trPanMatch").hide(); BetHdp = ""; BETeam = "<span class='gbX12'>" + _data.Home + "&nbsp;(" + RES.Win + ")</span>" }
            else {
                _$BetC.find("#trPanMatch").show();
                if (betType == "home" || betType == "away" || betType == "mmhome" || betType == "mmaway") {
                    gTitle = RES.HANDICAP2;
                    gTitle2 = RES.HDP;
                }
                else if (betType == "over" || betType == "under" || betType == "mmover" || betType == "mmunder") {
                    gTitle = RES.OVERUNDER2;
                    gTitle2 = RES.OU2;
                    if (betType == "over" || betType == "mmover") {
                        BETeam = '<span class="GBOver">' + RES.Odds_OVER + '</span>';
                    }
                    else {
                        BETeam = '<span class="GBUnder2" >' + RES.Odds_UNDER + '</span>';
                    }
                }
                else if (betType == "odd" || betType == "even") {
                    gTitle = RES.ODDEVEN2;
                    gTitle2 = RES.ODDEVEN2;
                    BetHdp = "";
                    if (betType == "odd") BETeam = '<span class="GBGive">' + RES.ODD2 + '<span>';
                    else BETeam = '<span class="GBUnder">' + RES.EVEN2 + '<span>';
                }
                else if ((betType == "1" || betType == "X" || betType == "2")) {
                    gTitle = "1X2";
                    gTitle2 = "1X2";
                    BetHdp = "";
                    if (betType == "1") BETeam = '<span class="gbX12">' + _data.Home + '&nbsp;(' + RES.Win + ')</span>';
                    else if (betType == "2") BETeam = '<span class="gbX12">' + _data.Away + '&nbsp;(' + RES.Win + ')</span>';
                    else BETeam = '<span class="gbX12">' + _data.Home + '&nbsp;(' + RES.Draw + ')</span>';
                }
                else if (betType == "cs" || betType == "csr") {
                    gTitle = RES.CORRECTSCORE2; BETeam = "";
                    gTitle2 = RES.CORRECTSCORE2;
                }
                else if (betType == "tg") {
                    gTitle = RES.TOTALGOAL2;
                    gTitle2 = RES.TOTALGOAL2;
                    BETeam = '<span class="GBUnder GBRed" >' + RES.TOTALGOAL2 + '</span>';
                }
                else if (betType == "htft") {
                    gTitle = RES.HALFTIMEFULLTIME2;
                    gTitle2 = RES.HALFTIMEFULLTIME2;
                    BETeam = '<span class="GBUnder GBRed" >' + RES.HALFTIME_FULLTIME + '</span>';
                }
                else if (betType == "fglg") {
                    gTitle = RES.FIRSTGOALLASTGOAL2;
                    gTitle2 = RES.FIRSTGOALLASTGOAL2;
                    var dc = _data.id.split('|')[5];
                    if (dc == "1") BetHdp = "<span class='Take'>" + _data.Home + "(" + RES.LastGoal + ")" + "</span>";
                    else if (dc == "10") BetHdp = "<span class='Take'>" + _data.Home + "(" + RES.FirstGoal + ")" + "</span>";
                    else if (dc == "0") BetHdp = "<span class='Take'>" + RES.NoGoal + "</span>";
                    else if (dc == "2") BetHdp = "<span class='Take'>" + _data.Away + "(" + RES.LastGoal + ")" + "</span>";
                    else if (dc == "20") BetHdp = "<span class='Take'>" + _data.Away + "(" + RES.FirstGoal + ")" + "</span>";
                    BETeam = '<span class="GBUnder GBRed" >' + RES.FIRSTGOALLASTGOAL2 + '</span>';
                }
                else if (betType == "dc") {
                    gTitle = RES.DOUBLECHANCE;
                    gTitle2 = RES.DOUBLECHANCE;
                    BetHdp = _data.bTT.replace("@", "").replace("FT.", "").replace("1H.", "").replace(/(^\s*)|(\s*$)/g, "");
                    BETeam = '<span class="GBUnder GBRed" >' + RES.DOUBLECHANCE + '</span>';
                }
            }
            $("#betGTitle").empty().html(gTitle);
            $("#betGTitle1").empty().html(gTitle2);
            _$BetC.find("#socBetTeam,#socBetTeam1").empty().html(BETeam);
            if (_data.Score != "") {
                _$BetC.find("#Socrelitte").show()
                _$BetC.find("#socIsRun,#socIsRun1").html(_data.Score.split('-')[0]);
                _$BetC.find("#socIsRun2,#socIsRun2_1").html(_data.Score.split('-')[1]);
            }
            else {
                _$BetC.find("#Socrelitte").hide()
                _$BetC.find("#socIsRun").html('');
                _$BetC.find("#socIsRun2").html('');
            }

            if (_$BetC.find("#socBetOdds").html() != "" && _$BetC.find("#bettable1").attr("Odds") != _data.Odds) {
                _$BetC.find("#socBetHdp,#socBetHdp1").html('<span class="boldBg2">' + BetHdp + '</span>');
                _$BetC.find("#socBetOdds,#socBetOdds1").html('<span class="boldBg2">&nbsp;@&nbsp;' + _data.Odds + "</span>");
            }
            else {
                _$BetC.find("#socBetHdp,#socBetHdp1").html(BetHdp)
                _$BetC.find("#socBetOdds,#socBetOdds1").html('&nbsp;@&nbsp;' + _data.Odds);
            }

            $("#betMaxLimit").empty().html(Number(_data.MaxLimit) == 0 ? Number(_data.MaxLimit) : toThousands(Number(_data.MaxLimit)));
            var betHidOdds = (_$BetC.find("#socBetOdds,#socBetOdds1").find("span span").length > 0) ? _$BetC.find("#socBetOdds,#socBetOdds1").find("span span").html().replace("&nbsp;@&nbsp;", "") : ((_$BetC.find("#socBetOdds,#socBetOdds1").find("span ").length > 0) ? _$BetC.find("#socBetOdds,#socBetOdds1").find("span ").html().replace("&nbsp;@&nbsp;", "") : _data.Odds);
            $("#betHidOdds").val((_data.iseu == "1") ? (betHidOdds - 1) : betHidOdds);
            $("#betMinLimit").empty().html(Number(_data.MinLimit) == 0 ? Number(_data.MinLimit) : toThousands(Number(_data.MinLimit)));
            _$BetC.find("#socFullTimeId").empty().html(_data.IsFH);
            if (_data.IsFH != "" && _data.IsFH != undefined) {
                _$BetC.find("#socFullTimeId1").empty().html(RES.FirstHalf3);
            } else {
                _$BetC.find("#socFullTimeId1").empty()
            }
            CountSETAMT(_data.AmtS, _data.Odds, _data.MinLimit);
            $('#txtBetUrl').val(_data.beturl);
            _$BetC.attr('b', _data.id.toString().split('|')[1]).attr('Odds', _data.Odds).attr('Max', _data.MaxLimit).attr('Min', _data.MinLimit);
        }
    }
    //混合下注
    else if (betmode == 'P') {
    //else if ($('#bettable1').css("display") == "none" && !($('#bettable2').css("display") == "none")) {
        _$BetC = $('#tbBetBox #betPar,#oddsTable .oddsBetWrap > .divWrapBet #betPar1');
        _$BetC.find('>table[betval]').attr('st', 'upd');
        //if ($('#oddsTable').find('.bingobet').length > 0) {
        //	var curleft = $('#oddsTable .bingobet').offset().left;
        //	var curtop = $('#oddsTable .bingobet').offset().top + $('#oddsTable .bingobet').outerHeight();
        //	var top = $("#fraMain").scrollTop();
        //	var wangyekj = document.documentElement.clientHeight;
        //	var tkheight = $('#dhshowbet').height();
        //	var sfupdown = wangyekj - curtop;
        //	var dingw = curleft - 249;
        //	if ($('#dhshowbet').css("visibility") == "hidden") {
        //		hidedhshow = false;
        //	} else {
        //		if ($("#oddsTable .bingobet").parents('div').hasClass('moreBetDiv')) {
        //			if (dingw > 842) {
        //				if (sfupdown < tkheight) {
        //					$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys rgafter2');
        //				} else {
        //					$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys rgafter2');
        //				}
        //			} else if (dingw > 810) {
        //				if (sfupdown < tkheight) {
        //					$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys rgafter1');
        //				} else {
        //					$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');
        //				}
        //			} else if (dingw > 300) {
        //				if (sfupdown < tkheight) {
        //					$('#dhshowbet').css('top', curtop + top - 147 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');
        //				} else {
        //					$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
        //				}
        //			} else {
        //				if (sfupdown < tkheight) {
        //					$('#dhshowbet').css('top', curtop + top - 147 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 262 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('cunzaiwys rgafter rgafter1 rgafter2');
        //				} else {
        //					$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 262 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter rgafter1 rgafter2').addClass('cunzaiwys');
        //				}
        //			}
        //		} else {
        //			//if (dingw > 540) 
        //			if (dingw > 842) {
        //				if (sfupdown < tkheight) {
        //					$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys').removeClass('rgafter2');
        //				} else {
        //					$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys rgafter2');
        //				}
        //			} else if (dingw > 810) {
        //				if (sfupdown < tkheight) {
        //					$('#dhshowbet').css('top', curtop + top - 146 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys').removeClass('rgafter1');
        //				} else {
        //					$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');
        //				}
        //			} else {
        //				if (sfupdown < tkheight) {
        //					$('#dhshowbet').css('top', curtop + top - 147 - tkheight - $('#oddsTable .bingobet').outerHeight() + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys').removeClass('rgafter');
        //				} else {
        //					$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
        //					$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
        //				}
        //			}

        //		}
        //	}
        //}


        for (var i = 0, len = _rows[0].length; i < len; i++) {
            var _dr = _rows[0][i], _$betItem = _$BetC.find('>table[betval="' + _dr.id + '"]');
            var _H = '<span class="' + (_dr.IsGive == "1" ? "Give" : "Take") + '">' + _dr.Home + '</span>';
            var _A = '<span class="' + (_dr.IsGive == "1" ? "Take" : "Give") + '">' + _dr.Away + '</span>';
            _$betItem.find('span[dbcol="homeName"]:first').html(_H);
            _$betItem.find('span[dbcol="awayName"]:first').html(_A);
            var betType = _dr.id.split('|')[1]
            _selName = (_dr.bTeam == 'Home' ? _$betItem.find('span[dbcol="homeName"]').html() : (_dr.bTeam == 'Away' ? _$betItem.find('span[dbcol="awayName"]').html() : _dr.bTeam));
            var _IsFH = _dr.IsFH == "" ? "" : _dr.IsFH + "<br>";
            _betHdp = _dr.Hdp.replace("(", "").replace(")", "")
            if (betType == "home_par" || betType == "away_par") {
                _betHdp = _betHdp + "&nbsp;@";
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
                else if (betType == "X_par") {
                    _betType = '<span class="gbX12" >(' + RES.Draw + ')</span>&nbsp;&nbsp';
                }
                else if (betType == "2_par") {
                    _betType = '<span class="gbX12" >(' + RES.Win + ')</span>&nbsp;&nbsp';
                }
            }
            _betScore = _dr.Score != "" ? "<span>(" + _dr.Score + ")<span>" : "";
            var _html = _IsFH + "&nbsp;" + _selName + "&nbsp;&nbsp" + _betType + _betScore + "<br>" + "&nbsp;&nbsp;" + _betHdp + "&nbsp;" + _dr.Odds;
            _$betItem.find(".betName").html(_html);
            _$betItem.find(".Parpicture").html('<img id="btnDelete" src="../Img/panel/btnClose.jpg" width="21px" height="19px" border="0" /></span>');
            $("#betGTitle").empty().html(RES.MixParlay);
            $("#mixtitle1").find("#betGTitle").empty().html(RES.MixParlay);
            _$betItem.attr('st', '');
        }
        var _blUpd = false;
        _$BetC.find('>table[st="upd"]').each(function () {
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
        $('#txtParOdds,#txtParOdds1').html(_rows[1][7].toFixed(2)).attr('burl', _rows[1][4]).attr('ExRate', _rows[1][5]);
        CountSETAMT(_rows[1][6], _rows[1][7].toFixed(2), _rows[1][1]);
        updateMaxParTicket2();
    }
}
function CountSETAMT(AMT, Odds, MinLimit) {
    var betLblMaxPayout = parseFloat(AMT) * parseFloat(Odds) + parseFloat(MinLimit);
    if (AMT != "0") {
        if ($("#betTxtAmount,#betTxtAmount1").val() == AMT || $("#betTxtAmount,#betTxtAmount1").val() == "") {
            $("#betTxtAmount,#betTxtAmount1").val(toThousands(Number(AMT)));
            if ($('#pBetList').is(":visible")) {
                $("#betTxtAmount1").select();
            } else {
                $("#betTxtAmount").select();
            }

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
        if ($("#betTxtAmount,#betTxtAmount1").val() == "") {
            $("#betTxtAmount,#betTxtAmount1").val("");
            if ($('#pBetList').is(":visible")) {
                $("#betTxtAmount1").focus();
            } else {
                $("#betTxtAmount").focus();
            }


        }
    }
    CountMaxPayout2();
}

var _wbhideTime;
function _autoHideBet() {
    _wbhideTime && clearTimeout(_wbhideTime); _wbhideTime = setTimeout(function () {
        if ($('#oddsTable .oddsBetWrap > .divWrapBet:first').attr("_ov") == "0") { hideWrapBet(); }
    }, 5000);
}
function showWrapBet(_$OddsBet) {
    _wbhideTime && clearTimeout(_wbhideTime);
    if (!_$OddsBet.hasClass("oddsBet") || !_$OddsBet.hasClass("ModdsBet")) {
        if (_$OddsBet.parent().hasClass("oddsBet") || _$OddsBet.parent().hasClass("ModdsBet")) _$OddsBet = _$OddsBet.parent();
        else if (_$OddsBet.parent().parent().hasClass("oddsBet") || _$OddsBet.parent().parent().hasClass("ModdsBet")) _$OddsBet = _$OddsBet.parent().parent();
        else if (_$OddsBet.parent().parent().parent().hasClass("oddsBet") || _$OddsBet.parent().parent().parent().hasClass("ModdsBet")) _$OddsBet = _$OddsBet.parent().parent().parent();
    }
    if (_$OddsBet.hasClass("bingobet") || _$OddsBet.parent().hasClass('oddsBetWrap')) return; hideWrapBet();
    _$OddsBet.addClass('bingobet').wrap("<div class='oddsBetWrap'></div>");
    var _$divWrapBet = $('#dhshowbet').clone().attr('id', '').removeAttr('id').attr("tabindex", "0").attr("_ov", "1");
    
    _$divWrapBet.on("click", function (e) { e.stopPropagation(); });
    $('#oddsTable .oddsBetWrap').on("mouseover", function (e) { $('#oddsTable .oddsBetWrap > .divWrapBet:first').attr("_ov", "1"); _wbhideTime && clearTimeout(_wbhideTime); })
        .on("mouseout", function (e) { $('#oddsTable .oddsBetWrap > .divWrapBet:first').attr("_ov", "0"); _autoHideBet(); });
    //_autoHideBet();
    if (_$fraMain.offset().top + _$fraMain.height() - _$OddsBet.offset().top < 200) _$divWrapBet.addClass("showtop");
    else _$divWrapBet.addClass("showbottom");
    if (_$OddsBet.offset().left < 400) _$divWrapBet.addClass("showright");
    else if (_$OddsBet.offset().left > 900) _$divWrapBet.addClass("showleft");
    _$OddsBet.before(_$divWrapBet); _$divWrapBet.show();  
}
function hideWrapBet() {
    $('#oddsTable .oddsBetWrap > .divWrapBet').next().unwrap().removeClass('bingobet').prevAll(".divWrapBet").remove();
}

var betmode = '';
function showBetBoxMB(url) {
    var event = event || window.event || arguments.callee.caller.arguments[0];
    event.cancelBubble = true;
    var _$this = $(event.target);
    showBetBox(url, _$this);
}
function showBetBox(url, _this) {

    var _$this = _this;
    if (_this == undefined || _this == "") {
        var event = event || window.event || arguments.callee.caller.arguments[0];
        event.cancelBubble = true;
        _$this = $(event.target);
    }
    var _tvurl = _$this.parents('td:first').siblings('td.tdTeam').find('div.TVurl img').attr('tvurl');
    var mkeyval, _HomeAway, mkeyvalNew;
    if (_$this.parents(".moreBetTr:first").length > 0) {
        mkeyval = _$this.parents(".moreBetTr:first").prev().attr("mkey");
        _tvurl = _$this.parents(".moreBetTr:first").parents("tbody:first").find(".gtRow[mkey=" + mkeyval + "]:first").find('div.TVurl img').attr('tvurl');
    }
    else if (_$this.parents(".gtRCS:first").length > 0) {
        mkeyval = _$this.parents(".gtRCS:first").parents("tr:first").attr("mkey");
        _tvurl = '';
    }
    else {
        mkeyval = _$this.parents(".gtRow:first").attr("mkey");
        _tvurl = _$this.parents('tbody:first').find(".gtRow[mkey=" + mkeyval + "]:first").find('div.TVurl img').attr('tvurl');
    }

    var b = GetQueryStr('b', url) || '', _haspar = (b == 'home' || b == 'away' || b == 'over' || b == 'under' || b == '1' || b == 'X' || b == '2' || b == 'odd' || b == 'even');
    var _tg = GetQueryStr('tg', url) || ''
    var _MinG = GetQueryStr('MinG', url) || ''
    var _par_b = GetQueryStr('par_b', url) || '', _gt = GetQueryStr('gt', url) || '', _g = GetQueryStr('g', url) || '1', _b = GetQueryStr('b', url) || '', _oId = GetQueryStr('oId', url) || '', _oId_fh = GetQueryStr('oId_fh', url) || '', _sc = GetQueryStr('sc', url) || '';
    if (_oId_fh && GetQueryStr('isFH', url) != 'true') _oId_fh = '';


    ////////////////////////////////////////////////////////////////////
    //var curleft = _$this.offset().left;
    //var curtop = _$this.offset().top + _$this.outerHeight();
    //var top = $("#fraMain").scrollTop();
    //var wangyekj = document.documentElement.clientHeight;
    //var tkheight = $('#dhshowbet').height();
    //var sfupdown = wangyekj - curtop;
    //   var dingw = curleft - 249;
    $('#TVFrame').hide();
    //$('#oddsTable .bingobet').removeClass('bingobet');
    //if ($(_$this).parents('div').eq(0).hasClass('ModdsBet')) {
    //	if ($(_$this).is('.Negative')) {
    //		if (dingw > 842) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys rgafter2');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys  rgafter2');
    //			}
    //		} else if (dingw > 810) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 391 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys rgafter1');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 391 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');

    //			}
    //		} else if (dingw > 300) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 364 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 364 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
    //			}
    //		} else {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 266 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('cunzaiwys rgafter rgafter1 rgafter2');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 266 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter rgafter1 rgafter2').addClass('cunzaiwys');
    //			}
    //		}
    //		$('#dhshowbet').addClass('cgafter');
    //		$(_$this).parents('div').eq(0).addClass('bingobet');

    //	} else {
    //		if (dingw > 842) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys rgafter2');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys rgafter2');
    //			}
    //		} else if (dingw > 810) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys rgafter1');

    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');

    //			}
    //		} else if (dingw > 300) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 147 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');

    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');

    //			}
    //		} else {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 147 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 262 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('cunzaiwys rgafter rgafter1 rgafter2');

    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 262 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter rgafter1 rgafter2').addClass('cunzaiwys');
    //			}
    //		}
    //		$('#dhshowbet').removeClass('cgafter');
    //		$(_$this).parents('div').eq(0).addClass('bingobet');
    //	}
    //} else {
    //	if ($(_$this).is('.Negative')) {

    //		if (dingw > 842) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys rgafter2');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys rgafter2');
    //			}
    //		} else if (dingw > 810) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 394 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys rgafter1');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 394 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');
    //			}
    //		} else if (dingw > 300) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 366 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 366 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
    //			}
    //		} else {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 482 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 482 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
    //			}
    //		}
    //		if ($(_$this).parents("table.C1_4").length>0) {
    //			$(_$this).parent('td').addClass('bingobet');
    //		}
    //		else if ($(_$this).parent('span').hasClass('MB_NegOdds')) {
    //			$(_$this).parents('span').eq(0).addClass('bingobet');
    //		} else {
    //			$(_$this).parents('div').eq(0).addClass('bingobet');
    //		}
    //	} else {
    //		if (dingw > 842) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').removeClass('cunzaiwys rgafter2');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 415 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter1').addClass('cunzaiwys rgafter2');
    //			}
    //		} else if (dingw > 810) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 146 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').removeClass('cunzaiwys rgafter1');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 146 + 'px').css('left', curleft - 388 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter').removeClass('rgafter2').addClass('cunzaiwys rgafter1');
    //			}
    //		} else if (dingw > 300) {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 147 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 360 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
    //			}
    //		} else {
    //			if (tkheight - sfupdown >= 10) {
    //				$('#dhshowbet').css('top', curtop + top - 147 - tkheight - _$this.outerHeight() + 'px').css('left', curleft - 475 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').removeClass('cunzaiwys rgafter');
    //			} else {
    //				$('#dhshowbet').css('top', curtop + top - 147 + 'px').css('left', curleft - 475 + 'px').css('visibility', 'visible');
    //				$('#dhshowbet').removeClass('rgafter1').removeClass('rgafter2').addClass('cunzaiwys rgafter');
    //			}
    //		}
    //		if ($(_$this).parents("table.C1_4").length>0) {
    //			$(_$this).parent('td').addClass('bingobet');
    //		} else if ($(_$this).hasClass('MB_PosOdds')) {
    //			$(_$this).addClass('bingobet');
    //		} else {
    //			$(_$this).parents('div').eq(0).addClass('bingobet');
    //		}
    //	}
    //}
    stacoupon = 1;
    showHint();
    showWrapBet(_$this);
    //球赛和下注的显示和隐藏
    $('#betTxtAmount,#betTxtAmount1')[0].disabled = false;
    $('#submitBetT').show();
    $("#tbBetBox").show();
    $("#tbBetBox").addClass('active');
    $("#panSports").hide();
    $("#betTxtAmount,#betTxtAmount1").val("");
    $('#betChkMaxBet')[0].checked = false;
    if (_par_b == '') {
        _haspar = false;
    }

    mkeyvalNew = mkeyval.split("_")[0] + '_' + mkeyval.split("_")[1] + '_' + mkeyval.split("_")[2];
    //把过关的数据写入
    if (_haspar) {
        $("#PARTipsInTable").hide();
        if (_betLen < 10) {
            betList(true, mkeyvalNew, _gt, _b, _g, _oId, _oId_fh, _sc, _tvurl);
        }
        showBet();
    } else {
        if (_betLen > 1) {
            $("#TipSParlary").addClass("showTipParlary");
            setTimeout(function () {
                $("#TipSParlary").removeClass("showTipParlary");
            }, 2000);
            showBet();
        } else {
            $("#PARTipsInTable").show();
            if (_betLen == 1) {
                betList(false);//清除，这个列表只存可以过关的
            }
            showBet(_gt, _b, _g, _oId, _oId_fh, _sc, mkeyvalNew, _tvurl);
            //$('#TVFrame').show();
        }
    }
    //if (!_haspar || _haspar && _betLen == 1) {
    //    if (_betLen > 0 && !_haspar) {
    //        if (_betList[mkeyvalNew] != "" && _betList[mkeyvalNew] != undefined && _betLen == 1) {
    //            betList(false, mkeyvalNew);
    //            $('#TVFrame').show();
    //        }
    //        else {
    //            $("#TipSParlary").addClass("showTipParlary");
    //            setTimeout(function () {
    //                $("#TipSParlary").removeClass("showTipParlary");
    //            }, 2000)
    //        }
    //    }
    //    if (_betLen == 0 && !_haspar) $("#PARTipsInTable").show();
    //    else $("#PARTipsInTable").hide();
    //    if (_betLen > 1 || (_betLen == 1 && !_haspar)) {showBet(); return; }
    //    showBet(_gt, _b, _g, _oId, _oId_fh, _sc, mkeyvalNew, _tvurl);
    //} else {
    //    showBet();
    //}

}

var _betList = {}, _betLen = 0,_SBet;
function betList(_add, _id, _gt, _b, _g, _oId, _oId_fh, _sc, _TVurl) {
    if (_add) {
        delete _betList[_id];
        _betList[_id] = { gt: _gt, b: _b, g: _g, oId: _oId, oId_fh: _oId_fh, sc: _sc, TVurl: _TVurl };
    } else if (_id == null) {
        _betList = {};
    } else {
        delete _betList[_id];
    }
    var _len = 0;
    for (var _key in _betList) { _len++; } _betLen = _len;
}

var oldindex = '';
function showBetBoxRU(url, idx, b, ot, delay) {
    var event = event || window.event || arguments.callee.caller.arguments[0];
    event.cancelBubble = true;
    var _$this = $(event.target);

    $('#betTxtAmount,#betTxtAmount1')[0].disabled = false;
    $('#submitBetT').show();
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
        waitDo(showBetBox(url, _$this), 5000);
        //setTimeout('showBetBox("' + url + ',' + _$this + '")', 5000);
    }
    else if (delay == 30) {
        waitDo(showBetBox(url, _$this), 7000);
        //setTimeout('showBetBox("' + url + ',' + _$this + '")', 7000);
    }
    else { showBetBox(url, _$this); }

    return false;
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