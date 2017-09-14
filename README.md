# README #

BASIC DESCRIPTION -:

* This repository is an android studio project for making an app that can record a video and along with video, it is also recording accelerometer sensor values and gps location of the mobile phone used in the context for research purposes.

* You can find the apk related to this code at
 https://drive.google.com/file/d/0B3Baq0w3Jj7HaXNPRlBDOUxKSVk/view?usp=sharing

* This repository was initially developed by Sachin Bijalwan(contact - 2015csb1027@iitrpr.ac.in). Future developers can add their name and
	contact info in this point.

********************************************************************************************************************************

BASIC WORKING OF APP-:

* Using Camera2 classes to record videos. I have basically copied my whole code of video fragment and xml file from the github repository (mentioned in sources).

* Using sensors classes for GPS and accelerometer. (Sources enclosed in the end of this file).

* Using Cursor Adapter to display list of videos recorded.

* Using Tab view to display datas related to app.

**********************************************************************************************************************************

IMPROVMENTS LEFT IN APP -:

* Recording the data to the maximum precision but displaying data to a fixed point of precision.(not implementing this one)

* A way of converting database tables to a Microsoft excel sheet.//done

* Find a better way for recording data because the smartphone is becoming too much heat as a result of overprocessing of data. //done using reducing frame rate

* Removing below mentioned bugs.

***********************************************************************************************************************************

BUGS -:

* On opening the tablayout , the first gps tab is not loaded in the first time.	//solved

* Some other bug is responsible for the crash of the app.

************************************************************************************************************************************

RESOURCES:-

* Description of Camera2 -: "https://github.com/googlesamples/android-Camera2Basic".

* GPS - https://developer.android.com/guide/topics/location/strategies.html

* Accelerometer - https://developer.android.com/guide/topics/sensors/sensors_overview.html

* A complete description of CursorAdapter - https://github.com/codepath/android_guides/wiki/Populating-a-ListView-with-a-CursorAdapter

* Sources from where I got a way to convert my database table to a .csv file - http://www.parallelcodes.com/android-export-sqlite-database/
