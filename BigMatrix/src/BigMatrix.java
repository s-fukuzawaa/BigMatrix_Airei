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
		if(value==0)
		{
			if(rowmap.containsKey(row)==false)
			{
				return;
			}
			if(colmap.containsKey(col)==false)
			{
				return;
			}
			

			
				rowmap.get(row).remove(col);

				colmap.get(col).remove(row);
				if(rowmap.get(row).isEmpty())
				{
					rowmap.remove(row);
				}
				if(colmap.get(col).isEmpty())
				{
					colmap.remove(col);
				}
				return;
			
		}
		
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
	
	public int getValue(Object temp1, Object inside)
	{
		if(this.rowmap.isEmpty()||this.colmap.isEmpty()||rowmap.containsKey(temp1)==false||colmap.containsKey(inside)==false||rowmap.get(temp1).containsKey(inside)==false||colmap.get(inside).containsKey(temp1)==false)
		{
			return 0;
		}
		return rowmap.get(temp1).get(inside);
		
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
			temp.add(t[i]);
			
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
			temp.add(t[i]);
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
		List<Integer> temp=getNonEmptyRowsInColumn(col);
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
		
		BigMatrix result= new BigMatrix();
		Object[] a=rowmap.keySet().toArray();
		Object[] b=colmap.keySet().toArray();
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<b.length; j++)
			{
				result.setValue((Integer)a[i], (Integer)b[j], getValue(a[i], b[j])*constant);
			}
		}
		
		
		return result;
		

	}
	
	public BigMatrix addMatrix(BigMatrix other)
	{
		BigMatrix result= new BigMatrix();
		result.rowmap.putAll(rowmap);
		result.colmap.putAll(colmap);
		Object[] row=other.rowmap.keySet().toArray();
		Object[] col=other.colmap.keySet().toArray();
		for(int i=0; i<row.length; i++)
		{
			for(int j=0; j<col.length; j++)
			{
				result.setValue((Integer)row[i], (Integer)col[j], result.getValue(row[i], col[j])+other.getValue(row[i], col[j]));
			}
		}
		
		
		return result;
		
	}
	public static void main(String[] args)
	{
		BigMatrix test= new BigMatrix();
		BigMatrix test1= new BigMatrix();
		BigMatrix test2= new BigMatrix();
		test.setValue(0, 0, 1);
		test.setValue(1000, 10, 2);
		test.setValue(10, 1000, 3);
		test.setValue(0, 1000, 4);
		test.setValue(1000, 0, 5);
		test.setValue(0, 10, 6);
		test.setValue(10, 0, 7);
		test.setValue(1000, 10, 0);
		test.setValue(0, 10, 0);
		
		test1.setValue(0, 0, 1);
		test1.setValue(1000, 10, 2);
		test1.setValue(10, 1000, 3);
		test1.setValue(0, 1000, 4);
		test1.setValue(1000, 0, 5);
		test1.setValue(0, 10, 6);
		test1.setValue(10, 0, 7);
		
		test2.setValue(0, 0, 1);
		test2.setValue(10000, 100, 2);
		test2.setValue(100, 10000, 3);
		test2.setValue(0, 10000, 4);
		test2.setValue(10000, 0, 5);
		test2.setValue(0, 100, 6);
		test2.setValue(100, 0, 7);
		

		BigMatrix sum=test.addMatrix(test1);
		BigMatrix result=sum.multiplyByConstant(3);
		/*System.out.println(test.getValue(1000, 10));
		System.out.println(test.getValue(10,1000));
		System.out.println(test.getValue(0,1000));
		System.out.println(test.getValue(1000,0));*/
		//System.out.println(test.getValue(0,10));
		//System.out.println(test.getValue(10,0));



		
	}
}