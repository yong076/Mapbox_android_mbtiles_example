# Releasing

1. Choose a version number per [Semantic Versioning](http://semver.org/). Let's call it `x.y.z`.
1. Create a release ticket with release highlights and release steps

```
- [ ] Update HTTP User Agent Version in MapboxConstants.java
- [ ] Update VERSION_NAME to `x.y.z` in [/MapboxAndroidSDK/gradle.properties](https://github.com/mapbox/mapbox-android-sdk/blob/mb-pages/MapboxAndroidSDK/gradle.properties)
- [ ] Publish `x.y.z` artifact to Central Repository
- [ ] Update [README](https://github.com/mapbox/mapbox-android-sdk/blob/mb-pages/README.md) to list current and snapshot build numbers
- [ ] Update Mapbox.com Documentation (requires `x.y.z` to be found on http://search.maven.org - usually a few hours after release)
- [ ] Tag `x.y.z` in GitHub
- [ ] Update VERSION_NAME to `x.y+1.z-SNAPSHOT` in [/MapboxAndroidSDK/gradle.properties](https://github.com/mapbox/mapbox-android-sdk/blob/mb-pages/MapboxAndroidSDK/gradle.properties)
- [ ] Update Demo Project to use new `x.y.z` artifact from Central Repository
- [ ] Push new updated Demo App to Google Play
- [ ] Move non completed issues from `x.y.z` to `x.y+1.z` milestone
- [ ] Close Milestone `x.y.z` in GitHub
```

### Publishing To Central Repository
1. Add Nexus Repository and Signing Credentials To /MapboxAndroidSDK/gradle.properties (DO NOT COMMIT)
1. In Terminal

        
        cd MapboxAndroidSDK
        ./deploy.sh
        

1. Follow [instructions](http://central.sonatype.org/pages/releasing-the-deployment.html) to Close Staging Repository and Release To Central Repository
1. Remove Nexus Repository and Signing Credentials From /MapboxAndroidSDK/gradle.properties

### Updating Mapbox.com Documentation
1. Follow instructions in [/docs/README.md](https://github.com/mapbox/mapbox-android-sdk/blob/mb-pages/docs/README.md)

### Releasing New Demo App On Google Play
1. Update Mapbox Android SDK dependency, Version Code, and Version Name in [/MapBoxAndroidDemo/build.gradle](https://github.com/mapbox/mapbox-android-demo/blob/master/MapBoxAndroidDemo/build.gradle)
1. Build .apk with `./gradlew clean assembleRelease` from command line
1. Sign `MapBoxAndroidDemo/build/outputs/apk/MapBoxAndroidDemo-release-unsigned.apk` with same signing credentials used in Publishing To Central Repository
  See [Android documentation on doing this with Android Studio or Manually](http://developer.android.com/tools/publishing/app-signing.html)
1. Use `zipalign` to [align the APK](http://developer.android.com/tools/help/zipalign.html)
1. Upload to [Google Play](https://play.google.com/apps/publish).  See @bleege or @tmcw for access.
