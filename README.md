<a id="readme-top"></a>
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/irene-fabbri/pc-shop-using-db">
    <img src="https://cdn-icons-png.flaticon.com/256/17351/17351425.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Pc Shop</h3>

  <p align="center">
    An attempt at creating a web app using SpringBoot and Thymeleaf with a MySql backend
    <br />
    <a href="https://github.com/irene-fabbri/pc-shop-using-db"><strong>Explore the docs »</strong></a>
    <br />
    <!-- TODO: add demo
      <br />
      <a href="https://github.com/github_username/repo_name">View Demo</a>
    -->
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#code-description">Code description</a>
      <ul>
          <li><a href="#java">Java</a></li>
          <li><a href="#html">Html</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Pc Shop Screen Shot][product-screenshot]](https://example.com)

My first attempt at creating a web application using Spring Boot and Thymeleaf with a MySQL backend.
The application will allow users to purchase PCs listed in the database, ensuring sufficient stock before completing the transaction. 
After a successful purchase, users will receive a confirmation email. The project will emphasize form validation and responsive design.
<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* [![Java][Java.com]][Java-url]
* [![Springboot][Spring.io]][Spring-url]
* [![Thymeleaf][Thymeleaf.org]][Thymeleaf-url]
* [![MySql][MySql.com]][MySql-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Code description
### Dependencies
  > Thymeleaf,
  > Spring Web,
  > Spring Boot Web Tools,
  > MySql Driver,
  > Spring Data JDBC
### Java
  - [Database Config](DatabaseConfig.java) - _configures the connection to the MySql database_
  - [Mail Config](MailConfig.java) - _configures the connection to gmail_
  - [Email Service](EmailService.java) - _provides some useful mail templates_
  - [Pc](Pc.java) - _is the personal computer in store class: it has model name, brand,description, price, image, quantity available in store and quantity sold. reflects the columns of the database table_
  - [PcSelezionato](PcSelezionato.java) - _is the personal computer in shopping cart class: it has model name, brand,description, price, image, quantity selected for purchase_
  - [PcJdbcTemplate](PcJdbcTemplate.java) - _is the class containing the database queries: select, insert, delete and update quantity (to update the available number and sold number of a given pc)_
  - [MyController](MyController.java) - _a class that handles HTTP requests and responses, facilitating the interaction between the front end and the back end of the application._
### Html
  - [Layout](templates/layout/layout.html) - _the layout of every html page. It contains the css libraries, JavaScript scripts, a navbar, a sidebar and a footer_
  - [Index](templates/index.html) - _the homepage: it contains a shop description, the card of the item sold in shop and a div with a random mario card obtained interrogating an API_
  - [Carrello](templates/carrello.html) - _the cart page: it contains a table with the list of every item purchased. The user can modify the quantity, remove from cart and purchase the selected items_
  - [Fattura](templates/fattura.html) - _the order confirmation page: it contains a table with the list of every item purchased and a box where the user can put the email address to recieve an order recap_

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

This project is intendend for educational purposes and that is why contribution are not accepted. However, if you have suggestions on how to make the project better,
feel free to reach out and give me suggestions.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contributors:

  <a href="https://github.com/FrancescoColli"><img src="https://avatars.githubusercontent.com/u/183523732?v=4" alt="Francesco Colli image" style="width: 5vw; height: 5vw; border-radius: 50%; background-color: #ccc;" /></a>
  <a href="https://github.com/ebofra95"><img src="https://avatars.githubusercontent.com/u/183523320?v=4" alt="Francesco Eboli image" style="width: 5vw; height: 5vw; border-radius: 50%; background-color: #ccc;" /></a>
  <a href="https://github.com/Pasquale1702"><img src="https://avatars.githubusercontent.com/u/183523433?v=4" alt="Pasquale Gerbi image" style="width: 5vw; height: 5vw; border-radius: 50%; background-color: #ccc;" /></a>
  <a href="https://github.com/federic553"><img src="https://avatars.githubusercontent.com/u/183523995?v=4" alt="Federico Manni image" style="width: 5vw; height: 5vw; border-radius: 50%; background-color: #ccc;" /></a>

<!-- CONTACT -->
## Contact

Irene Fabbri - [linkedin-url] - irenefabbri.ed@gmail.com

Project Link: [https://github.com/irene-fabbri/pc-shop-using-db](https://github.com/irene-fabbri/pc-shop-using-db)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Carlo Inserra](https://github.com/Carleoinserra)
* [Francesco Colli](https://github.com/FrancescoColli)
* [Francesco Eboli](https://github.com/ebofra95)
* [Pasquale Gerbi]()
* [Federico Manni](https://github.com/federic553)
<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/ifabbri
[product-screenshot]: images/screen.PNG
[Java.com]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/
[Spring.io]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[Thymeleaf.org]: https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white
[Thymeleaf-url]: https://thymeleaf.org/
[MySql.com]: https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white
[MySql-url]: https://www.mysql.com/
