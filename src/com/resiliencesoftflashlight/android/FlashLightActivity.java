package com.resiliencesoftflashlight.android;



import com.resiliencesoftflashlight.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.RelativeLayout;
import android.widget.Toast;


public class FlashLightActivity extends Activity {

	//flag to detect flash is on or off
	private boolean isLighOn = false;

	private Camera camera;

	
	
	private RelativeLayout relativelayout;

	@Override
	protected void onStop() {
		super.onStop();

		if (camera != null) {
			camera.release();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		relativelayout=(RelativeLayout)findViewById(R.id.relativeLayout1);
		Context context = this;
		PackageManager pm = context.getPackageManager();

		// if device support camera?
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Toast.makeText(FlashLightActivity.this, "Device has no camera",
					Toast.LENGTH_SHORT).show();
			Log.e("err", "Device has no camera!");
			return;
		}

		camera = Camera.open();
		final Parameters p = camera.getParameters();

		relativelayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isLighOn) {

					Log.i("info", "torch is turn off!");
					Toast.makeText(FlashLightActivity.this, "torch is turn off!",
							Toast.LENGTH_SHORT).show();
					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(p);
					camera.stopPreview();
					isLighOn = false;

				} else {

					Log.i("info", "torch is turn on!");
				Toast.makeText(FlashLightActivity.this, "torch is turn on!",
							Toast.LENGTH_SHORT).show();
					p.setFlashMode(Parameters.FLASH_MODE_TORCH);

					camera.setParameters(p);
					camera.startPreview();
					isLighOn = true;

				}
			}
		});
				
		


	}
}