package in.woodu.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import in.woodu.R;
import in.woodu.model.FurnitureItem;
import in.woodu.widget.BadgeDrawable;

/**
 * Created by KT on 8/1/15.
 */
public class Utils {

    public static final String ROOT_DIR = Environment.getExternalStorageDirectory().getPath()
            + File.separator + "Halo" + File.separator;

    public static void setBadgeCount(Context context, LayerDrawable icon, int count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    public static void copyFileOrDir(Context mContext, String path) {
        AssetManager assetManager = mContext.getAssets();
        String assets[] = null;
        try {
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(mContext, path);
            } else {
                File dir = new File(Environment.getExternalStorageDirectory(), path);
                if (!dir.exists())
                    dir.mkdir();
                for (int i = 0; i < assets.length; ++i) {
                    copyFileOrDir(mContext, path + "/" + assets[i]);
                }
            }
        } catch (IOException ex) {
            Log.e("tag", "I/O Exception", ex);
        }
    }

    private static  void copyFile(Context mContext, String filename) {
        AssetManager assetManager = mContext.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            String newFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }

    public static Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals("Oswald")) {
            switch (textStyle) {
                case 1: // bold
                    return Typeface.createFromAsset(context.getAssets(), "fonts/Oswald-Bold.ttf");

                case 2: // italic
                    return Typeface.createFromAsset(context.getAssets(), "fonts/Oswald-Italic.ttf");

                case 0: // regular
                default:
                    return Typeface.createFromAsset(context.getAssets(), "fonts/Oswald-Regular.ttf");
            }
        } else {
            return null;
        }
    }

    public static FurnitureItem readProperties(File dir, String productid) {
        File propFile = new File(dir, productid);
        File imageFile = new File(propFile.getAbsolutePath().replace(".properties", ".jpg"));
        Properties prop = new Properties();
        InputStream input = null;
        FurnitureItem mItem = new FurnitureItem();

        try {
            input = new FileInputStream(propFile);
            prop.load(input);
            mItem.setName(prop.getProperty("name"));
            mItem.setPrice(prop.getProperty("price"));
            mItem.setImage(imageFile.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mItem;
    }

}
