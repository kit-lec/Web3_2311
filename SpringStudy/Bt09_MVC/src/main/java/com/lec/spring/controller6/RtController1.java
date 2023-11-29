package com.lec.spring.controller6;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 참고: Http 통신을 위한 자바진영 대표적인 라이브러리들.
 *
 * HttpURLConnection : 자바에서 제공하는 기본 객체
 * Retrofit2  :  안드로이드에서 많이 사용됨.
 * Volley  :  안드로이드에서 많이 사용됨.
 * OkHttp
 * RestTemplate : 스프링 에서 제공
 */

/**
 * RestTemplate
 *   Http 통신을 위한 객체  (Synchronous 통신)
 *
 *   공식 레퍼런스 : https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
 *
 *   주요 메소드
 *      1. request method 별 메소드들
 *          .getForEntity(...)  .getForObject(...)
 *          .postForEntity(...) .postForObject(...)  .postForLocation(...)
 *          .put(...)
 *          .patchForObject(...)
 *          .delete(...)
 *          .optionsForAllow(...)
 *          .headForHeaders(...)
 *
 *      2. xxxForEntity() 와 xxxForObject() 의 차이
 *
 *          공통점: response 타입을 지정해야 함.
 *              첫번째 매개변수 url 은 String 이거나 URI객체
 *
 *          차이점: 리턴타입
 *            .getForEntity(url, Class<T> responseType)
 *                - 리턴타입 : ResponseEntity<T> .  header. body 등의 상세한 정보가 담겨 있다.
 *
 *            .getForObject(url, Class<T> responseType)
 *                - 리턴타입 : T.   response body 만 원할 경우
 *
 *      2. exchange() 메소드들
 *        아래 메소드들은 모두 ResponseEntity<T> 를 리턴
 *          exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
 *          exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String,?> uriVariables)
 *          exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables)
 *          exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Map<String,?> uriVariables)
 *          exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType)
 *          exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType)
 *          exchange(RequestEntity<?> entity, Class<T> responseType)
 *          exchange(RequestEntity<?> entity, ParameterizedTypeReference<T> responseType)
 *
 */


@RestController
public class RtController1 {

    public static final String HTTPBIN_URL = "https://httpbin.org";

    @RequestMapping("api/test01")
    public String apiTest01(){

        RestTemplate rt = new RestTemplate();
        // ※ 등장하는 메소드들을 함 보자
        //   궁극적으로는 exchange() 를 사용하려 하는거지만, 간단하게 사용할수 있는 메소드들도 있다.

        // T <- getForObject(String uri, Class<T>)

        String result = rt.getForObject(HTTPBIN_URL + "/get", String.class);

        return result;
    }

    @RequestMapping("api/test02")
    public String apiTest02(){
        // URI,  URI 를 만들이 위해  UriComponentsBuilder
        URI uri = UriComponentsBuilder
                .fromUriString(HTTPBIN_URL)
                .path("/get")
                .build()    // UriComponents 리턴
                .toUri();

        RestTemplate rt = new RestTemplate();

        var result = rt.getForObject(uri, String.class);
        return result;
    }

    // ResponseEntity<T> 로 받아오기
    @RequestMapping("api/test03")
    public String apiTest03(){
        URI uri = UriComponentsBuilder
                .fromUriString(HTTPBIN_URL)
                .path("/get")
                .build()    // UriComponents 리턴
                .toUri();

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> result = rt.getForEntity(uri, String.class);

        printResponseEntity(result);

        return result.getBody();
    }

    private void printResponseEntity(ResponseEntity response) {
        System.out.println("""
                status code: %s
                headers: %s
                body: %s
                """.formatted(
                        response.getStatusCode(),   // HttpStatusCode
                        response.getHeaders(),      // HttpHeaders
                        response.getBody()          // T
        ));
    }

    // JSON 을 Java 객체로 받아오기

    @Data
    public static class HttpBinModel{
        String origin;
        String url;
    }

    @RequestMapping("api/test04")
    public HttpBinModel apiTest04(){
        URI uri = UriComponentsBuilder
                .fromUriString(HTTPBIN_URL)
                .path("/get")
                .build()    // UriComponents 리턴
                .toUri();

        RestTemplate rt = new RestTemplate();

        ResponseEntity<HttpBinModel> result = rt.getForEntity(uri, HttpBinModel.class);

        System.out.println(result.getBody());
        //printResponseEntity(result);

        return result.getBody();
    }


    // UriComponentsBuilder 를 통해 query string 넘겨주기
    //   https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/util/UriComponentsBuilder.html
    //   queryParam(String name, Object... values)
    //   queryParam(String name, Collection<?> values)
    //   queryParams(MultiValueMap<String, String> params)
    @RequestMapping("api/test05")
    public String apiTest05(){
        URI uri = UriComponentsBuilder
                .fromUriString(HTTPBIN_URL)
                //.path("/get?name=John&age=34")  // 안된다!
                .path("/get")

                // queryParam(name, Object...)
                .queryParam("name", "John")
                .queryParam("age", 23)
                .queryParam("name", "Susan")  // name=John&name=Susan
                .queryParam("score", 80, 90, 100)  // score=80&score=90&score=100

                // encoding 해야 한다.
                .queryParam("nick", "번개")
                .encode()

                .build()
                .toUri();

        RestTemplate rt = new RestTemplate();
        return rt.getForObject(uri, String.class);
    }



}














