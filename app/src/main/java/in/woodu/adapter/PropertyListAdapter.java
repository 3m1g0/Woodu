package in.woodu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.woodu.R;


/**
 * Created by KT on 7/29/15.
 */
public class PropertyListAdapter extends BaseAdapter {

    private String[] keys;

    private String values[];

    private Context mContext;

    public PropertyListAdapter(String[] keys, String[] values, Context mContext) {
        this.keys = keys;
        this.values = values;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return keys.length;
    }

    @Override
    public Object getItem(int i) {
        return keys[0];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.properties_list_item, null);
        ViewHolder holder = new ViewHolder(view);
        holder.name.setText(keys[i]);
        holder.value.setText(values[i]);
        return view;
    }

    public static class ViewHolder {
        TextView name;
        TextView value;

        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.property_name);
            value = (TextView) v.findViewById(R.id.property_value);
        }
    }
}
