# todo-list
![Untitled-3](https://github.com/user-attachments/assets/977fdaf6-3ddf-4a78-82a7-cb244d327e9a)




| Feature                         | Method  |         url             |
| ------------------------------- | ------- | ----------------------- |
| **Register User**               | `POST`  |   /api/v1/user          |
| **Add Task**                    | `POST`  |   /api/v1/task          |
| **Get Task By Id**              | `GET`   |   /api/v1/task/{id}     |
| **Get All Task**                | `GET`   |   /api/v1/task          |
| **Update Task by Id**           | `PUT`   |   /api/v1/task/{id}     |
| **Update Task's Content by Id** | `PATCH` |   /api/v1/task/{id}     |
| **Delete Task by Id**           | `DELETE`|   /api/v1/task/{id}     |


## POST /api/v1/user - Register User

### Request Body

```
  {
    userName: youngjun,
    email: "youngjun@gmail.com",
    password: youngjun123,    
  }
```
### Responses

Status Code: 200

```
{
  success : true,
  message : "Response Message",
  id: 123,
  username: "youngjun",
  email: "youngjun@gmail.com",
}
```

## POST /api/v1/task - Add Task

### Request Body

```
  {
    userId: 123,
    password: "password123",
    categoryId: 1,
    content: "updated content body",
    dueDate: "2024-11-05",
    priority: "Medium",
    status: "Pending"
  }
```

### Responses

Status Code: 201

```
{
  success : true,
  message : "Response Message",
  result : {
    taksId: 123,
    content: "content body",
    due_date: "2024-11-05",
    priority: "Medium",
    status: "Pending",
    categoryInfo: {
      categoryId: 1,
      categoryName: "Work",
    },
    userInfo: {
       userId: 123.
       username: "youngjun",
    },
  }
}
```

## GET /api/v1/task/{taskId} - Get Task By ID

### Parameters

| Name                    | Type        |
| ----------------------- | ----------- |
| **taskId**              | `Interger`  |


### Responses

Status Code: 200

```
{
  success : true,
  message : "Response Message",
  result : {
    taskId: 123,
    content: "content body",
    dueDate: "2024-11-05",
    priority: "Medium",
    status: "Pending",
    categoryInfo: {
      categoryId: 1,
      categoryName: "Work"
    },
    userInfo: {
       userId: 123.
       username: "youngjun"
    },
  }
}
```

## GET /api/v1/tasks - Get Tasks

### Request Query

| Name                    | Type        |
| ----------------------- | ----------- |
| **priority**            | `String`    |
| **status**              | `String`    |
| **page**                | `Interger`  |
| **lastTaskId**          | `Interger`  |


### Responses

Status Code: 200

```
{
  success : true,
  message : "Response Message",
  result : {
    isLast: true,
    tasks: [
    {
      taksId: 123,
      content: "content body",
      due_date: "2024-11-05",
      priority: "Medium",
      status: "Pending",
      categoryInfo: {
        categoryId: 1,
        categoryName: "Work,
      },
      userInfo: {
        userId: 123.
        username: "youngjun"
      }
    }
  ]    
 }
}
```

## PUT /api/v1/task/{taskId} - Update Task By Id

### Parameters

| Name                    | Type        |
| ----------------------- | ----------- |
| **taskId**              | `Interger`  |

### Request Body

```
  {
    userId: 123,
    userPassword: "password123",
    content: "updated content body",
    due_date: "2024-11-05",
    priority: "Medium",
    status: "Pending"
  }
```

### Responses

Status Code: 200

```
{
  success : true,
  message : "Response Message",
  result : {
    taksId: 123,
    content: "updated content body",
    due_date: "2024-11-05",
    priority: "Medium",
    status: "Pending",
    categoryInfo: {
      categoryId: 1,
      categoryName: "Work",
    },
    userInfo: {
       userId: 123.
       username: "youngjun",
    }
  }
}
```


## PATCH /api/v1/task/{taskId} - Update Task's Priority By Id

### Parameters

| Name                    | Type        |
| ----------------------- | ----------- |
| **taskId**              | `Interger`  |

### Request Body

```
  {
    userId: 123,
    userPassword: "password123",
    priority: "High",
    status: "Pending"
  }
```

### Responses

Status Code: 200

```
{
  success : true,
  message : "Response Message",
  result : {
    taksId: 123,
    content: "updated content body",
    due_date: "2024-11-05",
    priority: "High",
    status: "Pending",
    categoryInfo: {
      categoryId: 1,
      categoryName: "Work",
    },
    userInfo: {
       userId: 123.
       username: "youngjun",
    },
  }
}
```



## DELETE /api/v1/task/{taskId} - Delete Task By Id

### Request Parameters

| Name                    | Type        |
| ----------------------- | ----------- |
| **taskId**              | `Interger`  |

### Request Body

```
  {
    userId: 123,
    userPassword: "password123",    
  }
```

### Responses

Status Code: 200

```
{
  "success": true,
  "message": "Response Message",
  "result": {
    "taskId": 0
  }
}
```


## Error Code 

| HTTP Status | Message                               | Description                                                  |
|-------------|---------------------------------------|--------------------------------------------------------------|
| 400         | Invalid request data                 | One or more required fields are missing or invalid           |
| 400         | Invalid parameter                    | An invalid or missing parameter, e.g., `taskId`              |
| 401         | Unauthorized access                  | Incorrect or missing authentication credentials              |
| 401         | Permission denied                    | User lacks permission to modify this task                    |
| 403         | Forbidden                            | User action not allowed                                      |
| 404         | User not found                       | User with the given ID was not found                         |
| 404         | Task not found                       | Task with the specified ID does not exist                    |
| 404         | Category not found                   | Category with the specified ID does not exist                |
| 409         | Duplicate entry                      | User or task already exists                                  |
| 500         | Internal server error                | General server error                                         |
| 503         | Service unavailable                  | Database or required service is currently unavailable        |





