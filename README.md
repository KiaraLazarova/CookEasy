# Cook Easy
<p align="center">
  <strong><i>Cook Easy</i></strong> is a web application for sharing a vast range of recipes. Its goal is to preserve traditional and not so traditional recipes and stimulate     people's desire to consume more homemade food. It is clear from its name that cooking does not have to be difficult. In addition, the design is specifically made to be           accessible, understandable and easy to navigate for the end user.
</p>
<p align="center">
  This is why <img src="https://user-images.githubusercontent.com/73967109/145695134-e5c91a25-cc0f-4e9d-9e18-608ba11b4f9a.png" style="width: 95%; height: 95%;">
</p>
<p align="center"><img src="https://user-images.githubusercontent.com/73967109/145695064-bfae56a0-80b9-4963-b8b7-1ccbef5ec892.jpg"></p>

### This is a web application that uses the following technologies, frameworks and development techniques:
* It is implemented using **Spring Framework**.

* It uses **Thymeleaf** template engine to create, process and display templates.

* It uses **MySQL** relational database management system to store data that is necessary for the application in order to work properly.
	* It uses **Spring Data** to access the information that is stored in the database.
	* It uses database that follows the so-called **Normal Forms** up to 4NF.

* It implements **Responsive Web Page Design** based on **Bootstrap**.

* It uses the standard **Spring Security** to manage users and roles.
  * User **roles** are **manageable** from the application.
  * The **role management** is **secured** and **error-safe**.
  * **Registered** users can have either a **user role** or an **admin role**, or **both**.
  * **Admins** have the same permissions as the users but they can also _edit users_ (make them admins or delete them), _edit comments_ (approve them or archive them) and _view             statistics_.
  * **Guest users** can view _index_ page, _login_ and _register_ page as well as _about us_ page.
  * **Logged users** can explore all the other functionalities of the app such as _adding a new recipe_, _commenting a recipe_, _viewing their collections_ etc.

* It has one **REST Controller** and therefore it uses **JavaScript Fetch API** to load and display data about comments on recipes.

* It implements **Error Handling** and **Data Validation** both client-side (templates) and server-side (models and controllers) to **avoid** crashes when **invalid input** is       enetered by the user. Consequently, appropriate messages are shown to the particular user.

* It uses 2 interceptors - one for the statistics (visible only to admins) and one for the internationalisation implemented by the _quote_ functionality.

* It has 1 **Scheduled Job** used to delete archived comments on every 24 hours.

* It uses **ModelMapper** mapping library.

* It has **Unit** and **Integration** tests that cover **63 %** of its business logic.
