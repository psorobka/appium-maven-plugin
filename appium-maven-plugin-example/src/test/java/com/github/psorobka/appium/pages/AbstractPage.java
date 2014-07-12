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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
public class AbstractPage {

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementIsPresent(String elementId) {
        (new WebDriverWait(driver, 10)).until(presenceOfElementLocated(By.id(elementId)));
    }

    public void waitUntilElementIsPresent(String elementId, int timeOutInSeconds) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(presenceOfElementLocated(By.id(elementId)));
    }

    public void waitUntilElementIsNotPresent(String elementId) {
        waitUntilElementIsNotPresent(elementId, 10);
    }

    public void waitUntilElementIsNotPresent(String elementId, int timeOutInSeconds) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(not(presenceOfElementLocated(By.id(elementId))));
    }

    public WebElement getActionBarTitleTextView() {
        return driver.findElement(By.id("android:id/action_bar_title"));
    }

    public WebElement getHomeImageView() {
        return driver.findElement(By.id("android:id/home"));
    }
}
