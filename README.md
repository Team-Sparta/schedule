# todo-list
![Untitled](https://github.com/user-attachments/assets/ca44bdad-9dd2-4c7c-9b9d-8215c93e853b)




| Feature                    | Method  |         url             | Request |                    Response                       |    Status Code              |
| -------------------------  | ------- | ----------------------- | ------- | ------------------------------------------------- | --------------------------- |  
| **Register User**          | `POST`  |   /api/v1/user/register |   BODY  |  Register new user                                | SUCCESS: 201                |
| **Add Task**               | `POST`  |   /api/v1/task          |   BODY  |  Add a new task                                   | SUCCESS: 201                |
| **Get Task By Id**         | `GET`   |   /api/v1/task/{id}     |     -   |  Get single task by ID                            | SUCCESS: 200/204,           |
| **Get All Task**           | `GET`   |   /api/v1/task          |     -   |  Get all task from user                           | SUCCESS: 200/204            |
| **Get Task by Completed**  | `GET`   |   /api/v1/task          |  PARAMS |  Get all task that is sorted by completion status | SUCCESS: 200/204            |
| **Get Task by Pagination** | `GET`   |   /api/v1/task          |  PARAMS |  Get all task by pagination value                 | SUCCESS: 200/204            |
| **Update Task by Id**      | `PUT`   |   /api/v1/task/{id}     |   BODY  |  Update task by ID                                | SUCCESS: 200                |
| **Delete Task by Id**      | `DELETE`|   /api/v1/task/{id}     |    -    |  Delete or removed task from database by ID       | SUCCESS: 200                |
| **Delte All**              | `DELTE` |   /api/v1/task          |    -    |  Delete All Tasks                                 | SUCCESS: 200                |
