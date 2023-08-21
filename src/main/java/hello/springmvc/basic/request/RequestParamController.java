package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok"); // 다른 응답 형식 pdf 2,3,4 차이 적기

    }

    @ResponseBody // 반환값을 http 메세지로 넣어 바로 반환, restController와 같은 효과
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                     @RequestParam("age") int age){
            log.info("username={}, age={}", memberName, age);
            return "ok";
    }

    @ResponseBody // 반환값을 http 메세지로 넣어 바로 반환, restController와 같은 효과
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 반환값을 http 메세지로 넣어 바로 반환, restController와 같은 효과
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String memberName, int age){ // String, int, Integer등의 단순타입은 @RequestParam 생략 가능
        log.info("username={}, age={}", memberName, age);
        return "ok";
    }

    @ResponseBody // 반환값을 http 메세지로 넣어 바로 반환, restController와 같은 효과
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam String memberName,
            @RequestParam(required = false) int age){ // String, int, Integer등의 단순타입은 @RequestParam 생략 가능
        // but int형에는 null값이 대입되지 않아서 위 age의 타입은 기본 타입 int 말고 객체형 Integer 필요
        // 요청 url에 '파라미터명=' 이 있으면 빈 문자로 통과됨
        log.info("username={}, age={}", memberName, age);
        return "ok";
    }

    @ResponseBody // 반환값을 http 메세지로 넣어 바로 반환, restController와 같은 효과
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String memberName,
            @RequestParam(required = false, defaultValue = "-1") Integer age){
        // 파라미터에 빈문자를 주더라도 디폴트값이 들어감
        log.info("username={}, age={}", memberName, age);
        return "ok";
    }

    @ResponseBody // 반환값을 http 메세지로 넣어 바로 반환, restController와 같은 효과
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("hellodata={}", helloData); // 롬복덕에 자동으로 toString() 적용됨

        return "ok";
    }
    @ResponseBody
    @RequestMapping("/model-attribute-v1.5")
    public String modelAttributeV1Re(@ModelAttribute HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("hellodata={}", helloData); // 롬복덕에 자동으로 toString() 적용됨

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV1(HelloData helloData){
        // 스프링은 @ 생략시 다음과 같음 int, String, Integer 같은 단순타입이 @RequestParam, 그 외 @ModelAttribute

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("hellodata={}", helloData); // 롬복덕에 자동으로 toString() 적용됨

        return "ok";
    }


}
