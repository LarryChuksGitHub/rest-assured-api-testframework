package tutorial.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class DataProviderDemo {


    @Test(dataProvider = "data")
    public void testDataProvider(Map<Object,Object> map){
        System.out.println(map.get("username"));
        System.out.println(map.get("password"));
        System.out.println(map.get("email"));
        System.out.println(map.get("address"));

    }

    @DataProvider(name = "data")
    public Object [] provider(){
         Object [] data = null; //= new Object[4][1];

        Map<Object,Object> map = new HashMap<>();
        map.put("username","Larry");
        map.put("password","12345");
        map.put("email","email.com");
        map.put("address","Berlin");

        Map<Object,Object> map2 = new HashMap<>();
        map2.put("username","Agnes");
        map2.put("password","12345");
        map2.put("email","email.com");
        map2.put("address","Berlin");


        Map<Object,Object> map3 = new HashMap<>();
        map3.put("username","Ebu");
        map3.put("password","12345");
        map3.put("email","email.com");
        map3.put("address","Berlin");


        Map<Object,Object> map4 = new HashMap<>();
        map4.put("username","Jere");
        map4.put("password","12345");
        map4.put("email","email.com");
        map4.put("address","Berlin");

        List<Map<Object,Object>> mapList = new ArrayList<>();
        mapList.add(map);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
//
//        data [0][0] = map;
//        data [1][0] = map2;
//        data [2][0] = map3;
//        data [3][0] = map4;

       // data = (Object[][]) Arrays.asList(map,map2,map3,map4).toArray();
       data = mapList.toArray();

        return data;
    }
}
