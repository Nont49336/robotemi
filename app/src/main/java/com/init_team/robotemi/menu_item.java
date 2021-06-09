package com.init_team.robotemi;

public class menu_item {
    private String Menu_title;
    private int Menu_pic_id;

    public menu_item() {

    }

    public menu_item(String menu_title, int menu_picture_id) {
        Menu_title = menu_title;
        Menu_pic_id = menu_picture_id;
    }

    public String getMenu_title() {
        return Menu_title;
    }

    public Integer getMenu_pic_id() {
        return Menu_pic_id;
    }

    public void setMenu_title(String menu_title) {
        Menu_title = menu_title;
    }

    public void setMenu_pic_id(int menu_pic_id)
    {
        Menu_pic_id = menu_pic_id;
    }
}
