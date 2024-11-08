# todo-list
<img width="804" alt="Screenshot 2024-11-07 at 15 53 00" src="https://github.com/user-attachments/assets/4471c5ec-f414-498b-b049-7ed254a3b832">



| Feature                             | Method  | URL                    |
|-------------------------------------| ------- |------------------------|
| **Sing In**                         | `POST`  | /api/v1/users/sign-in  |
| **Sign Up**                         | `POST`  | /api/v1/users/sign-up  |
| **Add Schedule**                    | `POST`  | /api/v1/schedules      |
| **Get All Schedules**               | `GET`   | /api/v1/schedules      |
| **Get Schedule By Id**              | `GET`   | /api/v1/schedules/{id} |
| **Update Schedule by Id**           | `PUT`   | /api/v1/schedules/{id} |
| **Update Schedule's Content by Id** | `PATCH` | /api/v1/schedules/{id} |
| **Delete Schedule by Id**           | `DELETE`| /api/v1/schedules/{id} |
| **Get All Categories**              | `GET`   | /api/v1/categories     |

## Sign In

| Method     | URL                                 | 
| ---------- |-------------------------------------| 
| `POST`     | https://localhost:8080/api/v1/users | 

### Request Body

| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userName**            | `String`    |       Y       |
| **password**            | `String`    |       Y       |

### Response Body

#### Main

| Name                    |  Type       |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  | Indicates whether the request was successful. |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name                    | Type       |   Description |
| ----------------------- | ---------- | ------------- |
| **id**                  |  `Integer` |  User's id    |
| **userName**            |  `String`  |  User's name  |
| **email**               |  `String`  |  User's email |


### Example

#### Request

```
curl -X POST https://localhost:8080/api/v1/my-info \
  -H "Content-Type: application/json" \
  -d '{    
    "userName": "youngjun",    
    "password": "youngjun123"
  }'
```

#### Response

```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success" : true,
  "message" : "Response Message",
  "result": {
    "id": 123,
    "username": "youngjun",
    "email": "youngjun@gmail.com",
  }
}
```

## Sign Up

| Method     | URL                                  | 
| ---------- | ------------------------------------ | 
| `POST`     | https://http://localhost:8080/api/v1/users| 


### Request Body

| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userName**            | `String`    |       Y       |
| **email**               | `String`    |       Y       |
| **password**            | `String`    |       Y       |

### Response Body

#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  |   Indicates whether the request was successful.  |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name                    | Type        |   Description |
| ----------------------- | ----------- | ------------- |
| **id**                  |  `Integer`  |  User's id    |
| **userName**            |  `String`   |  User's name  |
| **email**               |  `String`   |  User's email |

### Example

#### Request

```
curl -X POST https://localhost:8080/api/v1/user \
  -H "Content-Type: application/json" \
  -d '{    
    "userName": "youngjun",
    "email": "youngjun@gmail.com",
    "password": "youngjun123"
  }'
```
#### Response

```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success" : true,
  "message" : "Response Message",
  "result": {
    "id": 123,
    "username": "youngjun",
    "email": "youngjun@gmail.com",
  }
}
```

## Add Schedule

| Method     | URL                                    | 
| ---------- |----------------------------------------| 
| `POST`     | http://localhost:8080/api/v1/schedules | 


### Request Body

| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       Y       |
| **categoryName**        | `String`    |       Y       |
| **content**             | `String`    |       Y       |
| **dueDate**             | `String`    |       Y       |
| **priority**            | `String`    |       N       |
| **status**              | `String`    |       N       |

### Response Body

#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  |  Indicates whether the request was successful. |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |


#### Result

| Name             | Type        | Description                                            |
|------------------| ----------- |--------------------------------------------------------|
| **scheduleId**   |  `Integer`  | Schedule's id                                          |
| **content**      |  `String`   | Schedule's Content                                 |
| **dueDate**      |  `String`   | Schedule's Deadline Date                           |
| **priority**     |  `String`   | Taks's Priority ["Low", "Medium", "High"]              |
| **status**       |  `String`   | Schedule's Status ["Pending", "Progress", "Completed"] |
| **categoryInfo** |  CategoryInfo  | Taks's Category                                        |
| **userInfo**     |  UserInfo   | User's Information                                     |

#### CategoryInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **categoryId**          |  `Integer`  |    Category's id   |
| **categoryName**        |  `String`   |    Category's name |

#### UserInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **userId**              |  `Integer`  |    User's id   |
| **userName**            |  `String`   |    User's name |

### Example

#### Request
```
curl -X POST https://localhost:8080/api/v1/schedules \
  -H "Content-Type: application/json" \
  -d '{
      "userId": 123,      
      "categoryName": "Work",
      "content": "updated content body",
      "dueDate": "2024-11-05",
      "priority": "Medium",
      "status": "Pending"
    }'
```

#### Response
```
HTTP/1.1 201 CREATED
Content-Type: application/json;charset=UTF-8

{
  "success" : true,
  "message" : "Response Message",
  "result" : {
    "taksId": 123,
    "content": "Content body",
    "dueDate": "2024-11-05",
    "priority": "High",
    "status": "Pending",
    "categoryInfo": {
      "categoryId": 1,
      "categoryName": "Work",
    },
    "userInfo": {
       "userId": 123.
       "username": "youngjun",
    },
  }
}
```

## Get Schedule By ID

| Method     | URL                                                 | 
| ---------- |-----------------------------------------------------| 
| `GET`     | http://localhost:8080/api/v1/schedules/{scheduleId} | 


### Parameters

| Name           | Type        |
|----------------| ----------- |
| **scheduleId** | `Interger`  |

### Response Body

#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  | Indicates whether the request was successful.  |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name             | Type          | Description                                            |
|------------------| ------------- |--------------------------------------------------------|
| **scheduleId**   |  `Integer`    | Schedule's id                                          |
| **content**      |  `String`     | Schedule's Content                                 |
| **dueDate**      |  `String`     | Schedule's Deadline Date                           |
| **priority**     |  `String`     | Taks's Priority ["Low", "Medium", "High"]              |
| **status**       |  `String`     | Schedule's Status ["Pending", "Progress", "Completed"] |
| **categoryInfo** |  CategoryInfo | Taks's Category                                        |
| **userInfo**     |  UserInfo     | User's Information                                     |

#### CategoryInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **categoryId**          |  `Integer`  |    Category's id   |
| **categoryName**        |  `String`   |    Category's name |

#### UserInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **userId**              |  `Integer`  |    User's id   |
| **userName**            |  `String`   |    User's name |


### Example

#### Request

```
curl -X GET https://localhost:8080/api/v1/schedules/1
```

#### Response

```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success" : true,
  "message" : "Response Message",
  "result" : {
    "taksId": 123,
    "content": "Content body",
    "dueDate": "2024-11-05",
    "priority": "High",
    "status": "Pending",
    "categoryInfo": {
      "categoryId": 1,
      "categoryName": "Work",
    },
    "userInfo": {
       "userId": 123.
       "username": "youngjun",
    },
  }
}
```

## Get Schedules

| Method     | URL                                                                                                                                                 | 
| ---------- |-----------------------------------------------------------------------------------------------------------------------------------------------------| 
| `GET`      | http://localhost:8080/api/v1/schedules/{scheduleId}?userId={userId}&priority={priority}&status={status}&page={page}&lastScheduleId={lastScheduleId} | 


### Query

| Name                    | Type        |   Required    |
| ----------------------- | ----------- |  ------------ |
| **userId**              | `String`    |  Y |
| **priority**            | `String`    |  N |
| **status**              | `String`    |  N |
| **page**                | `Interger`  |  Y |

### Response Body

#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  |   Indicates whether the request was successful.  |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name          | Type        | Description                           |
|---------------|-------------|---------------------------------------|
| **isLast**    | `Boolean`   | True, If the list is last items in DB |
| **schedules** | `Schedules` | Schedules's List                      |

#### Schedules

