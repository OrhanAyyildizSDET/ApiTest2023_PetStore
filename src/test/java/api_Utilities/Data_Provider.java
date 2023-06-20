package api_Utilities;


import org.testng.annotations.DataProvider;

import java.io.IOException;

public class Data_Provider {

    String path = System.getProperty("user.dir")+"//test_Data/userData.xlsx";
    Excel_Utility xl = new Excel_Utility(path);

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {

        int rowNum = xl.getRowCount("userData");
        int columnNum = xl.getCellCount("userData",1);

        String[][] apiData = new String[rowNum][columnNum];

        for (var i = 1; i<=rowNum; i++){
            for (var j = 0; j<columnNum; j++){
                apiData[i-1][j] = xl.getCellData("userData", i, j);
            }
        }

        return apiData;
    }

    @DataProvider(name = "UserNames")
    public Object[] getUserNames() throws IOException {

        int rowNum = xl.getRowCount("userData");

        String[] apiData = new String[rowNum];

        for (var i = 1; i<=rowNum; i++){
            apiData[i-1] = xl.getCellData("userData", i, 1);
        }

        return apiData;
    }

}
