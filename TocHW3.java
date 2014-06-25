import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TocHW3 {

  public static  JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      JSONArray jsonRealPrice = new JSONArray(new JSONTokener(rd));
      
    //  System.out.println("HELLOW");
      return jsonRealPrice;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
	  JSONArray json = readJsonFromUrl(args[0]);

    String comy,objectyear;//compare year // and the data year 
    int number=0,sum=0,average=0;
    
    	for(int i = 0;i<json.length();i++)//for every json object
    	{
    		for(int k = Integer.parseInt(args[3]);k<=103;k++)
    		{
    			comy=""+k;
    			objectyear=""+json.getJSONObject(i).getInt("交易年月") ;
    		
    			if(objectyear.startsWith(comy) && 
    				json.getJSONObject(i).getString("鄉鎮市區").equals(args[1])
    				&&json.getJSONObject(i).getString("土地區段位置或建物區門牌").contains(args[2] ))
    				
    			{
    				number++;
    				sum += json.getJSONObject(i).getInt("總價元");
    			//	System.out.println(json.getJSONObject(i).getString("鄉鎮市區") + "    " + json.getJSONObject(i).getString("土地區段位置或建物區門牌") + "    " + 
					//		json.getJSONObject(i).getInt("交易年月") + "    " +json.getJSONObject(i).getInt("總價元"));
    			}//judge the data is satisfied and output it year by year 
    			
    		}
    	}
    	average = sum/number;
		System.out.println(average);
    //System.out.println("HELLOW");
    //System.out.println(json.get("id"));
  }
}