//收藏的书
  public static ArrayList<Shoucang_book> shoucang_book(String number) {
      Connection conn = null;
      Statement stmt = null;
      Connection conn1 = null;
      Statement stmt1 = null;
      boolean flag=false;
      ArrayList<Shoucang_book> rst = new ArrayList<Shoucang_book>();
              try {
          Class.forName(JDBC_DRIVER);
          System.out.println("shoucang_book:正在连接数据库......");
          conn = DriverManager.getConnection(DB_URL, USER, PASS);
          stmt = conn.createStatement();
          conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
          stmt1 = conn1.createStatement();
          StringBuffer sql = new StringBuffer("select  sc.xuhao,content,image,book.bookname from  sc,book_content,book_image,book  where  sc.xuhao=book.xuhao and sc.xuhao=book_content.xuhao and sc.xuhao=book_image.xuhao and sc.number='"+ number + "'");
          String  s="select * from sc where  number='"+number+"'";
          ResultSet rs = stmt.executeQuery(sql.toString());
          ResultSet s1 = stmt1.executeQuery(s.toString());
          if(s1.isBeforeFirst()) {
              flag=true;
          }
          while (rs.next()) {
              Shoucang_book  dto = new Shoucang_book();
              dto.setXuhao(rs.getInt("xuhao"));
              dto.setBookname(rs.getString("bookname"));
              dto.setContent(rs.getString("content"));
              dto.setImage(rs.getString("image"));
              dto.setFlag(flag);
              rst.add(dto);
          }
          rs.close();
          s1.close();
          stmt.close();
          conn.close();
          stmt1.close();
          conn1.close();
          System.out.println("      shoucang_book:连接数据库成功......");
      } catch (SQLException se) {
          se.printStackTrace();
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              if (stmt != null) stmt.close();
          } catch (SQLException se2) {
          }
          try {
              if (conn != null) conn.close();
          } catch (SQLException se) {
              se.printStackTrace();
          }
      }
      return rst;
  }
}