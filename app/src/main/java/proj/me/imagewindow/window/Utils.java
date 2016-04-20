package proj.me.imagewindow.window;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import proj.me.imagewindow.R;


/**
 * Created by deepak on 24/5/15.
 */
public class Utils {
    /**
     * Convert Dp to Pixel
     * dp-pixel
     */
    public static int widthPixel, heightPixel;
    public static int dpToPx(float dp, Resources resources){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }
    public static int spToPx(float sp, Resources resources){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.getDisplayMetrics());
        return (int) px;
    }
    private static final String TAG = "Image_Window";
    public static void logMessage(String message){
        Log.i(TAG, message);
    }
    public static void logError(String message){
        Log.e(TAG, message);
    }
    public static void showToast(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static String formatMessage(String message, Object ... values){
        return String.format(message, values);
    }


    //for add in layout
    public static boolean shouldShowAddInLayout(){
        return false;
    }
    //for comments
    public static boolean shouldShowComments(){
        return false;
    }

    public static boolean showShowDivider(){
        return true;
    }

    public static ColorCombination getColorCombo(){
        return ColorCombination.VIBRANT_TO_MUTED;
    }

    //for image
    public static int getErrorDrawableId(){
        return R.mipmap.ic_launcher;
    }
    public static ImageView.ScaleType getImageScaleType(){
        return ImageView.ScaleType.CENTER_CROP;
    }

    public static ColorPallet getColorPallet(){
        return ColorPallet.VIBRANT;
    }
    /**
     * for max 6 colors it'll do mixing at a time
     * it's simply taking average of the colors
     * */
    private static int getMixedColor(int ... colors){
        //for 1
        //return same color
        //for 2
        //add colors then right shift them with two to find average
        //for 3
        //add all then right shift by 1 then multiply by 2/3 by shift/add 101010...
        //for 4 add them then right shift 2
        //for 5 add them then divide by 10 then multiply by 2
        //for 6 add them all then divide by 3 then divide by 2
        int finalColor = 0;

        switch(colors.length){
            case 1:
                return colors[0];
            case 2:
                return (colors[0] + colors[1]) >> 1;
            case 3:
                int number = colors[0] + colors[1] + colors[2];
                int resultInt = number >> 1;
                int v = number;int n = 1;

                //calculating leading zeros
                if(v >>> 16 == 0){ n+=16; v <<= 16;}
                if(v >>> 24 == 0){ n+=8; v <<= 8; }
                if(v >>> 28 == 0){ n+=4; v <<= 4; }
                if(v >>> 30 == 0){ n+=2; v <<= 2; }
                n-=v >>> 31;

                int bitLength = 32 - n;
                finalColor = 0;
                int i = 1;
                while(bitLength > 0){
                    //shifting to left as 1, 3, 5.....
                    finalColor += resultInt << i;
                    bitLength--;
                    i+=2;
                }

                bitLength = 32 - n;
                int bits = bitLength * 2;
                int val = 1 << (bits - 1);

                if((val & finalColor) != 0){ finalColor = finalColor >> bits;  return finalColor + 1;}
                else return finalColor >> bits;
            case 4:
                return (colors[0] + colors[1] + colors[2] + colors[3]) >> 2;
            case 5:
                finalColor = colors[0] + colors[1] + colors[2] + colors[3] + colors[4];
                int q = (finalColor >> 1) + (finalColor >> 2);
                q = q + (q >> 4);
                q = q + (q >> 8);
                q = q + (q >> 16);
                q = q >> 3;
                int r = finalColor - (((q << 2) + q) << 1);
                return (q + ((r > 9) ? 1 : 0)) << 1;
            case 6:
                finalColor = getMixedColor(colors[0]+colors[1]+colors[2]+colors[3], colors[4], colors[5]);
                return finalColor >> 1;
        }
        return finalColor;
    }

    public static int getMixedArgbColor(int ... colors){
        int mixedAlpha = 0;
        int mixedRed = 0;
        int mixedGreen = 0;
        int mixedBlue = 0;
        switch(colors.length){
            case 1:
                mixedAlpha = getMixedColor(colors[0] >>> 24);
                mixedRed = getMixedColor(colors[0] >> 16 & 0xFF);
                mixedGreen = getMixedColor(colors[0] >> 8 & 0xFF);
                mixedBlue = getMixedColor(colors[0] & 0xFF);
                break;
            case 2:
                mixedAlpha = getMixedColor(colors[0] >>> 24, colors[1] >>> 24);
                mixedRed = getMixedColor(colors[0] >> 16 & 0xFF, colors[1] >> 16 & 0xFF);
                mixedGreen = getMixedColor(colors[0] >> 8 & 0xFF, colors[1] >> 8 & 0xFF);
                mixedBlue = getMixedColor(colors[0] & 0xFF, colors[1] & 0xFF);
                break;
            case 3:
                mixedAlpha = getMixedColor(colors[0] >>> 24, colors[1] >>> 24, colors[2] >>> 24);
                mixedRed = getMixedColor(colors[0] >> 16 & 0xFF, colors[1] >> 16 & 0xFF, colors[2] >> 16 & 0xFF);
                mixedGreen = getMixedColor(colors[0] >> 8 & 0xFF, colors[1] >> 8 & 0xFF, colors[2] >> 8 & 0xFF);
                mixedBlue = getMixedColor(colors[0] & 0xFF, colors[1] & 0xFF, colors[2] & 0xFF);
                break;
            case 4:
                mixedAlpha = getMixedColor(colors[0] >>> 24, colors[1] >>> 24, colors[2] >>> 24,
                        colors[3] >>> 24);
                mixedRed = getMixedColor(colors[0] >> 16 & 0xFF, colors[1] >> 16 & 0xFF, colors[2] >> 16 & 0xFF,
                        colors[3] >> 16 & 0xFF);
                mixedGreen = getMixedColor(colors[0] >> 8 & 0xFF, colors[1] >> 8 & 0xFF, colors[2] >> 8 & 0xFF,
                        colors[3] >> 8 & 0xFF);
                mixedBlue = getMixedColor(colors[0] & 0xFF, colors[1] & 0xFF, colors[2] & 0xFF,
                        colors[3] & 0xFF);
                break;
            case 5:
                mixedAlpha = getMixedColor(colors[0] >>> 24, colors[1] >>> 24, colors[2] >>> 24,
                        colors[3] >>> 24, colors[4] >>> 24);
                mixedRed = getMixedColor(colors[0] >> 16 & 0xFF, colors[1] >> 16 & 0xFF, colors[2] >> 16 & 0xFF,
                        colors[3] >> 16 & 0xFF, colors[4] >> 16 & 0xFF);
                mixedGreen = getMixedColor(colors[0] >> 8 & 0xFF, colors[1] >> 8 & 0xFF, colors[2] >> 8 & 0xFF,
                        colors[3] >> 8 & 0xFF, colors[4] >> 8 & 0xFF);
                mixedBlue = getMixedColor(colors[0] & 0xFF, colors[1] & 0xFF, colors[2] & 0xFF,
                        colors[3] & 0xFF, colors[4] & 0xFF);
                break;
            case 6:
                mixedAlpha = getMixedColor(colors[0] >>> 24, colors[1] >>> 24, colors[2] >>> 24,
                        colors[3] >>> 24, colors[4] >>> 24, colors[5] >>> 24);
                mixedRed = getMixedColor(colors[0] >> 16 & 0xFF, colors[1] >> 16 & 0xFF, colors[2] >> 16 & 0xFF,
                        colors[3] >> 16 & 0xFF, colors[4] >> 16 & 0xFF, colors[5] >> 16 & 0xFF);
                mixedGreen = getMixedColor(colors[0] >> 8 & 0xFF, colors[1] >> 8 & 0xFF, colors[2] >> 8 & 0xFF,
                        colors[3] >> 8 & 0xFF, colors[4] >> 8 & 0xFF, colors[5] >> 8 & 0xFF);
                mixedBlue = getMixedColor(colors[0] & 0xFF, colors[1] & 0xFF, colors[2] & 0xFF,
                        colors[3] & 0xFF, colors[4] & 0xFF, colors[5] & 0xFF);
                break;
        }
        return Color.argb(mixedAlpha, mixedRed, mixedGreen, mixedBlue);
    }
    public static int getInverseColor(int color){
        float hsv[] = new float[3];
        Color.colorToHSV(color, hsv);
        //inverting color
        float hue = hsv[2];
        hue = (hue + 180) % 360;
        hsv[2] = hue;
        return Color.HSVToColor(hsv);
    }

    public static int getColorWithTransparency(int color, int transparencyPercent){
        int alpha = 235 * transparencyPercent / 100;
        return Color.argb(alpha, color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF);
    }

    public static final int COMMENT_TRANSPARENCY_PERCENT = 70;

    public static float MIN_WIDTH = 300;
    public static float MIN_HIGHT = 300;
    public static float MAX_WIDTH = 700;
    public static float MAX_HEIGHT = 900;

    /*public static final float MIN_RATIO = 0.3f;
    public static final float MAX_RATIO = 0.7f;*/

    public static final float MIN_ADD_RATIO = 0.25f;

    public static final boolean HAS_FIXED_DIMENSIONS = true;

}