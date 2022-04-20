package com.example.gbt_4;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class SelectYear extends DialogFragment {

    private static final int Max_Year = 2022;
    private static final int Min_Year = 1940;
    private DatePickerDialog.OnDateSetListener listener;
    public Calendar calendar = Calendar.getInstance();

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    Button btn_confirm, btn_cancel;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog =  inflater.inflate(R.layout.year_select,null);

        btn_confirm = dialog.findViewById(R.id.btn_confirm);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);

        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectYear.this.getDialog().cancel();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDateSet(null,yearPicker.getValue(),0, 0);
                SelectYear.this.getDialog().cancel();
            }
        });

        int year = calendar.get(Calendar.YEAR);
        yearPicker.setMinValue(Min_Year);
        yearPicker.setMaxValue(Max_Year);
        yearPicker.setValue(year);

        builder.setView(dialog);
        return builder.create();
    }

}
