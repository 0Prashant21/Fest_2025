import uuid
from datetime import datetime

class Expense:
    def __init__(self, title, amount, category, date=None):
        self.id = str(uuid.uuid4())[:8]
        self.title = title
        self.amount = float(amount)
        self.category = category
        self.date = date if date else datetime.now().strftime("%Y-%m-%d")

    def to_dict(self):
        return {
            "id": self.id,
            "title": self.title,
            "amount": self.amount,
            "category": self.category,
            "date": self.date
        }

    @staticmethod
    def from_dict(data):
        exp = Expense(data["title"], data["amount"], data["category"], data["date"])
        exp.id = data["id"]
        return exp
