package com.arawind.kmap;

public class KMapTableBuilder {
	private int numCol, numVar;
	public String str;
	private String[] rowHead, colHead;
	private String varNames;
	public KMapTableBuilder(int numVar){
		this.numVar = numVar;
		
		buildStr();
		
		switch(numVar){
		case 2:
			rowHead = new String[] {"0", "1"};
			colHead = new String[] {"0", "1"};
			numCol = 2;
			varNames = "A\\B";
			break;
		case 3:
			rowHead = new String[] {"0", "1"};
			colHead = new String[] {"00", "01", "11", "10"};
			numCol = 4;
			varNames = "A\\BC";
			break;
		case 4:
			rowHead = new String[] {"00", "01", "11", "10"};
			colHead = new String[] {"00", "01", "11", "10"};
			numCol = 4;
			varNames = "AB\\CD";
			break;
		case 5:
			rowHead = new String[] {"00", "01", "11", "10"};
			colHead = new String[] {"00", "01", "11", "10"};
			numCol = 4;
			varNames = "BC\\DE";
			break;
		}
	}
	
	public String[] buildStr(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < Math.pow(2, numVar); i++){
			sb.append((Math.random() > 0.5) ? 1 : 0);
		}
		str = sb.toString();
		
		return str.split("");
	}
	
	public String[] getTableString(){
		return str.split("");
	}
	public String[] getRowHead(){
		return rowHead;
	}
	public String[] getColHead(){
		return colHead;
	}
	public int getNumCol(){
		return numCol;
	}
	public String getVarNames(){
		return varNames;
	}
}