| Name             | Type        | Description                                            |
|------------------| ----------- |--------------------------------------------------------|
| **scheduleId**   |  `Integer`  | Schedule's id                                          |
| **content**      |  `String`   | Schedule's Content                                 |
| **dueDate**      |  `String`   | Schedule's Deadline Date                           |
| **priority**     |  `String`   | Taks's Priority ["Low", "Medium", "High"]              |
| **status**       |  `String`   | Schedule's Status ["Pending", "Progress", "Completed"] |
| **categoryInfo** |  CategoryInfo  | Taks's Category                                        |
| **userInfo**     |  UserInfo   | User's Information                                     |

#### CategoryInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **categoryId**          |  `Integer`  |    Category's id   |
| **categoryName**        |  `String`   |    Category's name |

#### UserInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **userId**              |  `Integer`  |    User's id   |
| **userName**            |  `String`   |    User's name |


### Example

#### Request
```
curl -X GET https://localhost:8080/api/v1/schedules?userId=12&priority=Medium&status=Completed&page=1
```

#### Response
```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success" : true,
  "message" : "Response Message",
  "result" : {
    "taksId": 123,
    "content": "Content body",
    "dueDate": "2024-11-05",
    "priority": "High",
    "status": "Pending",
    "categoryInfo": {
      "categoryId": 1,
      "categoryName": "Work",
    },
    "userInfo": {
       "userId": 123.
       "username": "youngjun",
    },
  }
}
```

## Update Schedule By Id

| Method     | URL                                                 | 
| ---------- |-----------------------------------------------------| 
| `PUT`      | http://localhost:8080/api/v1/schedules/{scheduleId} | 

### Parameters

| Name           | Type        |
|----------------| ----------- |
| **scheduleId** | `Interger`  |

### Request Body


| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       Y       |
| **categoryId**          | `Interger`  |       Y       |
| **content**             | `String`    |       Y       |
| **dueDate**             | `String`    |       Y       |
| **priority**            | `String`    |       Y       |
| **status**              | `String`    |       Y       |

### Response Body 

#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  |   Indicates whether the request was successful.  |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name             | Type        | Description                                            |
|------------------| ----------- |--------------------------------------------------------|
| **scheduleId**   |  `Integer`  | Schedule's id                                          |
| **content**      |  `String`   | Schedule's Content                                 |
| **dueDate**      |  `String`   | Schedule's Deadline Date                           |
| **priority**     |  `String`   | Taks's Priority ["Low", "Medium", "High"]              |
| **status**       |  `String`   | Schedule's Status ["Pending", "Progress", "Completed"] |
| **categoryInfo** |  CategoryInfo  | Taks's Category                                        |
| **userInfo**     |  UserInfo   | User's Information                                     |

#### CategoryInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **categoryId**          |  `Integer`  |    Category's id   |
| **categoryName**        |  `String`   |    Category's name |

#### UserInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **userId**              |  `Integer`  |    User's id   |
| **userName**            |  `String`   |    User's name |

### Example

#### Request
```
curl -X PUT https://localhost:8080/api/v1/schedules/1 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 123,    
    "categoryId": 1,
    "content": "updated content body",
    "dueDate": "2024-11-05",
    "priority": "Medium",
    "status": "Pending"
    }'
```


#### Response

```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success" : true,
  "message" : "Response Message",
  "result" : {
    "taksId": 123,
    "content": "updated content body",
    "dueDate": "2024-11-05",
    "priority": "High",
    "status": "Pending",
    "categoryInfo": {
      "categoryId": 1,
      "categoryName": "Work",
    },
    "userInfo": {
       "userId": 123.
       "username": "youngjun",
    },
  }
}
```

## Update Schedule's Priority By Id

| Method     | URL                                                 | 
| ---------- |-----------------------------------------------------| 
| `PATCH`      | http://localhost:8080/api/v1/schedules/{scheduleId} | 


### Parameters

| Name           | Type        |
|----------------| ----------- |
| **scheduleId** | `Interger`  |


### Request Body

| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       Y       |
| **priority**            | `String`    |       Y       |


### Response Body

