package WeekRoute.planer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("main")
    public String hello(Model model) {
        return "index";
    }
}
//    @GetMapping("hello-string")
//    @ResponseBody // body부에 직접 데이터를 넣겠다.
//    public String helloString(@RequestParam(value = "name") String name) {
//        return "hello " + name;
//    }
//    @GetMapping("hello-api")
//    @ResponseBody
//    public Hello helloApi(@RequestParam("name") String name) {
//        Hello hello = new Hello();
//        hello.setName(name);
//        return hello;
//    }
//   static class Hello {
//            private String name;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }