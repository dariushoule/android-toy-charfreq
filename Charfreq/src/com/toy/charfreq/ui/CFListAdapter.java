package com.toy.charfreq.ui;

import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CFListAdapter extends BaseAdapter {
	
	private Character[] mKeySet;
	private HashMap<Character, Integer> mData;
	private Context mContext;
	
	public CFListAdapter(Context context, HashMap<Character, Integer> data) {
		
		// Gotta get an array of keys so we can iterate this in an order
		mKeySet = data.keySet().toArray(new Character[data.keySet().size()]);
		mData = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		
		// Data set size
		return mData.size();
	}

	@Override
	public Object getItem(int index) {
		
		// Get the frequency count
		return mData.get(mKeySet[index]); 
	}

	@Override
	public long getItemId(int index) {
		
		// Get the character
		return mKeySet[index]; 
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		TextView frequencyCountLabel;
		
		// Recycle the view if we can
        if (convertView == null) {  
        	frequencyCountLabel = new TextView(mContext);
        } else {
        	frequencyCountLabel = (TextView) convertView;
        }

        frequencyCountLabel.setText((char)getItemId(index) + ": " + getItem(index));
        return frequencyCountLabel;
	}
	
}
