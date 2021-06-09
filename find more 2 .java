//搜索对应书籍的信息———计算机、数学、中文、外语、护理、体育、经济
    public static ArrayList<BookInfo> booktype(String leixing) {
        Connection conn = null;
        Statement stmt = null;
        //   ArrayList<BookInfo> rst = new ArrayList<BookInfo>();
        ArrayList<BookInfo> rst = new ArrayList<BookInfo>();
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("booktype:连接数据库......");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            StringBuffer sql = new StringBuffer("select book.xuhao,bookname,zongliang,yuliang,sj_number,leixing,image from   book ,book_image where book.xuhao=book_image.xuhao and leixing='" + leixing + "'");
            ResultSet rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                BookInfo dto = new BookInfo();
                dto.setXuhao(rs.getInt("xuhao"));
                dto.setBookname(rs.getString("bookname"));
                dto.setZongliang(rs.getInt("zongliang"));
                dto.setYuliang(rs.getInt("yuliang"));
                dto.setSj_number(rs.getString("sj_number"));
                dto.setLeixing(rs.getString("leixing"));
                dto.setImage(rs.getString("image"));
                rst.add(dto);
            }
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("      booktype:连接数据库成功......");
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