import os

def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')

def print_table(expenses):
    print("\n{:<10} {:<20} {:<10} {:<15} {:<12}".format("ID", "Title", "Amount", "Category", "Date"))
    print("-" * 70)
    for e in expenses:
        print("{:<10} {:<20} {:<10} {:<15} {:<12}".format(
            e.id, e.title, e.amount, e.category, e.date
        ))
