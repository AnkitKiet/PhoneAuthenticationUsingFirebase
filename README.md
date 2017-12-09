# PhoneAuthenticationUsingFirebase

This is an application demonstrating, how to login using Phone Verification with Otp generated using Firebase.
Firebase now provides authentication with phone with a free generation of 10K otp request/day. 
This app shows how to login by-
<br />
1: Firebase UI
<br />
2: Custom UI
<br /> Custom UI require a broadcast reciever which need to be registered to read Otp automatically. But FirebaseUi solves these problem and provides a bolierplate code for AuthUI with all verification on phone number.
<br />
#### Problems Faced During Developing
When Initializing Firebase, the version compatibility creates many issues. FirebaseUI must be compatible with the Firebase Core version.Even the AppCompat version should be compatible.
<br />
You can check your compatibility from  [FirebaseCompatibilityCheck]( https://github.com/firebase/FirebaseUI-Android#compatibility-with-firebase--google-play-services-libraries)
<br />
There is a myth to newbies that Firebase PhoneAuth can only be used in Android Studio 3.0+ . But that's not true. It can be used with any version above 2+ .
