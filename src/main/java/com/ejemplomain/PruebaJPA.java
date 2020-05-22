//
//
//package com.ejemplomain;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import motor.entities.Documento;
//import motor.entities.Posteo;
///**
// *
// * @author nacho
// */
//
//public class PruebaJPA 
//{
//    // no deberia hacer falta las variables emf y manager con este @
//    @PersistenceContext(unitName = "JPA_PU")
//    //
//    private static EntityManager manager;
//    private static EntityManagerFactory emf;
//    public static void main(String[] args) 
//    {
//        // Probar si es necesario hacer esto
//        emf = Persistence.createEntityManagerFactory("JPA_PU");
//        manager = emf.createEntityManager();
//        //
//        
//        List<Documento> documentos = (List<Documento>)manager.createQuery("SELECT d FROM Documento d").getResultList();
//        System.out.println("En esta base de datos hay: " + documentos.size() + " documentos"); 
//        
//        List<Posteo> posteos = (List<Posteo>)manager.createQuery("SELECT p FROM Posteo p").getResultList();
//        System.out.println("En la base de datos hay: " + posteos.size() + " posteos realizados");
//        
//        
//    }
//    
//}
