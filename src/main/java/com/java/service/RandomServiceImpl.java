package com.java.service;

import com.java.Enum.EnumAbs;
import com.java.Model.AutoNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RandomServiceImpl implements RandomService {

    private static final String CONSTANT = " 116 RUS";
    private static final Integer COUNT = 6;
    private static final Integer MAX = 10;
    private static final Integer MIN = 0;

    @Autowired
    private AutoNumber autoNumber;

    @Override
    public String getRandomNumber() {
        return randomABS();
    }
    private String randomABS(){
        String str = Arrays.toString(EnumAbs.values());
        String[] abs = str.replaceAll("[,.\\]\\[ ]", "").split("");
        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder result = new StringBuilder();
        StringBuilder prom1 = new StringBuilder();
        StringBuilder prom2 = new StringBuilder();
        for(int i=0; i < COUNT; i++){
            if(i >= 1 && i <= 3){
                int num = rnd(MIN, MAX-1);
                result.append(number[num]);
                prom1.append(number[num]);
            }else {
                int absnum = rnd(MIN, MAX);
                result.append(abs[absnum]);
                prom2.append(abs[absnum]);
            }
        }
        autoNumber.setNum(prom1.toString());
        autoNumber.setAbs(prom2.toString());
        result.append(CONSTANT);
        autoNumber.setAutoNumber(result.toString());
        return autoNumber.getAutoNumber();
    }


    private int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
