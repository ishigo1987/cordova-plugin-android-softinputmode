# cordova-plugin-softinputmode

This plugin lets you change the [android:windowSoftInputMode property](https://developer.android.com/guide/topics/manifest/activity-element.html#wsoft) at runtime.

Currently `adjustPan`, `adjustResize`, `adjustNothing`, `stateUnspecified`, `stateUnchanged`, `stateHidden`, `stateAlwaysHidden`, `stateVisible`, `stateAlwaysVisible`, `adjustUnspecified` values are supported.

## Example
After the `deviceready` event:

`window.SoftInputMode.set('adjustNothing')`