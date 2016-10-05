package com.delaroystudios.filechooser;

/**
 * Created by delaroystudios on 10/4/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileListAdapter extends BaseAdapter {

    private final static int ICON_FOLDER = R.drawable.ic_folder;
    private final static int ICON_FILE = R.drawable.ic_file;

    private final LayoutInflater mInflater;

    private List<File> mData = new ArrayList<File>();

    public FileListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void add(File file) {
        mData.add(file);
        notifyDataSetChanged();
    }

    public void remove(File file) {
        mData.remove(file);
        notifyDataSetChanged();
    }

    public void insert(File file, int index) {
        mData.add(index, file);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public File getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public List<File> getListItems() {
        return mData;
    }

    /**
     * Set the list items without notifying on the clear. This prevents loss of
     * scroll position.
     *
     * @param data
     */
    public void setListItems(List<File> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null)
            row = mInflater.inflate(R.layout.file, parent, false);

        TextView view = (TextView) row;

        // Get the file at the current position
        final File file = getItem(position);

        // Set the TextView as the file name
        view.setText(file.getName());

        // If the item is not a directory, use the file icon
        int icon = file.isDirectory() ? ICON_FOLDER : ICON_FILE;
        view.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);

        return row;
    }

}
