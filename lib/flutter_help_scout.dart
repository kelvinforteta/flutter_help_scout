import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class FlutterHelpScout {
  static const MethodChannel _channel =
      const MethodChannel('flutter_help_scout');

  final String beaconId;
  final String name;
  final String email;

  FlutterHelpScout({this.email = '', this.name = '', @required this.beaconId});

  Future<void> openBeacon() async {
    var data = <String, dynamic>{
      'beaconId': beaconId,
      'email': email,
      'name': name,
    };

    try {
      final int result = await _channel.invokeMethod(
        'openBeacon',
        data,
      );
    } on PlatformException catch (e) {
      print('Unable to open beacon: ${e.toString()}');
    }
  }
}
