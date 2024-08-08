# Rest Basics

### Task

#### Recommended Timeline

The recommended timeline for the whole module is 2 weeks.

#### Business requirements

- Add Comment entity with fields Id, Content(should have length from 5 to 255), NewsId, Created, Modified.
- Implement REST controllers for Author, News, Tag and Comment entities.
- Implement exception handler for REST controllers. Use @ControllerAdvice annotation.
- The REST controllers should support of [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) operations for above mentioned objects.
- The REST controller methods should:
  - Support retrieval of collection of authors, tags, news and comments using search criteria, pagination and sorting.
  - Support retrieval of author, tag, news and comment by id.
  - Support creation of author, tag, news and comment.
  - Support modification of author, tag, news and comment.
  - Support partial modification of author, tag, news and comment using PATCH HTTP Verb.
  - Support deletion of author, tag, news and comment by id.
  - Support retrieval of author by news id.
  - Support retrieval of tags by news id.
  - Support retrieval of comments by news id.
  - Support retrieval of news by tag names, tag ids, author name, title, content (all params are optional and can be used
    in conjunction) [optional].
- Use versioning of REST controllers and their methods [optional].
- Use Data transfer objects DTO as input parameters (requests) and output result (response).
- Validate input parameters (requests) of REST controller methods.

#### Prerequisites

Your **ORM** solution. Do not delete or move basic interfaces. 

You may create your own REST controller interfaces on the base of the BaseController interface.

Use Spring Boot starter: `org.springframework.boot:spring-boot-starter-web` for implementing REST functionality.

#### Operations

The system should expose CRUD operations for News, Author, Tag and Comments from the __web__ module in the project:

- [x] Create News - fill only title, content, authorId, tag ids (optional) in news dto request and return created news,
  as dto response, http response status - 201.
- [x] Create Author - fill only name in author dto request and return created author, as dto response, http response status - 201.
- [x] Create Tag - fill only name in tag dto request and return created tag as dto response, http response status - 201.
- [x] Create Comment - fill only content and newsId in comment dto request and return created comment as dto response,
  http response status - 201.

- [x] Get All News – return list of news dtos using search criteria, pagination and sorting, http response status - 200.
- [x] Get All Authors – return list of authors dtos using search criteria, pagination and sorting, http response status - 200.
- [x] Get All Tags – return list of tag dtos using search criteria, pagination and sorting, http response status - 200.
- [x] Get All Comments – return list of comment dtos using search criteria, pagination and sorting, http response status - 200.

- [x] Get News by id – return news by provided id, http response status - 200.
- [x] Get Author by id – return author by provided id, http response status - 200.
- [x] Get Tag by id – return tag by provided id, http response status - 200.
- [x] Get Comment by id – return comment by provided id, http response status - 200.

- [x] Update News – update only title, content, authorId, tag ids [tag ids are optional] by provided news id and return updated
  news as dto, http response status - 200.
- [x] Update Author – update only name by provided author id and return updated author as dto, http response status - 200.
- [x] Update Tag – update only name by provided tag id and return updated tag as dto, http response status - 200.
- [x] Update Comment – update only content by provided comment id and return updated comment as dto, http response status - 200.

- [x] Delete News – delete news by provided news id and return no value, http response status - 204.
- [x] Delete Author – delete author by provided author id and return no value, http response status - 204.
- [x] Delete Tag – delete tag by provided tag id and return no value, http response status - 204.
- [x] Delete Comment – delete comment by provided comment id and return no value, http response status - 204.

- [x] Get Author by news id – return author as dto by provided news id, http response status - 200.
- [x] Get Tags by news id – return tags as list of dtos by provided news id, http response status - 200.
- [x] Get Comments by news id – return comments as list of dtos by provided news id, http response status - 200.
- [x] Get News by tag names, tag ids, author name, title, content (all params are optional and can be used in
  conjunction) – return news by provided params, http response status - 200. [optional].

All returned and received data should be like [DTO](https://en.wikipedia.org/wiki/Data_transfer_object) type.

The mapping between the `dto` and the `model (domain object)` should be done at the service layer using any library.
For example: [Mapstruct](https://mapstruct.org/), [Modelmapper](http://modelmapper.org/).

#### Validation

All input parameters (requests) should be validated directly in business logic code via custom validation or spring functionality.
>Note: to support your custom annotations and perform validation outside of business logic code you can use
> e.g. [Aspects](https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/core.html#aop).

#### Testing

- [x] Cover controller layer with JUnit tests using e.g. [RestAssured Framework](https://semaphoreci.com/community/tutorials/testing-rest-endpoints-using-rest-assured).

#### General requirements:

1. Code should be clean and should not contain any “developer-purpose” constructions.
2. App should be designed and written with respect to OOD, SOLID principles and best REST design practices.
3. Clear layered structure should be used with responsibilities of each application layer defined.
4. All business logic should be written in the module-service: mapping `model` to `dto` and vice versa, validation, etc.
5. Module-web should accept and return `dto` data transfer objects.
6. Controller methods that return collection of `dto` should support pagination and sorting.
7. Controllers and their methods should support versioning.
8. Convenient error/exception should be implemented: all errors should be meaningful. Errors should
   contain `errorMessage` and `errorCode`, where `errorCode` is your custom code.
9. Application should be tested and pass all tests suites.

#### Application requirements:

1. Java 17 should be used.
2. Gradle. Multi-module project. Spring Boot.
3. Application packages root: `com.mjc.school`.
4. Java Code Convention is mandatory.

#### Our solution review:

If you have finished task and would like to see the original solution of it written by our experts, write in #stage-3 channel about it. Access will be provided.

