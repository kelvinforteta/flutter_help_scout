package com.maxinville.flutter_help_scout;


import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.helpscout.beacon.Beacon;
import com.helpscout.beacon.ui.BeaconActivity;

import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import java.util.*;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;

import io.flutter.plugin.common.PluginRegistry.Registrar;


/** FlutterHelpScoutPlugin */
public class FlutterHelpScoutPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware  {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  Activity context;

  BeaconHelpers beaconHelpers = new BeaconHelpers();


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {

    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "maxinville.com/flutter_help_scout");
    channel.setMethodCallHandler(this);

  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    final Map<String, Object> arguments = call.arguments();

    // this will initialize the beacon
    if (call.method.equals("initialize")) {
      beaconHelpers.initialize((String) arguments.get("beaconId"));
      beaconHelpers.identity((String) arguments.get("email"), (String) arguments.get("name"), (String) arguments.get("avatar"), (String) arguments.get("company"), (String) arguments.get("jobTitle"));
      result.success("Beacon successfully initialized!");
    }
    // open the beacon
    else if(call.method.equals("openBeacon")){
      beaconHelpers.openBeacon(context);
      result.success("Beacon successfully opened!");
    }

    else if(call.method.equals("logoutBeacon")){
      beaconHelpers.logout();
      result.success("Beacon successfully logged out!");
    }

    else if(call.method.equals("clearBeacon")){
      beaconHelpers.logout();
      result.success("Beacon successfully cleared!");
    }

  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    this.context = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {

  }
}
