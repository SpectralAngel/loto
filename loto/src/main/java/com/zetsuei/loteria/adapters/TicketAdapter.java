package com.zetsuei.loteria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.zetsuei.loteria.R;
import com.zetsuei.loteria.model.Ticket;

/**
 * Created by Carlos on 3/30/2014.
 */
public class TicketAdapter extends ArrayAdapter<Ticket> {
    private final Context context;
    private final LayoutInflater inflater;

    static class ViewHolder {
        @InjectView(R.id.numbers) TextView numbers;
        @InjectView(R.id.date) TextView date;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public TicketAdapter(Context context) {
        super(context, R.layout.ticket_row);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.ticket_row, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Ticket ticket = getItem(position);
        holder.date.setText(ticket.dateTime.toString());
        holder.numbers.setText(ticket.toString());

        return view;
    }
}
