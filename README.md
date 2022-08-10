# Consulteer-Zadatak

<h2>Project Description</h2>

I have created data model for Post and Comment as required.

Post entity has the following fields:
Long id - autoincrement,

String title - required,

String content - required,

Integer likes,

Integer dislikes

Next the Comment entity has the following fields:

Long id - autoincrement,

String comment - required,

Long post_id - required


One Post can have zero or more Comments. One Comment belongs to only one
Post. One Comment cannot have other Comments. This is the reason why post_id field is set to be required.

For the purpose of generating entities and their relationship I have used hibernate.

In this project I have created two controllers - one for the Post and one for the Comment.

The main route for the PostController is http://localhost:8080/api/post

The main route for the CommentController is http://localhost:8080/api/comment

Both controllers have endpoints that support basic CRUD operations  which are: 

Create, Update, Delete, GetAll, GetById

PostController has additional endpoints for liking and disliking Posts.

API documentation in json format can be found on the following link http://localhost:8080/api/api-docs

Swager UI is available on the following link http://localhost:8080/api/swagger-ui.html

<h2>Technologies used</h2>

Project has been accomplished using Java programming language. Framework that has been used is Spring Boot and ORM that has been used is Hibernate.

Build-tool used inside of this project is Maven.

Next, springdoc-openapi is responsible for generating API documentation in json format. 

For the purpose of this project I have used MySQL database and the MySQL Workbench tool.
