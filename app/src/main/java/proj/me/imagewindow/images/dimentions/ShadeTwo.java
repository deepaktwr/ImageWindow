package proj.me.imagewindow.images.dimentions;

import java.util.ArrayList;
import java.util.List;

import proj.me.imagewindow.window.Utils;

/**
 * Created by root on 21/3/16.
 * counting 1 to last in horizontal manner
 */
public class ShadeTwo {

    private static float WIDTH_1 = 0, WIDTH_2 = 0, HEIGHT_1 = 0, HEIGHT_2 = 0;
    public static BeanShade2 calculateDimentions(int width1, int height1, int width2, int height2){
        WIDTH_1 = 0; WIDTH_2 = 0; HEIGHT_1 = 0; HEIGHT_2 = 0;
        BeanShade2 beanShade2 = new BeanShade2();
        //width1 amount to required width
        float amountW1 = width1 / Utils.MAX_WIDTH;
        //height1 amount to required height
        float amountH1 = height1 / Utils.MAX_HEIGHT;

        //width2 amount to required width
        float amountW2 = width2 / Utils.MAX_WIDTH;
        //height2 amount to required height
        float amountH2 = height2 / Utils.MAX_HEIGHT;

        beanShade2.setLayoutType(LayoutType.HORZ);

        List<ImageOrder> imageOrderList = new ArrayList<>();

        float requiredMinWidth = Utils.MIN_WIDTH + Utils.MIN_WIDTH / 8f;

        if(amountW1 >= amountH1 && amountW1 >= amountH2 && amountW1 >= amountW2 && (width1 >= requiredMinWidth || width2 >= requiredMinWidth)){
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);
            //will go vert with first image
            beanShade2.setLayoutType(LayoutType.VERT);

            //pre width calculation

            WIDTH_1 = width1 > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            WIDTH_2 = width2 > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;

            HEIGHT_1 = height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;
            HEIGHT_2 = height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;

            calculateVert(beanShade2, height1, height2);

        }else if(amountW2 >= amountH1 && amountW2 >= amountH2 && amountW2 >= amountW1 && (width1 >= requiredMinWidth || width2 >= requiredMinWidth)){
            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.FIRST);


            //will go vert with second image
            beanShade2.setLayoutType(LayoutType.VERT);

            //pre width calculation

            WIDTH_1 = width2 > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;
            WIDTH_2 = width1 > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            HEIGHT_1 = height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;
            HEIGHT_2 = height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;

