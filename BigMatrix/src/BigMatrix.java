import java.util.HashMap;
import java.util.List;

public class BigMatrix 
{
	private HashMap<Integer, HashMap<Integer,Integer>> row;//HashMap<row,HashMap<col,value>>
	private HashMap<Integer, HashMap<Integer,Integer>> col;//HashMap<col,HashMap<row,value>>
	public BigMatrix()
	{
		row= new HashMap<Integer, HashMap<Integer,Integer>>();
		col= new HashMap<Integer, HashMap<Integer,Integer>>();
	}
	
	public void setValue(int row, int col, int value)
	{
		if(value==0)
		{
			return;
		}
		HashMap<Integer,Integer> trow= new HashMap<Integer,Integer>();
		trow.put(col, value);
		this.row.put(row, trow);
		HashMap<Integer,Integer> tcol= new HashMap<Integer,Integer>();
		tcol.put(row, value);
		this.row.put(col, tcol);
	
	}
	
	public int getValue(int row, int col)
	{
		if(this.row.get(row).get(col)==null)
		{
			return 0;
		}
		return this.row.get(row).get(col);		
	}
	
	public List<Integer> getNonEmptyRows()
	{
		throw new UnsupportedOperationException();
	}
	
	public List<Integer> getNonEmptyRowsInColumn(int col)
	{
		throw new UnsupportedOperationException();
	}
	
	public List<Integer> getNonEmptyCols()
	{
		throw new UnsupportedOperationException();
	}
	
	public List<Integer> getNonEmptyColsInRow(int row)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getRowSum(int row)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getColSum(int col)
	{
		throw new UnsupportedOperationException();
	}
	
	public int getTotalSum()
	{
		throw new UnsupportedOperationException();
	}
	
	public BigMatrix multiplyByConstant(int constant)
	{
		throw new UnsupportedOperationException();
	}
	
	public BigMatrix addMatrix(BigMatrix other)
	{
		throw new UnsupportedOperationException();
	}
	public static void main(String[] args)
	{
		BigMatrix test= new BigMatrix();
		test.setValue(0, 0, 1);
		test.setValue(1000, 10, 2);
		System.out.println(test.getValue(0,0));
		System.out.println(test.getValue(1000, 10));
		//System.out.println(test.getValue(2, 5));

		
	}
}
