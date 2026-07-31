// toey forever
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataAnalyzerTest {

    private final DataAnalyzer analyzer = new DataAnalyzer();

    @Test
    @DisplayName("正常消费数据：应正确统计数量、总额、均值与等级")
    void analyzeConsumeData_withValidData_countsAndTagsCorrectly() {
        DataAnalyzer.AnalysisResult result = analyzer.analyzeConsumeData(Arrays.asList(100.0, 200.0, 300.0), 150.0);

        assertNotNull(result);
        assertEquals(3, result.getTotalNum());
        assertEquals(600.0, result.getTotalConsume());
        assertEquals(200.0, result.getAvgConsume());
        assertEquals(2, result.getHighConsumeCount());
        assertEquals(1, result.getNormalCount());
        assertEquals("中等消费用户", result.getUserLevel());
    }

    @Test
    @DisplayName("均值达到阈值1.5倍时应标记为高消费用户")
    void analyzeConsumeData_withAvgAbove1_5Threshold_marksHighLevel() {
        DataAnalyzer.AnalysisResult result = analyzer.analyzeConsumeData(Arrays.asList(300.0, 300.0), 150.0);

        assertEquals("高消费用户", result.getUserLevel());
    }

    @Test
    @DisplayName("均值低于阈值时应标记为普通消费用户")
    void analyzeConsumeData_withAvgBelowThreshold_marksNormalLevel() {
        DataAnalyzer.AnalysisResult result = analyzer.analyzeConsumeData(Arrays.asList(50.0, 100.0), 150.0);

        assertEquals("普通消费用户", result.getUserLevel());
    }

    @Test
    @DisplayName("负数和空值应被过滤，不计入统计")
    void analyzeConsumeData_filtersNegativeAndNullValues() {
        DataAnalyzer.AnalysisResult result = analyzer.analyzeConsumeData(Arrays.asList(-10.0, 0.0, null, 100.0), 150.0);

        assertEquals(1, result.getTotalNum());
        assertEquals(100.0, result.getTotalConsume());
        assertEquals(100.0, result.getAvgConsume());
        assertEquals(0, result.getHighConsumeCount());
        assertEquals(1, result.getNormalCount());
    }

    @Test
    @DisplayName("空列表：统计应全为0且均值不抛异常")
    void analyzeConsumeData_withEmptyList_returnsZeroStats() {
        DataAnalyzer.AnalysisResult result = analyzer.analyzeConsumeData(new ArrayList<>(), 150.0);

        assertEquals(0, result.getTotalNum());
        assertEquals(0.0, result.getTotalConsume());
        assertEquals(0.0, result.getAvgConsume());
        assertEquals(0, result.getHighConsumeCount());
        assertEquals(0, result.getNormalCount());
    }

    @Test
    @DisplayName("null入参：按空数据处理，不抛异常")
    void analyzeConsumeData_withNullList_returnsZeroStats() {
        DataAnalyzer.AnalysisResult result = analyzer.analyzeConsumeData(null, 150.0);

        assertNotNull(result);
        assertEquals(0, result.getTotalNum());
        assertEquals(0.0, result.getTotalConsume());
        assertEquals(0.0, result.getAvgConsume());
    }

    @Test
    @DisplayName("消费金额等于阈值时记为高消费")
    void analyzeConsumeData_amountEqualToThreshold_countsAsHigh() {
        DataAnalyzer.AnalysisResult result = analyzer.analyzeConsumeData(Arrays.asList(150.0), 150.0);

        assertEquals(1, result.getHighConsumeCount());
        assertEquals(0, result.getNormalCount());
        assertEquals("中等消费用户", result.getUserLevel());
    }
}
