package dk.sculpto.plugin;

import android.view.WindowManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class SoftInputMode extends CordovaPlugin {

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
  }

  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if (action.equals("set")) {
      final String value = args.getString(0);
      this.set(value, callbackContext);
      return true;
    }
    return false;
  }

  private void set(final String value, final CallbackContext callbackContext) {
    cordova.getActivity().runOnUiThread(new Runnable() {
      public void run() {
        int mode;
        if (value.equals("adjustNothing"))
          mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING;
        else if (value.equals("adjustResize"))
          mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
        else if (value.equals("adjustPan"))
          mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
        else if (value.equals("stateUnspecified")) {
          mode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
        } else if (value.equals("stateUnchanged")) {
          mode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
        } else if (value.equals("stateHidden")) {
          mode = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;
        } else if (value.equals("stateAlwaysHidden")) {
          mode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN;
        } else if (value.equals("stateAlwaysVisible")) {
          mode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;
        } else if (value.equals("adjustUnspecified")) {
          mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED;
        } else {
          callbackContext.error("Invalid mode");
          return;
        }

        cordova.getActivity().getWindow().setSoftInputMode(mode);
        callbackContext.success("Success");
      }
    });
  }
}
