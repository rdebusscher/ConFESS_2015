/*
 * Copyright 2014-2015 Rudy De Busscher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package be.c4j.demo.view;

import be.c4j.demo.service.LoginService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private LoginService loginService;

    private String userName;
    private String password;

    private boolean isLoggedOn = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedOn() {
        return isLoggedOn;
    }

    public void doLogin() {
        loginService.checkCredentials(userName, password);
        isLoggedOn = true;
    }

    public void logout() {
        userName = null;
        password = null;
        isLoggedOn = false;
    }
}
