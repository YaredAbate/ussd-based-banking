package com.example.demo.controller;

import com.example.demo.model.History;
import com.example.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/History")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @GetMapping
    public ResponseEntity<List<History>> getAllHistory(){
        List<History> getAllHistory=this.historyService.getAllHistories();
        return ResponseEntity.ok(getAllHistory);
    }
    @GetMapping("/{id}")
    public Optional<History> getHistoryById(@PathVariable long id){
        Optional<History> getHistoryById=this.historyService.getHistoryById(id);
        return getHistoryById;
    }
   /* @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody History history){
        History createdHistory=this.historyService.createHistory(history);
        return ResponseEntity.ok(createdHistory);
    }*/
    /*@DeleteMapping("/{id}")
    public void deleteHistory(@PathVariable long id){
        this.historyService.deleteHistory(id);
    }*/
}
