import json
import os
from expense import Expense

DATA_FILE = "data/expenses.json"

def load_expenses():
    if not os.path.exists(DATA_FILE):
        return []
    with open(DATA_FILE, "r") as f:
        return json.load(f)

def save_expenses(expenses):
    with open(DATA_FILE, "w") as f:
        json.dump(expenses, f, indent=4)

def add_expense(expense):
    data = load_expenses()
    data.append(expense.to_dict())
    save_expenses(data)

def delete_expense(expense_id):
    data = load_expenses()
    data = [e for e in data if e["id"] != expense_id]
    save_expenses(data)

def get_all_expenses():
    return [Expense.from_dict(e) for e in load_expenses()]
