package space.theninjaguys.www.lifa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import space.theninjaguys.www.lifa.Models.BarberListItem;
import space.theninjaguys.www.lifa.R;

/**
 * Created by rj on 8/7/15.
 */
public class BarberListAdapter extends BaseAdapter {

    private Context mContext;
    public List<BarberListItem> mBarberList;
    private BarberListItemViewHOlder mViewHolder;

    public BarberListAdapter(Context context, List<BarberListItem> mList) {

        mContext = context;
        mBarberList = mList;

    }

    @Override
    public int getCount() {
        return mBarberList.size();
    }

    @Override
    public BarberListItem getItem(int position) {
        return mBarberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.barber_list_row_item, null);
            mViewHolder = new BarberListItemViewHOlder();

            mViewHolder.shopName = (TextView) convertView.findViewById(R.id.shopName);
            mViewHolder.shopAddress = (TextView) convertView.findViewById(R.id.shopAddress);
            mViewHolder.barberName = (TextView) convertView.findViewById(R.id.barberName);
            mViewHolder.availability = (TextView) convertView.findViewById(R.id.availability);
            mViewHolder.bookNow = (TextView) convertView.findViewById(R.id.bookNow);

            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (BarberListItemViewHOlder) convertView.getTag();

        }


        return null;
    }

    private class BarberListItemViewHOlder {

        TextView shopName;
        TextView barberName;
        TextView shopAddress;
        TextView availability;
        TextView bookNow;

    }
}
