package proj.me.imagewindow.images.dimentions;

import org.antlr.runtime.MismatchedNotSetException;

import java.util.ArrayList;
import java.util.List;

import proj.me.imagewindow.window.Utils;

/**
 * Created by root on 27/3/16.
 */
public class ShadeThree {
    private static float WIDTH_1 = 0, WIDTH_2 = 0, WIDTH_3 = 0, HEIGHT_1 = 0, HEIGHT_2 = 0, HEIGHT_3 = 0;
    public static BeanShade3 calculateDimentions(int width1, int height1, int width2, int height2, int width3, int height3){
        WIDTH_1 = 0; WIDTH_2 = 0; WIDTH_3 = 0; HEIGHT_1 = 0; HEIGHT_2 = 0; HEIGHT_3 = 0;
        BeanShade3 beanShade3 = new BeanShade3();

        List<ImageOrder> imageOrderList = new ArrayList<>();

        //width1 amount to required width
        float amountW1 = width1 / Utils.MAX_WIDTH;
        //height1 amount to required height
        float amountH1 = height1 / Utils.MAX_HEIGHT;

        //width2 amount to required width
        float amountW2 = width2 / Utils.MAX_WIDTH;
        //height2 amount to required height
        float amountH2 = height2 / Utils.MAX_HEIGHT;

        //width3 amount to required width
        float amountW3 = width3 / Utils.MAX_WIDTH;
        //height3 amount to required height
        float amountH3 = height3 / Utils.MAX_HEIGHT;

        float minParallelWidth = Utils.MAX_WIDTH + (Utils.MAX_WIDTH / 4f);//adding 25% of width
        float minParallelHeight = Utils.MAX_HEIGHT + (Utils.MAX_HEIGHT / 4f);//adding 25% of height

        float minHeightForParallelWidth = Utils.MAX_HEIGHT - (Utils.MAX_HEIGHT / 5f);//subtracting 20% of the height
        float minWidthForParallelHeight = Utils.MAX_WIDTH - (Utils.MAX_WIDTH / 10f);//subtracting 10% of width

        int widthSum = width1 + width2 + width3;
        int heightSum = height1 + height2 + height3;


        //first need to make cases for parallel layouts
        if(minParallelWidth >= widthSum && widthSum >= Utils.MAX_WIDTH
                && height1 >= minHeightForParallelWidth && height2 >= minHeightForParallelWidth && height3 >= minHeightForParallelWidth){
            //the min width will be different for this as we have put the min width a/c to two images in the view
            float MIN_WIDTH = Utils.MIN_WIDTH / 1.5f;
            //will go parallel horz
            beanShade3.setLayoutType(LayoutType.PARALLEL_HORZ);
            //we need to check min width is not less than Utils Min Width, but we can put the same ordering as came
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.THIRD);

            //height calculation
            float avgHeight = (height1 + height2 + height3) / 3f;
            HEIGHT_1 = HEIGHT_2 = HEIGHT_3 = Utils.HAS_FIXED_DIMENSIONS  || avgHeight > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : avgHeight;


            //changing actual dimentions
            if(Utils.HAS_FIXED_DIMENSIONS){
                width1 += Utils.MAX_WIDTH / 3f  + 2;
                width2 += Utils.MAX_WIDTH / 3f  + 2;
                width3 += Utils.MAX_WIDTH / 3f  + 2;

                widthSum = width1 + width2 + width3;
            }

            //width calculation
            WIDTH_1 = width1;
            WIDTH_2 = width2;
            WIDTH_3 = width3;



            if(widthSum > Utils.MAX_WIDTH){
                float reducePercent = ((widthSum - Utils.MAX_WIDTH) / widthSum) * 100f;
                WIDTH_1 = width1 - width1 * reducePercent / 100f;
                WIDTH_2 = width2 - width2 * reducePercent / 100f;
                WIDTH_3 = width3 - width3 * reducePercent / 100f;
            }

            float width1Increase = 0f, width2Increase = 0f, width3Increase = 0f;

            if(WIDTH_1 < MIN_WIDTH) width1Increase = MIN_WIDTH - WIDTH_1;
            if(WIDTH_2 < MIN_WIDTH) width2Increase = MIN_WIDTH - WIDTH_2;
            if(WIDTH_3 < MIN_WIDTH) width3Increase = MIN_WIDTH - WIDTH_3;

            //it'll make 6 cases
            if(width1Increase > 0 && width2Increase > 0 && width3Increase > 0) {
                WIDTH_1 = MIN_WIDTH;
                WIDTH_2 = MIN_WIDTH;
                WIDTH_3 = MIN_WIDTH;
            }else if(width1Increase > 0 && width2Increase > 0){
                WIDTH_1 = MIN_WIDTH;
                WIDTH_2 = MIN_WIDTH;
                if(WIDTH_1 + WIDTH_2 + WIDTH_3  > Utils.MAX_WIDTH) WIDTH_3 = Utils.MAX_WIDTH - WIDTH_1 - WIDTH_2;
                //WIDTH_3 -= width1Increase + width2Increase;
            }else if(width2Increase > 0 && width3Increase > 0){
                WIDTH_3 = MIN_WIDTH;
                WIDTH_2 = MIN_WIDTH;
                if(WIDTH_1 + WIDTH_2 + WIDTH_3  > Utils.MAX_WIDTH) WIDTH_1 = Utils.MAX_WIDTH - WIDTH_2 - WIDTH_3;
                //WIDTH_1 -= width2Increase + width3Increase;
            }else if(width1Increase > 0 && width3Increase > 0){
                WIDTH_1 = MIN_WIDTH;
                WIDTH_3 = MIN_WIDTH;
                if(WIDTH_1 + WIDTH_2 + WIDTH_3  > Utils.MAX_WIDTH) WIDTH_2 = Utils.MAX_WIDTH - WIDTH_1 - WIDTH_3;
                //WIDTH_2 -= width1Increase + width3Increase;
            }else {
                if (width1Increase > 0) {
                    WIDTH_1 = MIN_WIDTH;
                    if(WIDTH_1 + WIDTH_2 + WIDTH_3 > Utils.MAX_WIDTH){
                        float totalWidth = Utils.MAX_WIDTH - MIN_WIDTH;
                        float width = WIDTH_2;
                        WIDTH_2 = totalWidth * WIDTH_2 / (WIDTH_2 + WIDTH_3);
                        WIDTH_3 = totalWidth * WIDTH_3 / (width + WIDTH_3);
                        if (WIDTH_2 < MIN_WIDTH){
                            WIDTH_3 = totalWidth - MIN_WIDTH;
                            WIDTH_2 = MIN_WIDTH;
                        }
                        else if (WIDTH_3 < MIN_WIDTH){
                            WIDTH_2 = totalWidth - MIN_WIDTH;
                            WIDTH_3 = MIN_WIDTH;
                        }
                    }
                }
                else if (width2Increase > 0) {
                    WIDTH_2 = MIN_WIDTH;
                    if(WIDTH_1 + WIDTH_2 + WIDTH_3 > Utils.MAX_WIDTH){
                        float totalWidth = Utils.MAX_WIDTH - MIN_WIDTH;
                        //here WIDTH_1 instead of width1 because it has been reduced or not with same percentage
                        float width = WIDTH_1;
                        WIDTH_1 = totalWidth * WIDTH_1 / (WIDTH_1 + WIDTH_3);
                        WIDTH_3 = totalWidth * WIDTH_3 / (width + WIDTH_3);
                        if (WIDTH_1 < MIN_WIDTH){
                            WIDTH_3 = totalWidth - MIN_WIDTH;
                            WIDTH_1 = MIN_WIDTH;
                        }
                        else if (WIDTH_3 < MIN_WIDTH){
                            WIDTH_1 = totalWidth - MIN_WIDTH;
                            WIDTH_3 = MIN_WIDTH;
                        }
                    }
                }
                else if (width3Increase > 0) {
                    WIDTH_3 = MIN_WIDTH;
                    if(WIDTH_1 + WIDTH_2 + WIDTH_3 > Utils.MAX_WIDTH){
                        float totalWidth = Utils.MAX_WIDTH - MIN_WIDTH;
                        float width = WIDTH_2;
                        WIDTH_2 = totalWidth * WIDTH_2 / (WIDTH_2 + WIDTH_1);
                        WIDTH_1 = totalWidth * WIDTH_1 / (width + WIDTH_1);
                        if (WIDTH_2 < MIN_WIDTH){
                            WIDTH_1 = totalWidth - MIN_WIDTH;
                            WIDTH_2 = MIN_WIDTH;
                        }
                        else if (WIDTH_1 < MIN_WIDTH){
                            WIDTH_2 = totalWidth - MIN_WIDTH;
                            WIDTH_1 = MIN_WIDTH;
                        }
                    }
                }
            }
        }else if(minParallelHeight >= heightSum && heightSum >= Utils.MAX_HEIGHT
                && width1 >= minWidthForParallelHeight && width2 >= minWidthForParallelHeight && width3 >= minWidthForParallelHeight){
            //the min height will be different for this as we have put the min height a/c to two images in the view
            float MIN_HIGHT = Utils.MIN_HIGHT / 1.5f;
            //will go parallel vert
            beanShade3.setLayoutType(LayoutType.PARALLEL_VERT);
            //we need to check min height is not less than Utils Min Height, but we can put the same ordering as came
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.THIRD);

