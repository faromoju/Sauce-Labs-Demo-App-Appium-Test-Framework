# Sauce-Labs-Demo-App-Appium-Test-Framework
## An Automated Test Framework built using Appium and TestNG for Sauce Labs Demo App


##### Use the command "mvn test -PFullFunctionalTest" in the commandline to run the entire test suite
##### Other profiles can be set up by organising the required tests in a xml file and creating the profile in the pom.xml file
##### Environment variables are stored in the data.properties file
##### Environment variables can be updated via the commandline by applying the "-D" command before the variable
##### Project is run using an Android Emulator. Set up Android Emulator as an environment variable on the local machine and create a device and set the target device name in data.properties or in Jenkins parameters