import java.util.HashMap;
import java.util.List;

public class BigMatrix 
{
	private HashMap<Integer, HashMap<Integer,Integer>> rowmap;//HashMap<row,HashMap<col,value>>
	private HashMap<Integer, HashMap<Integer,Integer>> colmap;//HashMap<col,HashMap<row,value>>
	public BigMatrix()
	{
		rowmap= new HashMap<Integer, HashMap<Integer,Integer>>();
		colmap= new HashMap<Integer, HashMap<Integer,Integer>>();
	}
	
	public void setValue(int row, int col, int value)
	{
		
		if(rowmap.containsKey(row))
		{
			rowmap.get(row).put(col, value);
			if(colmap.containsKey(col))
			{
				colmap.get(col).put(row, value);
			}
			else
			{
				HashMap<Integer,Integer> tcol= new HashMap<Integer,Integer>();
				tcol.put(row, value);
				colmap.put(col, tcol);
			}
			
		}
		else if(colmap.containsKey(col))
		{
			colmap.get(col).put(row, value);
			
				HashMap<Integer,Integer> trow= new HashMap<Integer,Integer>();
				trow.put(col, value);
				rowmap.put(row, trow);
			
			
		}		
		else
		{
			HashMap<Integer,Integer> trow= new HashMap<Integer,Integer>();
			trow.put(col, value);
			rowmap.put(row, trow);
			HashMap<Integer,Integer> tcol= new HashMap<Integer,Integer>();
			tcol.put(row, value);
			colmap.put(col, tcol);

		}
		
		
		
	
	}
	
	public int getValue(int row, int col)
	{
		if(this.rowmap.isEmpty()||this.colmap.isEmpty()||rowmap.containsKey(row)==false||colmap.containsKey(col)==false||rowmap.get(row).containsKey(col)==false||colmap.get(col).containsKey(row)==false)
		{
			return 0;
		}
		return rowmap.get(row).get(col);
		
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
		test.setValue(1, 0, 0);
		/*test.setValue(10, 1000, 3);
		
		test.setValue(0, 1000, 4);
		test.setValue(1000, 0, 5);
		test.setValue(0, 10, 6);
		test.setValue(10, 0, 7);
		test.setValue(10, 1000, 0);
		test.setValue(10, 0, 0);*/

		System.out.println(test.getValue(0,0));
		/*System.out.println(test.getValue(1000, 10));
		System.out.println(test.getValue(10,1000));
		System.out.println(test.getValue(0,1000));
		System.out.println(test.getValue(1000,0));*/
		//System.out.println(test.getValue(0,10));
		//System.out.println(test.getValue(10,0));



		
	}
}
