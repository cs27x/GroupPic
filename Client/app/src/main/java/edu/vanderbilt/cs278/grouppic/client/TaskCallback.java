/* 
**
** Copyright 2014, Jules White
**
** 
*/
package edu.vanderbilt.cs278.grouppic.client;

public interface TaskCallback<T> {

    public void success(T result);

    public void error(Exception e);

}
