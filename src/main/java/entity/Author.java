/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Andreas
 */
public class Author {
    
    String authorID;
    String fullName;

    public Author() {
    }

    public Author(String authorID, String fullName) {
        this.authorID = authorID;
        this.fullName = fullName;
    }
    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
}
