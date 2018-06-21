/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13.data.dao;

/**
 *
 * @author HP PC
 */
import java.sql.*;
import java.util.ArrayList;
import javaapplication13.data.Connection;
import javaapplication13.data.entidades.Materia;
public class Materias {
    private final String TABLE_NAME="materias";
    private final String nombre="nombre";
    private final String id="id";
    private final String uv="uv";
    
    public ArrayList<Materia> getMaterias(){
        java.sql.Connection con=null;
        ArrayList<Materia> materias=new ArrayList<>();
        
        try{
            con=Connection.getInstance().getConnection();
            Statement statement=con.createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+ TABLE_NAME);
            
            while(resultSet.next()){
                Materia materia=new Materia();
                materia.setId(resultSet.getInt(id));
                materia.setNombre(resultSet.getString(nombre));
                materia.setUv(resultSet.getInt(uv));
                materias.add(materia);
                
            }
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(con!=null){
                try{
                    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
                
            }
        }
        return materias;
    }
    
    public ArrayList<Materia> getNombre(String nombre){
        java.sql.Connection con=null;
        ArrayList<Materia> materias=new ArrayList<>();
        
        try{
            con=Connection.getInstance().getConnection();
            
            String query= "SELECT * FROM " + TABLE_NAME + " WHERE " + nombre + " LIKE ?";
            PreparedStatement statement=con.prepareStatement(query);
            
            
            statement.setString(1, nombre);
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                Materia materia=new Materia();
                materia.setId(resultSet.getInt(id));
                materia.setNombre(resultSet.getString(nombre));
                materia.setUv(resultSet.getInt(uv));
                materias.add(materia);
                
                resultSet.close();
                
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(con!=null){
                try{
                    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return materias;
    }
    
    public void insert(Materia materia){
        java.sql.Connection con=null;
        try{
            con=Connection.getInstance().getConnection();
            PreparedStatement statement=con.prepareStatement("INSERT INTO "+TABLE_NAME+ " (" + nombre + ", "+uv+" )"+ " VALUES(?,?)" );
            statement.setString(1,materia.getNombre());
            statement.setInt(2, materia.getUv());
            
            statement.execute();
            
            statement.close();
         }catch(SQLException e){
             e.printStackTrace();
         }finally{
            if(con!=null){
                try{
                    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
                
            }
        }
    }
    
}

