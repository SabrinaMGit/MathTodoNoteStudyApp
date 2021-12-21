package com.example.user.work.data;

import com.example.user.work.models.BlitzrechnenModels;

import java.util.ArrayList;
import java.util.List;

public class BlitzrechnenData {

        public List<BlitzrechnenModels> blitzrechnenDatas = new ArrayList<BlitzrechnenModels>() {
            {
                //blitzrechnenDatas.add(new blitzrechnenModels(0,1,1,2,"plus",true,3));
            }
        };

        public List<BlitzrechnenModels> getBlitzrechnenData() {
            return blitzrechnenDatas;
        }

        public void setBlitzrechnenData(List<BlitzrechnenModels> blitzrechenData) {
            this.blitzrechnenDatas = blitzrechenData;
        }
    }
