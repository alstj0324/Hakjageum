package com.mySpringWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlaceController {
    @RequestMapping(value="placeRecommend.do")
    public String placeRecommend() {
        return "placeRecommend";
    }
}
