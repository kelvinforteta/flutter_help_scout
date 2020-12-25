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

  private   Context context;
  private Activity activity;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {

    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_help_scout");
    channel.setMethodCallHandler(this);

  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    final Map<String, Object> arguments = call.arguments();

    try{

      new Beacon.Builder()
              .withBeaconId((String)arguments.get("beaconId"))
              .build();

        identifyUser((String)arguments.get("email"), (String)arguments.get("name"));

    }catch (Exception e){
      throw(e);
    }

    if (call.method.equals("openBeacon")) {
      openBeacon(activity);

    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  void identifyUser(String email, String name) {
    try {
      Beacon.identify(email, name);
    } catch (Exception e) {
      throw(e);
    }
  }


  void openBeacon(Context context){
    BeaconActivity.open(context);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    activity = binding.getActivity();
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
