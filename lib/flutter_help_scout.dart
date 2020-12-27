import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class FlutterHelpScout {
  static const MethodChannel _channel =
      const MethodChannel('maxinville.com/flutter_help_scout');

  final String beaconId;
  final String name;
  final String email;
  final String avatar;
  final String company;
  final String jobTitle;

  FlutterHelpScout(
      {this.email = '',
      this.name = '',
      @required this.beaconId,
      this.avatar = '',
      this.company = '',
      this.jobTitle = ''});

  // this method will initialize the beacon
  Future<void> initialize() async {
    var data = <String, dynamic>{
      'beaconId': beaconId,
      'email': email,
      'name': name,
      'avatar': avatar,
      'jobTitle': jobTitle,
      'company': company,
    };

    try {
      final String result = await _channel.invokeMethod(
        'initialize',
        data,
      );

      return result;
    } on PlatformException catch (e) {
      print('Unable to initialize beacon: ${e.toString()}');
    }
  }

  /*
   * Once you’ve initialized Beacon, you’re ready to interact with it.
   * Whenever you want to invoke Beacon, use the code below to
   * display the Beacon user interface.
   */
  Future<void> openBeacon() async {
    try {
      final String result = await _channel.invokeMethod(
        'openBeacon',
      );

      return result;
    } on PlatformException catch (e) {
      print('Unable to open beacon: ${e.toString()}');
    }
  }

  /*
    * Calling this method resets the current Beacon state,
    * thereby deleting all the user data: email, name, user attributes,
    * push token and resets the Beacon Device ID. It won’t
    * remove the Beacon ID, or any local config overrides.
   */
  Future<void> logoutBeacon() async {
    try {
      final String result = await _channel.invokeMethod(
        'logoutBeacon',
      );

      return result;
    } on PlatformException catch (e) {
      print('Unable to open beacon: ${e.toString()}');
    }
  }

  /*
    * This method wipes all data from the Beacon,
    * including the Beacon ID. This may be useful if
    * you are using different Beacons in different parts of your app.
    */
  Future<void> clearBeacon() async {
    try {
      final String result = await _channel.invokeMethod(
        'clearBeacon',
      );

      return result;
    } on PlatformException catch (e) {
      print('Unable to open beacon: ${e.toString()}');
    }
  }
}
