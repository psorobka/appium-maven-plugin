appium-maven-plugin
===================

Plugin for managing [Appium](http://appium.io/) server from Maven.

[![Build Status](https://travis-ci.org/psorobka/appium-maven-plugin.svg?branch=master)](https://travis-ci.org/psorobka/appium-maven-plugin)   [![Stories in Ready](https://badge.waffle.io/psorobka/appium-maven-plugin.png?label=ready&title=Ready)](https://waffle.io/psorobka/appium-maven-plugin)   [ ![Codeship Status for psorobka/appium-maven-plugin](https://www.codeship.io/projects/643dac20-f034-0131-9cd2-22f63bc3c02a/status)](https://www.codeship.io/projects/27347)

From Appium website:
> Appium is an open source test automation framework for use with native and hybrid mobile apps. It drives iOS and Android apps using the WebDriver JSON wire protocol.

Features
-------------
 - Start and stop Appium server from Maven
 - Automatic download of Appium, using frontend-maven-plugin
 - Fully functional example: Maven Android application, with Appium integration tests running on Android emulator.

Example
-------------------
To run example Android project:
 - Using Appium installed in default location (${user.home}/node_modules/.bin) and default device connected to ADB: `$ mvn clean verify -Pintegration-test`
 - Using Appium installed during Maven build and Android emulator: `$ mvn clean verify -Pintegration-test,install-appium,use-installed-appium,start-and-stop-emulator`
