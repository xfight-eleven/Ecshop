package com.langt.zjgx.server;

import android.text.TextUtils;
import android.util.Log;

import com.langt.zjgx.base.BaseBean;
import com.langt.zjgx.goods.bean.MyGoodsListBean;
import com.langt.zjgx.login.model.UserLoginBean;
import com.langt.zjgx.mine.model.MyAddrListBean;
import com.langt.zjgx.mine.model.MyCollectListBean;
import com.langt.zjgx.search.model.HotSearchListResultModel;
import com.langt.zjgx.utils.CoreLib;
import com.langt.zjgx.utils.GsonUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class HttpClient {


    public Observable<BaseBean> sendSmsCode(String userId, String userPhone, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "sendSmsCode");
        if (!TextUtils.isEmpty(userId)) {
            params.put("userId", userId);
        }
        params.put("userPhone", userPhone);
        params.put("type", type);
        return getApi().sendSmsCode(toJson(params));
    }

    public Observable<UserLoginBean> userLogin(String userPhone, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "userLogin");
        params.put("userPhone", userPhone);
        params.put("password", password);
        return getApi().userLogin(toJson(params));
    }

    public Observable<UserLoginBean> thirdLogin(String thirdUid, String nickName, String userIcon) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "thirdLogin");
        params.put("thirdUid", thirdUid);
        params.put("nickName", nickName);
        params.put("userIcon", userIcon);
        return getApi().thirdLogin(toJson(params));
    }

    public Observable<BaseBean> forgetPassword(String userPhone, String password, String smsCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "forgetPassword");
        params.put("userPhone", userPhone);
        params.put("password", password);
        params.put("smsCode", smsCode);
        return getApi().forgetPassword(toJson(params));
    }


    public Observable<BaseBean> userRegister(String userPhone, String password, String smsCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "userRegister");
        params.put("userPhone", userPhone);
        params.put("password", password);
        params.put("smsCode", smsCode);
        return getApi().userRegister(toJson(params));
    }


    /**
     * 设置默认地址
     *
     * @param userId
     * @param addrId
     * @return
     */
    public Observable<BaseBean> setDefaultAddr(String userId, String addrId) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "setDefaultAddr");
        params.put("userId", userId);
        params.put("addrId", addrId);
        return getApi().setDefaultAddr(toJson(params));
    }

    /**
     * 我的地址管理
     *
     * @param userId
     * @param nowPage
     * @return
     */
    public Observable<MyAddrListBean> getMyAddrList(String userId, int nowPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "getMyAddrList");
        params.put("userId", userId);
        params.put("nowPage", nowPage);
        return getApi().getMyAddrList(toJson(params));
    }

    /**
     * 我的收藏
     *
     * @param userId
     * @param type
     * @param nowPage
     * @return
     */
    public Observable<MyCollectListBean> getMyCollectList(String userId, int type, int nowPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "getMyCollectList");
        params.put("userId", userId);
        params.put("type", type);
        params.put("nowPage", nowPage);
        return getApi().getMyCollectList(toJson(params));
    }

    /**
     * 意见反馈
     *
     * @param userId
     * @param fdTitle
     * @param fdContent
     * @return
     */
    public Observable<BaseBean> feedBack(String userId, String fdTitle, String fdContent) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "feedBack");
        params.put("userId", userId);
        params.put("fdTitle", fdTitle);
        params.put("fdContent", fdContent);
        return getApi().feedBack(toJson(params));
    }

    /**
     * 2.0 获取热门搜索列表
     *
     * @return
     */
    public Observable<HotSearchListResultModel> getLocationCity(String province, String city) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "getRecomSkeyList");
        params.put("province", province);
        params.put("city", city);
        return getApi().getHotSearchList(toJson(params));
    }

    /**
     * 2.4 获取热门搜索列表
     *
     * @return
     */
    public Observable<HotSearchListResultModel> getHotSearchList() {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "getRecomSkeyList");
        params.put("userId", CoreLib.getUserId());
        return getApi().getHotSearchList(toJson(params));
    }

    /**
     * 2.5 获取商品列表
     * {
     * cmd:"searchGoodsList"
     * userId:"5"   //用户id
     * cityId:""       //城市id
     * lng:""          //地理经度
     * lat:""          //地理纬度
     * searchKey:""    //搜索关键字
     * nowPage:””
     * }
     */
    public Observable<MyGoodsListBean> searchGoodsList(String key, int nowPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("cmd", "searchGoodsList");
        params.put("cityId", CoreLib.getCityId());
        params.put("lng", CoreLib.getLongitude());
        params.put("lat", CoreLib.getLatitude());
        params.put("key", key);
        params.put("nowPage", String.valueOf(nowPage));
        params.put("userId", CoreLib.getUserId());
        return getApi().searchGoodsList(toJson(params));
    }

    private static ApiServer getApi() {
        return ApiRetrofit.getInstance().getApiServer();
    }


    private String toJson(Map<String, Object> params) {
        String json = GsonUtils.toJson(params);
        Log.i("OkHttp", "request======>> " + json);
        return json;
    }
}
