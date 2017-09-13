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
        if(value.equals("adjustNothing"))
          mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING;
        else if(value.equals("adjustResize"))
          mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
        else if(value.equals("adjustPan"))
          mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
        else {
          callbackContext.error("Invalid mode");
          return;
        }

        cordova.getActivity().getWindow().setSoftInputMode(mode);
        callbackContext.success("Success");
      }
	  });
  }
}
