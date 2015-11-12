package in.woodu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.io.FilenameFilter;

import in.woodu.ItemsListActivity;
import in.woodu.R;
import in.woodu.model.FurnitureItem;
import in.woodu.utils.Utils;
import in.woodu.widget.TextView;

/**
 * Created by KT on 10/18/15.
 */
public class SubCategoryGridAdapter extends BaseAdapter {

    private String[] subCategories;
    private String category;
    private Context mContext;

    public SubCategoryGridAdapter(String category, String[] subCategories, Context mContext) {
        this.category = category;
        this.subCategories = subCategories;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return subCategories.length;
    }

    @Override
    public Object getItem(int i) {
        return subCategories[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subcategory_grid_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final String subCategory = subCategories[i];
        holder.title.setText(subCategory);
        final String contextPath = category + File.separator + subCategory;
        File dir = new File(Utils.ROOT_DIR + contextPath);
        File files[] = null;
        if (dir.exists()) {
            files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return new File(file, s).getName().endsWith(".properties");
                }
            });
            if (files != null) {
                FurnitureItem furnitureItem = Utils.readProperties(dir, files[0].getName());
                holder.image.setImageURI(Uri.parse(furnitureItem.getImage()));
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ItemsListActivity.class);
                intent.putExtra("contextPath", contextPath);
                intent.putExtra("category", subCategory);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    public static class ViewHolder {
        TextView title;
        ImageView image;

        public ViewHolder(View v) {
            title = (TextView) v.findViewById(R.id.subcategory_grid_item_tile);
            image = (ImageView) v.findViewById(R.id.subcategory_grid_item_image);
        }
    }
}
