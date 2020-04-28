package com.whp.buyshop.utils.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 10:02
 * @descrpition :
 */
public enum BuyEnum {
    jrtj("今日推荐", 0, "api-jinrituijian"), miaosha("秒杀", 1, "api-miaosha"), jptg("精品团购", 2, "api-jingpintuangou");
    private String name;
    private Integer code;
    private String mine;
    public static final Map<Integer, BuyEnum> CODE_ENUM_MAP = new HashMap<>();

    static {
        for (BuyEnum anonymous : BuyEnum.values()) {
            CODE_ENUM_MAP.put(anonymous.code, anonymous);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }

    BuyEnum(String name, Integer code, String mine) {
        this.setCode(code);
        this.setName(name);
        this.setMine(mine);
    }
}
