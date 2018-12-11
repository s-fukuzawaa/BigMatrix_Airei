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
		result.rowmap=rowmap;
		result.colmap=colmap;
		
		
		
		Object[] temp1=rowmap.keySet().toArray();
		for(int i=0; i<temp1.length; i++)
		{
			Object[] inside=rowmap.get(temp1[i]).keySet().toArray();
			for(int j=0; j<inside.length; j++)
			{
				result.rowmap.get(temp1[i]).put((Integer) inside[j],result.getValue(temp1[i], inside[j])*constant);
			}
		}
		Object[] temp2=colmap.keySet().toArray();
		for(int i=0; i<temp2.length; i++)
		{
			Object[] inside=colmap.get(temp2[i]).keySet().toArray();
			for(int j=0; j<inside.length; j++)
			{
				result.colmap.get(temp2[i]).put((Integer) inside[j], result.getValue(temp2[i], inside[j])*constant);
			}
		}
		return result;

	}
	
	public BigMatrix addMatrix(BigMatrix other)
	{
		BigMatrix result= new BigMatrix();
		result.rowmap=rowmap;
		result.colmap=colmap;
		Object[] rowt=other.rowmap.keySet().toArray();
		Object[] colt=other.colmap.keySet().toArray();

		for(int i=0; i<rowt.length; i++)
		{
			for(int j=0; j<colt.length; j++)
			{
				result.setValue((Integer)rowt[i], (Integer)colt[j], other.getValue(rowt[i], colt[j])+result.getValue(rowt[i], colt[j]));
			}
		}
		
		return result;
		
	}
	public static void main(String[] args)
	{
		BigMatrix test= new BigMatrix();
		test.setValue(0, 0, 1);
		test.setValue(1000, 10, 2);
		test.setValue(10, 1000, 3);
		
		test.setValue(0, 1000, 4);
		test.setValue(1000, 0, 5);
		test.setValue(0, 10, 6);
		test.setValue(10, 0, 7);
		/*test.setValue(1000, 10, 0);
		test.setValue(0, 10, 0);*/

		test.addMatrix(test);
		/*System.out.println(test.getValue(1000, 10));
		System.out.println(test.getValue(10,1000));
		System.out.println(test.getValue(0,1000));
		System.out.println(test.getValue(1000,0));*/
		//System.out.println(test.getValue(0,10));
		//System.out.println(test.getValue(10,0));



		
	}
}
