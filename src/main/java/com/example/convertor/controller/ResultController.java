package com.example.convertor.controller;

import com.example.convertor.entity.ResultsHolder;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Controller
public class ResultController {
    private final ResultsHolder resultsHolder;

    @Autowired
    public ResultController(ResultsHolder resultsHolder) {
        this.resultsHolder = resultsHolder;
    }

    @GetMapping(value = "result/{filename}.xml",  produces = "application/octet-stream")
    @ResponseBody
    public byte[] test(@PathVariable("filename") String filename) {
        Map<String,String> results = resultsHolder.get(filename);

        return results.get("result").getBytes();
    }
}
