package proj.me.imagewindow.images;

import android.content.Intent;
import android.view.View;

import java.util.List;

/**
 * Created by Deepak.Tiwari on 30-10-2015.
 */
public interface ImageCallback{
    void addImageView(View view, int viewWidth);
    //1 2
    //3 4
    /**
     * @param imageType single to multiplevalue will be null if image has not been loaded.
     * @param imagePosition defined in horizontal way.
     * @param imageUrls image urls except the images which has been showing on the screen.
     * @param loadedImageUrls the image urls of the images which has been showing to the sreen in horizontal manner*/
    void imageClicked(ImageType imageType, int imagePosition, List<String> imageUrls, List<String> loadedImageUrls);
    void processResult(int requestCode, int resultCode, Intent data, String comment);

    void addMore();
    void setColorsToAddMoreView(int resultColor, int mixedColor, int invertedColor);
}
