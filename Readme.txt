Library Management System API
This project is an API for a Library Management System. It allows librarians to manage books' data and student information. The API is built using Spring Boot framework with Java programming language, and the data is stored in MySQL.

Technologies Used
Eclipse
Postman
MySQL
Spring Boot
Java

API Endpoints
POST /user/login: Authenticate user credentials and return an access token
POST /user/add: Add a new user (requires Librarian access)
POST /student/addStudent: Add a new student (requires Librarian access)
GET /student/getAllStudents: Get a list of all students and book(s) issued by them respectively(requires Librarian access)
PUT /student/updateMobileNumber/{studentId}: Update a student's mobile number by ID (requires Librarian access)
GET /student/getstudentByMobile: Get a student by mobile number (requires Librarian access)
POST /book/addBook: Add a new book (requires Librarian access)
POST /book/issueBook/{bookId}: Issue a book to a student by book ID (requires Librarian access)
POST /book/returnBook/{bookId}: Return a book by book ID (requires Librarian access)
GET /book/getAllBooks: Get a list of all books and student to whom book is/was issued(requires Librarian access)
GET /book/getBookById/{bookId}: Get a book by ID and student to whom book is/was issued(requires Librarian access)


How to Run
Clone the repository
Import the project into Eclipse
Run the application
Use Postman to make requests to the API endpoints

Contribution Guidelines
This project is open to contributions. If you find any issues or would like to suggest an improvement, feel free to create a pull request.