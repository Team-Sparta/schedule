# todo-list
![Untitled-5](https://github.com/user-attachments/assets/e460714f-ac9b-4836-8218-0c6196373ba0)



| Feature                         | Method  |         url             |
| ------------------------------- | ------- | ----------------------- |
| **Get User**                    | `GET`   |   /api/v1/user          |
| **Register User**               | `POST`  |   /api/v1/user          |
| **Add Task**                    | `POST`  |   /api/v1/task          |
| **Get Task By Id**              | `GET`   |   /api/v1/task/{id}     |
| **Get All Task**                | `GET`   |   /api/v1/task          |
| **Update Task by Id**           | `PUT`   |   /api/v1/task/{id}     |
| **Update Task's Content by Id** | `PATCH` |   /api/v1/task/{id}     |
| **Delete Task by Id**           | `DELETE`|   /api/v1/task/{id}     |


## Get User

### GET - /api/v1/user 

| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userName**            | `String`    |       O       |
| **email**               | `String`    |       O       |
| **password**            | `String`    |       O       |


### Request Body

```
  {
    id : 1,
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
  result: {
    id: 123,
    username: "youngjun",
    email: "youngjun@gmail.com",
  }
}
```



## Register User

### POST - /api/v1/user 


| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userName**            | `String`    |       O       |
| **email**               | `String`    |       O       |
| **password**            | `String`    |       O       |


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
  result: {
    id: 123,
    username: "youngjun",
    email: "youngjun@gmail.com",
  }
}
```

## Add Task

### POST - /api/v1/task 


| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       O       |
| **password**            | `String`    |       O       |
| **categoryId**          | `String`    |       O       |
| **content**             | `String`    |       O       |
| **dueDate**             | `String`    |       O       |
| **priority**            | `String`    |       X       |
| **status**              | `String`    |       X       |

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

## Get Task By ID

### GET - /api/v1/task/{taskId}

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

## Get Tasks
### GET - /api/v1/tasks

### Request Query

| Name                    | Type        |   Required    |
| ----------------------- | ----------- |  ------------ |
| **priority**            | `String`    |  X |
| **status**              | `String`    |  X |
| **page**                | `Interger`  |  O |
| **lastTaskId**          | `Interger`  |  X |


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

## Update Task By Id
### PUT - /api/v1/task/{taskId}

### Parameters

| Name                    | Type        |
| ----------------------- | ----------- |
| **taskId**              | `Interger`  |


| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       O       |
| **password**            | `String`    |       O       |
| **categoryId**          | `Interger`  |       O       |
| **content**             | `String`    |       O       |
| **dueDate**             | `String`    |       O       |
| **priority**            | `String`    |       O       |
| **status**              | `String`    |       O       |

### Request Body

```
  {
    userId: 123,
    password: "password123",
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


## Update Task's Priority By Id
### PATCH - /api/v1/task/{taskId}

### Parameters

| Name                    | Type        |
| ----------------------- | ----------- |
| **taskId**              | `Interger`  |


| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       O       |
| **password**            | `String`    |       O       |
| **priority**            | `String`    |       O       |

### Request Body

```
  {
    userId: 123,
    password: "password123",
    priority: "High",    
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



## Delete Task By Id
### DELETE - /api/v1/task/{taskId}

### Request Parameters

| Name                    | Type        |
| ----------------------- | ----------- |
| **taskId**              | `Interger`  |

### Request Body


| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       O       |
| **password**            | `String`    |       O       |

```
  {
    userId: 123,
    password: "password123",    
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