            //width calculation
            float avgWidth = (width1 + width2 + width3) / 3f;
            WIDTH_1 = WIDTH_2 = WIDTH_3 = Utils.HAS_FIXED_DIMENSIONS || avgWidth > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : avgWidth;


            //changing actual dimentions
            if(Utils.HAS_FIXED_DIMENSIONS){
                height1 += Utils.MAX_HEIGHT / 3f  + 2;
                height2 += Utils.MAX_HEIGHT / 3f  + 2;
                height3 += Utils.MAX_HEIGHT / 3f  + 2;

                heightSum = height1 + height2 + height3;
            }

            //height calculation
            HEIGHT_1 = height1;
            HEIGHT_2 = height2;
            HEIGHT_3 = height3;

            if(heightSum > Utils.MAX_HEIGHT){
                float reducePercent = ((heightSum - Utils.MAX_HEIGHT) / heightSum) * 100f;
                HEIGHT_1 = height1 - height1 * reducePercent / 100f;
                HEIGHT_2 = height2 - height2 * reducePercent / 100f;
                HEIGHT_3 = height3 - height3 * reducePercent / 100f;
            }

            float height1Increase = 0f, height2Increase = 0f, height3Increase = 0f;

            if(HEIGHT_1 < MIN_HIGHT) height1Increase = MIN_HIGHT - HEIGHT_1;
            if(HEIGHT_2 < MIN_HIGHT) height2Increase = MIN_HIGHT - HEIGHT_2;
            if(HEIGHT_3 < MIN_HIGHT) height3Increase = MIN_HIGHT - HEIGHT_3;

