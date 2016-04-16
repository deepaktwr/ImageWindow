package proj.me.imagewindow.images.shading_four;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import proj.me.imagewindow.R;
import proj.me.imagewindow.databinding.ViewMultipleHorzBinding;
import proj.me.imagewindow.databinding.ViewMultipleHorzDoubleBinding;
import proj.me.imagewindow.databinding.ViewMultipleHorzVertBinding;
import proj.me.imagewindow.databinding.ViewMultipleVaryHeightBinding;
import proj.me.imagewindow.databinding.ViewMultipleVaryWidthBinding;
import proj.me.imagewindow.databinding.ViewMultipleVertBinding;
import proj.me.imagewindow.databinding.ViewMultipleVertDoubleBinding;
import proj.me.imagewindow.databinding.ViewMultipleVertHorzBinding;
import proj.me.imagewindow.images.ImageCallback;
import proj.me.imagewindow.images.ImageClickHandler;
import proj.me.imagewindow.images.ImageType;
import proj.me.imagewindow.images.dimentions.BeanShade4;
import proj.me.imagewindow.images.dimentions.ShadeFour;
import proj.me.imagewindow.window.Utils;

import java.util.List;

/**
 * Created by Deepak.Tiwari on 29-09-2015.
 */
public class ImageShadingFour implements ImageClickHandler{
    private LayoutInflater inflater;
    private ImageCallback layoutCallback;
    private int totalImages;

    public ImageShadingFour(Context context, ImageCallback layoutCallback, int totalImages) {
        inflater = LayoutInflater.from(context);
        this.layoutCallback = layoutCallback;
        this.totalImages = totalImages;
    }
    private List<String> imageUrls;
    private List<String> loadedImageUrls;

    private BindingShadeFour bindingShadeFour;

