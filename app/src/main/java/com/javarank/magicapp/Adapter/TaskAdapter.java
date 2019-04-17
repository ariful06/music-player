package com.javarank.magicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.javarank.magicapp.AlertDialog.AlertDialogActivity;
import com.javarank.magicapp.Calculator.CalculatorActivity;
import com.javarank.magicapp.Helper.ItemClickListener;
import com.javarank.magicapp.MediaPlayer.MediaPlayerActivity;
import com.javarank.magicapp.MusicPlayer.MusicPlayerActivity;
import com.javarank.magicapp.R;
import com.javarank.magicapp.ReportApp.ReportActivity;
import com.javarank.magicapp.SharedPreference.SharedPreferenceActivity;
import com.javarank.magicapp.Toast.ToastActivity;
import com.javarank.magicapp.WeatherApp.WeatherActivity;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    
    List<String> dataSource;
    Context context;

    public TaskAdapter(List<String> dataSource, Context context) {
        this.dataSource = dataSource;
        this.context = context;
    }
    public TaskAdapter() {
        dataSource = new ArrayList<>();
        dataSource.add("Toast");
        dataSource.add("Shared Preference");
        dataSource.add("Implicit intents");
        dataSource.add("Explicit Intent");
        dataSource.add("Alert Dialog");
        dataSource.add("View Pager with Tab Layout");

        dataSource.add("Calculator");
        dataSource.add("Report Card(Retrofit)");
        dataSource.add("Weather forecast");
        dataSource.add("Quiz App(Firebase)");
        dataSource.add("Media Player");
        dataSource.add("Fragment");
        dataSource.add("Expanded List View");
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new TaskViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.taskName.setText(dataSource.get(position));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClicked) {
                if (isLongClicked) {
                    Toast.makeText(view.getContext(), "" + dataSource.get(position), Toast.LENGTH_SHORT).show();
                } else {
                    if (dataSource.get(position).equals("Calculator")) {
                        Intent intent = new Intent(view.getContext(), CalculatorActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Report Card")) {
                        Intent intent = new Intent(view.getContext(), ReportActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Weather forecast")) {
                        Intent intent = new Intent(view.getContext(), WeatherActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Quiz App")) {
                        Intent intent = new Intent(view.getContext(), CalculatorActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Media Player")) {
                        Intent intent = new Intent(view.getContext(), MusicPlayerActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Shared Preference")) {
                        Intent intent = new Intent(view.getContext(), SharedPreferenceActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Toast")) {
                        Intent intent = new Intent(view.getContext(), ToastActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Implicit intents")) {
                        Intent intent = new Intent(view.getContext(), CalculatorActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (dataSource.get(position).equals("Explicit Intent")) {
                        Intent intent = new Intent(view.getContext(), CalculatorActivity.class);
                        view.getContext().startActivity(intent);
                    }else if (dataSource.get(position).equals("Alert Dialog")) {
                        Intent intent = new Intent(view.getContext(), AlertDialogActivity.class);
                        view.getContext().startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView taskName;
        private ItemClickListener itemClickListener;
        private TaskViewHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {

            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);

            return true;
        }
    }
}

