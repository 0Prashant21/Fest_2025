import csv
from datetime import datetime
from utils import clear_screen

def generate_summary(expenses):
    summary = {}
    total = 0
    for e in expenses:
        summary[e.category] = summary.get(e.category, 0) + e.amount
        total += e.amount

    print("\n=== Expense Summary by Category ===")
    for category, amount in summary.items():
        print(f"{category:<15} : Rs. {amount:.2f}")
    print(f"\nTotal Spent: Rs. {total:.2f}")

def export_to_csv(expenses):
    filename = f"data/expenses_{datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
    with open(filename, "w", newline="") as file:
        writer = csv.DictWriter(file, fieldnames=["id", "title", "amount", "category", "date"])
        writer.writeheader()
        for e in expenses:
            writer.writerow(e.to_dict())
    print(f"\n✅ Expenses exported to: {filename}")
