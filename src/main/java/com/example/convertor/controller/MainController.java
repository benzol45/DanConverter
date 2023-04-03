package com.example.convertor.controller;

import com.example.convertor.entity.Problem;
import com.example.convertor.service.ReportConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        model.addAttribute("problems", extractProblemList(result.get("problem")));

        return "result";
    }

    private List<Problem> extractProblemList(String string) {
        return Arrays.stream(string.split("\\|"))
                .map(s -> s.trim())
                .filter(s -> s.length() > 0)
                .map(s -> {
                    Problem p = new Problem();
                    p.deserialize(s);
                    return p;
                })
                .collect(Collectors.toList());
    }
}
