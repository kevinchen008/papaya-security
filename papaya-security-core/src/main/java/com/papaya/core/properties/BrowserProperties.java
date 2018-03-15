package com.papaya.core.properties;

import static com.papaya.core.properties.LoginType.JSON;

public class BrowserProperties {
    private String loginPage="/browser-login.html";

    private String signUpPage="/browser-signup.html";

    private LoginType loginType = JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getSignUpPage() {
        return signUpPage;
    }

    public void setSignUpPage(String signUpPage) {
        this.signUpPage = signUpPage;
    }
}
