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
- Redirect URL

## Build
Install Ant. Run the following command to build the jar file:

    ant build jar

## Usage
**HTTP GET**

```java
Http httpGet = new HttpGet("https://httpbin.org/get");
int responseCode = httpGet.execute();
```
    
**HTTP POST**

```java
Http httpPost = new HttpPost("https://httpbin.org/post");
int responseCode = httpPost.setBody("username=user&password=passwd")
                           .execute();
```
                            
**HTTP PUT**

```java
Http httpPut = new HttpPut("https://httpbin.org/put");
int responseCode = httpPut.setBody("username=user&password=passwd")
                          .execute();
```                          
  
**HTTP DELETE**

```java
Http httpDelete = new HttpDelete("https://httpbin.org/delete?user");
int responseCode = httpDelete.execute();
```
    
## Full Example

```java
try {
    Http http = new HttpPost("http://httpbin.org/post");

    int responseCode = http.setHeader("Accept-Encoding", "gzip, deflate")
                           .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36")
                           .setCookie(new HttpCookie("csrftoken", "951157e1fecfce6d8f9f52587ee27f2a"))
                           .setConnectionTimeout(15000)
                           .setReadTimeout(15000)
                           .setFollowRedirects(true)
                           .setBody("username=user&password=passwd")
                           .execute();

    Map<String, List<String>> responseHeaders;
    List<HttpCookie> responseCookies;
    String responseBody;
    String redirectUrl;

    if (responseCode == HttpURLConnection.HTTP_OK) {
        responseHeaders = http.getResponseHeaders();
        responseCookies = http.getResponseCookies();
        responseBody = http.getResponseBody();
        redirectUrl = http.getRedirectUrl();
    }

    // Process response
}
catch (IOException ex) {
    ex.printStackTrace();
}
```
