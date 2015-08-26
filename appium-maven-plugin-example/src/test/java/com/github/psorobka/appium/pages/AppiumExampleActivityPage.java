/*
 * Copyright © 2014 Piotr Soróbka <psorobka@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.psorobka.appium.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
public class AppiumExampleActivityPage extends AbstractPage {

    public static final String NAME_ID = "com.github.psorobka.appium:id/name";
    public static final String BUTTON_ID = "com.github.psorobka.appium:id/button";

    public AppiumExampleActivityPage(AndroidDriver driver) {
        super(driver);
        waitUntilElementIsPresent(NAME_ID);
    }

    public AppiumExampleActivityPage setName(String name) {
        WebElement element = driver.findElement(By.id(NAME_ID));
        element.sendKeys(name);
        return this;
    }

    public SayHelloDialog clickSayHelloButton() {
        WebElement element = driver.findElement(By.id(BUTTON_ID));
        element.click();
        return new SayHelloDialog(driver);
    }

    public class SayHelloDialog extends AbstractPage {

        public static final String SAY_HELLO_ID = "android:id/message";
        public static final String OK_BUTTON_ID = "android:id/button1";

        public SayHelloDialog(AndroidDriver driver) {
            super(driver);
            waitUntilElementIsPresent(SAY_HELLO_ID);
        }

        public String getSayHello() {
            WebElement element = driver.findElement(By.id(SAY_HELLO_ID));
            return element.getText();
        }

        public AppiumExampleActivityPage clickOkButton() {
            WebElement element = driver.findElement(By.id(OK_BUTTON_ID));
            element.click();
            return new AppiumExampleActivityPage(driver);
        }
    }
}
