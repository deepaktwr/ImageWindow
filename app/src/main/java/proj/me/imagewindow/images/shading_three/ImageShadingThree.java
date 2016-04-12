package proj.me.imagewindow.images.shading_three;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import proj.me.imagewindow.R;
import proj.me.imagewindow.databinding.ViewTrippleHorzBinding;
import proj.me.imagewindow.databinding.ViewTrippleParrHorzBinding;
import proj.me.imagewindow.databinding.ViewTrippleParrVertBinding;
import proj.me.imagewindow.databinding.ViewTrippleVertBinding;
import proj.me.imagewindow.images.ImageCallback;
import proj.me.imagewindow.images.ImageClickHandler;
import proj.me.imagewindow.images.ImageType;
import proj.me.imagewindow.images.dimentions.BeanShade3;
import proj.me.imagewindow.images.dimentions.ImageOrder;
import proj.me.imagewindow.images.dimentions.LayoutType;
import proj.me.imagewindow.images.dimentions.ShadeThree;
import proj.me.imagewindow.images.shading_two.BindingShadeTwo;
import proj.me.imagewindow.window.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak.Tiwari on 29-09-2015.
 */
public class ImageShadingThree implements ImageClickHandler{
    private LayoutInflater inflater;
    private ImageCallback layoutCallback;
    private int totalImages;

    private BindingShadeThree bindingShadeThree;

    public ImageShadingThree(Context context, ImageCallback layoutCallback, int totalImages) {
        inflater = LayoutInflater.from(context);
        this.layoutCallback = layoutCallback;
        this.totalImages = totalImages;
    }

    private List<String> imageUrls;
    private List<String> loadedImageUrls;

