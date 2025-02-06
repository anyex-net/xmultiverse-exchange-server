package com.anyex.amap.model;

import lombok.Data;

@Data
public class AmapAreaModel {

    String province ;
    String city ;
    String district ;
    String township; // 乡镇信息可能不一定返回
}
