package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//dataProvider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testdata\\OpenCart_loginData.xlsx";  //taking xl file from testdata
		ExcelUtility xlutil = new ExcelUtility(path);  //creating an object for xlutility;
		int totalrow =xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][]= new String[totalrow][totalcols];  //created for two dimention array which can stored
		
		for(int i=1;i<=totalrow;i++)  //1   read the data from xl storing into two dimentional array
		{
			for(int j=0;j<totalcols;j++) //0   i is row j is col
			{
				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0
			}
		}
		
		return loginData;  //returning two dimensional array
		
	}

	//dataProvider2
}
