package com.example.convertor.entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResultsHolder {
    private final Map<String, Map<String,String>> holder = new HashMap<>();

    public void put(String name, Map<String,String> result) {
        holder.put(name,result);
    }

    public Map<String,String> get(String name) {
        return holder.get(name);
    }
}
