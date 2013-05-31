XBMC Voice Control
==================

Android based Voice Control for XBMC


Building and Deploying
----------------------
To build the application, deploy it to a (virtual) device and run it in one go, execute the following command in the root of the project.

	mvn clean install && mvn -pl xbmcvc-android android:redeploy android:run
