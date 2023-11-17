package com.example.t1.service;

import com.example.t1.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MagicServiceImpl implements MagicService {
    @Override
    public String getString(String in) {
        in = in.replaceAll(" ", "");
        String[] splitIn = in.split("");
        if (in.isEmpty()) {
            throw new BadRequestException("Нельзя передавать пустую строку");
        }
        List<String> listOfSymbols = new ArrayList<>();
        List<Integer> listOfNumbers = new ArrayList<>();
        int count = 1;
        String symbol = splitIn[0];
        for (int i = 1; i < splitIn.length; i++) {
            if (splitIn[i].equals(symbol)) {
                count++;
            } else {
                listOfSymbols.add("\"" + symbol + "\": " + count);
                listOfNumbers.add(count);
                count = 1;
                symbol = splitIn[i];
            }
        }
        listOfSymbols.add("\"" + symbol + "\": " + count);
        listOfNumbers.add(count);
        String[] arrayOfSymbols = listOfSymbols.toArray(new String[0]);
        Integer[] arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i < arrayOfNumbers.length; i++) {
                if (arrayOfNumbers[i - 1] < arrayOfNumbers[i]) {
                    int changingNumber = arrayOfNumbers[i - 1];
                    arrayOfNumbers[i - 1] = arrayOfNumbers[i];
                    arrayOfNumbers[i] = changingNumber;
                    String changingSymbol = arrayOfSymbols[i - 1];
                    arrayOfSymbols[i - 1] = arrayOfSymbols[i];
                    arrayOfSymbols[i] = changingSymbol;
                    flag = true;
                }
            }
        }
        return String.join(", ", arrayOfSymbols);
    }
}
