# UserService web app

Simple web app to fetch data asynchronously from external endpoint https://jsonplaceholder.typicode.com

The result is merged and provided via JSON.

OpenAPI: http://localhost:8080/v3/api-docs

No persistation, tested with hurl (https://hurl.dev/) as an file based API endpoint testing tool.

Debian: ``apt-get install hurl``

Mac: ``brew install hurl``

Usage:
``hurl --test -v user-service.hurl``

Output:
```
> hurl --test -v user-service.hurl
user-service.hurl: Running [1/1]
* ------------------------------------------------------------------------------
* Executing entry 1
*
* Cookie store:
*
* Request:
* GET http://localhost:8080/api/v1/users/1
*
* Request can be run with the following curl command:
* curl 'http://localhost:8080/api/v1/users/1'
*
> GET /api/v1/users/1 HTTP/1.1
> Host: localhost:8080
> Accept: */*
> User-Agent: hurl/4.3.0
>
* Response: (received 2706 bytes in 76 ms)
*
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 07 May 2024 13:21:04 GMT
<
*
* ------------------------------------------------------------------------------
* Executing entry 2
*
* Cookie store:
*
* Request:
* GET http://localhost:8080/api/v1/users/1000
*
* Request can be run with the following curl command:
* curl 'http://localhost:8080/api/v1/users/1000'
*
> GET /api/v1/users/1000 HTTP/1.1
> Host: localhost:8080
> Accept: */*
> User-Agent: hurl/4.3.0
>
* Response: (received 0 bytes in 35 ms)
*
< HTTP/1.1 404
< Content-Length: 0
< Date: Tue, 07 May 2024 13:21:04 GMT
<
*
user-service.hurl: Success (2 request(s) in 112 ms)
--------------------------------------------------------------------------------
Executed files:  1
Succeeded files: 1 (100.0%)
Failed files:    0 (0.0%)
Duration:        112 ms

```