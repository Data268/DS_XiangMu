package com.lll.weidustore.bean;

public class GoodsBanner {
    /**
     * imageUrl : http://172.17.8.100/images/small/banner/cj.png
     * jumpUrl : http://172.17.8.100/htm/lottery/index.html
     * rank : 5
     * title : 抽奖
     */
    private String imageUrl;
    private String jumpUrl;
    private int rank;
    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
