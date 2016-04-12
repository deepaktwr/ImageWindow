package proj.me.imagewindow.images.dimentions;

import proj.me.imagewindow.window.Utils;

/**
 * Created by root on 21/3/16.
 */
public class ShadeOne {
    public static BeanShade1 calculateDimentions(int width, int height){
        BeanShade1 beanShade1 = new BeanShade1();
        float WIDTH_1 = width > Utils.MAX_WIDTH ? Utils.MAX_WIDTH : width < Utils.MIN_WIDTH ? Utils.MIN_WIDTH : width;
        float HEIGHT_1 = height > Utils.MAX_HEIGHT ? Utils.MAX_HEIGHT : height < Utils.MIN_HIGHT ? Utils.MIN_HIGHT : height;
        beanShade1.setWidth1((int)WIDTH_1);
        beanShade1.setHeight1((int)HEIGHT_1);
        return beanShade1;
    }
}
