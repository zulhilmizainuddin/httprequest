# httprequest [![Build Status](https://travis-ci.org/zulhilmizainuddin/httprequest.svg?branch=master)](https://travis-ci.org/zulhilmizainuddin/httprequest)
Simple and easy to use HttpUrlConnection wrapper for Java and Android.

Wrappers for:

    HTTP GET
    HTTP POST
    HTTP PUT
    HTTP DELETE

## Build
Install Ant. Run the following command to build the jar file:

    ant build jar

## Usage
HTTP GET

    Http httpGet = new HttpGet("https://httpbin.org/get");
    int responseCode = httpGet.execute();
    
HTTP POST

    Http httpPost = new HttpPost("https://httpbin.org/post");
    int responseCode = httpPost.setBody("username=user&password=passwd")
                               .execute();
                            
HTTP PUT

    Http httpPut = new HttpPut("https://httpbin.org/put");
    int responseCode = httpPut.setBody("username=user&password=passwd")
                              .execute();
  
HTTP DELETE

    Http httpDelete = new HttpDelete("https://httpbin.org/delete?user");
    int responseCode = httpDelete.execute();
    