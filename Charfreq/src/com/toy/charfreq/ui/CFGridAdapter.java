package com.toy.charfreq.ui;

import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CFGridAdapter extends BaseAdapter {
	
	private Character[] keySet;
	private HashMap<Character, Integer> data;
	private Context context;
	
	public CFGridAdapter(Context context, HashMap<Character, Integer> data) {
		
		// Gotta get an array of keys so we can iterate this in an order
		keySet = (Character[])data.keySet().toArray();
		this.data = data;
	}

	@Override
	public int getCount() {
		
		// Data set size
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		
		// Get the frequency count
		return data.get(keySet[index]); 
	}

	@Override
	public long getItemId(int index) {
		
		// Get the character
		return keySet[index]; 
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		TextView frequencyCountLabel;
		
		// Recycle the view if we can
        if (convertView == null) {  
        	frequencyCountLabel = new TextView(context);
        } else {
        	frequencyCountLabel = (TextView) convertView;
        }

        frequencyCountLabel.setText("" + getItemId(index) + ": " + getItem(index));
        return frequencyCountLabel;
	}
	
}
