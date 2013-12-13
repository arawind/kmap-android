package com.arawind.kmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GVArrayAdapter extends ArrayAdapter<String> {
	
	private int resid;
	private Map<String, Integer> slist;
	private List<String> objects;

	public GVArrayAdapter(Context context, int resid, List<String> objects, Map<String, Integer> map) {
		super(context, resid, objects);
		this.resid = resid;
		this.slist = map;
		this.objects = objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(resid, null);
		}
		
		((TextView)v).setText(objects.get(position));
		
		if(slist != null){
			int currentColor;
			ArrayList<String> removeList = new ArrayList<String>();
			for(Map.Entry<String, Integer> entry : slist.entrySet()){
				int pos = Integer.parseInt(entry.getKey().split("-")[0]);
				if(pos == position && removeList.indexOf(entry.getKey()) == -1){
					currentColor = entry.getValue();
					((AutoResizeTextView)v).addColor(currentColor, position);
					removeList.add(entry.getKey());
				}
			}
		}
		return v;
	}
}
