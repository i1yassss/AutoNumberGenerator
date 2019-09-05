package com.java.service;

import com.java.Enum.EnumAbs;
import com.java.Model.AutoNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NextServiceImpl implements NextService {

    private static final String CONSTANT = " 116 RUS";
    private static final String DEFAULT_ABS = " AAA";
    private static final String DEFAULT_NUM = " 001";

    @Autowired
    private AutoNumber autoNumber;

    @Override
    public String getNextNumber() {
        if (autoNumber.getNum() == null || autoNumber.getAbs() == null) {
            autoNumber.setAbs(DEFAULT_ABS);
            autoNumber.setNum(DEFAULT_NUM);
        } else {
            Integer number = Integer.valueOf(autoNumber.getNum());
            String preAbs = autoNumber.getAbs();
            if (number == 999) {
                number = 0;
                autoNumber.setAbs(getPreAbsCode(preAbs));
            }
            if (number >= 99) {
                number++;
                autoNumber.setNum(number.toString());
            } else if (number < 9) {
                number++;
                autoNumber.setNum("00" + number.toString());
            } else if (number >= 9 && number < 99) {
                number++;
                autoNumber.setNum("0" + number.toString());
            }
        }
        return getTrueFormat();
    }

    private String getTrueFormat(){
        String[] arr = autoNumber.getAbs().split("");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == 1) {
                result.append(autoNumber.getNum());
                result.append(arr[i]);
            }else{
                result.append(arr[i]);
            }
        }
        result.append(CONSTANT);
        return result.toString();
    }

    private String getPreAbsCode(String preAbs) {
        String[] a = preAbs.split("");
        String str = Arrays.toString(EnumAbs.values());
        str = str.replaceAll("[,.\\]\\[ ]", "");
        String[] arr = str.split("");
        ArrayList<String> stringCode = new ArrayList<>();
        for (String gos : a) {
            for (String s : arr) {
                if (gos.compareTo(s) == 0) {
                    stringCode.add(String.valueOf(Arrays.asList(arr).indexOf(s)));
                }
            }
        }
        int pre = 1 + (144 * Integer.valueOf(stringCode.get(0))) + (12 * Integer.valueOf(stringCode.get(1))) + Integer.valueOf(stringCode.get(2));
        int x = pre / 144;
        int y = (pre / 12) % 12;
        int z = pre % 12;
        ArrayList<String> newArr = new ArrayList<>(Arrays.asList(str.split("")));
        return newArr.get(x) + newArr.get(y) + newArr.get(z);
    }
}



