GET http://localhost:8080/api/v1/users/1
HTTP 200
[Asserts]
jsonpath "$.id" == 1
jsonpath "$.name" == "Leanne Graham"
jsonpath "$.posts" count == 10
jsonpath "$.posts[0].userId" == 1

GET http://localhost:8080/api/v1/users/1000
HTTP 404
