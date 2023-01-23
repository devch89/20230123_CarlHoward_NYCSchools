package com.example.a20230123_carlhoward_nycschools.view;

public interface Listeners {
    void openDetails(String dbn, String name,String loc, String email, String phone);
    interface ListClickEvent{
        void clickDetails(String dbn, String name,String loc, String email, String phone);
    }
}
