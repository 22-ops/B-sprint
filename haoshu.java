//搜索好书信息-----对应小程序活动板块
    public static ArrayList<GoodBook> searchgoodbook() {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<GoodBook> rst = new ArrayList<GoodBook>();
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("goodbook:连接数据库......");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            StringBuffer sql = new StringBuffer("select  * FROM  good_book");
            ResultSet rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                GoodBook dto = new GoodBook();
                dto.setId(rs.getInt("id"));
                dto.setTitle(rs.getString("title"));
                dto.setUser_id(rs.getString("user_id"));
                dto.setUser_content(rs.getString("user_content"));
                dto.setTime(rs.getString("time"));
                dto.setImage(rs.getString("image"));
                rst.add(dto);
            }
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("      goodbook:连接数据库成功......");
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