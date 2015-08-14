Documentation
---

The API documentation on mapbox.com uses the generated Javadocs. 

Step 1. Copy JavaDocs Into API
```sh
cd docs/api
mkdir x.y.z  //version number
cp -rp ../../MapboxAndroidSDK/build/docs/javadoc/release/ .
git add x.y.z
git commit -a
```

Step 2. Edit Link To New API
  * Update `x.y.z` in [docs/_layouts/pages.html](https://github.com/mapbox/mapbox-android-sdk/blob/mb-pages/docs/_layouts/pages.html#L55)
