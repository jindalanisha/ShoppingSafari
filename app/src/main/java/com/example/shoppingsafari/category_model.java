package com.example.shoppingsafari;

public class category_model {

    public String title;
    public int image;

    public category_model()
    {

    }

    public category_model(String title,int image)
    {
        this.title=title;
        this.image=image;
    }
    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public int getImage()
    {
        return this.image;
    }
    public void setImage(int image)
    {
        this.image=image;
    }


}
