class Triangle:

    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c

    def classify(self):

        a = self.a
        b = self.b
        c = self.c

        if a <= 0 or b <= 0 or c <= 0:
            return "Invalid triangle"

        if a + b <= c or a + c <= b or b + c <= a:
            return "Invalid triangle"

        if a == b == c:
            return "Equilateral"

        if a == b or b == c or a == c:
            return "Isosceles"

        return "Scalene"
