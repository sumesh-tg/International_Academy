/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author 100018
 */
public class HelloWorld {

    String message;

    /**
     *
     * @return
     */
    public String getMessage() {
        System.out.println("Your message is :: " + message);
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorld{" + "message=" + message + '}';
    }

}
