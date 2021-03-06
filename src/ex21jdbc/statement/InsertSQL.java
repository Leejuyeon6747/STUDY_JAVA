package ex21jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertSQL
{ 
	// 멤버변수
	Connection con; // DB연결을 위한 객체
	Statement stmt; // 쿼리 전송 및 실행을 위한 객체 

	// 생성자 객체가 생성될 때 생성
	public InsertSQL()
	{
		try 
		{
			// 1. 오라클 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");

			// 2. 커넥션 객체를 통해 DB연결
			con = DriverManager.getConnection("jdbc:oracle:thin://@localhost:1521:orcl", "kosmo", "1234");
			System.out.println("오라클 DB 연결 성공");
		} catch (ClassNotFoundException e)
		{
			// ojdbc6.jar파일이 없거나 연동되지 않았을 때 예외발생
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e)
		{
			// 커넥션 URL이나 계정명이 잘못되었을 때 발생되는 예외
			System.out.println("DB연결 실패");
			e.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("알 수 없는 예외 발생");
		}
	}

	// 쿼리 작성 및 실행 메소드
	public void execute()
	{
		try
		{
			// 3. Statement객체 생성을 위한 메소드 호출
			stmt = con.createStatement();

			// 4. SQL(쿼리문)문 작성
			String sql = "insert into member values ('test3', '2222', '이길동', sysdate)";

			// 5. 쿼리 실행 및 결과값 반환
			/*
			 * excuteUpdate() : 쿼리문이 insert/update/delete와 같이 기존레코드에 영향을
			 * 미치는 쿼리를 실행할 때 사용하낟. 실행 후 영향을 받은 행의 갯수(int)가 반환한다.
			 * 
			 * excuteQuery() : 쿼리문이 select일 때 호출하는 메소드로 레코드에 영향을 미치지 않는
			 * 쿼리를 실행한다. 즉, 조회만 진행하고, 반환타입은 ResultSet이다.
			 */
			int affected = stmt.executeUpdate(sql);
			System.out.println(affected + "행이 입력되었습니다.");
		} catch (SQLException e)
		{
			System.out.println("쿼리실행에 문제가 발생하였습니다.");
			e.printStackTrace();
		} finally
		{
			// 6. 자원반납
			close();
		}
	}

	private void close()
	{
		try
		{
			if (stmt != null) // Statement객체 자원 반납
				stmt.close();
			if (con != null) // Connection객체 자원 반납
				con.close();
			System.out.println("DB자원 반납 완료");
		} catch (SQLException e)
		{
			System.out.println("자원반납시 오류가 발생하였습니다.");
		}
	}

	public static void main(String[] args)
	{
		// 실행방법 1 : 객체생성 후 참조변수에 대입 후 메소드 호출
//		InsertSQL iSQL = new InsertSQL();
//		iSQL.execute();

		// 실행방법 2 : 참조변수 없이 객체생성과 동시에 메소드 호출
		new InsertSQL().execute();
	}

}
