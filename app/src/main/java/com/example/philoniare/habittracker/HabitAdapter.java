package com.example.philoniare.habittracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.philoniare.habittracker.model.Habit;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitViewHolder> {
    private List<Habit> mHabitList;
    private Context mContext;

    public HabitAdapter(Context context, List<Habit> mHabitList) {
        this.mHabitList = mHabitList;
        this.mContext = context;
    }

    @Override
    public HabitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_item, null);
        HabitViewHolder rcv = new HabitViewHolder(layoutView, mContext);
        return rcv;
    }

    @Override
    public void onBindViewHolder(HabitViewHolder holder, int position) {
        // Bind text with list item
//         currentArticle = mNewsArticles.get(position);
//        String articleTitle = currentArticle.getTitle();
//        String articleSectionName = currentArticle.getSectionName();
//        String articleThumbnail = currentArticle.getThumbnail();
//        holder.articleTitle.setText(articleTitle);
//        holder.articleSectionName.setText(articleSectionName);
//        if (!articleThumbnail.equals("")) {
//            Picasso.with(mContext).load(articleThumbnail).into(holder.articleThumbnail);
//        } else {
//            holder.articleThumbnail.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return this.mHabitList.size();
    }
}
