package com.estsoft.blogproject.tdd;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String pw;
    private List<ICoupon> coupons;

    public User(String id, String pw){
        coupons = new ArrayList<>();
    }

    public int getTotalCouponCount() {
        return coupons.size();
    }


    public void addCoupon(ICoupon coupon) {
        if(coupon.isValid()){

            coupons.add(coupon);
        }

    }
}
