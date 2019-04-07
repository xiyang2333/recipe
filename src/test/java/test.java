import com.recipefinder.pojo.Item;
import com.recipefinder.service.CoreService;
import com.recipefinder.service.impl.CoreServiceImpl;

import java.util.List;

/**
 * Created by xiyang on 2019/4/7
 */
public class test {

    public static void  main(String[] args) throws Exception {
        //        boolean isMatch = Pattern.matches(pattern, "25/12/2014");
//        System.out.println(isMatch);
        csvTest();
    }

    private static void csvTest() throws Exception {
        String fridge = "bread,10,slices,25/12/2019\n" +
                "cheese,10,slices,25/12/2014\n" +
                "butter,250,grams,25/12/2014 peanut butter,250,grams,2/12/2019 mixed salad,150,grams,26/12/2018";

        CoreService service = new CoreServiceImpl();

        List<Item> items = service.csvDeal( fridge);
    }
}
