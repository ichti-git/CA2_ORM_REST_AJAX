/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author ichti
 */
public class GeneralNotFoundException extends Exception {
    public GeneralNotFoundException(String msg) {
        super(msg);
    }
    public GeneralNotFoundException() {
        super("Entity not found.");
    }
}
