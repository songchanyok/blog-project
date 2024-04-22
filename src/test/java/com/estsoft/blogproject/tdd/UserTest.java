package com.estsoft.blogproject.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    ICoupon iCoupon;
    @DisplayName("쿠폰 발급 실패(유효하지 않은 쿠폰일 경우)")
    @Test
    public void testNoAddCoupon(){
        User user = new User("area00","pw");
        Mockito.doReturn(false).when(iCoupon).isValid();
        user.addCoupon(iCoupon);
        assertEquals(0,user.getTotalCouponCount());

    }
    @Test
    public void testAddCoupon(){
        User user = new User("area00", "pw");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰수령전

        ICoupon coupon = Mockito.mock(ICoupon.class);
//        BDDMockito.given(coupon.isValid())
//                .willReturn(true);        // isValid() 호출시 리턴값은 true
        Mockito.doReturn(true).when(coupon).isValid();

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount());

        //실패 케이스
        ICoupon coupon2 = Mockito.mock(ICoupon.class);
        Mockito.doReturn(false).when(coupon2).isValid();
        user.addCoupon(coupon2);
        assertEquals(1,user.getTotalCouponCount());

    }


}