#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  |   Indicates whether the request was successful.  |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name             | Type        | Description                                            |
|------------------| ----------- |--------------------------------------------------------|
| **scheduleId**   |  `Integer`  | Schedule's id                                          |
| **content**      |  `String`   | Schedule's Content                                 |
| **dueDate**      |  `String`   | Schedule's Deadline Date                           |
| **priority**     |  `String`   | Taks's Priority ["Low", "Medium", "High"]              |
| **status**       |  `String`   | Schedule's Status ["Pending", "Progress", "Completed"] |
| **categoryInfo** |  CategoryInfo  | Taks's Category                                        |
| **userInfo**     |  UserInfo   | User's Information                                     |

#### CategoryInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **categoryId**          |  `Integer`  |    Category's id   |
| **categoryName**        |  `String`   |    Category's name |

#### UserInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **userId**              |  `Integer`  |    User's id   |
| **userName**            |  `String`   |    User's name |


### Example

#### Request
```
curl -X FETCH https://localhost:8080/api/v1/schedules/1 \
  -H "Content-Type: application/json" \
  -d '{
      "userId": 123,      
      "priority": "High",    
    }'
```

#### Response
```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success" : true,
  "message" : "Response Message",
  "result" : {
    "taksId": 123,
    "content": "updated content body",
    "dueDate": "2024-11-05",
    "priority": "High",
    "status": "Pending",
    "categoryInfo": {
      "categoryId": 1,
      "categoryName": "Work",
    },
    "userInfo": {
       "userId": 123.
       "username": "youngjun",
    },
  }
}
```


## Delete Schedule By Id

| Method     | URL                                                 | 
| ---------- |-----------------------------------------------------| 
| `DELETE`      | http://localhost:8080/api/v1/schedules/{scheduleId} | 


### Request Parameters

| Name           | Type        |
|----------------| ----------- |
| **scheduleId** | `Interger`  |

### Request Body


| Name                    | Type        |   Required    |
| ----------------------- | ----------- | ------------- |
| **userId**              | `Interger`  |       Y       |


### Response Body
#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  |   Indicates whether the request was successful.  |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name           | Type        | Description   |
|----------------| ----------- |---------------|
| **scheduleId** |  `Integer`  | Schedule's id |

### Example
#### Request

```
curl -X DELETE https://localhost:8080/api/v1/schedules/1 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 123,    
    }'
```

#### Response
```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success": true,
  "message": "Response Message",
  "result": {
    "scheduleId": 0
  }
}
```

## Get All Categories

| Method     | URL                                  | 
| ---------- | ------------------------------------ | 
| `GET`      | http://localhost:8080/api/v1/categories| 

### Response Body

#### Main

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **success**             |  `Boolean`  |   Indicates whether the request was successful.  |
| **message**             |  `String`   | Response's Message |
| **result**              |  `Result`   | Response's Result  |

#### Result

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **cateogries**          |  `Categories`  |  List of Category Info |

#### CategoryInfo

| Name                    | Type        |     Description    |
| ----------------------- | ----------- | ------------------ |
| **categoryId**          |  `Integer`  |    Category's id   |
| **categoryName**        |  `String`   |    Category's name |


### Example
#### Request

```
curl -X GET https://localhost:8080/api/v1/categories
```

#### Response
```
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success": true,
  "message": "Response Message",
  "categories": [
    {
      "categoryId": 1,
      "categoryName": "Work",
    },
    {
      "categoryId": 2,
      "categoryName": "Hobby",
    },
    {
      "categoryId": 3,
      "categoryName": "Chore",
    }
  ]
}
```

## Error Code 

| HTTP Status | Message               | Description                                           |
|-------------|-----------------------|-------------------------------------------------------|
| 400         | Invalid request data  | One or more required fields are missing or invalid    |
| 400         | Invalid parameter     | An invalid or missing parameter, e.g., `scheduleId`   |
| 401         | Unauthorized access   | Incorrect or missing authentication credentials       |
| 401         | Permission denied     | User lacks permission to modify this schedules        |
| 403         | Forbidden             | User action not allowed                               |
| 404         | User not found        | User with the given ID was not found                  |
| 404         | Schedule not found    | Schedule with the specified ID does not exist         |
| 404         | Category not found    | Category with the specified ID does not exist         |
| 409         | Duplicate entry       | User or schedules already exists                      |
| 500         | Internal server error | General server error                                  |
| 503         | Service unavailable   | Database or required service is currently unavailable |


