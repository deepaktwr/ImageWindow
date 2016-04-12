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
import android.widget.RelativeLayout;
import android.widget.TextView;

import proj.me.imagewindow.R;
import proj.me.imagewindow.databinding.ViewMultipleFourBinding;
import proj.me.imagewindow.databinding.ViewMultipleHorzBinding;
import proj.me.imagewindow.databinding.ViewMultipleVertBinding;
import proj.me.imagewindow.images.ImageCallback;
import proj.me.imagewindow.images.ImageClickHandler;
import proj.me.imagewindow.images.ImageType;
import proj.me.imagewindow.window.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak.Tiwari on 29-09-2015.
 */
public class ImageShadingFour implements ImageClickHandler{
    private Context context;
    private final int maxWidth, minWidth;
    private final int maxHeight, minHeight;
    private LayoutInflater inflater;
    private ImageCallback layoutCallback;
    private int totalImages;
    private List<Integer> wh;

    public ImageShadingFour(Context context, ImageCallback layoutCallback, int minWidth, int minHeight, int maxWidth, int maxHeight, int totalImages) {
        this.context = context;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight + 100;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        inflater = LayoutInflater.from(context);
        this.layoutCallback = layoutCallback;
        this.totalImages = totalImages;
        wh = new ArrayList<>();
    }
    private List<String> imageUrls;
    private List<String> loadedImageUrls;

    private BindingShadeFour bindingShadeFour;

