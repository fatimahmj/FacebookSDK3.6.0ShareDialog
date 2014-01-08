package com.fmj.facebookshare;

import android.app.AlertDialog;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

public class FBWrapper {
	FacebookDialog shareDialog;


	public static void shareDefaultPost(MainActivity mainActivity,
			UiLifecycleHelper uiHelper) {
		if (FacebookDialog.canPresentShareDialog(mainActivity,
				FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
			FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(
					mainActivity).setName("Testing")
					.setDescription("Tutorial using Share Dialog Facebook SDK.")
					.setLink("https://developers.facebook.com/docs/android/share-dialog/").build();
			uiHelper.trackPendingDialogCall(shareDialog.present());
		} else {
			AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
			alert.setMessage("Facebook Application not installed.")
					.setNeutralButton("OK", null).show();

		}

	}

}
