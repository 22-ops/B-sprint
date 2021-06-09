
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
          