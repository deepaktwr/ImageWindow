package proj.me.imagewindow.images;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Map;

/**
 * Created by Deepak.Tiwari on 02-12-2015.
 */
public class ImageScrollParcelable implements Parcelable{

    private ImageType imageType;
    private int imagePosition;
    private List<String> imageUrls;
    private List<String> loadedImageUrls;
    private Map<String, String> dataTexts;

    public Map<String, String> getDataTexts() {
        return dataTexts;
    }

    public void setDataTexts(Map<String, String> dataTexts) {
        this.dataTexts = dataTexts;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public int getImagePosition() {
        return imagePosition;
    }

    public void setImagePosition(int imagePosition) {
        this.imagePosition = imagePosition;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getLoadedImageUrls() {
        return loadedImageUrls;
    }

    public void setLoadedImageUrls(List<String> loadedImageUrls) {
        this.loadedImageUrls = loadedImageUrls;
    }

    public ImageScrollParcelable(){}
    protected ImageScrollParcelable(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<ImageScrollParcelable> CREATOR = new Creator<ImageScrollParcelable>() {
        @Override
        public ImageScrollParcelable createFromParcel(Parcel in) {
            return new ImageScrollParcelable(in);
        }

        @Override
        public ImageScrollParcelable[] newArray(int size) {
            return new ImageScrollParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(imageType);
        parcel.writeInt(imagePosition);
        parcel.writeList(imageUrls);
        parcel.writeList(loadedImageUrls);
        parcel.writeMap(dataTexts);
    }
    private void readFromParcel(Parcel in){
        imageType = (ImageType)in.readValue(getClass().getClassLoader());
        imagePosition = in.readInt();
        in.readList(imageUrls, getClass().getClassLoader());
        in.readList(loadedImageUrls, getClass().getClassLoader());
        in.readMap(dataTexts, getClass().getClassLoader());
    }
}
