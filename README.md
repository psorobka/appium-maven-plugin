appium-maven-plugin
===================

Plugin for managing [Appium](http://appium.io/) server from Maven.

From Appium website:
> Appium is an open source test automation framework for use with native and hybrid mobile apps. It drives iOS and Android apps using the WebDriver JSON wire protocol.

Features
-------------
 - Start and stop Appium server from Maven
 - Automatic download of Appium, using frontend-maven-plugin
 - Fully functional example: Maven Android application, with Appium integration tests running on Android emulator.
 - Support for Appium 1.4.10
**Build status**

[![Build Status](https://travis-ci.org/psorobka/appium-maven-plugin.svg?branch=master)](https://travis-ci.org/psorobka/appium-maven-plugin)

**Project status**

[![Still Maintained](http://stillmaintained.com/psorobka/appium-maven-plugin.png)](http://stillmaintained.com/psorobka/appium-maven-plugi)[![Backlog](https://badge.waffle.io/psorobka/appium-maven-plugin.png?label=backlog&title=Backlog)](https://waffle.io/psorobka/appium-maven-plugin)
[![Backlog](https://badge.waffle.io/psorobka/appium-maven-plugin.svg?title=Backlog)](http://waffle.io/psorobka/appium-maven-plugin)
[![Ready](https://badge.waffle.io/psorobka/appium-maven-plugin.svg?label=ready&title=Ready)](http://waffle.io/psorobka/appium-maven-plugin)
[![In progress](https://badge.waffle.io/psorobka/appium-maven-plugin.svg?label=in_progress&title=In%20Progress)](http://waffle.io/psorobka/appium-maven-plugin)
[![Done](https://badge.waffle.io/psorobka/appium-maven-plugin.svg?label=in_progress&title=In%20Progress)](http://waffle.io/psorobka/appium-maven-plugin)

**Release status**

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.psorobka/appium-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.psorobka/appium-maven-plugin)

Example
-------------------
To run example Android project:
 - Using already started Appium and default device connected to ADB: `$ mvn clean verify -Pintegration-test`
 - Using Appium installed in default location (${user.home}/node_modules/.bin) and default device connected to ADB: `$ mvn clean verify -Pstart-and-stop-appium,integration-test`
 - Using Appium installed during Maven build and Android emulator: `$ mvn clean verify -Pinstall-appium,use-installed-appium,start-and-stop-appium,integration-test,start-and-stop-emulator` or `$ mvn clean verify -Dfull-integration-test=true`
