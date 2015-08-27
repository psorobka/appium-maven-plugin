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
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.Os;
import org.zeroturnaround.process.PidUtil;
import org.zeroturnaround.process.Processes;

/**
 *
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
@Mojo(name = "start", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class StartServerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}", readonly = true, required = true)
    private File target;
    @Parameter(property = "node.home", required = false)
    private File nodeHome;
    @Parameter(property = "appium.home", required = true, defaultValue = "${user.home}/node_modules/appium")
    private File appiumHome;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            getLog().info("Starting Appium server...");
            getLog().debug("Node home: " + nodeHome);
            getLog().debug("Appium home: " + appiumHome);
            String nodeBin;
            if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                nodeBin = "node.exe";
            } else {
                nodeBin = "node";
            }
            if (nodeHome != null) {
                File nodeBinFile = new File(nodeHome, nodeBin);
                if (!nodeBinFile.exists()) {
                    throw new MojoExecutionException("Node binary does not exist: " + nodeBinFile);
                }
                nodeBin = nodeBinFile.getAbsolutePath();
            }
            getLog().debug("Node bin: " + nodeBin);
            File appiumJsFile = new File(appiumHome.getAbsoluteFile(), "bin" + File.separator + "appium.js");
            getLog().debug("Appium js: " + appiumJsFile);
            if (!appiumJsFile.exists()) {
                throw new MojoExecutionException("Appium js does not exist: " + appiumJsFile);
            }
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(nodeBin, appiumJsFile.getAbsolutePath(), "--log-timestamp", "--log", new File(target, "appiumLog.txt").getAbsolutePath());
            processBuilder.redirectError(new File(target, "appiumErrorLog.txt"));
            processBuilder.redirectOutput(new File(target, "appiumOutputLog.txt"));
            getLog().debug("Appium server commands " + processBuilder.command());
            Process process = processBuilder.start();
            if (!Processes.newPidProcess(process).isAlive()) {
                throw new MojoExecutionException("Failed to start Appium server");
            }
            int pid = PidUtil.getPid(process);
            getLog().info("Appium server started");
            getLog().debug("Appium server PID " + pid);
            FileUtils.writeStringToFile(new File(target, "appium.pid"), Integer.toString(pid));
            //Dumb way to sleep until appium starts - file watcher would be better
            Thread.sleep(5000);
        } catch (IOException | InterruptedException ex) {
            throw new MojoExecutionException("Failed to start Appium server", ex);
        }
    }
}
