# FatherHome
## Capstone project from Udacity as part of the Android Nanodegree

Api Bible from: http://bibliaapi.com/docs/Available_Bibles, get a key for json request functionality.

You can insert the API key into the gradle.properties files. Add the next line:
BibleApi="KEY HERE"

The app also uses Google Maps, you can insert a valid key in the string.xml: google_maps_key
The app uses Firebase RealtimeDatabase, if you want to install the app you will need a google-services.json file, and create a database according to: [Sample for Father Home Database](database_sample.json)

![FatherHomeImage1](sign_in_activity.jpg)

![FatherHomeGif1](fatherhome1.gif)

![FatherHomeGif2](fatherhome2.gif)

![FatherHomeGif3](fatherhome3.gif)

![FatherHomeGif4](fatherhome4.gif)

## Common Project Requirements

* App conforms to common standards found in the [Android Nanodegree General Project Guidelines](http://udacity.github.io/android-nanodegree-guidelines/core.html)
* App is written solely in the Java Programming Language
* App utilizes stable release versions of all libraries, Gradle, and Android Studio.

## Core Platform Development

* App integrates a third-party library.
* App validates all input from servers and users. If data does not exist or is in the wrong format, the app logs this fact and does not crash.
* App includes support for accessibility. That includes content descriptions, navigation using a D-pad, and, if applicable, non-audio versions of audio cues.
* App keeps all strings in a strings.xml file and enables RTL layout switching on all layouts.
* App provides a widget to provide relevant information to the user on the home screen.

## Google Play Services

* App integrates two or more Google services. Google service integrations can be a part of Google Play Services or Firebase.
* Each service imported in the build.gradle is used in the app.
* If Location is used, the app customizes the user’s experience by using the device's location.
* If Admob is used, the app displays test ads. If Admob was not used, student meets specifications.
* If Analytics is used, the app creates only one analytics instance. If Analytics was not used, student meets specifications.
* If Maps is used, the map provides relevant information to the user. If Maps was not used, student meets specifications.
* If Identity is used, the user’s identity influences some portion of the app. If Identity was not used, student meets specifications.

## Material Design

* App theme extends AppCompat.
* App uses an app bar and associated toolbars.
* App uses standard and simple transitions between activities.

## Building

* App builds from a clean repository checkout with no additional configuration.
* App builds and deploys using the installRelease Gradle task.
* App is equipped with a signing configuration, and the keystore and passwords are included in the repository. Keystore is referred to by a relative path.
* All app dependencies are managed by Gradle.

## Data Persistence

* App stores data locally either by implementing a ContentProvider OR using Firebase Realtime Database OR using Room. No third party frameworks nor Persistence Libraries may be used.
* Must implement at least one of the three
* If it regularly pulls or sends data to/from a web service or API, app updates data in its cache at regular intervals using a SyncAdapter or JobDispacter.
OR
* If it needs to pull or send data to/from a web service or API only once, or on a per request basis (such as a search application), app uses an IntentService to do so.
OR
* It it performs short duration, on-demand requests(such as search), app uses an AsyncTask.
* If Content provider is used, the app uses a Loader to move its data to its views.
* If Room is used then LiveData and ViewModel are used when required and no unnecessary calls to the database are made.




