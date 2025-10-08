from expense import Expense
import manager
import report
from utils import print_table, clear_screen

def main():
    while True:
        print("\n=== Expense Tracker ===")
        print("1. Add Expense")
        print("2. View All Expenses")
        print("3. Delete Expense")
        print("4. Generate Summary")
        print("5. Export to CSV")
        print("6. Exit")

        choice = input("Enter your choice: ")

        if choice == "1":
            title = input("Title: ")
            amount = float(input("Amount: Rs. "))
            category = input("Category (Food, Travel, etc.): ")
            exp = Expense(title, amount, category)
            manager.add_expense(exp)
            print("✅ Expense added!")

        elif choice == "2":
            expenses = manager.get_all_expenses()
            if expenses:
                print_table(expenses)
            else:
                print("No expenses found.")

        elif choice == "3":
            exp_id = input("Enter Expense ID to delete: ")
            manager.delete_expense(exp_id)
            print("🗑️ Expense deleted!")

        elif choice == "4":
            expenses = manager.get_all_expenses()
            report.generate_summary(expenses)

        elif choice == "5":
            expenses = manager.get_all_expenses()
            report.export_to_csv(expenses)

        elif choice == "6":
            print("👋 Goodbye!")
            break

        else:
            print("Invalid choice, try again.")

if __name__ == "__main__":
    main()
