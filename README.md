# httprequest [![Build Status](https://travis-ci.org/zulhilmizainuddin/httprequest.svg?branch=master)](https://travis-ci.org/zulhilmizainuddin/httprequest)
Simple, easy to use HttpUrlConnection wrapper for Java and Android.

**Wrappers for:**
- HTTP GET
- HTTP POST
- HTTP PUT
- HTTP DELETE

**With setters and getters for:**
- HTTP headers
- HTTP body (uncompressed, gzip, deflate)
- Cookies
- Connection timeout
- Read timeout
- Follow redirects

## Build
Install Ant. Run the following command to build the jar file:

    ant build jar

## Usage
HTTP GET

```java
Http httpGet = new HttpGet("https://httpbin.org/get");
int responseCode = httpGet.execute();
```
    
HTTP POST

```java
Http httpPost = new HttpPost("https://httpbin.org/post");
int responseCode = httpPost.setBody("username=user&password=passwd")
                           .execute();
```
                            
HTTP PUT

```java
Http httpPut = new HttpPut("https://httpbin.org/put");
int responseCode = httpPut.setBody("username=user&password=passwd")
                          .execute();
```                          
  
HTTP DELETE

```java
Http httpDelete = new HttpDelete("https://httpbin.org/delete?user");
int responseCode = httpDelete.execute();
```
    
