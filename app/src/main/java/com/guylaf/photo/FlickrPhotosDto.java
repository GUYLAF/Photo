package com.guylaf.photo;

import java.util.List;

/**
 * Created by guyla on 25/11/2016.
 */

public class FlickrPhotosDto {
    long page;
    long pages;
    long perpage;
    String total;
    List<PhotoDto> photo;

    public FlickrPhotosDto(long page, long pages, long perpage, String total, List<PhotoDto> photo) {
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
        this.photo = photo;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getPerpage() {
        return perpage;
    }

    public void setPerpage(long perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<PhotoDto> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoDto> photo) {
        this.photo = photo;
    }
}
