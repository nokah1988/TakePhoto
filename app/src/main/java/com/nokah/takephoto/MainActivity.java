package com.nokah.takephoto;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView nokahImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nokahButton = (Button) findViewById(R.id.nokahButton);
        nokahImageView = (ImageView) findViewById(R.id.nokahImageView);

        // disable the button if the user hasn't camera
        if (!hasCamera())
            nokahButton.setEnabled(false);
    }

        // check if the user has a camera
        private boolean hasCamera() {
            return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        }

        // lauching the camera
        public void launchCamera(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }

        // if you want to return the image taken
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK);

                // get picture
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");
                nokahImageView.setImageBitmap(photo);
        }


}
