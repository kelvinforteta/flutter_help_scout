# Beacon

Flutter implementation for Help Scout mobile SDK. Help Scout is an all-in-one customer service platform that helps you balance everything your customers need to be happy.

### Overview 
This plugin implements Help Scout official native SDK [Learn more](https://developer.helpscout.com/beacon-2/)

### Demo
![flutter_help_scout_demo](https://user-images.githubusercontent.com/53996412/103737367-63348b80-4ff2-11eb-835c-fddbba85b20c.gif)

### Usage
Setup beacon with your Beacon ID, user's email address and name (optional)

```
FlutterHelpScout _beacon;
String beaconId = 'YOUR_BEACON_ID';

Future<void> initBeacon() async {
    _beacon = FlutterHelpScout(
        beaconId: beaconId,
        email: 'example@example.com',
        name: 'John Doe',
        avatar: 'https://avatars3.githubusercontent.com/u/53996412?s=460&v=4');

    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      _beacon.initialize();
    } on PlatformException catch (e) {
      debugPrint('${e.message}');
    }
  }

  @override
  void initState() {
    // initialize beacon
    initBeacon();

    super.initState();
  }

  _beacon.open(beaconId: beaconId);
  ```
  

### Requirements
**For Android**
- minSdkVersion: 21 (Android 5.0)
- compileSdkVersion: 29 (Android 10.0)
- Enable Java 8 language feature support

```
android {
...
compileOptions {
  sourceCompatibility JavaVersion.VERSION_1_8
  targetCompatibility JavaVersion.VERSION_1_8
}
// For Kotlin projects
kotlinOptions {
  jvmTarget = "1.8"
}
}
```
- The Beacon SDK only needs [Internet permission](https://developer.android.com/training/basics/network-ops/connecting).


**For iOS**

The Beacon SDK requires iOS 11.0+.

*Note: For this plugin to work, add the following permission to your info.plist file (Optional)*

- `NSPhotoLibraryUsageDescription`
- `NSCameraUsageDescription`
- `NSMicrophoneUsageDescription`
- `NSPhotoLibraryAddUsageDescription`

![info.plist config](https://github.com/helpscout/HSAttachmentPicker/raw/master/picker_photos_permissions.png)

**DOCUMENTS**

To access the documents picker in the attachment menu, you’ll need the entitlements for iCloud and iCloud Containers. The ‘Import file from’ menu option throws an error message without these entitlements.

![iCloud container](https://github.com/helpscout/HSAttachmentPicker/raw/master/picker_icloud_permissions.png)

