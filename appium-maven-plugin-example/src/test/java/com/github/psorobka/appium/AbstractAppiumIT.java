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
package com.github.psorobka.appium;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import org.openqa.selenium.WebElement;

/**
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
public abstract class AbstractAppiumIT extends AbstractIT {

    protected AndroidDriver<WebElement> driver;
    protected final File app;

    public AbstractAppiumIT() {
        app = new File(projectBuildDirectory + File.separator
                + projectBuildFinalName + ".apk");
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("appPackage", "com.github.psorobka.appium");
        capabilities.setCapability("appActivity", ".AppiumExampleActivity");
        capabilities.setCapability("deviceReadyTimeout", "180");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
