package com.example.shoppingsafari;

public class product_model {

    public String title;
    public String price;
    public String shelf;
    public int image;

    public product_model()
    {

    }

    public product_model(String title,int image)
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
    public String getPrice()
    {
        return this.price;
    }
    public void setPrice(String price)
    {
        this.price=price;
    }
    public String getShelf()
    {
        return this.shelf;
    }
    public void setShelf(String shelf)
    {
        this.shelf=shelf;
    }
}
