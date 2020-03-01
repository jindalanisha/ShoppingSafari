package com.example.shoppingsafari;


public class myproduct {
    private int _id;
    private String productname;
    private int shelf;
    public myproduct(){

    }
    public myproduct(String name, int shelf)
    {
        this.productname = name;
        this.shelf = shelf;
    }
    public void set_id(int id){
        this._id = id;
    }
    public int get_id(){
        return _id;
    }
    public String get_productname(){
        return productname;
    }
    public int getShelf(){
        return shelf;
    }
}