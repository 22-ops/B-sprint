//搜索书的全部信息-----小程序首页全部书籍
    public static ArrayList<BookInfo> searchbook() {

        Connection conn = null;
        Statement stmt = null;

        ArrayList<BookInfo> rst = new ArrayList<BookInfo>();
        try {

            Class.forName(JDBC_DRIVER); //加载数据库驱动

            System.out.println("allInfo:正在连接数据库......");//连接数据库
            conn = DriverManager.getConnection(DB_URL, USER, PASS);//得到连接对象
            stmt = conn.createStatement();
            StringBuffer sql = new StringBuffer("select  book.xuhao,bookname,zongliang,yuliang,sj_number,leixing,image FROM book,book_image where book.xuhao=book_image.xuhao");
            
            ResultSet rs = stmt.executeQuery(sql.toString());//执行sql语句
            while (rs.next()) {
                BookInfo dto = new BookInfo();//创建BookInfo的对象
                dto.setXuhao(rs.getInt("xuhao"));//赋值
                dto.setBookname(rs.getString("bookname"));//赋值
                dto.setZongliang(rs.getInt("zongliang"));//赋值
                dto.setYuliang(rs.getInt("yuliang"));
                dto.setSj_number(rs.getString("sj_number"));
                dto.setLeixing(rs.getString("leixing"));
                dto.setImage(rs.getString("image"));
                rst.add(dto);//添加
            }
            rs.close();//关闭
            stmt.close();//关闭
            conn.close();//关闭
            System.out.println("      allInfo:连接数据库成功......");
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
                if (conn != null) conn.close();//关闭数据库连接
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return rst;
    }