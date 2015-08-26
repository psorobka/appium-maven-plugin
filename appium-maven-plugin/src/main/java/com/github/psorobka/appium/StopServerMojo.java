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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.Os;
import org.zeroturnaround.process.PidProcess;
import org.zeroturnaround.process.ProcessUtil;
import org.zeroturnaround.process.Processes;
import org.zeroturnaround.process.WindowsProcess;

/**
 *
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
@Mojo(name = "stop", defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST)
public class StopServerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}", readonly = true, required = true)
    private File target;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            getLog().info("Stopping Appium server...");
            int pid = Integer.parseInt(FileUtils.readFileToString(new File(target, "appium.pid")));
            getLog().debug("Appium server PID " + pid);
            PidProcess process;
            if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                WindowsProcess windowsProcess = (WindowsProcess) Processes.newPidProcess(pid);
                windowsProcess.setIncludeChildren(true);
                process = windowsProcess;
            } else {
                process = Processes.newPidProcess(pid);
            }
            if (!process.isAlive()) {
                throw new MojoExecutionException("Appium server is not running");
            }
            ProcessUtil.destroyGracefullyOrForcefullyAndWait(process, 30, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);
            if (process.isAlive()) {
                throw new MojoExecutionException("Failed to stop Appium server");
            }
            getLog().info("Appium server stopped");
        } catch (InterruptedException | TimeoutException | IOException ex) {
            throw new MojoExecutionException("Failed to stop Appium server", ex);
        }
    }
}