            calculateVert(beanShade2, height2, height1);

        }else if((amountH1 >= amountW1 && amountH1 >= amountW2 && amountH1 >= amountH2) || (amountH1 >= amountH2 && (width1 < requiredMinWidth && width2 < requiredMinWidth))){
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);

            //will go horz with first image

            //pre height calculation
            HEIGHT_1 = height1 > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;
            HEIGHT_2 = height2 > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;

            WIDTH_1 = width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            WIDTH_2 = width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;

            calculateHorz(beanShade2, width1, width2);

        }else if((amountH2 >= amountW1 && amountH2 >= amountW2 && amountH2 >= amountH1) || (amountH2 >= amountH1 && (width1 < requiredMinWidth && width2 < requiredMinWidth))){
            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.FIRST);

            //will go horz with second image

            //pre height calculation
            HEIGHT_1 = height2 > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;
            HEIGHT_2 = height1 > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;

            WIDTH_1 = width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;
            WIDTH_2 = width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;

            calculateHorz(beanShade2, width2, width1);
        }

        beanShade2.setImageOrderList(imageOrderList);

        beanShade2.setWidth1((int)WIDTH_1);
        beanShade2.setWidth2((int)WIDTH_2);
        beanShade2.setHeight1((int)HEIGHT_1);
        beanShade2.setHeight2((int)HEIGHT_2);

        return beanShade2;
    }

    private static void calculateVert(BeanShade2 beanShade2, int height1, int height2){
        //adjust add button in layout through width
        float maxImageWidthForAdd = WIDTH_1 - WIDTH_1 * Utils.MIN_ADD_RATIO;
        if(WIDTH_2 >= WIDTH_1 * 0.9f){
            WIDTH_2 = WIDTH_1;
            beanShade2.setAddInLayout(false);
        }else if(WIDTH_2 > maxImageWidthForAdd && maxImageWidthForAdd >= Utils.MIN_WIDTH){
            WIDTH_2 = maxImageWidthForAdd;
            beanShade2.setAddInLayout(true);
        }else if(WIDTH_2 > maxImageWidthForAdd){
            float width1Increase = Utils.MIN_WIDTH - maxImageWidthForAdd;
            WIDTH_1 = WIDTH_1 + width1Increase > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : WIDTH_1 + width1Increase;
            WIDTH_2 = Utils.MIN_WIDTH;
            beanShade2.setAddInLayout(true);
        }else beanShade2.setAddInLayout(true);


        //post height calculation
        float sumHeight = HEIGHT_1 + HEIGHT_2;
        if(sumHeight > Utils.MAX_HEIGHT){
            float ratio = (float)height1 / (height1 + height2);
            HEIGHT_1 = Utils.MAX_HEIGHT * ratio;
            HEIGHT_2 = Utils.MAX_HEIGHT * (1f - ratio);

            if(HEIGHT_1 < Utils.MIN_HIGHT){
                HEIGHT_1 = Utils.MIN_HIGHT;
                if(Utils.MIN_HIGHT + HEIGHT_2 > Utils.MAX_HEIGHT) HEIGHT_2 = Utils.MAX_HEIGHT - Utils.MIN_HIGHT;
            } else if(HEIGHT_2 < Utils.MIN_HIGHT){
                HEIGHT_2  = Utils.MIN_HIGHT;
                if(Utils.MIN_HIGHT + HEIGHT_1 > Utils.MAX_HEIGHT) HEIGHT_1 = Utils.MAX_HEIGHT - Utils.MIN_HIGHT;
            }
        }
    }
    private static void calculateHorz(BeanShade2 beanShade2, int width1, int width2){
        //adjust add button in layout through height
        float maxImageHeightForAdd = HEIGHT_1 - HEIGHT_1 * Utils.MIN_ADD_RATIO;
        if(HEIGHT_2 >= HEIGHT_1 * 0.9f){
            HEIGHT_2 = HEIGHT_1;
            beanShade2.setAddInLayout(false);
        }else if(HEIGHT_2 > maxImageHeightForAdd && maxImageHeightForAdd >= Utils.MIN_HIGHT){
            HEIGHT_2 = maxImageHeightForAdd;
            beanShade2.setAddInLayout(true);
        }else if(HEIGHT_2 > maxImageHeightForAdd){
            float height1Increase = Utils.MIN_HIGHT - maxImageHeightForAdd;
            HEIGHT_1 = HEIGHT_1 + height1Increase > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : HEIGHT_1 + height1Increase;
            HEIGHT_2 = Utils.MIN_HIGHT;
            beanShade2.setAddInLayout(true);
        }else beanShade2.setAddInLayout(true);

        //post width calculation
        float sumWidth = WIDTH_1 + WIDTH_2;
        if(sumWidth > Utils.MAX_WIDTH){
            float ratio =  (float)width1/ (width1 + width2);
            WIDTH_1 = Utils.MAX_WIDTH * ratio;
            WIDTH_2 = Utils.MAX_WIDTH * (1f - ratio);

            if(WIDTH_1 < Utils.MIN_WIDTH){
                WIDTH_1 = Utils.MIN_WIDTH;
                if(Utils.MIN_WIDTH + WIDTH_2 > Utils.MAX_WIDTH) WIDTH_2 = Utils.MAX_WIDTH - Utils.MIN_WIDTH;
            } else if(WIDTH_2 < Utils.MIN_WIDTH){
                WIDTH_2  = Utils.MIN_WIDTH;
                if(Utils.MIN_WIDTH + WIDTH_1 > Utils.MAX_WIDTH) WIDTH_1 = Utils.MAX_WIDTH - Utils.MIN_WIDTH;
            }
        }
    }
}
