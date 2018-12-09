import java.util.ArrayList;
import java.util.Collection;
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
	
	public List<Integer> getNonEmptyRows()//ckpt 2
	{
		Object[] temp=rowmap.keySet().toArray();
		ArrayList temp2=new ArrayList();
		for(int i=0; i<temp.length; i++)
		{
			temp2.add(temp[i]);
		}
		
		return temp2;
	}
	
	public List<Integer> getNonEmptyRowsInColumn(int col)//ckpt 2
	{
		if(colmap.containsKey(col)==false)
		{
			return new ArrayList();
		}
		Object[] t=colmap.get(col).keySet().toArray();
		
		ArrayList temp=new ArrayList();
		for(int i=0; i<t.length; i++)
		{
			if(colmap.get(col).get(t[i])!=0)
			{
				temp.add(t[i]);
			}
		}
		
		return temp;
	}
	
	public List<Integer> getNonEmptyCols()//ckpt 2
	{
		Object[] temp=colmap.keySet().toArray();
		ArrayList temp2=new ArrayList();
		for(int i=0; i<temp.length; i++)
		{
			temp2.add(temp[i]);
		}
		
		return temp2;
	}
	
	public List<Integer> getNonEmptyColsInRow(int row)//ckpt 2
	{
		if(rowmap.containsKey(row)==false)
		{
			return new ArrayList();
		}
		Object[] t=rowmap.get(row).keySet().toArray();
		
		ArrayList temp=new ArrayList();
		for(int i=0; i<t.length; i++)
		{
			if(rowmap.get(row).get(t[i])!=0)
			{
				temp.add(t[i]);
			}
		}
		
		return temp;
	}
	
	public int getRowSum(int row)//ckpt 2
	{
		if(rowmap.containsKey(row)==false)
		{
			return 0;
		}
		List<Integer> temp=getNonEmptyColsInRow(row);
		int result=0;
		for(int i=0; i<temp.size(); i++)
		{
			result+=rowmap.get(row).get(temp.get(i));
		}
		return result; 
	}
	
	public int getColSum(int col)//ckpt 2
	{
		if(colmap.containsKey(col)==false)
		{
			return 0;
		}
		List<Integer> temp=getNonEmptyColsInRow(col);
		int result=0;
		for(int i=0; i<temp.size(); i++)
		{
			result+=colmap.get(col).get(temp.get(i));
		}
		return result; 
	}
	
	public int getTotalSum()
	{
		Object[] temp= rowmap.keySet().toArray();
		int result=0;
		for(int i=0; i<temp.length; i++ )
		{
			result+=getRowSum((int) temp[i]);
		}
		return result;
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
		test.setValue(1, 0, 3);
		test.setValue(10, 1000, 3);
		
		test.setValue(0, 1000, 4);
		test.setValue(1000, 0, 5);
		test.setValue(0, 10, 6);
		test.setValue(10, 0, 7);
		test.setValue(10, 1000, 0);
		test.setValue(10, 0, 0);

		List<Integer> te=test.getNonEmptyRowsInColumn(0);
		System.out.println(te);
		/*System.out.println(test.getValue(1000, 10));
		System.out.println(test.getValue(10,1000));
		System.out.println(test.getValue(0,1000));
		System.out.println(test.getValue(1000,0));*/
		//System.out.println(test.getValue(0,10));
		//System.out.println(test.getValue(10,0));



		
	}
}