package mybatis;

// 파라미터 처리를 위한 DTO객체
public class ParameterDTO
{
	private String user_id; // 사용자 아이디
	private String board_idx; // 게시판 일련번호
	// 검색어 처리를 위한 멤버변수
	private String search_field; // 검색할 필드명
	private String search_txt; // 검색어
	// select구간을 위한 멤버변수
	private int start_num; // select의 시작
	private int end_num; // select의 끝

	public int getStart_num()
	{
		return start_num;
	}

	public void setStart_num(int start_num)
	{
		this.start_num = start_num;
	}

	public int getEnd_num()
	{
		return end_num;
	}

	public void setEnd_num(int end_num)
	{
		this.end_num = end_num;
	}

	public String getSearch_field()
	{
		return search_field;
	}

	public void setSearch_field(String search_field)
	{
		this.search_field = search_field;
	}

	public String getSearch_txt()
	{
		return search_txt;
	}

	public void setSearch_txt(String search_txt)
	{
		this.search_txt = search_txt;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getBoard_idx()
	{
		return board_idx;
	}

	public void setBoard_idx(String board_idx)
	{
		this.board_idx = board_idx;
	}
}
