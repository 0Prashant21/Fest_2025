#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <iomanip>
#include <algorithm>
using namespace std;

class Book {
protected:
    int id;
    string title;
    string author;
    bool isIssued;

public:
    Book() : id(0), title(""), author(""), isIssued(false) {}
    Book(int id, string title, string author) : id(id), title(title), author(author), isIssued(false) {}

    int getId() const { return id; }
    string getTitle() const { return title; }
    string getAuthor() const { return author; }
    bool getStatus() const { return isIssued; }

    void issueBook() { isIssued = true; }
    void returnBook() { isIssued = false; }

    virtual void display() const {
        cout << left << setw(6) << id
             << setw(25) << title
             << setw(20) << author
             << setw(10) << (isIssued ? "Issued" : "Available") << endl;
    }

    string serialize() const {
        return to_string(id) + "|" + title + "|" + author + "|" + (isIssued ? "1" : "0") + "\n";
    }

    static Book deserialize(const string &line) {
        Book b;
        size_t pos1 = line.find('|');
        size_t pos2 = line.find('|', pos1 + 1);
        size_t pos3 = line.find('|', pos2 + 1);
        if (pos3 == string::npos) return b;

        b.id = stoi(line.substr(0, pos1));
        b.title = line.substr(pos1 + 1, pos2 - pos1 - 1);
        b.author = line.substr(pos2 + 1, pos3 - pos2 - 1);
        b.isIssued = (line.substr(pos3 + 1) == "1");
        return b;
    }
};

class RareBook : public Book {
    int publicationYear;
public:
    RareBook() : Book(), publicationYear(0) {}
    RareBook(int id, string title, string author, int year)
        : Book(id, title, author), publicationYear(year) {}

    void display() const override {
        cout << left << setw(6) << id
             << setw(25) << title
             << setw(20) << author
             << setw(10) << (isIssued ? "Issued" : "Available")
             << setw(10) << publicationYear << endl;
    }

    string serialize() const {
        return "R|" + to_string(id) + "|" + title + "|" + author + "|" +
               to_string(publicationYear) + "|" + (isIssued ? "1" : "0") + "\n";
    }

    static RareBook deserialize(const string &line) {
        RareBook rb;
        vector<string> parts;
        size_t start = 0, end;
        while ((end = line.find('|', start)) != string::npos) {
            parts.push_back(line.substr(start, end - start));
            start = end + 1;
        }
        parts.push_back(line.substr(start));

        if (parts.size() == 6) {
            rb.id = stoi(parts[1]);
            rb.title = parts[2];
            rb.author = parts[3];
            rb.publicationYear = stoi(parts[4]);
            rb.isIssued = (parts[5] == "1");
        }
        return rb;
    }
};

class Library {
    vector<Book*> books;
    const string filename = "library_data.txt";

public:
    ~Library() {
        for (auto b : books) delete b;
    }

    void addBook() {
        int id;
        string title, author;
        cout << "Enter Book ID: ";
        cin >> id;
        cin.ignore();
        cout << "Enter Title: ";
        getline(cin, title);
        cout << "Enter Author: ";
        getline(cin, author);

        books.push_back(new Book(id, title, author));
        cout << " Book added successfully!\n";
    }

    void addRareBook() {
        int id, year;
        string title, author;
        cout << "Enter Rare Book ID: ";
        cin >> id;
        cin.ignore();
        cout << "Enter Title: ";
        getline(cin, title);
        cout << "Enter Author: ";
        getline(cin, author);
        cout << "Enter Publication Year: ";
        cin >> year;

        books.push_back(new RareBook(id, title, author, year));
        cout << " Rare Book added successfully!\n";
    }

    void displayAll() const {
        cout << "\n" << left
             << setw(6) << "ID"
             << setw(25) << "Title"
             << setw(20) << "Author"
             << setw(10) << "Status"
             << setw(10) << "Year" << endl;
        cout << string(70, '-') << endl;

        for (auto b : books)
            b->display();
    }

    void issueBook() {
        int id;
        cout << "Enter Book ID to issue: ";
        cin >> id;
        for (auto b : books) {
            if (b->getId() == id) {
                if (!b->getStatus()) {
                    b->issueBook();
                    cout << " Book issued successfully!\n";
                    return;
                } else {
                    cout << " Already issued!\n";
                    return;
                }
            }
        }
        cout << " Book not found!\n";
    }

    void returnBook() {
        int id;
        cout << "Enter Book ID to return: ";
        cin >> id;
        for (auto b : books) {
            if (b->getId() == id) {
                if (b->getStatus()) {
                    b->returnBook();
                    cout << " Book returned successfully!\n";
                    return;
                } else {
                    cout << " Book wasn't issued!\n";
                    return;
                }
            }
        }
        cout << " Book not found!\n";
    }

    void searchBook() const {
        string keyword;
        cout << "Enter title or author keyword: ";
        cin.ignore();
        getline(cin, keyword);

        bool found = false;
        for (auto b : books) {
            if (b->getTitle().find(keyword) != string::npos ||
                b->getAuthor().find(keyword) != string::npos) {
                b->display();
                found = true;
            }
        }
        if (!found) cout << " No matching books found!\n";
    }

    void saveToFile() {
        ofstream file(filename);
        for (auto b : books) {
            RareBook* rb = dynamic_cast<RareBook*>(b);
            if (rb)
                file << rb->serialize();
            else
                file << b->serialize();
        }
        file.close();
        cout << "💾 Data saved successfully!\n";
    }

    void loadFromFile() {
        ifstream file(filename);
        if (!file.is_open()) return;

        string line;
        while (getline(file, line)) {
            if (line.empty()) continue;
            if (line[0] == 'R')
                books.push_back(new RareBook(RareBook::deserialize(line)));
            else
                books.push_back(new Book(Book::deserialize(line)));
        }
        file.close();
    }
};

int main() {
    Library lib;
    lib.loadFromFile();

    int choice;
    do {
        cout << "\n===  LIBRARY MANAGEMENT SYSTEM ===\n";
        cout << "1. Add Book\n";
        cout << "2. Add Rare Book\n";
        cout << "3. Display All Books\n";
        cout << "4. Issue Book\n";
        cout << "5. Return Book\n";
        cout << "6. Search Book\n";
        cout << "7. Save and Exit\n";
        cout << "Enter choice: ";
        cin >> choice;

        switch (choice) {
            case 1: lib.addBook(); break;
            case 2: lib.addRareBook(); break;
            case 3: lib.displayAll(); break;
            case 4: lib.issueBook(); break;
            case 5: lib.returnBook(); break;
            case 6: lib.searchBook(); break;
            case 7: lib.saveToFile(); cout << " Exiting... Goodbye!\n"; break;
            default: cout << " Invalid choice!\n";
        }
    } while (choice != 7);

    return 0;
}
