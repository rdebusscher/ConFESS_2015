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

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;

/**
 *
 */
@Ignore
public class AbstractTest {

    private static final String ROOT = "http://localhost:63342/html/src/main/webapp/";
    @Drone
    protected WebDriver driver;

    protected void showPage(String page) {
        driver.get(ROOT + page);
    }
}
