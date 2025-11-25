/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.taurus.interfac;


/**
 *
 * @author victoraugustonunes
 * Foi utilizado na interface polimorfismo paramétrico, devido o parametro ser
 * um objeto
 */
public interface Icrud<T> {
    
    public void salvar(T obj);
    public void editar(T obj);
    public void excluir(T obj);
    
}
