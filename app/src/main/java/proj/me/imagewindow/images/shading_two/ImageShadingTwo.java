package proj.me.imagewindow.images.shading_two;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import proj.me.imagewindow.R;
import proj.me.imagewindow.databinding.ViewDoubleHorzBinding;
import proj.me.imagewindow.databinding.ViewDoubleVertBinding;
import proj.me.imagewindow.images.ImageCallback;
import proj.me.imagewindow.images.ImageClickHandler;
import proj.me.imagewindow.images.ImageType;
import proj.me.imagewindow.images.dimentions.BeanShade1;
import proj.me.imagewindow.images.dimentions.BeanShade2;
import proj.me.imagewindow.images.dimentions.ImageOrder;
import proj.me.imagewindow.images.dimentions.LayoutType;
import proj.me.imagewindow.images.dimentions.ShadeTwo;
import proj.me.imagewindow.window.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak.Tiwari on 29-09-2015.
 */
public class ImageShadingTwo implements ImageClickHandler{

    private LayoutInflater inflater;
    private ImageCallback layoutCallback;
    private int totalImages;
    private List<String> imageUrls;

    private List<String> loadedImageUrl;

    private BindingShadeTwo bindingShadeTwo;

    public ImageShadingTwo(Context context, ImageCallback layoutCallback, int totalImages){
        inflater = LayoutInflater.from(context);
        this.layoutCallback = layoutCallback;
        this.totalImages = totalImages;
        bindingShadeTwo = new BindingShadeTwo();
    }

