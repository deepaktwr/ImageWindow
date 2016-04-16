package proj.me.imagewindow.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import proj.me.imagewindow.images.shading_four.ImageShadingFour;
import proj.me.imagewindow.images.shading_one.ImageShadingOne;
import proj.me.imagewindow.images.shading_three.ImageShadingThree;
import proj.me.imagewindow.images.shading_two.ImageShadingTwo;

/**
 * Created by Deepak.Tiwari on 28-09-2015.
 */
public final class ImageShading {

    private Context context;
    private final int maxWidth, minWidth;
    private final int maxHeight, minHeight;
    private int MAX_COUNTER;
    private List<Bitmap> images;
    private List<String> textImages;
    private int totalImages;
    private ImageCallback layoutCallback;
    private List<String> imageUrls;

    private int counter = 0;
    private boolean result;
    private boolean doneLoading;

    private List<String> bitmapImageUrls = new ArrayList<>();
    private List<String> otherImages = new ArrayList<>();
    private List<String> imageData = new ArrayList<>();
    private Map<String, String> imageTexts;

    ImageShading(Context context, ImageCallback layoutCallback, int minWidth, int minHeight, int maxWidth, int maxHeight){
        this.context =context;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        images = new ArrayList<>();
        textImages = new ArrayList<>();
        this.layoutCallback = layoutCallback;
    }

    void mapImages(List<String> imageUrls, int totalImages, List<String> otherImages, Map<String, String> imageTexts){
        targets.clear();
        this.imageTexts = imageTexts;
        this.totalImages = totalImages;
        this.imageUrls  = new ArrayList<>();
        bitmapImageUrls.clear();
        this.otherImages = otherImages;
        this.imageUrls.addAll(imageUrls);
        MAX_COUNTER = imageUrls.size();
        doneLoading = false;
        imageData.clear();
        imageData.addAll(imageUrls);
        loadImage(imageData.get(0));
    }
    private List<Target> targets = new ArrayList<>();
    private void loadImage(final String imageUrl){
        final MyTarget myTarget = new MyTarget(context, imageUrl);
        targets.add(myTarget);

            Picasso
                    .with(context)
                    .load(imageUrl)
                    .into(myTarget);


        imageData.remove(imageUrl);
        if(imageData.size() > 0)loadImage(imageData.get(0));
    }

    private boolean isRequestCanceled;
    private void onImageLoaded(boolean result, Bitmap bitmap, String imageUrl, String imageText){
        if(result) {
            imageUrls.remove(imageUrl);
            bitmapImageUrls.add(imageUrl);
        }
        if(doneLoading){
            isRequestCanceled = true;
            cancelAllRequests();
            return;
        }
        counter++;
        images.add(bitmap);
        textImages.add(imageText);
        if(!this.result) this.result = result;
        if(counter == MAX_COUNTER && !this.result){
            imageUrls.addAll(otherImages);
            ImageShadingOne imageShadingOne = new ImageShadingOne(context, layoutCallback, totalImages);
            imageShadingOne.updateFailedOrSingleUi(false, null, null, imageUrls, null);
            counter =0;
            images.clear();
            textImages.clear();
            this.result =false;
            doneLoading = true;
        }else if(counter == MAX_COUNTER){
            imageUrls.addAll(otherImages);
            images.removeAll(Collections.singleton(null));
            switch(images.size()){
                case 1:
                    ImageShadingOne imageShadingOne = new ImageShadingOne(context, layoutCallback, totalImages);
                    imageShadingOne.updateFailedOrSingleUi(true, images.get(0), textImages.get(0), imageUrls, bitmapImageUrls.get(0));
                    break;
                case 2:
                    ImageShadingTwo imageShadingTwo = new ImageShadingTwo(context, layoutCallback, totalImages);
                    imageShadingTwo.updateDoubleUi(images, textImages, imageUrls, bitmapImageUrls);
                    break;
                case 3:
                    ImageShadingThree imageShadingThree = new ImageShadingThree(context, layoutCallback, totalImages);
                    imageShadingThree.updateTripleUi(images, textImages, imageUrls, bitmapImageUrls);
                    break;
                case 4:
                    ImageShadingFour imageShadingFour = new ImageShadingFour(context, layoutCallback, totalImages);
                    imageShadingFour.updateOctalUi(images, textImages, imageUrls, bitmapImageUrls);
                    break;
            }
            counter =0;
            images.clear();
            textImages.clear();
            this.result =false;
            doneLoading = true;
        }
    }

    private void cancelAllRequests(){
        for(Target target : targets){
            Picasso.with(context).cancelRequest(target);
        }
        targets.clear();
        isRequestCanceled = false;
    }

    private class MyTarget extends View implements Target{

        private String imgUrl;

        public MyTarget(Context context, String imgUrl) {
            super(context);
            this.imgUrl = imgUrl;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            if(isRequestCanceled) return;
            targets.remove(this);
            onImageLoaded(true, bitmap, imgUrl, imageTexts.get(imgUrl));
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            if(isRequestCanceled) return;
            targets.remove(this);
            onImageLoaded(false, null, imgUrl, imageTexts.get(imgUrl));
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    }
}
