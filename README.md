FacebookSDK3.6.0ShareDialog
===========================


Share Dialog using Facebook SDK 3.6.0

This is a sample project how to implement Facebook SDK 3.6.0 into android app.

## How To 

Import the  Facebook SDK in the /libFolder from this project.

**AndroidManifest.xml**

Add the following codes into AndroidManifest.xml file :
``` xml
<meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/app_id" />
```


MainActivity.java
Import the UiLifecycleHelper from Facebook package. We use this as a callback to handle result after opening Share Dialog.
``` java
import com.facebook.UiLifecycleHelper;
```
Create new UiLifecycleHelper class instance. 
``` java
private UiLifecycleHelper uiHelper;
```
Next, configure the UiLifecycleHelper  in OnCreate method :
``` java 
@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);		
	}
```

Create a local variable “callback”
``` java
private Session.StatusCallback callback = new Session.StatusCallback() {
@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};
```


Then, configure a callback’s handler that invoked when the share dialog closes and control the result. 
``` java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);

	uiHelper.onActivityResult(requestCode, resultCode, data,
	new FacebookDialog.Callback() {
	@Override
	public void onError(FacebookDialog.PendingCall pendingCall,
Exception error, Bundle data) {
		Log.e("Activity",String.format("Error: %s", error.toString()));
	}

@Override
public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	Log.i("Activity", "Success!");
}
});
}
```



The final step is to configure other methods on UiHelper to handle Activity lifecycle callback correctly.
``` java
@Override
	protected void onResume() {
		super.onResume();
		uiHelper.onResume();
	}
@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}
@Override
	protected void onPause() {
		super.onPause();
		uiHelper.onPause();
	}
@Override
	protected void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}
```

The code to call the share dialog :
``` java
FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(context)
.setName("name")
.setDescription("description")
	.setPicture(pictureURL)
.setLink(URL).build();
			uiHelper.trackPendingDialogCall(shareDialog.present());
```

Use this to prevent the app crash If Facebook application is not  installed :
``` java
if (FacebookDialog.canPresentShareDialog(context,
				FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
// code to call share dialog
}
```
Don’t forget to add string of your app_id in your strings.xml file  :
``` java
<string name="app_id">***************</string>
```

The link for documentation : http://bit.ly/1cOCOHC

