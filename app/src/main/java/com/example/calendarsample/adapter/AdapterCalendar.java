package com.example.calendarsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendarsample.CalendarObject;
import com.example.calendarsample.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCalendar extends RecyclerView.Adapter<AdapterCalendar.CalendarHolder> {
    private List<CalendarObject> list;
    private LayoutInflater inflater;
    private Context context;

    public AdapterCalendar(Context context){
        this.list = new ArrayList<>();
        this.context = context;

    }

    public void setList(List<CalendarObject> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_calendar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarHolder holder, int position) {
        CalendarObject calendarObject = list.get(position);
        holder.txtTitle.setText(calendarObject.getTitle());
        holder.txtDay.setText(calendarObject.getDiStart());
        holder.view.setBackgroundColor(calendarObject.getColorDisplay());
        holder.viewCalendar.setBackgroundColor(calendarObject.getCalendarColor());
    }

    @Override
    public int getItemCount() {
        return list
                .size();
    }

    public class CalendarHolder extends RecyclerView.ViewHolder {
        private TextView txtDay;
        private TextView txtTitle;
        private View view;
        private View viewCalendar;
        public CalendarHolder(@NonNull View itemView) {
            super(itemView);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            view = itemView.findViewById(R.id.viewColor);
            viewCalendar = itemView.findViewById(R.id.viewCalendar);
        }
    }
}
