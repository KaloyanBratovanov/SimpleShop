package ex.shop.web;


import ex.shop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

//    @GetMapping("/")
//    public String index(HttpSession httpSession){
//
//        return httpSession.getAttribute("user") == null ?  "index" : "home";
//    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession,
                              ModelAndView modelAndView){


        if (httpSession.getAttribute("user")== null){
            modelAndView.setViewName("index");
        }else {

            modelAndView.addObject("items",this.itemService.findAllItems() );
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }
}
