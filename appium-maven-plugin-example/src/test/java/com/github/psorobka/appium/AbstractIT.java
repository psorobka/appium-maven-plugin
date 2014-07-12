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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
public abstract class AbstractIT {

    protected final Properties properties = new Properties();
    protected final String projectBuildFinalName;
    protected final String projectBuildDirectory;

    public AbstractIT() {
        InputStream inputStream = null;
        try {
            inputStream = AbstractIT.class.getResourceAsStream("/it.properties");
            if (inputStream == null) {
                throw new RuntimeException("it.properties is null");
            }
            properties.load(inputStream);
            projectBuildDirectory = properties.getProperty("projectBuildDirectory");
            projectBuildFinalName = properties.getProperty("projectBuildFinalName");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties, e");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
