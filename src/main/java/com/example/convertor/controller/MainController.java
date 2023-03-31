package com.example.convertor.controller;

import com.example.convertor.service.ReportConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/convert")
public class MainController {
    private final ReportConverter reportConverter;

    @Autowired
    public MainController(ReportConverter reportConverter) {
        this.reportConverter = reportConverter;
    }

    @GetMapping
    public String getPage() {
        return "main";
    }

    @PostMapping
    public String convertFile(@RequestParam("file") MultipartFile file, @RequestParam("company_name") String name, @RequestParam("company_inn") String inn, Model model) {
        Map<String,String> result = reportConverter.convert(file, name, inn);

        model.addAttribute("filename", file.getOriginalFilename().replaceAll("\\.xlsx",".xml"));
        model.addAttribute("error", result.get("error"));
        model.addAttribute("problem", result.get("problem"));

        return "result";
    }
}
