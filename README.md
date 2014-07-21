appium-maven-plugin
===================

Plugin for managing [Appium](http://appium.io/) server from Maven.

From Appium website:
> Appium is an open source test automation framework for use with native and hybrid mobile apps. It drives iOS and Android apps using the WebDriver JSON wire protocol.

**Build status**

[![Build Status](https://travis-ci.org/psorobka/appium-maven-plugin.svg?branch=master)](https://travis-ci.org/psorobka/appium-maven-plugin)[![Build Status](https://drone.io/github.com/psorobka/appium-maven-plugin/status.png)](https://drone.io/github.com/psorobka/appium-maven-plugin/latest)[![Codeship Status for psorobka/appium-maven-plugin](https://www.codeship.io/projects/643dac20-f034-0131-9cd2-22f63bc3c02a/status)](https://www.codeship.io/projects/27347)

**Project status**

[![Still Maintained](http://stillmaintained.com/psorobka/appium-maven-plugin.png)](http://stillmaintained.com/psorobka/appium-maven-plugi)[![Backlog](https://badge.waffle.io/psorobka/appium-maven-plugin.png?label=backlog&title=Backlog)](https://waffle.io/psorobka/appium-maven-plugin) [![Ready](https://badge.waffle.io/psorobka/appium-maven-plugin.png?label=ready&title=Ready)](https://waffle.io/psorobka/appium-maven-plugin)[![In Progress](https://badge.waffle.io/psorobka/appium-maven-plugin.png?label=in%20progress&title=In%20Progress)](https://waffle.io/psorobka/appium-maven-plugin) [![Done](https://badge.waffle.io/psorobka/appium-maven-plugin.png?label=done&title=Done)](https://waffle.io/psorobka/appium-maven-plugin)

**Release status**

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.psorobka/appium-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.psorobka/appium-maven-plugin)

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
