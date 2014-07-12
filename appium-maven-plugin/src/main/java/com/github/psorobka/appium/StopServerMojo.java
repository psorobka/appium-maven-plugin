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
import java.io.OutputStream;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
@Mojo(name = "stop", defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST)
public class StopServerMojo extends AbstractMojo {

    private static final char CTRL_C = (char) 3;

    @Parameter(defaultValue = "${session}", readonly = true, required = true)
    private MavenSession session;

    @Override
    public void execute() throws MojoExecutionException {
        OutputStream os = null;
        try {
            getLog().info("Stopping Appium server...");
            Process process = (Process) session.getExecutionProperties().get(StartServerMojo.PROCESS_PROPERTY_NAME);
            os = process.getOutputStream();
            os.write(CTRL_C);
            os.flush();
            process.destroy();
            getLog().info("Appium server stopped");
        } catch (IOException ex) {
            throw new MojoExecutionException("Error while sending Ctrl-C to process", ex);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
