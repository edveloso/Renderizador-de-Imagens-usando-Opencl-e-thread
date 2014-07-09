/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.IOException;

/**
 *
 * @author Lavinia
 */
public class JExecutaJar {

    public void executaJavaApp() throws IOException {
         Runtime.getRuntime().exec("java -jar C:/AppsPrjFinal/EstouroJvmJava.jar");
    }

    public void executaJavaThReadApp() throws IOException {
        Runtime.getRuntime().exec("java -jar C:/AppsPrjFinal/EstouroJvmJavaTHREAD.jar");
    }

    public void executaJOCLApp() throws IOException {
        Runtime.getRuntime().exec("java -jar C:/AppsPrjFinal/EstouroJvmJOCL.jar");
    }
}
