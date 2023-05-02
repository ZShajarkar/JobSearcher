# Job Searcher

A university final project that helps users to find jobs and companies to hire candidates using MySQL database, Java, Spring Boot, and Swagger UI.

## Installation

•  Clone the repository: `git clone https://github.com/ZShajarkar/job-searcher.git`

•  Navigate to the project directory: `cd job-searcher`

•  Create a MySQL database named `finalproject` and update the `application.properties` file with your database credentials.

•  Build the project: `mvn clean install`

•  Run the application: `mvn spring-boot:run`


## Usage

The application exposes a REST API that can be accessed using Swagger UI. To access the Swagger UI, open the following URL in your browser: http://localhost:8084/swagger-ui.html

The API consists of the following endpoints:

•  `/public/user/v1/process_register`: Register a new user with an email and a password.

•  `/public/user/v1/process_register/admin`: Register a new company with an email and a password.

•  `/public/user/v1/sign_in`: Login with an existing user or company and get a JWT token.

•  `/jobId/{job-id}`: Get resumes that had been sent to a job post.

•  `/public/resume/v1/job/{jobId}`: Upload resume and it needs user authorization


The application has three roles: admin, user, and company. Each role has different permissions and functionalities:

•  Admin: Can view, approve, or reject all jobs posted by companies. Can also view all users and companies registered in the system.

•  User: Can  apply for jobs posted by companies. Can also view the status of their applications and update their profile.

•  Company: Can create a job post and view the resumes of the applicants. Can also update their profile and delete their job posts.


## License

## Contact


•  Email: shajarkarz@gmail.com

•  GitHub: https://github.com/ZShajarkar
