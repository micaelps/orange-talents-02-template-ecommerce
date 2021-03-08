package com.micaelps.ecommerce.integrationUtils;

public abstract class SenderMail {

    /**
     * sending totally fictitious email
     * @param to
     * @param from
     * @param content
     */
    public static void sendDefault(String to, String from, String content){
        System.out.println("to: "+to);
        System.out.println("from: "+from);
        System.out.println("message: "+content);
    }

    public abstract void send(String to, String from, String content);
}
