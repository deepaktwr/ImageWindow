package proj.me.imagewindow.images.dimentions;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import proj.me.imagewindow.window.Utils;

import static junit.framework.Assert.assertTrue;

/**
 * Created by root on 10/4/16.
 */
@RunWith(Parameterized.class)
public class ShadeFourTest {
    private DimentionsModel dimentionsModel;
    public ShadeFourTest(DimentionsModel dimentionsModel){
        this.dimentionsModel = dimentionsModel;
    }


    @Parameterized.Parameters
    public static Collection<DimentionsModel> fakeData(){

        Utils.MIN_WIDTH = 285;
        Utils.MIN_HIGHT = 340;
        Utils.MAX_WIDTH = 670;
        Utils.MAX_HEIGHT = 780;

        int min3Height = (int)(3f * Utils.MIN_HIGHT / 1.5f);
        int min3Width = (int)(3f * Utils.MIN_WIDTH / 1.5f);

        int parallelMinWidth = (int)(Utils.MAX_WIDTH - (Utils.MAX_WIDTH / 5f));//subtracting 20% of the width
        int parallelMinHeight = (int)(Utils.MAX_HEIGHT- (Utils.MAX_HEIGHT / 5f));//subtracting 20% of height

        parallelMinWidth = parallelMinWidth >= min3Width ? parallelMinWidth : min3Width;
        parallelMinHeight = parallelMinHeight >= min3Height ? parallelMinHeight : min3Height;

        DimentionsModel[] dimentionsModels = new DimentionsModel[]{
                //layout type = vert
                /*new DimentionsModel(new int[]{
                        parallelMinWidth + 1, 500,
                        50, 200,
                        40, 20,
                        200, 20
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD, ImageOrder.FOURTH}, LayoutType.VERT),

                new DimentionsModel(new int[]{
                        parallelMinWidth - 20, 200,
                        parallelMinWidth + 100, 500,
                        10, 200,
                        100, 400
                }, new ImageOrder[]{ImageOrder.SECOND, ImageOrder.FIRST, ImageOrder.THIRD, ImageOrder.FOURTH}, LayoutType.VERT),

                new DimentionsModel(new int[]{
                        parallelMinWidth - 1, 600,
                        100, 200,
                        (int)Utils.MAX_WIDTH, 400,
                        400, 500
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.FOURTH}, LayoutType.VERT),

                new DimentionsModel(new int[]{
                        300, 700,
                        parallelMinWidth - 1, 600,
                        600, 600,
                        (int)Utils.MAX_WIDTH - 50, 700
                }, new ImageOrder[]{ImageOrder.FOURTH, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.VERT),*/


                //layout type = horz
                /*new DimentionsModel(new int[]{
                        300, parallelMinHeight + 1,
                        20, 60,
                        60, 60,
                        400, 500
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD, ImageOrder.FOURTH}, LayoutType.HORZ),

                new DimentionsModel(new int[]{
                        300, parallelMinHeight - 1,
                        200, (int)Utils.MAX_HEIGHT,
                        200, 60,
                        500, 500
                }, new ImageOrder[]{ImageOrder.SECOND, ImageOrder.FIRST, ImageOrder.THIRD, ImageOrder.FOURTH}, LayoutType.HORZ),

                new DimentionsModel(new int[]{
                        20, 500,
                        20, 60,
                        30, parallelMinHeight + 1,
                        50, 500
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.FOURTH}, LayoutType.HORZ),

                new DimentionsModel(new int[]{
                        20, 500,
                        200, 60,
                        50, 500,
                        300, 700,
                }, new ImageOrder[]{ImageOrder.FOURTH, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.HORZ),

                new DimentionsModel(new int[]{
                        200, 500,
                        200, 60,
                        300, 4000,
                        500, 500
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.FOURTH}, LayoutType.HORZ),*/

                //view double
                /*new DimentionsModel(new int[]{
                        2000, 500,
                        2000, 60,
                        300, 1000,
                        500, 500
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD, ImageOrder.FOURTH}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        600, 500,
                        300, 100,
                        (int)Utils.MAX_WIDTH, 600,
                        500, 500
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.FOURTH}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        (int)Utils.MAX_WIDTH, 60,
                        30, 10,
                        600, 50,
                        50, 50
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.THIRD, ImageOrder.SECOND, ImageOrder.FOURTH}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        parallelMinWidth + 10, 60,
                        30, 100,
                        50, 500,
                        parallelMinWidth + 1, 50,
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.FOURTH, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        parallelMinWidth + 100, 50,
                        5000, 60,
                        30, 500,
                        50, 50,
                }, new ImageOrder[]{ImageOrder.SECOND, ImageOrder.FIRST, ImageOrder.THIRD, ImageOrder.FOURTH}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        5, 5,
                        700, 5,
                        parallelMinWidth + 10, 5,
                        5, 5,
                }, new ImageOrder[]{ImageOrder.SECOND, ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.FOURTH}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        600, 500,
                        parallelMinWidth + 800, 500,
                        700, 500,
                        800, 600,
                }, new ImageOrder[]{ImageOrder.SECOND, ImageOrder.FOURTH, ImageOrder.FIRST, ImageOrder.THIRD}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        parallelMinWidth + 100, 50,
                        30, 500,
                        5000, 60,
                        50, 50,
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.FOURTH}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        300, 500,
                        parallelMinWidth + 100, 500,
                        800, 600,
                        500, 500,
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.SECOND, ImageOrder.FIRST, ImageOrder.FOURTH}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        300, 50,
                        500, 50,
                        800, 600,
                        parallelMinWidth + 100, 500,
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FOURTH, ImageOrder.FIRST, ImageOrder.SECOND}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        parallelMinWidth + 100, 500,
                        300, 50,
                        500, 50,
                        800, 600,
                }, new ImageOrder[]{ImageOrder.FOURTH, ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        3000, 2000,
                        parallelMinWidth + 5000, 2500,
                        5000, 3500,
                        8000, 4600,
                }, new ImageOrder[]{ImageOrder.FOURTH, ImageOrder.SECOND, ImageOrder.FIRST, ImageOrder.THIRD}, LayoutType.VERT_DOUBLE),

                new DimentionsModel(new int[]{
                        500, 350,
                        300, 200,
                        parallelMinWidth + 200, 50,
                        800, 60,
                }, new ImageOrder[]{ImageOrder.FOURTH, ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND}, LayoutType.VERT_DOUBLE),*/

                new DimentionsModel(new int[]{
                        500, 350,
                        300, 200,
                        parallelMinWidth + 200, 50,
                        800, 60,
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD, ImageOrder.FOURTH}, LayoutType.HORZ_DOUBLE),

        };
        return Arrays.asList(dimentionsModels);
    }

