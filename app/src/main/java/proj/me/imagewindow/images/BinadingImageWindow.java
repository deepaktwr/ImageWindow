package proj.me.imagewindow.images;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

import proj.me.imagewindow.BR;

/**
 * Created by root on 13/3/16.
 */
public class BinadingImageWindow extends BaseObservable{
    private int addBackgroundColor;
    private int addTextColor;
    private int progressBarColor;
    private String addText;
    private boolean progressBarVisibility;
    private boolean addTextVisibility;


    @Bindable public boolean isAddTextVisibility() {
        return addTextVisibility;
    }

    public void setAddTextVisibility(boolean addTextVisibility) {
        this.addTextVisibility = addTextVisibility;
		notifyPropertyChanged(BR.addTextVisibility);
    }

    @Bindable public boolean isProgressBarVisibility() {
        return progressBarVisibility;
    }

    public void setProgressBarVisibility(boolean progressBarVisibility) {
        this.progressBarVisibility = progressBarVisibility;
		notifyPropertyChanged(BR.progressBarVisibility);
    }

    @Bindable public String getAddText() {
        return addText;
    }

    public void setAddText(String addText) {
        this.addText = addText;
		notifyPropertyChanged(BR.addText);
    }

    @Bindable public int getAddBackgroundColor() {
        return addBackgroundColor;
    }

    public void setAddBackgroundColor(int addBackgroundColor) {
        this.addBackgroundColor = addBackgroundColor;
		notifyPropertyChanged(BR.addBackgroundColor);
    }

    @Bindable public int getAddTextColor() {
        return addTextColor;
    }

    public void setAddTextColor(int addTextColor) {
        this.addTextColor = addTextColor;
		notifyPropertyChanged(BR.addTextColor);
    }
    @Bindable
    public int getProgressBarColor() {
        return progressBarColor;
    }

    public void setProgressBarColor(int progressBarColor) {
        this.progressBarColor = progressBarColor;
		notifyPropertyChanged(BR.progressBarColor);
    }

    @BindingAdapter("android:layout_width")
    public static void setTextWidth(View view, int width){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

}
