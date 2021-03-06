package net.craftingstore;

import com.google.gson.annotations.SerializedName;

public class Donation {

    private String command;

    @SerializedName("mc_name")
    private String username;

    private String uuid;

    @SerializedName("package_name")
    private String packageName;

    @SerializedName("package_price")
    private int packagePrice;

    @SerializedName("coupon_discount")
    private int couponDiscount;

    public String getCommand() {
        return command;
    }

    public String getMcName() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPackageName() {
        return packageName;
    }

    public int getPackagePrice() {
        return packagePrice;
    }

    public int getCouponDiscount() {
        return couponDiscount;
    }

}
