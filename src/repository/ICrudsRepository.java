/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;


/**
 *
 * @author MATRIX COMPUTER
 */
public interface ICrudsRepository<T> {
    boolean create(T data);
    List<T> readAll();
    boolean update(T newData, int oldData);
    boolean delete(int id);
    T search(int id);
}
