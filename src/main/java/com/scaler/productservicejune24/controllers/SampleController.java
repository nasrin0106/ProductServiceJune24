
package com.scaler.productservicejune24.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping ("/say")
public class SampleController {

    @GetMapping("/hello/{name}/{times}")
    public String SayHello(@PathVariable("name") String name,@PathVariable("times") int times){
        String output="";
        for(int i=0;i<times;i++) {
            output = output+"Hello" + name;
        }
        return output;
    }
    @GetMapping("/bye")
    public String SayBye(){
        return "Bye Everyone";
    }
}
