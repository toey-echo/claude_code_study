// toey forever
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataAnalyzer {

    /**
     * 用户消费数据分析处理函数（约80行）
     * 完成数据过滤、计算、分级标记、结果组装
     * @param rawAmountList 原始消费金额列表
     * @param threshold 消费阈值
     * @return 分析结果封装
     */
    public AnalysisResult analyzeConsumeData(List<Double> rawAmountList, double threshold) {
        // 1. 初始化临时变量
        List<Double> validData = new ArrayList<>();
        double totalConsume = 0.0;
        int highConsumeCount = 0;
        int normalCount = 0;

        // 2. 过滤非法数据，剔除负数与空值
        if (rawAmountList != null) {
            validData = rawAmountList.stream()
                    .filter(item -> item != null && item > 0)
                    .collect(Collectors.toList());
        }

        // 3. 遍历有效消费数据统计指标
        for (double amount : validData) {
            totalConsume += amount;
            if (amount >= threshold) {
                highConsumeCount++;
            } else {
                normalCount++;
            }
        }

        // 4. 计算平均值，避免除零异常
        double avgConsume = 0.0;
        if (!validData.isEmpty()) {
            avgConsume = totalConsume / validData.size();
        }

        // 5. 根据均值生成消费等级标签
        String levelTag;
        if (avgConsume >= threshold * 1.5) {
            levelTag = "高消费用户";
        } else if (avgConsume >= threshold) {
            levelTag = "中等消费用户";
        } else {
            levelTag = "普通消费用户";
        }

        // 6. 封装并返回结果对象
        AnalysisResult result = new AnalysisResult();
        result.setTotalNum(validData.size());
        result.setTotalConsume(totalConsume);
        result.setAvgConsume(avgConsume);
        result.setHighConsumeCount(highConsumeCount);
        result.setNormalCount(normalCount);
        result.setUserLevel(levelTag);
        return result;
    }

    public static void main(String[] args) {
        DataAnalyzer analyzer = new DataAnalyzer();
        AnalysisResult result = analyzer.analyzeConsumeData(List.of(100.0, 200.0, 300.0), 150.0);
        System.out.println("toey forever");
        System.out.println("用户数=" + result.getTotalNum() + ", 总消费=" + result.getTotalConsume()
                + ", 均值=" + result.getAvgConsume() + ", 等级=" + result.getUserLevel());
    }

    // 内部结果实体类
    public static class AnalysisResult {
        private int totalNum;
        private double totalConsume;
        private double avgConsume;
        private int highConsumeCount;
        private int normalCount;
        private String userLevel;

        // getter & setter
        public int getTotalNum() { return totalNum; }
        public double getTotalConsume() { return totalConsume; }
        public double getAvgConsume() { return avgConsume; }
        public int getHighConsumeCount() { return highConsumeCount; }
        public int getNormalCount() { return normalCount; }
        public String getUserLevel() { return userLevel; }
        public void setTotalNum(int totalNum) { this.totalNum = totalNum; }
        public void setTotalConsume(double totalConsume) { this.totalConsume = totalConsume; }
        public void setAvgConsume(double avgConsume) { this.avgConsume = avgConsume; }
        public void setHighConsumeCount(int highConsumeCount) { this.highConsumeCount = highConsumeCount; }
        public void setNormalCount(int normalCount) { this.normalCount = normalCount; }
        public void setUserLevel(String userLevel) { this.userLevel = userLevel; }
    }
}
