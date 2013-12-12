package com.arawind.kmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GVArrayAdapter extends ArrayAdapter<String> {
	
	private int resid;
	private Context context;
	private Map<String, Integer> slist;
	private List<String> objects;

	public GVArrayAdapter(Context context, int resid, List<String> objects, Map<String, Integer> map) {
		super(context, resid, objects);
		this.context = context;
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
					if(v.getTag(R.string.CHECKED) != "true"){
						if(v.getTag(R.string.POSITION) == null)
							v.setTag(R.string.POSITION, position);
						ArrayList<Integer> colors = new ArrayList<Integer>();
						colors.add(currentColor);
						v.setTag(R.string.COLORS, colors);
						v.setTag(R.string.CHECKED, "true");
						((AutoResizeTextView)v).setBG(colors.size());
					}
					else{
						if(v.getTag(R.string.COLORS) != null){
							@SuppressWarnings("unchecked")
							ArrayList<Integer> colors = (ArrayList<Integer>) v.getTag(R.string.COLORS);
							if(colors.size() > 0){
								if(colors.indexOf(currentColor)!=-1)
									colors.remove(colors.indexOf(currentColor));
								else
									colors.add(currentColor);
								v.setTag(R.string.COLORS, colors);
								if(colors.size() > 0)
									((AutoResizeTextView)v).setBG(colors.size());
								else{
									v.setBackgroundDrawable(null);	
									v.setTag(R.string.CHECKED, "false");
								}
							}
						}
						else
							v.setTag(R.string.CHECKED, "false");
					}
					removeList.add(entry.getKey());
				}
			}
		}		
		return v;
	}
}