            //it'll make 6 cases
            if(height1Increase > 0 && height2Increase > 0 && height3Increase > 0) {
                HEIGHT_1 = MIN_HIGHT;
                HEIGHT_2 = MIN_HIGHT;
                HEIGHT_3 = MIN_HIGHT;
            }else if(height1Increase > 0 && height2Increase > 0){
                HEIGHT_1 = MIN_HIGHT;
                HEIGHT_2 = MIN_HIGHT;
                if(HEIGHT_1 + HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT) HEIGHT_3 = Utils.MAX_HEIGHT - HEIGHT_1 - HEIGHT_2;
                //HEIGHT_3 -= height1Increase + height2Increase;
            }else if(height2Increase > 0 && height3Increase > 0){
                HEIGHT_3 = MIN_HIGHT;
                HEIGHT_2 = MIN_HIGHT;
                if(HEIGHT_1 + HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT) HEIGHT_1 = Utils.MAX_HEIGHT - HEIGHT_2 - HEIGHT_3;
                //HEIGHT_1 -= height2Increase + height3Increase;
            }else if(height1Increase > 0 && height3Increase > 0){
                HEIGHT_1 = MIN_HIGHT;
                HEIGHT_3 = MIN_HIGHT;
                if(HEIGHT_1 + HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT) HEIGHT_2 = Utils.MAX_HEIGHT - HEIGHT_1 - HEIGHT_3;
                //HEIGHT_2 -= height1Increase + height3Increase;
            }else {
                if (height1Increase > 0) {
                    HEIGHT_1 = MIN_HIGHT;
                    if(HEIGHT_1 + HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT){
                        float totalHeight = Utils.MAX_HEIGHT - MIN_HIGHT;
                        float height = HEIGHT_2;
                        HEIGHT_2 = totalHeight * HEIGHT_2 / (HEIGHT_2 + HEIGHT_3);
                        HEIGHT_3 = totalHeight * HEIGHT_3 / (height + HEIGHT_3);
                        if (HEIGHT_2 < MIN_HIGHT){
                            HEIGHT_3 = totalHeight - MIN_HIGHT;
                            HEIGHT_2 = MIN_HIGHT;
                        }
                        else if (HEIGHT_3 < MIN_HIGHT){
                            HEIGHT_2 = totalHeight - MIN_HIGHT;
                            HEIGHT_3 = MIN_HIGHT;
                        }
                    }
                }
                if (height2Increase > 0) {
                    HEIGHT_2 = MIN_HIGHT;
                    if(HEIGHT_1 + HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT){
                        float totalHeight = Utils.MAX_HEIGHT - MIN_HIGHT;
                        float height = HEIGHT_1;
                        HEIGHT_1 = totalHeight * HEIGHT_1 / (HEIGHT_1 + HEIGHT_3);
                        HEIGHT_3 = totalHeight * HEIGHT_3 / (height + HEIGHT_3);
                        if (HEIGHT_1 < MIN_HIGHT){
                            HEIGHT_3 = totalHeight - MIN_HIGHT;
                            HEIGHT_1 = MIN_HIGHT;
                        }
                        else if (HEIGHT_3 < MIN_HIGHT){
                            HEIGHT_1 = totalHeight - MIN_HIGHT;
                            HEIGHT_3 = MIN_HIGHT;
                        }
                    }
                }
                if (height3Increase > 0) {
                    HEIGHT_3 = MIN_HIGHT;
                    if(HEIGHT_1 + HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT){
                        float totalHeight = Utils.MAX_HEIGHT - MIN_HIGHT;
                        float height = HEIGHT_2;
                        HEIGHT_2 = totalHeight * HEIGHT_2 / (HEIGHT_2 + HEIGHT_1);
                        HEIGHT_1 = totalHeight * HEIGHT_1 / (height + HEIGHT_1);
                        if (HEIGHT_2 < MIN_HIGHT){
                            HEIGHT_1 = totalHeight - MIN_HIGHT;
                            HEIGHT_2 = MIN_HIGHT;
                        }
                        else if (HEIGHT_1 < MIN_HIGHT){
                            HEIGHT_2 = totalHeight - MIN_HIGHT;
                            HEIGHT_1 = MIN_HIGHT;
                        }
                    }
                }
            }

        }
        //cases for vert layouts
        else if(amountW1 >= amountH1 && amountW1 >= amountH2 && amountW1 >= amountH3
                && amountW1 >= amountW2 && amountW1 >= amountW3){
            //will go vert with bigger first image, need to check the ordering
            beanShade3.setLayoutType(LayoutType.VERT);
            //pre condition
            WIDTH_1 = width1 < Utils.MIN_WIDTH * 2 ? Utils.MIN_WIDTH * 2 : width1;
            WIDTH_2 = width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;
            WIDTH_3 = width3 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width3;

            HEIGHT_1 = height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;
            HEIGHT_2 = height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;
            HEIGHT_3 = height3 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height3;

            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.THIRD);

            calculateWidthAndHeightVert();
        }else if(amountW2 >= amountH1 && amountW2 >= amountH2 && amountW2 >= amountH3
                && amountW2 >= amountW1 && amountW2 >= amountW3){
            //will go vert with bigger second image, need to check the ordering
            beanShade3.setLayoutType(LayoutType.VERT);
            //pre condition
            WIDTH_1 = width2 < Utils.MIN_WIDTH * 2 ? Utils.MIN_WIDTH * 2 : width2;
            WIDTH_2 = width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            WIDTH_3 = width3 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width3;

            HEIGHT_1 = height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;
            HEIGHT_2 = height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;
            HEIGHT_3 = height3 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height3;

            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.THIRD);

            calculateWidthAndHeightVert();
        }else if(amountW3 >= amountH1 && amountW3 >= amountH2 && amountW3 >= amountH3
                && amountW3 >= amountW1 && amountW3 >= amountW2){
            //will go vert with bigger third image, need to check the ordering
            beanShade3.setLayoutType(LayoutType.VERT);
            //pre condition
            WIDTH_1 = width3 < Utils.MIN_WIDTH * 2 ? Utils.MIN_WIDTH * 2 : width3;
            WIDTH_2 = width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            WIDTH_3 = width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;

            HEIGHT_1 = height3 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height3;
            HEIGHT_2 = height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;
            HEIGHT_3 = height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;

            imageOrderList.add(ImageOrder.THIRD);
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);

            calculateWidthAndHeightVert();

        }
        //cases for horz layouts
        else if(amountH1 >= amountW1 && amountH1 >= amountW2 && amountH1 >=  amountW3
                && amountH1 >= amountH2 && amountH1 >= amountH3){
            //will go horz with bigger first image, need to check the ordering
            beanShade3.setLayoutType(LayoutType.HORZ);
            //pre condition
            WIDTH_1 = width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            WIDTH_2 = width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;
            WIDTH_3 = width3 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width3;

            HEIGHT_1 = height1 < Utils.MIN_HIGHT * 2 ? Utils.MIN_HIGHT * 2 : height1;
            HEIGHT_2 = height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;
            HEIGHT_3 = height3 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height3;

            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.THIRD);

            calculateWidthAndHeightHorz();
        }else if(amountH2 >= amountW1 && amountH2 >= amountW2 && amountH2 >=  amountW3
                && amountH2 >= amountH1 && amountH2 >= amountH3){
            //will go horz with bigger second image, need to check the ordering
            beanShade3.setLayoutType(LayoutType.HORZ);
            //pre condition
            WIDTH_1 = width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;
            WIDTH_2 = width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            WIDTH_3 = width3 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width3;

            HEIGHT_1 = height2 < Utils.MIN_HIGHT * 2 ? Utils.MIN_HIGHT * 2 : height2;
            HEIGHT_2 = height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;
            HEIGHT_3 = height3 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height3;

            imageOrderList.add(ImageOrder.SECOND);
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.THIRD);

            calculateWidthAndHeightHorz();
        }else if(amountH3 >= amountW1 && amountH3 >= amountW2 && amountH3 >=  amountW3
                && amountH3 >= amountH2 && amountH3 >= amountH1){
            //will go horz with bigger third image, need to check the ordering
            beanShade3.setLayoutType(LayoutType.HORZ);
            //pre condition
            WIDTH_1 = width3 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width3;
            WIDTH_2 = width1 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width1;
            WIDTH_3 = width2 < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width2;

            HEIGHT_1 = height3 < Utils.MIN_HIGHT * 2 ? Utils.MIN_HIGHT * 2 : height3;
            HEIGHT_2 = height1 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height1;
            HEIGHT_3 = height2 < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height2;

            imageOrderList.add(ImageOrder.THIRD);
            imageOrderList.add(ImageOrder.FIRST);
            imageOrderList.add(ImageOrder.SECOND);

            calculateWidthAndHeightHorz();
        }

        beanShade3.setImageOrderList(imageOrderList);

        beanShade3.setWidth1((int)WIDTH_1);
        beanShade3.setWidth2((int)WIDTH_2);
        beanShade3.setWidth3((int)WIDTH_3);
        beanShade3.setHeight1((int)HEIGHT_1);
        beanShade3.setHeight2((int)HEIGHT_2);
        beanShade3.setHeight3((int)HEIGHT_3);

        return beanShade3;
    }

    private static void calculateWidthAndHeightVert(){
        //lower images width
        float tempWidth1 = WIDTH_1;
        float sumTwoWidth = WIDTH_2 + WIDTH_3;
        sumTwoWidth = Utils.HAS_FIXED_DIMENSIONS || sumTwoWidth > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : sumTwoWidth;
        WIDTH_1 = Utils.HAS_FIXED_DIMENSIONS || WIDTH_1 > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : WIDTH_1;

        float avgWidth = (sumTwoWidth + WIDTH_1) / 2f;

        WIDTH_1 = avgWidth;

        float tempWidth2 = WIDTH_2;
        float tempWidth3 = WIDTH_3;

        WIDTH_2 = avgWidth * WIDTH_2/(WIDTH_2 + WIDTH_3);
        WIDTH_3 = avgWidth * WIDTH_3/(tempWidth2 + WIDTH_3);

        if(WIDTH_2 < Utils.MIN_WIDTH){
            WIDTH_2 = Utils.MIN_WIDTH;
            if(WIDTH_2 + WIDTH_3 > Utils.MAX_WIDTH)
                WIDTH_3 = Utils.MAX_WIDTH - WIDTH_2;
        }else if(WIDTH_3 < Utils.MIN_WIDTH){
            WIDTH_3 = Utils.MIN_WIDTH;
            if(WIDTH_2 + WIDTH_3 > Utils.MAX_WIDTH)
                WIDTH_2 = Utils.MAX_WIDTH - WIDTH_3;
        }

        WIDTH_1 = (int)WIDTH_2 + (int)WIDTH_3;

        float avgTwoHeight = (HEIGHT_2 + HEIGHT_3) / 2f;
        float sumHeight = HEIGHT_1 + avgTwoHeight;

        sumHeight  = Utils.HAS_FIXED_DIMENSIONS || sumHeight > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : sumHeight;
        float tempHeight = HEIGHT_1;
        HEIGHT_1 = sumHeight * HEIGHT_1 / (HEIGHT_1 + avgTwoHeight);
        avgTwoHeight = sumHeight * avgTwoHeight / (tempHeight + avgTwoHeight);

        if(avgTwoHeight < Utils.MIN_HIGHT){
            if(HEIGHT_1 + Utils.MIN_HIGHT > Utils.MAX_HEIGHT)
                HEIGHT_1 = Utils.MAX_HEIGHT - Utils.MIN_HIGHT;
            avgTwoHeight = Utils.MIN_HIGHT;
        }else if(HEIGHT_1 < Utils.MIN_HIGHT){
            if(avgTwoHeight + Utils.MIN_HIGHT > Utils.MAX_HEIGHT)
                avgTwoHeight = Utils.MAX_HEIGHT - Utils.MIN_HIGHT;
            HEIGHT_1 = Utils.MIN_HIGHT;
        }
        HEIGHT_2 = HEIGHT_3 = avgTwoHeight;
    }
    private static void calculateWidthAndHeightHorz(){
        //right images height
        float sumTwoHeight = HEIGHT_2 + HEIGHT_3;
        sumTwoHeight = Utils.HAS_FIXED_DIMENSIONS || sumTwoHeight > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : sumTwoHeight;
        HEIGHT_1 = Utils.HAS_FIXED_DIMENSIONS || HEIGHT_1 > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : HEIGHT_1;

        float avgHeight = (sumTwoHeight + HEIGHT_1) / 2f;

        HEIGHT_1 = avgHeight;

        float tempHeight = HEIGHT_2;
        HEIGHT_2 = avgHeight * HEIGHT_2/(HEIGHT_2 + HEIGHT_3);
        HEIGHT_3 = avgHeight * HEIGHT_3/(tempHeight + HEIGHT_3);

        if(HEIGHT_2 < Utils.MIN_HIGHT){
            HEIGHT_2 = Utils.MIN_HIGHT;
            if(HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT)
                HEIGHT_3 = Utils.MAX_HEIGHT - HEIGHT_2;
        }else if(HEIGHT_3 < Utils.MIN_HIGHT){
            HEIGHT_3 = Utils.MIN_HIGHT;
            if(HEIGHT_2 + HEIGHT_3 > Utils.MAX_HEIGHT)
                HEIGHT_2 = Utils.MAX_HEIGHT - HEIGHT_3;
        }
        HEIGHT_1 = (int)HEIGHT_2 + (int)HEIGHT_3;

        float avgTwoWidth = (WIDTH_2 + WIDTH_3) / 2f;
        float sumWidth = WIDTH_1 + avgTwoWidth;

        sumWidth  = Utils.HAS_FIXED_DIMENSIONS || sumWidth > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : sumWidth;
        float tempWidth = WIDTH_1;
        WIDTH_1 = sumWidth * WIDTH_1 / (WIDTH_1 + avgTwoWidth);
        avgTwoWidth = sumWidth * avgTwoWidth / (tempWidth + avgTwoWidth);

        if(avgTwoWidth < Utils.MIN_WIDTH){
            if(WIDTH_1 + Utils.MIN_WIDTH > Utils.MAX_WIDTH)
                WIDTH_1 = Utils.MAX_WIDTH - Utils.MIN_WIDTH;
            avgTwoWidth = Utils.MIN_WIDTH;
        }else if(WIDTH_1 < Utils.MIN_WIDTH){
            if(avgTwoWidth + Utils.MIN_WIDTH > Utils.MAX_WIDTH)
                avgTwoWidth = Utils.MAX_WIDTH - Utils.MIN_WIDTH;
            WIDTH_1 = Utils.MIN_WIDTH;
        }
        WIDTH_2 = WIDTH_3 = avgTwoWidth;
    }
}
