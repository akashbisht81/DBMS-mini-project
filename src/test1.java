import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test1 {
    public static void main(String args[]) throws SQLException {


        int paperid = 0;
        int tpaperid = 0;
        boolean st = false;

        Connection conn = null;


        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        ResultSet rs3 = null;
        ResultSet rs4= null;
        String abc = null;
        String abc1 = null;
        String xyz = null;
        String xyz1 = null;

        conn = DBUtil.getConnection(DBType.MYSQLDB);

        String an= null;
        int pd= 0;
        int pd1= 0;
        int rr = 0;
        String name = null;
        int ad = 0;
        String cy = null;
        String re = null;
        String tr = null;



        /*
        String sql = "Select * from author where a_email= ? and a_pwd=? ";
        pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pstmt.setString(1, "aa7@g.com");
        pstmt.setInt(2, 1234);
        rs = pstmt.executeQuery();
        */

        String sql2 = "Select * from review";
        PreparedStatement statement2 = conn.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs1 = statement2.executeQuery();

        String sql3 = "select * from review r where rv_id= ? ";
                //"and p.paper_id=r.p_id and rr.rvr_id=r.Rrvr_id";
        PreparedStatement statement3 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        String sql4 = "select rvr_first_name from reviewer rrr,review r where r.rvr_id= ? and rrr.rvr_id=r.Rrvr_id ";
        //"and p.paper_id=r.p_id and rr.rvr_id=r.Rrvr_id";
        PreparedStatement statement4 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        String sql5 = "select * from paper p, review r,author a where paper_id= ? and p.paper_id=r.p_id and p.author_id=a.a_id";
        //"and p.paper_id=r.p_id and rr.rvr_id=r.Rrvr_id";
        PreparedStatement statement5 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        rs1 = statement2.executeQuery();
        while(rs1.next()) {
            pd = rs1.getInt("rv_id");

            System.out.println("pd");
            statement3.setInt(Integer.parseInt("1"),pd);

            rs2 = statement3.executeQuery();

            while (rs2.next()) {


                //an=rs2.getString("a_first_name");

                pd1 = rs2.getInt("p_id");
                rr = rs2.getInt("Rrvr_id");

                cy = rs2.getString("clarity");
                re = rs2.getString("relevance");
                tr = rs2.getString("technical_rate");


                //System.out.println("<form action=\"http://localhost:8080/reviewsystem\" method=\"post\">");


                statement4.setInt(Integer.parseInt("1"),rr);

                rs3 = statement4.executeQuery();

                while (rs3.next()) {

                   //name= rs3.getString("2");
                   System.out.println(rs3.next());

                }

                statement5.setInt(Integer.parseInt("1"),pd1);
                rs4=statement5.executeQuery();
                while (rs4.next()){

                  //  ad = rs4.getInt("2");

                }

            }

            System.out.println(
                    "author name is " + ad + "|" +
                            " paper id is " + "" + pd1 + " || Reviewer is " + name + "|" + "" +
                            "" + " Clarity is " + cy + "|" +
                            "" + " Relevance is " + re + "|" +
                            "" + " technical_rate is " + tr + "|" +
                            //out.println("<a href=\"https://www.w3schools.com\"> </a>");
                            //Paper no: "+paperid+"
                            //"    <td><input type=\"submit\" name=\"element\" value=\"Review this paper\"\"</td>\n" +
                            "");

        }
    }
}
