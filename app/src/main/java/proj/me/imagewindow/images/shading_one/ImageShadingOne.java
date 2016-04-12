package proj.me.imagewindow.images.shading_one;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

import proj.me.imagewindow.R;
import proj.me.imagewindow.databinding.ViewSingleBinding;
import proj.me.imagewindow.images.ImageCallback;
import proj.me.imagewindow.images.ImageClickHandler;
import proj.me.imagewindow.images.ImageType;
import proj.me.imagewindow.images.dimentions.BeanShade1;
import proj.me.imagewindow.images.dimentions.ShadeOne;
import proj.me.imagewindow.window.Utils;

/**
 * Created by Deepak.Tiwari on 28-09-2015.
 */
public final class ImageShadingOne implements ImageClickHandler, Palette.PaletteAsyncListener {
    private Context context;
    private LayoutInflater inflater;
    private ImageCallback layoutCallback;
    private int totalImages;
    private List<Integer> imageWidthAndHeight;

    private String imageUrl;
    private List<String> imageUrls;
    private boolean result;

    private BindingShadeOne shadingOneBinding;


    public ImageShadingOne(Context context, ImageCallback layoutCallback, int totalImages){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.layoutCallback = layoutCallback;
        this.totalImages = totalImages;
        imageWidthAndHeight = new ArrayList<>();
    }

    /**
     * should be called on any single image ui, either failed so it can show a default image set in the utils
     * or the loaded image via piccaso
     * */
    public void updateFailedOrSingleUi(boolean result, Bitmap bitmap, String imageText, List<String> imageUrls, String imageUrl){
        this.imageUrl = imageUrl;
        this.imageUrls = imageUrls;
        this.result = result;

        shadingOneBinding = new BindingShadeOne();

        ViewSingleBinding viewSingleBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_single, null));

        viewSingleBinding.setClickHandler(this);
        viewSingleBinding.setShadeOne(shadingOneBinding);


        //image counter
        shadingOneBinding.setImageCounterVisibility(false);

        //comments
        shadingOneBinding.setShouldCommentVisible(Utils.shouldShowComments());


        int width, height;
        if(!result){
            width =0;height=0;
            shadingOneBinding.setImageCounterVisibility(true);
            shadingOneBinding.setImageCounterText("+"+totalImages);
        }else {
            width = bitmap.getWidth();
            height = bitmap.getHeight();

            //has not been done in others
            imageWidthAndHeight.add(width);
            imageWidthAndHeight.add(height);

            shadingOneBinding.setComment(imageText);
            if(totalImages>1) {
                shadingOneBinding.setImageCounterVisibility(true);
                shadingOneBinding.setImageCounterText("+" + (totalImages - 1));
            }
        }

        BeanShade1 beanShade1 = ShadeOne.calculateDimentions(width, height);

        ImageView singleImage = (ImageView) viewSingleBinding.getRoot().findViewById(R.id.view_single_image);


        BindingShadeOne.setLayoutWidth(singleImage, beanShade1.getWidth1());
        BindingShadeOne.setLayoutHeight(singleImage, beanShade1.getHeight1());

        //the calculations were their if needed

        if(!result && bitmap == null)
            bitmap = BitmapFactory.decodeResource(context.getResources(), Utils.getErrorDrawableId());

        BindingShadeOne.setBitmap(singleImage, bitmap);
        shadingOneBinding.setImageScaleType(Utils.getImageScaleType());

        layoutCallback.addImageView(viewSingleBinding.getRoot(), width);
        Palette.from(bitmap).generate(this);
        if(!result) bitmap.recycle();
    }

    @Override
    public void onImageShadeClick(View view) {
        List<String> list = new ArrayList<>();
        list.add(ImageShadingOne.this.imageUrl);
        layoutCallback.imageClicked(result? ImageType.VIEW_SINGLE : null, 1, imageUrls, list);
    }

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
                /*switch(Utils.getColorPallet()){
                    case VIBRANT:
                        resultColor = palette.getVibrantColor(defaultColor);
                        Utils.logMessage("vibrant = "+resultColor);
                        break;
                    case VIBRANT_DARK:
                        resultColor = palette.getDarkVibrantColor(defaultColor);
                        Utils.logMessage("vibrant dark = "+resultColor);
                        break;
                    case VIBRANT_LIGHT:
                        resultColor = palette.getLightVibrantColor(defaultColor);
                        Utils.logMessage("vibrant light = "+resultColor);
                        break;
                    case MUTED:
                        resultColor = palette.getMutedColor(defaultColor);
                        Utils.logMessage("muted = "+resultColor);
                        break;
                    case MUTED_DARK:
                        resultColor = palette.getDarkMutedColor(defaultColor);
                        Utils.logMessage("muted dark = "+resultColor);
                        break;
                    case MUTED_LIGHT:
                        resultColor = palette.getLightMutedColor(defaultColor);
                        Utils.logMessage("muted light = "+resultColor);
                        break;
                }*/
        shadingOneBinding.setImageBackgroundColor(resultColor);
        shadingOneBinding.setCommentTextBackgroundColor(Utils.getColorWithTransparency(resultColor, 70));
        layoutCallback.setColorsToAddMoreView(resultColor, Utils.getMixedArgbColor(resultColor),
                Utils.getInverseColor(resultColor));
    }
}
