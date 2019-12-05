package me.jy.web;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author jy
 * @date 2018/02/14
 */
@RestController
public class ModelAttributeController {

    @ModelAttribute("className")
    public String setClassName() {
        return getClass().getSimpleName();
    }

    @ModelAttribute
    public void setModelAttr(Map<String, Object> map) {
        map.put("teacher", "mg");
    }

    @ModelAttribute
    public String setStringAttr() {
        return "str";
    }

    @ModelAttribute
    public String setStringAttr2() {
        return "another str";
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getAllAttr(Model model) {
        model.asMap().forEach((k,v)-> System.out.println(k+":"+v));
    }
}
