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
package be.c4j.demo.page;

import be.c4j.demo.AbstractTest;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class LoginPage extends AbstractTest {

    @FindBy(id = "frm:userName")
    private WebElement userNameField;

    @FindBy(id = "frm:password")
    private WebElement passwordField;

    @FindBy(id = "frm:login")
    private WebElement loginButton;

    @FindBy(id = "frm:logout")
    private GrapheneElement logoutLink;

    public void performLogin() {
        showPage("index.xhtml");

        userNameField.sendKeys("rudy");
        passwordField.sendKeys("ydur");

        guardHttp(loginButton).click();

        assertTrue(logoutLink.isDisplayed());


    }
}
