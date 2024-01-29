package com.flipkart.exceptions;

import com.flipkart.bean.Customer;

public class CustomerDneException extends  Exception{
    public CustomerDneException()
    {
        super("CustomerId does not exist");

    }
}
