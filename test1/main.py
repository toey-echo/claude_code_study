def pascal_triangle(n: int) -> None:
    if not isinstance(n, int):
        raise TypeError(f"n 必须是整数，实际得到: {type(n).__name__}")
    if n <= 0:
        raise ValueError("n 必须大于 0")
    row = [1]
    for _ in range(n):
        print(" ".join(map(str, row)))
        row = [1] + [row[i] + row[i + 1] for i in range(len(row) - 1)] + [1]


if __name__ == "__main__":
    try:
        pascal_triangle(5)
    except (TypeError, ValueError) as e:
        print(f"出错了: {e}")
    print("toey forever")

