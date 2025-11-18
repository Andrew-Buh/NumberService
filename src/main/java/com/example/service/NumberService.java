package com.example.service;

import com.example.dto.ResponseNumber;

public interface NumberService {

    ResponseNumber findNthMinimum(String filePath, int n);

}
