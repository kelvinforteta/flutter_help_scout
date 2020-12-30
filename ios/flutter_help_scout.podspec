#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint flutter_help_scout.podspec' to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'flutter_help_scout'
  s.version          = '0.0.1'
  s.summary          = 'Flutter implementation for Help Scout mobile SDK.'
  s.description      = <<-DESC
A new Flutter plugin.
                       DESC
  s.homepage         = 'http://kelvinforteta.dev'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Maxinville Technologies Limited' => 'hi@kelvinforteta.dev' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.dependency 'Flutter'
  s.platform = :ios, '8.0'

  s.dependency 'Beacon'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
