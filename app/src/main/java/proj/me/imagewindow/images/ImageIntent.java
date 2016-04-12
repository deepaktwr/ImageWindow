package proj.me.imagewindow.images;

import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import proj.me.imagewindow.window.Utils;

/**
 * Created by Deepak.Tiwari on 30-10-2015.
 */
public class ImageIntent {
    private Activity activity;
    private String fileName;
    private BitmapCompressure bitmapCompresur;
    private CustomDialogFrag customDialogFrag;
    private Uri fileUri;
    public ImageIntent(Activity activity){
        this.activity = activity;
        bitmapCompresur = new BitmapCompressure(activity);
    }

    public void callIntentForImage(int actionId) {
        fileUri =  null;
        customDialogFrag = new CustomDialogFrag(ImageIntent.this,
                actionId);
        customDialogFrag.show(activity.getFragmentManager(), "myImageDialog"
                + actionId);
    }

    void takePhoto(int actionId) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra("type", 1);
        try {
            createImageFile();
        } catch (IOException ex) {
            // Error occurred while creating the File
        }
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        activity.startActivityForResult(takePictureIntent, actionId);

    }

    void fetchFromGallery(int actionId) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("type", 1);
        activity.startActivityForResult(intent, actionId);
    }

    private void createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        ContentValues values = new ContentValues();
        fileName = "IMG_" + timeStamp + ".jpg";
        values.put(MediaStore.Images.Media.TITLE, fileName);
        fileUri = activity.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Utils.logError("file Uri ->"+fileUri);// values
    }

    private Uri saveCapturedFile(Uri fileUri,String extention, boolean isDelete)
    {
        //use nio
        Utils.logError("compressor->"+bitmapCompresur);
        Utils.logError("uri->"+fileUri);
        Bitmap bitmap=bitmapCompresur.createScaledBitmap(fileUri.toString(), isDelete);
        if(bitmap == null) {
            Log.e("bitmap", "nullllllllllllllllllll");
            return fileUri;
        }
        deleteFileFromContentValues(fileUri, isDelete);
        File dir1=new File(Environment.getExternalStorageDirectory(),"window"+"/");
        if(!dir1.exists())
            dir1.mkdirs();
        File dir=new File(dir1,"window/");
        if(!dir.exists())
            dir.mkdirs();
        String val="img";
        File file=new File(dir,val+"_"+System.currentTimeMillis()+
                extention);
        FileOutputStream fileOutputStrem=null;
        try {
            fileOutputStrem=new FileOutputStream(file);
            //bitmap.compress(null, 0, fileOutputStrem);
            if(fileUri == null)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 95, fileOutputStrem);
            else
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStrem);

            fileOutputStrem.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Uri.fromFile(file);

    }

    private void deleteFileFromContentValues(Uri fileUri,boolean isDelete) {
        if (isDelete)
            activity.getContentResolver().delete(fileUri, null, null);
    }
    public String getImageResultPath(Intent data, int actionId, String comment) {

        if((data == null || data.getData() == null) && fileUri == null) return null;
        Utils.logMessage("comment on image -> "+comment);
        String imagePath = "";
            String extention = ".jpg";
            if (data != null && data.getData() != null) {
                Uri img = data.getData();
                String[] filePathClm = { MediaStore.Images.Media.DATA };
                Cursor c = activity.getContentResolver().query(img, filePathClm,
                        null, null, null);
                c.moveToFirst();

                int clmIndex = c.getColumnIndex(filePathClm[0]);
                String path = c.getString(clmIndex);
                c.close();
                if(!TextUtils.isEmpty(path))
                    extention = path.substring(path.lastIndexOf("."));

                imagePath = saveCapturedFile(img,extention,false).toString();
            }else
                imagePath = saveCapturedFile(fileUri, extention, true).toString();
        return imagePath;
    }

    /**
     * Method to get file path from Uri
     *
     * @param contentUri uri of selected file
     * @return
     */
    public String getPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(activity, contentUri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
