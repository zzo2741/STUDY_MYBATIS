package mybatis;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public interface MybatisDAOImpl
{
	/*
	 * 방명록 리스트에서 사용할 메소드를 추상메소드로 정의함.
	 * 아래 추상메소드를 통해 컨트롤러는 Mapper의 각 엘리먼트를 호출하게 된다.
	 */
	public int getTotalCount();

	public ArrayList<MyBoardDTO> listPage(int s, int e);
}
