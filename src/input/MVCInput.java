/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import bangundatar.Persegi;

/**
 * @author Catur Rahmat
 */
public class MVCInput {
    public MVCInput() {
        InputView inputView = new InputView();//Instansiasi Obyek Input View
        InputController inputController = new InputController(inputView);//Instansiasi Obyek Input Controller
        //Input Controller dengan parameter Input View untuk Menghubungkan View dan Controller(Event Handling)
    }
}
