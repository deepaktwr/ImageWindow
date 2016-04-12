package proj.me.imagewindow.images;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Map;

import proj.me.imagewindow.R;

/**
 * Created by Deepak.Tiwari on 01-12-2015.
 */
public class ImageScrollFragment extends Fragment{
    private View rootView;
    private LinearLayout scrollLinear;
    private ScrollView scrollContainer;
    private ImageView image1, image2, image3, image4;
    private RelativeLayout rl1, rl2, rl3, rl4;
    private TextView text1, text2, text3, text4;

    private ImageType imageType;
    private int imagePosition;
    private List<String> imageUrls;
    private List<String> loadedImageUrls;

    private Bundle imageBundle;
    private Map<String, String> dataTexts;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            imageBundle = getArguments();
        else
            imageBundle = savedInstanceState.getBundle("image_bundle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return rootView = inflater.inflate(R.layout.imag_scrolling, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollLinear = (LinearLayout)rootView.findViewById(R.id.image_vert_scroll);
        scrollContainer = (ScrollView)rootView.findViewById(R.id.scrolllll);

        image1 = (ImageView)rootView.findViewById(R.id.scroll_image_1);
        image2 = (ImageView)rootView.findViewById(R.id.scroll_image_2);
        image3 = (ImageView)rootView.findViewById(R.id.scroll_image_3);
        image4 = (ImageView)rootView.findViewById(R.id.scroll_image_4);
        text1 = (TextView) rootView.findViewById(R.id.scroll_text_1);
        text2 = (TextView)rootView.findViewById(R.id.scroll_text_2);
        text3 = (TextView)rootView.findViewById(R.id.scroll_text_3);
        text4 = (TextView)rootView.findViewById(R.id.scroll_text_4);
        rl1 = (RelativeLayout)rootView.findViewById(R.id.scroll_rl_1);
        rl2 = (RelativeLayout)rootView.findViewById(R.id.scroll_rl_2);
        rl3 = (RelativeLayout)rootView.findViewById(R.id.scroll_rl_3);
        rl4 = (RelativeLayout)rootView.findViewById(R.id.scroll_rl_4);

        ImageScrollParcelable imageScrollParcelable = imageBundle.getParcelable("image_data");
        imageType = imageScrollParcelable.getImageType();
        imagePosition = imageScrollParcelable.getImagePosition();
        imageUrls = imageScrollParcelable.getImageUrls();
        loadedImageUrls = imageScrollParcelable.getLoadedImageUrls();
        dataTexts = imageScrollParcelable.getDataTexts();
        imageClicked();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context =activity;
    }

    public void imageClicked() {
        if(imageType == null) {
            //scrollContainer.setVisibility(View.VISIBLE);
            showImagesFromUrl(imageUrls);
            return;
        }

        switch(imageType){
            case VIEW_SINGLE:
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                rl4.setVisibility(View.GONE);
                if(loadedImageUrls.get(0) == null) return;
                Picasso.with(context).load(loadedImageUrls.get(0)).into(image1);
                text1.setText(dataTexts.get(loadedImageUrls.get(0)));
                break;
            case VIEW_DOUBLE:
                rl3.setVisibility(View.GONE);
                rl4.setVisibility(View.GONE);
                if(loadedImageUrls.get(0) == null || loadedImageUrls.get(1) == null) return;
                Picasso.with(context).load(loadedImageUrls.get(0)).into(image1);
                Picasso.with(context).load(loadedImageUrls.get(1)).into(image2);
                text1.setText(dataTexts.get(loadedImageUrls.get(0)));
                text2.setText(dataTexts.get(loadedImageUrls.get(1)));
                break;
            case VIEW_TRIPLE_1:
                rl4.setVisibility(View.GONE);
                if(loadedImageUrls.get(0) == null || loadedImageUrls.get(1) == null || loadedImageUrls.get(2) == null) return;
                Picasso.with(context).load(loadedImageUrls.get(0)).into(image1);
                Picasso.with(context).load(loadedImageUrls.get(1)).into(image2);
                Picasso.with(context).load(loadedImageUrls.get(2)).into(image3);
                text1.setText(dataTexts.get(loadedImageUrls.get(0)));
                text2.setText(dataTexts.get(loadedImageUrls.get(1)));
                text3.setText(dataTexts.get(loadedImageUrls.get(2)));
                break;
            case VIEW_MULTIPLE_1:
                if(loadedImageUrls.get(0) == null || loadedImageUrls.get(1) == null || loadedImageUrls.get(2) == null || loadedImageUrls.get(3) == null) return;
                Picasso.with(context).load(loadedImageUrls.get(0)).into(image1);
                Picasso.with(context).load(loadedImageUrls.get(1)).into(image2);
                Picasso.with(context).load(loadedImageUrls.get(2)).into(image3);
                Picasso.with(context).load(loadedImageUrls.get(3)).into(image4);
                text1.setText(dataTexts.get(loadedImageUrls.get(0)));
                text2.setText(dataTexts.get(loadedImageUrls.get(1)));
                text3.setText(dataTexts.get(loadedImageUrls.get(2)));
                text4.setText(dataTexts.get(loadedImageUrls.get(3)));
                break;
        }
        showImagesFromUrl(imageUrls);
        scrollContainer.smoothScrollTo(0, imagePosition == 1 ? rl1.getTop() : (imagePosition == 2 ? rl2.getTop() : (imagePosition == 3 ? rl3.getTop() : rl4.getTop())));
        //scrollContainer.setVisibility(View.VISIBLE);
    }
    private void showImagesFromUrl(List<String> imageUrls){
        // images urls is the urls which need to be added dynamically
        if(imageUrls == null || imageUrls.size() == 0) return;
        for(String url : imageUrls)
            addDynamicImage(url);
    }
    private void addDynamicImage(String imageUrl){
        RelativeLayout relativeLayout = (RelativeLayout)LayoutInflater.from(context).inflate(R.layout.base_image_scroll, null);
        ImageView imageView = (ImageView)relativeLayout.findViewById(R.id.scroll_image_dyn);
        TextView textView = (TextView)relativeLayout.findViewById(R.id.scroll_text_dyn);

        Picasso.with(context).load(imageUrl).into(imageView);
        textView.setText(dataTexts.get(imageUrl));

        scrollLinear.addView(relativeLayout);
        scrollContainer.requestLayout();
        scrollContainer.invalidate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(imageBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
