package com.mySpringWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlaceController {
    @RequestMapping(value="placecircle.do")
    public String placeCircle() {
        return "customMap";
    }

    @RequestMapping(value="placeRecommend.do")
    public String placeRecommend(
            Model model
    ) {
        return "placeRecommend";
    }
}
