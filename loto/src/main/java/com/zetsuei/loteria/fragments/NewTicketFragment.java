package com.zetsuei.loteria.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.zetsuei.loteria.R;
import com.zetsuei.loteria.model.Ticket;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.util.Calendar;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class NewTicketFragment extends Fragment
        implements DatePickerDialog.OnDateSetListener {
    private DateTime dateTime;
    @InjectView(R.id.new_ticket_change_date) Button changeDateButton;
    @InjectView(R.id.editNumbers) EditText editNumbers;
    @InjectView(R.id.save_ticket) Button saveButton;

    public NewTicketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_ticket, container, false);

        ButterKnife.inject(this, view);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String numbers = editNumbers.getText().toString();
                Log.w("Loteria", dateTime.toString());
                Ticket ticket = new Ticket(numbers, dateTime);
                ticket.save();
            }
        });

        changeDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerDialogFragment(
                        NewTicketFragment.this);
                dialogFragment.show(getFragmentManager(), "date_picker_dialog");
            }
        });

        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateTime = new DateTime(year, monthOfYear + 1, dayOfMonth, 0, 0);
        changeDateButton.setText(dateTime.toString());
    }

    private class DatePickerDialogFragment extends DialogFragment {
        NewTicketFragment fragment;

        public DatePickerDialogFragment(NewTicketFragment callback) {
            fragment = callback;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstance) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(fragment.getActivity(), fragment,
                    year, month, day);
        }
    }
}
