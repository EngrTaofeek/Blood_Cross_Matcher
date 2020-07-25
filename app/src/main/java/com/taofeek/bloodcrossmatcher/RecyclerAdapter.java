package com.taofeek.bloodcrossmatcher;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private final Context mContext;
    private Cursor mCursor;
    //private final LayoutInflater mLayoutInflater;
    private int mNamePos;
    private int mEmailPos;
    private int mBloodPos;
    private int mIdPos;

    public RecyclerAdapter (Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
        //mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();

    }
    private void populateColumnPositions() {
        if(mCursor == null)
            return;
        // Get column indexes from mCursor
        mNamePos = mCursor.getColumnIndex(DatabaseContract.Profile.COLUMN_NAME);
        mEmailPos = mCursor.getColumnIndex(DatabaseContract.Profile.COLUMN_EMAIL);
        mBloodPos = mCursor.getColumnIndex(DatabaseContract.Profile.COLUMN_BLOOD_TYPE);

        mIdPos = mCursor.getColumnIndex(DatabaseContract.Profile._ID);
    }
    public void changeCursor(Cursor cursor) {
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        populateColumnPositions();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_matcher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String name = mCursor.getString(mNamePos);
        String email = mCursor.getString(mEmailPos);
        String blood = mCursor.getString(mBloodPos);
        int id = mCursor.getInt(mIdPos);

        holder.name.setText(name);
        holder.email.setText(email);
        holder.blood_group.setText(blood);
        holder.mId = id;

    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView email;
        public TextView blood_group;
        public int mId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemtextname);
            email = itemView.findViewById(R.id.itemlistemail);
            blood_group = itemView.findViewById(R.id.itemlistbloodgroup);
        }
    }
}
