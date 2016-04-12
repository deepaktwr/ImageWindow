package proj.me.imagewindow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import proj.me.imagewindow.images.ViewImage;

public class ImageDrawerActivity extends AppCompatActivity {

    private ViewImage viewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_drawer);

        viewImage = (ViewImage) findViewById(R.id.container);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewImage.processResult(requestCode, resultCode, data, "my comment");
    }
}
