package com.papaya.core.social.qq.connect;

import com.papaya.core.social.qq.api.QQ;
import com.papaya.core.social.qq.api.QQUserinfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserinfo userinfo = api.getUserInfo();
        values.setDisplayName(userinfo.getNickname());//昵称
        values.setImageUrl(userinfo.getFigureurl_1());//头像
        values.setProfileUrl("");//个人主页
        values.setProviderUserId(userinfo.getOpenId());//openId;
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