    public void updateOctalUi(List<Bitmap> images, List<String> imageTexts, List<String> imageUrls, List<String> loadedImageUrls) {
        this.imageUrls = imageUrls;
        String f = null, s= null, t= null, fh=null;
        int width1 = images.get(0).getWidth();
        int height1 = images.get(0).getHeight();

        int width2 = images.get(1).getWidth();
        int height2 = images.get(1).getHeight();

        int width3 = images.get(2).getWidth();
        int height3 = images.get(2).getHeight();

        int width4 = images.get(3).getWidth();
        int height4 = images.get(3).getHeight();

        boolean isFirstMaxW, isSecondMaxW = false, isThirdMaxW = false, isFourthMaxW = false, isFirstMaxH, isSecondMaxH = false, isThirdMaxH = false, isFourthMaxH = false;
        boolean isFirstMaxWS, isSecondMaxWS = false, isThirdMaxWS = false, isFourthMaxWS = false, isFirstMaxHS, isSecondMaxHS = false, isThirdMaxHS = false, isFourthMaxHS = false;

        int maxFirstWidth = Math.max(Math.max(width1, Math.max(width2, width3)), width4);
        int maxFirstHeight = Math.max(Math.max(height1, Math.max(height2, height3)), height4);

        isFirstMaxW = maxFirstWidth == width1;
        if(!isFirstMaxW) {
            isSecondMaxW = maxFirstWidth == width2;
            if (!isSecondMaxW) {
                isThirdMaxW = maxFirstWidth == width3;
                if (!isThirdMaxW) isFourthMaxW = maxFirstWidth == width4;
            }
        }

        isFirstMaxH = maxFirstHeight == height1;
        if(!isFirstMaxH) {
            isSecondMaxH = maxFirstHeight == height2;
            if (!isSecondMaxH) {
                isThirdMaxH = maxFirstHeight == height3;
                if (!isThirdMaxH) isFourthMaxH = maxFirstHeight == height4;
            }
        }

        int maxSecondWidth = isFirstMaxW ? Math.max(width4, Math.max(width2, width3)) : (isSecondMaxW ?
                Math.max(width4, Math.max(width1, width3)) : isThirdMaxW ? Math.max(width4, Math.max(width2, width1)) :
                Math.max(width2, Math.max(width1, width3)));
        int maxSecondHeight = isFirstMaxH ? Math.max(height4, Math.max(height2, height3)) : (isSecondMaxH ?
                Math.max(height4, Math.max(height1, height3)) : isThirdMaxH ? Math.max(height4, Math.max(height2, height1)) :
                Math.max(height2, Math.max(height1, height3)));

        isFirstMaxWS = !isFirstMaxW && maxSecondWidth == width1;
        if(!isFirstMaxWS) {
            isSecondMaxWS = !isSecondMaxW && maxSecondWidth == width2;
            if (!isSecondMaxWS) {
                isThirdMaxWS = !isThirdMaxW && maxSecondWidth == width3;
                if (!isThirdMaxWS) isFourthMaxWS = !isFourthMaxW && maxSecondWidth == width4;
            }
        }
        isFirstMaxHS = !isFirstMaxH && maxSecondHeight == height1;
        if(!isFirstMaxHS) {
            isSecondMaxHS = !isSecondMaxH && maxSecondHeight == height2;
            if (!isSecondMaxHS) {
                isThirdMaxHS = !isThirdMaxH && maxSecondHeight == height3;
                if (!isThirdMaxHS) isFourthMaxHS = !isFourthMaxH && maxSecondHeight == height4;
            }
        }

        float ratioW = maxFirstWidth>=maxSecondWidth?(float)maxFirstWidth/(maxFirstWidth + maxSecondWidth):(float)maxSecondWidth/(maxFirstWidth + maxSecondWidth);
        float ratioH = maxFirstHeight>=maxSecondHeight?(float)maxFirstHeight/(maxFirstHeight + maxSecondHeight):(float)maxSecondHeight/(maxFirstHeight + maxSecondHeight);

        int imageWidth, imageHeight, imageHeight1, imageHeight2, imageHeight3, imageHeight4, imageWidth1, imageWidth2, imageWidth3, imageWidth4;
        bindingShadeFour = new BindingShadeFour();
        if(ratioW<=0.65f && ratioH <= 0.65f){
            ViewMultipleFourBinding viewMultipleFourBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_four, null));
            viewMultipleFourBinding.setClickHandler(this);
            viewMultipleFourBinding.setShadeFour(bindingShadeFour);
            if(ratioW >= ratioH){
                int avgWidth = ((width1 + width2) + (width3 + width4))/2;
                imageWidth = avgWidth > this.maxWidth ? this.maxWidth : (avgWidth < minWidth ? minWidth : avgWidth);

                float maxAvgHeight = (float)(maxFirstHeight + maxSecondHeight)/2;
                int minTwoWidths = width1 + width2 + width3 + width4 - maxFirstWidth - maxSecondWidth;
                int minTwoHeight = height1 + height2 + height3 + height4 - maxFirstHeight - maxSecondHeight;
                float minAvgHeight = (float)(minTwoHeight)/2;
                int sumHeight = (int)(maxAvgHeight + minAvgHeight);

                imageHeight = sumHeight > this.maxHeight ? this.maxHeight : (sumHeight < minHeight ? minHeight : sumHeight);

                float ratioHH = (float)maxAvgHeight/(maxAvgHeight+minAvgHeight);

                ratioHH = ratioHH > 0.7f ? 0.7f : (ratioHH < 0.3f ? 0.3f : ratioHH);
                imageHeight1  = (int)((float)imageHeight * ratioHH);
                imageHeight2  = imageHeight - imageHeight1;

                imageHeight1 = imageHeight1>=imageHeight2?imageHeight1:imageHeight2;
                imageHeight2 = imageHeight - imageHeight1;

                float ratioWW1 = ((float)maxFirstWidth/(maxFirstWidth + maxSecondWidth));
                ratioWW1 = ratioWW1 > 0.7f ? 0.7f : (ratioWW1 < 0.3f ? 0.3f : ratioWW1);

                imageWidth1  = (int)((float)imageWidth * ratioWW1);
                imageWidth2  = imageWidth - imageWidth1;

                imageWidth1 = imageWidth1>=imageWidth2?imageWidth1:imageWidth2;
                imageWidth2 = imageWidth - imageWidth1;

                int firstWidthVal = !isFirstMaxW && !isFirstMaxWS?0:(!isSecondMaxW && !isSecondMaxWS?1:(!isThirdMaxW && !isThirdMaxWS)?2:3);
                int secondWidthVal = !isFirstMaxW && !isFirstMaxWS && firstWidthVal!=0?0:(!isSecondMaxW && !isSecondMaxWS && firstWidthVal!=1?1:
                        (!isThirdMaxW && !isThirdMaxWS && firstWidthVal!=2)?2:3);

                int firstWidth = images.get(firstWidthVal).getWidth();
                int secondWidth = images.get(secondWidthVal).getWidth();

                float ratioWW2 = firstWidth>=secondWidth?((float)firstWidth/(firstWidth + secondWidth)):((float)secondWidth/(firstWidth + secondWidth));
                ratioWW2 = ratioWW2 > 0.7f ? 0.7f : (ratioWW2 < 0.3f ? 0.3f : ratioWW2);

                imageWidth3  = (int)((float)imageWidth * ratioWW2);
                imageWidth4  = imageWidth - imageWidth3;

                imageWidth3 = imageWidth3>=imageWidth4?imageWidth3:imageWidth4;
                imageWidth4  = imageWidth - imageWidth3;

                f = isFirstMaxW?loadedImageUrls.get(0):isSecondMaxW?loadedImageUrls.get(1):isThirdMaxW?loadedImageUrls.get(2):loadedImageUrls.get(3);
                s = isFirstMaxWS?loadedImageUrls.get(0):isSecondMaxWS?loadedImageUrls.get(1):isThirdMaxWS?loadedImageUrls.get(2):loadedImageUrls.get(3);
                t = firstWidth >= secondWidth?loadedImageUrls.get(firstWidthVal):loadedImageUrls.get(secondWidthVal);
                fh = firstWidth < secondWidth?loadedImageUrls.get(firstWidthVal):loadedImageUrls.get(secondWidthVal);
                loadedImageUrls.clear();
                Log.e("type1 width val", "" + (isFirstMaxW?0:isSecondMaxW?1:isThirdMaxW?2:3));
                Log.e("type1 width val", ""+(isFirstMaxWS?0:isSecondMaxWS?1:isThirdMaxWS?2:3));
                Log.e("type1 width val", ""+(firstWidth >= secondWidth?firstWidthVal:secondWidthVal));
                Log.e("type1 width val", "" + (firstWidth < secondWidth ? firstWidthVal : secondWidthVal));



                setOctalImagesFourType1(viewMultipleFourBinding.getRoot(), imageWidth, imageHeight, imageWidth1, imageWidth2, imageWidth3, imageWidth4, imageHeight1, imageHeight2,
                        isFirstMaxW ? images.get(0) : isSecondMaxW ? images.get(1) : isThirdMaxW ? images.get(2) : images.get(3),
                        isFirstMaxWS ? images.get(0) : isSecondMaxWS ? images.get(1) : isThirdMaxWS ? images.get(2) : images.get(3),
                        firstWidth >= secondWidth ? images.get(firstWidthVal) : images.get(secondWidthVal),
                        firstWidth < secondWidth ? images.get(firstWidthVal) : images.get(secondWidthVal),
                        imageTexts.get(isFirstMaxW?0:isSecondMaxW?1:isThirdMaxW?2:3),
                        imageTexts.get(isFirstMaxWS?0:isSecondMaxWS?1:isThirdMaxWS?2:3),
                        imageTexts.get(firstWidth >= secondWidth?firstWidthVal:secondWidthVal),
                        imageTexts.get(firstWidth < secondWidth ? firstWidthVal : secondWidthVal));
            }else{
                int avgHeight = ((height1 + height2) + (height3 + height4))/2;
                imageHeight = avgHeight > this.maxHeight ? this.maxHeight : (avgHeight < minHeight ? minHeight : avgHeight);

                float maxAvgWidth = (float)(maxFirstWidth + maxSecondWidth)/2;
                int minTwoWidths = width1 + width2 + width3 + width4 - maxFirstWidth - maxSecondWidth;
                int minTwoHeight = height1 + height2 + height3 + height4 - maxFirstHeight - maxSecondHeight;
                float minAvgWidth = (float)(minTwoWidths)/2;
                int sumWidth = (int)(maxAvgWidth + minAvgWidth);

                imageWidth = sumWidth > this.maxWidth ? this.maxWidth : (sumWidth < minWidth ? minWidth : sumWidth);

                float ratioWW = (float)maxAvgWidth/(maxAvgWidth+minAvgWidth);

                ratioWW = ratioWW > 0.7f ? 0.7f : (ratioWW < 0.3f ? 0.3f : ratioWW);
                imageWidth1  = (int)((float)imageWidth * ratioWW);
                imageWidth2  = imageWidth - imageWidth1;

                imageWidth1 = imageWidth1>=imageWidth2?imageWidth1:imageWidth2;
                imageWidth2 = imageWidth - imageWidth1;

                float ratioHH1 = ((float)maxFirstHeight/(maxFirstHeight + maxSecondHeight));
                ratioHH1 = ratioHH1 > 0.7f ? 0.7f : (ratioHH1 < 0.3f ? 0.3f : ratioHH1);

                imageHeight1  = (int)((float)imageHeight * ratioHH1);
                imageHeight2  = imageHeight - imageHeight1;

                imageHeight1 = imageHeight1>=imageHeight2?imageHeight1:imageHeight2;
                imageHeight2  = imageHeight - imageHeight1;

                int firstHeightVal = !isFirstMaxH && !isFirstMaxHS?0:(!isSecondMaxH && !isSecondMaxHS?1:(!isThirdMaxH && !isThirdMaxHS)?2:3);
                int secondHeightVal = !isFirstMaxH && !isFirstMaxHS && firstHeightVal!=0?0:(!isSecondMaxH && !isSecondMaxHS && firstHeightVal!=1?1:
                        (!isThirdMaxH && !isThirdMaxHS && firstHeightVal!=2)?2:3);

                int firstHeight = images.get(firstHeightVal).getHeight();
                int secondHeight = images.get(secondHeightVal).getHeight();

                float ratioHH2 = firstHeight>=secondHeight?((float)firstHeight/(firstHeight + secondHeight)):((float)secondHeight/(firstHeight + secondHeight));
                ratioHH2 = ratioHH2 > 0.7f ? 0.7f : (ratioHH2 < 0.3f ? 0.3f : ratioHH2);

                imageHeight3  = (int)((float)imageHeight * ratioHH2);
                imageHeight4  = imageHeight - imageHeight3;

                imageHeight3 = imageHeight3>=imageHeight4?imageHeight3:imageHeight4;
                imageHeight4  = imageHeight - imageHeight3;

                f = isFirstMaxH ? loadedImageUrls.get(0) : isSecondMaxH ? loadedImageUrls.get(1) : isThirdMaxH ? loadedImageUrls.get(2) : loadedImageUrls.get(3);
                s = isFirstMaxHS ? loadedImageUrls.get(0) : isSecondMaxHS ? loadedImageUrls.get(1) : isThirdMaxHS ? loadedImageUrls.get(2) : loadedImageUrls.get(3);
                t = firstHeight >= secondHeight ? loadedImageUrls.get(firstHeightVal) : loadedImageUrls.get(secondHeightVal);
                fh = firstHeight < secondHeight ? loadedImageUrls.get(firstHeightVal) : loadedImageUrls.get(secondHeightVal);
                loadedImageUrls.clear();

                Log.e("type2 width val", "" + (isFirstMaxH ? 0 : isSecondMaxH ? 1 : isThirdMaxH ? 2 : 3));
                Log.e("type2 width val", ""+(isFirstMaxHS ? 0 : isSecondMaxHS ? 1 : isThirdMaxHS ? 2 : 3));
                Log.e("type2 width val", ""+(firstHeight >= secondHeight ? firstHeightVal : secondHeightVal));
                Log.e("type2 width val", "" + (firstHeight < secondHeight ? firstHeightVal : secondHeightVal));
                setOctalImagesFourType2(viewMultipleFourBinding.getRoot(), imageWidth, imageHeight, imageWidth1, imageWidth2, imageHeight1, imageHeight2, imageHeight3, imageHeight4,
                        isFirstMaxH ? images.get(0) : isSecondMaxH ? images.get(1) : isThirdMaxH ? images.get(2) : images.get(3),
                        isFirstMaxHS ? images.get(0) : isSecondMaxHS ? images.get(1) : isThirdMaxHS ? images.get(2) : images.get(3),
                        firstHeight >= secondHeight ? images.get(firstHeightVal) : images.get(secondHeightVal),
                        firstHeight < secondHeight ? images.get(firstHeightVal) : images.get(secondHeightVal),
                        imageTexts.get(isFirstMaxH ? 0 : isSecondMaxH ? 1 : isThirdMaxH ? 2 : 3),
                        imageTexts.get(isFirstMaxHS ? 0 : isSecondMaxHS ? 1 : isThirdMaxHS ? 2 : 3),
                        imageTexts.get(firstHeight >= secondHeight ? firstHeightVal : secondHeightVal),
                        imageTexts.get(firstHeight < secondHeight ? firstHeightVal : secondHeightVal));
            }
        }else if(ratioW<=0.65f){
            ViewMultipleFourBinding viewMultipleFourBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_four, null));
            viewMultipleFourBinding.setClickHandler(this);
            viewMultipleFourBinding.setShadeFour(bindingShadeFour);
            int avgHeight = ((height1 + height2) + (height3 + height4))/2;
            imageHeight = avgHeight > this.maxHeight ? this.maxHeight : (avgHeight < minHeight ? minHeight : avgHeight);

            float maxAvgWidth = (float)(maxFirstWidth + maxSecondWidth)/2;
            int minTwoWidths = width1 + width2 + width3 + width4 - maxFirstWidth - maxSecondWidth;
            int minTwoHeight = height1 + height2 + height3 + height4 - maxFirstHeight - maxSecondHeight;
            float minAvgWidth = (float)(minTwoWidths)/2;
            int sumWidth = (int)(maxAvgWidth + minAvgWidth);

            imageWidth = sumWidth > this.maxWidth ? this.maxWidth : (sumWidth < minWidth ? minWidth : sumWidth);

            float ratioWW = (float)maxAvgWidth/(maxAvgWidth+minAvgWidth);

            ratioWW = ratioWW > 0.7f ? 0.7f : (ratioWW < 0.3f ? 0.3f : ratioWW);
            imageWidth1  = (int)((float)imageWidth * ratioWW);
            imageWidth2  = imageWidth - imageWidth1;

            imageWidth1 = imageWidth1>=imageWidth2?imageWidth1:imageWidth2;
            imageWidth2  = imageWidth - imageWidth1;

            float ratioHH1 = ((float)maxFirstHeight/(maxFirstHeight + maxSecondHeight));
            ratioHH1 = ratioHH1 > 0.7f ? 0.7f : (ratioHH1 < 0.3f ? 0.3f : ratioHH1);

            imageHeight1  = (int)((float)imageHeight * ratioHH1);
            imageHeight2  = imageHeight - imageHeight1;

            imageHeight1 = imageHeight1>=imageHeight2?imageHeight1:imageHeight2;
            imageHeight2  = imageHeight - imageHeight1;

            int firstHeightVal = !isFirstMaxH && !isFirstMaxHS?0:(!isSecondMaxH && !isSecondMaxHS?1:(!isThirdMaxH && !isThirdMaxHS)?2:3);
            int secondHeightVal = !isFirstMaxH && !isFirstMaxHS && firstHeightVal!=0?0:(!isSecondMaxH && !isSecondMaxHS && firstHeightVal!=1?1:
                    (!isThirdMaxH && !isThirdMaxHS && firstHeightVal!=2)?2:3);

            int firstHeight = images.get(firstHeightVal).getHeight();
            int secondHeight = images.get(secondHeightVal).getHeight();

            float ratioHH2 = firstHeight>=secondHeight?((float)firstHeight/(firstHeight + secondHeight)):((float)secondHeight/(firstHeight + secondHeight));
            ratioHH2 = ratioHH2 > 0.7f ? 0.7f : (ratioHH2 < 0.3f ? 0.3f : ratioHH2);

            imageHeight3  = (int)((float)imageHeight * ratioHH2);
            imageHeight4  = imageHeight - imageHeight3;

            imageHeight3 = imageHeight3>=imageHeight4?imageHeight3:imageHeight4;
            imageHeight4  = imageHeight - imageHeight3;

            f = isFirstMaxH ? loadedImageUrls.get(0) : isSecondMaxH ? loadedImageUrls.get(1) : isThirdMaxH ? loadedImageUrls.get(2) : loadedImageUrls.get(3);
            s = isFirstMaxHS ? loadedImageUrls.get(0) : isSecondMaxHS ? loadedImageUrls.get(1) : isThirdMaxHS ? loadedImageUrls.get(2) : loadedImageUrls.get(3);
            t = firstHeight >= secondHeight ? loadedImageUrls.get(firstHeightVal) : loadedImageUrls.get(secondHeightVal);
            fh = firstHeight < secondHeight ? loadedImageUrls.get(firstHeightVal) : loadedImageUrls.get(secondHeightVal);
            loadedImageUrls.clear();


            Log.e("type2 width val", "" + (isFirstMaxH ? 0 : isSecondMaxH ? 1 : isThirdMaxH ? 2 : 3));
            Log.e("type2 width val", ""+(isFirstMaxHS ? 0 : isSecondMaxHS ? 1 : isThirdMaxHS ? 2 : 3));
            Log.e("type2 width val", ""+(firstHeight >= secondHeight ? firstHeightVal : secondHeightVal));
            Log.e("type2 width val", "" + (firstHeight < secondHeight ? firstHeightVal : secondHeightVal));

            setOctalImagesFourType2(viewMultipleFourBinding.getRoot(), imageWidth, imageHeight, imageWidth1, imageWidth2, imageHeight1, imageHeight2, imageHeight3, imageHeight4,
                    isFirstMaxH ? images.get(0) : isSecondMaxH ? images.get(1) : isThirdMaxH ? images.get(2) : images.get(3),
                    isFirstMaxHS ? images.get(0) : isSecondMaxHS ? images.get(1) : isThirdMaxHS ? images.get(2) : images.get(3),
                    firstHeight >= secondHeight ? images.get(firstHeightVal) : images.get(secondHeightVal),
                    firstHeight < secondHeight ? images.get(firstHeightVal) : images.get(secondHeightVal),
                    imageTexts.get(isFirstMaxH ? 0 : isSecondMaxH ? 1 : isThirdMaxH ? 2 : 3),
                    imageTexts.get(isFirstMaxHS ? 0 : isSecondMaxHS ? 1 : isThirdMaxHS ? 2 : 3),
                    imageTexts.get(firstHeight >= secondHeight ? firstHeightVal : secondHeightVal),
                    imageTexts.get(firstHeight < secondHeight ? firstHeightVal : secondHeightVal));
        }else if(ratioH <= 0.65f){
            ViewMultipleFourBinding viewMultipleFourBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_four, null));
            viewMultipleFourBinding.setClickHandler(this);
            viewMultipleFourBinding.setShadeFour(bindingShadeFour);
            int avgWidth = ((width1 + width2) + (width3 + width4))/2;
            imageWidth = avgWidth > this.maxWidth ? this.maxWidth : (avgWidth < minWidth ? minWidth : avgWidth);

            float maxAvgHeight = (float)(maxFirstHeight + maxSecondHeight)/2;
            int minTwoWidths = width1 + width2 + width3 + width4 - maxFirstWidth - maxSecondWidth;
            int minTwoHeight = height1 + height2 + height3 + height4 - maxFirstHeight - maxSecondHeight;
            float minAvgHeight = (float)(minTwoHeight)/2;
            int sumHeight = (int)(maxAvgHeight + minAvgHeight);

            imageHeight = sumHeight > this.maxHeight ? this.maxHeight : (sumHeight < minHeight ? minHeight : sumHeight);

            float ratioHH = (float)maxAvgHeight/(maxAvgHeight+minAvgHeight);

            ratioHH = ratioHH > 0.7f ? 0.7f : (ratioHH < 0.3f ? 0.3f : ratioHH);
            imageHeight1  = (int)((float)imageHeight * ratioHH);
            imageHeight2  = imageHeight - imageHeight1;

            imageHeight1 = imageHeight1>=imageHeight2?imageHeight1:imageHeight2;
            imageHeight2  = imageHeight - imageHeight1;

            float ratioWW1 = ((float)maxFirstWidth/(maxFirstWidth + maxSecondWidth));
            ratioWW1 = ratioWW1 > 0.7f ? 0.7f : (ratioWW1 < 0.3f ? 0.3f : ratioWW1);

            imageWidth1  = (int)((float)imageWidth * ratioWW1);
            imageWidth2  = imageWidth - imageWidth1;

            imageWidth1 = imageWidth1>=imageWidth2?imageWidth1:imageWidth2;
            imageWidth2  = imageWidth - imageWidth1;

            int firstWidthVal = !isFirstMaxW && !isFirstMaxWS?0:(!isSecondMaxW && !isSecondMaxWS?1:(!isThirdMaxW && !isThirdMaxWS)?2:3);
            int secondWidthVal = !isFirstMaxW && !isFirstMaxWS && firstWidthVal!=0?0:(!isSecondMaxW && !isSecondMaxWS && firstWidthVal!=1?1:
                    (!isThirdMaxW && !isThirdMaxWS && firstWidthVal!=2)?2:3);

            int firstWidth = images.get(firstWidthVal).getWidth();
            int secondWidth = images.get(secondWidthVal).getWidth();

            float ratioWW2 = firstWidth>=secondWidth?((float)firstWidth/(firstWidth + secondWidth)):((float)secondWidth/(firstWidth + secondWidth));
            ratioWW2 = ratioWW2 > 0.7f ? 0.7f : (ratioWW2 < 0.3f ? 0.3f : ratioWW2);

            imageWidth3  = (int)((float)imageWidth * ratioWW2);
            imageWidth4  = imageWidth - imageWidth3;

            imageWidth3 = imageWidth3>=imageWidth4?imageWidth3:imageWidth4;
            imageWidth4  = imageWidth - imageWidth3;

            f = isFirstMaxW?loadedImageUrls.get(0):isSecondMaxW?loadedImageUrls.get(1):isThirdMaxW?loadedImageUrls.get(2):loadedImageUrls.get(3);
            s = isFirstMaxWS?loadedImageUrls.get(0):isSecondMaxWS?loadedImageUrls.get(1):isThirdMaxWS?loadedImageUrls.get(2):loadedImageUrls.get(3);
            t = firstWidth >= secondWidth?loadedImageUrls.get(firstWidthVal):loadedImageUrls.get(secondWidthVal);
            fh = firstWidth < secondWidth?loadedImageUrls.get(firstWidthVal):loadedImageUrls.get(secondWidthVal);
            loadedImageUrls.clear();

            Log.e("type1 width val", "" + (isFirstMaxW?0:isSecondMaxW?1:isThirdMaxW?2:3));
            Log.e("type1 width val", ""+(isFirstMaxWS?0:isSecondMaxWS?1:isThirdMaxWS?2:3));
            Log.e("type1 width val", ""+(firstWidth >= secondWidth?firstWidthVal:secondWidthVal));
            Log.e("type1 width val", "" + (firstWidth < secondWidth?firstWidthVal:secondWidthVal));
            setOctalImagesFourType1(viewMultipleFourBinding.getRoot(), imageWidth, imageHeight, imageWidth1, imageWidth2, imageWidth3, imageWidth4, imageHeight1, imageHeight2,
                    isFirstMaxW?images.get(0):isSecondMaxW?images.get(1):isThirdMaxW?images.get(2):images.get(3),
                    isFirstMaxWS?images.get(0):isSecondMaxWS?images.get(1):isThirdMaxWS?images.get(2):images.get(3),
                    firstWidth >= secondWidth?images.get(firstWidthVal):images.get(secondWidthVal),
                    firstWidth < secondWidth?images.get(firstWidthVal):images.get(secondWidthVal),
                    imageTexts.get(isFirstMaxW?0:isSecondMaxW?1:isThirdMaxW?2:3),
                    imageTexts.get(isFirstMaxWS?0:isSecondMaxWS?1:isThirdMaxWS?2:3),
                    imageTexts.get(firstWidth >= secondWidth?firstWidthVal:secondWidthVal),
                    imageTexts.get(firstWidth < secondWidth?firstWidthVal:secondWidthVal));
        }else{
            int remainingHeight = height1 + height2 + height3 + height4 - maxFirstHeight;
            int remainingWidth = width1 + width2 + width3 + width4 - maxFirstWidth;

            float ratioHorz = ((float)maxFirstHeight/(maxFirstHeight + remainingHeight));
            float ratioVert = ((float)maxFirstWidth/(maxFirstWidth + remainingWidth));

            if(ratioHorz >= ratioVert){
                ViewMultipleHorzBinding viewMultipleHorzBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_horz, null));
                viewMultipleHorzBinding.setClickHandler(this);
                viewMultipleHorzBinding.setShadeFour(bindingShadeFour);
                int firstHeightVal = isFirstMaxH?0:isSecondMaxH?1:isThirdMaxH?2:3;
                remainingWidth = firstHeightVal == 0 ?width2 + width3 + width4:firstHeightVal==1?width1 + width3 + width4:firstHeightVal==2?width1 + width2 + width4:width1 + width2 + width3;

                float remainWidthAvg = (float)remainingWidth/3;
                float remainHeightAvg = (float)remainingHeight/3;

                int sumWidth = (int)(images.get(firstHeightVal).getWidth() + remainWidthAvg);

                imageWidth = sumWidth > this.maxWidth ? this.maxWidth : (sumWidth < minWidth ? minWidth : sumWidth);

                float ratioWW = ((float)images.get(firstHeightVal).getWidth()/sumWidth);
                ratioWW = ratioWW > 0.7f ? 0.7f : (ratioWW < 0.3f ? 0.3f : ratioWW);
                imageWidth1 = (int)(imageWidth * ratioWW);
                imageWidth2 = imageWidth - imageWidth1;

                int avgHeight = (int)((float)(maxFirstHeight + remainingHeight)/2);
                imageHeight = avgHeight > this.maxHeight ? this.maxHeight : (avgHeight < minHeight ? minHeight : avgHeight);


                int secondHeightVal = isFirstMaxHS && firstHeightVal!=0?0:isSecondMaxHS && firstHeightVal!=1?1:isThirdMaxHS && firstHeightVal!=2?2:3;
                int thirdHeightVal = !isFirstMaxH && !isFirstMaxHS?0:(!isSecondMaxH && !isSecondMaxHS?1:(!isThirdMaxH && !isThirdMaxHS)?2:3);
                int fourthHeightVal = !isFirstMaxH && !isFirstMaxHS && thirdHeightVal!=0?0:(!isSecondMaxH && !isSecondMaxHS && thirdHeightVal!=1?1:
                        (!isThirdMaxH && !isThirdMaxHS && thirdHeightVal!=2)?2:3);

                imageHeight2 = images.get(secondHeightVal).getHeight();
                imageHeight3 = images.get(thirdHeightVal).getHeight();
                imageHeight4 = images.get(fourthHeightVal).getHeight();

                int val = thirdHeightVal;
                thirdHeightVal = imageHeight3>=imageHeight4?thirdHeightVal:fourthHeightVal;
                fourthHeightVal = thirdHeightVal==fourthHeightVal?val:fourthHeightVal;

                imageHeight3 = images.get(thirdHeightVal).getHeight();
                imageHeight4 = images.get(fourthHeightVal).getHeight();

                int sumHH = imageHeight2+imageHeight3+imageHeight4;

                float ratioH2 = ((float)imageHeight2/sumHH);
                float ratioH3 = ((float)imageHeight3/sumHH);
                float ratioH4 = ((float)imageHeight4/sumHH);

                if(ratioH4 < 0.27f){
                    ratioH4 = 0.27f;
                    float remainHeight = imageHeight * (1 - ratioH4);

                    ratioH2 = ratioH2*100f / (ratioH2*100f + ratioH3 * 100f);
                    ratioH3 = ratioH3*100f / (ratioH2*100f + ratioH3 * 100f);

                    //33 ------ 27
                    //50 ------ 40

                    ratioH3 = ratioH3<0.4f?0.4f:ratioH3;
                    ratioH2 = 1 - ratioH3;

                    imageHeight4 = (int)(imageHeight*ratioH4);
                    imageHeight3 = (int)(remainHeight*ratioH3);
                    imageHeight2 = (int)(remainHeight - imageHeight3);
                }else{
                    imageHeight4 = (int)(imageHeight*ratioH4);
                    imageHeight3 = (int)(imageHeight*ratioH3);
                    imageHeight2 = (int)(imageHeight*ratioH2);
                }

                f = loadedImageUrls.get(firstHeightVal);
                s = loadedImageUrls.get(secondHeightVal);
                t = loadedImageUrls.get(thirdHeightVal);
                fh = loadedImageUrls.get(fourthHeightVal);
                loadedImageUrls.clear();
                Log.e("rz width val", "" + firstHeightVal);
                Log.e("rz width val", "" + secondHeightVal);
                Log.e("rz width val", ""+thirdHeightVal);
                Log.e("rz width val", "" + fourthHeightVal);
                setOctalImagesHorz(viewMultipleHorzBinding.getRoot(), imageWidth, imageHeight, imageWidth1, imageWidth2, imageHeight2, imageHeight3, imageHeight4,
                        images.get(firstHeightVal), images.get(secondHeightVal), images.get(thirdHeightVal), images.get(fourthHeightVal),
                        imageTexts.get(firstHeightVal),
                        imageTexts.get(secondHeightVal),
                        imageTexts.get(thirdHeightVal),
                        imageTexts.get(fourthHeightVal));

            }else{
                ViewMultipleVertBinding viewMultipleVertBinding = DataBindingUtil.bind(inflater.inflate(R.layout.view_multiple_vert, null));
                viewMultipleVertBinding.setClickHandler(this);
                viewMultipleVertBinding.setShadeFour(bindingShadeFour);
                int firstWidthVal = isFirstMaxW?0:isSecondMaxW?1:isThirdMaxW?2:3;
                remainingHeight = firstWidthVal == 0 ?height2 + height3 + height4:firstWidthVal==1?height1 + height3 + height4:firstWidthVal==2?height1 + height2 + height4:height1 + height2 + height3;

                float remainWidthAvg = (float)remainingWidth/3;
                float remainHeightAvg = (float)remainingHeight/3;

                int sumHeight = (int)(images.get(firstWidthVal).getHeight() + remainHeightAvg);

                imageHeight = sumHeight > this.maxHeight ? this.maxHeight : (sumHeight < minHeight ? minHeight : sumHeight);

                float ratioHH = ((float)images.get(firstWidthVal).getHeight()/sumHeight);
                ratioHH = ratioHH > 0.7f ? 0.7f : (ratioHH < 0.3f ? 0.3f : ratioHH);
                imageHeight1 = (int)(imageHeight * ratioHH);
                imageHeight2 = imageHeight - imageHeight1;

                int avgWidth = (int)((float)(maxFirstWidth + remainingWidth)/2);
                imageWidth = avgWidth > this.maxWidth ? this.maxWidth : (avgWidth < minWidth ? minWidth : avgWidth);


                int secondWidthVal = isFirstMaxWS && firstWidthVal!=0?0:isSecondMaxWS && firstWidthVal!=1?1:isThirdMaxWS && firstWidthVal!=2?2:3;
                int thirdWidthVal = !isFirstMaxW && !isFirstMaxWS?0:(!isSecondMaxW && !isSecondMaxWS?1:(!isThirdMaxW && !isThirdMaxWS)?2:3);
                int fourthWidthVal = !isFirstMaxW && !isFirstMaxWS && thirdWidthVal!=0?0:(!isSecondMaxW && !isSecondMaxWS && thirdWidthVal!=1?1:
                        (!isThirdMaxW && !isThirdMaxWS && thirdWidthVal!=2)?2:3);

                imageWidth2 = images.get(secondWidthVal).getWidth();
                imageWidth3 = images.get(thirdWidthVal).getWidth();
                imageWidth4 = images.get(fourthWidthVal).getWidth();

                int val = thirdWidthVal;
                thirdWidthVal = imageWidth3>=imageWidth4?thirdWidthVal:fourthWidthVal;
                fourthWidthVal = thirdWidthVal==fourthWidthVal?val:fourthWidthVal;

                imageWidth3 = images.get(thirdWidthVal).getWidth();
                imageWidth4 = images.get(fourthWidthVal).getWidth();

                int sumWW = imageWidth2+imageWidth3+imageWidth4;

                float ratioW2 = ((float)imageWidth2/sumWW);
                float ratioW3 = ((float)imageWidth3/sumWW);
                float ratioW4 = ((float)imageWidth4/sumWW);

                if(ratioW4 < 0.27f){
                    ratioW4 = 0.27f;
                    float remainWidth = imageWidth * (1 - ratioW4);

                    ratioW2 = ratioW2*100f / (ratioW2*100f + ratioW3 * 100f);
                    ratioW3 = ratioW3*100f / (ratioW2*100f + ratioW3 * 100f);

                    //33 ------ 27
                    //50 ------ 40

                    ratioW3 = ratioW3<0.4f?0.4f:ratioW3;
                    ratioW2 = 1 - ratioW3;

                    imageWidth4 = (int)(imageWidth*ratioW4);
                    imageWidth3 = (int)(remainWidth*ratioW3);
                    imageWidth2 = (int)(remainWidth - imageWidth3);
                }else{
                    imageWidth4 = (int)(imageHeight*ratioW4);
                    imageWidth3 = (int)(imageHeight*ratioW3);
                    imageWidth2 = (int)(imageHeight*ratioW2);
                }

                f = loadedImageUrls.get(firstWidthVal);
                s = loadedImageUrls.get(secondWidthVal);
                t = loadedImageUrls.get(thirdWidthVal);
                fh = loadedImageUrls.get(fourthWidthVal);
                loadedImageUrls.clear();

                Log.e("first width val", "" + firstWidthVal);
                Log.e("second width val", ""+secondWidthVal);
                Log.e("third width val", ""+thirdWidthVal);
                Log.e("fourth width val", ""+fourthWidthVal);
                setOctalImagesVert(viewMultipleVertBinding.getRoot(), imageWidth, imageHeight, imageWidth2, imageWidth3, imageWidth4, imageHeight1, imageHeight2,
                        images.get(firstWidthVal), images.get(secondWidthVal), images.get(thirdWidthVal), images.get(fourthWidthVal),
                        imageTexts.get(firstWidthVal),
                        imageTexts.get(secondWidthVal),
                        imageTexts.get(thirdWidthVal),
                        imageTexts.get(fourthWidthVal));
            }
        }
        this.loadedImageUrls = loadedImageUrls;

        this.loadedImageUrls.add(f);
        this.loadedImageUrls.add(s);
        this.loadedImageUrls.add(t);
        this.loadedImageUrls.add(fh);
    }
    private void setOctalImagesFourType1(View root, int imageWidth, int imageHeight, int imageWidth1, int imageWidth2, int imageWidth3, int imageWidth4,
                                     int imageHeight1, int imageHeight2,
                                     Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3,
                                         String text1, String text2, String text3, String text4){
        ImageView imageView1 = (ImageView)root.findViewById(R.id.view_multiple_image1);
        ImageView imageView2 = (ImageView)root.findViewById(R.id.view_multiple_image2);
        ImageView imageView3 = (ImageView)root.findViewById(R.id.view_multiple_image3);
        ImageView imageView4 = (ImageView)root.findViewById(R.id.view_multiple_image4);

        if(totalImages > 4) {
            bindingShadeFour.setCounterVisibility(true);
            bindingShadeFour.setCounterText("+" + (totalImages - 4));
        }

        BindingShadeFour.setLayoutWidth(imageView1, imageWidth1);
        BindingShadeFour.setLayoutWidth(imageView2, imageWidth2);
        BindingShadeFour.setLayoutWidth(imageView3, imageWidth3);
        BindingShadeFour.setLayoutWidth(imageView4, imageWidth4);

        BindingShadeFour.setLayoutHeight(imageView1, imageHeight1);
        BindingShadeFour.setLayoutHeight(imageView2, imageHeight1);
        BindingShadeFour.setLayoutHeight(imageView3, imageHeight2);
        BindingShadeFour.setLayoutHeight(imageView4, imageHeight2);

        bindingShadeFour.setFirstImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setSecondImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setThirdImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setFourthImageScaleType(Utils.getImageScaleType());

        BindingShadeFour.setBitmap(imageView1, bitmap);
        generatePalette(bitmap, 0);
        BindingShadeFour.setBitmap(imageView2, bitmap1);
        generatePalette(bitmap1, 1);
        BindingShadeFour.setBitmap(imageView3, bitmap2);
        generatePalette(bitmap2, 2);
        BindingShadeFour.setBitmap(imageView4, bitmap3);
        generatePalette(bitmap3, 3);

        bindingShadeFour.setFirstCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setSecondCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setThirdCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setFourthCommentVisibility(Utils.shouldShowComments());

        bindingShadeFour.setFirstComment("First F1");
        bindingShadeFour.setSecondComment("Second F1");
        bindingShadeFour.setThirdComment("Third F1");
        bindingShadeFour.setFourthComment("Fourth F1");

        /*wh.add(bitmap.getWidth());
        wh.add(bitmap.getHeight());
        wh.add(bitmap1.getWidth());
        wh.add(bitmap1.getHeight());
        wh.add(bitmap2.getWidth());
        wh.add(bitmap2.getHeight());
        wh.add(bitmap3.getWidth());
        wh.add(bitmap3.getHeight());*/

        layoutCallback.addImageView(root,imageWidth1+imageWidth2+imageWidth3+imageWidth4);

    }
    private void setOctalImagesFourType2(View root, int imageWidth, int imageHeight, int imageWidth1, int imageWidth2,
                                         int imageHeight1, int imageHeight2, int imageHeight3, int imageHeight4,
                                         Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3,
                                         String text1, String text2, String text3, String text4){
        ImageView imageView1 = (ImageView)root.findViewById(R.id.view_multiple_image1);
        ImageView imageView2 = (ImageView)root.findViewById(R.id.view_multiple_image2);
        ImageView imageView3 = (ImageView)root.findViewById(R.id.view_multiple_image3);
        ImageView imageView4 = (ImageView)root.findViewById(R.id.view_multiple_image4);

        if(totalImages > 4) {
            bindingShadeFour.setCounterVisibility(true);
            bindingShadeFour.setCounterText("+" + (totalImages - 4));
        }

        BindingShadeFour.setLayoutWidth(imageView1, imageWidth1);
        BindingShadeFour.setLayoutWidth(imageView2, imageWidth1);
        BindingShadeFour.setLayoutWidth(imageView3, imageWidth2);
        BindingShadeFour.setLayoutWidth(imageView4, imageWidth2);

        BindingShadeFour.setLayoutHeight(imageView1, imageHeight1);
        BindingShadeFour.setLayoutHeight(imageView2, imageHeight2);
        BindingShadeFour.setLayoutHeight(imageView3, imageHeight3);
        BindingShadeFour.setLayoutHeight(imageView4, imageHeight4);

        bindingShadeFour.setFirstImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setSecondImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setThirdImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setFourthImageScaleType(Utils.getImageScaleType());


        BindingShadeFour.setBitmap(imageView1, bitmap);
        generatePalette(bitmap, 0);
        BindingShadeFour.setBitmap(imageView2, bitmap1);
        generatePalette(bitmap1, 1);
        BindingShadeFour.setBitmap(imageView3, bitmap2);
        generatePalette(bitmap2, 2);
        BindingShadeFour.setBitmap(imageView4, bitmap3);
        generatePalette(bitmap3, 3);

        bindingShadeFour.setFirstCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setSecondCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setThirdCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setFourthCommentVisibility(Utils.shouldShowComments());

        bindingShadeFour.setFirstComment("First F2");
        bindingShadeFour.setSecondComment("Second F2");
        bindingShadeFour.setThirdComment("Third F2");
        bindingShadeFour.setFourthComment("Fourth F2");

        /*wh.add(bitmap.getWidth());
        wh.add(bitmap.getHeight());
        wh.add(bitmap1.getWidth());
        wh.add(bitmap1.getHeight());
        wh.add(bitmap2.getWidth());
        wh.add(bitmap2.getHeight());
        wh.add(bitmap3.getWidth());
        wh.add(bitmap3.getHeight());*/

        layoutCallback.addImageView(root,imageWidth1+imageWidth2);
    }

    private void setOctalImagesHorz(View root, int imageWidth, int imageHeight, int imageWidth1, int imageWidth2,
                                    int imageHeight2, int imageHeight3, int imageHeight4,
                                    Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3,
                                    String text1, String text2, String text3, String text4){
        ImageView imageView1 = (ImageView)root.findViewById(R.id.view_multiple_image1);
        ImageView imageView2 = (ImageView)root.findViewById(R.id.view_multiple_image2);
        ImageView imageView3 = (ImageView)root.findViewById(R.id.view_multiple_image3);
        ImageView imageView4 = (ImageView)root.findViewById(R.id.view_multiple_image4);

        if(totalImages > 4) {
            bindingShadeFour.setCounterVisibility(true);
            bindingShadeFour.setCounterText("+" + (totalImages - 4));
        }

        BindingShadeFour.setLayoutWidth(imageView1, imageWidth1);
        BindingShadeFour.setLayoutWidth(imageView2, imageWidth2);
        BindingShadeFour.setLayoutWidth(imageView3, imageWidth2);
        BindingShadeFour.setLayoutWidth(imageView4, imageWidth2);

        BindingShadeFour.setLayoutHeight(imageView1, imageHeight);
        BindingShadeFour.setLayoutHeight(imageView2, imageHeight2);
        BindingShadeFour.setLayoutHeight(imageView3, imageHeight3);
        BindingShadeFour.setLayoutHeight(imageView4, imageHeight4);

        bindingShadeFour.setFirstImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setSecondImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setThirdImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setFourthImageScaleType(Utils.getImageScaleType());

        BindingShadeFour.setBitmap(imageView1, bitmap);
        generatePalette(bitmap, 0);
        BindingShadeFour.setBitmap(imageView2, bitmap1);
        generatePalette(bitmap1, 1);
        BindingShadeFour.setBitmap(imageView3, bitmap2);
        generatePalette(bitmap2, 2);
        BindingShadeFour.setBitmap(imageView4, bitmap3);
        generatePalette(bitmap3, 3);

        bindingShadeFour.setFirstCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setSecondCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setThirdCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setFourthCommentVisibility(Utils.shouldShowComments());

        bindingShadeFour.setFirstComment("First H");
        bindingShadeFour.setSecondComment("Second H");
        bindingShadeFour.setThirdComment("Third H");
        bindingShadeFour.setFourthComment("Fourth H");

        /*wh.add(bitmap.getWidth());
        wh.add(bitmap.getHeight());
        wh.add(bitmap1.getWidth());
        wh.add(bitmap1.getHeight());
        wh.add(bitmap2.getWidth());
        wh.add(bitmap2.getHeight());
        wh.add(bitmap3.getWidth());
        wh.add(bitmap3.getHeight());*/

        layoutCallback.addImageView(root,imageWidth1+imageWidth2);
    }
    private void setOctalImagesVert(View root, int imageWidth, int imageHeight,
                                    int imageWidth2, int imageWidth3, int imageWidth4, int imageHeight1, int imageHeight2,
                                    Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3,
                                    String text1, String text2, String text3, String text4){
        ImageView imageView1 = (ImageView)root.findViewById(R.id.view_multiple_image1);
        ImageView imageView2 = (ImageView)root.findViewById(R.id.view_multiple_image2);
        ImageView imageView3 = (ImageView)root.findViewById(R.id.view_multiple_image3);
        ImageView imageView4 = (ImageView)root.findViewById(R.id.view_multiple_image4);

        if(totalImages > 4) {
            bindingShadeFour.setCounterVisibility(true);
            bindingShadeFour.setCounterText("+" + (totalImages - 4));
        }

        BindingShadeFour.setLayoutWidth(imageView1, imageWidth);
        BindingShadeFour.setLayoutWidth(imageView2, imageWidth2);
        BindingShadeFour.setLayoutWidth(imageView3, imageWidth3);
        BindingShadeFour.setLayoutWidth(imageView4, imageWidth4);

        BindingShadeFour.setLayoutHeight(imageView1, imageHeight1);
        BindingShadeFour.setLayoutHeight(imageView2, imageHeight2);
        BindingShadeFour.setLayoutHeight(imageView3, imageHeight2);
        BindingShadeFour.setLayoutHeight(imageView4, imageHeight2);

        bindingShadeFour.setFirstImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setSecondImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setThirdImageScaleType(Utils.getImageScaleType());
        bindingShadeFour.setFourthImageScaleType(Utils.getImageScaleType());

        BindingShadeFour.setBitmap(imageView1, bitmap);
        generatePalette(bitmap, 0);
        BindingShadeFour.setBitmap(imageView2, bitmap1);
        generatePalette(bitmap1, 1);
        BindingShadeFour.setBitmap(imageView3, bitmap2);
        generatePalette(bitmap2, 2);
        BindingShadeFour.setBitmap(imageView4, bitmap3);
        generatePalette(bitmap3, 3);

        bindingShadeFour.setFirstCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setSecondCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setThirdCommentVisibility(Utils.shouldShowComments());
        bindingShadeFour.setFourthCommentVisibility(Utils.shouldShowComments());

        bindingShadeFour.setFirstComment("First V");
        bindingShadeFour.setSecondComment("Second V");
        bindingShadeFour.setThirdComment("Third V");
        bindingShadeFour.setFourthComment("Fourth V");

        /*wh.add(bitmap.getWidth());
        wh.add(bitmap.getHeight());
        wh.add(bitmap1.getWidth());
        wh.add(bitmap1.getHeight());
        wh.add(bitmap2.getWidth());
        wh.add(bitmap2.getHeight());
        wh.add(bitmap3.getWidth());
        wh.add(bitmap3.getHeight());*/

        layoutCallback.addImageView(root, imageWidth);
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
