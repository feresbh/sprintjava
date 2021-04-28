/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin;

import com.teknikindustries.bulksms.SMS;

/**
 *
 * @author lenovo
 */
public class sms {
    public static void main(String[] args) {
        SMS smsTut = new SMS();
        smsTut.SendSMS("aziz001", "Iwillbeericher1003", "Hello customer this Travminers agency, we are pleased to have you in our comunity, we cant wait to have you among us.For further information please visit our website ", "21627598472", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    }
}
