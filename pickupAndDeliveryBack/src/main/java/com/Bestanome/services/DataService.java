package com.Bestanome.services;

import org.springframework.stereotype.Service;
import com.Bestanome.Model.Data;

@Service
public class DataService {
    public static void resetData() {
        Data.reset();
    }
}
