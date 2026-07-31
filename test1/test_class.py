# toey forever
"""
示例Python代码：包含A、B、C三个类，实现类之间互相调用演示
A类内部调用B类的方法，各类拥有独立功能函数
"""
import logging
import unittest

logging.basicConfig(level=logging.INFO, format="%(asctime)s [%(levelname)s] %(message)s")
logger = logging.getLogger(__name__)


class ClassB:
    """B类：提供基础数据处理能力"""
    def __init__(self):
        self.base_number = 100

    def calculate_sum(self, num: int) -> int:
        """计算传入数字 + 内置基数的和"""
        return self.base_number + num

    def show_b_info(self) -> None:
        """输出B类自身信息"""
        logger.info("【ClassB】基础数值：%s", self.base_number)


class ClassC:
    """C类：字符串处理功能"""
    @staticmethod
    def format_text(content: str) -> str:
        """静态方法：格式化文本内容"""
        return f"[格式化文本] >>> {content}"

    def count_char(self, text: str) -> int:
        """统计字符串长度（不含首尾空格）"""
        return len(text.strip())


class ClassA:
    """A类：主业务类，内部调用ClassB的方法"""
    def __init__(self):
        # A类持有B类实例
        self.b_instance = ClassB()
        self.name = "A类业务实例"

    def run_business(self, input_num: int):
        """核心业务：调用ClassB中的calculate_sum方法"""
        logger.info("【%s】准备调用 ClassB 方法", self.name)
        result = self.b_instance.calculate_sum(input_num)
        logger.info("【%s】调用B计算结果 = %s", self.name, result)
        return result

    def self_func(self):
        """A类独立自有功能函数"""
        logger.info("【ClassA】执行自身独立功能逻辑")


# ---------------- 测试用例 ----------------
class TestClassABC(unittest.TestCase):
    def setUp(self):
        """每个测试方法执行前都会调用，准备共享实例"""
        self.obj_a = ClassA()
        self.obj_b = ClassB()
        self.obj_c = ClassC()

    def test_b_calculate_sum(self):
        """验证B的加法功能：基数100 + 传入数字"""
        self.assertEqual(self.obj_b.calculate_sum(50), 150)
        self.assertEqual(self.obj_b.calculate_sum(0), 100)
        self.assertEqual(self.obj_b.calculate_sum(-50), 50)

    def test_b_base_number(self):
        """验证B实例的初始状态"""
        self.assertEqual(self.obj_b.base_number, 100)

    def test_c_format_text(self):
        """验证C的格式化功能"""
        self.assertEqual(self.obj_c.format_text("hello"), "[格式化文本] >>> hello")
        self.assertEqual(self.obj_c.format_text(""), "[格式化文本] >>> ")

    def test_c_count_char(self):
        """验证C的字符统计功能：应忽略首尾空格"""
        self.assertEqual(self.obj_c.count_char("  test message  "), 12)
        self.assertEqual(self.obj_c.count_char("  "), 0)
        self.assertEqual(self.obj_c.count_char(""), 0)

    def test_a_run_business(self):
        """验证A委托B计算：100 + 50"""
        result = self.obj_a.run_business(50)
        self.assertEqual(result, 150)

    def test_a_holds_b_instance(self):
        """验证A确实持有B的实例（组合关系）"""
        self.assertIsInstance(self.obj_a.b_instance, ClassB)


# ---------------- 演示入口 ----------------
if __name__ == "__main__":
    print("toey forever")
    # 日志演示：A调用B内部方法
    obj_a = ClassA()
    obj_a.self_func()
    obj_a.run_business(50)

    # 单独使用B
    obj_b = ClassB()
    obj_b.show_b_info()

    # 单独使用C
    obj_c = ClassC()
    logger.info("%s", obj_c.format_text("hello world"))
    logger.info("字符数量：%s", obj_c.count_char("  test message  "))

    # 运行单元测试
    unittest.main(verbosity=2)
