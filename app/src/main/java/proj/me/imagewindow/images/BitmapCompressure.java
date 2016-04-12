package proj.me.imagewindow.images;

import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class BitmapCompressure {

	private Context context;
	private final int REQUARED_SIZE=600;
	public BitmapCompressure(Context context)
	{
		this.context=context;
	}
	public Bitmap createScaledBitmap(String fileUri, boolean fromCamera)
	{
		String stringVal=fileUri.toString();
		if(fromCamera && stringVal.contains("."))
			stringVal=stringVal.substring(0,stringVal.lastIndexOf('.'));
		Uri uri=Uri.parse(stringVal);
		return /*decodeSampledBitmapFromResource(uri,500,500);*/decodeFile(uri)/*getImageFromUri(uri)*/;
	}
	public Bitmap getImageFromUri(Uri fileUri)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		try{
		return BitmapFactory.decodeStream(context.getContentResolver()
				.openInputStream(fileUri), null, options);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}
	private Bitmap decodeFile(Uri fileUri)
	{
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		try {
			BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(fileUri), null, options);
			int scale = 1;
			if(options.outWidth > 1080 || options.outHeight > 1920) {
				int resizeWidth = (int)((float)options.outWidth/4.1f);
				int resizeHeight = (int)((float)options.outHeight/4.1f);
				while (options.outWidth / scale / 2 > resizeWidth && options.outHeight / scale / 2 > resizeHeight)
					scale *= 2;
			}
			BitmapFactory.Options optionsNew=new BitmapFactory.Options();
			optionsNew.inSampleSize=scale;
			
			return BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(fileUri), null, optionsNew);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
/*	private Bitmap decodeSampledBitmapFromResource(Uri fileUri, int reqWidth,
			int reqHeight){
		try
		{
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(context.getContentResolver()
				.openInputStream(fileUri), null, options);
		options.inSampleSize = calculateInSampleSize(options,reqWidth,
				reqWidth);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(context.getContentResolver()
				.openInputStream(fileUri), null, options);
		}catch(Exception e)
		{
			return null;
		}
	}

	private int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			while ((height / inSampleSize) > reqHeight
					&& (width / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}*/
}