    public void updateDoubleUi(List<Bitmap> images, List<String> imageTexts, List<String> imageUrls, List<String> loadedImageUrl){
        this.imageUrls = imageUrls;
        this.loadedImageUrl = loadedImageUrl;

        int width1 = images.get(0).getWidth();
        int height1 = images.get(0).getHeight();

        int width2 = images.get(1).getWidth();
        int height2 = images.get(1).getHeight();


        BeanShade2 beanShade2 = ShadeTwo.calculateDimentions(width1, height1, width2, height2);

        boolean isFirstImageLeftOrTop = beanShade2.getImageOrderList().get(0) == ImageOrder.FIRST;
        boolean isVertLayout = beanShade2.getLayoutType() == LayoutType.VERT;


        Utils.logMessage("getWidth1 : "+width1);
        Utils.logMessage("getHeight1 : "+height1);
        Utils.logMessage("getWidth2 : "+width2);
        Utils.logMessage("getHeight2 : "+height2);

        Utils.logMessage("Start++++++++++++++++++++++++++++++++++++Start");
        Utils.logMessage("getWidth1 : "+beanShade2.getWidth1());
        Utils.logMessage("getHeight1 : "+beanShade2.getHeight1());
        Utils.logMessage("getWidth2 : "+beanShade2.getWidth2());
        Utils.logMessage("getHeight2 : "+beanShade2.getHeight2());
        Utils.logMessage("isFirstImageLeftOrTop : "+isFirstImageLeftOrTop);
        Utils.logMessage("isVertLayout : "+isVertLayout);
        Utils.logMessage("End++++++++++++++++++++++++++++++++++++End");

        View root = null;

        if(isVertLayout){
            ViewDoubleVertBinding viewDoubleVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_double_vert, null));
            viewDoubleVertBinding.setShadeTwo(bindingShadeTwo);
            viewDoubleVertBinding.setClickHandler(this);
            root = viewDoubleVertBinding.getRoot();
        }else{
            ViewDoubleHorzBinding viewDoubleHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_double_horz, null));
            viewDoubleHorzBinding.setShadeTwo(bindingShadeTwo);
            viewDoubleHorzBinding.setClickHandler(this);
            root = viewDoubleHorzBinding.getRoot();
        }

        Bitmap bitmap1 = images.get(0);
        Bitmap bitmap2 = images.get(1);

        boolean isAddViewVisible = false;

        ImageView imageView1 = (ImageView) root.findViewById(R.id.view_double_image1);
        ImageView imageView2 = (ImageView) root.findViewById(R.id.view_double_image2);

        BindingShadeTwo.setLayoutHeight(imageView1, beanShade2.getHeight1());
        BindingShadeTwo.setLayoutWidth(imageView1, beanShade2.getWidth1());

        generatePalette(isFirstImageLeftOrTop ? bitmap1 : bitmap2, 1);
        BindingShadeTwo.setBitmap(imageView1, isFirstImageLeftOrTop ? bitmap1 : bitmap2);

        BindingShadeTwo.setLayoutWidth(imageView2, beanShade2.getWidth2());
        BindingShadeTwo.setLayoutHeight(imageView2, beanShade2.getHeight2());

        generatePalette(isFirstImageLeftOrTop ? bitmap2 : bitmap1, 2);
        BindingShadeTwo.setBitmap(imageView2, isFirstImageLeftOrTop ? bitmap2 : bitmap1);

        bindingShadeTwo.setAddVisibility(true);

        if(beanShade2.isAddInLayout()) {
            if (totalImages > 2) {
                bindingShadeTwo.setAddAsCounter(true);
                bindingShadeTwo.setAddText("+" + (totalImages - 2));
                //text color
                //bg
            } else {
                bindingShadeTwo.setAddAsCounter(false);
                bindingShadeTwo.setAddText("+");
                //text color
                //bg
                isAddViewVisible = true;
            }
        }else {
            if (totalImages > 2) {
                bindingShadeTwo.setCounterVisibility(true);
                bindingShadeTwo.setCounterText("+" + (totalImages - 2));
            }
        }

        bindingShadeTwo.setFirstImageScaleType(Utils.getImageScaleType());
        //bg
        bindingShadeTwo.setSecondImageScaleType(Utils.getImageScaleType());
        //bg

        bindingShadeTwo.setFirstComment(isFirstImageLeftOrTop ? imageTexts.get(0) : imageTexts.get(1));
        bindingShadeTwo.setFirstCommentVisibility(Utils.shouldShowComments());
        //bg
        bindingShadeTwo.setSecondComment(isFirstImageLeftOrTop ? imageTexts.get(1) : imageTexts.get(0));
        bindingShadeTwo.setSecondCommentVisibility(Utils.shouldShowComments());
        //bg

        //no need to add or remove from list

        String first = isFirstImageLeftOrTop ? loadedImageUrl.get(0) : loadedImageUrl.get(1);
        String second = isFirstImageLeftOrTop ? loadedImageUrl.get(1) : loadedImageUrl.get(0);

        loadedImageUrl.clear();
        loadedImageUrl.add(second);
        loadedImageUrl.add(first);

        if(isVertLayout)
            layoutCallback.addImageView(root, isAddViewVisible ? 0 : isFirstImageLeftOrTop ? beanShade2.getWidth1() : beanShade2.getWidth2());
        else
            layoutCallback.addImageView(root, isAddViewVisible ? 0 : beanShade2.getWidth1() >= beanShade2.getWidth2() ? beanShade2.getWidth1() : beanShade2.getWidth2());

    }
    @Override
    public void onImageShadeClick(View view) {
        switch((String)view.getTag()){
            case "img1":
                layoutCallback.imageClicked(ImageType.VIEW_DOUBLE, 1, imageUrls, loadedImageUrl);
                break;
            case "img2":
                layoutCallback.imageClicked(ImageType.VIEW_DOUBLE, 2, imageUrls, loadedImageUrl);
                break;
            case "add":
                layoutCallback.addMore();
                break;
        }
    }

    private int resultColor;
    private void generatePalette(Bitmap bitmap, final int viewId){
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultColor = Color.parseColor("#ffffffff");
                int commentColor = Color.parseColor("#33000000");
                int resultColor = 0;
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                int vibrantPopulation = vibrantSwatch == null ? 0 : vibrantSwatch.getPopulation();
                int mutedPopulation = mutedSwatch == null ? 0 : mutedSwatch.getPopulation();


                switch(Utils.getColorCombo()){
                    case VIBRANT_TO_MUTED:
                        if(vibrantPopulation > mutedPopulation)
                            resultColor = palette.getVibrantColor(defaultColor);
                        else resultColor = palette.getMutedColor(defaultColor);
                        break;
                    case MUTED_TO_VIBRANT:
                        if(vibrantPopulation > mutedPopulation)
                            resultColor = palette.getMutedColor(defaultColor);
                        else resultColor = palette.getVibrantColor(defaultColor);
                        break;
                }

                Utils.logMessage("vibrant pop = "+vibrantPopulation+"  muted pop"+mutedPopulation);

                switch(viewId){
                    case 1:
                        bindingShadeTwo.setFirstImageBgColor(resultColor);
                        bindingShadeTwo.setFirstCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                    case 2:
                        bindingShadeTwo.setSecondImageBgColor(resultColor);
                        bindingShadeTwo.setSecondCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                }

                if(ImageShadingTwo.this.resultColor == 0) ImageShadingTwo.this.resultColor = resultColor;
                else {
                    int mixedColor = Utils.getMixedArgbColor(ImageShadingTwo.this.resultColor, resultColor);
                    int inverseColor = Utils.getInverseColor(mixedColor);
                    layoutCallback.setColorsToAddMoreView(resultColor, mixedColor, inverseColor);

                    bindingShadeTwo.setDividerVisible(Utils.showShowDivider());
                    bindingShadeTwo.setDividerColor(inverseColor);



                    if(bindingShadeTwo.isAddVisibility()){
                        if(bindingShadeTwo.isAddAsCounter()){
                            bindingShadeTwo.setAddTextColor(defaultColor);
                            bindingShadeTwo.setAddBgColor(commentColor);
                        }else{
                            bindingShadeTwo.setAddBgColor(mixedColor);
                            bindingShadeTwo.setAddTextColor(inverseColor);
                        }
                    }
                }
            }
        });
    }
}
