package space.theninjaguys.www.lifa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import space.theninjaguys.www.lifa.Helper.Keys;
import space.theninjaguys.www.lifa.Models.BookedHistoryItem;
import space.theninjaguys.www.lifa.R;

/**
 * Created by rj on 8/7/15.
 */
public class BookingHistoryAdapter extends BaseAdapter {

    private Context mContext;
    public List<BookedHistoryItem> mBookingList;
    private BarberListItemViewHOlder mViewHolder;

    public BookingHistoryAdapter(Context context, List<BookedHistoryItem> mList) {

        mContext = context;
        mBookingList = mList;

    }

    @Override
    public int getCount() {
        return mBookingList.size();
    }

    @Override
    public BookedHistoryItem getItem(int position) {
        return mBookingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.booking_history_row_item, null);
            mViewHolder = new BarberListItemViewHOlder();

            mViewHolder.shopName = (TextView) convertView.findViewById(R.id.shopName);
            mViewHolder.shopAddress = (TextView) convertView.findViewById(R.id.shopAddress);
            mViewHolder.barberName = (TextView) convertView.findViewById(R.id.barberName);
            mViewHolder.availability = (TextView) convertView.findViewById(R.id.availability);
            mViewHolder.bookingDate = (TextView) convertView.findViewById(R.id.bookingDate);
            mViewHolder.bookingTime = (TextView) convertView.findViewById(R.id.bookingTime);

            mViewHolder.cancelBooking = (TextView) convertView.findViewById(R.id.bookNow);

            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (BarberListItemViewHOlder) convertView.getTag();

        }

        mViewHolder.shopName.setText(getItem(position).getShopName());
        mViewHolder.shopAddress.setText(getItem(position).getShopAddress());
        mViewHolder.barberName.setText(getItem(position).getBarberName());
        mViewHolder.uniquePin.setText(getItem(position).getUniquePin());
        mViewHolder.bookingDate.setText(getItem(position).getDate());
        mViewHolder.bookingTime.setText(getItem(position).getTime());

        if (getItem(position).isAvailability()) {

            //True case
            mViewHolder.availability.setTextColor(mContext.getResources().getColor(R.color.green));
            mViewHolder.availability.setText(mContext.getResources().getString(R.string.barber_available));
        } else {

            mViewHolder.availability.setTextColor(mContext.getResources().getColor(R.color.red));
            mViewHolder.availability.setText(mContext.getResources().getString(R.string.barber_unavailable));

        }

        if (getItem(position).getBookingStatus().equals(Keys.BOOKING_STATUS_PENDING)) {

            mViewHolder.cancelBooking.setText("Pending");
            mViewHolder.cancelBooking.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_orange_dark));

            mViewHolder.cancelBooking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //TODO API Call for cancelling the booking and refresh the adapter and data.

                }
            });

        } else if (getItem(position).getBookingStatus().equals(Keys.BOOKING_STATUS_COMPLETED)) {

            mViewHolder.cancelBooking.setText("Completed");
            mViewHolder.cancelBooking.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_green_dark));


        } else if (getItem(position).getBookingStatus().equals(Keys.BOOKING_STATUS_CANCELLED)) {

            mViewHolder.cancelBooking.setText("Cancelled");
            mViewHolder.cancelBooking.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_red_dark));

        }


        return convertView;
    }

    private final class BarberListItemViewHOlder {

        TextView shopName;
        TextView barberName;
        TextView shopAddress;
        TextView availability;
        TextView uniquePin;
        TextView bookingDate;
        TextView bookingTime;
        TextView cancelBooking;

    }
}