    public void updateTripleUi(List<Bitmap> images, List<String> imageTexts, List<String> imageUrls, List<String> loadedImageUrls) {
        this.imageUrls = imageUrls;
        this.loadedImageUrls = loadedImageUrls;
        int width1 = images.get(0).getWidth();
        int height1 = images.get(0).getHeight();

        int width2 = images.get(1).getWidth();
        int height2 = images.get(1).getHeight();

        int width3 = images.get(2).getWidth();
        int height3 = images.get(2).getHeight();

        Log.e("MAX_WIDTH", ""+Utils.MAX_WIDTH);
        Log.e("MAX_HEIGHT", ""+Utils.MAX_HEIGHT);
        Log.e("MIN_WIDTH", ""+Utils.MIN_WIDTH);
        Log.e("MIN_HIGHT", ""+Utils.MIN_HIGHT);

        Log.e("getWidth1 : ", ""+width1);
        Log.e("getHeight1 : ", ""+height1);
        Log.e("getWidth2 : ", ""+width2);
        Log.e("getHeight2 : ", ""+height2);
        Log.e("getWidth3 : ", ""+width3);
        Log.e("getHeight3 : ", ""+height3+"\n\n");

        BeanShade3 beanShade3 = ShadeThree.calculateDimentions(width1, height1, width2, height2, width3, height3);

        Log.e("Start", "++++++++++++++++++++++++++++++++++++Start");
        Log.e("getWidth1 : ", ""+beanShade3.getWidth1());
        Log.e("getHeight1 : ", ""+beanShade3.getHeight1());
        Log.e("getWidth2 : ", ""+beanShade3.getWidth2());
        Log.e("getHeight2 : ", ""+beanShade3.getHeight2());
        Log.e("getWidth3 : ", ""+beanShade3.getWidth3());
        Log.e("getHeight3 : ", ""+beanShade3.getHeight3());
        for(int i=0;i<3;i++) Log.e("image order : ", ""+beanShade3.getImageOrderList().get(i));

        Log.e("layoutType : ", ""+beanShade3.getLayoutType());
        Log.e("End", "++++++++++++++++++++++++++++++++++++End");

        String f  = loadedImageUrls.get(0);
        String s  = loadedImageUrls.get(1);
        String t  = loadedImageUrls.get(2);

        loadedImageUrls.clear();

        bindingShadeThree = new BindingShadeThree();
        View root = null;

        switch(beanShade3.getLayoutType()){
            case PARALLEL_VERT:
                ViewTrippleParrVertBinding viewTrippleParrVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_parr_vert, null));
                viewTrippleParrVertBinding.setClickHandler(this);
                viewTrippleParrVertBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleParrVertBinding.getRoot();
                break;
            case PARALLEL_HORZ:
                ViewTrippleParrHorzBinding viewTrippleParrHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_parr_horz, null));
                viewTrippleParrHorzBinding.setClickHandler(this);
                viewTrippleParrHorzBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleParrHorzBinding.getRoot();
                break;
            case VERT:
                ViewTrippleVertBinding viewTrippleVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_vert, null));
                viewTrippleVertBinding.setClickHandler(this);
                viewTrippleVertBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleVertBinding.getRoot();
                break;
            case HORZ:
                ViewTrippleHorzBinding viewTrippleHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_tripple_horz, null));
                viewTrippleHorzBinding.setClickHandler(this);
                viewTrippleHorzBinding.setShadeThree(bindingShadeThree);
                root = viewTrippleHorzBinding.getRoot();
                break;
        }

        Bitmap bitmap1 = null, bitmap2 = null, bitmap3 = null;

        switch (beanShade3.getImageOrderList().get(0)){
            case FIRST:bitmap1 = images.get(0);bindingShadeThree.setFirstComment(imageTexts.get(0));loadedImageUrls.add(f);break;
            case SECOND:bitmap1 = images.get(1);bindingShadeThree.setFirstComment(imageTexts.get(1));loadedImageUrls.add(s);break;
            case THIRD:bitmap1 = images.get(2);bindingShadeThree.setFirstComment(imageTexts.get(2));loadedImageUrls.add(t);break;
        }switch (beanShade3.getImageOrderList().get(1)){
            case FIRST:bitmap2 = images.get(0);bindingShadeThree.setSecondComment(imageTexts.get(0));loadedImageUrls.add(f);break;
            case SECOND:bitmap2 = images.get(1);bindingShadeThree.setSecondComment(imageTexts.get(1));loadedImageUrls.add(s);break;
            case THIRD:bitmap2 = images.get(2);bindingShadeThree.setSecondComment(imageTexts.get(2));loadedImageUrls.add(t);break;
        }switch (beanShade3.getImageOrderList().get(2)){
            case FIRST:bitmap3 = images.get(0);bindingShadeThree.setThirdComment(imageTexts.get(0));loadedImageUrls.add(f);break;
            case SECOND:bitmap3 = images.get(1);bindingShadeThree.setThirdComment(imageTexts.get(1));loadedImageUrls.add(s);break;
            case THIRD:bitmap3 = images.get(2);bindingShadeThree.setThirdComment(imageTexts.get(2));loadedImageUrls.add(t);break;
        }

        ImageView imageView1 = (ImageView)root.findViewById(R.id.view_triple_image1);
        ImageView imageView2 = (ImageView)root.findViewById(R.id.view_triple_image2);
        ImageView imageView3 = (ImageView)root.findViewById(R.id.view_triple_image3);

        if(totalImages > 3) {
            bindingShadeThree.setCounterVisibility(true);
            bindingShadeThree.setCounterText("+" + (totalImages - 3));
        }

        BindingShadeThree.setLayoutWidth(imageView1, beanShade3.getWidth1());
        BindingShadeThree.setLayoutWidth(imageView2, beanShade3.getWidth2());
        BindingShadeThree.setLayoutWidth(imageView3, beanShade3.getWidth3());

        BindingShadeThree.setLayoutHeight(imageView1, beanShade3.getHeight1());
        BindingShadeThree.setLayoutHeight(imageView2, beanShade3.getHeight2());
        BindingShadeThree.setLayoutHeight(imageView3, beanShade3.getHeight3());

        bindingShadeThree.setFirstImageScaleType(Utils.getImageScaleType());
        bindingShadeThree.setSecondImageScaleType(Utils.getImageScaleType());
        bindingShadeThree.setThirdImageScaleType(Utils.getImageScaleType());

        BindingShadeThree.setBitmap(imageView1, bitmap1);
        generatePalette(bitmap1, 0);
        BindingShadeThree.setBitmap(imageView2, bitmap2);
        generatePalette(bitmap2, 1);
        BindingShadeThree.setBitmap(imageView3, bitmap3);
        generatePalette(bitmap3, 2);

        bindingShadeThree.setFirstCommentVisibility(Utils.shouldShowComments());
        bindingShadeThree.setSecondCommentVisibility(Utils.shouldShowComments());
        bindingShadeThree.setThirdCommentVisibility(Utils.shouldShowComments());

        //a/c to layout type
        switch (beanShade3.getLayoutType()){
            case PARALLEL_VERT:
                layoutCallback.addImageView(root, beanShade3.getWidth1() + beanShade3.getWidth2() + beanShade3.getWidth3());
                break;
            case PARALLEL_HORZ:
                layoutCallback.addImageView(root, beanShade3.getWidth1());
                break;
            case VERT:
                layoutCallback.addImageView(root, beanShade3.getWidth1() + beanShade3.getWidth2());
                break;
            case HORZ:
                layoutCallback.addImageView(root, beanShade3.getWidth1());
                break;
        }
    }

    @Override
    public void onImageShadeClick(View view) {
        switch((String)view.getTag()){
            case "img1":
                layoutCallback.imageClicked(ImageType.VIEW_TRIPLE_1, 1, imageUrls, loadedImageUrls);
                break;
            case "img2":
                layoutCallback.imageClicked(ImageType.VIEW_TRIPLE_1, 2, imageUrls, loadedImageUrls);
                break;
            case "img3":
                layoutCallback.imageClicked(ImageType.VIEW_TRIPLE_1, 3, imageUrls, loadedImageUrls);
                break;
        }
    }

    private int[] resultColor = new int[3];

    private void generatePalette(Bitmap bitmap, final int viewId){
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultColor = Color.parseColor("#ffffffff");
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
                    case 0:
                        bindingShadeThree.setFirstImageBgColor(resultColor);
                        bindingShadeThree.setFirstCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                    case 1:
                        bindingShadeThree.setSecondImageBgColor(resultColor);
                        bindingShadeThree.setSecondCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                    case 2:
                        bindingShadeThree.setThirdImageBgColor(resultColor);
                        bindingShadeThree.setThirdCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                }
                ImageShadingThree.this.resultColor[viewId] = resultColor;

                if(ImageShadingThree.this.resultColor[0] == 0 || ImageShadingThree.this.resultColor[1] == 0 ||ImageShadingThree.this.resultColor[2] == 0) return;

                int mixedColor = Utils.getMixedArgbColor(ImageShadingThree.this.resultColor);
                int inverseColor = Utils.getInverseColor(mixedColor);
                layoutCallback.setColorsToAddMoreView(resultColor, mixedColor, inverseColor);

                bindingShadeThree.setDividerVisible(Utils.showShowDivider());
                bindingShadeThree.setDividerColor(inverseColor);
            }
        });
    }
}
