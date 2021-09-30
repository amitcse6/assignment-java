package com.am.assignment.serviceImpl;


import com.am.assignment.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${file.storage.dir}")
    private String DIR;

    public String getDIR() {
        return DIR;
    }
}
