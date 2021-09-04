package ru.bulkashmak.util;

public enum ProductCategory {

    MOTHERBOARDS("Материнские платы"),
    COMPUTER_CASES("Корпуса"); ////a[text()='Корпуса']/following-sibling::ul/li/a[text()='AEROCOOL']


    String sectionName;

    ProductCategory(String sectionName){
        this.sectionName = sectionName;
    }
}
