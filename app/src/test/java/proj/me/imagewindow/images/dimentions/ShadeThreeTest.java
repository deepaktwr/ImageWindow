package proj.me.imagewindow.images.dimentions;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import proj.me.imagewindow.window.Utils;

/**
 * Created by root on 3/4/16.
 */
@RunWith(Parameterized.class)
public class ShadeThreeTest {
    private DimentionsModel dimentionsModel;
    public ShadeThreeTest(DimentionsModel dimentionsModel){
        this.dimentionsModel = dimentionsModel;
    }

    @Parameterized.Parameters
    public static Collection<DimentionsModel> fakeData(){
        Utils.MIN_WIDTH = 285;
        Utils.MIN_HIGHT = 340;
        Utils.MAX_WIDTH = 670;
        Utils.MAX_HEIGHT = 780;

        float offsetWidth = Utils.MAX_WIDTH / 10f;
        float totalHeight = Utils.MAX_HEIGHT + Utils.MAX_HEIGHT / 6f;

        float offsetHeight = Utils.MAX_HEIGHT / 5f;
        float totalWidth = Utils.MAX_WIDTH + Utils.MAX_WIDTH /6f;
        //8 cases
        DimentionsModel[] data = new DimentionsModel[]{
                //parallel horz
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        225, 653,
                        225, 653,
                        225, 653
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        400, 653,
                        100, 953,
                        200, 753
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        50, 1053,
                        550, 640,
                        200, 800
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        400, 700,
                        30, 900,
                        400, 850
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        350, 700,
                        100, 900,
                        250, 850
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        450, 700,
                        100, 900,
                        150, 850
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        600, 700,
                        100, 900,
                        100, 850
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),
                new DimentionsModel(new int[]{
                        //835>= >=670, 630
                        600, 700,
                        50, 900,
                        100, 850
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_HORZ),

                //parallel vert
                new DimentionsModel(new int[]{
                        650, 300,
                        650, 300,
                        650, 300
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_VERT),
                new DimentionsModel(new int[]{
                        //>650, 934>= >=780
                        650, 350,
                        900, 350,
                        700, 200
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_VERT),
                new DimentionsModel(new int[]{
                        //>650, 934>= >=780
                        1000, 420,
                        900, 400,
                        700, 100
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_VERT),
                new DimentionsModel(new int[]{
                        //>650, 934>= >=780
                        1000, 100,
                        900, 420,
                        700, 400
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_VERT),
                new DimentionsModel(new int[]{
                        //>650, 934>= >=780
                        1000, 400,
                        900, 100,
                        700, 420
                },new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.PARALLEL_VERT),

                //vert
                new DimentionsModel(new int[]{
                        1000, 500, 800, 400, 800, 400
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.VERT),

                new DimentionsModel(new int[]{
                        100, 500, 1200, 400, 800, 400
                }, new ImageOrder[]{ImageOrder.SECOND, ImageOrder.FIRST, ImageOrder.THIRD}, LayoutType.VERT),

                new DimentionsModel(new int[]{
                        100, 500, 200, 400, 800, 400
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND}, LayoutType.VERT),

                //horz
                new DimentionsModel(new int[]{
                        100, 500, 100, 400, 300, 400
                }, new ImageOrder[]{ImageOrder.FIRST, ImageOrder.SECOND, ImageOrder.THIRD}, LayoutType.HORZ),

                new DimentionsModel(new int[]{
                        200, 500, 500, 800, 600, 400
                }, new ImageOrder[]{ImageOrder.SECOND, ImageOrder.FIRST, ImageOrder.THIRD}, LayoutType.HORZ),

                new DimentionsModel(new int[]{
                        600, 600, 500, 700, 600, 1000
                }, new ImageOrder[]{ImageOrder.THIRD, ImageOrder.FIRST, ImageOrder.SECOND}, LayoutType.HORZ)
        };
        return Arrays.asList(data);
    }
    //before or before class annotations are not calling before the parameterized colletion method

    @Test
    public void calculateDimentions_ShouldReturnBean(){

        BeanShade3 beanShade3Result = ShadeThree.calculateDimentions(
                dimentionsModel.params[0], dimentionsModel.params[1],
                dimentionsModel.params[2], dimentionsModel.params[3],
                dimentionsModel.params[4], dimentionsModel.params[5]
        );

        System.out.println("Start++++++++++++++++++++++++++++++++++++Start");
        System.out.println("getWidth1 : "+beanShade3Result.getWidth1());
        System.out.println("getHeight1 : "+beanShade3Result.getHeight1());
        System.out.println("getWidth2 : "+beanShade3Result.getWidth2());
        System.out.println("getHeight2 : "+beanShade3Result.getHeight2());
        System.out.println("getWidth3 : "+beanShade3Result.getWidth3());
        System.out.println("getHeight3 : "+beanShade3Result.getHeight3());
        for(int i=0;i<3;i++){
            System.out.println("image order : "+ beanShade3Result.getImageOrderList().get(i));
        }
        System.out.println("layoutType : "+beanShade3Result.getLayoutType());
        System.out.println("End++++++++++++++++++++++++++++++++++++End");


        assertTrue(beanShade3Result.getLayoutType() == dimentionsModel.layoutType);
        assertTrue(beanShade3Result.getImageOrderList().get(0) == dimentionsModel.imageOrders[0]);
        assertTrue(beanShade3Result.getImageOrderList().get(1) == dimentionsModel.imageOrders[1]);
        assertTrue(beanShade3Result.getImageOrderList().get(2) == dimentionsModel.imageOrders[2]);

        switch(beanShade3Result.getLayoutType()){
            case PARALLEL_VERT:
                float parallelVertRequiredWidth = Utils.MAX_WIDTH - Utils.MAX_WIDTH / 10f;
                float minParallelHeight = Utils.MAX_HEIGHT - Utils.MAX_HEIGHT / 5f;

                assertTrue(beanShade3Result.getWidth1() >= parallelVertRequiredWidth);
                assertTrue(beanShade3Result.getWidth2() >= parallelVertRequiredWidth);
                assertTrue(beanShade3Result.getWidth3() >= parallelVertRequiredWidth);

                float MIN_HIGHT = Utils.MIN_HIGHT / 1.5f;
                assertTrue(beanShade3Result.getHeight1() >= MIN_HIGHT - 1);
                assertTrue(beanShade3Result.getHeight2() >= MIN_HIGHT - 1);
                assertTrue(beanShade3Result.getHeight3() >= MIN_HIGHT - 1);

                assertTrue(beanShade3Result.getHeight1() + beanShade3Result.getHeight2() + beanShade3Result.getHeight3() <= Utils.MAX_HEIGHT);
                assertTrue(beanShade3Result.getHeight1() + beanShade3Result.getHeight2() + beanShade3Result.getHeight3() >= minParallelHeight);

                assertTrue(beanShade3Result.getImageOrderList().get(0) == ImageOrder.FIRST);
                assertTrue(beanShade3Result.getImageOrderList().get(1) == ImageOrder.SECOND);
                assertTrue(beanShade3Result.getImageOrderList().get(2) == ImageOrder.THIRD);
                break;
            case PARALLEL_HORZ:
                float parallelHorzRequiredHeight = Utils.MAX_HEIGHT - Utils.MAX_HEIGHT / 5f;
                float minParallelWidth = Utils.MAX_WIDTH - Utils.MAX_WIDTH / 5f;

                assertTrue(beanShade3Result.getHeight1() >= parallelHorzRequiredHeight);
                assertTrue(beanShade3Result.getHeight2() >= parallelHorzRequiredHeight);
                assertTrue(beanShade3Result.getHeight3() >= parallelHorzRequiredHeight);

                float MIN_WIDTH = Utils.MIN_WIDTH / 1.5f;
                assertTrue(beanShade3Result.getWidth1() >= MIN_WIDTH - 1);
                assertTrue(beanShade3Result.getWidth2() >= MIN_WIDTH - 1);
                assertTrue(beanShade3Result.getWidth3() >= MIN_WIDTH - 1);

                assertTrue(beanShade3Result.getWidth1() + beanShade3Result.getWidth2() + beanShade3Result.getWidth3() <= Utils.MAX_WIDTH);
                assertTrue(beanShade3Result.getWidth1() + beanShade3Result.getWidth2() + beanShade3Result.getWidth3() >= minParallelWidth);

                assertTrue(beanShade3Result.getImageOrderList().get(0) == ImageOrder.FIRST);
                assertTrue(beanShade3Result.getImageOrderList().get(1) == ImageOrder.SECOND);
                assertTrue(beanShade3Result.getImageOrderList().get(2) == ImageOrder.THIRD);
                break;
            case VERT:
                assertTrue(beanShade3Result.getHeight1() >= Utils.MIN_HIGHT);
                assertTrue(beanShade3Result.getHeight2() >= Utils.MIN_HIGHT);
                assertTrue(beanShade3Result.getHeight3() >= Utils.MIN_HIGHT);
                assertTrue(beanShade3Result.getHeight1() + beanShade3Result.getHeight2() <= Utils.MAX_HEIGHT);
                assertTrue(beanShade3Result.getHeight2() == beanShade3Result.getHeight3());

                assertTrue(beanShade3Result.getWidth2() >= Utils.MIN_WIDTH);
                assertTrue(beanShade3Result.getWidth3() >= Utils.MIN_WIDTH);
                assertTrue(beanShade3Result.getWidth1() == beanShade3Result.getWidth2() + beanShade3Result.getWidth3());
                assertTrue(beanShade3Result.getWidth1() <= Utils.MAX_WIDTH);
                break;
            case HORZ:
                assertTrue(beanShade3Result.getWidth1() >= Utils.MIN_WIDTH);
                assertTrue(beanShade3Result.getWidth2() >= Utils.MIN_WIDTH);
                assertTrue(beanShade3Result.getWidth3() >= Utils.MIN_WIDTH);
                assertTrue(beanShade3Result.getWidth1() + beanShade3Result.getWidth2() <= Utils.MAX_WIDTH);
                assertTrue(beanShade3Result.getWidth2() == beanShade3Result.getWidth3());

                assertTrue(beanShade3Result.getHeight2() >= Utils.MIN_HIGHT);
                assertTrue(beanShade3Result.getHeight3() >= Utils.MIN_HIGHT);
                assertTrue(beanShade3Result.getHeight1() == beanShade3Result.getHeight2() + beanShade3Result.getHeight3());
                assertTrue(beanShade3Result.getHeight1() <= Utils.MAX_HEIGHT);
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
