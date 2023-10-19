package com.mySpringWeb.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
   public static Connection getConnection() {
      
      try {
    	  Class.forName("com.mysql.cj.jdbc.Driver");
          return DriverManager.getConnection("jdbc:mysql://minnowseo.iptime.org:3306/test?useUnicode=true&serverTimezone=Asia/Seoul", "root", "wjdqhqhdks@1");
      }catch(Exception e){
         System.out.println("Exception[Connection]: "+e.getMessage());// 콘솔창에 남기는 로그
      }
      
      return null;
   }
   public static void close(PreparedStatement pstmt, Connection conn) {
      if(pstmt != null) {
         try {
            if(!pstmt.isClosed()) // 닫혀있지 않다면
            	pstmt.close(); // 닫아라
         }catch(Exception e) {// close할때 exception발생가능, try catch사용, try catch는 메소드를 보고 사용한다.
            System.out.println("Exception[pstmt]: "+e.getMessage());//�ܼ�â�� ����� �α�
         }
      }
      if(conn != null) {
         try {
            if(!conn.isClosed()) 
            	conn.close();
         }catch(Exception e) {
            System.out.println("Exception[conn]: "+e.getMessage());
         }
      }
   }
   public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
      if(rs != null) {
         try {
            if(!rs.isClosed()) 
            	rs.close();
         }catch(Exception e) {
            System.out.println("Exception[rs]: "+e.getMessage());
         }
      }
      if(pstmt != null) {
         try {
            if(!pstmt.isClosed()) 
            	pstmt.close(); //pstmt�� �������� ������ �ݱ�
         }catch(Exception e) {
            System.out.println("Exception[pstmt]: "+e.getMessage());
         }
      }
      if(conn != null) {
         try {
            if(!conn.isClosed()) 
            	conn.close();
         }catch(Exception e) {
            System.out.println("Exception[conn]: "+e.getMessage());
         }
      }
   }
   
}