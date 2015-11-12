package in.woodu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import in.woodu.DetailsActivity;
import in.woodu.R;
import in.woodu.model.FurnitureItem;
import in.woodu.widget.TextView;


/**
 * Created by KT on 7/29/15.
 */
public class ItemListAdapter extends BaseAdapter {

    private FurnitureItem[] mDataset;

    private Context mContext;

    public ItemListAdapter(FurnitureItem[] mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDataset.length;
    }

    @Override
    public Object getItem(int i) {
        return mDataset[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_list_row, null);
        ViewHolder holder = new ViewHolder(view);
        final FurnitureItem furnitureItem = mDataset[i];
        holder.name.setText(furnitureItem.getName().toUpperCase());
        holder.price.setText("Rs. " + furnitureItem.getPrice());
        holder.image.setImageURI(Uri.parse(furnitureItem.getImage()));
        holder.shopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("title", furnitureItem.getName());
                intent.putExtra("uri", furnitureItem.getImage());
                mContext.startActivity(intent);
            }
        });
        holder.shareAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(Intent.createChooser(createShareIntent(Uri.parse(furnitureItem.getImage())), ""));
            }
        });
        return view;
    }

    private Intent createShareIntent(Uri uri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("image/*");

        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        return shareIntent;
    }

    public static class ViewHolder {
        TextView name;
        TextView price;
        ImageView image;
        ImageView shareAction;
        TextView shopNow;

        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.card_item_name);
            price = (TextView) v.findViewById(R.id.card_item_price);
            image = (ImageView) v.findViewById(R.id.card_item_image);
            shopNow = (TextView) v.findViewById(R.id.card_item_shop_now);
            shareAction = (ImageView) v.findViewById(R.id.card_item_share);
        }
    }
}
