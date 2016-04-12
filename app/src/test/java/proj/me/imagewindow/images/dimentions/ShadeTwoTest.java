package proj.me.imagewindow.images.dimentions;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import proj.me.imagewindow.window.Utils;

/**
 * Created by root on 23/3/16.
 */
@RunWith(Parameterized.class)
public class ShadeTwoTest {

    private DimentionsModel dimentionsModel;
    public ShadeTwoTest(DimentionsModel dimentionsModel){
        this.dimentionsModel = dimentionsModel;
    }

    @Parameterized.Parameters
    public static Collection<DimentionsModel> fakeData(){
        Utils.MIN_WIDTH = 285;
        Utils.MIN_HIGHT = 340;
        Utils.MAX_WIDTH = 670;
        Utils.MAX_HEIGHT = 780;

        boolean vertLayout = true;
        boolean firstImageLeftOrTop = true;
        DimentionsModel[] data = new DimentionsModel[]{
                new DimentionsModel(new int[]{500, 400, 300, 100}, firstImageLeftOrTop, vertLayout),
                new DimentionsModel(new int[]{5000, 4000, 8000, 10000}, !firstImageLeftOrTop, !vertLayout),
                new DimentionsModel(new int[]{200, 500, 300, 400}, firstImageLeftOrTop, !vertLayout),
                new DimentionsModel(new  int[]{10, 20, 30, 40}, !firstImageLeftOrTop, !vertLayout),
                new DimentionsModel(new  int[]{500, 20, 500, 40}, firstImageLeftOrTop, vertLayout),
                new DimentionsModel(new  int[]{500, 600, 5000, 400}, !firstImageLeftOrTop, vertLayout),
                new DimentionsModel(new  int[]{105, 186, 300, 168}, firstImageLeftOrTop, !vertLayout),
                new DimentionsModel(new  int[]{300, 168, 105, 186}, !firstImageLeftOrTop, !vertLayout),
                new DimentionsModel(new  int[]{500, 1860, 1000, 1680}, firstImageLeftOrTop, !vertLayout),
        };
        return Arrays.asList(data);
    }

    @Test
    public void calculateDimentions_ShouldReturnBean(){
        BeanShade2 beanShade2Result = ShadeTwo.calculateDimentions
                (dimentionsModel.params[0],
                dimentionsModel.params[1],
                dimentionsModel.params[2],
                dimentionsModel.params[3]);

        boolean isFirstImageLeftOrTop = beanShade2Result.getImageOrderList().get(0) == ImageOrder.FIRST;
        boolean isVertLayout = beanShade2Result.getLayoutType() == LayoutType.VERT;

        System.out.println("Start++++++++++++++++++++++++++++++++++++Start");
        System.out.println("getWidth1 : "+beanShade2Result.getWidth1());
        System.out.println("getHeight1 : "+beanShade2Result.getHeight1());
        System.out.println("getWidth2 : "+beanShade2Result.getWidth2());
        System.out.println("getHeight2 : "+beanShade2Result.getHeight2());
        System.out.println("isFirstImageLeftOrTop : "+isFirstImageLeftOrTop);
        System.out.println("isVertLayout : "+isVertLayout);
        System.out.println("End++++++++++++++++++++++++++++++++++++End");

        assertEquals(dimentionsModel.firstImageLeftOrTop, isFirstImageLeftOrTop);
        assertEquals(dimentionsModel.vertLayout, isVertLayout);


        if(dimentionsModel.vertLayout){
            assertTrue(beanShade2Result.getWidth1() >= Utils.MIN_WIDTH);
            assertTrue(beanShade2Result.getWidth1() <= Utils.MAX_WIDTH);

            assertTrue(beanShade2Result.getWidth2() >= Utils.MIN_WIDTH);
            assertTrue(beanShade2Result.getWidth2() <= Utils.MAX_WIDTH);

            assertTrue(beanShade2Result.getHeight1() >= Utils.MIN_HIGHT);
            assertTrue(beanShade2Result.getHeight2() >= Utils.MIN_HIGHT);

            float sumHeight = beanShade2Result.getHeight1() + beanShade2Result.getHeight2();

            assertTrue(sumHeight <= Utils.MAX_HEIGHT);

            /*float height1Ratio = beanShade2Result.getHeight1() / sumHeight;
            float height2Ratio = beanShade2Result.getHeight2() / sumHeight;

            assertTrue(height1Ratio >= Utils.MIN_RATIO || height1Ratio <= Utils.MAX_RATIO);
            assertTrue(height2Ratio >= Utils.MIN_RATIO || height2Ratio <= Utils.MAX_RATIO);*/

            if(beanShade2Result.isAddInLayout())
                assertTrue(beanShade2Result.getWidth1() > beanShade2Result.getWidth2());
            else assertTrue(beanShade2Result.getWidth1() == beanShade2Result.getWidth2());
        }else{
            assertTrue(beanShade2Result.getHeight1() >= Utils.MIN_HIGHT);
            assertTrue(beanShade2Result.getHeight1() <= Utils.MAX_HEIGHT);

            assertTrue(beanShade2Result.getHeight2() >= Utils.MIN_HIGHT);
            assertTrue(beanShade2Result.getHeight2() <= Utils.MAX_HEIGHT);

            assertTrue(beanShade2Result.getWidth1() >= Utils.MIN_WIDTH);
            assertTrue(beanShade2Result.getWidth2() >= Utils.MIN_WIDTH);

            float sumWidth = beanShade2Result.getWidth1() + beanShade2Result.getWidth2();

            assertTrue(sumWidth <= Utils.MAX_WIDTH);

            /*float width1Ratio = beanShade2Result.getWidth1() / sumWidth;
            float width2Ratio = beanShade2Result.getWidth2() / sumWidth;

            assertTrue(width1Ratio >= Utils.MIN_RATIO || width1Ratio <= Utils.MAX_RATIO);
            assertTrue(width2Ratio >= Utils.MIN_RATIO || width2Ratio <= Utils.MAX_RATIO);*/

            if(beanShade2Result.isAddInLayout())
                assertTrue(beanShade2Result.getHeight1() > beanShade2Result.getHeight2());
            else assertTrue(beanShade2Result.getHeight1() == beanShade2Result.getHeight2());
        }

    }

    private static final class DimentionsModel{
        boolean vertLayout;
        boolean firstImageLeftOrTop;
        int[] params;
        DimentionsModel(int[] params, boolean firstImageLeftOrTop, boolean vertLayout){
            this.vertLayout = vertLayout;
            this.firstImageLeftOrTop = firstImageLeftOrTop;
            this.params = params;
        }
    }
}
