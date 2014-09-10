package com.mfollett.photo_booth.plugin;

// NOTE Used the camera plugin to figure out discrepencies between docs and reality.

// Docs are outdated; need to remove the api from the namespace.
//import org.apache.cordova.api.CordovaPlugin;
//import org.apache.cordova.api.PluginResult;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;

/**
 * This class echoes a string called from JavaScript.
 */
public class Share extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("share")) {
        	this.share(args.getString(0), callbackContext);
        	return true;
        }
        return false;
    }
    
    private void share(String imageURL, CallbackContext callbackContext) {    	
    	String body = "Here is my great picture!";
    	Intent intent = new Intent(android.content.Intent.ACTION_SEND);
    	intent.setType("text/plain");
    	intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "A great picture");
    	intent.putExtra(android.content.Intent.EXTRA_TEXT, body);
    	intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imageURL));
    	intent.setType("image/jpeg");
    	intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // According to Cordova this is the safe way to fire an activity inside a cordova app, but their
        // docs on it are kind of awful.
        // I have no idea what the 0 is for.
    	this.cordova.startActivityForResult((CordovaPlugin)this, Intent.createChooser(intent, "send"), 0);

    	callbackContext.success("shared");
    }
}
