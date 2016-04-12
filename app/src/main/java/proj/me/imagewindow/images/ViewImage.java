package proj.me.imagewindow.images;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import proj.me.imagewindow.R;
import proj.me.imagewindow.databinding.ItemImageviewBinding;
import proj.me.imagewindow.window.Utils;

/**
 * Created by Deepak.Tiwari on 10-08-2015.
 */
public class ViewImage extends LinearLayout implements ImageCallback, ImageClickHandler {
    private LayoutInflater layoutInflater;
    private RelativeLayout imageContainer;
    private List<String> data = new ArrayList<>();
    private Map<String, String> dataTexts = new HashMap<>();
    private List<String> listImg = new ArrayList<>();
    private ImageShading imageShading;
    private Context context;

    private ImageIntent imageIntent;

    private BinadingImageWindow binadingImageWindow;

    public ViewImage(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public ViewImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        init((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public ViewImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        init((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }


    private void init(LayoutInflater layoutInflater){
        binadingImageWindow = new BinadingImageWindow();
        this.layoutInflater = layoutInflater;
        context = layoutInflater.getContext();
        imageIntent = new ImageIntent(((Activity) layoutInflater.getContext()));
        setImageView();
    }

    private void setImageView(){
        ItemImageviewBinding itemImageviewBinding = DataBindingUtil.bind(layoutInflater.inflate(R.layout.item_imageview, null));
        itemImageviewBinding.setImageWindow(binadingImageWindow);
        itemImageviewBinding.setClickHandler(this);
        addView(itemImageviewBinding.getRoot());

        //set text default values
        binadingImageWindow.setAddTextVisibility(true);
        binadingImageWindow.setAddText("+");
        binadingImageWindow.setAddBackgroundColor(getResources().getColor(R.color.colorPrimary));
        binadingImageWindow.setAddTextColor(getResources().getColor(R.color.white));


        //for all views
        //give window size too for image max windows
        Utils.widthPixel = getResources().getDisplayMetrics().widthPixels;
        Utils.heightPixel = getResources().getDisplayMetrics().heightPixels;

        int maxW = Utils.widthPixel - 50;
        int maxH = (Utils.heightPixel - 500) < Utils.widthPixel ? Utils.widthPixel : Utils.heightPixel - 500;
        int minW = (maxW / 2) - 50;
        int minH = (maxH / 2) - 50;

        Utils.MIN_WIDTH = minW;
        Utils.MIN_HIGHT = minH;
        Utils.MAX_WIDTH = maxW;
        Utils.MAX_HEIGHT = maxH;

        imageShading = new ImageShading(context, this, minW, minH, maxW, maxH);
        imageContainer = (RelativeLayout)findViewById(R.id.parent_relative);
        /*result = findViewById(R.id.result_color);
        mixed = findViewById(R.id.mixed_color);
        inverted = findViewById(R.id.inverted_color);*/
    }


    public void fetchImage(int actionId) {
        imageIntent.callIntentForImage(actionId);
    }

    private void processImage(String imagePath, String commentText){
        if (TextUtils.isEmpty(imagePath)) {
            Utils.showToast(context, "could not process image");
            return;
        }
        List<String> nextList = new ArrayList<>();
        nextList.add(imagePath);
        int maxLength = this.data.size()<4?this.data.size():3;
        for(int i=this.data.size()-1;i>this.data.size()-1-maxLength;i--)
            nextList.add(this.data.get(i));
        this.data.add(imagePath);
        dataTexts.put(imagePath, commentText);
        listImg.clear();
        listImg.addAll(this.data);

        for(String url:nextList)
            listImg.remove(url);

        binadingImageWindow.setAddTextVisibility(false);
        binadingImageWindow.setProgressBarVisibility(true);
        imageContainer.removeAllViews();

        imageShading.mapImages(nextList, this.data.size(), listImg, dataTexts);
    }

    @Override
    public void processResult(int requestCode, int resultCode, Intent data, String comment) {
        String imagePath = imageIntent.getImageResultPath(data, requestCode, comment);
        // show a view to handle the camera or gallery data with cropping and commenting, and save it to the sd card with a path
        processImage(imagePath, "my commented image");
    }

    @Override
    public void addImageView(View view, int viewWidth) {
        binadingImageWindow.setProgressBarVisibility(false);
        if(viewWidth != 0){
            binadingImageWindow.setAddTextVisibility(true);
            binadingImageWindow.setAddText("+");
            //BinadingImageWindow.setTextWidth(findViewById(R.id.add_text), viewWidth);
        }else
            binadingImageWindow.setAddTextVisibility(false);

        imageContainer.addView(view);
        imageContainer.requestLayout();
    }

    @Override
    public void imageClicked(ImageType imageType, int imagePosition, List<String> imageUrls, List<String> loadedImageUrls) {
        Bundle imageBundle = new Bundle();

        ImageScrollParcelable imageScrollParcelable = new ImageScrollParcelable();
        imageScrollParcelable.setImageType(imageType);
        imageScrollParcelable.setImagePosition(imagePosition);
        imageScrollParcelable.setImageUrls(imageUrls);
        imageScrollParcelable.setLoadedImageUrls(loadedImageUrls);
        imageScrollParcelable.setDataTexts(dataTexts);
        imageBundle.putParcelable("image_data", imageScrollParcelable);
        Utils.showToast(context, "image clicked");
    }

    @Override
    public void addMore() {
        fetchImage(1);
    }

    /*private View result, mixed, inverted;*/
    @Override
    public void setColorsToAddMoreView(int resultColor, int mixedColor, int invertedColor) {
        //binadingImageWindow.setAddBackgroundColor(mixedColor);
        setAddTextBgColor(findViewById(R.id.add_text), mixedColor);
        binadingImageWindow.setAddTextColor(invertedColor);
        binadingImageWindow.setProgressBarColor(mixedColor);

        /*result.setBackgroundColor(resultColor);
        mixed.setBackgroundColor(mixedColor);
        inverted.setBackgroundColor(invertedColor);*/
    }

    private void setAddTextBgColor(View view, int color){
        GradientDrawable drawable = (GradientDrawable) view.getBackground();
        drawable.setColor(color);
    }

    @Override
    public void onImageShadeClick(View view) {
        fetchImage(1);
    }
}
