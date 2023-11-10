package com.example.demo.service.serviceImpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.History;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Override
    public History createHistory(History history) {
        return historyRepository.save(history);
    }



    @Override
    public Optional<History> getHistoryById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<History> getAllHistories() {
        return null;
    }

    @Override
    public void deleteHistory(Long id) {

    }
}
