package in.woodu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import in.woodu.R;
import in.woodu.widget.TextView;


/**
 * Created by KT on 7/29/15.
 */
public class CategoryListAdapter extends BaseAdapter {

    private String[] mCategories;

    private Context mContext;

    public CategoryListAdapter(Context mContext) {
        this.mContext = mContext;
        this.mCategories = mContext.getResources().getStringArray(R.array.furniture_classes);
    }

    @Override
    public int getCount() {
        return mCategories.length;
    }

    @Override
    public Object getItem(int i) {
        return mCategories[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.category_list_item, null);
        ViewHolder holder = new ViewHolder(view);
        final String mCategory = mCategories[i];
        int categoryResId = mContext.getResources().getIdentifier(mCategory, "array", mContext.getPackageName());
        String[] subCategories = mContext.getResources().getStringArray(categoryResId);
        holder.grid.setAdapter(new SubCategoryGridAdapter(mCategory, subCategories, mContext));
        holder.title.setText(mCategory.toUpperCase());
        holder.grid.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View p_v, MotionEvent p_event) {
                p_v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        return view;
    }

    public static class ViewHolder {
        TextView title;
        ImageView image;
        GridView grid;

        public ViewHolder(View v) {
            title = (TextView) v.findViewById(R.id.category_list_item_title);
            image = (ImageView) v.findViewById(R.id.category_list_item_image);
            grid = (GridView) v.findViewById(R.id.category_list_item_grid);
        }
    }
}
