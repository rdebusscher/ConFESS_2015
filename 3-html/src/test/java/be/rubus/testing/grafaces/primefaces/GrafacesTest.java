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
package be.rubus.testing.grafaces.primefaces;

import be.rubus.web.testing.annotation.Grafaces;
import be.rubus.web.testing.widget.AlertHandling;
import be.rubus.web.testing.widget.ButtonWidget;
import be.rubus.web.testing.widget.RadioButtonGroupWidget;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(Arquillian.class)
public class GrafacesTest extends AbstractTest {


    @FindBy(name = "framework")
    private RadioButtonGroupWidget frameworkRadioGroup;

    @FindBy(name = "submit")
    private ButtonWidget submitBtn;

    @Grafaces
    private AlertHandling alertHandling;

    @Test
    @RunAsClient
    public void testDefault() {
        showPage("index.html");

        assertEquals(4, frameworkRadioGroup.getNumberOfButtons());
        frameworkRadioGroup.clickButton(2);
        assertEquals("Vaadin", frameworkRadioGroup.getSelectedValue());

        submitBtn.click();

        try {
            assertEquals("You choose Vaadin", alertHandling.checkForAlert().switchToAlert().getAlertText());
        } finally {
            alertHandling.alertAccept();
        }

    }


}
