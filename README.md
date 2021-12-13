### Student Management System

Yuan Chen

yc5118@nyu.edu



#### Outline

The project is a student management system with basic CRUD function based on the Spring Boot framework.

It includes the knowledge of networking, databases, REST API and Sping Framework of Java. The basic architecture of my project is as the following shows. 

![image-20211211215212146](./doc/fig/arch.png)



#### Basic Functions

- **Login/Register:**

  - **Login:** 

    1. There are two type of users: teachers and students. They can both login the system with account number and password. 
  2. Users can not get access to any page of the system before login.
    3. The password is encrypted by MD5 in the database.

    **Register**: 

    1. Students can register their account through the register page. They can set account name, account number and password. 
  2. System can check whether the same account name and account number has already existed in the database. If the account name and account number has already existed, require the user to enter new account name and account number.
    3. Teachers' account should be manually added to the database.

    

- **Teachers' function:** 

  - **Change password:**

    1. Teachers can change their account password. The password can not be the same with the original. The password is save in MD5 in the database.

  - **Add course:**

    1. Teachers can add new courses: set the course name, the credit of the course, the total student limit, and the begin and end date of the course

  - **Course tabe:**

    Teachers can see their own courses in the course table, they can not see other teachers' courses.

    1. Course detail:

       Teachers can see all the students who have enrolled this course in the detail page.

       Teachers can save each students' grade in the detail page.

    2. Archive:

       Archive means archive all the students' grade and end this course. After archiving the course, all the students' grade can not be changed.

    

- **Students:**

  - **Course statistics:**

    In the dashboard page, students can see the course statistics. 

    1. The students can see the credits they earned , the total courses they've taken, and the average grade (previous three statistics are all based on the course which have been already archived by the teachers).

  - **Change password:**

    1. Students can change their account password. The password can not be the same with the original. The password is save in MD5 in the database.

  - **Enroll a course:**

    1. In the course table, students can see the list of all the courses which have not been archived.
  2. Students can enroll the course before the end date of the courses.
    3. Students can enroll the course which has remaining seats.

  -  **Drop a course**:

    1. Student can see the grade of a couse he/she have enrolled in this page.
    2. Students can drop this course before the teacher archive this course.
    3. As long as the teacher archive the course, student can no longer drop this course.

    
    

#### Technologies

- **Back-end:**

  - Java 1.8

  - MySQL

  - Spring Boot

  - Spring Date JPA

  - Lombok

  - Thymeleaf

    

- **Front-end:** 

  - Vue.js

  - JQuery

  - Ajax
  - BootStrap
  - [Light Bootstrap Dashboard](https://github.com/creativetimofficial/light-bootstrap-dashboard)

  


#### Database Design





#### Use the project