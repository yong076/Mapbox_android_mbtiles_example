##Getting started with the Mapbox Android SDK

This guide will take you through the process of adding a map to your Android app. It assumes you have a Java IDE (like Eclipse or IntelliJ IDEA) with the [Android SDK](http://developer.android.com/sdk/index.html) installed, and an app project open.

### Required Permissions

Ensure the following *core* permissions are requested in your `AndroidManifest.xml` file:

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

If your project needs to access location services, it'll also need the following permissions too:

```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```

###The MapView

The `MapView` class is the key component of our library. It behaves
like any other `ViewGroup` and its behavior can be changed statically with an
[XML layout](http://developer.android.com/guide/topics/ui/declaring-layout.html)
file, or programmatically during runtime.

#### XML layout
To add the `MapView` as a layout element, add the following to your xml file:

```xml
<com.mapbox.mapboxsdk.views.MapView
android:id="@+id/mapview"
android:layout_width="fill_parent"
android:layout_height="fill_parent"
mapbox:mapid="Your Mapbox mapid"
mapbox:accessToken="Your Mapbox Access Token"/>
```


And then you can call it programmatically with

```java
this.findViewById(R.id.mapview);
```

#### On runtime

On runtime you can create a new MapView by specifying the context of the
application and a valid [Mapbox mapid](https://www.mapbox.com/developers/api-overview/),
a [TileJSON](https://github.com/mapbox/tilejson-spec) file or
a [ZXY image template](http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames).

```java
MapView mapView = new MapView(context);
mapView.setAccessToken("Your Mapbox Access Token");
mapView.setTileSource(new MapboxTileLayer("examples.map-vyofok3q"));
```

And set it as the current view like this:
```java
this.setContentView(mapView);
```

### Overlays

Anything visual that is displayed over the map, maintaining its geographical
position, we call it an `Overlay`. To access a MapView's overlays
at any point during runtime, use:

```java
mapView.getOverlays();
```

#### Markers

Adding a marker with the default styling is as simple as calling this
for every marker you want to add:

```java
Marker marker = new Marker(mapView, title, description, LatLng)
mapView.addMarker(marker);
```

#### Location overlay

The location of the user can be displayed on the view using `UserLocationOverlay`

```java
GpsLocationProvider myLocationProvider = new GpsLocationProvider(getActivity());
UserLocationOverlay myLocationOverlay = new UserLocationOverlay(myLocationProvider, mapView);
myLocationOverlay.enableMyLocation();
myLocationOverlay.setDrawAccuracyEnabled(true);
mapView.getOverlays().add(myLocationOverlay);
```

####Paths

Paths are treated as any other `Overlay`, and are drawn like this:

```java
PathOverlay line = new PathOverlay(Color.RED, this);
line.addPoint(new LatLng(51.2, 0.1));
line.addPoint(new LatLng(51.7, 0.3));
mapView.getOverlays().add(line);
```

#### Drawing anything into the map

To add anything with a higher degree of  customization you can declare your own `Overlay`
subclass and define what to draw by overriding the `draw` method. It will
give you a Canvas object for you to add anything to it:

```java
class AnyOverlay extends Overlay{
    @Override
    protected void draw(Canvas canvas, MapView mapView, boolean shadow) {
        //do anything with the Canvas object
    }
}
```

### Screen rotation

By default, every time the screen is rotated, Android will call `onCreate`
and return all states in the app to their inital values. This includes current
zoom level and position of the MapView. The simplest way to avoid this is adding
this line to your `AndroidManifest.xml`, inside `<activity>`:

	android:configChanges="orientation|screenSize|uiMode"

Alternatively you can override the methods `onSaveInstanceState()` and
`onRestoreInstanceState()` to have broader control of the saved states in the app.
See this [StackOverflow question](http://stackoverflow.com/questions/4096169/onsaveinstancestate-and-onrestoreinstancestate) for
more information on these methods

### More Examples Via TestApp

The Mapbox Android SDK is actually an [Android Library Module](https://developer.android.com/tools/projects/index.html#LibraryModules),
which means in order to test it out in an emulator or a device during development a [Test Module](https://developer.android.com/tools/projects/index.html#testing) is needed.  We call this test module
the **TestApp**.  It contains many different examples of new functionality or just ways to do certain things.  We highly recommend checking it out.

The source code for these tests / examples is located under the [MapboxAndroidSDKTestApp directory](https://github.com/mapbox/mapbox-android-sdk/tree/mb-pages/MapboxAndroidSDKTestApp/src/main/java/com/mapbox/mapboxsdk/android/testapp).

If you're interested in running the TestApp yourself (which is also highly recommened!) here's how:

1. Clone the [mapbox-android-sdk](https://github.com/mapbox/mapbox-android-sdk) to your local computer
2. Open in [Android Studio](http://developer.android.com/tools/studio/index.html) by using `File | Import Project` and selecting the `mapbox-android-sdk` directory that you cloned in Step 1.
3. Create a Run configuration via `Run | Edit Configurations`, select `Android Application`, and select `MapboxAndroidSDKTestApp` in the Module UI.
4. Run the TestApp!

![Image Of Importing Project To Android Studio](/images/android-studio-import-project.png)

![Image Of Creating Run Configuration For TestApp](/images/android-studio-create-run-configuration.png)


### Including SDK JavaDoc

JavaDocs are automatically generated and distributed with each official and
SNAPSHOT release.  The can be downloaded from The Central Repository  [official](http://search.maven.org/#search|ga|1|mapbox) or [SNAPSHOT](https://oss.sonatype.org/content/repositories/snapshots/com/mapbox/mapboxsdk/mapbox-android-sdk/) for local viewing and / or integration with an IDE.

### Generating SDK Documentation (aka JavaDoc)

```
cd <PROJECT_HOME>/MapboxAndroidSDK/
../gradlew clean assembleRelease javadocrelease
cd build/docs/javadoc/release/
open index.html
```
