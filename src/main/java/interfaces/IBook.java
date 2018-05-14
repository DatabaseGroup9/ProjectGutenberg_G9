/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.City;

import java.util.List;

/**
 *
 * @author Cherry Rose Semeña
 */
public interface IBook {

  List<ICity>  getCities();
  void  setCities(List<ICity> cities);
  String getId();
  void setId(String id);
  String getTitle();
  void setTitle(String title);
  String getAuthor();
  void setAuthor(String author);
}
