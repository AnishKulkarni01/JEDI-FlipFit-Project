package com.flipkart.dao;

public interface PaymentDAOInterface {
    /**
     *
     * @param amount
     * @param customerId
     * @return
     */
    public boolean makePayment(int amount, String customerId);
    public boolean getPaymentDetails(String paymentId);
}