    @Test
    public void calculateDimentions_ShouldReturnBean(){
        BeanShade4 beanShade4Result = ShadeFour.calculateDimentions(
                dimentionsModel.params[0], dimentionsModel.params[1],
                dimentionsModel.params[2], dimentionsModel.params[3],
                dimentionsModel.params[4], dimentionsModel.params[5],
                dimentionsModel.params[6], dimentionsModel.params[7]
                );

        System.out.println("Start++++++++++++++++++++++++++++++++++++Start");
        System.out.println("getWidth1 : "+beanShade4Result.getWidth1());
        System.out.println("getHeight1 : "+beanShade4Result.getHeight1());
        System.out.println("getWidth2 : "+beanShade4Result.getWidth2());
        System.out.println("getHeight2 : "+beanShade4Result.getHeight2());
        System.out.println("getWidth3 : "+beanShade4Result.getWidth3());
        System.out.println("getHeight3 : "+beanShade4Result.getHeight3());
        System.out.println("getWidth4 : "+beanShade4Result.getWidth4());
        System.out.println("getHeight4 : "+beanShade4Result.getHeight4());
        for(int i=0;i<4;i++){
            System.out.println("image order : "+ beanShade4Result.getImageOrderList().get(i));
        }
        System.out.println("layoutType : "+beanShade4Result.getLayoutType());
        System.out.println("End++++++++++++++++++++++++++++++++++++End");

        assertTrue(beanShade4Result.getLayoutType() == dimentionsModel.layoutType);
        assertTrue(beanShade4Result.getImageOrderList().get(0) == dimentionsModel.imageOrders[0]);
        assertTrue(beanShade4Result.getImageOrderList().get(1) == dimentionsModel.imageOrders[1]);
        assertTrue(beanShade4Result.getImageOrderList().get(2) == dimentionsModel.imageOrders[2]);
        assertTrue(beanShade4Result.getImageOrderList().get(3) == dimentionsModel.imageOrders[3]);

        float MIN_HEIGHT = 0f;
        float MIN_WIDTH = 0f;

        switch(beanShade4Result.getLayoutType()){
            case VERT:
                MIN_HEIGHT = Utils.MIN_HIGHT;
                MIN_WIDTH = Utils.MIN_WIDTH / 1.5f;

                assertTrue(beanShade4Result.getWidth1() >= beanShade4Result.getWidth2() +
                        beanShade4Result.getWidth3() + beanShade4Result.getWidth4() - 1);

                assertTrue(beanShade4Result.getWidth2() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth3() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth4() >= MIN_WIDTH - 1);

                assertTrue(beanShade4Result.getHeight1() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight2() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight3() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight4() >= MIN_HEIGHT - 1);

                assertTrue(beanShade4Result.getWidth1() <= Utils.MAX_WIDTH);
                break;
            case HORZ:
                MIN_HEIGHT = Utils.MIN_HIGHT / 1.5f;
                MIN_WIDTH = Utils.MIN_WIDTH;

                assertTrue(beanShade4Result.getHeight1() >= beanShade4Result.getHeight2() +
                        beanShade4Result.getHeight3() + beanShade4Result.getHeight4() - 2);

                assertTrue(beanShade4Result.getHeight2() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight3() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight4() >= MIN_HEIGHT - 1);

                assertTrue(beanShade4Result.getWidth1() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth2() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth3() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth4() >= MIN_WIDTH - 1);

                assertTrue(beanShade4Result.getHeight1() <= Utils.MAX_HEIGHT);
                break;
            case VERT_DOUBLE:
                MIN_HEIGHT = Utils.MIN_HIGHT / 1.5f;
                MIN_WIDTH = Utils.MIN_WIDTH;

                assertTrue(beanShade4Result.getWidth1() == beanShade4Result.getWidth2());
                assertTrue(beanShade4Result.getWidth1() >= beanShade4Result.getWidth3() + beanShade4Result.getWidth4() - 2);

                assertTrue(beanShade4Result.getWidth3() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth4() >= MIN_WIDTH - 1);

                assertTrue(beanShade4Result.getHeight3() == beanShade4Result.getHeight4());

                assertTrue(beanShade4Result.getHeight1() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight2() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight3() >= MIN_HEIGHT - 1);

                assertTrue(beanShade4Result.getWidth1() <= Utils.MAX_WIDTH);
                break;
            case HORZ_DOUBLE:
                MIN_HEIGHT = Utils.MIN_HIGHT;
                MIN_WIDTH = Utils.MIN_WIDTH / 1.5f;

                assertTrue(beanShade4Result.getHeight1() == beanShade4Result.getHeight2());
                assertTrue(beanShade4Result.getHeight1() >= beanShade4Result.getHeight3() + beanShade4Result.getHeight4() - 2);

                assertTrue(beanShade4Result.getHeight3() >= MIN_HEIGHT - 1);
                assertTrue(beanShade4Result.getHeight4() >= MIN_HEIGHT - 1);

                assertTrue(beanShade4Result.getWidth3() == beanShade4Result.getWidth4());

                assertTrue(beanShade4Result.getWidth1() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth2() >= MIN_WIDTH - 1);
                assertTrue(beanShade4Result.getWidth3() >= MIN_WIDTH - 1);

                assertTrue(beanShade4Result.getHeight1() <= Utils.MAX_HEIGHT);
                break;
        }

    }

    private static final class DimentionsModel{
        ImageOrder[] imageOrders;
        LayoutType layoutType;
        int[] params;
        DimentionsModel(int[] params, ImageOrder[] imageOrders, LayoutType layoutType){
            this.layoutType = layoutType;
            this.imageOrders = imageOrders;
            this.params = params;
        }
    }
}
