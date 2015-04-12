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
package be.c4j.demo;

import be.c4j.demo.component.PFInputText;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(Arquillian.class)
public class LoginTest extends AbstractTest {

    @FindBy(id = "frm:userName")
    private WebElement userNameField;

    @FindBy(id = "frm:password")
    private WebElement passwordField;

    @FindBy(id = "frm:login")
    private WebElement loginButton;

    @FindBy(id = "frm:logout")
    private GrapheneElement logoutLink;

    @FindBy(id = "frm:userName")
    private PFInputText userNameComponent;

    @Test
    @RunAsClient
    public void testDefault() {
        showPage("index.xhtml");

        userNameField.sendKeys("rudy");
        passwordField.sendKeys("ydur");

        guardHttp(loginButton).click();

        assertTrue(logoutLink.isDisplayed());
    }

    @Test
    @RunAsClient
    public void testError() {
        showPage("index.xhtml");

        userNameField.sendKeys("test");
        passwordField.sendKeys("test");

        guardHttp(loginButton).click();

        assertFalse(logoutLink.isPresent());
    }

    @Test
    @RunAsClient
    public void testError_EmptyFields() {
        showPage("index.xhtml");

        assertFalse(userNameField.getAttribute("class").contains("ui-state-error"));
        guardHttp(loginButton).click();

        assertTrue(userNameField.getAttribute("class").contains("ui-state-error"));
        assertFalse(logoutLink.isPresent());
    }

    @Test
    @RunAsClient
    public void testError_EmptyFields2() {
        showPage("index.xhtml");

        assertFalse(userNameComponent.hasErrorIndication());
        guardHttp(loginButton).click();

        assertTrue(userNameComponent.hasErrorIndication());
        assertFalse(logoutLink.isPresent());
    }


}