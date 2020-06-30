package mybatis;

import org.springframework.stereotype.Service;

@Service
public interface MybatisMemberImpl
{
	public MemberVO login(String id, String pass);

}
