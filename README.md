# College-ERP-System
Run the main in Main.java. Then my code would ask you the role for which you want to login.
The login data of admin and professors have been provided in the code already and student
need to register first for logging in.
--> FOR STUDENT FIRST REGISTER AND THEN LOGIN USING THE SAME DETAILS

--> PROFESSORS EMAIL AND PASSWORD ARE IN THE MAINE FILE ON LINE 34-36:-
A. Name - name1
Email - email1 Password - 123
B. Name - name2
Email - email2 Password - 123
C. Name - name3
Email - email3 Password - 123

ADMIN EMAIL - Admin@iiitd.ac.in
ADMIN PASSWORD - 123

--> TA EMAIL AND PASSWORD ARE IN THE MAIN FILE ON LINE 37-39:-
A. Name - ta1
Email - email1 Password - 123
B. Name - ta1
Email - email2 Password - 123
C. Name - ta1
Email - email3 Password - 123

I have added 6 courses in my code. Among these 3 are for the
first semester(MAT101,MAT102,MAT103) and 2 are for the second semester(CSE101,CSE102)
and 1 for the 3rd semester (CSE103).

The professor initially does not know the courses allocated to him.
He will only know after the admin allocates the course to him .
The student need not register all the courses available in each semester, and if he fails in one of
the course registered by him, then he will remain in the same semester.
Admin assigns grade and accordingly the SGPA is calculated eventually cgpa is also calculated.


OOPS CONCEPTS APPLIED:
1.CLASSES - Used everywhere to make different entities like student, professor , admin etc.
2.INTERFACE -
"User" interface was used and implemented on student,professor and admin to
make sure they implement a menu method.
3.POLYMORPHISM - Can be seen in various places like Overriding toString method to return
string value of objects, Constructor overloading in classes by making parameterised and non
parameterised constructors.
4.ENCAPSULATION - Getters are Setters are used in evey class to get and set private
attributes (like name , email, password etc in student class).
5.GENERICS - Used for detailed feedback system so that student can either give rating out of
10 or give detailed detailed feeback which can be viewd by the professor. Both type of
feedbacks are stored in the same class with help of generics.
6.INHERITANCE - Inheritance has been used to make TA class which extends Student class
and all its methods.
7.EXCEPTION HANDLING - 3 Types of Custom exceptions are made:-
CourseFullException,InvalidLoginException,DropDeadlinePassedException which are invoked


using try-catch blocks with a custom message whenever there is an exception in program.
1. Exception Handling:-
Exception triggered due to invalid login credentials of Student,admin,professor
Exceptions due to exceeded students limits for the course
Exception due to dropping the course after the dropping deadline has passed
2. FEEDBACK
3. TA :-
Here TA is able to view the grade assigned by admin
Here the TA is able to update the grade