    public void updateOctalUi(List<Bitmap> images, List<String> imageTexts, List<String> imageUrls, List<String> loadedImageUrls) {
        this.imageUrls = imageUrls;

        int width1 = images.get(0).getWidth();
        int height1 = images.get(0).getHeight();

        int width2 = images.get(1).getWidth();
        int height2 = images.get(1).getHeight();

        int width3 = images.get(2).getWidth();
        int height3 = images.get(2).getHeight();

        int width4 = images.get(3).getWidth();
        int height4 = images.get(3).getHeight();

        Log.e("MAX_WIDTH", ""+Utils.MAX_WIDTH);
        Log.e("MAX_HEIGHT", ""+Utils.MAX_HEIGHT);
        Log.e("MIN_WIDTH", ""+Utils.MIN_WIDTH);
        Log.e("MIN_HIGHT", ""+Utils.MIN_HIGHT);

        Log.e("getWidth1 : ", ""+width1);
        Log.e("getHeight1 : ", ""+height1);
        Log.e("getWidth2 : ", ""+width2);
        Log.e("getHeight2 : ", ""+height2);
        Log.e("getWidth3 : ", ""+width3);
        Log.e("getHeight3 : ", ""+height3);
        Log.e("getWidth4 : ", ""+width4);
        Log.e("getHeight4 : ", ""+height4+"\n\n");


        BeanShade4 beanShade4 = ShadeFour.calculateDimentions(width1, height1, width2, height2, width3, height3, width4, height4);
        Log.e("Start", "++++++++++++++++++++++++++++++++++++Start");
        Log.e("getWidth1 : ", ""+beanShade4.getWidth1());
        Log.e("getHeight1 : ", ""+beanShade4.getHeight1());
        Log.e("getWidth2 : ", ""+beanShade4.getWidth2());
        Log.e("getHeight2 : ", ""+beanShade4.getHeight2());
        Log.e("getWidth3 : ", ""+beanShade4.getWidth3());
        Log.e("getHeight3 : ", ""+beanShade4.getHeight3());
        Log.e("getWidth4 : ", ""+beanShade4.getWidth4());
        Log.e("getHeight4 : ", ""+beanShade4.getHeight4());
        for(int i=0;i<4;i++) Log.e("image order : ", ""+beanShade4.getImageOrderList().get(i));

        Log.e("layoutType : ", ""+beanShade4.getLayoutType());
        Log.e("End", "++++++++++++++++++++++++++++++++++++End");

        String f  = loadedImageUrls.get(0);
        String s  = loadedImageUrls.get(1);
        String t  = loadedImageUrls.get(2);
        String fh  = loadedImageUrls.get(3);

        loadedImageUrls.clear();

        bindingShadeFour = new BindingShadeFour();
        View root = null;

        switch(beanShade4.getLayoutType()){
            case VERT:
                ViewMultipleVertBinding viewMultipleVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_vert, null));
                viewMultipleVertBinding.setClickHandler(this);
                viewMultipleVertBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleVertBinding.getRoot();
                break;
            case HORZ:
                ViewMultipleHorzBinding viewMultipleHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_horz, null));
                viewMultipleHorzBinding.setClickHandler(this);
                viewMultipleHorzBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleHorzBinding.getRoot();
                break;
            case VERT_DOUBLE:
                ViewMultipleVertDoubleBinding viewMultipleVertDoubleBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_vert_double, null));
                viewMultipleVertDoubleBinding.setClickHandler(this);
                viewMultipleVertDoubleBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleVertDoubleBinding.getRoot();
                break;
            case HORZ_DOUBLE:
                ViewMultipleHorzDoubleBinding viewMultipleHorzDoubleBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_horz_double, null));
                viewMultipleHorzDoubleBinding.setClickHandler(this);
                viewMultipleHorzDoubleBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleHorzDoubleBinding.getRoot();
                break;
            case VERT_HORZ:
                ViewMultipleVertHorzBinding viewMultipleVertHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_vert_horz, null));
                viewMultipleVertHorzBinding.setClickHandler(this);
                viewMultipleVertHorzBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleVertHorzBinding.getRoot();
                break;
            case HORZ_VERT:
                ViewMultipleHorzVertBinding viewMultipleHorzVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_horz_vert, null));
                viewMultipleHorzVertBinding.setClickHandler(this);
                viewMultipleHorzVertBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleHorzVertBinding.getRoot();
                break;
            case IDENTICAL_VARY_WIDTH:
                ViewMultipleVaryWidthBinding viewMultipleVaryWidthBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_vary_width, null));
                viewMultipleVaryWidthBinding.setClickHandler(this);
                viewMultipleVaryWidthBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleVaryWidthBinding.getRoot();
                break;
            case IDENTICAL_VARY_HEIGHT:
                ViewMultipleVaryHeightBinding viewMultipleVaryHeightBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_vary_height, null));
                viewMultipleVaryHeightBinding.setClickHandler(this);
                viewMultipleVaryHeightBinding.setShadeFour(bindingShadeFour);
                root = viewMultipleVaryHeightBinding.getRoot();
                break;
        }



        Bitmap bitmap1 = null, bitmap2 = null, bitmap3 = null, bitmap4 = null;

        switch (beanShade4.getImageOrderList().get(0)){
            case FIRST:bitmap1 = images.get(0);bindingShadeFour.setFirstComment(imageTexts.get(0));loadedImageUrls.add(f);break;
            case SECOND:bitmap1 = images.get(1);bindingShadeFour.setFirstComment(imageTexts.get(1));loadedImageUrls.add(s);break;
            case THIRD:bitmap1 = images.get(2);bindingShadeFour.setFirstComment(imageTexts.get(2));loadedImageUrls.add(t);break;
            case FOURTH:bitmap1 = images.get(3);bindingShadeFour.setFirstComment(imageTexts.get(3));loadedImageUrls.add(fh);break;
        }switch (beanShade4.getImageOrderList().get(1)){
            case FIRST:bitmap2 = images.get(0);bindingShadeFour.setSecondComment(imageTexts.get(0));loadedImageUrls.add(f);break;
            case SECOND:bitmap2 = images.get(1);bindingShadeFour.setSecondComment(imageTexts.get(1));loadedImageUrls.add(s);break;
            case THIRD:bitmap2 = images.get(2);bindingShadeFour.setSecondComment(imageTexts.get(2));loadedImageUrls.add(t);break;
            case FOURTH:bitmap2 = images.get(3);bindingShadeFour.setSecondComment(imageTexts.get(3));loadedImageUrls.add(fh);break;
        }switch (beanShade4.getImageOrderList().get(2)){
            case FIRST:bitmap3 = images.get(0);bindingShadeFour.setThirdComment(imageTexts.get(0));loadedImageUrls.add(f);break;
            case SECOND:bitmap3 = images.get(1);bindingShadeFour.setThirdComment(imageTexts.get(1));loadedImageUrls.add(s);break;
            case THIRD:bitmap3 = images.get(2);bindingShadeFour.setThirdComment(imageTexts.get(2));loadedImageUrls.add(t);break;
            case FOURTH:bitmap3 = images.get(3);bindingShadeFour.setThirdComment(imageTexts.get(3));loadedImageUrls.add(fh);break;
        }switch (beanShade4.getImageOrderList().get(3)){
            case FIRST:bitmap4 = images.get(0);bindingShadeFour.setFourthComment(imageTexts.get(0));loadedImageUrls.add(f);break;
            case SECOND:bitmap4 = images.get(1);bindingShadeFour.setFourthComment(imageTexts.get(1));loadedImageUrls.add(s);break;
            case THIRD:bitmap4 = images.get(2);bindingShadeFour.setFourthComment(imageTexts.get(2));loadedImageUrls.add(t);break;
            case FOURTH:bitmap4 = images.get(3);bindingShadeFour.setFourthComment(imageTexts.get(3));loadedImageUrls.add(fh);break;
        }


        ImageView imageView1 = (ImageView)root.findViewById(R.id.view_multiple_image1);
        ImageView imageView2 = (ImageView)root.findViewById(R.id.view_multiple_image2);
        ImageView imageView3 = (ImageView)root.findViewById(R.id.view_multiple_image3);
        ImageView imageView4 = (ImageView)root.findViewById(R.id.view_multiple_image4);

        if(totalImages > 4) {
            bindingShadeFour.setCounterVisibility(true);
            bindingShadeFour.setCounterText("+" + (totalImages - 4));
        }




        BindingShadeFour.setLayoutWidth(imageView1, beanShade4.getWidth1());
        BindingShadeFour.setLayoutWidth(imageView2, beanShade4.getWidth2());
        BindingShadeFour.setLayoutWidth(imageView3, beanShade4.getWidth3());
        BindingShadeFour.setLayoutWidth(imageView4, beanShade4.getWidth4());

        BindingShadeFour.setLayoutHeight(imageView1, beanShade4.getHeight1());
        BindingShadeFour.setLayoutHeight(imageView2, beanShade4.getHeight2());
        BindingShadeFour.setLayoutHeight(imageView3, beanShade4.getHeight3());
        BindingShadeFour.setLayoutHeight(imageView4, beanShade4.getHeight4());

        bindingShadeFour.setFirstImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setSecondImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setThirdImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setFourthImageScaleType(Utils.getImageScaleType());

        BindingShadeFour.setBitmap(imageView1, bitmap1);
        generatePalette(bitmap1, 0);
        BindingShadeFour.setBitmap(imageView2, bitmap2);
        generatePalette(bitmap2, 1);
        BindingShadeFour.setBitmap(imageView3, bitmap3);
        generatePalette(bitmap3, 2);
        BindingShadeFour.setBitmap(imageView4, bitmap4);
        generatePalette(bitmap4, 3);


        bindingShadeFour.setFirstCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setSecondCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setThirdCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setFourthCommentVisibility(Utils.shouldShowComments());


        //a/c to layout type
        switch (beanShade4.getLayoutType()){
            case VERT:
                layoutCallback.addImageView(root, beanShade4.getWidth1());
                break;
            case HORZ:
                layoutCallback.addImageView(root, beanShade4.getWidth1() + beanShade4.getWidth2());
                break;
            case VERT_DOUBLE:
                layoutCallback.addImageView(root, beanShade4.getWidth1());
                break;
            case HORZ_DOUBLE:
                layoutCallback.addImageView(root, beanShade4.getWidth1() + beanShade4.getWidth2() + beanShade4.getWidth3());
                break;
            case VERT_HORZ:
                layoutCallback.addImageView(root, beanShade4.getWidth1());
                break;
            case HORZ_VERT:
                layoutCallback.addImageView(root, beanShade4.getWidth1() + beanShade4.getWidth2());
                break;
            case IDENTICAL_VARY_WIDTH:
                layoutCallback.addImageView(root, beanShade4.getWidth1() + beanShade4.getWidth2());
                break;
            case IDENTICAL_VARY_HEIGHT:
                layoutCallback.addImageView(root, beanShade4.getWidth1() + beanShade4.getWidth3());
                break;
        }
    }

    @Override
    public void onImageShadeClick(View view) {
        switch((String)view.getTag()){
            case "img1":
                layoutCallback.imageClicked(ImageType.VIEW_MULTIPLE_1, 1, imageUrls, ImageShadingFour.this.loadedImageUrls);
                break;
            case "img2":
                layoutCallback.imageClicked(ImageType.VIEW_MULTIPLE_1, 2, imageUrls, ImageShadingFour.this.loadedImageUrls);
                break;
            case "img3":
                layoutCallback.imageClicked(ImageType.VIEW_MULTIPLE_1, 3, imageUrls, ImageShadingFour.this.loadedImageUrls);
                break;
            case "img4":
                layoutCallback.imageClicked(ImageType.VIEW_MULTIPLE_1, 4, imageUrls, ImageShadingFour.this.loadedImageUrls);
                break;
        }
    }


    private int[] resultColor = new int[4];

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
                        bindingShadeFour.setFirstImageBgColor(resultColor);
                        bindingShadeFour.setFirstCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                    case 1:
                        bindingShadeFour.setSecondImageBgColor(resultColor);
                        bindingShadeFour.setSecondCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                    case 2:
                        bindingShadeFour.setThirdImageBgColor(resultColor);
                        bindingShadeFour.setThirdCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                    case 3:
                        bindingShadeFour.setFourthImageBgColor(resultColor);
                        bindingShadeFour.setFourthCommentBgColor(Utils.getColorWithTransparency(resultColor, Utils.COMMENT_TRANSPARENCY_PERCENT));
                        break;
                }
                ImageShadingFour.this.resultColor[viewId] = resultColor;

                if(ImageShadingFour.this.resultColor[0] == 0 || ImageShadingFour.this.resultColor[1] == 0 || ImageShadingFour.this.resultColor[2] == 0 || ImageShadingFour.this.resultColor[3] == 0) return;

                int mixedColor = Utils.getMixedArgbColor(ImageShadingFour.this.resultColor);
                int inverseColor = Utils.getInverseColor(mixedColor);
                layoutCallback.setColorsToAddMoreView(resultColor, mixedColor, inverseColor);

                bindingShadeFour.setDividerVisible(Utils.showShowDivider());
                bindingShadeFour.setDividerColor(inverseColor);
            }
        });
    }
}
