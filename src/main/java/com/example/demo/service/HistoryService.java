package com.example.demo.service;

import com.example.demo.model.History;
import com.example.demo.model.History;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface HistoryService {
    public History createHistory(History history);
    public Optional<History> getHistoryById(Long id);
    public List<History> getAllHistories();
    public void deleteHistory(Long id);

}
