/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.localizae.model;

/**
 *
 * @author marca
 */
public class Main {

    public static void main(String[] args) throws Exception {
        int n = 6;
        int[][] mat = {{1,1,1,1,1,1}, {1,1,1,1,1,1}, {1,1,1,1,1,1}, {1,1,1,1,1,1}, {1,1,1,1,1,1}, {1,1,1,1,1,1}};
        int soma = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(j > i && (i+j) < n-1){
                    soma += mat[i][j];
                }
            }
        }
        System.out.println(soma);
    }
}
