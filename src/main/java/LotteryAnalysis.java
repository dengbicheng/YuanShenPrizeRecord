import java.util.List;
import java.util.Map;

/**
 * 分析概率
 */
public class LotteryAnalysis {
    public static void oddsAnalysis(Map<String, List<ItemRecord>> map) {
        String[] list = {"301", "302", "200", "100"};
        for (String item : list) {
            try{
                if (map.get(item).size() == 0) break;
            }catch (NullPointerException e){
                System.out.println(item+"未启动");
                continue;
            }
            double rankTypeThree = 0;
            double rankTypeFour = 0;
            double rankTypeFive = 0;
            List<ItemRecord> arr = map.get(item);
            for (int i = 0; i <arr.size(); i++) {
                String ranktype = arr.get(i).getRankType();
                switch (ranktype) {
                    case "3" -> rankTypeThree++;
                    case "4" -> rankTypeFour++;
                    case "5" -> rankTypeFive++;
                }
            }
            double sum = rankTypeFive + rankTypeFour + rankTypeThree;
            double five = rankTypeFive / sum;
            double four = rankTypeFour / sum;
            double three = rankTypeThree / sum;
            String five1 = String.format("%.2f%%", five * 100);
            String four1 = String.format("%.2f%%", four * 100);
            String three1 = String.format("%.2f%%", three * 100);
            String kcType = null;
            switch (item){
                case "301" -> kcType="up池";
                case "302" -> kcType="武器池";
                case "200" -> kcType="常驻池";
                case "100" -> kcType="新手池";
            }
            System.out.println("============"+kcType+"=============");
            System.out.println("五星概率为：" + five1);
            System.out.println("四星概率为：" + four1);
            System.out.println("三星概率为：" + three1);
        }

    }
}
