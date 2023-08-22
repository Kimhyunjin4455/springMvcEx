package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/request-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!!");
        return mav;
    }

    @RequestMapping("/request-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!!!");
        return "response/hello"; // 논리 뷰, @ResponseBody 사용하면 저 문자열 자체가 응답 화면에 나타남
    }

    @RequestMapping("/request/hello") // 컨트롤러의 명과 뷰의 논리적 이름이 같고 반환값이 없으면 요청매핑의 인자값이 논리적 뷰 이름이 됨
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello!!!");
    } // 권장x




}
