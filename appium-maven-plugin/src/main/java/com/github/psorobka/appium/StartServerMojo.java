/*
 * Copyright 2014 Piotr Soróbka <psorobka@gmail.com>.
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
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.Os;

/**
 *
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
@Mojo(name = "start")
public class StartServerMojo extends AbstractMojo {

    public static final String PROCESS_PROPERTY_NAME = "process";

    @Parameter(defaultValue = "${project.build.directory}", readonly = true)
    private File target;
    @Parameter(defaultValue = "${project.build.directory}\\node\\npm\\node_modules\\.bin")
    private File appiumHome;
    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession session;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("execute start");
            ProcessBuilder processBuilder = new ProcessBuilder();
            final String appiumBin;
            if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                appiumBin = appiumHome.getAbsolutePath() + File.separator + "appium.cmd";
            } else {
                appiumBin = appiumHome.getAbsolutePath() + File.separator + "appium";
            }
            getLog().debug("appium binary: " + appiumBin);
            processBuilder.command(appiumBin, "--log-timestamp");
            processBuilder.redirectError(new File(target, "appiumErrorLog.txt"));
            processBuilder.redirectOutput(new File(target, "appiumOutputLog.txt"));
            getLog().info("Starting Appium server...");
            Process startProcess = processBuilder.start();
            getLog().info("Appium server started");
            session.getUserProperties().put(PROCESS_PROPERTY_NAME, startProcess);
        } catch (IOException ex) {
            getLog().error("Error while executing start goal", ex);
            throw new MojoExecutionException("Error while executing start goal", ex);
        }
    }
}
