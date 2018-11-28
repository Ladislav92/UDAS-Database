package ba.rs.udas.database;

import ba.rs.udas.database.model.database.ConnectionManager;
import ba.rs.udas.database.model.database.DataAdapter;
import ba.rs.udas.database.model.database.MySqlAdapter;
import ba.rs.udas.database.model.member.Member;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
//TODO import Dao, member, DBConnection etc...


/**
 * In current implementation login is handled from client side, for productivity sake and its temporary.
 * Rpc Servlet communicates with post requests with UDAS database client in JSON format:
 * 
 *  {key : {param1, param2}}
 * 
 * Where params can be valid json objects too or lists
 */
public class RpcServlet extends HttpServlet{

    DataAdapter sqlAdapter = new MySqlAdapter();
    String databaseUser = "root";
    String databasePassword = "";
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

        response.setContentType("text/html");
            
        //This whole connection manager thing is stupid - TODO in version two alongside with token login system
        try {
            ConnectionManager.connect(databaseUser, databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //JSONOBject responseAsJSON = toJSONObject(request.getReader());
        
        //TODO get first key from json object 
        String command = "a"; // responseAsJSON.getKey();
        
        switch(command){
            
            case "addMember": // For example - name has to be same as in DAO
                Member newMember = null; //TODO = jsonToMemberObject(jsonResponse.getValue());
                try {
                    boolean succeed = sqlAdapter.addMember(newMember);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //buildResponse(command, succeed); //TODO

                break;
            case "TODO": 
                //rest of commands
                break;
        
                // ...
        
            default:
                //malformed command response failed or something!
        }

    }

    /**
     * TODO try-with-hecking-resources !
     *  Also belongs to some utility class
     * @param reader
     * @return
     */
//    public JSONObject toJSONObject(BufferedReader reader){
//        StringBuffer jb = new StringBuffer();
//        String line = null;
//        try {
//
//          while ((line = reader.readLine()) != null)
//            jb.append(line);
//        } catch (Exception e) { /*report an error*/ }
//
//        try {
//          JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
//        } catch (JSONException e) {
//          // crash and burn
//          throw new IOException("Error parsing JSON request string");
//        }
//    }

}


