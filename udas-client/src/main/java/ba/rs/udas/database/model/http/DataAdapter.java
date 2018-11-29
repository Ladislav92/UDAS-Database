package ba.rs.udas.database.model.http;


/**
 *
 *  This class should use AsyncRequest to do crud operations via http.
 *
 *  POST request should be implemented in JSON:
 *  {methodToCall :{arg1, arg2, arg3}}
 *
 *  Where key (methodToCall) is method name (or full path) to method on server side and
 *  its values arg1, arg2 etc... are arguments in form of JSON objects themselves.
 *
 *  Server should return true/false as key (or, was the command successful or not) and
 *  JSON data as return value.
 *
 *
 *  This class should probably be capable of converting JSON data into objects like Member, Injury etc...
 *
 */

public class DataAdapter {
}
