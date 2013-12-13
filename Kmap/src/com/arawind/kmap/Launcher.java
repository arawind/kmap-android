package com.arawind.kmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

public class Launcher extends Activity {
	
	private int numVar, numCol, currentColor;
	private boolean TOGGLE5;
	//private List<colorItem> selectedList;
	private Map<String, Integer> selectedList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		numVar = 4;
		setContentView(R.layout.activity_launcher);
		
		String sessionPref = "com.arawind.kmap.session";
		
		selectedList = new HashMap<String, Integer>();
		if(getLastNonConfigurationInstance() != null)
			selectedList = (Map<String,Integer>) getLastNonConfigurationInstance();
		
		
		SharedPreferences prefs = this.getSharedPreferences(sessionPref, Context.MODE_PRIVATE);
		numCol = prefs.getInt(sessionPref+".numCol", 0);
		
		String varNamesStr = prefs.getString(sessionPref+".varNamesStr", "");
		
		String[]  rowHead = prefs.getString(sessionPref+".rowHead","").split(",");
		String[]  colHead = prefs.getString(sessionPref+".colHead","").split(",");
		String[] strArray = prefs.getString(sessionPref+".strArray","").split(",");
		
		if(numCol==0){
			SharedPreferences.Editor prefEditor = prefs.edit();
			
			KMapTableBuilder kmap = new KMapTableBuilder(numVar);
						
			numCol = kmap.getNumCol();
			
			varNamesStr = kmap.getVarNames();
			
			rowHead  = kmap.getRowHead();
			colHead  = kmap.getColHead();
			strArray = kmap.getTableString();
			
			prefEditor.putInt(sessionPref+".numCol", numCol);
			
			prefEditor.putString(sessionPref+".varNamesStr", varNamesStr);
			prefEditor.putString(sessionPref+".rowHead", join(rowHead));
			prefEditor.putString(sessionPref+".colHead", join(rowHead));
			prefEditor.putString(sessionPref+".strArray", join(strArray));
			
			prefEditor.commit();
			
			Toast.makeText(this, "createdPrefs", Toast.LENGTH_SHORT).show();
		}
		
				
		GridView     gtop = (GridView) findViewById(R.id.gridTop);
		GridView    gleft = (GridView) findViewById(R.id.gridLeft);		
		GridView varNames = (GridView) findViewById(R.id.varNames);

		KmapGridView  gv = (KmapGridView) findViewById(R.id.gridTable);
		KmapGridView gv5 = (KmapGridView) findViewById(R.id.gridTable5);
		
		Spinner colorWheel = (Spinner) findViewById(R.id.colorWheel);
		
		Button extender5 = (Button) findViewById(R.id.extender5);
		
		TextView varNamesActual = (TextView) findViewById(R.id.varNamesActual);
		
		LinearLayout.LayoutParams      weight1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
		LinearLayout.LayoutParams weightNumCol = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, (float)numCol);
			
		List<String>    list = new ArrayList<String>(Arrays.asList(strArray));
		List<String> rowList = new ArrayList<String>(Arrays.asList(rowHead));
		List<String> colList = new ArrayList<String>(Arrays.asList(colHead));
		List<String> varList = new ArrayList<String>(Arrays.asList(""));
		
		List<String> colorList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Colors)));
		
		
		
		list.remove("");
		
		List<String> list5 = new ArrayList<String>();
		
		if(numVar == 5){
			list5 = list.subList(16, 32);
			list  = list.subList(0, 16);			
		}
		
		ArrayAdapter<String>    adapter = new GVArrayAdapter(this, R.layout.txt, list, selectedList);
		ArrayAdapter<String> rowAdapter = new ArrayAdapter<String>(this, R.layout.txt, rowList);
		ArrayAdapter<String> colAdapter = new ArrayAdapter<String>(this, R.layout.txt, colList);
		ArrayAdapter<String> varAdapter = new ArrayAdapter<String>(this, R.layout.txt, varList);
		ArrayAdapter<String>   adapter5 = new GVArrayAdapter(this, R.layout.txt, list5, selectedList);
		ArrayAdapter<String>    colorAd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colorList);
		
		colorAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		gv.setAdapter(adapter);
		gv5.setAdapter(adapter5);
		gtop.setAdapter(colAdapter);
		gleft.setAdapter(rowAdapter);
		colorWheel.setAdapter(colorAd);
		varNames.setAdapter(varAdapter);
		
		varNamesActual.setText("Variables: "+varNamesStr);
		
		if(numVar == 5){
			extender5.setVisibility(View.VISIBLE);
			extender5.setText("A = 0");
			TOGGLE5 = false;
			extender5.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					extenderToggler();
				}
			});
		}
		else{
			extender5.setVisibility(View.GONE);
		}
		
		gv.setLayoutParams(weight1);
		gv5.setLayoutParams(weight1);
		gtop.setLayoutParams(weight1);
		gleft.setLayoutParams(weightNumCol);
		varNames.setLayoutParams(weightNumCol);
		
		gleft.setNumColumns(1);
		gv.setNumColumns(numCol);
		gv5.setNumColumns(numCol);
		varNames.setNumColumns(1);
		gtop.setNumColumns(numCol);
		
		currentColor = getResources().getIdentifier(colorWheel.getSelectedItem().toString(), "color", "com.arawind.kmap");
		
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				gridOnItemClick(v, position);
			}
			
		});
		
		gv5.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				gridOnItemClick(v, 16+position);
			}
			
		});
		
		colorWheel.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				currentColor = getResources().getIdentifier(parent.getItemAtPosition(position).toString(), "color", "com.arawind.kmap");
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
	
	private void gridOnItemClick(View v, int position){
		
		switch(((AutoResizeTextView)v).addColor(currentColor, position)){
		case 1:
			if(!selectedList.containsKey(String.valueOf(position)+"-"+String.valueOf(currentColor)))
				selectedList.put(String.valueOf(position)+"-"+String.valueOf(currentColor), currentColor);
			break;
		case -1:
			selectedList.remove(String.valueOf(position)+"-"+String.valueOf(currentColor));
			break;
		default:
			break;
		//selectedList.put(String.valueOf(position)+"-"+String.valueOf(currentColor), currentColor);
		
		}
	}

	private void extenderToggler(){
		GridView  gv = (GridView) findViewById(R.id.gridTable);
		GridView gv5 = (GridView) findViewById(R.id.gridTable5);
		
		Button extender5 = (Button) findViewById(R.id.extender5);
		
		if(!TOGGLE5){
			gv.setVisibility(View.GONE);
			gv5.setVisibility(View.VISIBLE);
			extender5.setText("A = 1");
		}
		else{
			gv.setVisibility(View.VISIBLE);
			gv5.setVisibility(View.GONE);
			extender5.setText("A = 0");
		}
		TOGGLE5 = !TOGGLE5;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}
	
	private String join(String[] arg){
		StringBuilder sb = new StringBuilder();
		for(String string: arg){
			if(sb.length() > 0)
				sb.append(",");
			sb.append(string);
		}
		return sb.toString();
	}
	
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		Toast.makeText(this, "Saving", Toast.LENGTH_SHORT).show();
	}
	
	public Object onRetainNonConfigurationInstance(){
		final Map<String, Integer> sList = selectedList;
		return sList;
	}
	
	protected void onResume(){
		super.onResume();
	}
}
